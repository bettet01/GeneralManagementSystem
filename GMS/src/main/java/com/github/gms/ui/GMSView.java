package com.github.gms.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
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


	public Item displayaddItem(List<Department> departList){
		io.print("--- Add Item ---");
		String name = io.readString("Name of Item: ");
		LocalDate exp = io.setLocalDate("Experation Date (If Applicable): ");
		int count = io.readInt("Number of Items in inventory: ");
		BigDecimal ppu = io.readBigDecimal("Price per unit: ");
		Department departchoice = selectDepartment(departList);

		Item item = new Item(name, exp, count, ppu, departchoice.getName());
		return item;
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
		io.print("");
		int x = io.readInt("Choice: ", 0, count+1);

		return list.get(x-1);

	}

	public String findItem(){
		return io.readString("Enter item name: ");
	}


	public int editSelection(){
		io.print("--- Select Edit ---");
		io.print("1: Inventory Count");
		io.print("2. Price Per Unit");
		io.print("3. Exp Date");

		return io.readInt("Choice: ", 0, 4);
	}

	public String editPrice(){
		BigDecimal x = io.readBigDecimal("Enter New Price: ");
		x.setScale(2);
		return x.toString();
	}

	public String editCount(){
		int x = io.readInt("Enter New Count: ");
		return Integer.toString(x);
	}

	public String editExpDate(){
		LocalDate x = io.setLocalDate("Enter Exp Date: ");
		return x.toString();
	}
	
}