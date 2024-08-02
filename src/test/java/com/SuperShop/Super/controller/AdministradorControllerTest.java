package com.SuperShop.Super.controller;

import com.SuperShop.Super.controller.AdministradorController;
import com.SuperShop.Super.dto.AdministradorDTO;
import com.SuperShop.Super.model.Administrador;
import com.SuperShop.Super.usecase.AdministradorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AdministradorControllerTest {

    @Mock
    private AdministradorService administradorService;

    @InjectMocks
    private AdministradorController administradorController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(administradorController).build();
    }

    @Test
    public void testAdicionarAdministrador() throws Exception {
        AdministradorDTO adminDTO = new AdministradorDTO();
        adminDTO.setId(String.valueOf(1L));
        adminDTO.setNome("Administrador 1");

        when(administradorService.adicionarAdministrador(any(AdministradorDTO.class))).thenReturn(adminDTO);

        mockMvc.perform(post("/admin")
                        .contentType("application/json")
                        .content("{\"nome\": \"Administrador 1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Administrador 1"));
    }

    @Test
    public void testListarAdministradores() throws Exception {
        Administrador admin1 = new Administrador();
        admin1.setId(String.valueOf(1L));
        admin1.setNome("Administrador 1");

        Administrador admin2 = new Administrador();
        admin2.setId(String.valueOf(2L));
        admin2.setNome("Administrador 2");

        List<Administrador> admins = Arrays.asList(admin1, admin2);

        when(administradorService.listarAdministradores()).thenReturn(admins);

        mockMvc.perform(post("/admin/listar")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Administrador 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nome").value("Administrador 2"));
    }
}
