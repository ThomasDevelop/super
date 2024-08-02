package com.SuperShop.Super.dao.Impl;

import com.SuperShop.Super.dao.IJdbcTemplateFornDAO;
import com.SuperShop.Super.model.Fornecedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateFornDAO implements IJdbcTemplateFornDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void save(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedores VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, fornecedor.getId(), fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getEmail(), fornecedor.getSenha());
    }
    public Optional<Fornecedor> findById(String id) {
        String sql = "SELECT * FROM fornecedores WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new FornecedorRowMapper()).stream().findFirst();
    }
    public Optional<Fornecedor> findByCnpj(String cnpj) {
        String sql = "SELECT * FROM fornecedores WHERE cnpj = ?";
        return jdbcTemplate.query(sql, new Object[]{cnpj}, new FornecedorRowMapper()).stream().findFirst();
    }
    public Optional<Fornecedor> findByEmail(String email) {
        String sql = "SELECT * FROM fornecedores WHERE email = ?";
        return jdbcTemplate.query(sql, new Object[]{email}, new FornecedorRowMapper()).stream().findFirst();
    }
    public List<Fornecedor> findAll() {
        String sql = "SELECT * FROM fornecedores";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Fornecedor.class));
    }
    public void deleteById(String id) {
        String sql = "DELETE FROM fornecedores WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    @Override
    public Fornecedor mapRow(ResultSet rs, int rowNum) {
        return null;
    }
    private static class FornecedorRowMapper implements RowMapper<Fornecedor> {
    @Override
    public Fornecedor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(rs.getString("id"));
        fornecedor.setNome(rs.getString("nome"));
        fornecedor.setCnpj(rs.getString("cnpj"));
        fornecedor.setEmail(rs.getString("email"));
        fornecedor.setSenha(rs.getString("senha"));
        return fornecedor;
        }
    }
}