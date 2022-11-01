package semicolon.africa.login.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import semicolon.africa.login.dto.request.RegistrationRequest;

public interface RegistrationInterface {
    String register(RegistrationRequest request) throws UnirestException;
    String confirmToken(String token);
}
