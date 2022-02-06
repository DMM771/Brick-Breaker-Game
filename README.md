# Brick-Breaker-Game
This game is written in JAVA.
This code demonstrates the fundementals of OOP and makes heavy use of the Observer design pattern.
Objects in this code include: the bricks, the balls, the levels, and the background.
In addition to the trivial objects stated above, deeper objects exist such as Collidable objects, points, lines and animations exist too.
The physics in this game rely on vectors and and mathmatical calculations.
For example, for each frame in the animation loop, every ball has a current position and a trajectory (posstion in next frame),
and every ball has to calculate wether a collission is set to occur within those two points.
if a collission has been detected, the ball has to bounce in the opposite direction of the object that it hit.
In addition, the object that has been hit must delete itself if it is a brick.
In order for this to occur, a listener design pattern has been implemented, where the ball must notify the bricks of its location at all times,
and the bricks calculate for collisions by prompting the ball, and if detected, send the ball bouncing in the correct direction.
The animation is created by a GUI library, displaying the ball and brick in each frame.

Please view MP4 file above "Arkanoid.mp4" for quck preview of game (after clicking on the MP4 file, please click "view raw", as video is to large to play on github preview),
and of course, feel free to use scource code, and play the game!
