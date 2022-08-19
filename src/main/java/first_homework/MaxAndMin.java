package first_homework;

public class MaxAndMin {
    public static void main(String[] args){
        int[] array = {3, 7, 6, 13, 33, 9, -100, 25};
        int i = 0;
        int min = array[0];
        int max = array[0];
        while (i < array.length){
            System.out.println(array[i]);
            if (min > array[i]){
                min = array[i];
            }
            if (max < array[i]){
                max = array[i];
            }
            i++;
        }
        System.out.println("Maximum is " + max);
        System.out.println("Minumum is " +  min);
    }
}
