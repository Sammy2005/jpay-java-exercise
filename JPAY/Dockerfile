# Use the official gradle image to create a build artifact.
# https://hub.kube.com/_/gradle
FROM gradle:7.2-alpine  as builder

WORKDIR /app

COPY . .

# Build a release artifact.
RUN gradle build

# Use AdoptOpenJDK for base image.
# It's important to use OpenJDK 8u191 or above that has container support enabled.
# https://hub.kube.com/r/adoptopenjdk/openjdk8
# https://docs.kube.com/develop/develop-images/multistage-build/#use-multi-stage-builds
FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim

WORKDIR /app

# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/build/libs/*-SNAPSHOT.jar jpay.jar
COPY --from=builder /app/sample.db .

# Run the web service on container startup.
CMD ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "jpay.jar" ]

EXPOSE 8000
