QUOTES Voting application

1 - Backend is working
User creation/update/list/delete
Quote creation/update/list/delete
Upvoting/downvoting quotes
Get top 10 quotes, get flop 10 quotes
Get all votes of user

Get all votes data - when, who, what voted for which quote -->for graph

2 - No frontend - yet


To run from console

Once it's built:
mvn clean install


java -jar QuotesApplication-1.0.0.jar --spring.profiles.active=local


BACKEND
It uses simple spring annotation-based configuration and embedded H2 SQL database for simplicity and portability.

QuotesApp inititializes, JpaConfiguration sets up the web application.
Quote, User and VotingRepository are used for JPA-based database connection is being used within services, those are injected at RestApiController.

REST Services:
Backend Endpoints are all within RestApiController for simplicity.

GET users
GET /api/user/

GET one user
    /api/user/<user_id>

Create user (Create)
POST /api/user
{
Create, Get, List, Update and Delete operations for


Quote
-------------------------

    ** Get top 10 Quotes:

GET /api/quote/top

** Get flop 10 Quotes:
GET /api/quote/top/flop


    ** Get all Quotes :

GET /api/quote/

    ** Get one Quote :

GET /api/quote/<id>

    ** Create Quote

name: it's the actual quote
votes: by default it's zero, not mandatory
posted: the name of who posted this quote

POST /api/quote/
{
        "name": "All bubbles burst.",
        "votes": 0,
        "posted": "1"
}

    ** Update Quote

PUT /api/quote/<id>
{
        "name": "All bubbles burst.",
        "votes": 0,
        "posted": "1"
}

    ** Remove Quote:

DELETE /api/quote/<id>

---------------------
---------------------


User
------
Get all, Get one, Create, Update, Delete user


Voting
------

POST /api/upvote/<quoteId>/<userId>
POST /api/downvote/<quoteId>/<userId>


Voting data: who(userId) for what quote(quoteId) voted what(true if up, false if down) and when(timestamp)

Retrieve all voting data
GET /api/voting

Only retrieve voting data of given user<id>

/api/uservotes/<userId>

For example:
[
    {
        "id": 1,
        "user": 1,
        "quote": 1,
        "vote": false,
        "date": 1502553166746
    },
    {
        "id": 2,
        "user": 1,
        "quote": 1,
        "vote": false,
        "date": 1502553167698
    }
]
