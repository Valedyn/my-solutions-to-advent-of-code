package Day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day2 {
    private String input_path;

    public static void run(String input_path){
        Day2 day2 = new Day2(input_path);
        ArrayList<Game> games = new ArrayList<>();
        for(String string : day2.parseInput()){
            int game_id = Integer.parseInt(string.substring("Game ".length(), string.indexOf(":")));
            Game game = new Game(game_id);

            String[] inputSubsets = day2.inputSubsets(string);

            for(String subset : inputSubsets){
                game.addCubeSubset(day2.subsetCube(subset));
            }
            games.add(game);

        }

        int ids = 0;
        int power = 0;
        for(Game game: games){
            if (game.lessThanEquals(12, 14, 13)){
                ids += game.getId();
            }
            power += game.power();
        }
        System.out.println("ids: " + ids);
        System.out.println("power: " + power);
    }

    public Day2(String input_path){
        this.input_path = input_path;
    }

    public String[] parseInput(){
        File input_file = new File(this.input_path);
        String[] lines = {""};
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(input_file))){
            List<String> collection = bufferedReader.lines().toList();
            lines = new String[collection.size()];
            for(int idx = 0; idx < collection.size(); idx++){
                lines[idx] = collection.get(idx);
            }

        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return lines;
    }
    // get all subsets of a line
    public String[] inputSubsets(String line){
        String to_parse = line.substring(line.indexOf(": ") + 2);
        return to_parse.split("; ");
    }

    // get all cubes of a subset
    public Cube[] subsetCube(String subset){
        String[] subset_cubes = subset.split(", ");
        Cube[] cubes = new Cube[subset_cubes.length];
        int counter = 0;
        for(String cube : subset_cubes){
            String enum_type = cube.substring(cube.indexOf(" ") +1).toUpperCase();
            int amount = Integer.parseInt(cube.substring(0, cube.indexOf(" ")));

            Cube cube_object = new Cube(amount, CubeTypes.valueOf(enum_type));
            cubes[counter] = cube_object;
            counter++;
        }
        return cubes;
    }


}
