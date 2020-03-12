/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gms.service;

import com.github.gms.dao.GmsDao;
import com.github.gms.dto.Department;
import com.github.gms.dto.Item;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Jake
 */
public class ServiceLayerFileImpl implements ServiceLayer {
    HashMap<String, Department> mapDepartments = new HashMap<>();
    GmsDao dao;

    public ServiceLayerFileImpl(GmsDao dao) {
        this.dao = dao;
    }

    @Override
    public void createItem(Item item){
       
        Department depAdd = mapDepartments.get(item.getDepartment());
        depAdd.addItem(item);
        
        
    }
    
    @Override
    public void removeItem(String itToRemove, String department) {
        
        Department depart = mapDepartments.get(department);
        Collection<Item> collection = depart.getItems();
                
        for (Item item : collection) {
            if(itToRemove.equals(item.getName())) {
                Item itemR = item;
                depart.removeItems(itemR);
            }
        }
    }

    @Override
    public void editItem(String edit, String choice, String itemName) {
        Set<String> depKey = mapDepartments.keySet();
            for (String k : depKey) {
                for (Item j : mapDepartments.get(k).getItems()) {
                    switch (choice) {
                        case "1":
                            int iEdit = Integer.parseInt(edit);
                            j.setItemCount(iEdit);
                            break;
                        case "2":
                            BigDecimal bdEdit = new BigDecimal(edit);
                            j.setPpu(bdEdit);
                            break;
                        case "3":
                            LocalDate ldEdit = LocalDate.parse(edit);
                            j.setExpDate(ldEdit);
                    }
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
        return null;
    }

    @Override
    public Set<String> listAllDepartments() {   
        Set<String> keys = mapDepartments.keySet();
        
        return keys;
    }

    @Override
    public Department listItemsByDepartment(String department) {
        Department depart = mapDepartments.get(department);
        
        return depart; 
    } 
    
    @Override
    public void load() throws Exception{
        mapDepartments = dao.loadLibrary();
    }


    public List<Department> getDepartmentList() {
        List<Department> departList = new ArrayList<Department>(mapDepartments.values());
        return departList;
    }
}

