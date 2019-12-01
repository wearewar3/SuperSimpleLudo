# SuperSimpleLudo

This is a demo for the SSL(SuperSimpleLudo) with no AI players and each player has only one plane.

This is the game start screenshot.

![image](https://github.com/wearewar3/SuperSimpleLudo/raw/master/gameeScreen.jpg)

Run the game: go to puzzle game/src/sample and run the Main.java, it is designed with JavaFX

fxml is the frame design of the game and controller is the main part for all the actions in the game

This is the screenshot during the game.

![image](https://github.com/wearewar3/SuperSimpleLudo/raw/master/gameInPlay.jpg)


Rules for the Game

![image](https://github.com/wearewar3/SuperSimpleLudo/raw/master/gameInfo2.jpg)

![image](https://github.com/wearewar3/SuperSimpleLudo/raw/master/redRoute.jpg)

this picture shows the route for the red plane from the start point to the finish point

Each color has the similar route


1. Each player choose has one color and control the plane in this color
2. The first screenshot is the start frame of the game and the planes are in the airport(not on the route)
3. Each player has to roll the dice to start the plane which is 6 
   (if your plane is in the airport, you have to roll a 6 to put your plane to the start point)
4. Each player who has rolled a 6 can roll again and again until the dice number is not 6
5. The plane stops on the point which has the same color as the plane can fly once to the next same color point 
   (which means you can go 4 steps more for only once)
6. The plane stops on the point which has the same color arrow beside can cross the arrow to the opposite point
   (Note: this is not a fly, its a cross. So you can fly then cross or cross then fly if you stops on the specific point)
7. Each point can only has one plane. If the plane stops on the point which has another plane, the previous plane will be attacked and go  back to the airport(which means the previous plane has to roll another 6 to start the plane)
8. When enters the finish line, this plane can not be attacked any more
9. The plane must stop on the finish point to win the game otherwise the plane has to go backward.
   (Example: 1,2,3,4,5,6 this is the route, 1 is the start point and 6 is the finish point. A plane stops on 4 and roll a 5, it goes like 5, 6, 5, 4, 3 and it will stops on the 3. Then the player rolls a 3, it goes like 4, 5, 6 and it stops on the finish point, it wins the game.)
10. The textarea shows some of the instructions of the process.

P.S. Now the game has no AI player and starts in the order of blue, green, red and yellow. (Only the player rolls a 6 can move the plane to the start)

P.S. the next version will have more planes for each player and AI player will be added into the game.

Happy SSL!!!!!
