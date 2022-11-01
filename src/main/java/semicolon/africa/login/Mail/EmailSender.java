package semicolon.africa.login.Mail;

import com.mashape.unirest.http.exceptions.UnirestException;
import semicolon.africa.login.dto.request.VerificationMessageRequest;
import semicolon.africa.login.dto.response.MailResponse;

import java.util.concurrent.CompletableFuture;

public interface EmailSender {
    CompletableFuture<MailResponse> sendHtmlMail(VerificationMessageRequest messageRequest) throws UnirestException;
    CompletableFuture<MailResponse> sendSimpleMail(VerificationMessageRequest messageRequest) throws UnirestException;
}

