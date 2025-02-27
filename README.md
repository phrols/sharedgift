# sharedgift

## data base
## build images from docker-compose.yml
docker-compose build
## start a container
docker-compose up -d sgdb

## Stop and Remove Containers - This stops and removes all running containers, networks, and by default, does not remove named volumes.
docker-compose down

## Remove Volumes (Reset Data) - This ensures that all named and anonymous volumes associated with the services are deleted, resetting any stored data.
docker-compose down --volumes

## (Optional) Remove Built Images
docker-compose down --rmi all --volumes
--rmi all: Removes all images built by docker-compose build
--volumes: Deletes all associated volumes

## Restart all
docker-compose up -d

## connect into the container
docker exec -it sharedgift-db bash

## define vhost
<VirtualHost *:80>
ServerName auxiliaires
LogLevel proxy:debug proxy_http:debug
ProxyPass "/api/" "http://localhost:8080/" upgrade=websocket
ProxyPassReverse "/api/" "http://localhost:8080/"

ProxyPass "/" "http://localhost:5173/" upgrade=websocket
ProxyPassReverse "/" "http://localhost:5173/"
</VirtualHost>
