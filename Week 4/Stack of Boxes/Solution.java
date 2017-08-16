import java.io.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
//custom object class to sort without losing the height
class Box implements Comparable{
   int area;
   int height;
   Box(int area,int height){
    this.area = area;
    this.height = height;
  }
  //Method to sort the boxes according to their area
  @Override
  public int compareTo(Object o) {
    return Integer.compare(area,((Box)o).area);
  }
  //Method to know if two boxes have the same area
  @Override
  public boolean equals(Object o){
    return ((Box)o).area == area;
  }
}
public class Solution {
    public int stackOfBoxes(int[][] dim) {
      //checks for null object
      if(dim==null) throw new IllegalArgumentException();
      int ln = dim.length;
      Box[] boxes = new Box[ln*3];
      int j = 0;
      int max_height = 0;
      for(int i = 0; i<ln;i++){
        /*if all the parameters (dimensions) of the box are ok, it 
        * calculates all the possible areas
        */
        if(dim[i][0]>0&&dim[i][0]>0&&dim[i][0]>0){
          int area = dim[i][0]*dim[i][1];
          int height = dim[i][2];
          boxes[j] = new Box(area,height);
          j++;
          area = dim[i][1]*dim[i][2];
          height = dim[i][0];
          boxes[j] = new Box(area,height);
          j++;
          area = dim[i][0]*dim[i][2];
          height = dim[i][1];
          boxes[j] = new Box(area,height);
          j++;
        }else{
          throw new IllegalArgumentException();
        }
      }
      //sorting for comparison
      Arrays.sort(boxes);
      for(int i = 0; i<boxes.length;i++){
        int max = boxes[i].height;
        //checks if two or more boxes have the same area and chooses the highest
        while(i<boxes.length-1&&boxes[i].equals(boxes[i+1])){
          max = Math.max(max,boxes[i+1].height);
          i++;
        }
        max_height = max_height+boxes[i].height;
      }
      return max_height;
    }

    @Test
   public void testStackOfBoxes() {
   		//Null Array
   		boolean error = false;
   		try{
			stackOfBoxes(null);
   		}catch(IllegalArgumentException e){
   			error = true;
   		}
   		assertEquals(true,error);
   		//Array with dimesion <=0
   		error = true;
   		try{
			stackOfBoxes(new int[][]{{0,0,0},{1,2,3},{1,1,1}});
   		}catch(IllegalArgumentException e){
   			error = true;
   		}
   		assertEquals(true,error);
      //Array with simple solution
      assertEquals(45,stackOfBoxes(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
      //Array where there are two boxes with the same area
      assertEquals(10,stackOfBoxes(new int[][]{{1,2,3},{1,2,4}}));
      //Array where there are three boxes with the same area
      assertEquals(14,stackOfBoxes(new int[][]{{1,2,3},{1,2,4},{1,2,5}}));
   }
}