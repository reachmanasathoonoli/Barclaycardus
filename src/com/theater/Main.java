/**
 * 
 */
package com.theater;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Manasa
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		String line;
		
		Theater theater = new Theater();
		
		// reading theater layout
		while(true){
			line = input.nextLine();
			
			if(line == null){
				continue;
			}else if(line.isEmpty()){
				break;
			}else{
				theater.createTheaterLayout(line);
			}
		}
		
		
		// reading customer data
		while(true){
			line = input.nextLine();
			
			if(line == null){
				continue;
			}else if(line.isEmpty()){
				break;
			}else{
				theater.addCustomer(line);
			}
		}
		
		System.out.println("Theater layout : ");
		theater.printTheater();
		System.out.println("Seating decison : ");
		theater.decideSeating();
		theater.printCustomers();
		
		System.out.println("\nDone!!!");
		input.close();
	}
}