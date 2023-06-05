package co.powem.mulo.auth;

import jakarta.validation.Valid;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthController {
  @Autowired
  private UserService userService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest request) {
    userService.createUser(request);
    CustomUserDetails userDetails = (CustomUserDetails) userService.loadUserByUsername(request.getEmail());

    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    return ResponseEntity.ok(generateAuthResponse(userDetails));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

    return ResponseEntity.ok(generateAuthResponse(userDetails));
  }

  private AuthResponse generateAuthResponse(CustomUserDetails userDetails) {
    final String token = jwtUtil.generateToken(userDetails);
    final Date tokenExpiration = jwtUtil.extractExpiration(token);

    return AuthResponse.builder()
        .userId(userDetails.getUserId())
        .email(userDetails.getUsername())
        .role(userDetails.getRole())
        .token(token)
        .expiresAt(tokenExpiration)
        .build();
  }
}
