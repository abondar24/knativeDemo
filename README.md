# Knative Demo

Dummy Ktor based service to be deployed in KNative.


## Build and run

- Local build and run

```
./gradlew clean build 

java -jar build/libs/knativeDemo-$version.jar
```

Access: localhost:8080

- Local docker build
```
./gradlew jibDockerBuild

 docker run  -p8020:8020 -ePORT=8020 -eHOSTNAME=host $registry/knativedemo:$version
```
Default port is 8080. Env variables PORT and HOSTNAME are optional for setting

- Docker push
```
./gradlew jib
```

Set DOCKER_REGISTRY, DOCKER_USERNAME and DOCKER_PWD environment variables before build 

- Kubernetes deploy(requires image build)

```
kubectl apply --filename service.yaml   
```

Access: http://knative-demo.default.svc.cluster.local/ or http://knative-demo.default.example.com/ depending on dns type used

