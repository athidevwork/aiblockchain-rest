#!/bin/bash
set -x
docker rm aiblockchain-rest
docker rmi aiblockchain-rest

mvn clean package docker:build

docker run -d -h localhost -p 5000:5000 --name aiblockchain-rest aiblockchain-rest
docker logs -f --tail=all aiblockchain-rest
set +x
