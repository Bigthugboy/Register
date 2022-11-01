package semicolon.africa.login.controller.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import semicolon.africa.login.data.model.AppUser;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor

@Entity
@AllArgsConstructor
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expirdAt;
//    @Column(nullable = false)
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(nullable = false,name = "app_user_id")
    private AppUser appUser;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expirdAt,   AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expirdAt = expirdAt;

        this.appUser = appUser;
    }
}
