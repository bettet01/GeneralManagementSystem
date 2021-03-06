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
import java.util.Set;

/**
 *
 * @author briannaschladweiler
 */
public interface ServiceLayer {
    
    Item createItem(Item item);
    
    Item editItem(String edit, int choice, String itemName);
    
    Item removeItem(String itToRemove, String department);
    
    Item displayItem(String department, String itemName);
    
    Set<String> listAllDepartments();
    
    Department listItemsByDepartment(String department);
    
    void load() throws Exception;

    public List<Department> getDepartmentList();
    
    void writeLibrary() throws Exception;

    public HashMap<String, Department> getMap();
    
}
