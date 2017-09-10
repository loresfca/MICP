import java.io.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class Solution {
	//Class for vending machine
	class Machine {
		//variable to store the available money
		private Money m;
		//variable to store the available products
		private Inventory i;
		//constructor
		Machine(Money m,Inventory i){
			this.m = m;
			this.i = i;
		}
		//Method to check the existence of product
		public String checkProduct(String code){
			//If the product was ever registered checks if there are enough to make a sale
			if(i.getProducts().containsKey(code)){
				if(i.getProducts().get(code).getAmount()>0){
					return i.getProducts().get(code).price+" ";
				}
				return "Sold out";
			}else{
				return "Wrong code";
			}
		}
		//Method to buy a product, returns true if it was able to buy it, otherwise returns false
		public boolean buy(String code,int money){
			//checks if product is available
			String check = checkProduct(code);
			//if available
			if(!(check.equals("Sold out")||check.equals("Wrong code"))){
				//variable to store the change that must be generated
				int aux = money - i.getProducts().get(code).getPrice();
				if(aux<0) return false;
				//if it was able to generate the change
				if(m.generateChange(aux)){
					//dispense the product
					i.sellProduct(code);
					//Stores the money
					m.addDenomination(money,1);
					return true;
				}
				//cancels the sale
				return false;
			}else{
				return false;
			}
		}
	}
	class Money{
		//amount of coins per denomination
		private HashMap<Integer,Integer> current;
		//available denominations
		private ArrayList<Integer> coins;
		//constructors
		Money(){
			current = new HashMap<Integer,Integer>();
		}
		Money(HashMap<Integer,Integer> current,ArrayList<Integer> coins){
			this.current = current;
			this.coins = coins;
		}
		//adds new denomination
		void addDenomination(int value,int amount){
			if(current.containsKey(value)){
				current.put(value,current.get(value)+amount);
			}else{
				current.put(value,amount);
				coins.add(value);
			}
			Collections.sort(coins);
		}
		HashMap<Integer,Integer> getCurrent(){
			return current;
		}
		boolean generateChange(int money){
			//recursive method to check if it is possible to generate the change
			for(int i = coins.size()-1;i>=0; i--){
				int cur_coin = coins.get(i);
				//if the money needed is equals to the current coin it returns true and deletes the coins
				if(cur_coin==money){
					if(current.get(cur_coin)>1){
						current.put(cur_coin,current.get(cur_coin)-1);
					}else{
						current.remove(cur_coin);
						coins.remove(i);
					}
					return true;
				}
				if(cur_coin<money){
					//if this path is valid, it deletes the coins used
					if(generateChange(money-coins.get(i))){
						if(current.get(cur_coin)>1){
							current.put(cur_coin,current.get(cur_coin)-1);
						}else{
							current.remove(cur_coin);
							coins.remove(i);
						}
						return true;
					}
				}
			}
			return false;
		}
	}
	class Inventory{
		private HashMap<String,Product> products;
		Inventory(){
			products =  new HashMap<String,Product>();
		}
		Inventory(HashMap<String,Product> products){
			this.products = products;
		}
		//Stores new product
		void addProduct(String code, int price,int amount){
			if(products.containsKey(code)){
				products.get(code).setAmount(products.get(code).getAmount()+amount);
			}else{
				Product p = new Product(price,amount);
				products.put(code,p);
			}
		}
		//takes the sold item from the inventory
		void sellProduct(String code){
			products.get(code).setAmount(products.get(code).getAmount()-1);
		}
		HashMap<String,Product> getProducts(){
			return products;
		}
	}
	class Product{
		private int price;
		private int amount;
		Product(int price, int amount){
			this.price = price;
			this.amount = amount;
		}
		int getPrice(){
			return price;
		}
		int getAmount(){
			return amount;
		}
		void setAmount(int amount){
			this.amount = amount;
		}
	}
    @Test
   public void testMachine() {
   		//Set values for testing
   		Product p = new Product(10,2);
   		HashMap<String,Product> hm = new HashMap<String,Product>();
   		hm.put("B15",p);
   		Inventory i = new Inventory(hm);
   		i.addProduct("B16",20,0);
   		HashMap<Integer,Integer> hm2 = new HashMap<Integer,Integer>();
   		hm2.put(10,3);
   		ArrayList<Integer> coins = new ArrayList<Integer>();
   		coins.add(10);
        Money m = new Money(hm2,coins);
        Machine vm = new Machine(m,i);
        //CheckExistingItem
        assertEquals("10 ",vm.checkProduct("B15"));
        //CheckSoldOut
        assertEquals("Sold out",vm.checkProduct("B16"));
        //SuccesfullSale
        assertEquals(true,vm.buy("B15",20));
        //FailedSale
        assertEquals(false,vm.buy("B15",15));

   }
}