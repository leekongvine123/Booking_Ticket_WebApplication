<h1>Assignment_PRJ301_Group10_Victory</h1>

1. Requirements: Describe details of the system which you will develop. 
The main points are the 

Name of system: Ticket Booking System
What is the purpose of the system:  Booking ticket to travel.
Who uses the system:
+ Customers who want to book tickets online for travel.
+ Admin who can Add/delete/update Route.

List all features your system can do, and arrange the features in a specific classification.



<h2>Features:</h2></br>
<h3>User:</h3>
<h4>- Login: have 2 methods to login:</h4></br>
+ Login by using the username and password: the system will check if the username and password match to the data in database and check the roleID is 2 then it will redirect to user homepage.</br>
+ Login by Google: </br>
<h4>- Sign up: have 2 methods to sign up:</h4></br>
+  The user fill the blank then submit the form, the system will check the information if the information is valid, it will create an account following the information which the user submit.</br>
+ Sign up by google:</br>
<h4>Edit Profile</h4>
- User can edit the information of their profile.</br>
<h4>Change Password</h4> 
- User can change password by enter the old password to confirm and enter the new password to change.</br>
<h4>Forgot Password</h4>
- The user enter mail, the system will check if the mail valid, then send the verify code, the user enter the verify code, if it valid then the user can change to password.

<h4>- Booking ticket:</h4>
+ The user choose the departure place and destination and departure time then submit.</br>
+ The system will find the match journey for user by following information which user submit.</br>
+ If system did not found any matches, it will go back to homepage and alert, if it found a matches journey, it will show all the journey for user.</br>
+ In each journey user can see the information and click choose to choose a seat. the maximum seat which user can choose is 4, each time user choose seat, the screen will show the seat's name and total price.</br>
+ user click next to submit the information of journey and seat, if the seat was taken by the other user first it will come back to journey page and show alert, if the user booked first then it will redirect to order's information and create a order, in order information, if the user do not click pay button in 30 seccond, the order will be deleted and user can come back to choose seat again.</br>
<h4>-History</h4>
- user can see the order's information which the user was booked.</br>
<h4>Popular Routes</h4>
- Show the 2 most routes which have highest quanity of order.
- When user click to popular route, it will fill the departure place and destination for user.
<h3>Admin:</h3>
<h4>- Login: have 2 methods to login in:</h4></br>
+ Login by enter the userName and password: it will check if the userName and Password match to the account in database and check the roleID is 1 then it will redirect to admin homepage.</br>
+ Login by Google: </br>
<h4>Order Management</h4>
- Admin can edit the order: admin will choose another journey and seat following by the guide of user then the system will send the email to user to confirm the change of order.</br>
- Admin can delete the order if the the journey do not set off and the set off date is not matches to current date.</br>
- Admin can search/sort.</br>
<h4>Journey Management</h4>
- Admin can edit, delete journey if the journey was not taken in order.</br>
- Admin can view the list seat of journey.</br>
- Admin can search/sort.</br>
<h4>Location Management</h4>
- Admin can edit, delete location if the location was not taken in order.</br>
- Admin can add a location.
- Admin can search/sort.</br>
<h4>Car Management</h4>
- Admin can edit, delete location if the car was not taken in order.</br>
- Admin can add a car.</br>
- Admin can search/sort.</br>
<h4>Chart</h4>
- Show a pie chart to display total money of each routes</br>
- Show total money.</br>
- Show top 3 routes.</br>
- Show total of order.</br>



WireFrames:

Home Page:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/111102169/d282dec8-7369-45b2-b91f-14943fa4efe2)
![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/111102169/e2d6ef44-9c3f-4383-9348-0a257fc796dc)



Login:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/3b5f676a-80e5-4d59-be74-392ad924526a)

Sign up:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/548d8d8f-aee8-4bc8-9675-3cafdcde4764)


Login as User:
Routes:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/ad080b79-a778-47ff-9b5a-884800fbe7ec)

Choose seat:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/2ad71a1d-d0f9-43e7-bbd9-643cdcdaa3a7)


Payment:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/64efcc80-a13e-425b-80cf-c6316df45b93)

History:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/4c9f2cc0-a203-4721-9854-bc7b1fe32072)


Login as Admin:
Admin’s Home Page:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/d1c3ca6a-10e1-4fb3-bf84-b8802a942526)

Update Routes:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/761ecb08-ede7-4b24-a99c-6600a1705aaa)

Chart:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/13dc892f-0bde-4290-ba31-228bf286fa68)

Journey Management:
![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/84df9cb3-5e03-4261-a7e6-b0c61a82f9cb)

Add Journey:
![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/c246ae3f-3bf6-47bd-80ee-16a20230c75f)


Order Management:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/2430ade2-379d-4de4-b111-b89091e60495)

Route Management:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/8e7a1044-d72e-4f36-a659-42be8a28d6fe)

Add route:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/7a253972-d485-4491-b096-855775faf6a5)

Edit route:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/12f47796-4984-4f5a-9e27-1a33f227387c)

Location Managemnt:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/7885c400-1521-4bf5-bc5b-15b06aecb0ae)

Add Location:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/d6f1dc86-9424-41ba-a91e-d1fefe52ae9e)

Edit Location:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/7ab08351-e583-44af-8bb8-e0503de45072)

Car Management:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/78bd83d0-58f3-4ed3-84a7-7fe727d40b3b)

Add Car :

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/5a3d2fea-62e1-40d7-9a9e-5126792c3fea)

Edit Car:

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/85a5362d-d3ba-4e38-97d7-afdb6d77a729)


<h1>Site Map:</h1>

![88e0393897f046ae1fe1](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/111102169/66c53aac-b437-4f5b-b63f-f665f7d8306d)

<h1>ERD: </h1>

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/111102169/45b191b0-0c96-468d-b5ac-2e2d7619f14b)

<h1> DIAGRAM: </h1>

![image](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/14d08954-8d42-48df-b1db-e6707ec2a550)

<h1> UML: </h1>

![8535e472b8296b773238 (1)](https://github.com/dunghuynh-teaching/prj301-se1726-10/assets/113279864/dd2629a3-0c39-4f4e-9104-b7cbabe322de)

<h1> Conclusion: </h1>

Our asignment is booking ticket. We choose it because we think that is new topic and worth to challenge, We applied new knowledge, skill that we had learned in classroom and also when we worked together. Each member had differents task to do, but we still help each others if they had problem completing their task. Our asignment maybe not perfect but we really putting our effort on it. We learned new knowledge, skill and also consolidated old knowledge, skill. especially, the most hard part is picking seat, it use alot of javascript, if that only one car to pick to seat, it not really hard, but when you choose seat, it show many car to choose seat, and each seat much have unique id and many others thing like that, that make us for 2 weeks to complete it. The most think we like when do the assignment is know many library like lomkbok and chart's library. 




