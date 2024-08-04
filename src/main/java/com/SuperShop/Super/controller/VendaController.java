package com.SuperShop.Super.controller;

import com.SuperShop.Super.dto.VendaDTO;
import com.SuperShop.Super.model.ReciboVenda;
import com.SuperShop.Super.model.Venda;
import com.SuperShop.Super.usecase.VendaService;
<<<<<<< HEAD
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
=======
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
<<<<<<< HEAD
@Tag(name = "Venda", description = "API para gerenciamento de vendas")
=======
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
<<<<<<< HEAD
    @Operation(summary = "Adiciona uma nova venda")
=======
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
    public ResponseEntity<ReciboVenda> adicionarVenda(@RequestBody VendaDTO vendaDTO) {
        ReciboVenda recibo = vendaService.adicionarVenda(vendaDTO);
        return ResponseEntity.ok(recibo);
    }
    @GetMapping
<<<<<<< HEAD
    @Operation(summary = "Lista todas as vendas")
=======
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
    public List<Venda> listarVendas() {
        return vendaService.listarVendas();
    }
}
