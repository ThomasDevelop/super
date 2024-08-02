package com.SuperShop.Super.controller;

import com.SuperShop.Super.dto.ProdutoDTO;
import com.SuperShop.Super.dto.ProdutoListaDTO;
import com.SuperShop.Super.usecase.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;
    @PostMapping
    public ResponseEntity<String> adicionarProduto(@RequestBody ProdutoDTO produtoDTO) {
        produtoService.adicionarProduto(produtoDTO);
        return ResponseEntity.ok("Produto adicionado com sucesso");
    }
    @PostMapping("/listar")
    public ResponseEntity<List<ProdutoListaDTO>> listarProdutos() {
        List<ProdutoListaDTO> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }
}