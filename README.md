# AREP - LAB 5

1. The MongoDB service is an instance of MongoDB running in a docker container on an EC2 virtual machine
2. LogService is a REST service that receives a string, stores it in the database and responds in a JSON object with the last 10 strings stored in the database and the date they were stored.
3. The APP-LB-RoundRobin web application is composed of a web client and at least one REST service. The web client has a field and a button and every time the user sends a message, it sends it to the REST service and updates the screen with the information that it returns in JSON format. The REST service receives the string and implements a Round Robin load balancing algorithm, delegating the message processing and response return to each of the three instances of the LogService.

## Getting Started

### Pre-requisites

You need to have installed on your PC:

- JDK 
- Maven 
- Git
- Docker

Also, be aware of having an account in AWS

### Installing

Open a command prompt on the folder that you are going to save this project and copy:

```
git clone https://github.com/CarlosGomez380/arep-lab5.git
```

To change the URLs of the VM go to the class Rounrobin in lab5


The mongo's IP go to the class LogService in logservice



Once finish this process, open the 2 projects' folder on the terminal with 

```
cd arep-lab5/lab5
```

And copy:

```
mvn clean install
```

And

```
cd ..
```

```
cd logService
```

And copy:

```
mvn clean install
```

In your EC2 get the containers, get the same ports as you'll see below:

```
docker run -d -p 27017:27017 --name mongo-bd mongo
```

```
docker run -d -p 35000:6000 --name logservice1 carlosgomez380/logservice
```

```
docker run -d -p 35001:6000 --name logservice2 carlosgomez380/logservice
```

```
docker run -d -p 35002:6000 --name logservice3 carlosgomez380/logservice
```

```
docker run -d -p 35003:6001 --name roundrobin carlosgomez380/roundrobin
```

To see your containers:

```
docker ps
```

## Documentation JavaDoc

To see the javadoc generated copy:

```
mvn javadoc:javadoc
```

This document will be located in /target/site


## Built With

- [Maven](https://maven.apache.org/) - Dependency Management
- [Docker](https://www.docker.com/) - Container creator
- [Spark](http://sparkjava.com/) - framework

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
