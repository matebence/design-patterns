package Pattern;

import java.util.ArrayList;
import java.util.List;

public class EmployeeExample {

    private String name;
    private String dept;
    private int salary;
    private List<EmployeeExample> subordinates;

    // constructor
    public EmployeeExample(String name, String dept, int sal) {
        this.name = name;
        this.dept = dept;
        this.salary = sal;
        subordinates = new ArrayList<EmployeeExample>();
    }

    public void add(EmployeeExample e) {
        subordinates.add(e);
    }

    public void remove(EmployeeExample e) {
        subordinates.remove(e);
    }

    public List<EmployeeExample> getSubordinates(){
        return subordinates;
    }

    public String toString(){
        return ("EmployeeExample :[ Name : " + name + ", dept : " + dept + ", salary :" + salary+" ]");
    }
}