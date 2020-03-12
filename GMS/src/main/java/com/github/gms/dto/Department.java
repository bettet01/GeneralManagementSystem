package com.github.gms.dto;


import java.util.Collection;
import java.util.HashMap;


/**
 * @author Ethan Bettenga Department
 */
public class Department {


	String name;
	HashMap<String, Item> items = new HashMap<>();


   

    public Department() {

    }

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void removeItems(Item item) {
        items.remove(item.getName());
    }

    public Item getItem(String name){
        return items.get(name);
    }
    
	public Collection<Item> getItems() {
		return this.items.values();
	}

	public void addItem(Item item){
		items.put(item.getName(), item);
	}

}
