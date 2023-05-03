package Id;

import Dbconnection.DatabaseConnections;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Id {
    //This is the class that will all id in the database
    private final List<Integer> id = new ArrayList<>();
    Connection con;

    //There two ways , one is if we use the different database way

    //The other is if we use the table way
    Connection connection = DatabaseConnections.createconnectiontoTeethTreatment();


    public void  GettingTheId()  {
         con = DatabaseConnections.CreatetoConnectionTouserdetails();
        String query="SELECT * FROM theid";
        try {
            Statement statement2 = con.createStatement();
            ResultSet result = statement2.executeQuery(query);
            while (result.next()) {
                this.id.add(result.getInt("Id"));
            }
         }  catch (SQLException e) {
            e.printStackTrace();
           }

    }

    public void DeleteTheId(int a ){
        con = DatabaseConnections.CreatetoConnectionTouserdetails();
        try {
            String query="DELETE FROM theid WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, a);
            statement.executeUpdate();

        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SettingTheId(int a ){
         con = DatabaseConnections.CreatetoConnectionTouserdetails();
        String query="INSERT INTO theid VALUES (?)";
        try {
            PreparedStatement statement2 = con.prepareStatement(query);
                statement2.setInt(1,a );
                statement2.executeUpdate();

        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Id() {

    }


//This is to add Id to the list however before doing so we need to check if the id is already there
    //This class will only be used when creating a Patient
    public boolean addId(int id) {
        GettingTheId();
        boolean check;
        if (this.id.size() == 0) {
          SettingTheId(id);
            return true;
        }
        check = checkifIdisthere(id);
        if (!check) {
            SettingTheId(id);
            return true;
        }
        //I would like to create a custom exception here but we can do that later
        // {1,2,3,4}
        //Someone enter 2
        //We send an error back to the user saying that the id is already there and then we will make him add a new one
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

    // In one table - rayan - id 4 - we wanted to remove it from the list
    //
    //in the other one we imported rayan so now two tables have an id  4
    //So we need to check if the id is in the other table before we can remove it from this table
    //If not then we leave a chance with different patients having the same id which will
    //screw up our program

    //This if for the Delete crud app
    //We are checking to see if we can remove it from the list
    public boolean removeIdfromList(String table,int identity,String [] tables) {
        boolean canweRemove;
        GettingTheId();

        try {
            canweRemove = IdExistInOtherTable(table,identity,tables); // if it exist then we can't
            if(canweRemove){ // if we can then remove again we should try to implement binary searcu
                for (int i = 0; i < this.id.size(); i++) {
                    if (this.id.get(i) == identity) {
                        this.id.remove(i);
                        DeleteTheId(identity);
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
    String sqlQuery = "SELECT Id FROM " + table + " WHERE Id = ?";
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);
        ResultSet ifId = preparedStatement.executeQuery();
        if (ifId.next()) {
            return true;
        } else {
            return false;
        }
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
//Check if the id exist in the other table
private boolean IdExistInOtherTable(String table, int identity, String[] tables) throws SQLException {
    // We check in a while loop to see if it has the same id if not then return false

    for (String table1 : tables) {
        if (!table1.equals(table)) {
            String sqlQuery = "SELECT Id FROM " + table1 + " WHERE Id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, identity);
            ResultSet ifId = statement.executeQuery();
            if (ifId.next()) {
                return true;
            }
        }
    }
    // If we reach this point, it means the ID was not found in any table
    return false;
}
    }



