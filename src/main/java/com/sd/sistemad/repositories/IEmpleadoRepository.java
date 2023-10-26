package com.sd.sistemad.repositories;

import com.sd.sistemad.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findById(Long id);
}
