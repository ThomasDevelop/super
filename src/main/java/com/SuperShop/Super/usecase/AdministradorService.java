package com.SuperShop.Super.usecase;

import com.SuperShop.Super.dao.Impl.JdbcTemplateAdmDAO;
import com.SuperShop.Super.dto.AdministradorDTO;
import com.SuperShop.Super.exception.CustomException;
import com.SuperShop.Super.model.Administrador;
import com.SuperShop.Super.utils.IdUtils;
import com.SuperShop.Super.utils.NomeUtils;
import com.SuperShop.Super.utils.SenhaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {
    @Autowired
    private JdbcTemplateAdmDAO jdbcTemplateAdmDAO;
    public AdministradorDTO adicionarAdministrador(AdministradorDTO administradorDTO) {
        validarAdministradorDTO(administradorDTO);
        if (jdbcTemplateAdmDAO.findByEmail(administradorDTO.getEmail()).isPresent()) {
        throw new CustomException("Administrador com este email já está cadastrado");
        }
        Administrador administrador = new Administrador();
        administrador.setId(IdUtils.generateId('A'));
        administrador.setNome(administradorDTO.getNome());
        administrador.setEmail(administradorDTO.getEmail());
        String salt = SenhaUtils.generateSalt();
        administrador.setSenha(SenhaUtils.hashPassword(administradorDTO.getSenha(), salt));
        jdbcTemplateAdmDAO.save(administrador);
        AdministradorDTO respostaDTO = new AdministradorDTO();
        respostaDTO.setId(administrador.getId());
        respostaDTO.setNome(administrador.getNome());
        return respostaDTO;
    }
    public List<Administrador> listarAdministradores() {return jdbcTemplateAdmDAO.findAll();
    }
    public Optional<Administrador> buscarAdministradorPorId(String id) {return jdbcTemplateAdmDAO.findById(id);
    }
    public void deletarAdministrador(String id) {
        jdbcTemplateAdmDAO.deleteById(id);
    }
    public Administrador atualizarAdministrador(String id, AdministradorDTO administradorDTO) {
        validarAdministradorDTO(administradorDTO);
        Administrador administradorExistente = jdbcTemplateAdmDAO.findById(id)
            .orElseThrow(() -> new CustomException("Administrador não encontrado"));
        if (jdbcTemplateAdmDAO.findByEmail(administradorDTO.getEmail()).isPresent() &&
            !administradorExistente.getEmail().equals(administradorDTO.getEmail())) {
            throw new CustomException("Administrador com este email já está cadastrado");
        }
        administradorExistente.setNome(administradorDTO.getNome());
        administradorExistente.setEmail(administradorDTO.getEmail());
        String salt = SenhaUtils.generateSalt();
        administradorExistente.setSenha(SenhaUtils.hashPassword(administradorDTO.getSenha(), salt));
        jdbcTemplateAdmDAO.save(administradorExistente);
        return administradorExistente;
    }
    private void validarAdministradorDTO(AdministradorDTO administradorDTO) {
        NomeUtils.validarNome(administradorDTO.getNome());
        if (administradorDTO.getEmail() == null || administradorDTO.getEmail().isEmpty()) {
            throw new CustomException("Email é obrigatório");
        }
        if (!administradorDTO.getEmail().contains("@") || !administradorDTO.getEmail().contains(".com")) {
            throw new CustomException("Email inválido");
        }
        if (administradorDTO.getSenha() == null || administradorDTO.getSenha().isEmpty()) {
            throw new CustomException("Senha é obrigatória");
        }
        if (administradorDTO.getSenha().length() < 8) {
            throw new CustomException("A senha deve ter pelo menos 8 caracteres");
        }
        if (!administradorDTO.getSenha().matches(".*[A-Za-z].*") || !administradorDTO.getSenha().matches(".*\\d.*")) {
            throw new CustomException("A senha deve conter letras e números");
        }
    }
}