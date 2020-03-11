/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gms.service;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author briannaschladweiler
 */
public interface ServiceLayer {
    
    Item createItem();
    
    Item editItem();
    
    Item removeItem();
    
    Item displayItem();
    
    List<Department> listAllDepartments();
    
    HashMap<String, Department> listFoodByDeprtment();
    
}
