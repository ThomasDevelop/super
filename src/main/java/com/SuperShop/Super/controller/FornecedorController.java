package com.SuperShop.Super.controller;

import com.SuperShop.Super.dto.FornecedorDTO;
import com.SuperShop.Super.dto.FornecedorListaDTO;
import com.SuperShop.Super.exception.AdminIdInvalidoException;
import com.SuperShop.Super.model.Fornecedor;
import com.SuperShop.Super.usecase.AdministradorService;
import com.SuperShop.Super.usecase.FornecedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/fornecedores")
@Tag(name = "Fornecedor", description = "API para gerenciamento de fornecedores")
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private AdministradorService administradorService;
    @PostMapping
    @Operation(summary = "Adiciona um novo fornecedor")
    public ResponseEntity<?> adicionarFornecedor(@RequestBody FornecedorDTO fornecedorDTO) {
        try {
            Fornecedor fornecedor = fornecedorService.adicionarFornecedor(fornecedorDTO);
            FornecedorListaDTO responseDTO = FornecedorListaDTO.mapToResponseDTO(fornecedor);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (AdminIdInvalidoException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Admin ID inválido");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    @PostMapping("/listar")
    @Operation(summary = "Lista todos os fornecedores")
    public ResponseEntity<?> listarFornecedores(@RequestBody FornecedorDTO fornecedorDTO) {
        try {
            List<Fornecedor> fornecedores = fornecedorService.listarFornecedores(fornecedorDTO.getAdminId());
            List<FornecedorListaDTO> responseDTOs = fornecedores.stream()
            .map(FornecedorListaDTO::mapToResponseDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
        } catch (AdminIdInvalidoException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Admin ID inválido");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}