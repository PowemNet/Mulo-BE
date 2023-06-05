package co.powem.mulo.auth;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest { //todo add validations
  @NotNull String firstName;
  @NotNull String lastName;
  @NotNull String email;
  @NotNull String password;
  @NotNull String phoneNumber;
  @NotNull LocalDateTime dateOfBirth;
}
