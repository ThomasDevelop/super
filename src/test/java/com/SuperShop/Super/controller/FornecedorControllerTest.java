package com.SuperShop.Super.controller;

import com.SuperShop.Super.controller.FornecedorController;
import com.SuperShop.Super.dto.FornecedorDTO;
import com.SuperShop.Super.exception.AdminIdInvalidoException;
import com.SuperShop.Super.model.Fornecedor;
import com.SuperShop.Super.usecase.AdministradorService;
import com.SuperShop.Super.usecase.FornecedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FornecedorControllerTest {

    @Mock
    private FornecedorService fornecedorService;

    @Mock
    private AdministradorService administradorService;

    @InjectMocks
    private FornecedorController fornecedorController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(fornecedorController).build();
    }

    @Test
    public void testAdicionarFornecedor() throws Exception {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(String.valueOf(1L));
        fornecedor.setNome("Fornecedor 1");

        FornecedorDTO fornecedorDTO = new FornecedorDTO();
        fornecedorDTO.setNome("Fornecedor 1");

        when(fornecedorService.adicionarFornecedor(any(FornecedorDTO.class))).thenReturn(fornecedor);

        mockMvc.perform(post("/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Fornecedor 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Fornecedor 1"));
    }

    @Test
    public void testAdicionarFornecedorAdminIdInvalido() throws Exception {
        FornecedorDTO fornecedorDTO = new FornecedorDTO();
        fornecedorDTO.setNome("Fornecedor 1");

        when(fornecedorService.adicionarFornecedor(any(FornecedorDTO.class))).thenThrow(new AdminIdInvalidoException("Admin ID inválido"));

        mockMvc.perform(post("/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Fornecedor 1\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Admin ID inválido"));
    }

    @Test
    public void testListarFornecedores() throws Exception {
        Fornecedor fornecedor1 = new Fornecedor();
        fornecedor1.setId("1");
        fornecedor1.setNome("Fornecedor 1");

        Fornecedor fornecedor2 = new Fornecedor();
        fornecedor2.setId("2");
        fornecedor2.setNome("Fornecedor 2");

        List<Fornecedor> fornecedores = Arrays.asList(fornecedor1, fornecedor2);

        FornecedorDTO fornecedorDTO = new FornecedorDTO();
        fornecedorDTO.setAdminId("1");

        List<Fornecedor> fornecedorList = doReturn(fornecedores).when(fornecedorService).listarFornecedores("1");

        mockMvc.perform(post("/fornecedores/listar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"adminId\": \"1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Fornecedor 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nome").value("Fornecedor 2"));
    }

    @Test
    public void testListarFornecedoresAdminIdInvalido() throws Exception {
        FornecedorDTO fornecedorDTO = new FornecedorDTO();
        fornecedorDTO.setAdminId("1");

        when(fornecedorService.listarFornecedores("1")).thenThrow(new AdminIdInvalidoException("Admin ID inválido"));

        mockMvc.perform(post("/fornecedores/listar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"adminId\": \"1\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Admin ID inválido"));
    }
}
