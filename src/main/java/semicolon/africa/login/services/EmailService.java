package semicolon.africa.login.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import semicolon.africa.login.Exception.MessagingException;
import semicolon.africa.login.Mail.EmailSender;
import semicolon.africa.login.data.model.AppUser;
import semicolon.africa.login.dto.request.VerificationMessageRequest;
import semicolon.africa.login.dto.response.MailResponse;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
@Slf4j
@Service("mailgun_service")
@AllArgsConstructor
public class EmailService implements EmailSender {
    private final String DOMAIN = System.getenv("DOMAIN");
    private final String PRIVATE_KEY = System.getenv("MAILGUN_PRIVATE_KEY");
    private AppUserService appUserService;

    @Override
    @Async
    public CompletableFuture<MailResponse> sendHtmlMail(VerificationMessageRequest messageRequest) throws UnirestException {
        HttpResponse<String>  request = Unirest.post("https://api.mailgun.net/v3/" + DOMAIN + "/message")
                .basicAuth("api", PRIVATE_KEY)
                .queryString("from", messageRequest.getSender())
                .queryString("to", messageRequest.getReceiver())
                .queryString("subject", messageRequest.getSubject())
                .queryString("html", messageRequest.getBody())
                .asString();

        log.info("Message sent successfully");
        MailResponse mailResponse = request.getStatus() == 200 ? new MailResponse(true) : new MailResponse(false);
        return CompletableFuture.completedFuture(mailResponse);
    }

    @Override
    @Async
    public CompletableFuture<MailResponse> sendSimpleMail(VerificationMessageRequest messageRequest) throws UnirestException {
        AppUser appUser = new AppUser();
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + DOMAIN + "/messages")
                .basicAuth("api", PRIVATE_KEY)
                .queryString("from", messageRequest.getSender())
                .queryString("to", messageRequest.getReceiver())
                .queryString("subject", messageRequest.getSubject())
                .queryString("text", messageRequest.getBody())
                .asJson();
        log.info("Message sent successfully");
        MailResponse mailResponse = request.getStatus() == 200 ? new MailResponse(true) : new MailResponse(false);
        return CompletableFuture.completedFuture(mailResponse);

    }
}
    //p private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(EmailService.class);

//    @Override
//
//    public void send(String to, String email) {
//        try{
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
//            helper.setText(email,true);
//            helper.setTo(to);
//            helper.setSubject("confirm your email");
//            helper.setFrom("hello@shomoluBoy.com");
//        } catch (  javax.mail.MessagingException e) {
//            log.info("failed to send email", e);
//            throw new IllegalStateException("failed to send email");
//            //e.printStackTrace();
//        }



