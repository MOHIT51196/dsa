package sorting;

import java.util.List;

public class InsertionSort implements Sort {
    @Override
    public void sort(List<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            int key = arr.get(i);
            int j = i - 1;
            for (; j >= 0 && arr.get(j) > key; j--) {
                arr.set(j + 1, arr.get(j));
            }
            arr.set(j + 1, key);
//            System.out.println("LEVEL " + i + " = " + arr);
        }
    }



    public static void main(String[] args) {
        Initializer.init(new InsertionSort());
    }
}
