package com.SuperShop.Super.dao;

import com.SuperShop.Super.dao.Impl.JdbcTemplateProdutoDAO;
import com.SuperShop.Super.model.Produto;
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
public class JdbcTemplateProdutoDAOTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private JdbcTemplateProdutoDAO jdbcTemplateProdutoDAO;

    private Produto produto;

    @BeforeEach
    public void setup() {
        produto = new Produto();
        produto.setId("1");
        produto.setNome("Produto");
        produto.setDescricao("Descricao");
        produto.setPreco(100.0);
        produto.setQuantidade(10);
    }

    @Test
    public void testFindAll() {
        List<Produto> produtos = Arrays.asList(produto);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(produtos);

        List<Produto> result = jdbcTemplateProdutoDAO.findAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(produto, result.get(0));
    }

    @Test
    public void testFindById() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Arrays.asList(produto));

        Optional<Produto> result = jdbcTemplateProdutoDAO.findById("1");
        assertTrue(result.isPresent());
        assertEquals(produto, result.get());
    }

    @Test
    public void testFindById_NotFound() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Arrays.asList());

        Optional<Produto> result = jdbcTemplateProdutoDAO.findById("1");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByNomeAndDescricao() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Arrays.asList(produto));

        Optional<Produto> result = jdbcTemplateProdutoDAO.findByNomeAndDescricao("Produto", "Descricao");
        assertTrue(result.isPresent());
        assertEquals(produto, result.get());
    }

    @Test
    public void testFindByNomeAndDescricao_NotFound() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Arrays.asList());

        Optional<Produto> result = jdbcTemplateProdutoDAO.findByNomeAndDescricao("Produto", "Descricao");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByNome() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Arrays.asList(produto));

        Optional<Produto> result = jdbcTemplateProdutoDAO.findByNome("Produto");
        assertTrue(result.isPresent());
        assertEquals(produto, result.get());
    }

    @Test
    public void testFindByNome_NotFound() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(Arrays.asList());

        Optional<Produto> result = jdbcTemplateProdutoDAO.findByNome("Produto");
        assertFalse(result.isPresent());
    }
}
