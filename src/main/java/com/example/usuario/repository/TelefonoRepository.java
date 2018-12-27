package com.example.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usuario.model.TelefonoModel;

@Repository
public interface TelefonoRepository extends JpaRepository<TelefonoModel, Long> {

}
