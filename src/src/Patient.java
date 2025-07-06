import java.time.LocalDate;
import java.util.ArrayList;

public class Patient {
    private int id;
    private String name;
    private LocalDate DOB;
    private String diagnosis;
    private String allergies;
    private ArrayList<VitalSigns> vitalSigns;
    private boolean status;  // true = admitted, false = discharged

    // Constructor
    public Patient(int id, String name, LocalDate DOB,
                   String diagnosis, String allergies) {
        this.id = id;
        this.name = name;
        this.DOB = DOB;
        this.diagnosis = diagnosis;
        this.allergies = allergies;
        this.vitalSigns = new ArrayList<>();
        this.status = true;  // default admitted on creation
    }

    public Patient(int id, String name, LocalDate  DOB){
        this(id,name,DOB,"","");
    }

    public Patient(int id, String name, LocalDate DOB, String Diagnosis){
        this(id,name,DOB,Diagnosis,"");
    }


    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public LocalDate getDOB() { return DOB; }
    public String getDiagnosis() { return diagnosis; }
    public String getAllergies() { return allergies; }
    public ArrayList<VitalSigns> getVitalSigns() { return vitalSigns; }
    public boolean isAdmitted() { return status; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDOB(LocalDate DOB) { this.DOB = DOB; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public void setAllergies(String allergies) { this.allergies = allergies; }
    public void setStatus(boolean status) { this.status = status; }

    // CRUD for VitalSigns
    public void addVitalSign(VitalSigns vs) {
        vitalSigns.add(vs);
    }

    public void removeVitalSign(VitalSigns vs) {
        vitalSigns.remove(vs);
    }

    public void clearVitalSigns() {
        vitalSigns.clear();
    }

    // Toggle admission status
    public void admit() { this.status = true; }
    public void discharge() { this.status = false; }

    // For easy display
    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name +
                ", Status: " + (status ? "Admitted" : "Discharged");
    }
}
