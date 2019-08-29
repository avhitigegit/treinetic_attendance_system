FROM openjdk:11
#Add the jar file which is build by our Application  /path want to add(eg: as root directory)
ADD /target/treinetic-attendance-system.jar /treinetic-attendance-system.jar
#port want to expose
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "treinetic-attendance-system.jar"]

#To create a docker image from terminal
#docker build -f Dockerfile -t treinetic-attendance-system .
#After buid Then up the compose

# -f  = The docker file of the project
# -t  = The Image want to be create as
# .   = Current directory

#To run docker image using image id
#docker run -i -t 2a29d1e80db2 /bin/bash

#Remove unused images, container and all
#docker system prune

#To run docker image
#docker run -p 8080:8080 treinetic-attendance-system
#docker run -p 27017:27017 -v /Users/amrithahitige/MongoDB/data:/data/db --name mongodb mongo
#docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management      correct
#-p myportnumber:theirportnumber    -p 1818:5672
#docker run -d --hostname my-rabbit --name rabbitmqdocker -p 15672:15672 rabbitmq:3-management
#docker run -d --hostname my-rabbit --name some-rabbit -p 5672:5672 rabbitmq:3          correct