package sorting;

import java.util.List;

public class  QuickSort implements Sort{

    private int partition(List<Integer> arr, int start, int end){
        int head = start;
        int pivot = arr.get(end);

        for (int i = start; i <= end; i++) {
            if(arr.get(i) < pivot) {
                //swapping
                int temp = arr.get(i);
                arr.set(i, arr.get(head));
                arr.set(head, temp);

                head++;
            }
        }

        // swapping the pivot
        int temp = arr.get(head);
        arr.set(head, pivot);
        arr.set(end, temp);

        return head ;
    }

    private void quickSort(List<Integer> arr, int start, int end){
        if(start >= end) return;
        int partition = partition(arr, start, end);
        quickSort(arr, start, partition - 1);
        quickSort(arr, partition + 1, end);
    }
    @Override
    public void sort(List<Integer> arr) {
        quickSort(arr, 0, arr.size() - 1);
    }

    public static void main(String[] args) {
        Initializer.init(new QuickSort());
    }
}
