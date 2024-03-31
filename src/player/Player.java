package player;

import java.util.Scanner;

public class Player {
    private String name;
    private String address;
    private long mobileNumber;
    private String emailID;
    private int age;
    
    public void setPlayerDetails(String name, String address, long mobileNumber, String emailID, int age) {
        this.name = name;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.emailID = emailID;
        this.age = age;
    }
    
    public void setPlayerName (String name) {
        this.name = name;
    }
    
    public void getPlayerDetails() {
        System.out.println("Player Name: " + this.name);
        System.out.println("Player EmailID: " + this.emailID);
        System.out.println("Player Age: " + this.age);
        System.out.println("Player Mobile Number: " + this.mobileNumber);
        System.out.println("Player Address: " + this.address);
        System.out.println("Player Age: " + this.age);
    }
    
    public String getPlayerName() {
        return this.name;
    }
    
    public void setPlayerDetailsFromUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Player details");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Mobile Number: ");
        Long mobileNumber = sc.nextLong();
        System.out.print("Email ID: ");
        String emailID = sc.next();
        System.out.print("Age: ");
        int age = sc.nextInt();
        this.setPlayerDetails(name, address, mobileNumber, emailID, age);
    }
}
