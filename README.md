**Lista de REST API's**
===
> GET: Buscar todos os planetas
`http://localhost:8080/api/planet` 

- 200 -> OK!
---
> GET: Buscar planeta por ID
`http://localhost:8080/api/planet/{id}`

- 200 -> OK!
---
> GET: Buscar planeta por nome
`http://localhost:8080/api/planet/name/{name}`

- 200 -> OK!
---
> POST: Criar planeta
`http://localhost:8080/api/planet`

- 200 -> OK!
- 400 -> Erro em algum campo do json.
---
> PUT: Alterar planeta por ID
`http://localhost:8080/api/planet/{id}`

- 200 -> OK!
- 400 -> Erro em algum campo do json.
---
> DELETE: Deletar planeta por ID
`http://localhost:8080/api/planet/{id}`

- 200 -> OK!
