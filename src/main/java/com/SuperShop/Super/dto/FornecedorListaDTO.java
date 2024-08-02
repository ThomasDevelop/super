package com.SuperShop.Super.dto;

import com.SuperShop.Super.model.Fornecedor;
import lombok.Data;

@Data
public class FornecedorListaDTO {
    private String id;
    private String nome;
    public static FornecedorListaDTO mapToResponseDTO(Fornecedor fornecedor) {
        FornecedorListaDTO responseDTO = new FornecedorListaDTO();
        responseDTO.setId(fornecedor.getId());
        responseDTO.setNome(fornecedor.getNome());
        return responseDTO;
    }
}