package com.SuperShop.Super.controller;

import com.SuperShop.Super.dto.ProdutoDTO;
import com.SuperShop.Super.dto.ProdutoListaDTO;
import com.SuperShop.Super.usecase.ProdutoService;
<<<<<<< HEAD
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
=======
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/produtos")
<<<<<<< HEAD
@Tag(name = "Produto", description = "API para gerenciamento de produtos")
=======
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;
    @PostMapping
<<<<<<< HEAD
    @Operation(summary = "Adiciona um novo produto")
=======
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
    public ResponseEntity<String> adicionarProduto(@RequestBody ProdutoDTO produtoDTO) {
        produtoService.adicionarProduto(produtoDTO);
        return ResponseEntity.ok("Produto adicionado com sucesso");
    }
    @PostMapping("/listar")
<<<<<<< HEAD
    @Operation(summary = "Lista todos os produtos")
=======
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
    public ResponseEntity<List<ProdutoListaDTO>> listarProdutos() {
        List<ProdutoListaDTO> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }
}