# REST API made in Spring Boot
# for handling data from Narodowy Bank Polski

### Building docker image:
```docker
docker build -t nbpapi_image .
```

### Running docker image
```docker
 docker run --name nbpapi -p 8080:8080 nbpapi_image
 ```
