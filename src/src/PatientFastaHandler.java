import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PatientFastaHandler {
    private static final String FILE_PATH = "patients.fasta";

    public static void savePatients(ArrayList<Patient> patients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Patient p : patients) {
                // Write FASTA header line: >id|name|dob|diagnosis|allergies|status
                String header = ">" + p.getId() + "|"
                        + escapePipes(p.getName()) + "|"
                        + p.getDOB() + "|"
                        + escapePipes(p.getDiagnosis()) + "|"
                        + escapePipes(p.getAllergies()) + "|"
                        + p.isAdmitted();
                writer.write(header);
                writer.newLine();

                // Write vital signs lines
                for (VitalSigns vs : p.getVitalSigns()) {
                    writer.write(vs.serialize());
                    writer.newLine();
                }
                writer.newLine(); // blank line to separate patients
            }
            System.out.println("Patients saved in FASTA format.");
        } catch (IOException e) {
            System.err.println("Error saving patients: " + e.getMessage());
        }
    }

    public static ArrayList<Patient> loadPatients() {
        ArrayList<Patient> patients = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            Patient currentPatient = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    // blank line means patient block ended
                    if (currentPatient != null) {
                        patients.add(currentPatient);
                        currentPatient = null;
                    }
                    continue;
                }

                if (line.startsWith(">")) {
                    // Patient header line
                    if (currentPatient != null) {
                        patients.add(currentPatient);
                    }
                    currentPatient = parsePatientHeader(line);
                } else {
                    // VitalSigns line
                    if (currentPatient != null) {
                        VitalSigns vs = VitalSigns.deserialize(line);
                        currentPatient.addVitalSign(vs);
                    } else {
                        System.err.println("VitalSigns line without patient header: " + line);
                    }
                }
            }
            // Add last patient if file ends without blank line
            if (currentPatient != null) {
                patients.add(currentPatient);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with empty patient list.");
        } catch (IOException e) {
            System.err.println("Error reading patients: " + e.getMessage());
        }

        return patients;
    }

    private static Patient parsePatientHeader(String headerLine) {
        // Remove leading '>'
        String data = headerLine.substring(1);
        String[] parts = data.split("\\|", -1);  // split into all parts, even if empty

        if (parts.length < 6) {
            System.err.println("Invalid patient header: " + headerLine);
            return null;
        }

        try {
            int id = Integer.parseInt(parts[0]);
            String name = unescapePipes(parts[1]);
            LocalDate dob = LocalDate.parse(parts[2]);
            String diagnosis = unescapePipes(parts[3]);
            String allergies = unescapePipes(parts[4]);
            boolean status = Boolean.parseBoolean(parts[5]);

            Patient p = new Patient(id, name, dob, diagnosis, allergies);
            p.setStatus(status);
            return p;
        } catch (Exception e) {
            System.err.println("Error parsing patient header: " + e.getMessage());
            return null;
        }
    }

    // Replace | in strings to avoid breaking format
    private static String escapePipes(String input) {
        if (input == null) return "";
        return input.replace("|", "\\|");
    }

    private static String unescapePipes(String input) {
        if (input == null) return "";
        return input.replace("\\|", "|");
    }
}
