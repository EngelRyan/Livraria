# Sistema de Livraria

Este é um sistema de gerenciamento de livraria implementado em Java que criei no segundo semestre da faculdade de ADS. O sistema permite gerenciar livros e filiais, realizar buscas por diversos critérios e manter o controle do estoque.

## Conceitos usados

1. **Programação Orientada a Obejeto**
2. **Manipulação de Arquivos**
3. **Trataemnto de Exceções**

## Funcionalidades

O sistema oferece as seguintes funcionalidades:

1. **Cadastrar novo livro**
2. **Listar livros**
3. **Buscar livros por nome**
4. **Buscar livros por categoria**
5. **Buscar livros por preço**
6. **Buscar livros por quantidade em estoque**
7. **Exibir valor total no estoque**
8. **Listar estoque por filial**
9. **Atualizar estoque**
10. **Cadastrar nova filial**
11. **Buscar livro por código**
12. **Encerrar atividades**

## Estrutura do Projeto

O projeto está organizado em classes principais:

- `Livraria`: Classe principal que contém o método `main` e controla a interação com o usuário.
- `Filial`: Classe que representa uma filial da livraria, contendo informações sobre a filial e seus livros.
- `Livro`: Classe que representa um livro, contendo informações sobre o título, ano, categoria, editora, valor e quantidade em estoque.
