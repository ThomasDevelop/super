package com.SuperShop.Super.dao;

import com.SuperShop.Super.model.Administrador;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
public interface IJdbcTemplateAdmDAO {
    public Optional<Administrador> findById(String id);
    public Optional<Administrador> findByEmail(String email);
    public void save(Administrador administrador);
    public List<Administrador> findAll();
    public void deleteById(String id);
    public Administrador mapRow(ResultSet rs, int rowNum);
}
