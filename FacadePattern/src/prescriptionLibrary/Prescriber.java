package prescriptionLibrary;

import prescriptionLibrary.handler.DiagnosisHandler;
import prescriptionLibrary.patient.Case;
import prescriptionLibrary.patient.Patient;
import prescriptionLibrary.patient.Prescription;
import prescriptionLibrary.patient.Symptom;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Prescriber implements Runnable{

    private PatientDatabase patientDatabase;
    private DiagnosisHandler diagnosisHandler;
    private BlockingQueue<PrescriptionTask> waitingLine = new LinkedBlockingQueue<>();

    public Prescriber(PatientDatabase patientDatabase, DiagnosisHandler diagnosisHandler) {
        this.patientDatabase = patientDatabase;
        this.diagnosisHandler = diagnosisHandler;
    }

    @Override
    public void run() {
        while (true) {
            try {
                PrescriptionTask task = waitingLine.take();
                doPrescription(task.getClient(), task.getPatient(), task.getSymptoms());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void demandPrescription(Client client, String id, List<Symptom> symptoms) {
        Patient patient = patientDatabase.getPatientById(id);
        waitingLine.add(new PrescriptionTask(client, patient, symptoms));
    }

    public Prescription doPrescription(Client client, Patient patient, List<Symptom> symptoms) {
        try {
            Thread.sleep(3000);
            Prescription prescription = diagnosisHandler.handle(patient, symptoms);
            patient.getCases().add(new Case(symptoms, prescription, new Date(System.currentTimeMillis())));
            patientDatabase.updatePatientDatabase();
            notify(client, prescription);
            return prescription;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void notify(Client client, Prescription prescription){
        System.out.println(String.format("Prescription for %s is ready.", client.getName()));
        client.setPrescription(prescription);
    }

}
