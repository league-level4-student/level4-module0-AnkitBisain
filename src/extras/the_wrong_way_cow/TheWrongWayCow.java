//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

import java.util.ArrayList;
import java.util.HashMap;

public class TheWrongWayCow {

    public static int[] findWrongWayCow(final char[][] field) {
        HashMap<int[], String> cowDirections = new HashMap<>();
        HashMap<String, Integer> directions = new HashMap<>();
        directions.put("up", 0);
        directions.put("down", 0);
        directions.put("left", 0);
        directions.put("right", 0);
        ArrayList<int[]> heads = new ArrayList();
        
        for(int i = 0; i<field.length; i++) {
        		for(int j = 0; j< field[i].length; j++) {
        			if(field[i][j] == 'c') {
        				heads.add(new int[] {i,j});
        			}
        		}
        }
        
        for( int[] head : heads) {
        	cowDirections.put(head, direction(head, field));
        	int current = directions.get(direction(head, field));
        	directions.remove(direction(head, field), current);
        	directions.put(direction(head, field), current+1);
        }
        
        String wrongWay = "";
        
        for( String s : directions.keySet()) {
        	if(directions.get(s) == 1) {
        		wrongWay = s;
        	}
        }
        
        int[] theWrongWayCow = {100,100};
        
        for(int[] head : heads) {
        	if(cowDirections.get(head) == wrongWay) {
        		theWrongWayCow = head;	
        	}
        }
        
        int[] theRealWrongWayCow = {theWrongWayCow[1], theWrongWayCow[0]};
        
        return theRealWrongWayCow;
    }
    
    public static String direction(int[] head, char[][] field) {
    	if(getVal(new int[] {head[0]+1, head[1]} , field) == 'o' && getVal(new int[] {head[0]+2, head[1]} , field) == 'w') {
    		return "right";
    	}
    	if(getVal(new int[] {head[0]-1, head[1]} , field) == 'o' && getVal(new int[] {head[0]-2, head[1]} , field) == 'w') {
    		return "left";
    	}
    	if(getVal(new int[] {head[0], head[1]+1} , field) == 'o' && getVal(new int[] {head[0], head[1]+2} , field) == 'w') {
    		return "up";
    	}
    	if(getVal(new int[] {head[0], head[1]-1} , field) == 'o' && getVal(new int[] {head[0], head[1]-2} , field) == 'w') {
    		return "down";
    	}
    	return null;
    }
    
    public static char getVal(int[] spot, char[][] field) {
    	if(0 <= spot[0] && spot[0] < field.length) {
    		if(0 <= spot[1] && spot[1] <field[spot[0]].length) {
    			return field[spot[0]][spot[1]];
    		}
    	}
    	return 'z';
    }
}
