# FestivitiesRegisterApp

Request examples

Creation:
url: POST http://localhost:8080/festivities/
json:
{
    "name":"Festivity Name",
    "startDate": "Thu, 25 Oct 2016 12:00:00 UTC",
    "endDate": "Thu, 31 Oct 2016 12:00:00 UTC",
    "place":"Colombia"
}

Updating:
url: POST http://localhost:8080/festivities/{id}
json:
{
    "name":"Some Change",
    "startDate": "Thu, 20 Oct 2016 12:00:00 UTC",
    "endDate": "Thu, 31 Oct 2016 12:00:00 UTC",
    "place":"Colombia"
}

Fetch All Festivities:
Url: GET http://localhost:8080/festivities/

Query Festivity by name, place or range of start date (Can be sent only the start date)
http://localhost:8080/festivities?name=aaa&place=Italy&startDate1=16 Mar 2016 UTC&startDate2=20 Mar 2016 UTC

Running the test in console
> mvn clean verify

Running project from source code
> mvn spring-boot:run
 