# FLEA MARKET BACK-END

## DB schema:

![DB schema](/schema.png)

## Launch

### Build DB, application and UI images:
```bash
docker compose build
```

### Build the images (DB, application and UI; if they have not built yet) and run the containers:
```bash
docker compose up --detach
```

### Rebuild the application image:
```bash
docker compose rm application --stop --force
```
```bash
docker compose up --build --no-deps --detach application
```

### Rebuild the DB image:
```bash
docker compose rm database --stop --volumes --force
```
```bash
docker compose down --volumes
```
```bash
docker compose up --build --detach database
```

### Rebuild the UI image:
```bash
docker compose rm ui --stop --force
```
```bash
docker compose up --build --no-deps --detach ui
```

### Rebuild all the images:
```bash
docker compose down --volumes
```
```bash
docker compose up --build --force-recreate --detach
```
