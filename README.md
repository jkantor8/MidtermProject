# MidtermProject

## Description
SportSwap is a site where users can coordinate buying selling swapping and donating sports equipment. 

## Technologies Used
* Java
* Spring Tool Suite
* Spring Boot
* JPA
* MySQL
* MySQL WorkBench
* BootStrap
* MAMP
* AWS
* JSP
* HTML
* CSS
* Gradle
* JPQL
* Source Control using Git

## Lessons Learned
* encapsulate each task by jsp and controller mapping. This allows reuse of pages. Path through multiple pages of data entry can be determined by a context variable saved in the session.
* buttons are mapped by name for parameter and the value (which is the label) is passed
* weird errors - GRADLE REFRESH! 
* [Hibernate: More than one row with the given identifier was found error] - caused by incorrect mapping. When mapping, if you don't need a bidirectional relationship, don't. Also watch out for OneToOne where should be ManyToOne/OneToMany. It is not a problem saving to the database, it is a problem creating objects when trying to map the relationships to entities with incorrect relationships.
* & for mulitple parameters in URL
