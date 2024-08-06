package com.SuperShop.Super.dao;

import com.SuperShop.Super.model.Administrador;

import java.util.List;
import java.util.Optional;

public interface IJdbcTemplateAdmDAO {
    Optional<Administrador> findById(String id);
    Optional<Administrador> findByEmail(String email);
    void save(Administrador administrador);
    List<Administrador> findAll();
    void deleteById(String id);
}
