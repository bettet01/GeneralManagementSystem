/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gms.dao;

import com.github.gms.dto.Item;
import com.sun.tools.javac.jvm.Items;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author briannaschladweiler
 */
public class GmsDaoFileImpl implements GmsDao {

    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";

    private Map<String, Item> itemMap = new HashMap<>();
    
    @Override
    public Item addItem(String name, Item item)
            throws ItemDaoException {
        loadLibrary();
        Item newItem = items.put(name, item);
        writeLibrary();
        return newItem;
    }

    @Override
    public List<Items> getAllItems()
            throws GmsDaoException {
        loadLibrary();
        return new ArrayList(items.values());
    }

    @Override
    public Item getItem(String name)
            throws GmsDaoException {
        loadLibrary();
        return items.get(name);
    }

    @Override
    public Item removeItem(String name)
            throws GmsDaoException {
        loadLibrary();
        Item removedItem = items.remove(name);
        writeLibrary();
        return removedItem;
    }

    @Override
    public Item editItem(String name, Item item) throws GmsDaoException {
        loadLibrary();
        Item editItem = items.put(name, item);
        writeLibrary();
        return editItem;
    }

    @Override
        public List<Item> getItemByName(String name) {
        return itemMap.values()
                .stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
    
    private Map<String, Item> items = new HashMap<>();

    private Item unmarshallItem(String itemAsText) {
        // ___________________________________________________
        // |       |         |           |          |        |  
        // | Item  | ExpDate | ItemCount |    ppu   | dept   | 
        // |       |         |           |          |        |    
        // ---------------------------------------------------
        //  [0]       [1]       [2]          [3]        [4]      
        String[] itemTokens = itemAsText.split(DELIMITER);

        String name = itemTokens[0];
        
        LocalDate expDate = LocalDate.parse(itemTokens[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Item itemFromFile = new Item(name);

        itemFromFile.setItemCount(itemTokens[2]);

        itemFromFile.setPpu(itemTokens[3]);
        
        itemFromFile.setDepartment(itemTokens[4]);

        return itemFromFile;
    }

    private void loadLibrary() throws GmsDaoException {
        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new GmsDaoException(
                    "-_- Could not load library data into memory.", e);
        }

        String currentLine;
   
        Item currentItem;

        while (scanner.hasNextLine()) {
           
            currentLine = scanner.nextLine();

            currentItem = unmarshallItem(currentLine);

            items.put(currentItem.getName(), currentItem);
        }

        scanner.close();
    }

    private String marshallItem(Item aItem) {

        String itemAsText = aItem.getName() + DELIMITER;

        itemAsText += aItem.getExpDate().toString() + DELIMITER;

        itemAsText += aItem.getItemCount() + DELIMITER;

        itemAsText += aItem.getPpu() + DELIMITER;

        itemAsText += aItem.getDepartment() + DELIMITER;

        return itemAsText;
    }

    private void writeLibrary() throws GmsDaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new GmsDaoException(
                    "Could not save item data.", e);
        }

        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            
            itemAsText = marshallItem(currentItem);

            out.println(itemAsText);

            out.flush();
        }

        out.close();
    }

    @Override
    public List<Item> getAllItems() throws GmsDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
