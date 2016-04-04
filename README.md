# FestivitiesRegisterApp

Request examples

Creation:
POST 
> http://localhost:8080/festivities/


json:
{
    "name":"Festivity Name",
    "startDate": "Thu, 25 Oct 2016 12:00:00 UTC",
    "endDate": "Thu, 31 Oct 2016 12:00:00 UTC",
    "place":"Colombia"
}

Updating:
POST 
> http://localhost:8080/festivities/{id}

json:
{
    "name":"Some Change",
    "startDate": "Thu, 20 Oct 2016 12:00:00 UTC",
    "endDate": "Thu, 31 Oct 2016 12:00:00 UTC",
    "place":"Colombia"
}

Fetch All Festivities:
GET 
> http://localhost:8080/festivities/

Query Festivity by name, place or range of start date (Can be sent only the start date)

GET:
> http://localhost:8080/festivities?name=aaa&place=Italy&startDate1=16 Mar 2016 UTC&startDate2=20 Mar 2016 UTC

Running the test in console
> mvn clean verify

Running project from source code
> mvn spring-boot:run

Running the applications from JAR
> java -jar FestivitiesRegisterApp-0.0.1-SNAPSHOT.jar 
 
