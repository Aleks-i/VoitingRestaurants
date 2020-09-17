####                AdminRestController

#### get All Admins
`curl -s http://localhost:8080/votingrestaurants/rest/admins`

#### get Admins 100000
`curl -s http://localhost:8080/votingrestaurants/rest/admins/100000`


####                DishRestController

#### get All Dishes For Admin 100000
`curl -s http://localhost:8080/votingrestaurants/rest/admins/dishes/100000`


####                UserRestController

#### delete Users 100003
`curl -s -X DELETE http://localhost:8080/votingrestaurants/rest/users`


####                VoteRestController

#### get All Vote
`curl -s http://localhost:8080/votingrestaurants/rest/admins/votes`

#### get All Vote For Admin 100000
`curl -s http://localhost:8080/votingrestaurants/rest/admins/votes/100000`