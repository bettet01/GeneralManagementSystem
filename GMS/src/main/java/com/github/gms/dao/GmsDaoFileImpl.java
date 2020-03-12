/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gms.dao;

import com.github.gms.dto.Department;
import com.github.gms.dto.Item;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static void main(String[] args) {
        int count = 0;
        File file = new File("C:\\Users\\Ethan\\Documents\\Dev10\\GMS\\GMS\\resources");
        String[] pathnames = file.list();
        String[] departments = new String[pathnames.length];
        for (String pathname : pathnames) {
            String result = pathname.substring(0, pathname.length() - 4);
            departments[count] = result;
            count++;
        }

        Map<String, Department> departmentMap = new HashMap<>();
        for (String department : departments) {
            Department newDepartment = new Department(department);
            departmentMap.put(department, newDepartment);
        }
        System.out.println(Arrays.toString(departments));
        
        //return departmentMap;

    }

    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";

    private Map<String, Item> itemMap = new HashMap<>();

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

        itemFromFile.setItemCount(Integer.parseInt(itemTokens[2]));

        itemFromFile.setPpu(new BigDecimal(itemTokens[3]));

        itemFromFile.setDepartment(itemTokens[4]);

        return itemFromFile;
    }

    @Override
    public HashMap<String, Department> loadLibrary() throws GmsDaoException {
        Scanner scanner;

        int count = 0;
        File file = new File("C:\\Users\\Ethan\\Documents\\Dev10\\GMS\\GMS\\resources");

        String[] pathnames = file.list();
        String[] departments = new String[pathnames.length];
        for (String pathname : pathnames) {
            String result = pathname.substring(0, pathname.length() - 4);
            departments[count] = result;
            count++;
        }

        HashMap<String, Department> departmentMap = new HashMap<>();
        for (String department : departments) {
            Department newDepartment = new Department(department);
            departmentMap.put(department, newDepartment);
        }

        return departmentMap;

//        try {
//
//            scanner = new Scanner(
//                    new BufferedReader(
//                            new FileReader(file)));
//        } catch (FileNotFoundException e) {
//            throw new GmsDaoException(
//                    "-_- Could not load library data into memory.", e);
//        }
//
//        String currentLine;
//
//        Item currentItem;
//
//        while (scanner.hasNextLine()) {
//
//            currentLine = scanner.nextLine();
//
//            currentItem = unmarshallItem(currentLine);
//
//            items.put(currentItem.getName(), currentItem);
//        }
//
//        scanner.close();
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

//        String itemAsText;
//        List<Item> itemList = this.getAllItems();
//        for (Item currentItem : itemList) {
//
//            itemAsText = marshallItem(currentItem);
//
//            out.println(itemAsText);
//
//            out.flush();
//        }
//
//        out.close();
    }


}
