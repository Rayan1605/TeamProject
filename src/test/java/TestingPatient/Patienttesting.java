package TestingPatient;

import Patient.PatientClass;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class Patienttesting {
@Test
    public void Set(){

        PatientClass patient = new PatientClass();
        patient.setID(1);
        patient.setFirstname("John");
        patient.setEmail("John@gmail.com");
        patient.setLastName("Smith");
        patient.setPhoneNumber(123456789);
        patient.setAddress("PoliceStation");

        patient.setDateOfBirthday("01/01/2000");
        patient.setDateOfTreatment("01/01/2020");
        patient.setAddress("LeasNaCoille");
        patient.setSpecialNeeds(true);
        patient.setTypeOfTreatment("Filling");

        assertEquals(1, patient.getID());
        assertEquals("John", patient.getFirstname());
        assertEquals("01/01/2000", patient.getDateOfBirthday());
        assertEquals("01/01/2020", patient.getDateOfTreatment());
        assertEquals("LeasNaCoille", patient.getAddress());
        assertEquals(true, patient.getSpecialNeeds());
        assertEquals("Filling", patient.getTypeOfTreatment());







    }
}
