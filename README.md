Here are the requirements for this service:
As an admin user, I want to be able to create a feature which defaults to disabled As an admin user, I want to be able to switch on a feature for a user As a user, I want to be able to get all the enabled features (a mix of all the globally enabled ones and the ones enabled just for my user)

the service as the following
User endpoints
GET:http://localhost:8080/user/allusers :to list all the users

POST :http://localhost:8080/user/adduser :to add user

example to user model is { "username":"mina3", "password":"123", "role":"user" }

GET:http://localhost:8080/user/all/{id} : get all the enabled features (a mix of all the globally enabled ones and the ones enabled just for my user) where {id} is the user ID

admin endpoints
GET :http://localhost:8080/admin/all list all the features

POST : http://localhost:8080/admin/addfeature to add new feature PUT :http://localhost:8080/admin/switchon/{id} switch speific feature to be enabled globally PUT :http://localhost:8080/admin/switchon/{id}/user/{username} enable feature to user PUT :http://localhost:8080/admin/switchoff/{id}/user/{username} disbale feature to user

TO-DO
-add spring security to allow authentication to endpoints 
-add authorization so not all users be allowed to use admin endpoints
