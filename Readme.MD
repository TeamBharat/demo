Problem 1 solution :-


Application (URL) to use: https://www.makemytrip.com/ 
Automation tool: Any 
Test Steps:
Sr. No.	Step description/instruction
1	Launch MakeMyTrip website
2	Select From & To and search flights
3	Sorting, Filter should be used while performing below Scenario:

1) Find the flight with lowest & highest fare price.
2) Find the First & last flight of the day
3) Find the flights with Non-Stop, 1 Stop or 2 Stop & store them in different lists.
4) Create a HashMap with Key as Airline name and value as Price of all the flights available. 

Eg: 

{"Indigo": ["7308", "1234", "4321"],
"Airasia": ["7308", "1234", "4321"],
"Vistara": ["7308", "1234", "4321"], etc}


tools and technology used 👎

We have used POM based Strcuture
Selenium Version : 3.141.9
Extent Report : 2.41.2 for Screenshots
We tried to follow SRP concept also
