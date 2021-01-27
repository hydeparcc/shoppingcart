# shoppingcart
Shopping Cart application using Angular and Spring Boot

1. Start the `eureka-server` for the `productlist` and `cart` micro service to register themselves for service discovery

2. Start the `productlist` microservice

    2.1. This microservice has `data.sql` to prepopulate products in the in-memory `H2` database
  
    2.2. This has only 1 rest endpoint to provide the list of product

3. Start the `cart` microservice

    3.1. This microservice has in-memory `H2` database to store the cart items and session Id
  
    3.2. This service provides CRUD operation depending upon whether the item or item quantity is added/removed from the cart

4. Start the `angular-shoppingcart` application using `ng serve`

    4.1. This is the basic UI to list all the products and provide shopping cart functionality
  
    4.2. If item is added or removed from the cart, a REST API call is made to `cart` service to update the cart
    
    
