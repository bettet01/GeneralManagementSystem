/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gms.dao;

import com.github.gms.dto.Department;
import java.util.HashMap;

/**
 *
 * @author briannaschladweiler
 */
public interface GmsDao {
    
    HashMap<String, Department> loadLibrary() throws GmsDaoException;
}
