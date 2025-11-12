#!/bin/sh
set -e

./mvnw clean package -DskipTests

echo "app.jar создан"

docker build -t sentiment-api:local .

minikube image load sentiment-api:local
echo "Образ загружен в Minikube"