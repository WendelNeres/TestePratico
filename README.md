# TestePratico

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Build](https://img.shields.io/badge/build-passing-brightgreen.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Coverage](https://img.shields.io/badge/coverage-100%25-success.svg)
![Tests](https://img.shields.io/badge/tests-passing-brightgreen.svg)

Este projeto simula o funcionamento básico de uma biblioteca, permitindo o empréstimo e devolução de livros por diferentes tipos de usuários, como alunos e professores.

## 🚀 Tecnologias Utilizadas

- Java 21
- Maven
- JUnit 5
- Mockito

## 📁 Estrutura do Projeto

```bash
TestePratico/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── br/
│   │           └── com/
│   │               └── biblioteca/
│   │                   ├── model/
│   │                   ├── service/
│   │                   └── dto/
│   └── test/
│       └── java/
│           └── br/
│               └── com/
│                   └── biblioteca/
│                       └── service/
├── pom.xml
└── README.md

```
---

## ▶️ Como Executar a Aplicação
  
1 - Clone o repositório:

```bash
Copiar
Editar
git clone https://github.com/WendelNeres/TestePratico.git
```
2 - Acesse a pasta do projeto:

```bash
Copiar
Editar
cd TestePratico
```

3 - Compile o projeto:

```bash
Copiar
Editar
mvn clean install
```
---
## ✅ Executando os Testes Unitários

Os testes estão na pasta src/test/java e podem ser executados com:

```bash
Copiar
Editar
mvn test
```
# 🧪 Cobertura dos testes

Validação de empréstimo e devolução

Regras específicas para Aluno e Professor

Erros como livro já disponível, livro não encontrado, etc.

# 📦 Exemplos de Requisição
  # 🔄 Empréstimo de Livro
  ```json 
  Copiar
  Editar
  {
    "tituloLivro": "Spring Boot Avançado",
    "usuarioDTO": {
      "nome": "Carlos",
      "tipoUsuario": "professor"
    }
  }

```
# 📚 Devolução de Livro
```json
Copiar
Editar
{
  "tituloLivro": "Spring Boot Avançado",
  "usuarioDTO": {
    "nome": "Carlos",
    "tipoUsuario": "professor"
  }
}
```

📄 Licença
Distribuído sob a licença MIT. Veja LICENSE para mais informações.
