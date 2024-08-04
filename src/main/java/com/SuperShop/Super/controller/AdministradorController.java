package com.SuperShop.Super.controller;

import com.SuperShop.Super.dto.AdminListaDTO;
import com.SuperShop.Super.dto.AdministradorDTO;
import com.SuperShop.Super.model.Administrador;
import com.SuperShop.Super.usecase.AdministradorService;
<<<<<<< HEAD
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
=======
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
<<<<<<< HEAD
@Tag(name = "Administrador", description = "API para gerenciamento de administradores")
=======
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;
    @PostMapping()
<<<<<<< HEAD
    @Operation(summary = "Adiciona um novo administrador")
=======
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
    public AdminListaDTO adicionarAdministrador(@Valid @RequestBody AdministradorDTO administradorDTO) {
        AdministradorDTO adminDTO = administradorService.adicionarAdministrador(administradorDTO);
        return mapToResponseDTO(adminDTO);
    }

    @PostMapping("/listar")
<<<<<<< HEAD
    @Operation(summary = "Lista todos os administradores")
=======
>>>>>>> 102fa263415c29298c29325f6708a2c12fbc65ae
    public List<AdminListaDTO> listarAdministradores() {
        List<Administrador> administradores = administradorService.listarAdministradores();
        return administradores.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
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