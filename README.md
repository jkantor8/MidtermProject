# MidtermProject

## Description
Our team's full-stack application SportSwap is a site where users can coordinate the buying, selling, swapping, or donating of sports equipment. This project intends to set up a marketplace where users local to each other could exchange equipment in a multitude of different ways because of how expensive purchasing new and even after-market sports equipment can be. This vision is accomplished through the use of an aggregation of many technologies we have learned throughout the boot camp to this point. A few of these include Spring boot, JPA, MySQL workbench, AWS cloud deployment, GitHub, and bootstrap alongside HTML and CSS.  

### Database Schema

##### User Table
We used the visual tool MySQL workbench to develop the database schema for SportSwap. In total, we completed our schema with sixteen tables with varying levels of relationships to each other. A few of the more important tables include user, item, and all three different types of listings(donation, sale, swap). 

User holds all the information needed for, as the table name suggests, a user of the site. For example, their username and password associated with them which gives the user the ability to log into the website. This will be important for later usage of the actual application. The table also holds the user's active status. This determines whether the user can participate in creating listings, messaging, and posting comments on the site, which can be influenced by the admin user or the user. The user table also holds a many-to-one relationship with the message table, many messages to one user. Messages hold a subject, content, sender id, and receiver id to ensure messages are linked to the correct user id. This provides the functionality of sending messages to other users. Finally, the user table holds a one-to-one relationship with the address table, one address to one user. This will allow users to use their address while creating postings. The address table contains all the information one would normally associate with an address. Such as street number and name, city, state, etc.

##### Item Table

The item table is truly the foundation of the application since without it there would be no equipment to interact with. Items in our schema can naturally have a name, description, image URL to display them, gender, and brand. However, they can also access other information through three many-to-one relationships with the sport, age group, and item condition tables. These three tables allow for many items to be associated with each of the respective table's contents. Item also has a many-to-one relationship with the user table allowing a user to be associated with many items which will be useful later when users want to create listings. Item also features two Many-To-Many relationships with the swap and donation tables allowing for multiple items to be listed with each listing.

#### Listing Tables

The three listing tables in our schema function very much in the same way. The difference being that a sale listing has a price and an item foreign key with a one-to-one relationship since only one item can be used for each sale post, a swap or donation can involve multiple items and a swap and donation do not require a price. Address foreign keys also differentiate the tables. Sale listing does not connect with an address as it is implied that a user will use their address to sell their items. The swap and donation table do have address foreign keys since these listing types can be associated with another address such as a church for a donation or a meet-up spot for a swap. Although, they also can use the creating user's address as there is a user connected to each listing. Finally, all the listing tables connect with the post table through a many-to-one relationship. This allows each post to have many comments connected with it in the database and can be used to display on the site with the user's username and comment since post also connects to the specific user who made the post. 

#### Sport Table

As mentioned above, the sport table is connected through a Many-to-one with the item table, but it also has three Many-To-Many relationships with swap and donation listing as well as user. Although it was not formally used in the project, a swap or donation listing could have multiple different sports associated with it allowing for blanket sport donation drives or swaps. This is covered however by the fact that a description is provided for each listing where this could be explicitly stated. The Many-To-Many relationship with user allows for a user to have favorite sports connected to them in the database. This allows for the home page to display posts that are related to a user's favorite sports. 

### Hibernate

