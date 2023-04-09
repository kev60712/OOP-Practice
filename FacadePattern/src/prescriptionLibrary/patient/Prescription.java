package prescriptionLibrary.patient;

import java.util.List;

public class Prescription {

    private String patientName;
    private String prescriptionName;
    private String potentialDesease;
    private List<String> medicines;
    private String instruction;

    public Prescription(){
    }

    public Prescription(String patientName, String prescriptionName, String potentialDesease, List<String> medicines, String instruction) {
        this.patientName = patientName;
        this.prescriptionName = prescriptionName;
        this.potentialDesease = potentialDesease;
        this.medicines = medicines;
        this.instruction = instruction;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public String getPotentialDesease() {
        return potentialDesease;
    }

    public List<String> getMedicines() {
        return medicines;
    }

    public String getInstruction() {
        return instruction;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "patientName='" + patientName + '\'' +
                ", PrescriptionName='" + prescriptionName + '\'' +
                ", potentialDesease='" + potentialDesease + '\'' +
                ", medicines=" + medicines +
                ", instruction='" + instruction + '\'' +
                '}';
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    public void setPotentialDesease(String potentialDesease) {
        this.potentialDesease = potentialDesease;
    }

    public void setMedicines(List<String> medicines) {
        this.medicines = medicines;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
