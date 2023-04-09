package prescriptionLibrary.handler;

import prescriptionLibrary.patient.Patient;
import prescriptionLibrary.patient.Prescription;
import prescriptionLibrary.patient.Symptom;

import java.util.List;

public interface DiagnosisHandler {

    boolean canHandle(Patient patient, List<Symptom> symptoms);

    Prescription handle(Patient patient, List<Symptom> symptoms);

    Prescription diagnose(Patient patient, List<Symptom> symptoms);
}
