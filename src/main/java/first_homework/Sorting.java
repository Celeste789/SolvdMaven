package first_homework;

public class Sorting {
    public static int findMinFrom(int[] array, int position){
        int min = array[position];
        for (int i = position; i < array.length; i++){
            if (min > array[i]){
                min = array[i];
            }
        }
        return min;
    }

    public static int getPosition(int[] array, int element){
        int res = 0;
        for (int i = 0; i < array.length; i++){
            if (array[i] == element){
                res = i;
            }
        }
        return res;
    }

    public static int[] selectionSortFrom(int[] array, int position){
        int min = findMinFrom(array, position);
        int positionMin = getPosition(array, min);
        int aux = array[position];
        array[position] = min;
        array[positionMin] = aux;
        return array;
    }

    public static int[] selectionSort(int[] array){
        int position = 0;
        while (position != array.length - 1){
            array = selectionSortFrom(array, position);
            position++;
        }
        return array;
    }

    static int[] array = selectionSort(new int[]{2,4,1,3});

    public static void main(String[] args) {
        for (int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }
}
