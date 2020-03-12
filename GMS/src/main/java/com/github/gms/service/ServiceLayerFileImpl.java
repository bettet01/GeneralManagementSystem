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
    public Item createItem(Item item) {

        Department depAdd = mapDepartments.get(item.getDepartment());
        depAdd.addItem(item);
        try {
            dao.writeLibrary(mapDepartments);
        } catch (Exception e) {
            System.out.println("oops");
        }

        return mapDepartments.get(item.getDepartment()).getItem(item.getName());

    }

    @Override
    public Item removeItem(String itToRemove, String department) {

        Department depart = mapDepartments.get(department);
        Collection<Item> collection = depart.getItems();

        for (Item item : collection) {
            if (itToRemove.equals(item.getName().toUpperCase())) {
                depart.removeItems(item);

                try {
                    dao.writeLibrary(mapDepartments);
                } catch (Exception e) {
                    System.out.println("oops");
                }

                return item;
            }
        }

        return null;
    }

    @Override
    public Item editItem(String edit, int choice, String itemName) {
        Set<String> depKey = mapDepartments.keySet();
        for (String k : depKey) {
            for (Item j : mapDepartments.get(k).getItems()) {
                if (j.getName().toUpperCase().equals(itemName)) {
                    switch (choice) {
                        case 1:
                            int iEdit = Integer.parseInt(edit);
                            j.setItemCount(iEdit);
                            break;
                        case 2:
                            BigDecimal bdEdit = new BigDecimal(edit);
                            j.setPpu(bdEdit);
                            break;
                        case 3:
                            LocalDate ldEdit = LocalDate.parse(edit);
                            j.setExpDate(ldEdit);
                    }

                    try {
                        dao.writeLibrary(mapDepartments);
                    } catch (Exception e) {
                        System.out.println("oops");
                    }

                    return j;
                }
            }
        }

        return null;
    }

    @Override
    public Item displayItem(String department, String itemName) {
        Department depart = mapDepartments.get(department);
        Collection<Item> collection = depart.getItems();

        for (Item item : collection) {
            if (item.getName().toUpperCase().equals(itemName.toUpperCase())) {
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
    public void load() throws Exception {
        mapDepartments = dao.loadLibrary();
    }

    public void writeLibrary() throws Exception {
        dao.writeLibrary(mapDepartments);
    }

    public List<Department> getDepartmentList() {
        List<Department> departList = new ArrayList<Department>(mapDepartments.values());
        return departList;
    }

    public HashMap<String, Department> getMap() {
        return mapDepartments;
    }

}
