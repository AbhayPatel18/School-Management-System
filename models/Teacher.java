package models;
public class Teacher extends Person implements Sporty,Displayable{
    private double salary;
    private String subject;

    public Teacher(){
        salary = 0.0;
        subject = "--";
    }
    public Teacher(String name,String city, double salary, String subject){
        super(name , city);
        this.salary = salary;
        this.subject = subject;
    }
    //getter 
    public double getSalary(){
        return this.salary;
    }
    public String getSubject(){
        return this.subject;
    }//setter
    public void setSalary(double salary){
        this.salary = salary;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }
    @Override
    public void whoAmI(){
        System.out.println("I am a Teacher,Name:- "+getName()+",Subject:- "+this.subject+", Salary:- "+this.salary+", City:- "+getCity());
    }
    @Override
    public void performDuty(){
        System.out.println("I'm a Teacher , Teaching the Students");
    }
    public void displayTeacherDetails(){
        displayInfo();
        System.out.println("salary: " + this.salary);
        System.out.println("subject: " + this.subject);

    }
    @Override
    public void playSports(){
        System.out.println("I am playing Chess with Students. ");
    }
    @Override
    public void displayDetails(){
        System.out.printf("%-10s| %-4s | %-20s | %-15s | %-20s | %-7.3f | %n",
        "TEACHER",
        "TCH",
        this.getName(),
        this.getCity(),
        this.getSubject(),
        this.getSalary()
    );
    }

}
