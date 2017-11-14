IMAGE_NAME=$1
CONTAINER_NAME=$RANDOM

if [[ $# -ne 1 ]]; then
    echo "Must provide 1 argument: ./healtcheck.sh IMAGE_NAME"
    exit 1
fi

echo Running container $CONTAINER_NAME using image $IMAGE_NAME
docker run -it -d --rm --name ${CONTAINER_NAME} ${IMAGE_NAME}

echo Running healthcheck...
for i in 1 2 3 4 5 6 7 8 9 10; do
    [[ "healthy" = "$(docker inspect -f '{{.State.Health.Status}}' $CONTAINER_NAME)" ]] && echo "Healthcheck passed" && break
    [[ $i -eq 10  ]] && echo 'Healthcheck failed after 10 attempts' && exit 1
    sleep 6
done

echo Stopping container...
docker stop $CONTAINER_NAME
exit 0
