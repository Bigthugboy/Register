package semicolon.africa.login.controller.Registration;

import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import semicolon.africa.login.data.model.AppUser;
import semicolon.africa.login.services.AppUserService;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class AppUserController {
    private AppUserService appUserService;

    @PostMapping
    public String signUp(@RequestBody AppUser request) throws UnirestException {
        return appUserService.singUpUser(request);
    }



}
