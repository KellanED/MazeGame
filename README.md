# MazeGame
Final Project for APCSA 2018-2019

This game features a random maze generator and a "race" with a bot.


You can select one of 3 sizes for the maze:
  1. Small
  2. Medium
  3. Large
  
  
You can select one of 4 bots to race against:
  1. Left Wall Follower - follows the left wall
  2. Right Wall Follower - self explanatory
  3. Backtracer - utilizes backtracing: making random decisions at junctions then tracing its steps back if it reaches a dead end
  4. "Impossible" - already knows the correct path out and is very fast
The maze is generated with a depth-first algorithm that utilizes backtracing implemented with an explicit stack.
