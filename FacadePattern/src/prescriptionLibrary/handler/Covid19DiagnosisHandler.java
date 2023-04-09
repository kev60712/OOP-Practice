package prescriptionLibrary.handler;

import prescriptionLibrary.patient.Patient;
import prescriptionLibrary.patient.Prescription;
import prescriptionLibrary.patient.Symptom;

import java.util.Arrays;
import java.util.List;

public class Covid19DiagnosisHandler extends AbstractDiagnosisHandler {

    public Covid19DiagnosisHandler(DiagnosisHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public boolean canHandle(Patient patient, List<Symptom> symptoms) {
       List<Symptom> covid19Symptoms = List.of(Symptom.Sneeze, Symptom.Headache, Symptom.Cough);
       return symptoms.containsAll(covid19Symptoms);
    }

    @Override
    public Prescription diagnose(Patient patient, List<Symptom> symptoms) {
        return new Prescription(patient.getName(),
                "清冠一號",
                "新冠肺炎（專業學名：COVID-19）",
                Arrays.asList("清冠一號"),
                "將相關藥材裝入茶包裡，使用500 mL 溫、熱水沖泡悶煮1~3 分鐘後即可飲用。");
    }
}
