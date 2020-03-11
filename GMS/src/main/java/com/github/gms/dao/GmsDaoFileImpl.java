/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gms.dao;

import com.github.gms.dto.Item;
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
    public Item addItem(String title, Item item)
            throws ItemDaoException {
        loadLibrary();
        Item newItem = items.put(name, item);
        writeLibrary();
        return newItem;
    }

    @Override
    public List<Items> getAllDvds()
            throws GmsDaoException {
        loadLibrary();
        return new ArrayList(items.values());
    }

    @Override
    public Dvd getDvd(String title)
            throws DvdLibraryDaoException {
        loadLibrary();
        return dvds.get(title);
    }

    @Override
    public Dvd removeDvd(String title)
            throws DvdLibraryDaoException {
        loadLibrary();
        Dvd removedDvd = dvds.remove(title);
        writeLibrary();
        return removedDvd;
    }

    @Override
    public Dvd editDvd(String title, Dvd dvd) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd editDvd = dvds.put(title, dvd);
        writeLibrary();
        return editDvd;
    }

    @Override
        public List<Dvd> getDvdByDirector(String director) {
        return dvdMap.values()
                .stream()
                .filter(s -> s.getDirectorName().equalsIgnoreCase(director))
                .collect(Collectors.toList());
    }
    
    private Map<String, Dvd> dvds = new HashMap<>();

    private Dvd unmarshallDvd(String dvdAsText) {
        // dvdAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // Title::Release::Rating::Director::Studio::Note
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in dvdTokens.
        // Which should look like this:
        // ______________________________________________________
        // |       |         |        |          |        |      |
        // | Title | Release | Rating | Director | Studio | Note |
        // |       |         |        |          |        |      |
        // ------------------------------------------------------
        //  [0]       [1]       [2]      [3]        [4]      [5]
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Given the pattern above, the title is in index 0 of the array.
        String title = dvdTokens[0];
        
        LocalDate releaseDate = LocalDate.parse(dvdTokens[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Which we can then use to create a new Dvd object to satisfy
        // the requirements of the Dvd constructor.
        Dvd dvdFromFile = new Dvd(title);

        // However, there are 5 remaining tokens that need to be set into the
        // new dvd object. Do this manually by using the appropriate setters.
        dvdFromFile.setReleaseDate(releaseDate);

        dvdFromFile.setRating(dvdTokens[2]);

        dvdFromFile.setDirectorName(dvdTokens[3]);

        dvdFromFile.setStudio(dvdTokens[4]);

        dvdFromFile.setUserNote(dvdTokens[5]);
        // Dvd now created, must return it
        return dvdFromFile;
    }

    private void loadLibrary() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load library data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDvd holds the most recent dvd unmarshalled
        Dvd currentDvd;
        // Go through ROSTER_FILE line by line, decoding each line into a 
        // Dvd object by calling the unmarshallDvd method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Dvd
            currentDvd = unmarshallDvd(currentLine);

            // We are going to use the title as the map key for our dvd object.
            // Put currentDvd into the map using title as the key
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        // close scanner
        scanner.close();
    }

    private String marshallDvd(Dvd aDvd) {
        // We need to turn a Dvd object into a line of text for our file.

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 
        // Start with the title, since that's supposed to be first.
        String dvdAsText = aDvd.getTitle() + DELIMITER;

        // add the rest of the properties in the correct order:
        dvdAsText += aDvd.getReleaseDate().toString() + DELIMITER;

        dvdAsText += aDvd.getRating() + DELIMITER;

        dvdAsText += aDvd.getDirectorName() + DELIMITER;

        dvdAsText += aDvd.getStudio() + DELIMITER;

        
        if (aDvd.getUserNote().equals("")) {
                aDvd.setUserNote(" ");
            }

        dvdAsText += aDvd.getUserNote() + DELIMITER;

        // We have now turned a dvd to text! Return it!
        return dvdAsText;
    }

    /**
     * Writes all dvds in the roster out to a ROSTER_FILE. See loadRoster for
     * file format.
     *
     * @throws ClassRosterDaoException if an error occurs writing to the file
     */
    private void writeLibrary() throws DvdLibraryDaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save DVD data.", e);
        }

        String dvdAsText;
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            // turn a Dvd into a String
            dvdAsText = marshallDvd(currentDvd);
            // write the Dvd object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
