public class Ward {

    private int[] patientsID;
    private int size;

    //Constructor
    public Ward(int[] patientsID, int size) {
        this.patientsID = patientsID;
        this.size = size;
    }

    // add a Patient
    public addPatient(int patientID) {

        if (this.size >= patientsID.length) {
            return null;
        }
        patientsID[size] = patientID;
    }

    //Return copy of patient IDs
    public int[] getPatientsID() {
        int[] result = new int[patientsID.length];
        System.arraycopy(patientsID,0,result,0,size);
        return result;
    }

    //Returns current number of patients
    public int getSize() {
        return size;
    }

    //Returns number of patients in Ward
    public int getCapacity() {
        return patientsID.length;
    }


}
