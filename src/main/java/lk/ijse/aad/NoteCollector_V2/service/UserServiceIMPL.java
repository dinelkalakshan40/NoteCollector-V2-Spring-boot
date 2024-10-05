package lk.ijse.aad.NoteCollector_V2.service;

import jakarta.transaction.Transactional;
import lk.ijse.aad.NoteCollector_V2.customStatusCodes.SelectedUserAndNoteErrorStatus;
import lk.ijse.aad.NoteCollector_V2.dao.UserDao;
import lk.ijse.aad.NoteCollector_V2.dto.UserStatus;
import lk.ijse.aad.NoteCollector_V2.dto.impl.UserDTO;
import lk.ijse.aad.NoteCollector_V2.entity.impl.UserEntity;
import lk.ijse.aad.NoteCollector_V2.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceIMPL implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private Mapping mapping;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        return mapping.toUserDTO(userDao.save(mapping.toUserEntity(userDTO)));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }

    @Override
    public UserStatus getUser(String userId) {
        if (userDao.existsById(userId)){
            UserEntity selectedUser =userDao.getReferenceById(userId);
            return mapping.toUserDTO(selectedUser);
        }else {
            return new SelectedUserAndNoteErrorStatus(2,"User with id " + userId + " not found");
        }
    }

    @Override
    public void deleteUser(String userId) {
        userDao.deleteById(userId);
    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {
       Optional <UserEntity> tmpUser = userDao.findById(userId);
       if (tmpUser.isPresent()){
           tmpUser.get().setFirstName(userDTO.getFirstName());
           tmpUser.get().setLastName(userDTO.getLastName());
           tmpUser.get().setEmail(userDTO.getEmail());
           tmpUser.get().setPassword(userDTO.getPassword());
           tmpUser.get().setProfilePic(userDTO.getProfilePic());
       }
    }
}
