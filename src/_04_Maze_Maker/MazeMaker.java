package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		Random r = new Random();
		Cell c = maze.getCell(r.nextInt(w),r.nextInt(h));
		
		//5. call selectNextPath method with the randomly selected cell
		
		selectNextPath(c);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. check for unvisited neighbors using the cell
		ArrayList<Cell> naybers = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(naybers.isEmpty() == false) {
			//C1. select one at random.
			int index = randGen.nextInt(naybers.size());
			//C2. push it to the stack
			uncheckedCells.push(naybers.get(index));
			//C3. remove the wall between the two cells
			removeWalls(currentCell, naybers.get(index));
			//C4. make the new cell the current cell and mark it as visited
			naybers.get(index).setBeenVisited(true);
			currentCell = naybers.get(index);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}else {
			
		//D. if all neighbors are visited
		
			//D1. if the stack is not empty
			if(uncheckedCells.isEmpty() == false) {
				// D1a. pop a cell from the stack
					currentCell = uncheckedCells.pop();
				// D1b. make that the current cell
					selectNextPath(currentCell);
				// D1c. call the selectNextPath method with the current cell
			}
		}
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
			if(c1.getX() == c2.getX()+1) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
			if(c1.getX() == c2.getX()-1) {
				c2.setWestWall(false);
				c1.setEastWall(false);	
			}
			if(c1.getY() == c2.getY()+1) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}
			if(c1.getY() == c2.getY()-1) {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> neighbors = new ArrayList<>();
		int x = c.getX();
		int y = c.getY();

		if(0 <= x-1 && x-1 < width && 0 <= y && y < height) {
			Cell cc = maze.getCell(x-1,y);
			if(cc.hasBeenVisited() == false) {
				neighbors.add(cc);
			}
		}

		if(0 <= x && x < width && 0 <= y-1 && y-1 < height) {
			Cell cc = maze.getCell(x,y-1);
			if(cc.hasBeenVisited() == false) {
				neighbors.add(cc);
			}
		}

		if(0 <= x && x < width && 0 <= y+1 && y+1 < height) {
			Cell cc = maze.getCell(x,y+1);
			if(cc.hasBeenVisited() == false) {
				neighbors.add(cc);
			}
		}
		if(0 <= x+1 && x+1 < width && 0 <= y && y < height) {
			Cell cc = maze.getCell(x+1,y);
			if(cc.hasBeenVisited() == false) {
				neighbors.add(cc);
			}
		}

			
		return neighbors;
	}
}
