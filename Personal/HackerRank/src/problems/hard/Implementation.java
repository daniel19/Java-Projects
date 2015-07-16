package problems.hard;

/**
 *Static class to run HackerRank Challenges from the Algorithm Domain.
 */
public class Implementation{
    public static int ROTATE_LEFT = 0;
    public static int ROTATE_RIGHT = 1;

    private Implementation(){
        throw new AssertionError();
    }

    /**
     *Call the rotate method based on the number of rotations provided by the user. 
     *@param matrix
     *@param numberOfRotations
     *@param direction
     */
    public static void rotation(int[][] matrix, int numberOfRotations, int direction){
        //using inplace algorithm to rotate a matrix    
        System.out.println("Original Matrix");
        print2DMatrix(matrix);
        System.out.println();
        System.out.println("------------------");
        System.out.println();
        
        for(int i =0; i < numberOfRotations; i++){
            rotate(matrix, direction);
        }
    }

    private static void rotate(int[][] matrix, int direction){
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[][] result = new int[rows][columns];
        for(int i=0; i < rows; i++){
            for(int j=0; j < columns; j++){
                if(direction == 0){
                    result[i][j] = matrix[j][columns- i -1];
                    //moveLeft(rows, columns, i, j);
                }else{
                    result[i][j] = matrix[rows - j -1][i];
                    //moveRight(rows,columns, i, j);
                }
            }
        }
        print2DMatrix(result);
    }

    private static void moveLeft(int rows, int columns, int x, int y){
    }
   
   
    private static void moveRight(int rows, int columns, int x, int y){}
    
    private static void print2DMatrix(int[][] matrix){
        int row = matrix.length;
        int columns = matrix[0].length;
        for(int i =0; i < row; i++){
            for(int j =0; j < columns; j++){
                System.out.print(matrix[i][j] + " " );
            }
            System.out.println();
        }
    }

}
