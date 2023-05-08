# MessengerServiceClient

# Localhost:8081
# Problem

A business needs a software that manages all the business logic, for this you need customers to interact with the application to be able to generate services with the company, you need to have a record and control of employees and allow the management of processes relating to the services offered by the company, you need a package management to have control over shipments and inventory, in addition to having a more reliable delivery system, and you need to be able to manage shipments to finish with your business logic.

# Description

This repository is a microservice about application MessengerService, in this microservice, you will find all management about Client, When somebody want to use this
application, The Client must to register in the system, in this microservice, users can register, update their information, get their information and delete their 
susbscription.

#Technologies
The project was built in: 

-Java
-SpringBoot
-SpringJPA
-SpringSecurity
-MySQL
-JUnit
-Swagger
-CI/CD

# Funcionalities

[POST] Create: A client can register in the database.
RequestBody:
{
	"dni":123,
	"nameClient":"Juan",
	"lastNameClient":"Murillo",
	"phoneClient":"5555555555",
	"emailClient":"sJuanprog@gmail.com",
	"residencyAddressClient":"Carrera 97 # 23",
	"cityLocationClient":"Bogota"
}

[PUT] Update: A client can update their information.
Param: The param need a client registered in the database: {"dni":123}
RequestBody:
{
	"dni":123,
	"nameClient":"Juan",
	"lastNameClient":"Murillo",
	"phoneClient":"5555555555",
	"emailClient":"sJuanprog@gmail.com",
	"residencyAddressClient":"Carrera 97 # 23",
	"cityLocationClient":"Bogota"
}

[DELETE] Delete: A client can delete their susbscription.
Param: The param need a client registered in the database: {"dni":123}

[GET] Check Client: A client can consult their information in the database.
Param: The param need a client registered in the database: {"dni":123}



![ClientMessengerService](https://user-images.githubusercontent.com/119947948/236726029-e4f9982a-eb7a-4fdd-bcc6-7961f3dc23a0.jpg)


