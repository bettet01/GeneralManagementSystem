/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gms.controller;

import javax.swing.text.View;

import com.github.gms.dto.Item;
import com.github.gms.service.ServiceLayer;
import com.github.gms.ui.GMSView;

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

					break;
				case 3:

					break;
				case 4:

					break;
				case 5:

					break;
				case 6:

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

	public void addItem(){
		Item item = view.displayaddItem();
		service.createItem(item);
		view.displayItem(item);
	}
	
}  