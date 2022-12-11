# Spring-Ecommerce-API
---
# Usage
* clone
* install packages
* change application.properties file with port and url, username and password for your database
* run

## Made with:

- Spring and SpringBoot
- MySQL
- JPA and Hibernate
- Maven
- Stripe API for payments

## Description:

There are endpoints for Users(Sellers, Buyers), their addresses, their interaction with Products and Carts.

## Endpoints:

### Role Related:

|     Endpoint      | HTTP Method |                                              Usage                                              |                                                                  Returns                                                                   |
| :---------------: | :---------: | :---------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------: |
| `/roles` |   `GET`    |                   Call the API with the url                    |                                               Returns all the roles in the collection.                                               |
| `/roles` |   `POST`    |                   Call the API with the url with the details of the role (shortName, longName, canSell)                    |                                               Creates a Role in the Collection and Returns the url to retrieve the new role's details.                                               |
|    `/roles/{id}`    |    `GET`    |                     Call the API with the provided id in the url.                      |                                  Returns the role's details.                                   |
|    `/roles/{id}`    |   `PATCH`   |  Call the API with the provided id in the url and the details that are to be changed.  |                                  Returns the url to recieve the updated role's details.                                   |
|    `/roles/{id}`    |  `DELETE`   |                     Call the API with the provided id in the url.                      |              Deletes the roll from the collection               |


### Address Type Related:

|     Endpoint      | HTTP Method |                                              Usage                                              |                                                                  Returns                                                                   |
| :---------------: | :---------: | :---------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------: |
| `/addressTypes` |   `GET`    |                   Call the API with the url                    |                                               Returns all the address types in the collection.                                               |
| `/addressTypes` |   `POST`    |                   Call the API with the url with the details of the address type (shortName, longName)                    |                                               Creates an address types in the Collection and Returns the url to retrieve the new address type's details.                                               |
|    `/addressTypes/{id}`    |    `GET`    |                     Call the API with the provided id in the url.                      |                                  Returns the address type's details.                                   |
|    `/addressTypes/{id}`    |   `PATCH`   |  Call the API with the provided id in the url and the details that are to be changed.  |                                  Returns the url to recieve the updated address type's details.                                   |
|    `/addressTypes/{id}`    |  `DELETE`   |                     Call the API with the provided id in the url.                      |              Deletes the address type from the collection               |

### Tag Related:

|     Endpoint      | HTTP Method |                                              Usage                                              |                                                                  Returns                                                                   |
| :---------------: | :---------: | :---------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------: |
| `/tags` |   `GET`    |                   Call the API with the url                    |                                               Returns all the tags in the collection.                                               |
| `/tags` |   `POST`    |                   Call the API with the url with the details of the tag (shortName, longName)                    |                                               Creates a Tag in the Collection and Returns the url to retrieve the new tag's details.                                               |
|    `/tags/{id}`    |    `GET`    |                     Call the API with the provided id in the url.                      |                                  Returns the tag's details.                                   |
|    `/tags/{id}`    |   `PATCH`   |  Call the API with the provided id in the url and the details that are to be changed.  |                                  Returns the url to recieve the updated tag's details.                                   |
|    `/tags/{id}`    |  `DELETE`   |                     Call the API with the provided id in the url.                      |              Deletes the roll from the collection; but doesn't remove the tags from the products.               |
|    `/tags/{id}/products`    |  `GET`   |                     Call the API with the provided id in the url.                      |              Returns all the products associated with the tag.               |
|    `/tags/{id}/products`    |  `DELETE`   |                     Call the API with the provided id in the url.                      |              Removes the tags from all the products but doesn't delete the tag from the collection.               |

### Category Related:

|     Endpoint      | HTTP Method |                                              Usage                                              |                                                                  Returns                                                                   |
| :---------------: | :---------: | :---------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------: |
| `/categories` |   `GET`    |                   Call the API with the url                    |                                               Returns all the categories in the collection.                                               |
| `/categories` |   `POST`    |                   Call the API with the url with the details of the category (shortName, longName)                    |                                               Creates a Category in the Collection and Returns the url to retrieve the new role's details.                                               |
|    `/categories/{id}`    |    `GET`    |                     Call the API with the provided id in the url.                      |                                  Returns the category's details.                                   |
|    `/categories/{id}`    |   `PATCH`   |  Call the API with the provided id in the url and the details that are to be changed.  |                                  Returns the url to recieve the updated category's details.                                   |
|    `/categories/{id}`    |  `DELETE`   |                     Call the API with the provided id in the url.                      |              Deletes the category from the collection, but doesn't remove the category from the product              |
|    `/categories/{id}/products`    |  `GET`   |                     Call the API with the provided id in the url.                      |              Returns all the products associated with the category.               |
|    `/categories/{id}/products`    |  `DELETE`   |                     Call the API with the provided id in the url.                      |              Removes the category from all the products but doesn't delete the category from the collection.               |

### Discount Related:

