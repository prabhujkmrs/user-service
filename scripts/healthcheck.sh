#!/usr/bin/env bash

CONTAINER_NAME=$(docker-compose ps -q user-service)
echo Running healthcheck...
for i in 1 2 3 4 5 6 7 8 9 10; do
    [[ "healthy" = "$(docker inspect -f '{{.State.Health.Status}}' $CONTAINER_NAME)" ]] && echo "Healthcheck passed" && break
    [[ $i -eq 10  ]] && echo 'Healthcheck failed after 10 attempts' && exit 1
    sleep 6
done

exit 0
