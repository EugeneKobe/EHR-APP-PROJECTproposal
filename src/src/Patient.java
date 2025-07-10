public class Patient {

    private static int nextId = 1;
    private int id;
    private String name;
    private int DOB;
    private String gender;
    private int assignedDoctor;
    private String diagnosis;
    private String allergies;
    private String[] vitalSigns;
    private boolean status;

    //Constructor
    public Patient(String name, int DOB, String gender, int assignedDoctor, String diagnosis, String allergies, String[] vitalSigns, boolean status) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.DOB = DOB;
        this.gender = gender;
        this.assignedDoctor = assignedDoctor;
        this.diagnosis = diagnosis;
        this.allergies = allergies;
        this.vitalSigns = vitalSigns;
        this.status = status;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDOB() {
        return DOB;
    }
    public void setDOB(int DOB) {
        this.DOB = DOB;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getAssignedDoctor() {
        return assignedDoctor;
    }
    public void setAssignedDoctor(int assignedDoctor) {
        this.assignedDoctor = assignedDoctor;
    }
    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    public String getAllergies() {
        return allergies;
    }
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
    public String[] getVitalSigns() {
        return vitalSigns;
    }
    public void setVitalSigns(String[] vitalSigns) {
        this.vitalSigns = vitalSigns;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }



}
