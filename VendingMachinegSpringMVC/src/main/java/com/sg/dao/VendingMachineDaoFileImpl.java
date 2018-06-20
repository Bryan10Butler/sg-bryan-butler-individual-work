//package com.sg.dao;
//
//import com.sg.dto.VendingMachineItem;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//
//import java.io.*;
//import java.math.BigDecimal;
//import java.util.*;
//
//public class VendingMachineDaoFileImpl implements VendingMachineDao {
//
//    //private static String vendingMachineItemsFile;
//
//
//    //allows you to swap between test and prod files in APP
//
//    //public VendingMachineDaoFileImpl(String FILENAME) {
//        //this.vendingMachineItemsFile = FILENAME;
//    //}
//    private static final String INVENTORY_FILE = "Inventory.txt";
//    private static final String DELIMITER = "::";
//
//
//    //Map
//    private Map<String, VendingMachineItem> vendingMachineItemsMap = new HashMap<>();
//
//    @Override
//    public List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException {
//        loadVendingMachineItems();
//        //.values is a method on the map interface that returns a collection
//        Collection itemCollection = vendingMachineItemsMap.values();
//        //we need a list to return, not a collection
//        List itemList = new ArrayList(itemCollection);
//        return itemList;
//    }
//
//    @Override
//    public VendingMachineItem updateItem(VendingMachineItem itemToBeUpdated) throws VendingMachinePersistenceException {
//        loadVendingMachineItems();
//        vendingMachineItemsMap.put(itemToBeUpdated.getItemId(), itemToBeUpdated);
//        writeVendingMachineItems();
//        return itemToBeUpdated;
//    }
//
//    @Override
//    public VendingMachineItem retrieveItemById(String itemIdToPurchase) throws VendingMachinePersistenceException {
//        //Load text file
//        loadVendingMachineItems();
//        //get item  by id
//        VendingMachineItem itemRetrieved = vendingMachineItemsMap.get(itemIdToPurchase);
//        //return retrieved item
//        return itemRetrieved;
//    }
//
//    @Override
//    public VendingMachineItem removeVendingMachineItemById(String itemId) throws VendingMachinePersistenceException{
//
//        //pass in itemId from user to be removed
//        //pull item on map and remove
//        //store as new instance of VendingMachineItem
//        VendingMachineItem itemRemoved = vendingMachineItemsMap.remove(itemId);
//        //write that new item is removed
//        writeVendingMachineItems();
//        //return itemRemoved
//        return itemRemoved;
//    }
//
//    @Override
//    public VendingMachineItem createVendingMachineItemById(VendingMachineItem createItem)
//            throws VendingMachinePersistenceException{
//
//        //why do we need.getItemId();
//        VendingMachineItem createdItem = vendingMachineItemsMap.put(createItem.getItemId(), createItem);
//        writeVendingMachineItems();
//        return createdItem;
//    }
//
//    private void loadVendingMachineItems() throws VendingMachinePersistenceException {
//
//        Scanner scanner;
//
//        try {
//            // Create Scanner for reading the file
//            Resource resource = new ClassPathResource(INVENTORY_FILE);
//            scanner = new Scanner(new BufferedReader(new FileReader(resource.getFile())));
//        } catch (IOException e) {
//            throw new VendingMachinePersistenceException(
//                    "-_- Could not load roster data into memory.", e);
//        }
//        // currentLine holds the most recent line read from the file
//        String currentLine;
//        // currentTokens holds each of the parts of the currentLine after it has
//        // been split on our DELIMITER
//        // NOTE FOR APPRENTICES: In our case we use :: as our delimiter.  If
//        // currentLine looks like this:
//        // 1234::Joe::Smith::Java-September2013
//        // then currentTokens will be a string array that looks like this:
//        //
//        // ___________________________________
//        // |    |   |     |                  |
//        // |1234|Joe|Smith|Java-September2013|
//        // |    |   |     |                  |
//        // -----------------------------------
//        //  [0]  [1]  [2]         [3]
//        String[] currentTokens;
//        // Go through ROSTER_FILE line by line, decoding each line into a
//        // Student object.
//        // Process while we have more lines in the file
//        while (scanner.hasNextLine()) {
//            // get the next line in the file
//            currentLine = scanner.nextLine();
//            // break up the line into tokens
//            currentTokens = currentLine.split(DELIMITER);
//            // Create a new Student object and put it into the map of students
//            // NOTE FOR APPRENTICES: We are going to use the student id
//            // which is currentTokens[0] as the map key for our student object.
//            // We also have to pass the student id into the Student constructor
//            VendingMachineItem vendingMachineCurrentItem = new VendingMachineItem();
//            // Set the remaining values on currentStudent manually
//            vendingMachineCurrentItem.setName(currentTokens[1]);
//            vendingMachineCurrentItem.setPrice(new BigDecimal(currentTokens[2]));
//            vendingMachineCurrentItem.setQuantity(Integer.parseInt(currentTokens[3]));
//
//            // Put currentStudent into the map using studentID as the key
//            vendingMachineItemsMap.put(vendingMachineCurrentItem.getItemId(), vendingMachineCurrentItem);
//        }
//        // close scanner
//        scanner.close();
//
//    }
//
//    private void writeVendingMachineItems() throws VendingMachinePersistenceException {
//
//        // NOTE FOR APPRENTICES: We are not handling the IOException - but
//        // we are translating it to an application specific exception and
//        // then simple throwing it (i.e. 'reporting' it) to the code that
//        // called us.  It is the responsibility of the calling code to
//        // handle any errors that occur.
//        PrintWriter out;
//
//        try {
//            Resource resource = new ClassPathResource(INVENTORY_FILE);
//            out = new PrintWriter(new FileWriter(resource.getFile()));
//        } catch (IOException e) {
//            throw new VendingMachinePersistenceException(
//                    "Could not save item data.", e);
//        }
//
//        // Write out the Student objects to the roster file.
//        // NOTE TO THE APPRENTICES: We could just grab the student map,
//        // get the Collection of Students and iterate over them but we've
//        // already created a method that gets a List of Students so
//        // we'll reuse it.
//        List<VendingMachineItem> vendingMachineItemList = this.retrieveAllVendingMachineItems();
//        for (VendingMachineItem vendingMachineCurrentItem : vendingMachineItemList) {
//            // write the Student object to the file
//            out.println(vendingMachineCurrentItem.getItemId() + DELIMITER
//                    + vendingMachineCurrentItem.getName() + DELIMITER
//                    + vendingMachineCurrentItem.getPrice() + DELIMITER
//                    + vendingMachineCurrentItem.getQuantity());
//            // force PrintWriter to write line to the file
//            out.flush();
//        }
//        // Clean up
//        out.close();
//
//    }
//
//}
