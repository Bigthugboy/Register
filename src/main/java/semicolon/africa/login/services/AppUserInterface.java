package semicolon.africa.login.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.security.core.userdetails.UserDetails;
import semicolon.africa.login.data.model.AppUser;

public interface AppUserInterface {
    UserDetails loadUserByUsername(String email);
    String singUpUser(AppUser appUser) throws UnirestException;
    boolean enableAppUser(AppUser appUser);

}
