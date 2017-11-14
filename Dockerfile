FROM java:openjdk-8-jre-alpine

ENV ENVIRONMENT dev
ENV PORT 8080
ENV MONGO_URI mongo

WORKDIR /var/app/user-service

RUN apk add --update curl # Used for healthcheck instruction

ADD config/ ./config/
ADD target/user-service.jar ./

HEALTHCHECK --interval=5s --retries=10 CMD curl -f -s http://localhost:$PORT/health || exit 1

EXPOSE $PORT

CMD java -Djava.security.egd=file:/dev/./urandom -jar ./user-service.jar --spring.profiles.active=$ENVIRONMENT
