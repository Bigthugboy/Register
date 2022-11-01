package semicolon.africa.login.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import semicolon.africa.login.Mail.EmailSender;
import semicolon.africa.login.controller.token.ConfirmationToken;
import semicolon.africa.login.data.model.AppUser;
import semicolon.africa.login.data.repository.AppUserRepository;
import semicolon.africa.login.data.repository.ConfirmationTokenRepository;
import semicolon.africa.login.dto.request.VerificationMessageRequest;

import java.time.LocalDateTime;
import java.util.UUID;

@Service


public class AppUserService implements AppUserInterface {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailSender emailSender;
    @Autowired
   private ConfirmationTokenRepository confirmationTokenRepository;
    private final static String USER_NOT_FOUND = "user with email %s not found";



    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
    }

    public String singUpUser(AppUser appUser ) throws UnirestException {
    boolean userExist = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
    if(userExist){
        throw  new IllegalStateException("email already taken");
    }
   String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
    appUser.setPassword(encodedPassword);
    appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),appUser);
       confirmationTokenService.saveConfirmationToken(confirmationToken);
        VerificationMessageRequest verificationMessageRequest = VerificationMessageRequest.builder()
                .sender(appUser .getEmail())
                .body(confirmationToken.getToken())
                .receiver(appUser.getEmail())
                .subject("verification message")
                .build();
       emailSender.sendSimpleMail(verificationMessageRequest);
       return token;
    }

    public boolean enableAppUser(AppUser appUser) {
        boolean isConfired = confirmationTokenRepository.findByToken(appUser.getEmail()).isPresent();
       if(isConfired){
       appUser.setEnabled(true);
       }

        return isConfired;
    }

}
