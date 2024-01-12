package sorting;

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements Sort{

    private void merge(List<Integer> arr, int start, int mid, int end){
        int i = start;
        int j = mid + 1;

        List<Integer> temp = new ArrayList<>();
        while (i <= mid && j <= end){
            if(arr.get(i) > arr.get(j)) temp.add(arr.get(j++));
            else temp.add(arr.get(i++));
        }

        while (i <= mid) temp.add(arr.get(i++));
        while (j <= end) temp.add(arr.get(j++));

        for (int value : temp) arr.set(start++, value);
    }

    private void mergeSort(List<Integer> arr, int start, int end){
        if(start >= end) return;
        int mid = (start + end)/2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }
    @Override
    public void sort(List<Integer> arr) {
        mergeSort(arr, 0, arr.size() - 1);
    }

    public static void main(String[] args) { Initializer.init(new MergeSort()); }

}
