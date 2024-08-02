package com.SuperShop.Super.dao;

import com.SuperShop.Super.model.Produto;

import java.util.List;
import java.util.Optional;

public interface IJdbcTemplateProdutoDAO {
    void save(Produto produto);
    List<Produto> findAll();
    Optional<Produto> findById(String id);
    void update(Produto produto);
    void deleteById(String id);
    Optional<Produto> findByNomeAndDescricao(String nome, String descricao);
    //Produto mapRow(ResultSet rs, int rowNum);
    Optional<Produto> findByNome(String nome);
}