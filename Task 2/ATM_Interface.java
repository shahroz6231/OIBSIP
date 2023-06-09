import java.util.*;

import javax.accessibility.AccessibleAction;

class Account{
    int balance;
    int PrevTransaction;
    int flag=0;
    String UserName;
    String UserID;


    Account(String UserName, String UserID){
        this.UserName=UserName;
        this.UserID=UserID;
    }

    public final void clearscreen(){
        try{
            try{
                final String os=System.getProperty("os.name");
                if(os.contains("Windows")){
                    Runtime.getRuntime().exec("cls");
                }else{
                    Runtime.getRuntime().exec("clear");
                }
            }catch(final Exception e){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        }catch(final Exception es){

        }
    }

    void verifyID(){
        clearscreen();
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello "+ UserName+"\n");
        System.out.print("Please enter your UserID to move forward :");
        String id = sc.nextLine();
        if(id.equals(UserID)){
            clearscreen();
            Mainmenu();
        }else{
            System.out.println("\n**********************");
            System.out.println("INVALID ID... Try again.");
            System.out.println("**********************\n");

            if(flag<3){
                flag++;
                verifyID();
            }
        }

    }

    void deposit(int amount){
        if(amount!=0){
            balance=balance+amount;
            PrevTransaction=amount;
            System.out.println("Available Balance :"+ balance);
        }
    }

    void withdraw(int amount){
        if(balance> amount){
            balance=balance-amount;
            PrevTransaction=-amount;
            System.out.println("Available Balance : "+balance);
        }else{
            clearscreen();
            System.out.println("\n**********************");
            System.out.println("INSUFFICIENT BALANCE");
            System.out.println("**********************\n");
        }
    }

    void TransactionHistory(){
        if(PrevTransaction>0){
            System.out.println("Credited : "+ PrevTransaction);
        }else if(PrevTransaction<0){
            System.out.println("Debited : "+ PrevTransaction);
        }else{
            System.out.println("No Transaction History");
        }
    }

    void transfer(double amount, Account acc){
        if(this.balance<amount){
            clearscreen();
            System.out.println("\n**********************");
            System.out.println("INSUFFICIENT BALANCE");
            System.out.println("**********************\n");
        }else{
            this.balance -= amount;
            acc.balance += amount;
            System.out.println("\n******************");
            System.out.println("UPDATED STATEMENT");
            System.out.println("******************\n");
            System.out.println("NAME : "+this.UserName+" || AMOUNT : "+this.balance);
            System.out.println("NAME : "+acc.UserName+" || AMOUNT : "+acc.balance);
        }
    }

    void Mainmenu(){
        System.out.println("Hello "+UserName+ ".  ID : "+UserID);
        int option;
        do{
            System.out.println("1. Check balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            
            Scanner sc =new Scanner(System.in);
            option = sc.nextInt();
            System.out.println("\n*************************");
            System.out.println("PLEASE ENTER YOUR CHOICE");
            System.out.println("*************************\n");
    
            switch(option){
                case 1 :
                    clearscreen();
                    System.out.println("Available Balance : "+balance);
                    break;
                
                case 2:
                    clearscreen();
                    System.out.println("Enter the amount to withdraw");
                    int amount = sc.nextInt();
                    withdraw(amount);
                    System.out.println();
                    break;
    
                case 3:
                    clearscreen();
                    System.out.println("Enter the amount to deposit");
                    int amount2= sc.nextInt();
                    deposit(amount2);
                    System.out.println();
                    break;
                
                case 4: 
                    clearscreen();
                    Account a= new Account("Aman", "1234");
                    // System.out.println(a.customerName);
                    System.out.println("Enter amount to transfer");
                    double am = sc.nextDouble();
                    transfer(am, a);
                    break;
    
                case 5: 
                    clearscreen();
                    System.out.println("TRANSACTION HISTORY");
                    TransactionHistory();
                    System.out.println();
                    break;
    
                case 6: 
                    clearscreen();
                    System.out.println("You choose to EXIT");
                    break;
    
                default :
                    clearscreen();
                    System.out.println("Invalid option. Please Enter again.");
    
            }
        }while(option != 6);
        System.out.println("Thanks for using our ATM Services.");
        
    }

}

public class ATM_Interface {
    public static void main(String[] args) {
        Account ac= new Account("Shahroz", "6231");
        ac.verifyID();
    }
    
}
