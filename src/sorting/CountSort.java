package sorting;

import java.util.*;

public class CountSort implements Sort{

    Map<Integer, Integer> freqs = new TreeMap<>();
    @Override
    public void sort(List<Integer> arr) {
        // find the maximum element & find frequencies
        for (Integer e : arr) freqs.put(e, freqs.getOrDefault(e, 0) + 1);

        // update freq[i] += freq[i-1]
        int sum = 0;
        for(Map.Entry<Integer, Integer> e : freqs.entrySet()) {
            sum += e.getValue();
            freqs.put(e.getKey(), sum);
        }
//        System.out.println("FREQUENCIES + 1 = " + freqs);


        // actual sorting
        List<Integer> temp = new ArrayList<>(Collections.nCopies(arr.size(), 0));
        // reverse loop for keep the sort stable | preserve order
        for (int i = arr.size() -1; i>=0; i--) {
            int key = arr.get(i);
            int freq = freqs.get(key) - 1;
            freqs.put(key, freq);
            temp.set(freq, key);
        }

        Collections.copy(arr, temp);
    }

    public static void main(String[] args) { Initializer.init(new CountSort()); }

}
