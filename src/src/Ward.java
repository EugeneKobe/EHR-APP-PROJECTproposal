import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Ward {
    private static final int MAX_CAPACITY = 12;
    private ArrayList<Patient> patients;

    // Constructor
    public Ward() {
        this.patients = new ArrayList<>();
    }

    // Admit a patient (returns true if successful, false if not)
    public boolean admitPatient(Patient patient) {
        if (isFull()) {
            return false;
        }
        if (patient.isAdmitted() && !patients.contains(patient)) {
            patients.add(patient);
            return true;
        }
        return false;
    }

    // Discharge a patient (returns true if successful)
    public boolean dischargePatient(Patient patient) {
        if (patients.remove(patient)) {
            patient.discharge();
            return true;
        }
        return false;
    }

    // Get list of admitted patients (read-only copy)
    public ArrayList<Patient> getPatients() {
        return new ArrayList<>(patients);
    }

    // Check if ward is full
    public boolean isFull() {
        return patients.size() >= MAX_CAPACITY;
    }

    // Clear all patients (discharges them too)
    public void clearWard() {
        for (Patient p : patients) {
            p.discharge();
        }
        patients.clear();
    }

    // Save patients to file in FASTA format
    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Patient p : patients) {
                writer.write(serializePatient(p));
            }
        }
    }

    // Load patients from file and replace current patients list
    public void loadFromFile(String filename) throws IOException {
        ArrayList<Patient> loadedPatients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            Patient p;
            while ((p = deserializePatient(reader)) != null) {
                loadedPatients.add(p);
            }
        }
        this.patients = loadedPatients;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Ward Patients (Admitted):\n");
        for (Patient p : patients) {
            sb.append("ID: ").append(p.getId())
                    .append(", Name: ").append(p.getName())
                    .append("\n");
        }
        sb.append("Total: ").append(patients.size())
                .append("/").append(MAX_CAPACITY);
        return sb.toString();
    }

    // ---- Helper methods for serialization ----

    private static String serializePatient(Patient p) {
        StringBuilder sb = new StringBuilder();
        sb.append(">")
                .append(p.getId()).append("|")
                .append(p.getName()).append("|")
                .append(p.getDOB()).append("|")
                .append(p.getDiagnosis()).append("|")
                .append(p.getAllergies()).append("|")
                .append(p.isAdmitted())
                .append("\n");

        for (VitalSigns vs : p.getVitalSigns()) {
            sb.append(vs.serialize()).append("\n");
        }
        return sb.toString();
    }

    private static Patient deserializePatient(BufferedReader br) throws IOException {
        String header = br.readLine();
        if (header == null || !header.startsWith(">")) {
            return null;
        }
        String[] parts = header.substring(1).split("\\|");
        if (parts.length < 6) {
            throw new IOException("Invalid patient header: " + header);
        }
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        LocalDate dob = LocalDate.parse(parts[2]);
        String diagnosis = parts[3];
        String allergies = parts[4];
        boolean admitted = Boolean.parseBoolean(parts[5]);

        Patient p = new Patient(id, name, dob, diagnosis, allergies);
        p.setStatus(admitted);

        br.mark(1000);
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(">")) {
                br.reset();
                break;
            }
            if (!line.trim().isEmpty()) {
                try {
                    VitalSigns vs = VitalSigns.deserialize(line);
                    p.addVitalSign(vs);
                } catch (Exception e) {
                    System.out.println("Skipped invalid vital sign line: " + line);
                }
            }
            br.mark(1000);
        }
        return p;
    }
}
