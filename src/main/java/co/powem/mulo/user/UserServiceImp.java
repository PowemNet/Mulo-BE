package co.powem.mulo.user;

import java.util.List;
import java.util.Optional;

import java.util.UUID;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {

    UserRepository userRepository;

    @Override
    public User getUser(UUID id) {
        Optional<User> user = userRepository.findById(id);
       return unwrappedUser(user, id);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        User existingUser = getUser(id);
        userRepository.delete(existingUser);
    }

    @Override
    public User updateUser(UUID id, User user) {
         User existingUser = getUser(id);

         existingUser.setFirstName(user.getFirstName());
         existingUser.setLastName(user.getLastName());
         existingUser.setEmail(user.getEmail());
         existingUser.setPhoneNumber(user.getPhoneNumber());
         existingUser.setRole(user.getRole());
         existingUser.setPassword(user.getPassword());
         existingUser.setDateOfBirth(user.getDateOfBirth());

         return userRepository.save(existingUser);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    static User unwrappedUser(Optional<User> entity, UUID id) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new RuntimeException("User not found for id: " + id);
        }
    }

}
