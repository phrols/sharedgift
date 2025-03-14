# sharedgift

## docker image with postgresql database
### data base initialisation
The data repository contains a init.sh script which is mounted into the docker image and executed at launch

### build images from docker-compose.yml
```bash
docker-compose build
```
### start a container
```bash
docker-compose up -d sgdb
```

### Stop and Remove Containers 
This stops and removes all running containers, networks, and by default, does not remove named volumes.
```bash
docker-compose down
```

### Remove Volumes (Reset Data)
This ensures that all named and anonymous volumes associated with the services are deleted, resetting any stored data.
```bash
docker-compose down --volumes
```

### (Optional) Remove Built Images
```bash
docker-compose down --rmi all --volumes
--rmi all: Removes all images built by docker-compose build
--volumes: Deletes all associated volumes
```

### Restart all
```bash
docker-compose up -d
```

### connect into the container
```bash
docker exec -it sharedgift-db bash
```
## backend

### sources
- https://medium.com/@cat.edelveis/using-liquibase-with-spring-boot-tutorial-79245a0b79a6
- https://github.com/mat-chartier/arc-distinctions-ffta/
- https://medium.com/@victoronu/implementing-jwt-authentication-in-a-simple-spring-boot-application-with-java-b3135dbdb17b

### Install Eclipse
#### Lombok
- [Install lombok in Eclipse ](https://projectlombok.org/setup/eclipse)

### Run the backend server
mvn spring-boot:run -DJWT_SECRET=XXXX

### Jpa test
- https://courses.baeldung.com/courses/1295711/lectures/30127904


## frontend

## Others
### define vhost
This is mandatory if backend and frontend are not running on the same server

```xml
<VirtualHost *:80>
ServerName auxiliaires
LogLevel proxy:debug proxy_http:debug
ProxyPass "/api/" "http://localhost:8080/" upgrade=websocket
ProxyPassReverse "/api/" "http://localhost:8080/"

ProxyPass "/" "http://localhost:5173/" upgrade=websocket
ProxyPassReverse "/" "http://localhost:5173/"
</VirtualHost>
```