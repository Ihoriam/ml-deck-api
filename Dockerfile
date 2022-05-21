# stage one: build
FROM adoptopenjdk/openjdk11:alpine as builder

WORKDIR /tmp
COPY . /tmp

RUN chmod +x gradlew
RUN ./gradlew clean build

#stage two: run
FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /app
COPY --from=builder /tmp/build/libs/portfolio-1.0.jar /app

EXPOSE 9090

CMD java -jar /app/portfolio-1.0.jar