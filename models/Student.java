package models;
public class Student extends Person implements Sporty,Displayable {
    private int rollno;
    private double marks;
    private static int count = 100;
    
    //Constructors
    public Student() {
        count++; 
        rollno = count;
        marks = 0.0;
    }

    public Student(String name,String city, double marks) {
        super(name , city);
        count++;
        this.rollno = count;
        this.marks = marks;
    }
    public Student(String name,String city,int rollno, double marks) {
        super(name , city);
        this.rollno = rollno;
        this.marks = marks;
    }

    //Setters
    public void setRollno(int rollno){
        this.rollno = rollno;
    }
    public void setMarks(double marks){
        if(marks < 0 || marks > 100)
        throw new RuntimeException("Marks must be between 0 to 100");
        else this.marks = marks;
    }
        
    //Getters
    public int getRollno(){
        return this.rollno;
    }
    public double getMarks(){
        return this.marks;
    }
    //count of student
    public static int getStudentCount(){
        return count - 100;
    }
    @Override
    public void whoAmI(){
        System.out.println("I am a Student,Name:- "+getName()+", Roll no:- "+this.rollno+", Marks:- "+this.marks+", City:- "+getCity());
    }
    @Override
    public void performDuty(){
        System.out.println("I'm a Student , Studying for an Exam !");
    }
    public void displayStudentDetails() {
        displayInfo(); //name and city
        System.out.println("Roll: " + this.rollno);
        System.out.println("Marks: " + this.marks);
        System.out.println("-----------------");
    }

    @Override
    public void playSports(){
        System.out.println("I am playing Cricket for college team. ");
    }
     @Override
    public void displayDetails(){
        System.out.printf("%-10s| %-4s | %-20s | %-15s | %-20s | %-7.3f | %n",
        "STUDENT",
        this.getRollno(),
        this.getName(),
        this.getCity(),
        " - ",
        this.getMarks()
    );
    }

}