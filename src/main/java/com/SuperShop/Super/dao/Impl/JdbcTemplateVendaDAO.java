package com.SuperShop.Super.dao.Impl;

import com.SuperShop.Super.dao.IJdbcTemplateVendaDAO;
import com.SuperShop.Super.model.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateVendaDAO implements IJdbcTemplateVendaDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateVendaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Venda venda) {
        String sql = "INSERT INTO vendas (id, produto, quantidade, valor_total) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, venda.getId(), venda.getProduto(), venda.getQuantidade(), venda.getValorTotal());
    }

    @Override
    public List<Venda> findAll() {
        String sql = "SELECT * FROM vendas";
        return jdbcTemplate.query(sql, new VendaRowMapper());
    }

    @Override
    public Optional<Venda> findById(String id) {
        String sql = "SELECT * FROM vendas WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new VendaRowMapper()).stream().findFirst();
    }

    @Override
    public void update(Venda venda) {
        String sql = "UPDATE vendas SET produto = ?, quantidade = ?, valor_total = ? WHERE id = ?";
        jdbcTemplate.update(sql, venda.getProduto(), venda.getQuantidade(), venda.getValorTotal(), venda.getId());
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM vendas WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class VendaRowMapper implements RowMapper<Venda> {
        @Override
        public Venda mapRow(ResultSet rs, int rowNum) throws SQLException {
            Venda venda = new Venda();
            venda.setId(rs.getString("id"));
            venda.setProduto(rs.getString("produto"));
            venda.setQuantidade(rs.getInt("quantidade"));
            venda.setValorTotal(rs.getDouble("valor_total"));
            return venda;
        }
    }
}
