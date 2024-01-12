package sorting;

import java.util.List;

public class BubbleSort implements Sort {

    @Override
    public void sort(List<Integer> arr) {

        for (int i = 0; i < arr.size(); i++) {
            boolean isSorted = true;  // optimisation 2 : checking best case of already sorted array
            for (int j = 0; j < arr.size() - i - 1; j++) { // optimisation 1 : size - i
                if(arr.get(j) > arr.get(j+1)){
                    // swapping
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j+1));
                    arr.set(j+1, temp);
                    isSorted = false;
                }
            }
//            System.out.println("LEVEL " + i + " = " + arr);
            if(isSorted) return;
        }

    }


    public static void main(String[] args) { Initializer.init(new BubbleSort()); }
}
