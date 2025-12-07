# PROINOVA API

A PROINOVA API é uma plataforma desenvolvida como Trabalho de Conclusão de Semestre para a Faculdade Senac de Criciúma, com o objetivo de permitir a divulgação de projetos voltados à arrecadação de fundos e visibilidade.

---

## 1. Autenticação (Auth)

### 1.1 Registro de Usuário

**POST /auth/register**

**Body:**

```json
{
  "name": "Nome Completo",
  "username": "nomeusuario",
  "email": "usuario@email.com",
  "password": "pass123",
  "confirmPassword": "pass123"
}
```

**Regras:**

* Username: mínimo 3 e máximo 45 caracteres
* Senha: mínimo 3 e máximo 24 caracteres
* Todos os usuários possuem a role **PUBLIC** por padrão (ADMIN apenas via banco de dados)

**Resposta (200 OK):**

```json
{
  "token": "jwt-token"
}
```

### 1.2 Login

**POST /auth/login**

**Body:**

```json
{
  "email": "usuario@email.com",
  "password": "pass123"
}
```

**Resposta (200 OK):**

```json
{
  "token": "jwt-token"
}
```

---

## 2. Área de Atividade e Estágio de Desenvolvimento

Apenas administradores podem criar, editar ou excluir. As rotas de consulta são públicas para usuários autenticados.

### 2.1 Listar Áreas de Atividade

**GET /activityarea**

```json
{
  "id": 1,
  "area": "Medicina"
}
```

### 2.2 Listar Estágios de Desenvolvimento

**GET /devstage**

```json
{
  "id": 1,
  "stage": "MVP"
}
```

### 2.3 Criar (ADMIN)

**POST /activityarea**

Body:

```json
{
  "area": "Nome da Área"
}
```

**POST /devstage**

Body:

```json
{
  "stage": "Nome do Estágio"
}
```

### 2.4 Atualizar (ADMIN)

**PUT /activityarea/{id}**

Body:

```json
{
  "area": "Nome Atualizado da Área"
}
```

**PUT /devstage/{id}**

Body:

```json
{
  "stage": "Nome Atualizado do Estágio"
}
```

### 2.5 Buscar por ID

GET `/devstage/{id}`

### Exemplo de Resposta

```json
{
  "id": 1,
  "stage": "Ideação"
}
```

## 2.6 Deletar ActivityArea

DELETE `/activityarea/{id}`

### Exemplo de Resposta

```
204 No Content
```

## 2.7 Deletar DevStage

DELETE `/devstage/{id}`

*DevStage e ActivityArea só podem ser deletados se não houver projetos que estejam usando.*

### Exemplo de Resposta

```
204 No Content
```

## 3. Projetos

### 3.1 Listar Projetos (Público)

**GET /projects**

Retorno:

```json
{
  "id": 1,
  "title": "Titulo",
  "description": "Descrição detalhada",
  "currentFund": 500.00,
  "fundGoal": 1000.00,
  "members": ["João", "Maria"],
  "imageUrl": "/uploads/filename",
  "activityArea": { "id": 1, "area": "Medicina" },
  "devStage": { "id": 2, "stage": "MVP" }
}
```

### 3.2 Upload de Imagem

**POST /projects/uploadImage**

* Enviar arquivo pelo campo `file` via **FormData**
* Retorna URL da imagem para uso na criação do projeto

### 3.3 Criar Projeto

**POST /projects**

Body:

```json
{
  "title": "Titulo",
  "description": "Descrição detalhada",
  "currentFund": 500.00,
  "fundGoal": 1000.00,
  "members": ["João", "Maria"],
  "imageUrl": "/uploads/filename",
  "activityAreaId": 1,
  "devStageId": 1
}
```

### 3.4 Atualizar Projeto

**PUT /projects/{id}**

* Permitido apenas ao criador do projeto ou ADMIN

Body igual ao POST.

### 3.5 Excluir Projeto

**DELETE /projects/{id}**

* Apenas criador ou ADMIN podem excluir

---

## 4. Usuário

### 4.1 Obter Informações do Usuário Autenticado

**GET /user**

Retorno:

```json
{
  "name": "Nome completo",
  "username": "nomeusuario",
  "email": "email@email.com",
  "role": "PUBLIC"
}
```

### 4.2 Atualizar Nome/Username

**PUT /user/update-name**

Body:

```json
{
  "name": "Nome completo",
  "username": "nomeusuario"
}
```

* Ambos opcionais, pelo menos um é obrigatório

### 4.3 Atualizar Senha

**PUT /user/update-password**

Body:

```json
{
  "currentPass": "senhaAtual",
  "newPass": "novaSenha",
  "confirmPass": "novaSenha"
}
```

**GET /user/projects**

Retorna um array com todos os projetos do usuario logado.

---

## 5. Tecnologias

* Java 17+
* Spring Boot
* Spring Security (JWT)
* Spring Data JPA
* MySQL
* Maven

---

## 6. Como Executar

Configure seu `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/proinnova
    username: root
    password: senha
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

Executar:

```
mvn spring-boot:run
```

Acessar:

```
http://localhost:8080
```
