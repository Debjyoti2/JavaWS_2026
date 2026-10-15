package WAP;

import java.util.HashSet;
import java.util.Set;

public class Longest_Substring {

    static void main() {

        //String str = "abcabcbb";
        //String str = "abba";
        String str= "abcb";

        int maxlenth=0;
        int left=0;
        Set<Character> set = new HashSet<>();

        for(int right=0;right<str.length();right++){
            while(set.contains(str.charAt(right))){
                set.remove(str.charAt(left));
                left++;
            }
            set.add(str.charAt(right));
             maxlenth = Math.max(maxlenth, right-left+1);

        }
        System.out.println("maxlenth :: " + maxlenth);

    }

}
