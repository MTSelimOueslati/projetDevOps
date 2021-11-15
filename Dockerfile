FROM openjdk:8-jdk-alpine
EXPOSE 8090
ADD /target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-1.0.0.1.jar Timesheet-spring-boot-core-data-jpa-mvc-REST-1-1.0.0.1.jar
ENTRYPOINT [ "java", "-jar"	 , "/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-1.0.0.1.jar"]
