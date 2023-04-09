package prescriptionLibrary.handler;

import prescriptionLibrary.patient.Patient;
import prescriptionLibrary.patient.Prescription;
import prescriptionLibrary.patient.Symptom;

import java.util.Arrays;
import java.util.List;

public class SleepApneaSyndromeDiagnosisHandler extends AbstractDiagnosisHandler {

    public SleepApneaSyndromeDiagnosisHandler(DiagnosisHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public boolean canHandle(Patient patient, List<Symptom> symptoms) {
        return patient.calBMI() > 26 && symptoms.contains(Symptom.Snore);
    }

    @Override
    public Prescription diagnose(Patient patient, List<Symptom> symptoms) {
        return new Prescription(patient.getName(),
                "打呼抑制劑 ",
                "睡眠呼吸中止症（專業學名：SleepApneaSyndrome）",
                Arrays.asList("一捲膠帶"),
                "睡覺時，撕下兩塊膠帶，將兩塊膠帶交錯黏在關閉的嘴巴上，就不會打呼了。");
    }
}
