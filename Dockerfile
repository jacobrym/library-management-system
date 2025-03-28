FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /library-app

COPY pom.xml .
COPY checkstyle-9.3.xml .
COPY mvnw .

COPY .mvn ./.mvn
COPY src ./src

#--batch-mode for noninteractive mode
RUN ./mvnw clean package -DskipTests --batch-mode

FROM eclipse-temurin:17-jre-alpine
WORKDIR /library-app

COPY --from=builder /library-app/target/library-management-system-0.0.1-SNAPSHOT.jar library-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "library-app.jar"]
