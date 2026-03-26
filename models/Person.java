package models;
public abstract class Person  {
    private String name;
    private String city;

    public Person(){
        name = "--";
        city = "--";
    }
    public Person(String name,String city){
        this.name = name;
        this.city = city;
    }
    public void displayInfo(){
        System.out.println("Name: " + this.name);
        System.out.println("City: " + this.city);
    }
    //getters 
    public String getName(){
        return this.name;
    }
    public String getCity(){
        return this.city;
    }
    public void setCity(String city){
        this.city = city;
    }

    public abstract void whoAmI();
    public abstract void performDuty();
}
