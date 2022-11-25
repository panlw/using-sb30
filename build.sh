#!/usr/bin/env bash

# https://docs.spring.io/spring-boot/docs/current/reference/html/native-image.html
# #native-image.developing-your-first-application.native-build-tools.maven
mvn -Pnative,!dev -DskipTests native:compile
