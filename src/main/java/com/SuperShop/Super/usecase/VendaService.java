package com.SuperShop.Super.usecase;

import com.SuperShop.Super.dao.Impl.JdbcTemplateProdutoDAO;
import com.SuperShop.Super.dao.Impl.JdbcTemplateVendaDAO;
import com.SuperShop.Super.dto.VendaDTO;
import com.SuperShop.Super.exception.CustomException;
import com.SuperShop.Super.model.Produto;
import com.SuperShop.Super.model.ReciboVenda;
import com.SuperShop.Super.model.Venda;
import com.SuperShop.Super.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private JdbcTemplateVendaDAO jdbcTemplateVendaDAO;

    @Autowired
    private JdbcTemplateProdutoDAO jdbcTemplateProdutoDAO;

    public ReciboVenda adicionarVenda(VendaDTO vendaDTO) {
        Produto produto = jdbcTemplateProdutoDAO.findById(vendaDTO.getProduto())
                .orElseThrow(() -> new CustomException("Produto não encontrado"));

        // Verificar se o produto está fora de estoque
        if (produto.getQuantidade() <= 0) {
            throw new CustomException("Não tem produto no estoque");
        }

        // Verificar se a quantidade solicitada é maior do que a disponível em estoque
        if (vendaDTO.getQuantidade() > produto.getQuantidade()) {
            throw new CustomException("Quantidade maior do que o estoque de produto");
        }

        Venda venda = new Venda();
        venda.setId(IdUtils.generateId('V'));
        venda.setProduto(produto.getId()); // usando ID do produto
        venda.setQuantidade(vendaDTO.getQuantidade());
        venda.setValorTotal(vendaDTO.getValorTotal());

        // Verificar se a quantidade é válida
        if (venda.getQuantidade() <= 0) {
            throw new CustomException("Quantidade inválida");
        }

        // Verificar se o valor total é de acordo com a quantidade
        double valorEsperado = produto.getPreco() * venda.getQuantidade();

        // Verificar se o valor pago é maior que o preço do produto vezes a quantidade
        if (venda.getValorTotal() > valorEsperado) {
            throw new CustomException("Valor pago é maior do que o preço do produto");
        }

        if (venda.getValorTotal() != valorEsperado) {
            throw new CustomException("Valor total inválido. Deve ser: " + valorEsperado);
        }

        // Diminuir a quantidade do produto no estoque
        int novaQuantidade = produto.getQuantidade() - venda.getQuantidade();
        produto.setQuantidade(novaQuantidade);
        jdbcTemplateProdutoDAO.update(produto);

        jdbcTemplateVendaDAO.save(venda);

        ReciboVenda recibo = new ReciboVenda();
        recibo.setVendaId(venda.getId());
        recibo.setProdutoNome(produto.getNome());
        recibo.setQuantidade(venda.getQuantidade());
        recibo.setValorTotal(venda.getValorTotal());
        recibo.setPrecoUnitario(produto.getPreco());
        recibo.setValorPago(venda.getValorTotal());
        recibo.setMensagem("Venda realizada com sucesso!");

        return recibo;
    }
    public List<Venda> listarVendas() {
        return jdbcTemplateVendaDAO.findAll();
    }
}