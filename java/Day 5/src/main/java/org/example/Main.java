package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //Range range = new Range(50, 98, 2);

        String input = readInput("input.txt");
        ArrayList<Long> seeds = new ArrayList<>();
        for(String number : input.substring(input.indexOf(": ") +2, input.indexOf("\n")).split(" ")){
            seeds.add(Long.parseLong(number));
        }

        ArrayList<Range> seedToSoil = getMapArrayListFromString(input, "seed-to-soil map:");
        ArrayList<Range> soilToFertilizer = getMapArrayListFromString(input, "soil-to-fertilizer map:");
        ArrayList<Range> fertilizerToWater = getMapArrayListFromString(input, "fertilizer-to-water map:");
        ArrayList<Range> waterToLight = getMapArrayListFromString(input, "water-to-light map:");
        ArrayList<Range> lightToTemperature = getMapArrayListFromString(input, "light-to-temperature map:");
        ArrayList<Range> temperatureToHumidity = getMapArrayListFromString(input, "temperature-to-humidity map:");
        ArrayList<Range> humidityToLocation = getMapArrayListFromString(input, "humidity-to-location map:");

        ArrayList<ArrayList<Range>> maps = new ArrayList<>();
        maps.add(seedToSoil);
        maps.add(soilToFertilizer);
        maps.add(fertilizerToWater);
        maps.add(waterToLight);
        maps.add(lightToTemperature);
        maps.add(temperatureToHumidity);
        maps.add(humidityToLocation);

        ArrayList<Long> locations = new ArrayList<>();
        for(Long seed : seeds){
           // System.out.println("new seed " + seed + ": \n");
            long variable = seed;
            for(ArrayList<Range> map : maps){
                for(Range range : map){
                    Long result = range.result(variable);
                    if(result != null){
                        variable = result;
                        //System.out.println(result);
                        break;
                    }

                }
            }
            locations.add(variable);
            //System.out.println("\n");
        }
        long lowest_location = Long.MAX_VALUE;
        for(long location : locations){
            if(location < lowest_location){
                lowest_location = location;
            }

        }
        System.out.println("part 1, lowest location number: " + lowest_location);

        // part 2.. oh god, my unoptimized code + the nature of the exercise is... jeez- this takes ages-
        // code takes actual ages to run; could possibly optimize it by using multiple threads; i don't know of specific algorithms to actually optimize it, though
        // takes 30 minutes or so to run atm :(((
        ArrayList<Long[]> collectionOfPairs = new ArrayList<>();
        Long[] pairOfTwo = new Long[2];
        int pos = 0;
        for(int seedPos = 0; seedPos < seeds.size(); seedPos++){
            if(seedPos % 2 == 0){
                pos = 0;
                if(pairOfTwo[0] != null){
                    collectionOfPairs.add(pairOfTwo);
                }
                pairOfTwo = new Long[2];
            }
            pairOfTwo[pos] = seeds.get(seedPos);
            pos++;
        }



        lowest_location = Long.MAX_VALUE;
        for(int collectionPairPos = 0; collectionPairPos < collectionOfPairs.size(); collectionPairPos++){
            Long[] pair = collectionOfPairs.get(collectionPairPos);
            Long from = pair[0];
            Long to = pair[1];
            if(to == null){
                to = from;
            }
            System.out.format("%d | %d\n", from, to);
            for(long start = from; start < to + from; start++){
                    long variable = start; // code copied from part 1
                    for(ArrayList<Range> map : maps){
                        for(Range range : map){
                            Long result = range.result(variable);
                            if(result != null){
                                System.out.println("result: " + result);
                            }
                            if(result != null){
                                variable = result;
                                if(variable < lowest_location){
                                    lowest_location = variable;
                                    if(lowest_location == 0){
                                        System.exit(0);
                                    }

                                    System.out.println("current lowest_location: " + lowest_location);
                                }
                                break;
                            }

                        }
                    }



            }

        }
        System.out.println("part 2, lowest location number: " + lowest_location);
    }

    public static String readInput(String inputFilePath){
        String line;
        StringBuilder string = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath))){
            while((line = bufferedReader.readLine()) != null){
                string.append(line);
                string.append("\n");
            }
            string.delete(string.lastIndexOf("\n"), string.length()); // shouldn't i have just used string.length() - 2? lol
        }catch(IOException e){
            throw new RuntimeException(e.getMessage());
        }
        return string.toString();
    }

    public static ArrayList<Range> getMapArrayListFromString(String inputText, String fromString){
        ArrayList<Range> ranges = new ArrayList<>();
        String[] linesToLookThrough = inputText.substring(inputText.indexOf(fromString) + fromString.length() +1).split("\n");

        //System.out.println(Arrays.toString(linesToLookThrough));
        int emptyLine = 0;
        for(String line : linesToLookThrough){
            //System.out.println(line);
            if(line.isEmpty()){
                break;
            }
            emptyLine++;
        }

        String[] lines = new String[emptyLine];
        for(int linesPos = 0; linesPos < lines.length; linesPos++){
            lines[linesPos] = linesToLookThrough[linesPos];
        }
        for(String range_string : lines){
            String[] spaceSplit = range_string.split(" ");

            long destinationRange = Long.parseLong(spaceSplit[0]);
            long sourceRange = Long.parseLong(spaceSplit[1]); // OH MY GOD I HAD THE DESTINATION AND SOURCE RANGE SWAPPED; THAT'S WHY THE RESULTS WERE WRONG AAAAAAAAAA
            long rangeLength = Long.parseLong(spaceSplit[2]);

            ranges.add(new Range(sourceRange, destinationRange, rangeLength));

        }
        return ranges;
    }
}