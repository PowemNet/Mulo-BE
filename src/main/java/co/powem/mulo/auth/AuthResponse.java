package co.powem.mulo.auth;

import co.powem.mulo.user.Role;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
  private UUID userId;
  private String email;
  private Role role;
  private String token;
  private Date expiresAt;
}

