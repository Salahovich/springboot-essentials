package giza.example.springbootessentials.Models;

public class Student {


    public enum Gender {MALE, FEMALE}
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private String nationalId;
    public Student(){}

    public Student(int id, String firstName, String lastName, int age, Gender gender, String email, String phoneNumber, String nationalId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
    }

    public void clone(Student student) {
        this.setId(student.getId());
        this.setAge(student.getAge());
        this.setEmail(student.getEmail());
        this.setGender(student.getGender());
        this.setFirstName(student.getFirstName());
        this.setLastName(student.getLastName());
        this.setPhoneNumber(student.getPhoneNumber());
        this.setNationalId(student.getNationalId());
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}
