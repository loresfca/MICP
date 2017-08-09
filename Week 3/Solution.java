import java.io.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class Solution {
    public List<List<Integer>> threeSum(int[] n) {
        List<List<Integer>> res = new  ArrayList<List<Integer>>();
        if(n == null || n.length<3) return res;
        int len = n.length;
        HashSet<Integer> checked = new HashSet<Integer>();
        for(int i = 0; i<len; i++){
            int goal = n[i]*-1;
            if(checked.add(goal)){
                //TwoSum
                HashSet<Integer> seen = new HashSet<Integer>();
                for(int j = i+1;j<len;j++){
                    if(seen.contains(goal-n[j])){
                        addTriplet(res,n[i],n[j],(goal-n[j]));
                    }else{
                        seen.add(n[j]);
                    }
                } 
            }
        }
        return res;
    }
    void addTriplet(List<List<Integer>> res, int one, int two, int three){
        ArrayList<Integer> newTriplet = new ArrayList<Integer>();
        newTriplet.add(one);
        newTriplet.add(two);
        newTriplet.add(three);
        Collections.sort(newTriplet);
        if(!res.contains(newTriplet)){
            res.add(newTriplet);
        }       
    }
    @Test
   public void testThreeSum() {
        //Empty array.
        assertEquals("[]",threeSum(new int[]{}).toString()); 
        //null array,
        assertEquals("[]",threeSum(null).toString()); 
        //Array with less than 3 elements
        assertEquals("[]",threeSum(new int[]{1,-1}).toString()); 
        //Array with no answer.
        assertEquals("[]",threeSum(new int[]{1,2,3}).toString()); 
        //Array with unique elements and one answer.
        assertEquals("[[-3, 1, 2]]",threeSum(new int[]{1,2,-3,4}).toString()); 
        //Array with unique elements and multiple answers.
        assertEquals("[[-4, 1, 3], [-5, 2, 3]]",threeSum(new int[]{1,2,3,-4,-5}).toString()); 
        //Array with duplicates where the answer involves only one iteration of a value. (check for duplicate triplets).
        assertEquals("[[-3, 1, 2]]",threeSum(new int[]{1,2,1,-3}).toString()); 
        //Array with duplicates where the answer involves two iterations of a value. (check for duplicate triplets)
        assertEquals("[[-2, 1, 1]]",threeSum(new int[]{1,2,1,-2}).toString()); 
        //Array with duplicates and multiple answers
        assertEquals("[[-2, 1, 1], [-3, 1, 2]]",threeSum(new int[]{1,2,1,-2,-3}).toString()); 
        //Array where there would be an answer if the element was duplicated but it is not
        assertEquals("[]",threeSum(new int[]{1,2,-2}).toString()); 
        //Array where the answer are all 0
        assertEquals("[[0, 0, 0]]",threeSum(new int[]{0,0,0}).toString()); 


   }
}