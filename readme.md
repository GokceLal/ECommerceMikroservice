# Kurulumlar 

## Docker üzerinde postgreSQL kurulum 
 
```
docker run --name postgreSQL -e POSTGRES_PASSWORD=root -p 5432:5432 -d postgres
```

## Docker üzerinde MongoDB çalıştırmak

```bash
    docker run --name eCommerceMongoDB -d -e "MONGO_INITDB_ROOT_USERNAME=admin" -e "MONGO_INITDB_ROOT_PASSWORD=root" -p 27017:27017 mongo:jammy
```

MongoDB yönetmek için MongoDB Compas tool 

New Connection+ --> AdvancedConnection Options 

host kısmına veri tabanının ir adresi ve port numarası 
Authantication kısmına geçiş yaparak kurulum sırasında girdiğiniz kullanıcı adı ve 
şifreyi yazıyorsunuz. Docker run komutu ile çalıştırdıysanız -e ile giriş 
yaptığınız bilgiler (admin-root)


```
db.createUser({user: "bilgeUser",pwd: "bilgeUser*",roles: ["readWrite","dbAdmin"]}) 
```


