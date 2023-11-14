package com.sd.sistemasd.service.autenticacion;

import com.sd.sistemasd.dao.user.UserDAO;
import com.sd.sistemasd.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder encoder;

    public Iterable<UserEntity> findAll(){return userDAO.findAll();}

    public UserEntity save(UserEntity entity){
        Optional<UserEntity> oUser =this.userDAO.findByUsername(entity.getUsername());
        if(oUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User " + entity.getUsername() + " ya existe");

        }
        String passwordEncode = encoder.encode(entity.getPassword());
        entity.setPassword(passwordEncode);
        entity.setVigencia(true);

        return this.userDAO.save(entity);
    }


}
