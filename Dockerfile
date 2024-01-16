FROM amazoncorretto:21-alpine-jdk
RUN wget https://github.com/maksyche/mini-sqli-playground/releases/latest/download/mini-sqli-playground-1.0-SNAPSHOT.jar
EXPOSE 8081
CMD java -jar mini-sqli-playground-1.0-SNAPSHOT.jar
