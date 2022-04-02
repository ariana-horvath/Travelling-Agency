# Travelling Agency

The application accommodates 2 types of users: Regular User/Vacay seeker and Travelling Agency </br> </br> 
The Travelling Agency should be able to:
1. add vacation destination
2. add vacation packages for a specific destination (should contain name, price, period, extra details, number
of people that can book the vacation)
3. edit an existing vacation package
4. delete an existing vacation package
5. view all its listed vacation packages (with status: BOOKED, NOT_BOOKED,
IN_PROGRESS)
6. delete vacation destination </br> 
The Regular User should be able to:
1. register on the platform using some credentials (username/email - unique &
password)
2. login on the platform
3. view all available vacation packages
4. filter vacation packages by destination/price/period
5. book a vacation
6. view all its booked vacations </br> 
Note: Each vacation package has a limit of people that can book the trip. When
someone books the trip, you should keep track of the number of people who booked
a vacation, so that if the maximum number of people who can book that vacation is
reached, another user will not be able to see that vacation anymore in the platform. </br> 
Note2: When displaying a vacation to the Travelling Agency, a status will be shown,
based on the number of people who booked that vacation, such as:
* NOT_BOOKED: nobody booked that vacation yet
* IN_PROGRESS: somebody booked that vacation, but limit not reached
* BOOKED: limit of people is reached

About the application:
* Desktop application, written in Java + Java Swing
* Connectivity to relational database + storage of data
* Implements Data Access Layer using ORM (Hibernate)
* Implemented using Layered Architecture
