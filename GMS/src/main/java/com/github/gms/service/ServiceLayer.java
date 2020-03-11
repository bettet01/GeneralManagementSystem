/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gms.service;

import com.github.gms.dto.Department;
import com.github.gms.dto.Item;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author briannaschladweiler
 */
public interface ServiceLayer {
    
    Item createItem();
    
    Item editItem(String edit, String choice, String itemName);
    
    void removeItem(Item toRemove);
    
    Item displayItem();
    
    void listAllDepartments();
    
    HashMap<String, Department> listItemByDeprtment(String department);
    
}
