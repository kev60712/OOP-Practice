package prescriptionLibrary;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import prescriptionLibrary.patient.Patient;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PatientDatabase {

    private Map<String, Patient> patientMap = new HashMap<>();
    private ObjectMapper obm = new ObjectMapper();

    public PatientDatabase() throws FileNotFoundException {
        load();
    }

    public void load() throws FileNotFoundException {
//        // Initialize patient data
//        Patient patient1 = new Patient("A123456789", "Kevin", Patient.Gender.MALE, 20, 180f, 70f, new ArrayList<>());
//        Patient patient2 = new Patient("B123456789", "Andy", Patient.Gender.MALE, 29, 175f, 100f, new ArrayList<>());
//        Patient patient3 = new Patient("C123456789", "Joyce", Patient.Gender.FEMALE, 20, 160f, 70f, new ArrayList<>());
//        Patient patient4 = new Patient("D123456789", "Nancy", Patient.Gender.FEMALE, 18, 160f, 51f, new ArrayList<>());
//        List<Patient> patientList = Arrays.asList(patient1, patient2, patient3, patient4);
//        for (Patient patient : patientList) {
//            patientMap.put(patient.getId(), patient);
//        }
//        updatePatientDatabase();

        // Read data from patientData.json
        String filePath = System.getProperty("user.dir") + "/FacadePattern/src/datasource/patientData.json";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String jsonStr = br.lines().collect(Collectors.joining("\n"));
            List<Patient> patientList = obm.readValue(jsonStr, new TypeReference<List<Patient>>() {});
            for (Patient patient : patientList) {
                patientMap.put(patient.getId(), patient);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileNotFoundException("File not found: " + filePath);
        }
    }

    public Patient getPatientById(String id) {
        return patientMap.get(id);
    }


    public void updatePatientDatabase(){
        // write data to patientData.json
        String filePath = System.getProperty("user.dir") + "/FacadePattern/src/datasource/patientData.json";
        try {
            obm.writeValue(new File(filePath), patientMap.values());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
