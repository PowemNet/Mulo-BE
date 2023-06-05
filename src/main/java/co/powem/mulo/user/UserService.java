package co.powem.mulo.user;

import java.util.List;
import java.util.UUID;


public interface UserService {
    User getUser(UUID id);
    User addUser(User user);
    User updateUser(UUID id, User user);
    void deleteUser(UUID id);
    List<User> getUsers();
}
