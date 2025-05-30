# TestePratico

![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Build](https://img.shields.io/badge/build-passing-brightgreen.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Coverage](https://img.shields.io/badge/coverage-100%25-success.svg)
![Tests](https://img.shields.io/badge/tests-passing-brightgreen.svg)

Este projeto simula o funcionamento básico de uma biblioteca, permitindo o empréstimo e devolução de livros por diferentes tipos de usuários, como alunos e professores.

## 🚀 Tecnologias Utilizadas

-Java 21
-Spring Boot 3.5.0
-Maven
-JUnit 5
-Mockito

## 📁 Estrutura do Projeto

```pgsql
TestePratico/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/
│   │   │       └── com/
│   │   │           └── biblioteca/
│   │   │               ├── controllers/      <-- rotas REST
│   │   │               ├── domain/
│   │   │               │   ├── entities/      <-- entidades (Livro, Usuario, etc)
│   │   │               │   └── services/      <-- lógica de negócio
│   │   │               └── mappers/          <-- mapeadores como UsuarioMapper
│   │   │               
│   │   └── resources/
│   │       └── application.properties       <-- configs do Spring Boot
│   └── test/
│       └── java/
│           └── br/
│               └── com/
│                   └── biblioteca/
│                       └──  service/        <-- testes unitários dos serviços
│                       
├── pom.xml
└── README.md

```
---

## 🚀 Como executar

### Pré-requisitos

- Java 21
- Maven 3.8+
- IDE (como IntelliJ ou VS Code)
- Insomnia, Postman ou similar (para testar endpoints)
- 
---

### Clone o repositório

```bash
git clone https://github.com/WendelNeres/TestePratico.git
cd TestePratico
```
---
## Execute o projeto na IDE ou via terminal
```bash
Copiar
Editar
./mvnw spring-boot:run
```
A aplicação estará disponível em: http://localhost:8080/livros


---

## 👤 Usuários cadastrados

| Nome   | Tipo  | Creditos |
|--------|-------|--------- |
| João   | Aluno |   10     |
| Maria  | Aluno |   10     |
| Carlos   | Professor|   ∞     |
| Ana   |Professor |   ∞      |



---

## 📚 Livros disponíveis

| Titulo   | Creditos  | Disponivel |
|--------|-------|--------- |
| Spring Boot Avançado  | 2 |   ✅    |
| Cálculo  | 1 |   ✅     |
| Java Para Iniciantes   | 2|   ✅    |
| Matemática   |2 |   ✅      |




# 📦 Exemplos de Requisição

  # 📘 Emprestar Livro
  POST /livros/emprestimo
  
  # 🔄 Empréstimo de Livro
  ```json 
  {
    "tituloLivro": "Spring Boot Avançado",
    "usuarioDTO": {
      "nome": "Carlos",
      "tipoUsuario": "professor"
    }
  }
  
```
  
  # 📗 Devolver Livro
  POST /livros/devolucao

  # 📚 Devolução de Livro
    ```json
    {
      "tituloLivro": "Spring Boot Avançado",
      "usuarioDTO": {
          "nome": "Carlos",
          "tipoUsuario": "professor"
        }
    }
    ```
---
## ✅ Executando os Testes Unitários

Os testes estão na pasta src/test/java e podem ser executados com:

```bash
Copiar
Editar
mvn test
```
---
# 🧪 Cobertura dos testes

## ✅ Casos testados

- 🔹 Empréstimo com sucesso
- 🔹 Livro indisponível
- 🔹 Livro não encontrado
- 🔹 Aluno sem créditos suficientes
- 🔹 Devolução com sucesso
- 🔹 Livro já está disponível
- 🔹 Nenhum usuário pegou o livro
- 🔹 Usuário devolvendo livro que não pertence a ele

---
# 🧑‍💻 Autor
Wendel Neres

---
# 📄 Licença
Distribuído sob a licença MIT. Veja LICENSE para mais informações.
