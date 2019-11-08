FROM java:8
VOLUME /tmp
ADD myboot-simple-1.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8081
ENTRYPOINT [ "java", "-jar", "/app.jar" ]