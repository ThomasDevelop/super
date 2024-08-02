package com.SuperShop.Super.usecase;

import com.SuperShop.Super.dao.Impl.JdbcTemplateFornDAO;
import com.SuperShop.Super.dto.FornecedorDTO;
import com.SuperShop.Super.exception.AdminIdInvalidoException;
import com.SuperShop.Super.exception.CustomException;
import com.SuperShop.Super.model.Fornecedor;
import com.SuperShop.Super.utils.CnpjUtils;
import com.SuperShop.Super.utils.IdUtils;
import com.SuperShop.Super.utils.SenhaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private JdbcTemplateFornDAO jdbcTemplateFornDAO;

    @Autowired
    private AdministradorService administradorService;

    public Fornecedor adicionarFornecedor(FornecedorDTO fornecedorDTO) {
        validarAdminId(fornecedorDTO.getAdminId());
        validarFornecedorDTO(fornecedorDTO);
        String cnpjLimpo = fornecedorDTO.getCnpj().replaceAll("[^\\d]", "");
        if (jdbcTemplateFornDAO.findByCnpj(fornecedorDTO.getCnpj()).isPresent()) {
            throw new CustomException("Fornecedor com este CNPJ já está cadastrado");
        }
        if (jdbcTemplateFornDAO.findByEmail(fornecedorDTO.getEmail()).isPresent()) {
            throw new CustomException("Email já existente");
        }
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(IdUtils.generateId('F'));
        fornecedor.setNome(fornecedorDTO.getNome());
        fornecedor.setCnpj(fornecedorDTO.getCnpj());
        fornecedor.setEmail(fornecedorDTO.getEmail());
        String salt = SenhaUtils.generateSalt();
        fornecedor.setSenha(SenhaUtils.hashPassword(fornecedorDTO.getSenha(), salt));
        fornecedor.setSalt(salt);
        fornecedor.setAdminId(fornecedorDTO.getAdminId());
        if (!CnpjUtils.isValidCNPJ(fornecedor.getCnpj())) {
            throw new CustomException("CNPJ inválido");
        }
        jdbcTemplateFornDAO.save(fornecedor);
        return fornecedor;
    }

    public List<Fornecedor> listarFornecedores(String adminId) {
        validarAdminId(adminId);
        return jdbcTemplateFornDAO.findAll();
    }

    public Optional<Fornecedor> buscarFornecedorPorId(String id) {
        return jdbcTemplateFornDAO.findById(id);
    }

    public void deletarFornecedor(String id) {
        jdbcTemplateFornDAO.deleteById(id);
    }

    public Fornecedor atualizarFornecedor(String id, FornecedorDTO fornecedorDTO) {
        validarAdminId(fornecedorDTO.getAdminId());
        validarFornecedorDTO(fornecedorDTO);
        Fornecedor fornecedorExistente = jdbcTemplateFornDAO.findById(id)
                .orElseThrow(() -> new CustomException("Fornecedor não encontrado"));
        String cnpjLimpo = fornecedorDTO.getCnpj().replaceAll("[^\\d]", "");
        if (jdbcTemplateFornDAO.findByCnpj(fornecedorDTO.getCnpj()).isPresent() &&
                !fornecedorExistente.getCnpj().equals(fornecedorDTO.getCnpj())) {
            throw new CustomException("Fornecedor com este CNPJ já está cadastrado");
        }
        fornecedorExistente.setNome(fornecedorDTO.getNome());
        fornecedorExistente.setCnpj(fornecedorDTO.getCnpj());
        fornecedorExistente.setEmail(fornecedorDTO.getEmail());
        String salt = SenhaUtils.generateSalt();
        fornecedorExistente.setSenha(SenhaUtils.hashPassword(fornecedorDTO.getSenha(), salt));
        jdbcTemplateFornDAO.save(fornecedorExistente);
        return fornecedorExistente;
    }
    private void validarFornecedorDTO(FornecedorDTO fornecedorDTO) {
        if (fornecedorDTO.getNome() == null || fornecedorDTO.getNome().isEmpty()) {
            throw new CustomException("Nome é obrigatório");
        }
        if (fornecedorDTO.getEmail() == null || fornecedorDTO.getEmail().isEmpty()) {
            throw new CustomException("Email é obrigatório");
        }
        if (!fornecedorDTO.getEmail().contains("@") || !fornecedorDTO.getEmail().contains(".com")) {
            throw new CustomException("Email inválido");
        }
        if (fornecedorDTO.getSenha() == null || fornecedorDTO.getSenha().isEmpty()) {
            throw new CustomException("Senha é obrigatória");
        }
    }
    private void validarAdminId(String adminId) {
        if (!administradorService.buscarAdministradorPorId(adminId).isPresent()) {
        throw new AdminIdInvalidoException("Admin ID inválido");
        }
    }
}