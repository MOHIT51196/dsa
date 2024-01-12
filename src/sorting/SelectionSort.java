package sorting;

import java.util.List;

public class SelectionSort implements Sort{
    @Override
    public void sort(List<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            int min = i;
            for (int j = i+1; j < arr.size(); j++) {
                if(arr.get(j) < arr.get(min)) min = j;
            }
            // swapping
            int temp = arr.get(i);
            arr.set(i, arr.get(min));
            arr.set(min, temp);
//            System.out.println("LEVEL " + i + " = " + arr);
        }
    }

    public static void main(String[] args) {
        Initializer.init(new SelectionSort());
    }
}
