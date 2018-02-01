/**
 * 
 */
package com.theater;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Manasa
 *
 */
public class TheaterLayout {

	private int totalSeats;
	private List<List<Integer>> seating;
	
	public TheaterLayout(){
		this.totalSeats = 0;
		this.seating = new LinkedList<List<Integer>>(); 
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void decrementTotalSeats(int seats){
		this.totalSeats -= seats;
	}
	
	private void incrementTotalSeats(int seats) {
		this.totalSeats += seats;
	}
	
	public List<List<Integer>> getSeating(){
		return this.seating;
	}
	
	public void addSeatsInARow(String line){
		List<Integer> row = new ArrayList<Integer>();
		
		for(String section : line.split(" ")){
			this.incrementTotalSeats(Integer.parseInt(section));
			row.add(Integer.parseInt(section));
		}
		
		this.seating.add(row);
	}
	
	public void printSeating(){
		for(List<Integer> row : this.seating){
			for(int section : row){
				System.out.print(section + " ");
			}
			System.out.println();
		}
	}
}