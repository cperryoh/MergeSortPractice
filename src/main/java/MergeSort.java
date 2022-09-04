public class MergeSort {
    public static int[] mergeSort(int[] array) {
        return mergeSortHelper(array, 0, array.length);
    }

    public static int[] mergeSortHelper(int[] array, int start, int end) {
        int[] temp = array;
        int split = (end + start) / 2;
        if (end - start == 1 || split == 0)
            return copyArrayToTemp(temp, start, end);
        return combineArray(mergeSortHelper(temp, start, split), mergeSortHelper(temp, split, end));
    }

    static int[] combineArray(int[] arrFirst, int[] arrSecond) {
        int len = arrFirst.length + arrSecond.length;
        int firstIndex = 0, secondIndex = 0;
        int[] out = new int[len];
        int[] arr = null;
        int index = -1;
        int mainIndex = -1;
        for (int i = 0; i < len; i++) {
            if (firstIndex == arrFirst.length && secondIndex < arrSecond.length) {
                arr = arrSecond;
                index = secondIndex;
                mainIndex = i;
                break;
            }
            if (secondIndex == arrSecond.length && firstIndex < arrFirst.length) {
                arr = arrFirst;
                index = firstIndex;
                mainIndex = i;
                break;
            }
            if (arrFirst[firstIndex] < arrSecond[secondIndex]) {
                out[i] = arrFirst[firstIndex];
                firstIndex++;
            } else {
                out[i] = arrSecond[secondIndex];
                secondIndex++;
            }
        }
        if (index >= 0) {
            while (index < arr.length) {
                out[mainIndex] = arr[index];
                index++;
                mainIndex++;
            }
        }

        return out;
    }

    static int[] copyArrayToTemp(int[] array, int start, int end) {
        int[] out = new int[end - start];
        for (int i = start; i < end; i++) {
            out[i - start] = array[i];
        }
        return out;
    }
}
