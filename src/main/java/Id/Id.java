package Id;

import Dbconnection.DatabaseConnections;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Id {
    //This is the class that will all id in the database
    private final List<Integer> id = new ArrayList<>();
    //There two ways , one is if we use the different database way

    //The other is if we use the table way
    Connection connection = DatabaseConnections.createconnectiontoTeethTreatment();

    DatabaseMetaData tabledata;
//So this class will return all the table in our database
    public ResultSet getTable() {
        try {
            tabledata = connection.getMetaData();
            String[] differenttable = {"TABLE"};
            return tabledata.getTables(null, null, null, differenttable);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Id() {

    }


//This is to add Id to the list however before doing so we need to check if the id is already there
    //This class will only be used when creating a Patient
    public boolean addId(int id) {
        boolean check;
        if (this.id.size() == 0) {
            this.id.add(id);
            return true;
        }
        check = checkifIdisthere(id);
        if (!check) {
            this.id.add(id);
            return true;
        }
        //I would like to create a custom exception here but we can do that later
        // {1,2,3,4}
        //Someone enter 2
        //We send an error back to the user saying that the id is already there and then we will make him add a new one
        System.out.println("The id is already there");
        return false;
    }
    //To check if the id is already there in the list and we are using o(n) Complexity
    //However later on we can try to sort the id in the List and that way we can use
    //Binary search and get it o(log n) Complexity but we can do that later once we are done the project
    public boolean checkifIdisthere(int id){
        if (this.id.size() == 0) {
            return false;
        }
        else {
            for (Integer integer : this.id) {
                if (integer == id) {
                    return true;
                }
            }
            return false;
        }
    }
    public  void voidPrintallId(){
        for (int a : id) {
            System.out.println(a);
        }
    }

    // In one table - rayan - id 4 - we wanted to remove it from the list
    //
    //in the other one we imported rayan so now two tables have an id  4
    //So we need to check if the id is in the other table before we can remove it from this table
    //If not then we leave a chance with different patients having the same id which will
    //screw up our program

    //This if for the Delete crud app
    //We are checking to see if we can remove it from the list
    public boolean removeIdfromList(String table,int identity) {
        boolean canweRemove;

        try {
            canweRemove = IdExistInOtherTable(table,identity); // if it exist then we can't
            if(canweRemove){ // if we can then remove again we should try to implement binary searcu
                for (int i = 0; i < this.id.size(); i++) {
                    if (this.id.get(i) == identity) {
                        this.id.remove(i);
                        return true;
                    }
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
//This is for the Update and checking to see if there is an id in the table
    public boolean DoesIdExistInTable(String table, int id) {
        String sqlQuery = "SELECT id FROM " + table + " WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet ids = preparedStatement.executeQuery(sqlQuery);
            while (ids.next()) {
                if (ids.getInt("id") == id) {
                    return true;
                }
            }

            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//Check if the id exist in the other table
    private boolean IdExistInOtherTable(String table,int identity) throws SQLException {
        //Getting the table names
        ResultSet tables = getTable();

//this will consist all the id excluding the table that we are in
        //We should try to do binary search if we have time but for now we will do it like this
        ArrayList <Integer> ids = new ArrayList<>();

        while (tables.next()) {
            //While we are not in our table it will collect all the Id in the other tables
            //and compare them with the id that we want to remove and if it there
            //then we can't remove it from the list if it not then we can
            if (!tables.getString("TABLE_NAME").equals(table)) {

                Statement statement = connection.createStatement();
                String mytable = tables.getString("TABLE_NAME");
                String sqlQuery = "SELECT id FROM " + mytable + " WHERE id = ?";
                ResultSet theids = statement.executeQuery(sqlQuery);
                while (theids.next()) {
                    ids.add(theids.getInt("id"));
                }
                for (Integer integer : ids) {
                    if (integer == identity) {
                        return false;
                    }
                }

            }
        }
        return true;

    }






}
