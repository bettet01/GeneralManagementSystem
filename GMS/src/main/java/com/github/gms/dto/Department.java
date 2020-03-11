package com.github.gms.dto;


import java.util.Collection;
import java.util.HashMap;


/**
* @author Ethan Bettenga
* Department
*/

public class Department {
	String name;
	HashMap<String, Item> items = new HashMap<>();

	public Department(){

	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Item> getItems() {
		return this.items.values();
	}

	public void addItem(Item item){
		items.put(item.getName(), item);
	}

	public void removeItem(Item item){
		items.remove(item);
	}
	
}