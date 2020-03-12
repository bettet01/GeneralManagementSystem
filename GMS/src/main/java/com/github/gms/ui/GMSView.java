package com.github.gms.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.github.gms.dto.Department;
import com.github.gms.dto.Item;

/**
 * @author Ethan Bettenga GMSView
 */

public class GMSView {
	UserIO io;

	public GMSView(UserIO io){
		this.io = io;
	}


	public int mainMenu(){
		io.print("--- Main Menu ---");
		io.print("1. Add Item");
		io.print("2. Edit Item");
		io.print("3. Search and display Item");
		io.print("4. List all Departments");
		io.print("5. List Items by Department");
		io.print("6. Remove Item By Department");
		io.print("7. Exit");

		 return io.readInt("Choice: ", 0, 8);
	}

	public void displayAddedSuccess(){
		io.print("--- Item Added Sucessfully --- ");
		io.readString("Press enter to continue");
	}

	public void displayAddedFail(){
		io.print(" --- Unable to Add Item ---");
		io.readString("Press enter to continue");
	}


	public Item displayaddItem(){
		io.print("--- Add Item ---");
		String name = io.readString("Name of Item: ");
		LocalDate exp = io.setLocalDate("Experation Date (If Applicable): ");
		int count = io.readInt("Number of Items in inventory: ");
		BigDecimal ppu = io.readBigDecimal("Price per unit: ");
		String department = io.readString("Department Name: ");
		//TODO: add department list

		Item item = new Item(name, exp, count, ppu, department);
		return item;
	}

	public void displayremoveItem(){
		io.print("--- Remove Item ---");
		io.readString("Department Name: ");
		io.readString("Item Name: ");
	}

	public String displayFindItem(){
		io.print("--- Find Item ---");
		String name = io.readString("Enter Name of Item: ");
		return name;
	}

	public void displayItem(Item item){
		io.print("--- Item ---");
		io.print("Department: " + item.getDepartment());
		io.print("\tName: " + item.getName());
		io.print("\tPrice: " + item.getPpu());
		io.print("\tInv Count: " + item.getItemCount());
		io.print("\tExp Date: " + item.getExpDate());
		io.print("---   ---");
	}


	public void displayDepartmentItems(Collection<Item> items){
		io.print("--- Department Items ---");
		for(Item item: items){
			displayItem(item);
		}
	}

	public void displayDepartments(Set<String> departments){
		io.print("--- Departments --- ");
		for (String key : departments) {
			io.print("\t" + key);
		}

	}

	public Department selectDepartment(List<Department> list){
		io.print("--- Select Department ---");
		int count = 1;
		for (Department department : list) {
			io.print("\t"+count + ": " + department.getName());
			count++;
		}

		int x = io.readInt("Choice", 0, count+1);

		return list.get(x-1);

	}

	public String findItem(){
		return io.readString("Enter item name: ");
	}


	public int editSelection(int choice){

	}
	
}