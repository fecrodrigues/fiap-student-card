# Student Card

Criado projeto de uma empresa (FIAP) que está ingressando no segmento de cartões de crédito para fornecer o serviço aos seus alunos.

## Justificativas da composição do projeto

A linguagem escolhida para o desenvolvimento foi: <h3>Kotlin</h3>.
Foram utilizados diversos módulos do framework Spring, entre eles:

spring-boot-starter-web - Utilizado para a criação dos Endpoints;
spring-boot-starter-data-jpa - Para acesso ao banco de dados MySQL;
spring-boot-starter-security - Para a autenticação dos usuários nas APIs.

Para proteger o acesso as APIs foi criado o acesso através de um Bearer Token utilizando o JSON Web Token (io.jsonwebtoken:jjwt:0.9.1).

Foram criadas as APIs e separadas entre os grupos:

- Transactions;
- Authentication;
- Students.

Através das APIs é possível utilizar todas as funções essenciais para a manipulação dos dados.
As ações mais comuns implementadas foram: Create, Delete, Post, Put e Patch.

O banco de dados escolhido foi o MySQL e utilizado o Flyway para o controle de versionamento do banco.

Foi disponibilizada uma API para o envio de extrado do cliente por e-mail.

Foi implementado o Spring Security para proteger o acesso indevido as APIs.

Com os conhecimentos adquiridos em aula foi possível implementar o módulo security do Spring afim de garantir a autenticação de todas as chamadas no sistema. Tudo isso através do JWT.

Para o serviço de persistência dos dados foi utilizado o Spring Data, adicionando uma camada de abstração do banco e facilitando a implementação e garantindo o funcionamento em caso de possíveis mudanças de tecnologia.

O Spring Web foi responsável pela criação das rotas e expor a API a um acesso externo.

## Justificativas da composição do projeto

   
### Inicialização   
  A inicialização é feita de maneira simplificada pelo uso do spring boot sendo necessário apenas executar a classe **TransactionsApplication**. 
  Para o envio de e-mail é necessário utilizar as credenciais de acesso de uma conta do Gmail.
  
  
### Documentação
  As informações referentes as rotas e funcionalidades do microsserviço podem ser acessadas através do swagger(http://localhost:8082/swagger-ui.html) configurado no projeto.
  
  
## Importação massiva de alunos

A importação massiva encontra-se no repositorio, é recomendado rodar a mesma após ter iniciado esse projeto ao menos uma vez para que o flyway crie as tabelas e parâemtros necessários.

https://github.com/fecrodrigues/fiap-student-batch
