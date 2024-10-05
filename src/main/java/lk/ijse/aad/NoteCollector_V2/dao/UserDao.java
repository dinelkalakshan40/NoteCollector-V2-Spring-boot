package lk.ijse.aad.NoteCollector_V2.dao;


import lk.ijse.aad.NoteCollector_V2.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository <UserEntity,String>{
}
