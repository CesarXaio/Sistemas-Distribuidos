package com.sd.sistemasd.dao.user;

import com.sd.sistemasd.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
}
