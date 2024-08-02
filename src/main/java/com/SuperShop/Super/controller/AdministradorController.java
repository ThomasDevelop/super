package com.SuperShop.Super.controller;

import com.SuperShop.Super.dto.AdminListaDTO;
import com.SuperShop.Super.dto.AdministradorDTO;
import com.SuperShop.Super.model.Administrador;
import com.SuperShop.Super.usecase.AdministradorService;
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
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;
    @PostMapping()
    public AdminListaDTO adicionarAdministrador(@Valid @RequestBody AdministradorDTO administradorDTO) {
        AdministradorDTO adminDTO = administradorService.adicionarAdministrador(administradorDTO);
        return mapToResponseDTO(adminDTO);
    }

    @PostMapping("/listar")
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