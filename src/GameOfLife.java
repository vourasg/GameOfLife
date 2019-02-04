import java.util.Scanner;

public class GameOfLife {

    private static Grid grid;
    private static Iteration iteration;
    public static void main(String[] args){

        //Initialize objects
        grid = new Grid("5x5",1253,0.7);
        grid.initializeAsArray();
        iteration = new Iteration(grid);

        printGrid(grid);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Continue to next iteration? (y,n)");
        while(scanner.nextLine().equalsIgnoreCase("y")){
            iteration.nextIteration();
            printGrid(grid);
            System.out.println("Continue to next iteration? (y,n)");
        }


    }


    private static void printGrid(Grid grid){
        for(int i=0; i<grid.getDimX(); i++){

            for(int j=0; j<grid.getDimY(); j++){
                System.out.print("|");
                if(grid.isAlive(i,j))
                    System.out.print("*");
                else
                    System.out.print(" ");

            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("\n\n");
    }
}
