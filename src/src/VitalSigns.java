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

    // Temperature only
    public VitalSigns(double temp) {
        this(temp, -1, -1, -1, -1);
    }

    // Heart rate only
    public VitalSigns(int HR, boolean isHR) {
        this(-1, HR, -1, -1, -1);
    }

    // Blood pressure only
    public VitalSigns(int sys, int dias) {
        this(-1, -1, sys, dias, -1);
    }

    // SpO₂ only
    public VitalSigns(double SpO2, boolean isSpO2) {
        this(-1, -1, -1, -1, SpO2);
    }

    // Temperature + BP
    public VitalSigns(double temp, int sys, int dias) {
        this(temp, -1, sys, dias, -1);
    }

    // HR + BP
    public VitalSigns(int HR, int sys, int dias, boolean isHRBP) {
        this(-1, HR, sys, dias, -1);
    }

    // BP + SpO2
    public VitalSigns(int sys, int dias, double SpO2) {
        this(-1, -1, sys, dias, SpO2);
    }

    // Temperature + HR
    public VitalSigns(double temp, int HR) {
        this(temp, HR, -1, -1, -1);
    }

    // Temperature + SpO2
    public VitalSigns(double temp, double SpO2) {
        this(temp, -1, -1, -1, SpO2);
    }

    // HR + SpO2
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
}
