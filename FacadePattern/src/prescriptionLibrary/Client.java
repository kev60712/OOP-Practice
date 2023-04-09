package prescriptionLibrary;

import prescriptionLibrary.patient.Prescription;

public class Client {

    private String name;
    private Prescription prescription;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
