## 🧠 Главные отличия **PostgreSQL** от **PostgreSQL**

| PostgreSQL	                   | MongoDB                          |
|:------------------------------|:---------------------------------|
| @Entity + @Table              | @Document                        |
| @Id Long id	                  | @Id String id                    |
| spring-boot-starter-data-jpa	 | spring-boot-starter-data-mongodb |
| extends JpaRepository	        | extends MongoRepository          |
| @Column(unique = true)	       | @Indexed(unique = true)          |
| SQL миграции (Flyway)	        | Не нужны                         |

---
#### Установите и запустите MongoDB
```bash
docker run -d -p 27017:27017 --name mongodb mongo:latest
```
---
