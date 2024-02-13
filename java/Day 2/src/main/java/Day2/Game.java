package Day2;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private final int id;
    private ArrayList<Cube[]> subsets = new ArrayList<>();

    public Game(int id){
        this.id = id;
    }

    public void addCubeSubset(Cube[] cube_subset){
            subsets.add(cube_subset);
    }

    public ArrayList<Cube[]> getSubsets(){
            return subsets;
    }
    public int getId(){
        return id;
    }

    // get the amount of max cubes of a type across all subsets
    public int amountOfType(CubeTypes type){
        int amount = 0;
        for(Cube[] cubes : subsets){
            for(Cube cube : cubes){
                if(cube.getType().equals(type)){
                    if(cube.getAmount() > amount){
                        amount = cube.getAmount();
                        break;
                    }
                }

            }
        }

        return amount;
    }

    // amount of the max cubes of each type multiplied together
    public int power(){
        int red_total = amountOfType(CubeTypes.RED);
        int blue_total = amountOfType(CubeTypes.BLUE);
        int green_total = amountOfType(CubeTypes.GREEN);
        if(red_total == 0){
            red_total = 1;
        }else if(blue_total == 0){
            blue_total = 1;
        }else if(green_total == 0){
            green_total = 1;
        }
        return red_total*blue_total*green_total;
    }

    // checks if the max amount of cubes of each type are smaller than/equal the number of specified cubes in the parameters
    public boolean lessThanEquals(int red, int blue, int green){
        int red_total = amountOfType(CubeTypes.RED);
        int blue_total = amountOfType(CubeTypes.BLUE);
        int green_total = amountOfType(CubeTypes.GREEN);
        return (red_total <= red && blue_total <= blue && green_total <= green);
    }

}
