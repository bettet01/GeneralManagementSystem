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

	public void execute() {
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

	public void addItem() {
		Item item = view.displayaddItem();
		service.createItem(item);
		view.displayItem(item);
	}

	public void editItem() {
		// String edit = view.user input here
		// String choice = view. user input here
		// String itemName = view. user input here

		// service.editItem(edit, choice, itemName);
		// view.successedit
	}

	public void removeItem() {
		Department depart = view.selectDepartment(service.getDepartmentList());
		String name = depart.getName();
		String itemName = view.findItem();
		service.removeItem(itemName, name);
	}

	public void displayItem() {
		Department depart = view.selectDepartment(service.getDepartmentList());
		String name = depart.getName();
		String itemName = view.findItem();

		Item newItem = service.displayItem(name, itemName);
		view.displayItem(newItem);
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