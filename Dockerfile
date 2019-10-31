FROM java:8
EXPOSE 8080
ADD /target/wasting-1.0.0.jar wasting-1.0.0.jar
ENTRYPOINT ["java","-jar","wasting-1.0.0.jar"]