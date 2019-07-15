# We select the base image from. Locally available or from https://hub.docker.com/
FROM openjdk:8-jre-alpine

ARG frontendBuild=./rafflery-ui/build
ARG backendBuild=./rafflery-server/build/libs/rafflery.jar

# We define the user we will use in this instance to prevent using root that even in a container, can be a security risk.
ENV APPLICATION_USER ktor

# Then we add the user, create the /app folder and give permissions to our user.
RUN adduser -D -g '' $APPLICATION_USER
RUN mkdir /app
RUN chown -R $APPLICATION_USER /app

# Marks this container to use the specified $APPLICATION_USER
USER $APPLICATION_USER

RUN printf '%s\n' *
RUN cd app && printf '%s\n' *
#RUN cd /home/ktor && printf '%s\n' *

# We copy the FAT Jar we built into the /app folder and sets that folder as the working directory.
COPY ${frontendBuild} /app/rafflery-ui/build
COPY ${backendBuild} /app/rafflery.jar
WORKDIR /app

RUN cd /app && printf '%s\n' *

# We launch java to execute the jar, with good defauls intended for containers.
CMD ["java", "-server", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-XX:InitialRAMFraction=2", "-XX:MinRAMFraction=2", "-XX:MaxRAMFraction=2", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=100", "-XX:+UseStringDeduplication", "-jar", "rafflery.jar"]
