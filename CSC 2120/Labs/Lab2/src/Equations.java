import matrix.*;
/**
 *Main Driver to calculate a system of equations.
 *@author Daniel Brown
 */
public class Equations{
   /**
    *Main Method that creates the matrix variables and solves
    *   the system of equations.
    */
    public static void main(String[] args){
        //Create Matrices
        MatrixOperationsInterface A = MatrixCreator.create(3,3);
        MatrixOperationsInterface b = MatrixCreator.create(3,1);
        
        //Fill matrices with values
        A.setElement(1,1,1.6);//First Column
        A.setElement(2,1,17.6);
        A.setElement(3,1,-2);
        A.setElement(1,2,2.4); //Second Column
        A.setElement(2,2,-5.6);
        A.setElement(3,2,2);
        A.setElement(1,3,-3.7);//Third Column
        A.setElement(2,3,0.05);
        A.setElement(3,3,25.3);

        b.setElement(1,1,-22.10);
        b.setElement(2,1,-4.53);
        b.setElement(3,1,233.70);

        //Setup matrix equation Ax=b
        MatrixOperationsInterface inverserMatrixofA = A.inverse();
        MatrixOperationsInterface result = inverserMatrixofA.multiply(b);
        
        //Output to user
        System.out.println("Output for the given system of equations in lab");
        for (int i =1; i<=result.getNumRows(); i++){
            System.out.println(result.getElement(i,1));
        }
    }
}
