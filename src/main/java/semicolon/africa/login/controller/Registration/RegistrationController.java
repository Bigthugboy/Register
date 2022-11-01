//package semicolon.africa.login.controller.Registration;
//
//import com.mashape.unirest.http.exceptions.UnirestException;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import semicolon.africa.login.dto.request.RegistrationRequest;
//import semicolon.africa.login.services.RegistrationService;
//
//@RestController
//@RequestMapping(path = "api/v1/registration")
//@AllArgsConstructor
//public class RegistrationController {
//
//    private RegistrationService registrationService;
//
//
//   @PostMapping
//    public String register(@RequestBody RegistrationRequest request) throws UnirestException {
//        return registrationService.register(request);
//    }
//    @GetMapping(path = "/confirm/{token}")
//    public  String confirm(@PathVariable String token){
//        System.out.println("this is the token " + token);
//        return registrationService.confirmToken(token);
//   }
//}
