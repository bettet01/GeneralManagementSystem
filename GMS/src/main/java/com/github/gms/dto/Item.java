/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gms.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author briannaschladweiler
 */
public class Item {
		String name;
		LocalDate expDate;
		int itemCount;
		BigDecimal ppu;
		String department;

	public Item(){
		
	}

	public Item(String name, LocalDate expDate, int itemCount, BigDecimal ppu, String department) {
		this.name = name;
		this.expDate = expDate;
		this.itemCount = itemCount;
		this.ppu = ppu;
		this.department = department;
	}

    public Item(String name) {
        this.name = name;
    }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getExpDate() {
		return this.expDate;
	}

	public void setExpDate(LocalDate expDate) {
		this.expDate = expDate;
	}

	public int getItemCount() {
		return this.itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public BigDecimal getPpu() {
		return this.ppu;
	}

	public void setPpu(BigDecimal ppu) {
		this.ppu = ppu;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
