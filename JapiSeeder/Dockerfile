FROM eclipse-temurin:latest
WORKDIR .
RUN apt-get update
RUN apt-get -y install cron
ADD ${JRE_FILE} app.jar
RUN chmod 0644 app.jar
RUN crontab -l | {cat; echo "30 13 * * 6 java -jar app.jar"} | crontab -e
CMD cron