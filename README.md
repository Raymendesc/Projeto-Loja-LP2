[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/u8b1GGH-)

# Sistema de Loja 
Este é um sistema de gerenciamento para uma loja de produtos. Ele permite gerenciar produtos, realizar vendas, e manter registros de transações.

## Proposito do projeto
O propósito desse projeto é criar um sistema de gerenciamento abrangente para uma loja de produtos. O sistema visa facilitar e otimizar diversas operações relacionadas à gestão da loja, incluindo o gerenciamento de produtos, o acompanhamento de vendas e a manutenção de registros de transações.

## Diagrama de Classes

![diagrama-de-classes](https://github.com/imetropoledigital/trabalho-final-rayana-mendes/assets/81039247/7fafcd11-1ad5-4c5b-b96a-d5de0b946d32)

[Ver Documentação do Projeto](https://github.com/imetropoledigital/trabalho-final-rayana-mendes/files/13648818/documentacao.pdf)

## Tecnologias utilizadas

- Java 17;
- SQLite
- Gradle

## Estrutura do Projeto

O projeto está organizado em várias camadas, cada uma responsável por diferentes aspectos do sistema:

- `model`: Contém as classes que representam as entidades do negócio (Produto, Venda, etc.).
- `dao`: Camada de acesso a dados, com classes responsáveis pela interação com o banco de dados.
- `services`: Contém a lógica de negócios e interage com a camada de acesso a dados.
- `view`: Responsável pela interface de usuário, onde as interações com o usuário acontecem.
- `exception`: Contém as exceções personalizadas usadas no projeto.
- `utils`: Fornece utilitários diversos, como manipulação de cores e arquivos.

## Recursos Disponíveis

- Gestão de produtos: adicionar, atualizar, remover e buscar produtos.
- Realização de vendas: registrar vendas de produtos.
- Consulta de vendas: visualizar o histórico de vendas.
- Cancelar uma venda.
- Ver faturamento.


## Como realizar o build

1. **Navegue até o Diretório do Projeto:**
   Abra um terminal e navegue até o diretório do seu projeto.

3. **Execute o Comando de Build:**
   Execute o seguinte comando para construir o projeto:

```console
$ ./gradlew build
```

## Autores
| [<img alt="Raymendesc" src="https://github.com/Raymendesc.png?size=115" width="115"><br><sub>@Raymendesc</sub>](https://github.com/raymendesc)| [<img alt="Leandro208" src="https://github.com/leandro208.png?size=115" width="115"><br><sub>@Leandro208</sub>](https://github.com/leandro208)| [<img alt="DougFelipe" src="https://github.com/DougFelipe.png?size=115" width="115"><br><sub>@DougFelipe</sub>](https://github.com/DougFelipe)| 
| :---: |:---: |:---:
