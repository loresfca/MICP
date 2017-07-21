import java.io.*;
import java.util.*;

public class Solution {
	public static int atoi(String s){
		int sign = 1;
		int n = s.length();
		if(n==0) throw new NumberFormatException();
		if(s.charAt(0)==45) sign = -1;
		int i = 0; int res = 0;
		if(sign==-1) i = 1;
		for(;i<n;i++){
			int cur = s.charAt(i) - 48;
			if(cur>9||cur<0) throw new NumberFormatException();
			res = res*10+cur;
			if(res%10!=cur) throw new NumberFormatException();
		}
		return res * sign;
	}
	public static String itoa(int i){
		StringBuilder res = new StringBuilder();
		char sign =' ';
		if(i<0){
			i*=-1;
			sign = '-';
		}
		while(i>0){
			res.insert(0,i%10);
			i = i / 10;
		}
		res.insert(0,sign);
		return res.toString();
	}

    public static void main(String[] args) {
        try{
        	BufferedReader br = new BufferedReader(new FileReader("tests.txt"));
        	int t = Integer.parseInt(br.readLine());
	        while(t-->0){
	        	int c= Integer.parseInt(br.readLine());
	        	try{
	        		if(c == 1){
		        		int i = Integer.parseInt(br.readLine());
		        		System.out.println(itoa(i));
		        	}else{
		        		String s = br.readLine();
		        		System.out.println(atoi(s));
		        	}
	        	}catch(Exception e){
	        		System.out.println(e);
	        		continue;
	        	}	        	
	        }
        }catch(Exception e){
        	System.out.println(e);
        } 
        
    }
}