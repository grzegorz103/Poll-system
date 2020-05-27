# Poll system

## Table of contents
* [General info](#general-info)
* [Demo](#demo)
* [Technologies](#technologies)
* [Features](#features)
* [Setup](#setup)

## General info

This application allows you to create polls.

## Demo

Live demo available [here](https://hot-poll.herokuapp.com)

## Technologies

* Spring Boot
* Angular
* Hibernate
* PostgreSQL
* Maven, Lombok

## Features

- Create and manage polls
- Create private only polls
- Create multiple vote polls
- Vote in polls
- Browse public votes
- View poll's statistics in charts

## Setup
### Prerequisites

- Angular 5 or greater is required
```$xslt
$ npm install -g @angular/cli
``` 
- Java 8+

### Deployment

```
$ mvn spring-boot:run
$ cd front
$ npm install
$ ng serve
```
Run browser and head to ```http://localhost:4200```
