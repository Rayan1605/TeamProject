package TestingPatient;

import Patient.PatientClass;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class Patienttesting {
@Test
    public void Set(){

        PatientClass patient = new PatientClass();
        patient.setID(1);
        patient.setName("John");
        patient.setDateOfBirthday("01/01/2000");
        patient.setDateOfTreatment("01/01/2020");
        patient.setAge(20);
        patient.setSpecialNeeds(true);
        patient.setTypeOfTreatment("Filling");

        assertEquals(1, patient.getID());
        assertEquals("John", patient.getName());
        assertEquals("01/01/2000", patient.getDateOfBirthday());
        assertEquals("01/01/2020", patient.getDateOfTreatment());
        assertEquals(20, patient.getAge());
        assertEquals(true, patient.getSpecialNeeds());
        assertEquals("Filling", patient.getTypeOfTreatment());







    }
}
