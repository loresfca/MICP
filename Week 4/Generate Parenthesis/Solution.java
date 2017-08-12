import java.io.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class Solution {
    public List<String> generateParenthesis(int n) {
    	if(n<=0) throw new IllegalArgumentException();
      List<String> res = new ArrayList<String>();
      generateParenthesis("",0,0,res,n);
      return res;
    }
    public void generateParenthesis(String acc,int open, int closed,List<String> res, int n){
      if(acc.length()==2*n){
        res.add(acc);
      }
      if(open<n){
        generateParenthesis(acc+"(",open+1,closed,res,n);
      }
      if(closed<open){
        generateParenthesis(acc+")",open,closed+1,res,n);
      }
    }
    @Test
   public void testParenthesis() {
   		//n less than 0
   		boolean error = false;
   		try{
			 generateParenthesis(0);
   		}catch(IllegalArgumentException e){
   			error = true;
   		}
   		assertEquals(true,error);
      //Empty String
      assertEquals("[((())), (()()), (())(), ()(()), ()()()]",generateParenthesis(3).toString());
   }
}