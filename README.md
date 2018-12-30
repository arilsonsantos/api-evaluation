# API Evaluation #

## What is this repository for? ##

* The API Evaluation provides resources to add and get products through across rest services using Json content-type.
* Version 1.0.0

#### Available resources 

###### GET 
* /api/evaluation/products
* /api/evaluation/products/product
* /api/evaluation/products/images
* /api/evaluation/products/product-and-or-image
* /api/evaluation/products/{productId}
* /api/evaluation/products/{productId}/product
* /api/evaluation/products/{productId}/images
* /api/evaluation/products/{productId}/product-and-or-image

###### POST 
* /api/evaluation/products
* /api/evaluation/products/{productId}/images

###### DELETE 
* /api/evaluation/products/{productId}
* /api/evaluation/products/{productId}/images
* /api/evaluation/products/{productId}/images/{imagemId}  


#### Setup 

1. Make a clone of the repository 
2. git clone https://github.com/arilsonsantos/api-evaluation.git


#### Run tests 

* mvn test

#### Run the application

* mvn spring-boot:run


#### Examples

* ##### GET

* curl -X GET http://localhost:8080/api/evaluation/products
