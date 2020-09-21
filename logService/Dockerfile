FROM openjdk:8

WORKDIR /usrcarlos/bin

ENV PORT 6000

COPY /target/classes /usrcarlos/bin/classes
COPY /target/dependency /usrcarlos/bin/dependency

CMD ["java", "-cp", "./classes:./dependency/*","edu.escuelaing.service.App"]