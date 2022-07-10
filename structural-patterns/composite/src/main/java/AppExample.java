import Pattern.EmployeeExample;

public class AppExample {

    public static void main(String args[]){
        EmployeeExample CEO = new EmployeeExample("John","CEO", 30000);

        EmployeeExample headSales = new EmployeeExample("Robert","Head Sales", 20000);

        EmployeeExample headMarketing = new EmployeeExample("Michel","Head Marketing", 20000);

        EmployeeExample clerk1 = new EmployeeExample("Laura","Marketing", 10000);
        EmployeeExample clerk2 = new EmployeeExample("Bob","Marketing", 10000);

        EmployeeExample salesExecutive1 = new EmployeeExample("Richard","Sales", 10000);
        EmployeeExample salesExecutive2 = new EmployeeExample("Rob","Sales", 10000);

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        //print all employees of the organization
        System.out.println(CEO);

        for (EmployeeExample headEmployeeExample : CEO.getSubordinates()) {
            System.out.println(headEmployeeExample);

            for (EmployeeExample employeeExample : headEmployeeExample.getSubordinates()) {
                System.out.println(employeeExample);
            }
        }
    }
}