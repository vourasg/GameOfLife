import java.util.Random;

public class Grid {
    private boolean[][] grid,temp_grid;
    private Random rand;
    private String dimensions;
    private double probability;
    private int dimX,dimY;

    Grid(String dimensions, long seed, double probability){
        this.dimensions=dimensions;
        this.rand = new Random(seed);
        this.probability=probability;

        checkArguments();

    }

    Grid(Object grid){
        if(grid==null)
            throw new NullPointerException("Null object parameter.");
        this.grid = (boolean[][])grid;
        this.rand = new Random();
        probability=rand.nextDouble();
        dimX= ((boolean[][])grid).length;
        int j=0;
        try{
            for( j=0; j<Double.POSITIVE_INFINITY; j++){
                boolean a=this.grid[0][j];
            }
        }catch(IndexOutOfBoundsException ex){
            dimY=j;
            this.temp_grid = new boolean[dimX][dimY];
        }
    }


    private void checkArguments(){
        try {
            dimX=Integer.parseInt((dimensions.split("x"))[0]);
            dimY=Integer.parseInt((dimensions.split("x"))[1]);
        }catch(IndexOutOfBoundsException | NumberFormatException ex){
            throw new NumberFormatException("Error with dimensions (format eg: 22x34).");
        }
        if(dimX<=0)
            throw new NumberFormatException("Error with dimensions (must be >0).");
        if(dimY<=0)
            throw new NumberFormatException("Error with dimensions (must be >0).");
        if(probability>1 || probability<0)
            throw new NumberFormatException("Live cells' probability must in range [0,1]");
    }


    void initializeAsArray(){

        grid = new boolean[dimX][dimY];
        temp_grid = new boolean[dimX][dimY];

        fillArray(dimX,dimY,probability);
    }


    void fillArray(int dimX,int dimY, double probability){
        for(int i=0; i<dimX; i++)
            for(int j=0; j<dimY; j++)
                grid[i][j]=(rand.nextDouble()<=probability);

    }


    void setTemp_grid(int i, int j, boolean set){
        this.temp_grid[i][j]=set;
    }



    int getDimX(){
        return this.dimX;
    }



    int getDimY(){
        return this.dimY;
    }


    boolean isAlive(int i, int j){
        try {
            return this.grid[i][j];
        }catch(IndexOutOfBoundsException ex){return false;}
    }


    void copyToTemp(){
        for(int i=0; i<this.dimX; i++)
            for(int j=0; j<this.dimY; j++)
                this.temp_grid[i][j]=this.grid[i][j];
    }


    void copyToMain(){
        for(int i=0; i<this.dimX; i++)
            for(int j=0; j<this.dimY; j++)
                this.grid[i][j]=this.temp_grid[i][j];
    }


    boolean[][] getGrid(){
        return this.grid;
    }
}