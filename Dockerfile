# stage one: build
FROM openjdk:11 as builder

WORKDIR /tmp
COPY . /tmp

RUN chmod +x gradlew
RUN ./gradlew clean build

#stage two: run
FROM openjdk:11

WORKDIR /app

COPY --from=builder /tmp/build/libs/portfolio-1.0.jar /app

EXPOSE 8080

CMD java -jar /app/portfolio-1.0.jar