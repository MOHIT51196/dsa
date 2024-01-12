package sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Initializer {

    public static void init(Sort s){

        List<Integer> input = generate(1_00_000); // random large dataset
//        List<Integer> input = new ArrayList<>(List.of(1, -1, 100, 0, 78, -2, 79, 5)); // unsorted small dataset
//        List<Integer> input = new ArrayList<>(List.of(-2, -1, 0, 1, 5, 78, 79, 100)); // sorted small dataset
//        System.out.println("INPUT = " + input);
        System.out.println("Sorting list of " + input.size() + " elements ....");
        long runTime = execute(s, input);
//        System.out.println("OUTPUT = " + input);
        System.out.println("Execution time =  " + runTime/Math.pow(10, 6) + " ms");

    }

    private static long execute(Sort s, List<Integer> input){
        long startTime = System.nanoTime();
        s.sort(input);
        return System.nanoTime() - startTime;
    }

    private static List<Integer> generate(int size){
        List<Integer> res = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            res.add(r.nextInt(-1000, 1000));
        }
        return res;
    }

}
