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
|    `/roles/id`    |    `GET`    |                     Call the API with the provided id in the url.                      |                                  Returns the role's details.                                   |
|    `/roles/id`    |   `PATCH`   |  Call the API with the provided id in the url and the details that are to be changed.  |                                  Returns the url to recieve the updated role's details.                                   |
|    `/roles/id`    |  `DELETE`   |                     Call the API with the provided id in the url.                      |              Deletes the roll from the collection               |


### Address Type Related:

|     Endpoint      | HTTP Method |                                              Usage                                              |                                                                  Returns                                                                   |
| :---------------: | :---------: | :---------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------: |
| `/addressTypes` |   `GET`    |                   Call the API with the url                    |                                               Returns all the address types in the collection.                                               |
| `/addressTypes` |   `POST`    |                   Call the API with the url with the details of the address type (shortName, longName)                    |                                               Creates an address types in the Collection and Returns the url to retrieve the new address type's details.                                               |
|    `/addressTypes/id`    |    `GET`    |                     Call the API with the provided id in the url.                      |                                  Returns the address type's details.                                   |
|    `/addressTypes/id`    |   `PATCH`   |  Call the API with the provided id in the url and the details that are to be changed.  |                                  Returns the url to recieve the updated address type's details.                                   |
|    `/addressTypes/id`    |  `DELETE`   |                     Call the API with the provided id in the url.                      |              Deletes the address type from the collection               |


### User Related:

|     Endpoint      | HTTP Method |                                              Usage                                              |                                                                  Returns                                                                   |
| :---------------: | :---------: | :---------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------: |
| `/users` |   `GET`    |                   Call the API with the url                    |                                               Returns all the users in the collection.                                               |
| `/users` |   `POST`    |                   Call the API with the url with the details of the users (firstname, middlename,lastname, email, password, roleType)                    |                                               Creates a User in the Collection and Returns the url to retrieve the new user's details.                                               |
|    `/users/id`    |    `GET`    |                     Call the API with the provided id in the url.                      |                                  Returns the entire user details except private details like passwords.                                   |
|    `/users/id`    |   `PATCH`   |  Call the API with the provided id in the url and the details that are to be changed.  |                                  Returns the url to recieve the updated users details.                                   |
|    `/users/id`    |  `DELETE`   |                     Call the API with the provided id in the url.                      |              Deletes the user from the collection               |
|    `/users/id/addresses`    |  `GET`   |                     Call the API with the provided id in the url.                      |              Returns all the address associated with the user.               |
|    `/users/{d/addresses`    |  `POST`   |                     Call the API with the provided id in the url and the the details of the address (addressType, country, city, streetAddress1, streetAddress2 (optional), postalCode).                      |              Creates an address in the collection               |
|    `/users/userId/addresses/addressId`    |  `GET`   |                     Call the API with the provided user id and address id in the url.                      |              Returns the address details of the specified address id.            |
|    `/users/userId/addresses/addressId`    |  `DELETE`   |                     Call the API with the provided user id and address id in the url.                      |              Deletes the user address from the collection.            |
|    `/users/userId/addresses/addressId`    |  `Patch`   |                     Call the API with the provided user id and address id in the url.                      |              Updates the address of the user               |

### Product Related:

|           Endpoint           | HTTP Method |                                             Usage                                             |                                                                  Returns                                                                   |
| :--------------------------: | :---------: | :-------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------------------: |


### Cart Related:

|           Endpoint           | HTTP Method |                                              Usage                                              |                                                      Returns                                                      |
| :--------------------------: | :---------: | :---------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------: |


## Database Schemas
