# Sistema Corporativo - PetShop Amigo

Projeto desenvolvido como Estudo de Caso para a disciplina de **Desenvolvimento de Sistemas Corporativos** (UERN).

## 1. Cenário da Empresa Fictícia
A **PetShop Amigo** é uma loja de produtos para animais de estimação que precisava substituir o controle manual em papel por um sistema corporativo web simples, capaz de gerenciar o cadastro de produtos, o controle de estoque e o registro de vendas.

## 2. Proposta de Desenvolvimento (Arquitetura MVC em 3 Camadas)
O sistema foi estruturado seguindo o padrão arquitetural **Model-View-Controller (MVC)** em três camadas:
* **Camada de Aplicação (View / Visão):** Desenvolvida em HTML5, CSS3 e JavaScript puro (`src/main/resources/static`), comunicando-se com o servidor via requisições REST assíncronas (`fetch`).
* **Camada de Negócios (Controller / Controle):** Desenvolvida em Java com Spring Boot (`PetShopController`), responsável pela autenticação, validação de regras de negócio, baixa de estoque e exposição da API REST.
* **Camada de Persistência (Model / Modelo):** Implementada com Spring Data JPA e banco de dados relacional PostgreSQL (`Produto` e `ProdutoRepository`).

## 3. Transações da Empresa e Caso de Uso
* **Principais Transações:** Autenticação de operador, Cadastro de Produtos, Registro de Venda (baixa automática no estoque), Consulta de Estoque e Emissão de Relatório.
* **Exemplo de Caso de Uso (UC01 - Registrar Venda):**
  * **Ator:** Operador / Vendedor.
  * **Pré-condição:** Usuário autenticado no sistema e produto cadastrado com saldo em estoque.
  * **Fluxo Principal:**
    1. O operador acessa o sistema com usuário e senha (`admin` / `1234`).
    2. Visualiza a lista de produtos disponíveis na tela de consulta.
    3. Clica no botão **"Vender 1 un."** ao lado do produto desejado.
    4. O sistema envia uma requisição `PUT` à API REST, abate 1 unidade no banco de dados PostgreSQL e atualiza o valor total em estoque.

## 4. Como Executar o Projeto
### Pré-requisitos
* Java JDK 21 (ou 17+)
* PostgreSQL instalado
* IDE Eclipse com suporte a Maven

### Passo a passo
1. No PostgreSQL (`psql` ou pgAdmin), crie o banco de dados vazio:
   ```sql
   CREATE DATABASE petshopamigo;