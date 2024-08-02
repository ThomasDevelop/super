package com.SuperShop.Super.dao.Impl;

import com.SuperShop.Super.dao.IJdbcTemplateProdutoDAO;
import com.SuperShop.Super.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateProdutoDAO implements IJdbcTemplateProdutoDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcTemplateProdutoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void save(Produto produto) {
        String sql = "INSERT INTO produtos VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getQuantidade());
    }
    @Override
    public List<Produto> findAll() {
        String sql = "SELECT * FROM produtos";
        return jdbcTemplate.query(sql, new ProdutoRowMapper());
    }
    @Override
    public Optional<Produto> findById(String id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new ProdutoRowMapper()).stream().findFirst();
    }
    @Override
    public void update(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, descricao = ?, preco = ?, quantidade = ? WHERE id = ?";
        jdbcTemplate.update(sql, produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getQuantidade(), produto.getId());
    }
    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    @Override
    public Optional<Produto> findByNomeAndDescricao(String nome, String descricao) {
        String sql = "SELECT * FROM produtos WHERE nome = ? AND descricao = ?";
        return jdbcTemplate.query(sql, new Object[]{nome, descricao}, new ProdutoRowMapper()).stream().findFirst();
    }
    public Optional<Produto> findByNome(String nome) {
        String sql = "SELECT * FROM produtos WHERE nome = ?";
        return jdbcTemplate.query(sql, new Object[]{nome}, new ProdutoRowMapper()).stream().findFirst();
    }
//    @Override
//    public Produto mapRow(ResultSet rs, int rowNum) {
//        return null;
//    }
    private static class ProdutoRowMapper implements RowMapper<Produto> {
    @Override
    public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getString("id"));
        produto.setNome(rs.getString("nome"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setPreco(rs.getDouble("preco"));
        produto.setQuantidade(rs.getInt("quantidade"));
        return produto;
        }
    }
}