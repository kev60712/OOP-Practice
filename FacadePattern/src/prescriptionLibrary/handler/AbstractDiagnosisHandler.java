package prescriptionLibrary.handler;

import prescriptionLibrary.patient.Patient;
import prescriptionLibrary.patient.Prescription;
import prescriptionLibrary.patient.Symptom;

import java.util.List;

public abstract class AbstractDiagnosisHandler implements DiagnosisHandler {

    private DiagnosisHandler nextHandler;

    public AbstractDiagnosisHandler(DiagnosisHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Prescription handle(Patient patient, List<Symptom> symptoms) {
        if (canHandle(patient, symptoms)) {
            return diagnose(patient, symptoms);
        } else if (nextHandler != null){
            return nextHandler.handle(patient, symptoms);
        } else {
            System.out.println(patient.getName());
            throw new RuntimeException("No handler can handle this case.");
        }
    }

    public void setNextHandler(DiagnosisHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