Hibernate is the Object Relational Mapping tool used in this project to pull information from the database schema and create Java entities in Spring Tool Suite. This is demonstrated well in the DonationListing entity. The DonationListing entity is tagged with the annotations entity, so Hibernate knows to look for that name as a table name in the database, and Table so it also knows that it knows the table name in MySQL workbench when it looks to create that entity. Since our table name did not match our class name, hibernate would not be able to find the table without this Table annotation. Next, we have the annotation ID and Generated Value. This tells hibernate that the entity attached to these annotations is the primary key of the table and that the entity will be automatically generated anytime a new instance of the object is created in the application. We also have the annotations CreationTimestamp and UpdateTimestamp to store information in our database about when the instance of the entity is created and if it is ever updated. The annotation Column function is the same as the Table annotation, telling hibernate that this is the name of the column in the database if the field name does not match. This is not needed for fields like active, title, and description because they match the column names in the database. Finally, we have relationship fields. These fields are annotated with any combination of the words one and many and, in the case of donation listing, with the parenthesized words mappedBy. These annotations tell hibernate to look into another entity and make a connection with that entity based on what that field is mapped to. For example, DonationListing maps to post as the one side of a one-to-many relationship. This means that there will be a list of posts (many) associated with one listing. In the post entity, the annotation ManyToOne is used in conjunction with JoinColumn to find the foreign key within the post table so that these two entities can be connected as they are in the database.

### Application Tour

##### Home.jsp

When a user accesses our application they are taken to the homepage where they are displayed three randomized listings from the database, one of each type. This is done through a method in each of the listing types DAO's named getRandom. In this method, a Random object is used in conjunction with an entire list of each respective listing type. This Random object randomizes the Integer pulled when a .size() method is called on the list of listings which then returns that listing. This method is called within the user controller to display a randomized three listings on the home page when a user is not logged in. 

The homepage also displays a navbar that allows a non-logged-in user to view all the listings on the site or create an account. If the non-logged-in user decides to view listings they will be taken to the listings.jsp where three buttons will be displayed so the user can choose which type of listing they would like to view. When a user clicks on one of these buttons, they are displayed all the listings of that type by the listing number, the title of the listing, and a description of the listing.

If a non-logged-in user decides to create an account, they are taken to create_account.jsp where they will fill out all the information necessary to fulfill the user table, the address table and the Many-To-Many connection user has to sport. Once the account is created they are taken to their account page. 

##### Account.jsp

On the account page a user can update their account in any way they see fit, with the same information from the account create page aside from their favorite sports. This can be done by filling in the required to change text fields and hitting the update account button. A user can also "delete" their account from this page. However, the account is not actually deleted it is merely put into an inactive state in the database and the user is returned to the homepage.  

A user can also send messages from their account through the show messages button as well as, look at all of the listings they have posted. 

###### Messages

If the user selects the show messages button they are taken to the messages.jsp where they are displayed any messages they have sent or received with the name of the sender, the message, and a time stamp of when the message was sent. From this page a user can select to create a new message with a link to the new-message.jsp. On this page they can fill out the name of the recipient they would like to send the message to and the message they would like to send them. When a user hits send that message will now display on the recieving users messages.jsp. 

###### User Listings

If, from the account page, a user selects to view a specific version of one of their listings, they will be taken to the respective viewUserListing jsp. Here all the information for their listing will be displayed as well as a button to view the listing and update the listing. If the user selects to view the listing they are taken to a jsp that displays only that listing where they have the ability to view comments on the listing as well as comment on the listing themselves. Commenting or posting as we have called it is only a function of logged in users. 

If the listing is to be edited, the user will be taken to the updateListing.jsp where the information of their original post will be displayed back to them. Here a user can decide to change anything about their post and if they want the post to be active or inactive. If the listing is deactivated it will no longer display for other users on the listing.jsp page. However, the listing will still display for the user who created the listing when they access their listings through their account page. 

### Create Listings

In the nav bar of the home page, a logged-in user can see a drop-down bar named Create Listing. This drop-down will take the user to the create listing page where, depending on which item they selected from the drop-down menu, they will be taken on a path to create the listing they chose. However, these are all mapped to the same method in the listing controller and are differentiated in the routeTest method by the listing_type variable that is passed in when the user selects from the drop-down menu. A similar process occurs on the listing.jsp page to display all the listings of each type when selecting one of the buttons. 

##### Donation Creation

