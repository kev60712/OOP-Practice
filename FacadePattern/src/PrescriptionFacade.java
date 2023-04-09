import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import prescriptionLibrary.*;
import prescriptionLibrary.handler.*;
import prescriptionLibrary.patient.Prescription;
import prescriptionLibrary.patient.Symptom;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class PrescriptionFacade {

    private Prescriber prescriber;
    private PatientDatabase patientDatabase;

    public PrescriptionFacade() throws FileNotFoundException {
        patientDatabase = new PatientDatabase();
        DiagnosisHandler diagnosisHandler = loadSupportedDiagnosisHandler();
        prescriber = new Prescriber(patientDatabase, diagnosisHandler);
        Thread thread = new Thread(prescriber);
        thread.start();
    }

    public DiagnosisHandler loadSupportedDiagnosisHandler() throws FileNotFoundException {
        List<AbstractDiagnosisHandler> handlerList = new ArrayList<>();
        String filePath = System.getProperty("user.dir") + "/FacadePattern/src/datasource/SupportedDiagnosis.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                if(line.equals("COVID-19")){
                    handlerList.add(new Covid19DiagnosisHandler(null));
                }else if(line.equals("Attractive")){
                    handlerList.add(new AttractiveHandler(null));
                }else if(line.equals("SleepApneaSyndrome")){
                    handlerList.add(new SleepApneaSyndromeDiagnosisHandler(null));
                }
            }
            for(int i = 0; i < handlerList.size() - 1; i++){
                handlerList.get(i).setNextHandler(handlerList.get(i + 1));
            }
        }catch (Exception e) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        return handlerList.get(0);
    }

    public void demandPrescription(Client client, String id, List<Symptom> symptoms) {
        System.out.println(String.format("Client %s demands prescription.", client.getName()));
        prescriber.demandPrescription(client, id, symptoms);
    }

    public void saveToJson(Prescription prescription) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(prescription);
        try(FileWriter fileWriter = new FileWriter("FacadePattern/src/output/" + prescription.getPatientName()+ "_prescription.json")){
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToCsv(Prescription prescription) {
        // TODO: Implement this method
    }
}
