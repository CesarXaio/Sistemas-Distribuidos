package com.sd.sistemasd.service.user;

import com.sd.sistemasd.beans.empleado.EmpleadoBean;
import com.sd.sistemasd.dao.empleado.EmpleadoDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private EmpleadoDAO userDao;

    @Transactional
    public Set<String> getUserRoles(String email) {
        Optional<EmpleadoBean> userOptional = userDao.findByEmail(email);
        if (userOptional.isPresent()) {
            EmpleadoBean user = userOptional.get();
            return user.getRoles().stream()
                    .map(role -> role.getName())
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}
