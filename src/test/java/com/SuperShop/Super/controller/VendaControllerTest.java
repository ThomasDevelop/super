package com.SuperShop.Super.controller;

import com.SuperShop.Super.controller.VendaController;
import com.SuperShop.Super.dto.VendaDTO;
import com.SuperShop.Super.model.ReciboVenda;
import com.SuperShop.Super.model.Venda;
import com.SuperShop.Super.usecase.VendaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VendaControllerTest {

    @Mock
    private VendaService vendaService;

    @InjectMocks
    private VendaController vendaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void adicionarVendaTest() {
        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setProduto("Produto Teste");
        vendaDTO.setQuantidade(10);
        vendaDTO.setValorTotal(100.0);

        ReciboVenda reciboVenda = new ReciboVenda();
        when(vendaService.adicionarVenda(any(VendaDTO.class))).thenReturn(reciboVenda);

        ResponseEntity<ReciboVenda> response = vendaController.adicionarVenda(vendaDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(reciboVenda, response.getBody());
        verify(vendaService, times(1)).adicionarVenda(any(VendaDTO.class));
    }

    @Test
    void listarVendasTest() {
        Venda venda1 = new Venda();
        Venda venda2 = new Venda();
        List<Venda> vendas = Arrays.asList(venda1, venda2);

        when(vendaService.listarVendas()).thenReturn(vendas);

        List<Venda> response = vendaController.listarVendas();

        assertEquals(2, response.size());
        assertEquals(vendas, response);
        verify(vendaService, times(1)).listarVendas();
    }
}
