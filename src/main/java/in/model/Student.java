package in.model;


public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String hostel;

    public Student() {
    }

    public Student(String id, String firstName, String lastName, String hostel) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hostel = hostel;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHostel() {
        return hostel;
    }
}
