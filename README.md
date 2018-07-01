# SimpleAfricaQuiz
This is the link to the Simple Africa Quiz 
https://drive.google.com/open?id=1eA47qX_cirvO-6WvZmmfK7czCUu3tE2W
### Features of the App
1. Login, 
2. One minute Count Down Time, 
3. Select and Type answer for Quiz questions,
4. Auto scroll to unanswered question. 
5. Submit Quiz, View Result, View Correction and Try Again.
### Major Project Files
The Project File Contain:
1. One Activity Java file named Main_Activity.java
2. Three XML Layout files <br>
    login.xml, quiz.xml and users_decision.xml
### How The Application work
The login page is display when Application is lunched.<br>
The user is required to login by providing minimum of 5 and maximum of 9 letters.<br>
A successful login takes the user to the quiz layout (page).<br>
The Count Down Time start immediately the quiz is displayed.<br>
While the time is still running, the user cannot submit the quiz without answering all Quiz Question.<br>
Any Atempt to submit, The application Auto Scroll to the Top Most Question that is yet to be answered. <br>
If all questions are answered user can submit successfull before the end of the Time.<br>
The Quiz Automatically submit when the Count Down Time stop and take the user to the decision page (layout).<br>
The decision page contain three choice of actions That is,
1. Try Again, 
2. Show Result, and 
3. Show Correction. <br>
Try Again -> Enable the User to Restart The Quiz <br>
Show Result -> Display the user Score in a Toast <br>
             The score is grade on percentage where by, <br>
             less than 30% is Poor, 30% to 49% is very Good, 50% to 69% is Average and 70% above is Excellent <br>
 Correction  -> Display Correction of the Quiz Questions.
