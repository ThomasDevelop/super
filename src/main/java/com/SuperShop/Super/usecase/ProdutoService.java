package com.SuperShop.Super.usecase;

import com.SuperShop.Super.dao.IJdbcTemplateAdmDAO;
import com.SuperShop.Super.dao.IJdbcTemplateFornDAO;
import com.SuperShop.Super.dao.IJdbcTemplateProdutoDAO;
import com.SuperShop.Super.dto.ProdutoDTO;
import com.SuperShop.Super.dto.ProdutoListaDTO;
import com.SuperShop.Super.exception.CustomException;
import com.SuperShop.Super.model.Produto;
import com.SuperShop.Super.utils.IdUtils;
import com.SuperShop.Super.utils.ProdutoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private IJdbcTemplateProdutoDAO jdbcTemplateProdutoDAO;

    @Autowired
    private IJdbcTemplateAdmDAO jdbcTemplateAdmDAO;

    @Autowired
    private IJdbcTemplateFornDAO jdbcTemplateFornDAO;

    private static final Pattern LETTERS_ONLY_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");
    private static final Pattern REAL_FORMAT_PATTERN = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
    private static final Pattern INTEGER_FORMAT_PATTERN = Pattern.compile("^\\d+$");

    public void adicionarProduto(ProdutoDTO produtoDTO) {
        String precoStr = produtoDTO.getPreco().trim();
        if (!REAL_FORMAT_PATTERN.matcher(precoStr).matches()) {
            throw new CustomException("O preço do produto deve ser em reais.");
        }
        double preco;
        try {
            preco = Double.parseDouble(precoStr.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new CustomException("O preço do produto é inválido.");
        }
        if (!ProdutoUtils.isPrecoValido(preco)) {
            throw new CustomException("O preço do produto deve ser maior que R$0,10.");
        }
        String quantidadeStr = produtoDTO.getQuantidade().trim();
        if (!INTEGER_FORMAT_PATTERN.matcher(quantidadeStr).matches()) {
            throw new CustomException("A quantidade do produto deve ser um número inteiro.");
        }
        int quantidade;
        try {
            quantidade = Integer.parseInt(quantidadeStr);
        } catch (NumberFormatException e) {
            throw new CustomException("A quantidade do produto é inválida.");
        }
        if (!ProdutoUtils.isQuantidadeValida(quantidade)) {
            throw new CustomException("A quantidade do produto deve ser no mínimo 1.");
        }
        if (produtoDTO.getFornecedorId() == null || produtoDTO.getFornecedorId().isEmpty()) {
            throw new CustomException("ID do Fornecedor é obrigatório.");
        }
        if (produtoDTO.getAdminId() == null || produtoDTO.getAdminId().isEmpty()) {
            throw new CustomException("ID do Administrador é obrigatório.");
        }
        if (!LETTERS_ONLY_PATTERN.matcher(produtoDTO.getNome()).matches()) {
            throw new CustomException("O nome do produto deve conter apenas letras e espaços.");
        }
        boolean adminExists = jdbcTemplateAdmDAO.findById(produtoDTO.getAdminId()).isPresent();
        if (!adminExists) {
            throw new CustomException("ID do Administrador não encontrado no banco de dados.");
        }
        boolean fornecedorExists = jdbcTemplateFornDAO.findById(produtoDTO.getFornecedorId()).isPresent();
        if (!fornecedorExists) {
            throw new CustomException("ID do Fornecedor não encontrado no banco de dados.");
        }
        Optional<Produto> produtoExistente = jdbcTemplateProdutoDAO.findByNomeAndDescricao(produtoDTO.getNome(), produtoDTO.getDescricao());
        if (produtoExistente.isPresent()) {
            throw new CustomException("Já existe um produto com o mesmo nome.");
        }
        Produto produto = new Produto();
        produto.setId(IdUtils.generateId('P'));
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(preco);
        produto.setQuantidade(quantidade);
        produto.setFornecedorId(produtoDTO.getFornecedorId());
        produto.setAdminId(produtoDTO.getAdminId());
        jdbcTemplateProdutoDAO.save(produto);
    }
    public List<ProdutoListaDTO> listarProdutos() {
        List<Produto> produtos = jdbcTemplateProdutoDAO.findAll();
        return produtos.stream()
                .map(produto -> {
                    ProdutoListaDTO dto = new ProdutoListaDTO();
                    dto.setNome(produto.getNome());
                    dto.setDescricao(produto.getDescricao());
                    dto.setPreco(String.valueOf(produto.getPreco()));
                    dto.setQuantidade(String.valueOf(produto.getQuantidade()));
                    return dto;
                })
                .collect(Collectors.toList());
    }
}