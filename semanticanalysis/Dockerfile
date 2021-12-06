FROM maven:3.6.1-jdk-8-alpine
WORKDIR /app
COPY . /app
RUN mvn package
EXPOSE 8080
CMD ["mvn", "spring-boot:run"]