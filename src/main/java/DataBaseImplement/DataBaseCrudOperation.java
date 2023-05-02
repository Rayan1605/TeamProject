package DataBaseImplement;
import Dbconnection.DatabaseConnections;
import Id.Id;
import Patient.PatientClass;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class DataBaseCrudOperation extends Id implements DatabaseInterface  {
    Connection con;

//This is the class to handle all the Crud operation in the database
    //We were originally going to write a new class for each database and hard code
    //However we changed it and decide to write it in one class and use the database name as a parameter
//This is the class that implements the interface
    @Override
    public boolean createPatient(PatientClass patient,String DatabaseName) {
       if (!addId(patient.getID())) {
           int choice = 0;
           do {
               System.out.println("The id is already there");
               System.out.println("Do you wish to import it from the other table");
               System.out.println("Press 1 for yes and 2 for no");

               Scanner myinput = new Scanner(System.in);
               System.out.println("Enter here -> ");
              choice = myinput.nextInt();
               if (choice == 1 ){
            //  patient  = ImportPatient(patient.getID());
               }
              else if (choice == 2){
                   System.out.println("Please try again later");
                return false;
               }
           }while (choice != 1);
        }
        con= DatabaseConnections.createconnectiontoTeethTreatment();
        String query="INSERT INTO " + DatabaseName + " VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement pst=con.prepareStatement(query);
            pst.setInt( 1, patient.getID());
            pst.setString( 2, patient.getName());
            pst.setString( 3, patient.getDateOfBirthday());
            pst.setString( 4, patient.getDateOfTreatment());
            pst.setInt( 5, patient.getAge());
            pst.setBoolean( 6, patient.isNeedspecialNeeds());
            pst.setString( 7, patient.getTypeOfTreatment());
              int cnt = pst.executeUpdate();
              if(cnt!=0){
                  return true;
              }

              return false;

        }catch(Exception ex){
            return false;
        }
    }




    @Override
    public void showAllPatient(String DatabaseName) {
        con= DatabaseConnections.createconnectiontoTeethTreatment();
        String query="SELECT * FROM " + DatabaseName;
        System.out.println("Patient details: ");

        System.out.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\n",
                "ID|", "Name|", "DateofBirth|", "DateofTreatment|", "Age|"
                , "NeedsSpecialNeeds|", "TypeOfTreatment|");
        System.out.println("------------------------------------------------------------------------------------------------------------------\n");

        try{
            Statement stm2 =con.createStatement();
            ResultSet result= stm2.executeQuery(query);
            while(result.next()){
                System.out.format("%d|\t%s|\t%s|\t%s|\t%d|\t%b|\t%s|\n",
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getInt(5),
                        result.getBoolean(6),
                        result.getString(7));
                System.out.println("------------------------------------------------------------------------------------------------------------------\n");

            }
            System.out.println("\n");

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void showPatientBasedonID(int id, String DatabaseName) {
        System.out.println("------------------------------------------------------------------------------------------------------------------\n");
        con = DatabaseConnections.createconnectiontoTeethTreatment();
        String query = "SELECT * FROM " + DatabaseName + " where id = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                System.out.format("%d|\t%s|\t%s|\t%s|\t%d|\t%b|\t%s|\n",
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getInt(5),
                        result.getBoolean(6),
                        result.getString(7));
                System.out.println("------------------------------------------------------------------------------------------------------------------\n");
            } else {
                System.out.println("The id is not there");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean updatePatient(int id, String itemtoUpdate, String newValue, int index, String DatabaseName) {
        // take 3 things
        //id to know which person to update
        int count;
        con = DatabaseConnections.createconnectiontoTeethTreatment();
        String query = "UPDATE " + DatabaseName + " SET " + itemtoUpdate + " = ? WHERE id = ?";
        itemtoUpdate.toLowerCase();
        PreparedStatement statement;

            try {
                 statement = con.prepareStatement(query);
                if (Objects.equals(itemtoUpdate, "age")) {
                    for (Character c : newValue.toCharArray()) { // making sure the values are
                        //Number using ascii table
                        if (c >= 48 && c <= 57) {
                        } else {
                            return false;
                        }
                    }
                    statement.setInt(1, Integer.parseInt(newValue));
                    statement.setInt(2, id);
                  count =  statement.executeUpdate();

                } else if (Objects.equals(itemtoUpdate, "needspecialNeeds")) {
                    if (itemtoUpdate == "true" || itemtoUpdate == "false") {
                        statement.setBoolean(1, Boolean.parseBoolean(newValue));
                        statement.setInt(2, id);
                       count= statement.executeUpdate();
                    } else {
                        System.out.println("Please enter true or False");
                        System.out.println("Application now closing");
                        System.exit(0);
                        return false;
                    }
                } else {
                    statement.setString(1, newValue);
                    statement.setInt(2, id);
                   count =  statement.executeUpdate();
                }
                if (count > 0) {
                    System.out.println("Employee updated successfully");
                    return true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
    }

    @Override
    public void deletePatient(int id, String DatabaseName, String[] tableNames) {
        con = DatabaseConnections.createconnectiontoTeethTreatment();
        String query = "DELETE FROM  " + DatabaseName + " where id = ?";
        try{
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt( 1, id);
            int cnt = pst.executeUpdate();
            if (cnt!=0) {
                System.out.println("Patient deleted!");
                removeIdfromList(DatabaseName,id,tableNames);
                Id id1 = new Id();
                id1.DeleteTheId(id);
            }
            else {
                System.out.println("Patient not found!");
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String GetPassword(String DatabaseName){
        con = DatabaseConnections.CreatetoConnectionTouserdetails();
        PreparedStatement statement;
        try {
            statement = con.prepareStatement("SELECT Password FROM " + DatabaseName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("Password");
            } else {
                return "";
            }
        } catch (SQLException e) {
            return "";
        }
    }

    @Override
    public void SetPassword(String password , String DatabaseName){
      con = DatabaseConnections.CreatetoConnectionTouserdetails();
        String query = "INSERT INTO " + DatabaseName + " (password) VALUES (?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,password);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}



    

