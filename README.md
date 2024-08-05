# Sistema de Supermercado PDV

## Descrição


O Sistema de Supermercado (PDV) é um sistema backend desenvolvido em Java utilizando Spring Boot, com foco na gestão de operações de um supermercado. O sistema é projetado para gerenciar administradores, fornecedores, produtos e vendas, oferecendo funcionalidades para cadastro, listagem e gerenciamento de dados.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **PostgreSQL**: Banco de dados relacional.
- **Lombok**: Biblioteca para redução de código boilerplate.
- **Swagger**: Documentação da API.
- **Postman**: Teste de APIs.
- **JDBC Template**: Camada de persistência de dados.

## Funcionalidades

### Administradores

- Cadastro;
- Consulta de todos os administradores;

### Fornecedor

- Cadastro
- Consulta de todos os fornecedores;

### Produtos

- Cadastro de produtos;
- Consulta de todos os produtos;

### Vendas

- Realização de vendas;
- Consulta de todas as vendas;

## Estrutura do Projeto

- **Controller**: Ponto de entrada da aplicação (endpoints).
- **Dao**: Camada de persistência de dados.
- **UseCase**: Lógica de negócio.
- **DTO**: Objetos para mapeamento.
- **Exceptions**: Exceções personalizadas.
- **Config**: Configurações do projeto.
- **Utils**: Classes utilitárias.
- **Model**: Classes de domínio.

## Configuração

## Documentação da API

A documentação da API está disponível no Swagger. Após iniciar a aplicação, acesse:
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Testes

Para testar a aplicação, utilize o Postman para interagir com os endpoints expostos e verificar a funcionalidade do sistema.
