package Patient;

import Id.Id;

public class PatientClass extends Id {
    private int ID;
    private String name;
    private String dateOfBirthday;
    private String dateOfTreatment;

    private int age;
    private boolean needspecialNeeds;
    private String typeOfTreatment;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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


    @Override
    public String toString() {
        return "Patient" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", dateOfBirthday='" + dateOfBirthday + '\'' +
                ", dateOfTreatment='" + dateOfTreatment + '\'' +
                ", age=" + age +
                ", specialNeeds=" + needspecialNeeds +
                ", typeOfTreatment='" + typeOfTreatment + '\'';
    }
}
