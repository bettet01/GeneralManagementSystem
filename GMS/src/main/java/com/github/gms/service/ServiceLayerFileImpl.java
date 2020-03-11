/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gms.service;

import com.github.gms.dto.Department;
import com.github.gms.dto.Item;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Jake
 */
public class ServiceLayerFileImpl implements ServiceLayer {
    HashMap<String, Department> mapDepartments = new HashMap<>();
    List<Department> listDepartments = new ArrayList();
    
    public void createItem(String name, LocalDate expDate, int itemCount, BigDecimal ppu, String department){
        
        Item newItem = new Item(name, expDate, itemCount, ppu, department);
        Department depAdd = mapDepartments.get(newItem.getDepartment());
        depAdd.addItem(newItem);
        
    }
    
    @Override
    public Item removeItem(Item toRemove) {
        
        Item itemRemove = new Item();
        Department department = mapDepartments.get(itemRemove.getDepartment());
        department.removeItem(itemRmove);
        
    }

    @Override
    public Item createItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item editItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item displayItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Department> listAllDepartments() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Department> listItemByDeprtment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     
}
