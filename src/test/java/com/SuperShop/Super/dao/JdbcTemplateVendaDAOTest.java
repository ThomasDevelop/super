package com.SuperShop.Super.dao;

import com.SuperShop.Super.dao.Impl.JdbcTemplateVendaDAO;
import com.SuperShop.Super.model.Venda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JdbcTemplateVendaDAOTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private JdbcTemplateVendaDAO jdbcTemplateVendaDAO;

    private Venda venda;

    @BeforeEach
    public void setup() {
        venda = new Venda();
        venda.setId("1");
        venda.setProduto("Produto");
        venda.setQuantidade(5);
        venda.setValorTotal(500.0);
    }

    @Test
    public void testFindAll() {
        List<Venda> vendas = Arrays.asList(venda);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(vendas);

        List<Venda> result = jdbcTemplateVendaDAO.findAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(venda, result.get(0));
    }

    @Test
    public void testFindById() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Arrays.asList(venda));

        Optional<Venda> result = jdbcTemplateVendaDAO.findById("1");
        assertTrue(result.isPresent());
        assertEquals(venda, result.get());
    }

    @Test
    public void testFindById_NotFound() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Arrays.asList());

        Optional<Venda> result = jdbcTemplateVendaDAO.findById("1");
        assertFalse(result.isPresent());
    }
}
