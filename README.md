# FLEA MARKET BACK-END

## Launch

### Build DB and application images:
```bash
docker compose build
```

### Build the images (DB and application; if they have not built yet) and run the containers:
```bash
docker compose up --detach
```

### Rebuild the application image and run the container:
```bash
docker compose rm application --stop --force
docker compose up --build --no-deps --detach application
```

### Rebuild the DB image and run the container:
```bash
docker compose rm database --stop --volumes --force
docker volume rm flea-market_database-data --force
docker compose up --build --detach database
```

### Rebuild both of the images:
```bash
docker compose down --volumes
docker compose up --build --force-recreate --detach
```
