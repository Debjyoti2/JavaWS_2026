package WAP;

import java.util.Arrays;

public class Reverse_an_array {


    static void main() {

        String[] array = {"apple", "banana", "orange", "grape"};
        String[] array2 = new String[array.length];

        for(int i=0;i<array.length;i++){
            array2[i]=array[array.length-1-i];
        }
        System.out.println("array2 :: " + Arrays.toString(array2));

    }


}
