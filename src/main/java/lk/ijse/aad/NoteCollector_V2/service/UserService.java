package lk.ijse.aad.NoteCollector_V2.service;



import lk.ijse.aad.NoteCollector_V2.dto.UserStatus;
import lk.ijse.aad.NoteCollector_V2.dto.impl.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserStatus getUser(String userId);
    void deleteUser(String userId);
    void updateUser(String userId, UserDTO userDTO);
}
