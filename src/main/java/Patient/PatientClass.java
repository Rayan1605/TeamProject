package Patient;

import Id.Id;

public class PatientClass extends Id {
    private int ID;
    private String Firstname;
    private String dateOfBirthday;
    private String dateOfTreatment;

    private String address;
    private boolean needspecialNeeds;
    private String typeOfTreatment;
    private int phoneNumber;
    private String email;
    private String LastName;

    @Override
    public String toString() {
        return "PatientClass{" +
                "ID=" + ID +
                ", name='" + Firstname + '\'' +
                ", dateOfBirthday='" + dateOfBirthday + '\'' +
                ", dateOfTreatment='" + dateOfTreatment + '\'' +
                ", address='" + address + '\'' +
                ", needspecialNeeds=" + needspecialNeeds +
                ", typeOfTreatment='" + typeOfTreatment + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", LastName='" + LastName + '\'' +
                '}';
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public PatientClass() {

    }

    public boolean isNeedspecialNeeds() {
        return needspecialNeeds;
    }

    public void setNeedspecialNeeds(boolean needspecialNeeds) {
        this.needspecialNeeds = needspecialNeeds;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String name) {
        this.Firstname = name;
    }

    public String getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(String dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getDateOfTreatment() {
        return dateOfTreatment;
    }

    public void setDateOfTreatment(String dateOfTreatment) {
        this.dateOfTreatment = dateOfTreatment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getSpecialNeeds() {
        return needspecialNeeds;
    }

    public void setSpecialNeeds(boolean specialNeeds) {
        this.needspecialNeeds = specialNeeds;
    }

    public String getTypeOfTreatment() {
        return typeOfTreatment;
    }

    public void setTypeOfTreatment(String typeOfTreatment) {
        this.typeOfTreatment = typeOfTreatment;
    }


}
