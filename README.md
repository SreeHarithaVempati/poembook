# poembook

The aim of this project is to browse poems by title or authors.

## Problem Statement

Build an application to search,save and browse poems/sonnets.

## Requirements:

1. The application needs to display the poems from [http://poetrydb.org].
2. A frontend where the user can see the **Sonnets**, **Favourites**,**Author's list** and should be able to **search poems and sonnets by different authors** out of it.
3. After reading poems user should be able to add poems to the favourites list.

## Modules:
1. userlistservice - Should be able to manage user accounts
2. frontend -
  - User should be able to
    - search different types of poems/sonnets by title
	- search all poems/sonnets of a particular author
    - add to favourites
    - delete poems from favourite list
    - should be able to add/update/delete comments for a poem in favourites list
3. favouritelistservice - Application should be able to 
  -  store favourites
  -  credentials


## Flow of Modules

- **Building frontend**
  1. Building **Responsive** Views:
    1. Build a View to show all the sonnets and authors
      1. Poems/sonnets - Populating from external api
      2. Build a view to show all favourites
      3. A view to authenticate users
  2. Using **Services** to populate these data in views
  3. Stitching these views using **Routes and Guards**
  4. E2E test cases and unit test cases
  5. **Writing CI configuration file**
  6. **Dockerized the frontend**

- **Building the userlistservice**
  1. Creating a server in Spring Boot to 
    1. **facilitate registration** and login using **JWT token** and **MySQL**
  2. Writing Swagger Documentation
  3. Unit Testing
  4. Writing CI Configuration
  5. Dockerized the application

  - **Building the favouriteservice**
  1. Creating a server in Spring Boot to 
    1. **facilitate CRUD operation** over favourites resources stored in MySQL
  2. Writing Swagger Documentation
  3. Unit Testing
  4. Writing CI Configuration
  5. Dockerized the application

  
## Running unit tests for frontend

Run `ng test` to execute the unit tests .

## Running unit tests for userlistservice and favouritelistservice

Right click on the testing class and click `Run As `Junit`` .

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests .

## Running The Application

Run `docker-compose up` to run the full scaled aplication .


