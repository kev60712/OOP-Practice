package prescriptionLibrary.patient;

import java.util.Date;
import java.util.List;

public class Case {

    private List<Symptom> symptomList;
    private Prescription prescription;
    private Date caseTime;

    public List<Symptom> getSymptomList() {
        return symptomList;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public Date getCaseTime() {
        return caseTime;
    }

    public Case(){

    }

    public Case(List<Symptom> symptomList, Prescription prescription, Date caseTime) {
        this.symptomList = symptomList;
        this.prescription = prescription;
        this.caseTime = caseTime;
    }
}
