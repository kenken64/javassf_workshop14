## Workshop 14

```
./nvmw spring-boot:run
```

### Docker Workshop 

Package it before dockerization

```
./mvnw package
```

Test your newly package jar

```
java -jar target/workshop14.jar
```

Build docker image

```
docker build -t springio/workshop14-spring-boot-docker .
docker images
docker run -d -p 8080:8080 springio/workshop14-spring-boot-docker
docker logs <container id>
```

Scan for vulnerability

```
docker login
docker tag 4fc336bf6d08 kenken64/javaworkshop14:v1
docker push kenken64/javaworkshop14:v1
docker scan kenken64/javaworkshop14:v1
```

Run the docker image as container

```
docker run -d -p 8080:8080 kenken64/javaworkshop14:v1
```

Check docker container network info

```
docker inspect <container id>
```

Kill vs Stop container

```
docker stop <container id>
docker container ls
docker kill <container id>
```

Start local redist container image

```
docker run --rm -p 4025:6379 -d --name redis-1 redis redis-server
```

How to clean up all the docker images, volume disk space 
```
docker system prune -a

Docker composition


```
version: '2'
services:
  app:
    build: .
    ports:
     - "8080:8080"
    links:
      - "db:redis"
  db:
    image: "redis:alpine"
    hostname: redis
    ports:
     - "6379:6379"
```

```
./mvnw clean package

docker-compose up -d --build

docker-compose down
```

Select Dockerfile for building

```
docker build -f Dockerfile.2 -t springio/workshop14-spring-boot-docker2 .
docker build -f Dockerfile.3 -t springio/workshop14-spring-boot-docker3 .

```


Interactive mode into the container
```
docker exec -it <redis-container-id> sh
```