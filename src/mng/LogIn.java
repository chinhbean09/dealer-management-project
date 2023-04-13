package mng;

import data.Account;
import data.AccountChecker;
import data.DealerList;
import java.util.Scanner;
import tools.MyTool;

public class LogIn {

    private Account acc = null;//account will log in

    // giá trị ban đầu là null. Biến "acc" được sử dụng để lưu trữ đối tượng của lớp
    // "Account" mà đối tượng của lớp "LogIn" sử dụng để đăng nhập
    public LogIn(Account acc) {
        this.acc = acc;
    }

    /*Input data of an account
    Create new Account 
    return this Account
//  trả về một đối tượng mới của lớp Account.


     */
    public static Account inputAccount() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter account number: ");
        String accName = sc.nextLine();

        System.out.print("Enter account pwd: ");
        String pwd = sc.nextLine();

        System.out.print("Enter account role: ");
        String role = sc.nextLine();

        // Tạo và trả về đối tượng tài khoản mới
        return new Account( accName,  pwd,  role);
    }

    public Account getAcc() {
        return acc;
    }
    public static void main(String[] args) {
        Account acc = null; //account will login to system
        boolean cont = false; //login again?
        boolean valid = false; //valid account or not 
        do{
            AccountChecker accChk = new AccountChecker();
            acc = inputAccount(); //input account's data 
            valid = accChk.check(acc);//check validity
            if(!valid)
                cont = MyTool.readBool("Invalid account - Try again?");
            if (!valid && !cont) System.exit(0); //quit the program
            
        }while(cont);
        LogIn loginObj = new LogIn(acc);//create a login obj for valid acc
        //Run Dealer manager
        if(acc.getRole().equalsIgnoreCase("ACC-1")){
            //Setup menu
            String[] options = {"Add new dealer","Search a dealer",
                "Remove a dealer","Update a dealer",
                "Print all dealer", "Print continuing dealers",
                "Print UN_continuing dealers", "Write to file"  
            };
            Menu mnu = new Menu(options);
            DealerList dList = new DealerList(loginObj); //Setup DealerList
            dList.initWithFile();
            int choice = 0;
            do{//Do activities 
                choice = mnu.getChoice("Managing dealers");
                switch(choice){
                    case 1: dList.addDealer();break;
                    case 2: dList.searchDealer();break;
                    case 3: dList.removeDealer();break;
                    case 4: dList.updateDealer();break;
                    case 5: dList.printAllDealer();break;
                    case 6: dList.printContinuingDealers();break;
                    case 7: dList.printUnContinuingDealers();break;
                    case 8: dList.writeDealerToFile(); break;
                    default:
                        if(dList.isChanged()){
                            boolean res = MyTool.readBool("Data changed. Write to file?");
                            if (res == true) dList.writeDealerToFile();
                        }
                }
            }
            while (choice > 0 && choice < mnu.size());
            System.out.println("Bye.");
        }
    }
    

}


