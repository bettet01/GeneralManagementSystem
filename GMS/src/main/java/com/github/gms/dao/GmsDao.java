/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gms.dao;

import com.github.gms.dto.Item;
import java.util.List;

/**
 *
 * @author briannaschladweiler
 */
public interface GmsDao {
    
    Item addItem(String name, Item item)
            throws GmsDaoException;

    List<Item> getAllItems()
            throws GmsDaoException;

    Item getItem(String name)
            throws GmsDaoException;

    Item removeItem(String name)
            throws GmsDaoException;

    Item editItem(String title, Item item)
            throws GmsDaoException;
 
    public List<Item> getItemByName(String name);
    
    void loadLibrary() throws GmsDaoException;
}
