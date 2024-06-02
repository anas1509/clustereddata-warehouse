# Project: clustereddata-warehouse

## How to run:

To run this application you should have docker and docker-compose installed in you machine then run the following command on the root of this directory:

```bash
    docker-compose -f docker-compose.yml up --build
```


## tecnical tools:
    programming language: java 17
    framework: spring boot 3.3.0
    build tool: Apache Maven 3.9.0
    unit testing: Junit
    DB: postges 15


## This simple project has four APIs:

1. Save deal: /fxDeals/service/saveDealsave
    HTTP method: POST
    this API takes a deal object in the body and save it if the deal is valid.

```JSON
    sample JSON body:
        {
            "id": null,
            "uuid": "123e4567-e89b-12d3-a456-426614174689",
            "fromCurrencyIsoCode": "USD",
            "toCurrencyIsoCode": "JOD",
            "dealTimestamp": "2023-06-01T12:00:00",
            "amount": 234.50,
            "creationDate": null,
            "updateDate": null
        }
```

    The validation mainly check for the following points:
    1 - check if the currency from is in a valid ISO code format.
    2 - check if the currency to is in a valid ISO code format.
    3 - check if the deal is not already submitted, by checking if there is an existing deal with the same UUID saved in the DB, assuming that UUID is the business identifier of the deal.
    4 - check if the deal amount is valid, only accept values that are above 0.00.

2.  Get deal bu UUId: /fxDeals/service/getDealByUuid?uuid=xxxxxx
    HTTP method: GET
    This API allow the user to look for the deal details using the deal UUID as a URl parameter.

3. Get all deals: /fxDeals/service/getAllDeals
    HTTP method: GET
    This API retrieve all deals and do not take any parameters, and return a list of all deals.

4. Keep alive: /fxDeals/keep-alive
    HTTP method: POST
    This API returns a simple string to monitor the application availability.

## Utils:

- In this project, I made a cutom Exception "BusinessException.java" that is used to handle validations and returns a meaningful message to the consumers
- Controller Advice: to handle the response of the exceptions.
- lookup service, which is used to for storing currnecies information, the dataset I used where from online resource, whic is a simple table that contians mainly the country name and the currency in ISO format, which is used mainly for validations.
- Junit: used to create a simple test cases for the services in the project.


### Please refer to the postman collection in the root of this project for quick testing.