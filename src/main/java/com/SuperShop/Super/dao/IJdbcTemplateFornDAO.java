    package com.SuperShop.Super.dao;

    import com.SuperShop.Super.model.Fornecedor;

    import java.sql.ResultSet;
    import java.util.List;
    import java.util.Optional;

    public interface IJdbcTemplateFornDAO {
        public void save(Fornecedor fornecedor);
        public Optional<Fornecedor> findById(String id);
        public Optional<Fornecedor> findByCnpj(String cnpj);
        public List<Fornecedor> findAll();
        public void deleteById(String id);
        public Fornecedor mapRow(ResultSet rs, int rowNum);
    }