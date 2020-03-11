package com.github.gms.dto;

import java.util.ArrayList;
import java.util.List;

/**
* @author Ethan Bettenga
* Department
*/

public class Department {
	String name;
	List<Item> items = new ArrayList<>();

	public Department(){

	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void addItem(Item item){
		items.add(item);
	}

	public void removeItem(Item item){
		items.remove(item);
	}
	
}