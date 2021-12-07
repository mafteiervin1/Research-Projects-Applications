FROM maven:3.6.1-jdk-8-alpine
WORKDIR /app
COPY . /app
RUN mvn package
EXPOSE 8080
CMD ["mvn", "spring-boot:run", "-Drun.jvmArguments=\"-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8081\""]