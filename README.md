# mini-sqli-playground
A small Java application for playing with SQL injection vulnerabilities on a local in-memory H2 database.

## Run
Application is accessible on [http://localhost:8081](http://localhost:8081).

Run it using either **Java** or **Docker**.

### Java
- Download `.jar`:
```bash
wget https://github.com/maksyche/mini-sqli-playground/releases/latest/download/mini-sqli-playground-1.1-SNAPSHOT.jar
```
- Run it _(Java installation required)_:
```bash
java -jar ./mini-sqli-playground-1.1-SNAPSHOT.jar
```

### Docker
- Download `Dockerfile`:
```bash
wget https://raw.githubusercontent.com/maksyche/mini-sqli-playground/master/Dockerfile
```
- Build and run it _(Docker installation required)_:
```bash
docker run -p 127.0.0.1:8081:8081/tcp --rm -it $(docker build -q .)
```

## H2 Console
H2 console is accessible on [http://localhost:8081/h2-console](http://localhost:8081/h2-console) with these settings:
```
Driver Class:   org.h2.Driver
JDBC URL:       jdbc:h2:./sqlipg
User Name:      sa
Password:       password
```

## Build
Use this command to package a new `.jar` with your changes included:
```bash
mvn clean install
```
You'll find the jar file here: `./target/mini-sqli-playground-1.1-SNAPSHOT.jar`
