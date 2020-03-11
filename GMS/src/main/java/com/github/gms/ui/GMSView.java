package com.github.gms.ui;

/**
* @author Ethan Bettenga
* GMSView
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
	
}