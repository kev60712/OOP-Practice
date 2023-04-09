package prescriptionLibrary.patient;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class Patient{

    public enum Gender{
        MALE, FEMALE
    }

    private String id;
    private String name;
    private Gender gender;
    private Integer age;
    private Float height;
    private Float weight;
    private List<Case> cases;

    public Patient(){

    }

    public Patient(String id, String name, Gender gender, int age, float height, float weight, List<Case> cases) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.cases = cases;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public Float getHeight() {
        return height;
    }

    public Float getWeight() {
        return weight;
    }

    public List<Case> getCases() {
        return cases;
    }

    public float calBMI(){
        float heightInMeter = height/100;
        return weight/(heightInMeter*heightInMeter);
    }
}
