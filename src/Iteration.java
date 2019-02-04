public class Iteration {

    private Grid grid;

    Iteration(Grid grid){
        try{
            this.grid=grid;
            for(int i=0; i<grid.getDimX(); i++){
                for(int j=0; j<grid.getDimY(); j++){
                    boolean a = grid.isAlive(i,j);
                }
            }
        }catch(NullPointerException ex){
            throw new NullPointerException("Grid's cells are not initialized.");
        }
    }



    void nextIteration(){
        grid.copyToTemp();
        int neighbours;
        boolean decision;
        if(!noIteration()){
            for(int i=0; i<grid.getDimX(); i++){
                for(int j=0; j<grid.getDimY(); j++){
                    neighbours=calcNeighbours(i,j);
                    decision=scenarioDecision(i,j,neighbours);
                    grid.setTemp_grid(i,j,decision);
                }
            }
        }
        grid.copyToMain();

    }



    //Scenarios
    boolean noIteration() {
            for (int i = 0; i < grid.getDimX(); i++) {
                for (int j = 0; j < grid.getDimY(); j++) {
                    if (grid.isAlive(i,j))
                        return false;
                }
            }

        return true;
    }




    int calcNeighbours(int i, int j){

        if(i<0 || i>=grid.getDimX())
            throw new IndexOutOfBoundsException("Out of bounds i");
        if(j<0 || j>=grid.getDimY())
            throw new IndexOutOfBoundsException("Out of bounds j");
        //Initialize as -1 if cell is alive, because will be counted as neighbour
        int neighbours;
        neighbours = grid.isAlive(i,j) ? -1 : 0;

        for(int k=i-1; k<=i+1; k++){
            for(int l=j-1; l<=j+1; l++){
                if(grid.isAlive(k,l))
                    neighbours++;
            }
        }
        return neighbours;
    }




    boolean scenarioDecision(int i, int j, int neighbours){
        //Scenario 1-3
        if(grid.isAlive(i,j)){
            return neighbours >= 2 && neighbours <= 3;
        }
        //Scenario 4
        else{
            return neighbours == 3;
        }
    }
}
