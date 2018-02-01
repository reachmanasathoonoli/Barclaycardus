/**
 * 
 */
package com.theater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Manasa
 *
 */
public class Theater{

	private TheaterLayout theaterLayout;
	private Map<String,Customer> customers;
	private Map<String, Integer> temp;
	
	public Theater(){
		this.theaterLayout = new TheaterLayout();
		this.customers = new LinkedHashMap<String,Customer>();
		this.temp = new HashMap<String, Integer>();
	}
	
	public void createTheaterLayout(String line){
		this.theaterLayout.addSeatsInARow(line);
	}
	
	public void printTheater(){
		this.theaterLayout.printSeating();
	}
	
	public void addCustomer(String line){
		String[] parts = line.split(" ");
		this.customers.put(parts[0], new Customer(parts[0] , Integer.parseInt(parts[1])));
		this.temp.put(parts[0], Integer.parseInt(parts[1]));
	}
	
	public void printCustomers(){
		for(String customer : this.customers.keySet()){
			System.out.print("Customer : " + customer + ", # seats : " + this.customers.get(customer).getSeats());
			System.out.println(", Result : " + this.customers.get(customer).getResult());
		}
	}
	
	
	public void decideSeating(){
		// sort customers according to # of seat
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(this.temp.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );
		
		// deciding seating arrangements
		int row;
		boolean found;
		
		for(Map.Entry<String, Integer> customer : list){
			if(customer.getValue() <= this.theaterLayout.getTotalSeats()){
				row = 0;
				for(List<Integer> theaterrow: this.theaterLayout.getSeating()){
					row++;
					found = false;
					for(int i = 0; i < theaterrow.size(); i++){
						if(customer.getValue() <= theaterrow.get(i)){
							this.theaterLayout.decrementTotalSeats(customer.getValue());
							theaterrow.set(i, -1);
							this.customers.get(customer.getKey()).setResult("Row " + row + " Section " + (i + 1));
							found = true;
							break;
						}
					}
					
					if(found){
						break;
					}else{
						this.customers.get(customer.getKey()).setResult("Call to split party");
					}
				}
			}else{
				this.customers.get(customer.getKey()).setResult("Sorry, we can't handle your party.");
			}
		}
	}
}
