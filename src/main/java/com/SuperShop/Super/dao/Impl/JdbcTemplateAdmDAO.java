package com.SuperShop.Super.dao.Impl;

import com.SuperShop.Super.dao.IJdbcTemplateAdmDAO;
import com.SuperShop.Super.model.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateAdmDAO implements IJdbcTemplateAdmDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Administrador> findById(String id) {
        String sql = "SELECT * FROM administradores WHERE id = ?";
        try {
        Administrador administrador = jdbcTemplate.queryForObject(sql, new Object[]{id}, new AdministradorRowMapper());
        return Optional.ofNullable(administrador);
        } catch (EmptyResultDataAccessException e) {
        return Optional.empty();
        }
    }
    @Override
    public Optional<Administrador> findByEmail(String email) {
        String sql = "SELECT * FROM administradores WHERE email = ?";
        try {
        Administrador administrador = jdbcTemplate.queryForObject(sql, new Object[]{email}, new AdministradorRowMapper());
        return Optional.ofNullable(administrador);
        } catch (EmptyResultDataAccessException e) {
        return Optional.empty();
        }
    }
    @Override
    public void save(Administrador administrador) {
        String sql = "INSERT INTO administradores VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, administrador.getId(), administrador.getNome(), administrador.getEmail(), administrador.getSenha());
    }
    @Override
    public List<Administrador> findAll() {
        String sql = "SELECT * FROM administradores";
        return jdbcTemplate.query(sql, new AdministradorRowMapper());
    }
    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM administradores WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    @Override
    public Administrador mapRow(ResultSet rs, int rowNum) {
        return null;
    }
<<<<<<< HEAD
    public static class AdministradorRowMapper implements RowMapper<Administrador> {
=======
    private static class AdministradorRowMapper implements RowMapper<Administrador> {
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
    @Override
    public Administrador mapRow(ResultSet rs, int rowNum) throws SQLException {
        Administrador administrador = new Administrador();
        administrador.setId(rs.getString("id"));
        administrador.setNome(rs.getString("nome"));
        administrador.setEmail(rs.getString("email"));
        administrador.setSenha(rs.getString("senha"));
        return administrador;
        }
    }
}