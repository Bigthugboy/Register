//package semicolon.africa.login.services;
//
//import com.mashape.unirest.http.exceptions.UnirestException;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import semicolon.africa.login.Mail.EmailSender;
//import semicolon.africa.login.controller.token.ConfirmationToken;
//import semicolon.africa.login.data.model.AppUser;
//import semicolon.africa.login.data.model.AppUserRole;
//import semicolon.africa.login.dto.request.RegistrationRequest;
//import semicolon.africa.login.validator.EmailValidator;
//
//import java.time.LocalDateTime;
//
//@Service
//@AllArgsConstructor
//public class RegistrationService implements RegistrationInterface {
//    private final  EmailValidator emailValidator;
//    private final ConfirmationTokenService confirmationTokenService;
//   // private final AppUserService appUserService;
//    private final EmailSender emailSender;
//
//
//    public String register(RegistrationRequest request) throws UnirestException {
//        boolean isValidEmail = emailValidator.test(request.getEmail());
//        if (!isValidEmail){
//            throw new IllegalStateException("email is not valid");
//        }
//        return appUserService.singUpUser(new AppUser(request.getFirstName(),request.getLastName(),request.getEmail(),request.getPassword(), AppUserRole.USER));
//
//    }
//
//
////    @Transactional
//
//    public String confirmToken(String token){
//        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(()-> new IllegalStateException("token not found"));
//        if (confirmationToken.getConfirmedAt() != null){
//            throw  new IllegalStateException("email already confirmed");
//        }
//        LocalDateTime expiredAt = confirmationToken.getExpirdAt();
//        if(expiredAt.isBefore(LocalDateTime.now())){
//            throw  new IllegalStateException("token expired");
//        }
//        confirmationTokenService.setConfirmedAt(token);
//        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());
//        return "confirmed";
//
//    }
//}
