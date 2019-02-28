# ROBOT

*Robot is an app that collects book information for promo books across several online bookstores*

### Tech stack

* web scrapping with jsoup
* PostgreSQL 
* database access through Spring Data using Hibernate as JPA provider
* Lombok library for POJO methods generation

### How to use this APP

1. Clone this repo: git clone https://github.com/michalakadam/Robot.git 
2. Build project: mvn clean install
3. Set up PostgreSQL database: https://www.digitalocean.com/community/tutorials/how-to-install-and-use-postgresql-on-ubuntu-18-04
4. insert database details in file application.properties located in Robot/persistencelayer/main/resources
5. Run DBHandler class in Robot/persistencelayer/main/java/pl/michalak/adam/dbupdate
6. To see the results type in a browser YOUR_IP_ADDRESS:8081/books/all

### Requirements

* Maven 3.6.0 or newer
* Java 1.8 or newer
* PostgreSQL 42.2.5


### TO DO:

* logger using Spring framework aspect
* script running app on a server once a day 
* more bookstores
* higher unit tests coverage with focus on anticorruption layer
* GUI with HTML, CSS, JS and thymeleaf
* Scrap data using several servers with different IPs and delegator to manage them -> solution to problem of pages not loading when multiple threads are walking through taniaksiazka.pl webpage.
