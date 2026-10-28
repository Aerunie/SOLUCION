FROM tomcat:10.1-jdk21-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

# Vistas y librerías
COPY webapp/ /usr/local/tomcat/webapps/ROOT/

# Driver MySQL
ADD https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.4.0/mysql-connector-j-8.4.0.jar /usr/local/tomcat/webapps/ROOT/WEB-INF/lib/

# Compilar las clases Java dentro de la imagen
COPY src/main/java /tmp/java
RUN mkdir -p /usr/local/tomcat/webapps/ROOT/WEB-INF/classes \
 && find /tmp/java -name "*.java" > /tmp/sources.txt \
 && javac -encoding UTF-8 --release 21 \
      -cp "/usr/local/tomcat/lib/servlet-api.jar:/usr/local/tomcat/lib/jsp-api.jar" \
      -d /usr/local/tomcat/webapps/ROOT/WEB-INF/classes @/tmp/sources.txt \
 && rm -rf /tmp/java /tmp/sources.txt

COPY start.sh /start.sh
RUN chmod +x /start.sh

ENV JAVA_OPTS="-XX:MaxRAMPercentage=75"
CMD ["/start.sh"]
