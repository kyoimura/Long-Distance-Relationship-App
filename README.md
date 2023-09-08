# Closer

Project Motivation

Long Distance Relationships can be a challenge because of its difficulty to maintain meaningful and connected relationships. The distance between partners often leads to feeling sorrow which emerges when the heartfelt desire to be reunited cannot be fulfilled, feeling a lack of shared activities, and feeling lonely. Inspired by personal experiences and a deep understanding of the struggles faced in long-distance relationships, the motivation behind creating this project is to bridge the gap and provide a comprehensive solution to enhance emotional connections, foster communication, and create memorable shared experiences for couples separated by distance.

Features

-Countdown to Next Meeting:

Acknowledging the emotional significance of reunions in long-distance relationships, this feature enables users to track the days, hours, and minutes until their next encounter. The countdown function transforms the waiting period with a sense of purpose and shared excitement.

-Post Goals and Progress

Personal growth during LDR is important because couples can chanel their energy into meaningful endeavors, so this feature empowers users to outline and document their individual pursuits. This function serves as a digital canvas for setting and tracking personal goals, fostering a sense of accomplishment and facilitating exchange of support and celebration. 

-Post future activities users want to do with one another (Shared Bucket List)

Couples can articulate and share their desires for the future. Each posted activity becomes a reminder of the relationship's strength and resilience, serving as a beacon of hope during times of separation.

Challenges I faced

-Adding partner

It was a challenge to implement users to be able to add their partner to create an exclusive relationship between 2 people because I had no idea on how to design an intuitive user experience for adding their partner, handling friend requests, etc. First of all, because it is a closed social interaction app between couples, I was able to discard the implementation of managing friend lists and also handling friend requests. So, I was able to assume that mutual following is a must because they are a couple. With that in mind, when I thought of how to add their partner easily, what I decided to do was when the user registers, it also generates their own unique code. So, upon opening the webpage, the user can see their code as well as a textbox to input their partner’s code. The user can tell their code to the other person, so the other person can input it on their page. When they press submit, if the code is valid, both of their user information is stored in a new relationship table. If a user is in a relationship, the textbox and their code disappears from the page to emphasize exclusivity. 

​​-Date and Time Calculations

The countdown timer was not able to modify the remaining time without reloading the entire page because I learned that Spring Boot application uses server-side rendering, which means that when the user makes a request, the server processes that request, interacts with the database, generates HTML content, and then sends it back to the client. I fixed this by using JavaScript because it runs on the client side. It can interact directly with the user interface, so there was no need to reload the entire page. 

Features I want to implement in the future

-Being able to input photos when sending their future plans and what they have done. 
-Profile Image Feature / Editing their username to add more playfulness
-Calculate Total Distance between couples to get familiar with Google Maps API
-Create fun and interactive challenges that couples can complete together while apart. This could include trivia games or quizzes.
