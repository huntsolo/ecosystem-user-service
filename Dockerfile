FROM adoptopenjdk/openjdk11:jre-11.0.10_9-alpine

COPY ./target/ecosystem-user-service.jar /
#COPY ./carey-development-service-config.json /

#ENV GOOGLE_APPLICATION_CREDENTIALS="/carey-development-service-config.json"

EXPOSE 32010
ENTRYPOINT ["java", "-jar", "./ecosystem-user-service.jar"]