|     Endpoint      | HTTP Method |                                              Usage                                              |                                                                  Returns                                                                   |
| :---------------: | :---------: | :---------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------: |
| `/discounts` |   `GET`    |                   Call the API with the url                    |                                               Returns all the discounts in the collection.                                               |
| `/discounts` |   `POST`    |                   Call the API with the url with the details of the discount (shortName, longName, active, percentage)                    |                                               Creates a Discount in the Collection and Returns the url to retrieve the new discount's details.                                               |
|    `/discounts/{id}`    |    `GET`    |                     Call the API with the provided id in the url.                      |                                  Returns the discount's details.                                   |
|    `/discounts/{id}`    |   `PATCH`   |  Call the API with the provided id in the url and the details that are to be changed.  |                                  Returns the url to recieve the updated discount's details.                                   |
|    `/discounts/{id}`    |  `DELETE`   |                     Call the API with the provided id in the url.                      |              Deletes the discount from the collection, but it isn't removed from the products.              |
|    `/discounts/{id}/products`    |  `GET`   |                     Call the API with the provided id in the url.                      |              Returns all the products associated with the discount               |
|    `/discounts/{id}/products`    |  `DELETE`   |                     Call the API with the provided id in the url.                      |              Deletes the discount from the products; but it's not deleted from the collection.               |
|    `/discounts/active/{status}`    |  `GET`   |                     Call the API with the provided status in the url.                      |              Returns all the discounts with the active status               |
|    `/discounts/percentage/{amount}`    |  `GET`   |                     Call the API with the provided discount percentage in the url.                      |              Returns all the discounts with the percentage               |

### User Related:

|     Endpoint      | HTTP Method |                                              Usage                                              |                                                                  Returns                                                                   |
| :---------------: | :---------: | :---------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------: |
| `/users` |   `GET`    |                   Call the API with the url                    |                                               Returns all the users in the collection.                                               |
| `/users` |   `POST`    |                   Call the API with the url with the details of the users (firstname, middlename,lastname, email, password, roleType)                    |                                               Creates a User in the Collection and Returns the url to retrieve the new user's details.                                               |
|    `/users/{id}`    |    `GET`    |                     Call the API with the provided id in the url.                      |                                  Returns the entire user details except private details like passwords.                                   |
|    `/users/{id}`    |   `PATCH`   |  Call the API with the provided id in the url and the details that are to be changed.  |                                  Returns the url to recieve the updated users details.                                   |
|    `/users/{id}`    |  `DELETE`   |                     Call the API with the provided id in the url.                      |              Deletes the user from the collection               |
|    `/users/{id}/addresses`    |  `GET`   |                     Call the API with the provided id in the url.                      |              Returns all the address associated with the user.               |
|    `/users/{id}/addresses`    |  `POST`   |                     Call the API with the provided id in the url and the the details of the address (addressType, country, city, streetAddress1, streetAddress2 (optional), postalCode).                      |              Creates an address in the collection               |
|    `/users/{userId}/addresses/{addressId}`    |  `GET`   |                     Call the API with the provided user id and address id in the url.                      |              Returns the address details of the specified address id.            |
|    `/users/{userId}/addresses/{addressId}`    |  `DELETE`   |                     Call the API with the provided user id and address id in the url.                      |              Deletes the user address from the collection.            |
|    `/users/{userId}/addresses/{addressId}`    |  `Patch`   |                     Call the API with the provided user id and address id in the url.                      |              Updates the address of the user               |

### Product Related:

|           Endpoint           | HTTP Method |                                             Usage                                             |                                                                  Returns                                                                   |
| :--------------------------: | :---------: | :-------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------: |
| `/products` |   `GET`    |                   Call the API with the url                    |                                               Returns all the products in the collection.                                               |
| `/products` |   `POST`    |                   Call the API with the url with the details of the product (shortName, longName, quantity, categoriesIds, sellerId, tagIds, discountId, price)                    |                                               Creates a Product in the Collection and Returns the url to retrieve the new product's details.                                               |
|    `/products/{id}`    |    `GET`    |                     Call the API with the provided id in the url.                      |                                  Returns the product's details.                                   |
|    `/products/{id}`    |   `PATCH`   |  Call the API with the provided id in the url and the details that are to be changed.  |                                  Returns the url to recieve the updated role's details.                                   |
|    `/products/{id}`    |  `DELETE`   |                     Call the API with the provided id in the url.                      |              Deletes the product from the collection               |

### Cart Related:

|           Endpoint           | HTTP Method |                                              Usage                                              |                                                      Returns                                                      |
| :--------------------------: | :---------: | :---------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------: |
| `/users/{id}/carts` |   `GET`    |                   Call the API with the url with the user id                   |                                               Returns all the carts for the user.                                               |
| `/users/{id}/carts` |   `POST`    |                   Call the API with the url with the details of the cart (buyerId).                    |                                               Creates a Cart in the Collection and Returns the url to retrieve the new cart's details.                                               |
|    `/users/{userId}/carts/{cartId}`    |    `GET`    |                     Call the API with the user id and cart id in the url.                      |                                  Returns the entire cart details.                                   |
|    `/users/{userId}/carts/{cartId}`    |  `DELETE`   |                     Call the API with the user id and cart id in the url.                      |              Deletes the cart from the collection               |
|    `/users/{userId}/carts/{cartId}/items`    |  `GET`   |                     Call the API with the user id and cart id in the url.                      |              Returns all the items associated with the cart.               |
|    `/users/{userId}/carts/{cartId}/items`    |  `POST`   |                     Call the API with the user id and cart id in the url and the the details of the item (productId, qunatity).                      |              Creates a cartItem in the collection and adds it to the cart               |
|    `/users/{userId}/carts/{cartId}/items/{itemId}`    |  `GET`   |                     Call the API with the provided user id, cart id and item id in the url.                      |              Returns the item details of the specified cart id.            |
|    `/users/{userId}/carts/{cartId}/items/{itemId}`    |  `DELETE`   |                     Call the API with the provided user id, cart id and item id in the url.                      |              Deletes the cart item from the collection.            |



## Database Schema:
