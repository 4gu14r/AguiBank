FROM maven

RUN apt-get update && apt-get upgrade -y

WORKDIR /workspace

COPY ../ .

RUN mvn clean
RUN mvn dependency:go-offline

CMD ["mvn", "clean", "package"]