package com.SuperShop.Super.controller;

import com.SuperShop.Super.dto.VendaDTO;
import com.SuperShop.Super.model.ReciboVenda;
import com.SuperShop.Super.model.Venda;
import com.SuperShop.Super.usecase.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
@Tag(name = "Venda", description = "API para gerenciamento de vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    @Operation(summary = "Adiciona uma nova venda")
    public ResponseEntity<ReciboVenda> adicionarVenda(@RequestBody VendaDTO vendaDTO) {
        ReciboVenda recibo = vendaService.adicionarVenda(vendaDTO);
        return ResponseEntity.ok(recibo);
    }
    @GetMapping
    @Operation(summary = "Lista todas as vendas")
    public List<Venda> listarVendas() {
        return vendaService.listarVendas();
    }
}
