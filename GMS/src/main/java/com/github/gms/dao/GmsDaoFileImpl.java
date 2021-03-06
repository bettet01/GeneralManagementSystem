/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gms.dao;

import com.github.gms.dto.Department;
import com.github.gms.dto.Item;
import com.github.gms.service.ServiceLayer;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author briannaschladweiler
 */
public class GmsDaoFileImpl implements GmsDao {

    ServiceLayer service;

    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";

    private Item unmarshallItem(String itemAsText) {
        // ___________________________________________________
        // |       |         |           |          |        |  
        // | Item  | ExpDate | ItemCount |    ppu   | dept   | 
        // |       |         |           |          |        |    
        // ---------------------------------------------------
        //  [0]       [1]       [2]          [3]        [4]      
        String[] itemTokens = itemAsText.split(DELIMITER);
        
        String name = itemTokens[0];
        
        Item itemFromFile = new Item(name);

        if (itemTokens[1].equals("")) {
            LocalDate expDate = null;
            itemFromFile.setExpDate(expDate);
        } else {
            LocalDate expDate = LocalDate.parse(itemTokens[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            itemFromFile.setExpDate(expDate);
        }

        itemFromFile.setItemCount(Integer.parseInt(itemTokens[2]));

        itemFromFile.setPpu(new BigDecimal(itemTokens[3]));

        itemFromFile.setDepartment(itemTokens[4]);

        return itemFromFile;
    }

    @Override
    public HashMap<String, Department> loadLibrary() throws GmsDaoException {

        int count = 0;

        File file = new File("./resources");

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

            try {

                Scanner scanner = new Scanner(
                        new BufferedReader(
                                new FileReader("./resources/" + department + ".txt")));
                String currentLine;

                Item currentItem;

                while (scanner.hasNextLine()) {

                    currentLine = scanner.nextLine();

                    currentItem = unmarshallItem(currentLine);

                    newDepartment.addItem(currentItem);
                }

                scanner.close();

                departmentMap.put(department, newDepartment);
            } catch (FileNotFoundException e) {
                System.out.println("Hello");
            }

        }
        return departmentMap;
    }

    private String marshallItem(Item aItem) {

        String itemAsText = aItem.getName() + DELIMITER;

        if (aItem.getExpDate() == null) {
            itemAsText += "" + DELIMITER;
        } else {
            itemAsText += aItem.getExpDate().toString() + DELIMITER;
        }

        itemAsText += aItem.getItemCount() + DELIMITER;

        itemAsText += aItem.getPpu() + DELIMITER;

        itemAsText += aItem.getDepartment() + DELIMITER;

        return itemAsText;
    }

    @Override
    public void writeLibrary(HashMap<String, Department> mapDepartment) throws GmsDaoException {

        PrintWriter out;

        Set<String> depKey = mapDepartment.keySet();

        for (String k : depKey) {
            try {
                out = new PrintWriter(new FileWriter("./resources/" + k + ".txt"));

                String itemAsText;
                for (Item j : mapDepartment.get(k).getItems()) {
                    itemAsText = marshallItem(j);
                    out.println(itemAsText);
                    out.flush();
                }
                out.close();
            } catch (IOException e) {
                throw new GmsDaoException(
                        "Could not save item data.", e);
            }

        }
    }

    public void addService(ServiceLayer service) {
        this.service = service;
    }
}
