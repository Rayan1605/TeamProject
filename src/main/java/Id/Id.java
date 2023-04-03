package Id;

import Dbconnection.DatabaseConnections;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Id {
    private final List<Integer> id = new ArrayList<>();
    //There two ways , one is if we use the different database way
    private final String[] database = {"orthodontic department", "Teethcleaning", "implantsandextraction"};
    //The other is if we use the table way
    Connection connection = DatabaseConnections.createconnectiontoOrtho();

    DatabaseMetaData tabledata;

    public ResultSet getTable() {
        try {
            tabledata = connection.getMetaData();
            String[] differenttable = {"TABLE"};
            ResultSet tables = tabledata.getTables(null, null, null, differenttable);
            return tables;
        } catch (SQLException e) {
           e.printStackTrace();
           return null;
        }
    }


    public Id() {

    }



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
        System.out.println("The id is already there");
       return false;
    }
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

    public List<Integer> allId() {
        return id;
    }

    public boolean removeIdfromList(String table,int identity) {
        boolean canweRemove;

            try {
                canweRemove = IsIdExist(table,identity);
                if(canweRemove){
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

    private boolean IsIdExist(String table,int identity) throws SQLException {
        ResultSet tables = getTable();

      boolean check = checkiftableexist(tables, table);
      if (!check){
          return false;
      }

        ArrayList <Integer> ids = new ArrayList<>();

      while (tables.next()) {
          if (tables.getString("TABLE_NAME").equals(table)) {

          }
          else{
              Statement statement = connection.createStatement();
              String sqlQuery = "SELECT id FROM mytable";// Once I know we are using table
              //then this mytable will replace with the table name that we are using and we will
              //use a string to keep changing it to the table name that we are using
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

    private boolean checkiftableexist(ResultSet tables, String table) {
try {
            while (tables.next()) {
                if (tables.getString("TABLE_NAME").equals(table)) {
                   return true;
                }
            }
            //We should make a custom exception here but we can do that later for now we will
    // just return false
    System.out.println("The table does not exist");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




}
