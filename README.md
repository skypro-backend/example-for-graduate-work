## Запуск

### Frontend
```shell
docker run -p 3000:3000 ghcr.io/bizinmitya/front-react-avito:v1.21 -d
```

### DB
```shell
docker run --name graduate-pg -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=graduatedb -d postgres
```

### Backend
Запуск `HomeworkApplication::main`