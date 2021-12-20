FROM openjdk:11
COPY presentation/src/main/java/am/smartcafe/ /tmp
WORKDIR /tmp
CMD java presentation.SmartCafeApplication

