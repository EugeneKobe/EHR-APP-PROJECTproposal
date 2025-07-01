public class UI {
    public static void main(String[] args) {
        VitalSigns bpOnly = new VitalSigns(120, 80); // BP only
        System.out.println("BP: " + bpOnly.getSys() + "/" + bpOnly.getDias());
        System.out.println("Timestamp: " + bpOnly.getFormattedTime());

        System.out.println();

        VitalSigns tempAndBP = new VitalSigns(36.8, 115, 75); // Temp + BP
        System.out.println("Temp: " + tempAndBP.getTemp());
        System.out.println("BP: " + tempAndBP.getSys() + "/" + tempAndBP.getDias());
        System.out.println("Timestamp: " + tempAndBP.getFormattedTime());

        System.out.println();

        VitalSigns hrBp = new VitalSigns(72, 130, 85, true); // HR + BP
        System.out.println("HR: " + hrBp.getHR() + ", BP: " + hrBp.getSys() + "/" + hrBp.getDias());
        System.out.println("Timestamp: " + hrBp.getFormattedTime());

        System.out.println();

        VitalSigns bpAndSpO2 = new VitalSigns(125, 79, 97.2); // BP + SpO2
        System.out.println("BP: " + bpAndSpO2.getSys() + "/" + bpAndSpO2.getDias() + ", SpO2: " + bpAndSpO2.getSpO2());
        System.out.println("Timestamp: " + bpAndSpO2.getFormattedTime());
        lj  hqwiuheaklr
    }
}
