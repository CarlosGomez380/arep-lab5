FROM openjdk:8

WORKDIR /usrcarlos/bin

ENV PORT 6001

COPY /target/classes /usrcarlos/bin/classes
COPY /target/dependency /usrcarlos/bin/dependency

CMD ["java", "-cp", "./classes:./dependency/*","co.edu.escuelaing.lab4.App"]