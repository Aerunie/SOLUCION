FROM tomcat:9.0-jdk17-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

# La app se sirve en la raíz (/)
COPY web/ /usr/local/tomcat/webapps/ROOT/

# Drivers JDBC (MySQL y SQL Server)
ADD https://repo1.maven.org/maven2/com/microsoft/sqlserver/mssql-jdbc/12.8.1.jre11/mssql-jdbc-12.8.1.jre11.jar /usr/local/tomcat/webapps/ROOT/WEB-INF/lib/
ADD https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.4.0/mysql-connector-j-8.4.0.jar /usr/local/tomcat/webapps/ROOT/WEB-INF/lib/

COPY start.sh /start.sh
RUN chmod +x /start.sh

ENV JAVA_OPTS="-XX:MaxRAMPercentage=75"
CMD ["/start.sh"]
