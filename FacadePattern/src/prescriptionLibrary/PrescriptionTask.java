package prescriptionLibrary;

import prescriptionLibrary.patient.Patient;
import prescriptionLibrary.patient.Symptom;

import java.util.List;

public class PrescriptionTask {

    private Client client;
    private Patient patient;
    private List<Symptom> symptoms;

    public PrescriptionTask(Client client, Patient patient, List<Symptom> symptoms) {
        this.client = client;
        this.patient = patient;
        this.symptoms = symptoms;
    }

    public Client getClient() {
        return client;
    }

    public Patient getPatient() {
        return patient;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }
}
