# 📝 Task Management API

Esta é uma aplicação backend desenvolvida em Java com uso do Spring Framework, voltada para o gerenciamento de tarefas com data de vencimento, prioridade e status. A aplicação também organiza essas tarefas em listas de tarefas, permitindo estruturação e agrupamento lógico das atividades do usuário.

---

## 🎯 Objetivo

A API oferece funcionalidades para:

- Criar, listar, atualizar e remover listas de tarefas.
- Gerenciar tarefas dentro dessas listas, com controle de prioridade, status e prazos.
- Calcular automaticamente o progresso de cada lista com base nas tarefas concluídas.

---

## 🧱 Arquitetura e Componentes

### 🔸 Entidades (Modelo de Domínio)

- **Task**: Representa uma tarefa individual. Possui título, descrição, status (aberta ou fechada), prioridade (alta, média ou baixa), data de vencimento, data de criação e atualização.
- **TaskList**: Representa um agrupamento de tarefas. Armazena informações como título, descrição e lista de tarefas relacionadas, além de timestamps de criação e atualização.
- **Enums**: Define os valores possíveis de prioridade e status para uma tarefa.

As entidades estão mapeadas com anotações JPA (Jakarta Persistence API), formando o modelo de entidade-relacional (ER) para persistência em banco de dados relacional.

---

### 🔸 Repositórios

Utilizam Spring Data JPA para acesso aos dados persistidos:

- **TaskRepository**: Gerencia operações de persistência de tarefas.
- **TaskListRepository**: Gerencia operações de persistência das listas de tarefas.

---

### 🔸 Serviços

Contêm a lógica de negócio, como validações e manipulação de dados:

- **TaskService** e **TaskServiceImpl**: Implementam as regras para criação, atualização, remoção e recuperação de tarefas.
- **TaskListService** e **TaskListServiceImpl**: Gerenciam as operações relacionadas às listas de tarefas.

As implementações fazem uso de injeção de dependência e controle transacional com anotações Spring, como `@Service`, `@Transactional` e `@RequiredArgsConstructor`.

---

### 🔸 Controladores (Controllers)

Expostos via Spring Web, os controladores são responsáveis por receber as requisições HTTP e delegar as operações aos serviços:

- **TaskController**: Gerencia endpoints para operações com tarefas.
- **TaskListController**: Gerencia endpoints para operações com listas de tarefas.

---

### 🔸 Mapeadores (Mappers)

Fazem a conversão entre entidades e objetos de transferência de dados (DTOs), promovendo separação de camadas e segurança na exposição de dados:

- **TaskMapper**: Mapeia entre `Task` e `TaskDto`.
- **TaskListMapper**: Mapeia entre `TaskList` e `TaskListDto`, além de calcular o progresso da lista com base nas tarefas fechadas.

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot (Core, Web, Data JPA, Transactional)
- Jakarta Persistence API (JPA)
- Lombok
- Swagger/OpenAPI (documentação dos endpoints)
- UUID para identificação única de tarefas e listas

---

## 📐 Modelagem

A modelagem da aplicação é baseada em:

- **Diagrama de Classe**: Representa as relações entre `Task`, `TaskList`, seus atributos e comportamentos esperados.
- **Modelo Entidade-Relacional (MER)**: Tabelas `tb_tasks` e `tb_task_lists`, com relacionamento muitos-para-um entre tarefas e listas.

---

## ✅ Funcionalidades em Destaque

- Associação entre tarefas e listas.
- Registro automático de data de criação e atualização.
- Cálculo de progresso por lista com base em tarefas concluídas.
- Filtragem de tarefas por lista.
- Validações de integridade e consistência nos serviços.
