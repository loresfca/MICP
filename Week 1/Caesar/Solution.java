import java.io.*;
import java.util.*;

public class Solution {
	public static String caesar(String s){
		String[] comp = s.split(":");
		int pos = 0;
		try{
			pos = Integer.parseInt(comp[0]);
		}catch(Exception e){
			return "Error: wrong shift number";	
		}
		
		if(pos<-1000000000||pos>1000000000){
        	return "Error: shift too large/small";		
        }
		if(comp.length==1) return null;
		int len = comp[1].length();
		boolean neg = false;
		if(pos<0) neg = true;
		StringBuilder res = new StringBuilder();
		for(int i = 0; i<len; i++){
			int cur = (int) comp[1].charAt(i);
			int r = pos;
			if(cur>=48&&cur<=57){
				r = r%10;
				if(neg){
					cur+=r;
					if(cur<48) cur = 58-(48-cur);
				}else{
					cur+=r;
					if(cur>57) cur = cur-58+48;
				}
			}
			if(cur>=65&&cur<=90){
				r = r%26;
				if(neg){
					cur+=r;
					if(cur<65) cur = 91-(65-cur);
				}else{
					cur+=r;
					if(cur>90) cur = cur-91+65;
				}
			}
			if(cur>=97&&cur<=122){
				r = r%10;
				if(neg){
					cur+=r;
					if(cur<97) cur = 123-(97-cur);
				}else{
					cur+=r;
					if(cur>122) cur = cur-123+97;
				}
			}
			res.append((char)cur);
		}
		return res.toString();
	}

    public static void main(String[] args) {
        try{
        	BufferedReader br = new BufferedReader(new FileReader("tests.txt"));
        	int t = Integer.parseInt(br.readLine());
	        while(t-->0){
	        	String s = br.readLine();
	        	System.out.println(caesar(s));
	        }
        }catch(Exception e){
        	System.out.println(e);
        } 
        
    }
}