When a user elects to create a donation listing they are routed to the address_check.jsp where they are asked to determine whether they would like to use their address or create a new address for the donation location. If they elect to create an address, they are routed to address_create.jsp where they will fill in the information for the address which will be persisted in the database. After this, they are taken to select whether they would like to request items or donate items themselves. If they are requesting items they will be taken to the submission page where they can fill out a title and description for the items they are looking for. Once this is submitted it will be viewable on the site. If the user is offering items, they are taken to the item selection jsp where they are asked if they would like to use an existing item associated with them or create a new one. Once an item is chosen or created the user will be taken to select the items they want to associate with the post, since multiple items can be associated with one post. Finally, they are taken to the submission screen and the process ends the same as before.

##### Swap Creation 

A swap creation begins with choosing the location of the swap location. Either the user's address or a created one for a meet-up point. After the address is chosen the user is routed to select items for the post. Whether they would like to create one or use existing ones in their inventory. Whichever path they choose they end up at item selection where they select items equivalent to the donation listing and then submit the post to be viewed on the site. 

##### Sale Creation

The path to creating a sale is a little shorter than the other two listings since the user uses their address automatically for the listing. Because of this, the user is automatically routed to the item selection page and then the submission page to post their listing on the site. The only difference being the user will select a price to price their item at.

##### Admin User

The final piece of our application is the admin user. The admin user has "god" privileges and can deactivate any user or listing from the website. This is done through the admin page which only the admin user has access to because of JSP c:if logic. When the admin is alerted to a post that needs to be deleted they can do so from the admin page simply by clicking the deactivate button. This can also be done for any listing on the site. If the user is deactivated they will no longer be able to sign in to the site, therefore, losing all privileges to the site, and if the post is deactivated it will no longer appear on the listing.jsp page. This means that no users will be able to view the post. Only the original creator. 


## Agile Workflow

Agile workflow was a large portion of the building process of this application and can be broken down into a few different aspects. First, the Trello board. Our Trello board is where we devised and recorded all of our user stories that we built the project around. This was crucial to the project because all of our ideas on how we wanted the application to work and flow were all in one spot that we could all see. 

A second aspect of agile workflow was twice daily scrum meetings. Scrum meetings were necessary for planning out our days. In these meetings, we determined what had been accomplished since the last meeting, what we would be working on until the next meeting, what was hindering us from completing our work, and what work had already been completed. These meetings were also great for avoiding merge conflicts in GitHub and just getting a general sense of how the project was progressing. 

The final aspect of the agile workflow was GitHub itself. GitHub and the branching ability it provides allowed all of us to work diligently on our portions of the project and amass all of our code in one place. Although we did not go through the entire development process without a merge conflict, all of the ones that we did have to deal with were minor. 




### Stretch goals
We would have liked to have been able to apply a reply section to the posts under our listings as well as the ability to update and delete those posts. Unfortunately, we did not have enough time to implement these features.



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
* Trello Boards
* Agile workflow

## Lessons Learned
* We learned that each task should be encapsulated to jsp and controller mapping. When tasks are carried out in this manner, it allows for the reuse of jsp pages and not requiring an entirely new jsp for each pathway. This is because paths through multiple pages of data entry can be determined by a context variable saved in the session.
* We also learned that submit buttons are mapped by the attribute name for parameters in controller methods and the value, which is also the label of the button, is passed along as the value of that variable. This is extremely useful for the first point made in this section. 
* Sometimes weird errors in your code can be solved by project or Gradle refreshes. 
* [Hibernate: More than one row with the given identifier was found error] - caused by incorrect mapping. When mapping, if you don't need a bidirectional relationship, don't. Also, watch out for OneToOne where should be ManyToOne/OneToMany. It is not a problem saving to the database, it is a problem creating objects when trying to map the relationships to entities with incorrect relationships.
* The & symbol allows for multiple parameters to be passed through a URL
* One of the hardest lessons we learned during this development process was how important a solid and normalized database is. Using three different listing types instead of one succinct one that had a table related to it caused us to sometimes have to write similar, and in some cases, the same code three times over. 

