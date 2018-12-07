FROM dockerfile/java:oracle-java8

VOLUME /tmp
ADD et-gateway-service.jar /opt/et-gateway-service/
EXPOSE 8080
WORKDIR /opt/et-gateway-service/
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms512m", "-Xmx1g", "-jar", "et-gateway-service.jar"]
