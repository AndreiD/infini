import org.javatuples.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class MyClassB {

    static List<List<Integer>> findSequences(List<Integer> input) {
        List<List<Integer>> output = new ArrayList<>();

        HashMap<Pair<Integer, Integer>, Integer> mHashMap = new HashMap<>();

        Integer minInput = Collections.min(input);
        Integer maxInput = Collections.max(input);

        // check every N such that minValue*N*N <= maxValue => N <= sqrt(maxValue/minValue)
        int nMax = (int) Math.sqrt((double) maxInput / minInput);

        for (int i = 2; i < input.size(); i++) {

            for (int n = 2; n < minThreeNumbers((double) i, (double) (input.size() - 1) / 2, nMax) + 1; n++) {
                if (input.get(i - n) * n == input.get(i)) {
                    Pair<Integer, Integer> t = Pair.with(i - n, n);

                    int le = 1;
                    if (mHashMap.containsKey(t)) {
                        le = mHashMap.get(t);
                    }
                    mHashMap.put(Pair.with(i, n), le + 1);
                }
            }
        }

        HashMap<Integer, Pair<Integer, Integer>> res = new HashMap<>();
        mHashMap.forEach((key, value) -> {
            Integer n = key.getValue1();
            Integer le = mHashMap.get(key);
            Integer ending = key.getValue0();

            Pair<Integer, Integer> existsPair = res.get(n);
            if (existsPair != null) {
                if (res.get(n).getValue0() < le) {
                    res.put(n, Pair.with(le, ending));
                }
            } else {
                res.put(n, Pair.with(le, ending));
            }
        });

        res.forEach((key, value) ->
                {
                    List<Integer> innerList = new ArrayList<>();
                    Integer seqLength = value.getValue0();
                    Integer endIndex = value.getValue1();
                    while (seqLength > 0) {
                        seqLength--;
                        innerList.add(input.get(endIndex));
                        endIndex = endIndex - key;
                    }
                    if (!innerList.contains(0)) {
                        Collections.sort(innerList);
                        int sum = innerList.stream().mapToInt(Integer::intValue).sum();
                        System.out.println("for N = " + key + " >>---> " + innerList + " with the sum = " + sum);
                        output.add(innerList);
                    }
                }
        );

        return output;
    }

    // minimum of 3 numbers.
    private static double minThreeNumbers(double a, double b, double c) {
        return Math.min(Math.min(a, b), c);
    }

    public static void main(String[] args) {

        System.out.println("----------------------------------------");
        System.out.println("1st input data");
        List<Integer> inputList = Arrays.asList(2, 10, 4, 3, 8, 6, 9, 9, 18, 27);
        List<List<Integer>> sequence = findSequences(inputList);

        int sumOfAllSequences = sequence.stream().flatMap(List::stream).collect(Collectors.toList()).stream().mapToInt(Integer::intValue).sum();
        System.out.println("sum of all sequences = " + sumOfAllSequences);

        System.out.println("----------------------------------------");


        System.out.println("2nd input data");

        List<Integer> inputList2 =
                Arrays.asList(2, 10, 4, 3, 8, 6, 9, 9, 18, 27, 1, 52, 81, 10, 1, 0, 2, 0, 4, 0, 8, 0, 16, 0, 32, 0, 64, 0, 128, 2, 10, 4, 3, 8, 6, 9, 9, 1, 8,
                        27, 1, 52, 81, 10, 100, 50, 0, 0, 0, 0, 0, 0);
        sequence = findSequences(inputList2);
        sumOfAllSequences = sequence.stream().flatMap(List::stream).collect(Collectors.toList()).stream().mapToInt(Integer::intValue).sum();
        System.out.println("sum of all sequences = " + sumOfAllSequences);

        System.out.println("----------------------------------------");


    }
}

