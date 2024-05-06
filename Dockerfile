FROM openjdk:8-jdk-alpine

WORKDIR /clustered-data-warehouse

COPY target/clustered-data-warehouse.jar .
EXPOSE 8080

CMD ["java","-jar","clustered-data-warehouse.jar"]