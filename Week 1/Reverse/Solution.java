import java.io.*;
import java.util.*;

public class Solution {
	public static int numChars(String s,int end){
		if(s.length()==0) return 1;
		String c = s.charAt(end)+"";
		byte[] b = c.getBytes();
		if(b.length==3&&(b[1]>>8&1)==1)return 2;
		if(b.length==1&&(b[0]>>8&1)==0)return 1;
		return 0;
	}
    public static void main(String[] args) {
        try{
        	BufferedReader br = new BufferedReader(new FileReader("tests.txt"));
        	int t = Integer.parseInt(br.readLine());
	        while(t-->0){
	        	String s = br.readLine();
	        	int end = Integer.parseInt(br.readLine()); 
	        	System.out.println(numChars(s,end));   	
	        }
        }catch(Exception e){
        	System.out.println(e);
        } 
        
    }
}