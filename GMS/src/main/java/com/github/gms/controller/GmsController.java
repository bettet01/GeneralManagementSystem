/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gms.controller;

import com.github.gms.dto.Department;

import com.github.gms.dto.Item;
import com.github.gms.service.ServiceLayer;
import com.github.gms.ui.GMSView;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author briannaschladweiler
 */
public class GmsController {
	GMSView view;
	ServiceLayer service;

	public GmsController(GMSView view, ServiceLayer service) {
		this.view = view;
		this.service = service;
	}

	public void execute() throws Exception {
		boolean gameOn = true;

		try {
			service.load();
		} catch (Exception e) {
			System.out.println("oops.");
			e.printStackTrace();
		}
		while (gameOn) {
			int x = mainMenu();
			switch (x) {
				case 1:
					addItem();
					break;
				case 2:
					editItem();
					break;
				case 3:
					displayItem();
					break;
				case 4:
					listAllDepartments();
					break;
				case 5:
					listItemsByDepartment();
					break;
				case 6:
					removeItem();
					break;
				case 7:
					gameOn = false;
					break;
				default:
					break;
			}
		}
	}

	public int mainMenu() {
		return view.mainMenu();
	}

	public void addItem() throws Exception {
		Item item = view.displayaddItem(service.getDepartmentList());
		Item returnItem = service.createItem(item);
		if(returnItem != null){
			view.displayItem(item);
		} else {
			view.displayAddedFail();
		}

	}

	public void editItem() {
		String item;
		String itemName = view.findItem();
		int choice = view.editSelection();
		if(choice == 1){
			item = view.editCount();
		} else if(choice == 2){
			item = view.editPrice();
		} else{
			item = view.editExpDate();
		}

		Item returnItem = service.editItem(item, choice, itemName);

		if(returnItem != null){
			view.displayEditSuccess();
		} else {
			view.displayEditFail();
		}
	}

	public void removeItem() {
		Department depart = view.selectDepartment(service.getDepartmentList());
		String name = depart.getName();
		String itemName = view.findItem();
		Item returnItem = service.removeItem(itemName, name);
		if(returnItem != null){
			view.displayRemoveSuccess();
		} else {
			view.displayRemoveFail();
		}
	}

	public void displayItem() {
		Department depart = view.selectDepartment(service.getDepartmentList());
		String name = depart.getName();
		String itemName = view.findItem();

		Item newItem = service.displayItem(name, itemName);
		if(newItem != null){
			view.displayItem(newItem);
		} else {
			view.displayCouldNotLocate();
		}

	}

	public void listAllDepartments() {
		Set<String> departments = service.listAllDepartments();
		view.displayDepartments(departments);
	}

	public void listItemsByDepartment() {
		Department selection = view.selectDepartment(service.getDepartmentList());
		String name = selection.getName();
		// String department = view. user input here
		Department depart = service.listItemsByDepartment(name);
		Collection<Item> listItems = depart.getItems();

		view.displayDepartmentItems(listItems);
	}
}