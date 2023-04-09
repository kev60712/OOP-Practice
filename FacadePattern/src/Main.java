import com.fasterxml.jackson.core.JsonProcessingException;
import prescriptionLibrary.Client;
import prescriptionLibrary.patient.Symptom;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException, FileNotFoundException {
        try{
            // init the client
            Client client1 = new Client("Kevin");
            Client client2 = new Client("Nancy");
            Client client3 = new Client("Andy");

            // init the prescription system
            PrescriptionFacade prescriptionFacade = new PrescriptionFacade();

            // Patient patient1 = new prescriptionSystem.patient.Patient("A123456789", "Kevin", prescriptionSystem.patient.Patient.Gender.MALE, 20, 180f, 70f, new ArrayList<>());
            List<Symptom> symptoms = Arrays.asList(Symptom.Sneeze, Symptom.Cough, Symptom.Headache);
            prescriptionFacade.demandPrescription(client1,"A123456789", symptoms);

            // Patient patient4 = new prescriptionSystem.patient.Patient("D123456789", "Nancy", prescriptionSystem.patient.Patient.Gender.FEMALE, 18, 160f, 51f, new ArrayList<>());
            symptoms = Arrays.asList(Symptom.Sneeze);
            prescriptionFacade.demandPrescription(client2,"D123456789", symptoms);

            // Patient patient2 = new prescriptionSystem.patient.Patient("B123456789", "Andy", prescriptionSystem.patient.Patient.Gender.MALE, 29, 175f, 100f, new ArrayList<>());
            symptoms = Arrays.asList(Symptom.Snore);
            prescriptionFacade.demandPrescription(client3,"B123456789", symptoms);

            // Wait for the prescription
            Thread.sleep(11000);

            // Get the prescription
            System.out.println(client1.getPrescription());
            prescriptionFacade.saveToJson(client1.getPrescription());
            System.out.println(client2.getPrescription());
            prescriptionFacade.saveToJson(client2.getPrescription());
            System.out.println(client3.getPrescription());
            prescriptionFacade.saveToJson(client3.getPrescription());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

