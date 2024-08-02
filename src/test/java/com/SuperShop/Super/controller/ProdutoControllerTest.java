package com.SuperShop.Super.controller;

import com.SuperShop.Super.controller.ProdutoController;
import com.SuperShop.Super.dto.ProdutoDTO;

import com.SuperShop.Super.dto.ProdutoListaDTO;
import com.SuperShop.Super.usecase.ProdutoService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }

    @Test
    public void testAdicionarProduto() throws Exception {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Produto 1");

        doNothing().when(produtoService).adicionarProduto(any(ProdutoDTO.class));

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Produto 1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Produto adicionado com sucesso"));
    }

    @Test
    public void testListarProdutos() throws Exception {
        ProdutoListaDTO produto1 = new ProdutoListaDTO();
        produto1.setNome("Produto 1");

        ProdutoListaDTO produto2 = new ProdutoListaDTO();
        produto2.setNome("Produto 2");

        List<ProdutoListaDTO> produtos = Arrays.asList(produto1, produto2);

        when(produtoService.listarProdutos()).thenReturn(produtos);

        mockMvc.perform(post("/produtos/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].nome").value("Produto 1"))
                .andExpect(jsonPath("$[1].nome").value("Produto 2"));
    }
}
