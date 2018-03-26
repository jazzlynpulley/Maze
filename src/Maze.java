import java.io.File ;
import java.io.IOException;
import java.util.Scanner;


public class Maze {
	
	static char maze[][] = new char[12][12] ;
	private static Scanner file = null ;
	static Scanner scan = new Scanner(System.in) ;
	
	public static void main(String[] args) throws IOException{
		
		String fileName = args[0] ;
		file = new Scanner(new File(fileName)) ;
		
		//i = rows and j = columns
		
		for (int i=0; i<12; i++){  //initialize maze from file read in
			
			String lineText = file.nextLine() ;
			lineText = lineText.replaceAll(" ", "") ;
			
			for (int j=0; j<12; j++)
			{
				maze[i][j] = lineText.charAt(j);
			}
			
		
		}
		
		for (int j = 0; j < maze[0].length; j++) {
            if (maze[0][j] == '.') {
                solveMaze(0, j, 0, j - 1);
            }
        }

		
        for (int j = 0; j < maze[11].length; j++) {
            if (maze[11][j] == '.') {
                solveMaze(11, j, 11, j + 1);
            }
        }

  
        for (int row = 0; row < maze.length; row++) {
            if (maze[row][0] == '.') {
                solveMaze(row, 0, row + 1, 0);
            }
        }

        for (int row = 0; row < maze.length; row++) {
            if (maze[row][11] == '.') {
                solveMaze(row, 11, row - 1, 11);
            }
        }
		
		
	}

	public static void print(){
		
		for (int i=0; i<12; i++){
			
			for (int j=0; j<12; j++){
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println() ;

} 
	
	public static void solveMaze(int xLoc, int yLoc, int handXLoc, int handYLoc){
		
		if (maze[xLoc][yLoc]== 'F'){
			System.out.println("Congrats.") ;
			maze[xLoc][yLoc] = 'X' ;
			print() ;
			System.exit(0) ;
			}
		else {
			maze[xLoc][yLoc] = 'X' ;
		}
		print() ;
		
		
		System.out.println("Please press 1 to make a move.") ;
		int input = scan.nextInt() ;

         if (input == 1) {

             //handles a right turn 

                 if ((maze[handXLoc][handYLoc] == '.') || (maze[handXLoc][handYLoc] == 'F') || (maze[handXLoc][handYLoc] == 'o')) {

                     if (handXLoc > xLoc) {

                         //south
                         solveMaze(handXLoc, handYLoc, handXLoc, handYLoc - 1);
                     } else if (handXLoc < xLoc) {

                         //north
                         solveMaze(handXLoc, handYLoc, handXLoc, handYLoc + 1);
                     } else if (handYLoc > yLoc) {

                         //east
                         solveMaze(handXLoc, handYLoc, ++handXLoc, handYLoc);
                     } else if (handYLoc < yLoc) {

                         //west
                         solveMaze(handXLoc, handYLoc, handXLoc - 1, handYLoc);
                     }
                 } 
                 else if (handXLoc > xLoc) {

                     if ((maze[xLoc][yLoc + 1] == '.') || (maze[xLoc][yLoc + 1] == 'F' || (maze[xLoc][yLoc + 1] == 'o'))) {

                         //go straight
                         solveMaze(xLoc, ++yLoc, handXLoc, ++handYLoc);
                     } else {
                         if ((maze[xLoc - 1][yLoc] == '.') || (maze[xLoc - 1][yLoc] == 'F') || (maze[xLoc - 1][yLoc] == 'o')) {

                             
                             //turn left
                             solveMaze(xLoc, yLoc, --handXLoc, ++handYLoc);
                         } else {

                             //turn around
                             solveMaze(xLoc, yLoc, --handXLoc, ++handYLoc);

                         }
                     }
                 } //facing west
                 else if (handXLoc < xLoc) {

                     if ((maze[xLoc][yLoc - 1] == '.') || (maze[xLoc][yLoc - 1] == 'F') || (maze[xLoc][yLoc - 1] == 'o')) {

                         //go straight
                         solveMaze(xLoc, --yLoc, handXLoc, --handYLoc);
                     } else {
                         if ((maze[xLoc + 1][yLoc] == '.') || (maze[xLoc + 1][yLoc] == 'F') || (maze[xLoc + 1][yLoc] == 'o')) {

                             //turn left
                             solveMaze(xLoc, yLoc, ++handXLoc, --handYLoc);
                         } else {

                             //turn around
                             solveMaze(xLoc, yLoc, ++handXLoc, --handYLoc);

                         }
                     }
                 } //facing north
                 else if (handYLoc > yLoc) {

                     if ((maze[xLoc - 1][yLoc] == '.') || (maze[xLoc - 1][yLoc] == 'F') || (maze[xLoc - 1][yLoc] == 'o')) {

                         //go straight
                         solveMaze(--xLoc, yLoc, --handXLoc, handYLoc);
                     } else {
                         if ((maze[xLoc][yLoc - 1] == '.') || (maze[xLoc][yLoc - 1] == 'F') || (maze[xLoc][yLoc - 1] == 'o')) {

                             //turn left
                             solveMaze(xLoc, yLoc, --handXLoc, --handYLoc);
                         } else {

                             //turn around
                             solveMaze(xLoc, yLoc, --handXLoc, --handYLoc);
                         }
                     }
                 } //facing south
                 else if (handYLoc < yLoc) {

                     if ((maze[xLoc + 1][yLoc] == '.') || (maze[xLoc + 1][yLoc] == 'F') || (maze[xLoc + 1][yLoc] == 'o')) {

                         //go straight
                         solveMaze(++xLoc, yLoc, ++handXLoc, handYLoc);
                     } else {
                         if ((maze[xLoc][yLoc + 1] == '.') || (maze[xLoc][yLoc + 1] == 'F') || (maze[xLoc][yLoc + 1] == 'o')) {

                             //turn left
                             solveMaze(xLoc, yLoc, ++handXLoc, ++handYLoc);
                         } else {

                             //turn around
                             solveMaze(xLoc, yLoc, ++handXLoc, ++handYLoc);
                         }
                     }
               
             } 
	
	
}
	}
}
	
	



















