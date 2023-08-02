# temp build
FROM docker.io/gradle:8.0.2-jdk AS TEMP_BUILD
ARG SKIP_TESTS=false
# Copy project files
# COPY --chown=gradle:gradle . /home/gradle/src
#--chown=gradle:gradle
COPY  --chmod=gradle:gradle build.gradle.kts settings.gradle.kts /home/gradle/src/
COPY --chmod=gradle:gradle src /home/gradle/src/src
COPY --chmod=gradle:gradle gradle /home/gradle/src/gradle
COPY --chmod=gradle:gradle docker/configs /home/gradle/src/configs
COPY --chmod=gradle:gradle service-matrix.properties /home/gradle/src/
WORKDIR /home/gradle/src
RUN if [ "$SKIP_TESTS" = "true" ]; then \
    gradle build --no-daemon -x test; \
  else \
    gradle build --no-daemon; \
  fi

# build image
FROM openjdk:17-alpine
RUN addgroup -S nonroot \
    && adduser -S nonroot -G nonroot
USER nonroot
WORKDIR /app
COPY --from=TEMP_BUILD /home/gradle/src/service-matrix.properties /app/
COPY --from=TEMP_BUILD /home/gradle/src/configs /app/configs
COPY --from=TEMP_BUILD /home/gradle/src/build/libs/*.jar /app/
ENTRYPOINT ["java", "-jar", "/app/in2-wallet-wca-0.0.1-SNAPSHOT.jar"]
