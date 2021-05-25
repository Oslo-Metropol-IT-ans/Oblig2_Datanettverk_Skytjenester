#! /bin/sh

#mvn clean install
docker build -t chat-api .
docker run -p8080:8080 chat-api

