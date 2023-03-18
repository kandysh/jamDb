FROM eclipse-temurin:17-jdk-alpine
RUN apk update && apk add bash
ADD ${JRE_FILE} app.jar
RUN chmod 0644 app.jar
RUN apk add cron
RUN crontab -l | {cat; echo "0 0 * * 0 java -jar app.jar";} | crontab -
CMD cron