import java.io.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length() == 0) return 0;
        int len = s.length();
        int max = 1;
        HashMap<Character,Integer> seen_pos = new HashMap<Character,Integer>();
        int i = 0;
        int j = 0;
        while(i<len){
            char cur = s.charAt(i);
            if(seen_pos.containsKey(cur)){
                j = Math.max(j,seen_pos.get(cur)+1);
            }
            max = Math.max(max,i-j+1);
            seen_pos.put(cur,i);
            i++;
        }
        return max;
    }
     @Test
   public void lengthOfLongestSubstring() {
        //null
        assertEquals(0,lengthOfLongestSubstring(null));
        //empty string
        assertEquals(0,lengthOfLongestSubstring(""));
        //Upper and lowercase
        assertEquals(2,lengthOfLongestSubstring("aAa"));
        //String with spaces and character
        assertEquals(4,lengthOfLongestSubstring("a b_"));
        //String where the longest unique substring is the last substring
        assertEquals(2,lengthOfLongestSubstring("au"));
        //String where the longest unique substring is at the beginning
        assertEquals(3,lengthOfLongestSubstring("abcaaa"));
        //String where the longest substring is in the middle
        assertEquals(3,lengthOfLongestSubstring("abbabcaccs"));
   }
}