/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gms.dao;

import com.github.gms.dto.Department;
import com.github.gms.dto.Item;
import com.github.gms.service.ServiceLayer;
import com.github.gms.service.ServiceLayerFileImpl;
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
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author briannaschladweiler
 */
public class GmsDaoFileImpl implements GmsDao {
        
    ServiceLayer service;
   
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

        itemFromFile.setExpDate(expDate);
        
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

        itemAsText += aItem.getExpDate().toString() + DELIMITER;

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
            }
            catch (IOException e) {
                throw new GmsDaoException(
                        "Could not save item data.", e);
            }

        }
    }
    
    public void addService(ServiceLayer service) {
        this.service = service;
    }
}
