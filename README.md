# REST API made in Spring Boot
for handling data from Narodowy Bank Polski

### Building docker image:
```console
docker build -t nbpapi_image .
```

### Running docker image
```console
docker run --name nbpapi -p 8080:8080 nbpapi_image
```
 
### 1. Printing out the exchange rate:
```
/exchanges-rates/{currencyCode}/{date[YYYY-MM-DD]}
```
### Example: exchange rate of Polish zloty to Czech crown on 10th March 2022

```console
curl http://localhost:8080/exchanges-rates/CZK/2022-03-10
```

### screenshot of the JSON result from Postman:
![image](https://user-images.githubusercontent.com/87145190/235984951-0b9b54fd-84f0-4f98-baea-20cb99b9ad2f.png)

### 2. Printing out the highest and the lowest average exchange rate from the given number of quotations:
```
/average-exchange-rates/{currencyCode}/{quotationsNo}
```
### Example: maximal and minimal average exchange rates of Polish zloty to Swedish crown from the last 31 days

```console
curl http://localhost:8080/average-exchange-rates/SEK/31
```
### screenshot of the JSON result from Postman:
![image](https://user-images.githubusercontent.com/87145190/235988517-791eedf7-ccb6-4bd6-8dd2-d4521244dac8.png)

### 3. Printing out the highest offer, so the major difference between bid and ask of exchange rate from the given number of quotations:
```
/bid-ask-offer/{currencyCode}/{quotationsNo}
```
### Example: major difference between bid and ask offer on a Swiss frank from last 7 days:

```console
curl http://localhost:8080/bid-ask-offer/CHF/7
```

### screenshot of the JSON result from Postman:
![image](https://user-images.githubusercontent.com/87145190/235990128-597bb689-017e-48bf-8544-e8c27b059858.png)


