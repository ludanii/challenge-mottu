# 🚀 SOLUÇÃO PARA MOTTU

## 👥 Integrantes

- Ana Carolina de Castro Gonçalves - RM554669
- Luisa Danielle - RM555292
- Michelle Marques Potenza - RM557702

## 📄 Descrição do Projeto
- Esta aplicação tem como objetivo centralizar e organizar o gerenciamento de motocicletas nos pátios da empresa Mottu, por meio de uma API REST desenvolvida em Java com Spring Boot.

- O sistema permite o cadastro, atualização, listagem e remoção de informações relacionadas a quatro entidades principais:
  - Clientes
  - Funcionários
  - Motos
  - Pátios

## 🎯 Objetivo da Solução

- Organizar e centralizar o controle das motos em pátios, evitando:
  - Falta de informações atualizadas;
  - Desorganização entre setores e funcionários;
  - Perda de tempo com retrabalho.

## 🛠️ Como Rodar o Projeto

- A API roda localmente na porta 8080; 
- Use uma ferramenta como Postman ou Insomnia para testar os endpoints;
- É necessário abrir o Oracle SQL Developer e acessar com as credenciais definidas no application.properties.


## 📡 Endpoints Disponíveis
### 👤 Funcionários
  - POST (Cadastrar)
    - http://localhost:8080/funcionarios
    - JSON: 
`    {
      "nomeUsuario": "joaosilva",
      "email": "joao@gmail.com",
      "senha": "senha429"
    }`
  - GET (Listar)
    - http://localhost:8080/funcionarios
  - PUT (Atualizar)
    - http://localhost:8080/funcionarios/{id}
    - JSON:
`    {
      "nomeUsuario": "joaosilva",
      "email": "joao1@gmail.com",
      "senha": "senha410"
    }`
  - DELETE (Deletar)
    - http://localhost:8080/funcionarios/{id}

### 🏢 Pátios
  - POST (Cadastrar)
    - http://localhost:8080/patios
    - JSON:
`    {
      "localizacao": "Rua Humberto Parente, 18 – São Miguel",
      "quantidadeVagas": "60"
    }`
  - GET (Listar)
    - http://localhost:8080/patios
  - PUT (Atualizar)
    - http://localhost:8080/patios/{id}
    - JSON:
`    {
      "localizacao": "Rua Humberto Parente, 180 – São Miguel",
      "quantidadeVagas": "40"
    }`
  - DELETE (Deletar)
    - http://localhost:8080/patios/{id}

### 🛵 Motos
  - POST (Cadastrar)
    - http://localhost:8080/motos
    - JSON:
`    {
      "modelo": "Mottu Pop",
      "placa": "GHI9012",
      "status": "Indisponível",
      "setor": "Bom"
    }`
  - GET (Listar)
    - http://localhost:8080/motos
  - PUT (Atualizar)
    - http://localhost:8080/motos/{id}
    - JSON:
`    {
      "modelo": "Mottu Pop",
      "placa": "GHI9Y12",
      "status": "Disponível",
      "setor": "Bom"
    }`
  - DELETE (Deletar)
    - http://localhost:8080/motos/{id}


### 🧍‍♀️ Clientes
  - POST (Cadastrar)
    - http://localhost:8080/clientes
    - JSON:
`    {
      "nome": "Juliana Rocha",
      "email": "juliana@gmail.com",
      "cpf": "025.462.884-23",
      "telefone": "11977776666",
      "dataNascimento": "1988-12-05"
     }`
  - GET (Listar)
    - http://localhost:8080/clientes
  - PUT (Atualizar)
    - http://localhost:8080/clientes/{id}
    - JSON:
`     {
       "nome": "Juliana Rocha",
       "email": "juliana@gmail.com",
       "cpf": "025.462.884-23",
       "telefone": "11934563221",
       "dataNascimento": "1988-12-06"
     }`
   - DELETE (Deletar)
       - http://localhost:8080/clientes/{id}