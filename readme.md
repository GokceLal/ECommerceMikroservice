# Kurulumlar 

## Docker üzerinde postgreSQL kurulum 
 
```
docker run --name postgreSQL -e POSTGRES_PASSWORD=root -p 5432:5432 -d postgres
```

## Docker üzerinde MongoDB çalıştırmak

```bash
    docker run --name eCommerceMongoDB -d -e "MONGO_INITDB_ROOT_USERNAME=admin" -e "MONGO_INITDB_ROOT_PASSWORD=root" -p 27017:27017 mongo:jammy
```

