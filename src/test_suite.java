import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class test_suite {

    //================================================================================================================//
    //                                              Grid class tests                                                  //
    //================================================================================================================//
    @Test(expected=NumberFormatException.class)
    public void test_Constructor_dimensions_1(){
        new Grid("-1x2", 1234, 0.5);
    }


    @Test(expected=NumberFormatException.class)
    public void test_Constructor_dimensions_2(){
        new Grid("1x-2", 1234, 0.5);
    }


    @Test(expected=NumberFormatException.class)
    public void test_Constructor_dimensions_3(){
        new Grid("-1sx2", 1234, 0.5);
    }


    @Test(expected=NumberFormatException.class)
    public void test_Constructor_dimensions_4(){
        new Grid("2 x2", 1234, 0.5);
    }


    @Test(expected=NumberFormatException.class)
    public void test_Constructor_dimensions_5(){
        new Grid("2 x2", 1234, 0.5);
    }



    @Test(expected=NumberFormatException.class)
    public void test_Constructor_dimensions_6(){
        new Grid("2x", 1234, 0.5);
    }



    @Test(expected=NumberFormatException.class)
    public void test_Constructor_probability_1(){
        new Grid("2x2", 1234, 1.1);
    }



    @Test(expected=NumberFormatException.class)
    public void test_Constructor_probability_2(){
        new Grid("2x2", 1234, -1);
    }



    @Test(expected=NullPointerException.class)
    public void test_Constructor_null_1(){
        new Grid(null);
    }


    @Test
    public void test_Constructor(){
        new Grid("2x2", 1234, 0.5);
    }


    @Test(expected=NumberFormatException.class)
    public void test_Constructor_1(){
        new Grid("2", 1234, 0.5);
    }



    @Test
    public void test_Constructor_2(){
        boolean[][] grid = new boolean[5][5];

        new Grid(grid);
    }


    @Test
    public void test_initialize_fill_Array(){
        Grid grid = new Grid(new boolean[10][10]);
        for(int i=0; i<10;i++)
            for(int j=0; j<10; j++)
                Assert.assertFalse(grid.getGrid()[i][j]);
        grid.initializeAsArray();
        boolean f=false;
        for(int i=0; i<10;i++)
            for(int j=0; j<10; j++)
                if(grid.isAlive(i,j))
                    f=true;
        Assert.assertTrue(f);
    }



    @Test
    public void test_isAlive(){
        Grid grid = new Grid(new boolean[5][5]);
        Assert.assertFalse(grid.isAlive(10,10));
    }



    @Test
    public void test_getDimX(){
        Grid grid = new Grid(new boolean[5][5]);
        Assert.assertEquals(5,grid.getDimX());
    }


    @Test
    public void test_getDimY(){
        Grid grid = new Grid(new boolean[5][5]);
        Assert.assertEquals(5,grid.getDimY());
    }



    @Test
    public void test_copyToMain_copyToTemp_setTempGrid(){
        Grid grid = new Grid(new boolean[5][5]);
        grid.setTemp_grid(0,0,true);
        grid.setTemp_grid(2,2,true);
        grid.copyToMain();
        grid.copyToTemp();
        Assert.assertTrue(grid.isAlive(0,0));
        Assert.assertTrue(grid.isAlive(2,2));
    }




    //================================================================================================================//
    //                                           Iteration class tests                                                //
    //================================================================================================================//

    @Test(expected=NullPointerException.class)
    public void test_constructor(){
        new Iteration(null);

    }



    @Test(expected=NullPointerException.class)
    public void test_constructor_2(){
        new Iteration(new Grid("2x2",1,0.5));
    }



    @Test
    public void test_constructor_3(){
        Grid grid = new Grid("2x2",1,0.5);
        grid.initializeAsArray();
        new Iteration(grid);
    }





    @Test
    public void test_noIteration(){

        boolean[][] source;
        source = new boolean[10][10];
        for(int i=0; i<10; i++)
            for (int j = 0; j < 10; j++)
                source[i][j] = true;

        Grid grid = new Grid(source);

        Iteration iteration = new Iteration(grid);
        assertFalse(iteration.noIteration());
    }



    @Test
    public void test_noIteration_2(){

        boolean[][] source;
        source = new boolean[10][10];
        for(int i=0; i<10; i++)
            for (int j = 0; j < 10; j++)
                source[i][j] = false;

        Grid grid = new Grid(source);

        Iteration iteration = new Iteration(grid);
        assertTrue(iteration.noIteration());
    }



    @Test
    public void test_noIteration_3(){

        boolean[][] source;
        source = new boolean[10][10];

        Grid grid = new Grid(source);

        Iteration iteration = new Iteration(grid);
        assertTrue(iteration.noIteration());
    }




    @Test(expected=IndexOutOfBoundsException.class)
    public void test_calcNeighbours_2() {

        boolean[][] source;
        source = new boolean[10][10];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                source[i][j] = false;
        Grid grid = new Grid(source);

        Iteration iteration = new Iteration(grid);
        iteration.calcNeighbours(-1,0);
    }



    @Test(expected=IndexOutOfBoundsException.class)
    public void test_calcNeighbours_3() {

        boolean[][] source;
        source = new boolean[10][10];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                source[i][j] = false;
        Grid grid = new Grid(source);

        Iteration iteration = new Iteration(grid);
        iteration.calcNeighbours(0,-1);
    }



    @Test
    public void test_calcNeighbours_4() {

        boolean[][] source;
        source = new boolean[10][10];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                source[i][j] = false;
        Grid grid = new Grid(source);

        Iteration iteration = new Iteration(grid);
        int neighbours=iteration.calcNeighbours(0,0);
        assertEquals(0,neighbours);

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                source[i][j] = true;
        grid = new Grid(source);
        iteration = new Iteration(grid);
        neighbours=iteration.calcNeighbours(0,0);
        assertEquals(3,neighbours);

        neighbours=iteration.calcNeighbours(5,5);
        assertEquals(8,neighbours);

        neighbours=iteration.calcNeighbours(0,5);
        assertEquals(5,neighbours);
    }




    @Test
    public void test_ScenarioDecision_Underpopulation() {

        boolean[][] source;
        boolean result;
        source = new boolean[1][1];
        source[0][0] = true;

        Grid grid = new Grid(source);

        Iteration iteration = new Iteration(grid);
        result=iteration.scenarioDecision(0,0,0);
        assertFalse(result);

        result=iteration.scenarioDecision(0,0,1);
        assertFalse(result);
    }



    @Test
    public void test_ScenarioDecision_Overcrowding() {

        boolean[][] source;
        boolean result;
        source = new boolean[1][1];
        source[0][0] = true;

        Grid grid = new Grid(source);

        Iteration iteration = new Iteration(grid);
        result=iteration.scenarioDecision(0,0,4);
        assertFalse(result);

        result=iteration.scenarioDecision(0,0,5);
        assertFalse(result);
    }




    @Test
    public void test_ScenarioDecision_Survival() {

        boolean[][] source;
        boolean result;
        source = new boolean[1][1];
        source[0][0] = true;

        Grid grid = new Grid(source);

        Iteration iteration = new Iteration(grid);
        result=iteration.scenarioDecision(0,0,2);
        assertTrue(result);

        result=iteration.scenarioDecision(0,0,3);
        assertTrue(result);
    }




    @Test
    public void test_ScenarioDecision_CreationOfLife() {

        boolean[][] source;
        boolean result;
        source = new boolean[1][1];
        source[0][0] = false;

        Grid grid = new Grid(source);

        Iteration iteration = new Iteration(grid);
        result=iteration.scenarioDecision(0,0,3);
        assertTrue(result);

        result=iteration.scenarioDecision(0,0,2);
        assertFalse(result);

        result=iteration.scenarioDecision(0,0,4);
        assertFalse(result);

        result=iteration.scenarioDecision(0,0,0);
        assertFalse(result);
    }




    @Test
    public void test_nextIteration() {

        boolean[][] source;
        boolean result;
        source = new boolean[5][5];
        source[2][2] = true;
        source[2][3] = true;
        source[2][1] = true;

        Grid grid = new Grid(source);

        Iteration iteration = new Iteration(grid);
        iteration.nextIteration();
        assertFalse(grid.isAlive(2,3));
        assertFalse(grid.isAlive(2,1));
        assertTrue(grid.isAlive(2,2));
        assertTrue(grid.isAlive(1,2));
        assertTrue(grid.isAlive(3,2));
    }
}