package com.SuperShop.Super.dao;

import com.SuperShop.Super.dao.Impl.JdbcTemplateFornDAO;
import com.SuperShop.Super.model.Fornecedor;
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
public class JdbcTemplateFornDAOTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private JdbcTemplateFornDAO jdbcTemplateFornDAO;

    private Fornecedor fornecedor;

    @BeforeEach
    public void setup() {
        fornecedor = new Fornecedor();
        fornecedor.setId("1");
        fornecedor.setNome("Fornecedor");
        fornecedor.setCnpj("12345678901234");
        fornecedor.setEmail("fornecedor@example.com");
        fornecedor.setSenha("password");
    }

    @Test
    public void testFindById() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(Arrays.asList(fornecedor));

        Optional<Fornecedor> result = jdbcTemplateFornDAO.findById("1");
        assertTrue(result.isPresent());
        assertEquals(fornecedor, result.get());
    }

    @Test
    public void testFindById_NotFound() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(Arrays.asList());

        Optional<Fornecedor> result = jdbcTemplateFornDAO.findById("1");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByCnpj() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(Arrays.asList(fornecedor));

        Optional<Fornecedor> result = jdbcTemplateFornDAO.findByCnpj("12345678901234");
        assertTrue(result.isPresent());
        assertEquals(fornecedor, result.get());
    }

    @Test
    public void testFindByCnpj_NotFound() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(Arrays.asList());

        Optional<Fornecedor> result = jdbcTemplateFornDAO.findByCnpj("12345678901234");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByEmail() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(Arrays.asList(fornecedor));

        Optional<Fornecedor> result = jdbcTemplateFornDAO.findByEmail("fornecedor@example.com");
        assertTrue(result.isPresent());
        assertEquals(fornecedor, result.get());
    }

    @Test
    public void testFindByEmail_NotFound() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(Arrays.asList());

        Optional<Fornecedor> result = jdbcTemplateFornDAO.findByEmail("fornecedor@example.com");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindAll() {
        List<Fornecedor> fornecedores = Arrays.asList(fornecedor);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class)))
                .thenReturn(fornecedores);

        List<Fornecedor> result = jdbcTemplateFornDAO.findAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(fornecedor, result.get(0));
    }
}
