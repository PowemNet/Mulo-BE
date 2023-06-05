package co.powem.mulo.auth;

import co.powem.mulo.exception.UserAlreadyExistsException;
import co.powem.mulo.user.Role;
import co.powem.mulo.user.User;
import co.powem.mulo.user.UserRepository;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User createUser(SignupRequest request) {
    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
      throw new UserAlreadyExistsException(String.format("User with email %s  already exists", request.email));
    }

    User user = new User();
    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setPhoneNumber(request.getPhoneNumber());
    user.setDateOfBirth(request.dateOfBirth);
//    user.setDateOfBirth(request.dateOfBirth.atZone(ZoneOffset.UTC).toInstant());
    user.setRole(Role.USER);
    user.setCreatedBy("system");
    user.setCreatedDate(Instant.now());
    return userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() ->
            new UsernameNotFoundException("User not found with email: " + email)
        );

    return CustomUserDetails.builder().username(user.getEmail()).password(user.getPassword()).userId(user.getId()).role(user.getRole()).build();
  }
}
