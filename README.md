# Sistema de Supermercado PDV

## Créditos


✍ Projeto proposto pela ***Fourcamp***. Sendo disciplinado e orientado pelos mestres: *[Bruno Martin](https://www.linkedin.com/in/brunoermacora/)*
e  *[Denilson Elias](https://www.linkedin.com/in/denilsonbitit/)*

## Descrição


O Sistema de Supermercado (PDV) é um sistema backend desenvolvido em Java utilizando Spring Boot, com foco na gestão de operações de um supermercado. O sistema é projetado para gerenciar administradores, fornecedores, produtos e vendas, oferecendo funcionalidades para cadastro, listagem e gerenciamento de dados.

- ## Tecnologias usadas 💻

- **JAVA**: linguagem de programação principal.


- **SPRING-BOOT**: Framework para desenvolvimento de aplicações Java.


- **POSTGRESQL**: Banco de dados relacional.


- **MAVEN**: Ferramenta principal utilizada para gerenciar essas aplicações em Java.


- **POSTMAN**: Ferramenta para testar e manipular os endpoints da API.


- **SWAGGER**: Documentação interativa dos endpoints.


- **INTELLIJ**: Ambiente de desenvolvimento.


<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg" width="50" height="50" />
<img src="https://github.com/user-attachments/assets/f83409ca-b336-453a-a45c-84c743fdd781" width="50" height="50" />
<img src="https://github.com/user-attachments/assets/0304ff16-0be3-426a-9ae2-57846e217db8" width="45" height="45" />
<img src="https://github.com/user-attachments/assets/01708cb5-c31d-409f-b73c-9de7d941c72c" width="45" height="45" />
<img src="https://github.com/user-attachments/assets/205b41ac-971f-4871-92dc-468d2676a846" width="75" height="50" />
<img src="https://github.com/user-attachments/assets/eb215758-11c4-49ad-8ab0-73624c23f44f" width="120" height="50" />
<img src="https://github.com/user-attachments/assets/de8d1369-0da3-49a8-b892-5c11c0509987" width="40" height="40" />



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

## Documentação da API

A documentação da API está disponível no Swagger. Após iniciar a aplicação, acesse:
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Testes

Para testar a aplicação, utilize o Postman para interagir com os endpoints expostos e verificar a funcionalidade do sistema.
- **Configurar o Banco de Dados**

    - O projeto utiliza um banco de dados que está em outro repositório. Certifique-se de cloná-lo e configurá-lo corretamente. Você pode encontrar o repositório do banco de dados em: [Link para o Repositório do Banco de Dados](https://github.com/ThomasDevelop/codigoSqlSuperShop.git)

    - Após configurar o banco de dados, insira as credenciais no arquivo `application.properties`.
- **Compilar e Executar a Aplicação**

## 🤝 Agradecimentos:
Obrigado [Foursys](https://br.linkedin.com/company/foursys). e professores: [Bruno Martin](https://www.linkedin.com/in/brunoermacora/) e [Denilson Elias](https://www.linkedin.com/in/denilsonbitit/), por me proporcionar essa experiência incrível de aprendizado e evolução 😎🤝