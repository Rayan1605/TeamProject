package DataBaseImplement;
import Dbconnection.DatabaseConnections;
import Id.Id;
import Patient.PatientClass;

import java.sql.*;
import java.util.Objects;

public class DataBaseCrudOperation extends Id implements DatabaseInterface  {
    Connection con;

//This is the class to handle all the Crud operation in the database
    //We were originally going to write a new class for each database and hard code
    //However we changed it and decide to write it in one class and use the database name as a parameter
//This is the class that implements the interface
    @Override
    public boolean createPatient(PatientClass patient,String DatabaseName) {
        con= DatabaseConnections.createconnectiontoTeethTreatment();
        String query="insert into " + DatabaseName + " values (?,?,?,?,?,?,?)";
        try{
            PreparedStatement pst=con.prepareStatement(query);
            pst.setInt( 1, patient.getID());
            pst.setString( 2, patient.getName());
            pst.setString( 3, patient.getDateOfBirthday());
            pst.setString( 4, patient.getDateOfTreatment());
            pst.setInt( 5, patient.getAge());
            pst.setBoolean( 6, patient.isNeedspecialNeeds());
            pst.setString( 7, patient.getTypeOfTreatment());

            int cnt=pst.executeUpdate();
            addId(patient.getID());//adding the id to main class
            if(cnt!=0){
                return true;
            }

        }catch(Exception ex){
            return false;
        }
        return false;
    }

    @Override
    public void showAllPatient(String DatabaseName) {
        con= DatabaseConnections.createconnectiontoTeethTreatment();
        String query="select * from " + DatabaseName;
        System.out.println("Patient details: ");
        System.out.println("*****************");
        System.out.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
                "ID", "Name", "DateofBirth", "DateofTreatment", "Address", "Age",
                "Allergies", "NeedsSpecialNeeds", "TypeOfTreatment");
        System.out.println("***********************");

        try{
            Statement stm2 =con.createStatement();
            ResultSet result= stm2.executeQuery(query);
            while(result.next()){
                System.out.format("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%b\t%s",
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getInt(6),
                        result.getString(7),
                        result.getBoolean(8),
                        result.getString(9));


                System.out.println("******************");

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void showPatientBasedonID(int id, String DatabaseName) {
        con=DatabaseConnections.createconnectiontoTeethTreatment();
        String query="select * from " + DatabaseName + " where id=" + id;
        try {
            Statement stm2=con.createStatement();
            ResultSet result= stm2.executeQuery(query);
            while(result.next()){
                System.out.format("%d\t%s\t%s\t%s\t%s\t%s\t%s\t%b\t%s",
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getInt(6),
                        result.getString(7),
                        result.getBoolean(8),
                        result.getString(9));
                System.out.println("******************");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean updatePatient(int id, String itemtoUpdate, String newValue, int index, String DatabaseName) {
        // take 3 things
        //id to know which person to update
        con = DatabaseConnections.createconnectiontoTeethTreatment();
        String query = "update " + DatabaseName + " set " + itemtoUpdate + " = ? where id = ?";
        itemtoUpdate.toLowerCase();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            if (Objects.equals(itemtoUpdate, "age")){
                for (Character c : newValue.toCharArray()) { // making sure the values are
                    //Number using ascii table
                    if (c >= 48 && c <= 57){

                    }
                    else{
                        return false;
                    }
                }
                ps.setInt(index, Integer.parseInt(newValue));

            } else if (Objects.equals(itemtoUpdate, "needspecialNeeds")) {
                if (itemtoUpdate == "true" || itemtoUpdate == "false") {
                    ps.setBoolean(index, Boolean.parseBoolean(newValue));
                }
                else {
                    System.out.println("Please enter true or False");
                    return false;
                }
            }else{
                ps.setString(index,newValue);
            }
            int count = ps.executeUpdate();
            if (count > 0){
                System.out.println("Employee updated successfully");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void deletePatient(int id, String DatabaseName) {
        con = DatabaseConnections.createconnectiontoTeethTreatment();
        String query = "delete from " + DatabaseName + " where id =?";
        try{
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt( 1, id);
            int cnt = pst.executeUpdate();
            if (cnt!=0) {
                System.out.println("Patient deleted!" + id);
                removeIdfromList("orthodontistclinic",id);
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



    

