# Cinema Service #
### About the project ###
This project simulates work of a real movie-service app, letting you to:
- register as a user, log in as an admin or user
- check all available movies, movie sessions, cinema halls (data is displayed in JSON format)
- add tickets for movies you would like to watch, and complete the order after you got all the tickets you needed
### List of endpoints ###
POST: /register - all  
POST: /cinema-halls - admin role required  
POST: /movies - admin role required    
POST: /movie-sessions - admin role required  
PUT: /movie-sessions/{id} - admin role required  
DELETE: /movie-sessions/{id} - admin role required  
GET: /users/by-email - admin role required  
GET: /cinema-halls - user/admin  
GET: /movies - user/admin  
GET: /movie-sessions/available - user/admin  
GET: /movie-sessions/{id} - user/admin  
GET: /orders - user role required  
POST: /orders/complete - user role required  
PUT: /shopping-carts/movie-sessions - user role required  
GET: /shopping-carts/by-user - user role required  
### Technologies used ###
- Java 11
- Hibernate
- Spring
- MySQL
- REST
- Apache Tomcat
### Installation ###
To install and run this app you need to consequently follow these steps:
1. Install MySQL and Apache Tomcat
2. Create an empty DB called "cinema-app"
3. Clone this project to your IDE
4. Configure src/main/resources/db.properties file for it to fit your DB
5. Add Tomcat to your project's run/debug 
6. Edit admin's email/password if needed (src/main/java/application/config/DataInitializer)
7. Run the project