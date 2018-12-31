# API Evaluation #


* The API Evaluation provides resources to add and get products through across rest services using Json content-type (JAX-RS Implementation).
* Version 1.0.0

#### Available resources 

###### GET 
* /api/evaluation/products
* /api/evaluation/products/product
* /api/evaluation/products/image
* /api/evaluation/products/product-and-or-image
* /api/evaluation/products/{productId}
* /api/evaluation/products/{productId}/product
* /api/evaluation/products/{productId}/image
* /api/evaluation/products/{productId}/product-and-or-image

###### POST (ADD/UPDATE)
* /api/evaluation/products
* /api/evaluation/products/{productId}/images

###### DELETE 
* /api/evaluation/products/{productId}
* /api/evaluation/products/{productId}/images
* /api/evaluation/products/{productId}/images/{imagemId}  


#### Setup 

1. Make a clone of the repository 
2. git clone https://github.com/arilsonsantos/api-evaluation.git

3. Or download it clicking [here](https://bitbucket.org/arilsonsantos/api-evaluation/get/ab3f406d5596.zip)

#### Run tests 

* mvn test

#### Run the application

* mvn spring-boot:run


### Examples


* Find all products excluding relationships.
![01-products.png](https://bitbucket.org/repo/9pMrKdz/images/1208196868-01-products.png)

* Find all products with child product(s).
![02-products-product.png](https://bitbucket.org/repo/9pMrKdz/images/2975098416-02-products-product.png)

* Find all products with image(s).
![03-products-image.png](https://bitbucket.org/repo/9pMrKdz/images/3228795575-03-products-image.png)

* Find all products with child product(s) and/or image(s).
![04-products-product-and-or-image.png](https://bitbucket.org/repo/9pMrKdz/images/736841398-04-products-product-and-or-image.png)

* Get one product excluding relationships.
![05-products-product-one.png](https://bitbucket.org/repo/9pMrKdz/images/25210668-05-products-product-one.png)

* Get one product with child product(s).
![06-products-product-one-product.png](https://bitbucket.org/repo/9pMrKdz/images/3151845787-06-products-product-one-product.png)

* Get one product with image(s).
![07-products-product-one-image.png](https://bitbucket.org/repo/9pMrKdz/images/3463753563-07-products-product-one-image.png)

* Get one product with child product(s) and/or image(s).
![08-products-product-one-product-and-or-image.png](https://bitbucket.org/repo/9pMrKdz/images/1460114968-08-products-product-one-product-and-or-image.png)

* Add product. 
 Both name and description are required. You can add a parent product and/or images as well.
![09-products-post-prorduct.png](https://bitbucket.org/repo/9pMrKdz/images/1542565599-09-products-post-prorduct.png)

* Update product. It is needed to set the <id>.
![09.0-products-post-prorduct.png](https://bitbucket.org/repo/9pMrKdz/images/1086120484-09.0-products-post-prorduct.png)

* Add image(s) to one product.
![10-products-post-images.png](https://bitbucket.org/repo/9pMrKdz/images/1857542162-10-products-post-images.png)

* Update image(s) to one product.
![10-products-post-images-update.png](https://bitbucket.org/repo/9pMrKdz/images/240106182-10-products-post-images-update.png)

- Trying to add images(s) to a product that doesn't exist
![10.1-products-post-images-product-not-found.png](https://bitbucket.org/repo/9pMrKdz/images/4149109270-10.1-products-post-images-product-not-found.png)

* Delete one product and its child(s) and/or image(s).
![11-products-delete-product.png](https://bitbucket.org/repo/9pMrKdz/images/3754758966-11-products-delete-product.png)

* Delete image(s) of a product
![12-products-delete-images.png](https://bitbucket.org/repo/9pMrKdz/images/907312418-12-products-delete-images.png)

* Delete one image of a product
![13-products-delete-image.png](https://bitbucket.org/repo/9pMrKdz/images/3281457171-13-products-delete-image.png)
