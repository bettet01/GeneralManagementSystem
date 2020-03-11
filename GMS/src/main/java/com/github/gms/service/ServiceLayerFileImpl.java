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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jake
 */
public class ServiceLayerFileImpl implements ServiceLayer {
    HashMap<String, Department> mapDepartments = new HashMap<>();
    
    public void createItem(String name, LocalDate expDate, int itemCount, BigDecimal ppu, String department){
        
        Item newItem = new Item(name, expDate, itemCount, ppu, department);
        Department depAdd = mapDepartments.get(newItem.getDepartment());
        depAdd.addItem(newItem);
        
    }
    
    @Override
    public void removeItem(String itToRemove, String department) {
        
        Department depart = mapDepartments.get(department);
        Collection<Item> collection = depart.getItems();
                
        for (Item item : collection) {
            if(itToRemove.equals(item.getName())) {
                Item itemR = item;
                depart.removeItem(itemR);
            }
        }
    }

    @Override
    public Item editItem(String choice, String itemName) {
        Set<String> depKey = mapDepartments.keySet();
            for (String k : depKey) {
                System.out.println( k + "    " + mapDepartments.get(k));
                if ((mapDepartments.get(k).getName().equals(itemName))) {
                    Item choiceItem = 
                }
            }
            Department depart = mapDepartments.get(department);
            Collection<Item> collection = depart.getItems();
            for (Item item : collection) {
            if(itemName.equals(item.getName())) {
                Item itemR = item;
                return itemR;
            }
        }
        }

    @Override
    public Item displayItem(String department, String itemName) {
        Department depart = mapDepartments.get(department);
        Collection<Item> collection = depart.getItems();
                
        for (Item item : collection) {
            if(itemName.equals(item.getName())) {
                Item itemR = item;
                return itemR;
            }
        }
    }

    @Override
    public void listAllDepartments() {
        
        Set<String> keys = mapDepartments.keySet();
        for (String k : keys) {
            System.out.println(keys);
        }
    }

    @Override
    public HashMap<String, Department> listItemsByDepartment(String department) {
        Department depart = mapDepartments.get(department);
        
        Set<String> keys = mapDepartments.keySet();
        for (String k : keys) {
            //view display here
        }
        
    }
    
     
}
