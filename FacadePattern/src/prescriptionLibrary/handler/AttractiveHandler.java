package prescriptionLibrary.handler;

import prescriptionLibrary.patient.Patient;
import prescriptionLibrary.patient.Prescription;
import prescriptionLibrary.patient.Symptom;

import java.util.List;

public class AttractiveHandler extends AbstractDiagnosisHandler {

    public AttractiveHandler(DiagnosisHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public boolean canHandle(Patient patient, List<Symptom> symptoms) {
        return patient.getAge().equals(18) && symptoms.contains(Symptom.Sneeze);
    }

    @Override
    public Prescription diagnose(Patient patient, List<Symptom> symptoms) {
        return new Prescription(patient.getName(),
                "青春抑制劑 ",
                "有人想你了 (專業學名：Attractive)",
                List.of("假鬢角", "臭味"),
                "把假鬢角黏在臉的兩側，讓自己異性緣差一點，自然就不會有人想妳了。");
    }
}
