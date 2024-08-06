package com.SuperShop.Super.controller;

import com.SuperShop.Super.dto.AdminListaDTO;
import com.SuperShop.Super.dto.AdministradorDTO;
import com.SuperShop.Super.exception.CustomException;
import com.SuperShop.Super.model.Administrador;
import com.SuperShop.Super.usecase.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@Tag(name = "Administrador", description = "API para gerenciamento de administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @PostMapping()
    @Operation(summary = "Adiciona um novo administrador")
    public AdminListaDTO adicionarAdministrador(@Valid @RequestBody AdministradorDTO administradorDTO) {
        AdministradorDTO adminDTO = administradorService.adicionarAdministrador(administradorDTO);
        return mapToResponseDTO(adminDTO);
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todos os administradores")
    public List<AdminListaDTO> listarAdministradores() {
        List<Administrador> administradores = administradorService.listarAdministradores();
        return administradores.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um administrador pelo ID")
    public ResponseEntity<String> deletarAdministrador(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não fornecido");
        }
        try {
            String mensagem = administradorService.deletarAdministrador(id);
            return ResponseEntity.status(HttpStatus.OK).body(mensagem);
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    private AdminListaDTO mapToResponseDTO(AdministradorDTO administradorDTO) {
        AdminListaDTO responseDTO = new AdminListaDTO();
        responseDTO.setId(administradorDTO.getId());
        responseDTO.setNome(administradorDTO.getNome());
        return responseDTO;
    }

    private AdminListaDTO mapToResponseDTO(Administrador administrador) {
        AdminListaDTO responseDTO = new AdminListaDTO();
        responseDTO.setId(administrador.getId());
        responseDTO.setNome(administrador.getNome());
        return responseDTO;
    }
}
