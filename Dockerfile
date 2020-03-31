FROM openjdk:9
ADD target/kick-scooter-trip.jar kick-scooter-trip.jar
ENTRYPOINT ["java", "-jar", "kick-scooter-trip.jar"]