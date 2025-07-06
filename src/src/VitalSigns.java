import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VitalSigns {
    private double temp;
    private int HR;
    private int sys;  // Systolic
    private int dias; // Diastolic
    private double SpO2;
    private LocalDateTime time;

    // Full constructor
    public VitalSigns(double temp, int HR, int sys, int dias, double SpO2) {
        this.temp = temp;
        this.HR = HR;
        this.sys = sys;
        this.dias = dias;
        this.SpO2 = SpO2;
        this.time = LocalDateTime.now();
    }

    // Constructor with time (used in deserialization)
    private VitalSigns(double temp, int HR, int sys, int dias, double SpO2, LocalDateTime time) {
        this.temp = temp;
        this.HR = HR;
        this.sys = sys;
        this.dias = dias;
        this.SpO2 = SpO2;
        this.time = time;
    }

    // Other constructors (temperature only, HR only, BP only, etc.)
    public VitalSigns(double temp) {
        this(temp, -1, -1, -1, -1);
    }

    public VitalSigns(int HR, boolean isHR) {
        this(-1, HR, -1, -1, -1);
    }

    public VitalSigns(int sys, int dias) {
        this(-1, -1, sys, dias, -1);
    }

    public VitalSigns(double SpO2, boolean isSpO2) {
        this(-1, -1, -1, -1, SpO2);
    }

    public VitalSigns(double temp, int sys, int dias) {
        this(temp, -1, sys, dias, -1);
    }

    public VitalSigns(int HR, int sys, int dias, boolean isHRBP) {
        this(-1, HR, sys, dias, -1);
    }

    public VitalSigns(int sys, int dias, double SpO2) {
        this(-1, -1, sys, dias, SpO2);
    }

    public VitalSigns(double temp, int HR) {
        this(temp, HR, -1, -1, -1);
    }

    public VitalSigns(double temp, double SpO2) {
        this(temp, -1, -1, -1, SpO2);
    }

    public VitalSigns(int HR, double SpO2, boolean isHRSpO2) {
        this(-1, HR, -1, -1, SpO2);
    }

    // Getters
    public double getTemp() { return temp; }
    public int getHR() { return HR; }
    public int getSys() { return sys; }
    public int getDias() { return dias; }
    public double getSpO2() { return SpO2; }
    public LocalDateTime getTime() { return time; }

    public String getFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return time.format(formatter);
    }

    // --- Serialize the object to a CSV string ---
    // Format: temp,HR,sys,dias,SpO2,timestamp
    // Use -1 to represent "no data" for numeric fields
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return temp + "," +
                HR + "," +
                sys + "," +
                dias + "," +
                SpO2 + "," +
                time.format(formatter);
    }

    // --- Deserialize a CSV string into a VitalSigns object ---
    public static VitalSigns deserialize(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid vital signs format: " + line);
        }
        try {
            double temp = Double.parseDouble(parts[0]);
            int HR = Integer.parseInt(parts[1]);
            int sys = Integer.parseInt(parts[2]);
            int dias = Integer.parseInt(parts[3]);
            double SpO2 = Double.parseDouble(parts[4]);
            LocalDateTime time = LocalDateTime.parse(parts[5]);

            return new VitalSigns(temp, HR, sys, dias, SpO2, time);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing vital signs: " + e.getMessage());
        }
    }
}
