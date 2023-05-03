# REST API made in Spring Boot
# for handling data from Narodowy Bank Polski

### Building docker image:
```bash
docker build -t nbpapi_image .
```

### Running docker image
```bash
 docker run --name nbpapi -p 8080:8080 nbpapi_image
 ```
 
 #### 1. Printing out the exchange rate of Polish zloty to Czech crown on 10th March 2022

```bash
curl http://localhost:8080/exchanges-rates/CZK/2022-03-10
```

##### screenshot of the result from Postman:
![image](https://user-images.githubusercontent.com/87145190/235984951-0b9b54fd-84f0-4f98-baea-20cb99b9ad2f.png)
