FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=poemdatabase
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/poemdatabase
ADD ./target/favouritelistservice-0.0.1-SNAPSHOT.jar /usr/src/favouritelistservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "favouritelistservice-0.0.1-SNAPSHOT.jar"]
