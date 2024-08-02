package com.SuperShop.Super.dao;

import com.SuperShop.Super.dao.Impl.JdbcTemplateAdmDAO;
import com.SuperShop.Super.model.Administrador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JdbcTemplateAdmDAOTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private JdbcTemplateAdmDAO jdbcTemplateAdmDAO;

    private Administrador administrador;

    @BeforeEach
    public void setup() {
        administrador = new Administrador();
        administrador.setId("1");
        administrador.setNome("Admin");
        administrador.setEmail("admin@example.com");
        administrador.setSenha("password");
    }

    @Test
    public void testFindById() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(JdbcTemplateAdmDAO.AdministradorRowMapper.class)))
                .thenReturn(administrador);

        Optional<Administrador> result = jdbcTemplateAdmDAO.findById("1");
        assertTrue(result.isPresent());
        assertEquals(administrador, result.get());
    }

    @Test
    public void testFindById_NotFound() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(JdbcTemplateAdmDAO.AdministradorRowMapper.class)))
                .thenThrow(new EmptyResultDataAccessException(1));

        Optional<Administrador> result = jdbcTemplateAdmDAO.findById("1");
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindByEmail() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(JdbcTemplateAdmDAO.AdministradorRowMapper.class)))
                .thenReturn(administrador);

        Optional<Administrador> result = jdbcTemplateAdmDAO.findByEmail("admin@example.com");
        assertTrue(result.isPresent());
        assertEquals(administrador, result.get());
    }

    @Test
    public void testFindByEmail_NotFound() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(JdbcTemplateAdmDAO.AdministradorRowMapper.class)))
                .thenThrow(new EmptyResultDataAccessException(1));

        Optional<Administrador> result = jdbcTemplateAdmDAO.findByEmail("admin@example.com");
        assertFalse(result.isPresent());
    }
    @Test
    public void testFindAll() {
        List<Administrador> administradores = Arrays.asList(administrador);
        when(jdbcTemplate.query(anyString(), any(JdbcTemplateAdmDAO.AdministradorRowMapper.class)))
                .thenReturn(administradores);

        List<Administrador> result = jdbcTemplateAdmDAO.findAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(administrador, result.get(0));
    }
}
