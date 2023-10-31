package com.sd.sistemad.services;


import com.sd.sistemad.models.Deporte;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;


@Service
public class DeporteService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void guardarDeporte(String nombre) {
        Deporte deporte = new Deporte();
        deporte.setNombre("Padel");
        entityManager.persist(deporte);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
