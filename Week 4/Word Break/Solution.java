import java.io.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class Solution {
    public boolean validDict(String s,String[] dict) {
    	if(s==null||dict==null) throw new IllegalArgumentException();
    	if(s.length()==0||dict.length==0) return false;
    	//To store previously checked suffixes.
    	HashMap<String,Boolean> suffix = new HashMap<String,Boolean>();
    	s = s.toLowerCase();
    	for(int i = 0; i<dict.length;i++) dict[i].toLowerCase();
    	Arrays.sort(dict); //for binary search
    	//Start of recursion.
    	return validDict(s,dict,suffix);
    }
    public boolean validDict(String s,String[] dict,HashMap<String,Boolean> suffix){
    	//for manages the size of the prefix
    	for(int i = 1; i<=s.length();i++){
    		//prefix
    		String curr = s.substring(0,i);
    		//current suffix
    		String curr_pos = s.substring(i);
    		//if the dictionary contains the current string it continues, else it tries the next prefix available
    		if(contains(dict,curr)){
    			//Stores that this string is valid
    			suffix.put(curr,true);
    			//if this is not the last possible string (there is a suffix) it checks if the suffix is also valid
    			if(curr_pos.length()>0){
    				//This if checks if the suffix has already been seen and if it is valid
    				//if the suffix haven't been seen it is analyzed iteratively
    				if(!suffix.containsKey(curr_pos)){
    					suffix.put(curr_pos,validDict(curr_pos,dict,suffix));
    				}
    				if(suffix.get(curr_pos)){
    					return true;
    				}
    			}else{
    				//if the current string doesn't have a suffix and is valid true is returned.
    				return true;
    			}
    		}
    	}
    	return false;
    }
    public boolean contains(String[] dict,String curr){
    	int i = Arrays.binarySearch(dict,curr);
    	if(i>=0) return true;
    	return false;
    }
    @Test
   public void testThreeSum() {
   		//Null String
   		boolean error = false;
   		try{
			validDict(null,new String[]{"a","b"});
   		}catch(IllegalArgumentException e){
   			error = true;
   		}
   		assertEquals(true,error);
   		//Null dictionary
   		error = true;
   		try{
			validDict("a",null);
   		}catch(IllegalArgumentException e){
   			error = true;
   		}
   		assertEquals(true,error);
        //Empty String
        assertEquals(false,validDict("",new String[]{"a","b"}));
        //Empty dictionary 
		assertEquals(false,validDict("a",new String[]{}));
        //Valid String & dictionary (no upper)
        assertEquals(true,validDict("ab",new String[]{"a","b"}));
        //Valid String & dictionary (Upper)
        assertEquals(true,validDict("Abc",new String[]{"a","b","c"})); 
        //No solution
        assertEquals(false,validDict("abb",new String[]{"a"})); 
        //String where there are two valid prefix but one returns false in the end
        assertEquals(true,validDict("aab",new String[]{"a","ab"})); 
        //dictionary string happens twice
        assertEquals(true,validDict("abb",new String[]{"a","b"})); 
   }
}