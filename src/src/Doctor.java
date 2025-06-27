import java.util.ArrayList;

public class Doctor {
    private int id;
    private String name;
    private String username;
    private String pw;
    private ArrayList<String> assignedPatients;

    // Constructor
    public Doctor(int id, String name, String username, String pw) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.pw = pw;
        this.assignedPatients = new ArrayList<>();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPw() {
        return pw;
    }

    public ArrayList<String> getAssignedPatients() {
        return assignedPatients;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setAssignedPatients(ArrayList<String> assignedPatients) {
        this.assignedPatients = assignedPatients;
    }

    // Add or remove patients
    public void addPatient(String patientId) {
        if (!assignedPatients.contains(patientId)) {
            assignedPatients.add(patientId);
        }
    }

    public void removePatient(String patientId) {
        assignedPatients.remove(patientId);
    }
}
