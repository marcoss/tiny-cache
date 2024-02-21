# tiny-cache

## Info
A simple and tiny cache service written in Kotlin and deployed with Kubernetes.

## Run

### Start service
```
bazel run //src/main/kotlin/mcast/tinycache
```

### Start service with capacity of 10
```
bazel run //src/main/kotlin/mcast/tinycache -- 10
```

### Get
```
http http://localhost:8080/api/v1/tinycache/$K
```

### Put
```
http PUT http://localhost:8080/api/v1/tinycache key=$K value=$V
```