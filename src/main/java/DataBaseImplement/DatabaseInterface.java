package DataBaseImplement;

import Patient.PatientClass;

public interface DatabaseInterface {
    boolean createPatient(PatientClass patient, String DatabaseName);
    void showAllPatient(String DatabaseName);
    void showPatientBasedonID(int id, String DatabaseName);
    PatientClass ImportPatient(int id);
    boolean updatePatient (int id, String itemtoUpdate, String newValue, int index , String DatabaseName);
    void deletePatient (int id,String DatabaseName,String[] TableNames);
    String GetPassword (String databaseName);
    void SetPassword (String password, String databaseName);

}
