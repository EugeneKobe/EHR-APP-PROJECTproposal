import java.io.IOException;
import java.time.LocalDate;

public class UI {
    public static void main(String[] args) {
        Ward ward = new Ward();

        // Create patient and admit
        Patient p1 = new Patient(1, "Alice", LocalDate.of(1980, 3, 15), "Flu", "None");
        p1.addVitalSign(new VitalSigns(37.2, 72, 120, 80, 98.0));
        ward.admitPatient(p1);

        Patient p2 = new Patient(2, "Bob", LocalDate.of(1975, 7, 22));
        p2.addVitalSign(new VitalSigns(36.5, 70, 118, 78, 99.0));
        ward.admitPatient(p2);

        String filename = "patients.fasta";

        try {
            ward.saveToFile(filename);
            System.out.println("Ward patients saved.");
        } catch (IOException e) {
            System.err.println("Failed to save: " + e.getMessage());
        }

        // Clear ward, then reload patients from file
        ward.clearWard();
        try {
            ward.loadFromFile(filename);
            System.out.println("Ward patients loaded.");
        } catch (IOException e) {
            System.err.println("Failed to load: " + e.getMessage());
        }

        // Print ward patients
        System.out.println(ward);

        for (Patient p : ward.getPatients()) {
            System.out.println(p);
            for (VitalSigns vs : p.getVitalSigns()) {
                System.out.println("  " + vs.serialize());
            }
        }
    }
}
