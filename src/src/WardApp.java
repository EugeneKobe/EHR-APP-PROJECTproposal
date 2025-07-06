import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

public class WardApp extends JFrame {
    private Ward ward;
    private DefaultListModel<String> patientListModel;
    private JList<String> patientList;

    public WardApp() {
        ward = new Ward();

        setTitle("Ward Management");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        patientListModel = new DefaultListModel<>();
        patientList = new JList<>(patientListModel);
        JScrollPane scrollPane = new JScrollPane(patientList);

        // Input panel for admitting patients
        JPanel admitPanel = new JPanel(new GridLayout(5, 2));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField dobField = new JTextField("YYYY-MM-DD");
        JTextField diagnosisField = new JTextField();
        JTextField allergiesField = new JTextField();

        admitPanel.add(new JLabel("ID:"));
        admitPanel.add(idField);
        admitPanel.add(new JLabel("Name:"));
        admitPanel.add(nameField);
        admitPanel.add(new JLabel("DOB:"));
        admitPanel.add(dobField);
        admitPanel.add(new JLabel("Diagnosis:"));
        admitPanel.add(diagnosisField);
        admitPanel.add(new JLabel("Allergies:"));
        admitPanel.add(allergiesField);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton admitBtn = new JButton("Admit Patient");
        JButton dischargeBtn = new JButton("Discharge Selected");
        JButton saveBtn = new JButton("Save to File");
        JButton loadBtn = new JButton("Load from File");

        buttonPanel.add(admitBtn);
        buttonPanel.add(dischargeBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(loadBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(admitPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        admitBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                LocalDate dob = LocalDate.parse(dobField.getText().trim());
                String diagnosis = diagnosisField.getText().trim();
                String allergies = allergiesField.getText().trim();

                Patient newPatient = new Patient(id, name, dob, diagnosis, allergies);
                newPatient.admit();

                if (ward.admitPatient(newPatient)) {
                    updatePatientList();
                    JOptionPane.showMessageDialog(this, "Patient admitted.");
                } else {
                    JOptionPane.showMessageDialog(this, "Ward is full or patient already admitted.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            }
        });

        dischargeBtn.addActionListener(e -> {
            int index = patientList.getSelectedIndex();
            if (index >= 0) {
                String selected = patientListModel.get(index);
                int patientId = Integer.parseInt(selected.split(":")[0].trim());
                Patient pToDischarge = null;
                for (Patient p : ward.getPatients()) {
                    if (p.getId() == patientId) {
                        pToDischarge = p;
                        break;
                    }
                }
                if (pToDischarge != null && ward.dischargePatient(pToDischarge)) {
                    updatePatientList();
                    JOptionPane.showMessageDialog(this, "Patient discharged.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a patient to discharge.");
            }
        });

        saveBtn.addActionListener(e -> {
            try {
                ward.saveToFile("patients.fasta");
                JOptionPane.showMessageDialog(this, "Ward data saved.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving: " + ex.getMessage());
            }
        });

        loadBtn.addActionListener(e -> {
            try {
                ward.loadFromFile("patients.fasta");
                updatePatientList();
                JOptionPane.showMessageDialog(this, "Ward data loaded.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading: " + ex.getMessage());
            }
        });

        updatePatientList();
    }

    private void updatePatientList() {
        patientListModel.clear();
        for (Patient p : ward.getPatients()) {
            patientListModel.addElement(p.getId() + ": " + p.getName() + " (" + (p.isAdmitted() ? "Admitted" : "Discharged") + ")");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WardApp().setVisible(true);
        });
    }
}
