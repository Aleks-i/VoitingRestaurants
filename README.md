Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

Environment variable for logging: VOTE_RESTAURANTS

Use curl for test:

####                AdminRestController

#### get All Admins
curl -s http://localhost:8080/votingrestaurants/rest/admins

#### get Admins 100000
curl -s http://localhost:8080/votingrestaurants/rest/admins/100000


####                DishRestController

#### get All Dishes For Admin 100000
curl -s http://localhost:8080/votingrestaurants/rest/admins/dishes/100000

#### create Dishes For Admin 100000 (!!! HTTP Status 400 – Bad Request)
curl -s -X POST -d '{"name":"новая еда","price":"250"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingrestaurants/rest/admins/dishes/create --user admin0@yandex.ru:password0

#### delete Dishes 100007 For Admin 100000
curl -s -X DELETE http://localhost:8080/votingrestaurants/rest/admins/dishes/delete/100007 --user admin0@yandex.ru:password0


####                UserRestController

#### delete Auth Users (!!! HTTP Status 500 – Internal Server Error)
curl -s -X DELETE http://localhost:8080/votingrestaurants/rest/users/profile/delete --user user0@yandex.ru:password0

#### register Users (!!! HTTP Status 500 – Internal Server Error)
curl -s -i -X POST -d '{"name":"New User","email":"testuser@mail.ru","password":"testpassword"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingrestaurants/rest/users/register

#### update User 100002 (!!! HTTP Status 415 – Unsupported Media Type)
curl -s -X PUT -d '{"name":"Update User","email":"updateuser@mail.ru","password":"updatepassword"}' -H 'Content-Type: application/json' http://localhost:8080/votingrestaurants/rest/users --user user0@yandex.ru:password0

####                VoteRestController

#### get All Vote
curl -s http://localhost:8080/votingrestaurants/rest/admins/votes

#### get All Vote For Admin 100000
curl -s http://localhost:8080/votingrestaurants/rest/admins/votes/100000

#### create Vote User 100002 Admin 100000 (!!! HTTP Status 500 – Internal Server Error)
curl -s -X POST -d '{"localDate":"2020-09-20","localTime":"10:00:00","user_id":100002}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingrestaurants/rest/admins/votes/100000 --user user0@yandex.ru:password0
