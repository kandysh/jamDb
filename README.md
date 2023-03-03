![this is pepe](https://github.com/kandysh/jamDb/blob/main/pepepng.png)


Jam Db 

Work in Progress

Template - generated using vite

------------------------
Password Encryption with BCrypt
 -BCrypt works random salt, so each call will generate a different result.

For testing we are using main database as in memory databases don't provide the full test



Api Endpoints
main prefix api/v1
- /user/:username - provides details of a user ; needs JWT token
- /auth 
  - /register - creates a new user returns username email and jwt token
  - /login - authenticates a user returns username email and jwt token