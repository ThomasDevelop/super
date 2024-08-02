package com.SuperShop.Super.dao;

import com.SuperShop.Super.model.Venda;

import java.util.List;
import java.util.Optional;

public interface IJdbcTemplateVendaDAO {
    void save(Venda venda);
    List<Venda> findAll();
    Optional<Venda> findById(String id);
    void update(Venda venda);
    void deleteById(String id);
}