# Brick-Breaker-Game

This game is written in JAVA.

The code demonstrates the fundementals of OOP and makes heavy use of the Observer design pattern, as well as mechanics physics.
Obvious objects in this code include: the bricks, the balls, the levels.
In addition to the trivial objects stated above, deeper objects exist such as: level objects, background objects, Collidable objects, points, lines and animations as well.

The physics in this game rely on vectors and and mathmatical calculations.
For example, every ball has a current and a trajectory position (position in next frame),
the distance between these two points will depend of the balls velocity.
within each itteration of the animation loop (every frame),
each ball will cal the "moveOneStep()" method, and calculate wether a collission is set to occur within those two points.
This calculation is done by first creating a line object, where the cordinates correspond to current and trajectory points of the ball.
This line is then used to find any collisions (interception points) with any other collidable objects in the environment.

If a collission has been detected, the ball has to bounce occordingly, if a block has been hit, the ball must bounce in the opposite direction,
if the paddle was hit, the balls bounce angle will depend on the collision location on the padde.
Therefore the ball must have sufficiant information regarding the collision.
In addition, the object that has been hit must delete itself if it is a brick.
Thats quite alot to do at once, and can get a little messy, both from a coding perspective, and from a logical perspective.
Therefore, listener design pattern has been implemented.

The implemantation works as follows.
where the ball must notify the bricks of its location at all times,
and the bricks calculate for collisions by prompting the ball, and if detected, send the ball bouncing in the correct direction.
The animation is created by a GUI library, displaying the ball and brick in each frame.

Please view MP4 file above "Arkanoid.mp4" for quck preview of game (after clicking on the MP4 file, please click "view raw", as video is to large to play on github preview),
and of course, feel free to use scource code, and play the game!
