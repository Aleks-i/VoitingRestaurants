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

####                RestaurantRestController

#### get All Restaurants
`curl -s http://localhost:8080/votingrestaurants/restaurants`

#### get Restaurant 100007
`curl -s http://localhost:8080/votingrestaurants/restaurants/100007`


####                DishRestController

#### get All Dishes For Restaurant 100007
`curl -s http://localhost:8080/votingrestaurants/restaurants/dishes/100007`

#### create Dishes For Restaurant 10007 ()
`curl -s -X POST -d '{"name":"новая еда","price":"250"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingrestaurants/restaurants/dishes/100007/create --user admin0@yandex.ru:password0`

#### delete Dishes 100009 For Restaurant 100007
`curl -s -X DELETE http://localhost:8080/votingrestaurants/restaurants/dishes/100007/delete/100009 --user admin0@yandex.ru:password0`


####                UserRestController

#### delete Auth Users
`curl -s -X DELETE http://localhost:8080/votingrestaurants/users/profile/delete --user admin0@yandex.ru:password0`

#### register Users (!!! HTTP Status 500 – Internal Server Error ConstraintViolationImpl{interpolatedMessage='размер должен находиться в диапазоне от 3 до 51', propertyPath=password)
`curl -s -i -X POST -d '{"name":"New User","email":"testuser@mail.ru","password":"testpassword"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingrestaurants/users/register`

#### update User 100002 (!!! HTTP Status 500 – Internal Server Error ConstraintViolationImpl{interpolatedMessage='размер должен находиться в диапазоне от 3 до 51', propertyPath=password)
`curl -s -X PUT -d '{"name":"Update User","email":"updateuser@mail.ru","password":"updatepassword","roles":["USER"]}' -H 'Content-Type: application/json' http://localhost:8080/votingrestaurants/users --user user0@yandex.ru:password0`

####                VoteRestController

#### get All Vote
`curl -s http://localhost:8080/votingrestaurants/restaurants/votes`

#### get All Vote For Restaurants 100007
`curl -s http://localhost:8080/votingrestaurants/restaurants/votes/100007`

#### create Vote User 100002 Restaurant 100007 (the date in the body should be today)
`curl -s -X POST -d '{"localDate":"2021-01-09","localTime":"10:30:00","userId":100002}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingrestaurants/restaurants/votes/100007/create --user user0@yandex.ru:password0`
