FROM openjdk:11
COPY presentation/src/main/java/am/smartcafe/ /tmp
WORKDIR /tmp
EXPOSE 8080
CMD java presentation.am.smartcafe.presentation.SmartCafeApplication
