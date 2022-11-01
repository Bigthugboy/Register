package semicolon.africa.login.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import semicolon.africa.login.controller.token.ConfirmationToken;
import semicolon.africa.login.data.repository.ConfirmationTokenRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService implements ConfirmationTokenInterface {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        Optional<ConfirmationToken> confirmationToken = getToken(token);
        confirmationToken.ifPresent(value -> value.setConfirmedAt(LocalDateTime.now()));
        confirmationTokenRepository.save(confirmationToken.get());
    }
}