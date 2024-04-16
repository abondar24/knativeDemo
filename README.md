# Knative Demo

Dummy Ktor based service to be deployed in KNative.


## Build and run

- Local run

```
./gradlew clean build
```

Access: localhost:8080

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

