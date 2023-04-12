/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import mng.LogIn;
import tools.MyTool;

public class DealerList extends ArrayList<Dealer>{
    LogIn loginObj= null;
//Biến "loginObj" có thể được sử dụng để lưu trữ một đối tượng "LogIn" để xác thực và xác định
//quyền truy cập của người dùng khi họ muốn truy cập vào danh sách này.
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private String dataFile="";
    boolean changed = false; //whether data in list change or not

   public DealerList(LogIn loginObj){
       super();
       this.loginObj = loginObj;
   }
   /*
   Load dealers from file 
   Use MyTool to read lines from the data file, List lines 
   For each line in lines, create a dealer using this line as parameter 
   Add thid create dealer to the list 
   */
   private void loadDealerFromFile(){
    List<String> lines = MyTool.readLinesFromFile(dataFile);
    for (String line : lines) {
    //Đối với mỗi dòng dữ liệu, tạo một đối tượng Dealer mới bằng cách sử dụng chuỗi dữ liệu hiện tại như tham số.  
    Dealer dealer = new Dealer(line); 
    this.add(dealer);
   }
    
}
   public void initWithFile(){
    Config cR = new Config();//vì contructor của Config() có readData về tệp chứa danh sách đại lý
    dataFile = cR.getDealerFile(); //get file containing dealer
// getDealerFile để lấy tên tệp dữ liệu chứa danh sách đại lý và lưu vào biến dataFile
    loadDealerFromFile();//để đọc data dealer từ file và thêm vào Dealer file
   }
/*
Get the list of continuing dealers 
Create new result list belonging to DealerList
For each Dealer d in this
 if d.isContinuing() == true then add d to result list;
Return result;   
trả về một danh sách chứa các dealer đang tiếp tục hợp tác.
   */
public DealerList getContinuingList(){
     DealerList resultList = new DealerList(loginObj);
    for (Dealer d : this) {
        if (d.isContinuing()) {
            resultList.add(d);
        }
    }
    return resultList;
}
public DealerList getUnContinuingList(){
     DealerList resultList = new DealerList(loginObj);
    for (Dealer d : this) {
        if (!d.isContinuing()) {
            resultList.add(d);
        }
    }
    return resultList;

}
/*Search dealer - Use linear search
Convert the parameter ID to uppercase 
N = size of this list
for(i=0;i<n;i++)
if(i(th)dealer having the same ID) return i;
return -1 
//tìm kiếm một đại lý trong danh sách đại lý bằng cách duyệt từ đầu đến cuối danh sách.
*/
private int searchDealer (String ID){
      ID = ID.toUpperCase(); // chuyển đổi ID thành chữ hoa để so sánh chính xác hơn
       int n = this.size();//lấy kích thước hiện tại của danh sách các đại lý
    for (int i = 0; i < n; i++) {
        if (this.get(i).getID().toUpperCase().equals(ID)) {
            return i; // tìm thấy dealer có cùng ID, trả về chỉ số i
        }
    }
    return -1; // không tìm thấy dealer nào có cùng ID, trả về -1
}
/*
    Search dealer 
    Input String ID 
Call searchDealer(ID) and assign it's return value to pos 
if(pos<0) output "NOT FOUND!")
else output he pos(th) dealer in this list 
*/
public void searchDealer() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the ID of the dealer to search for: ");
    String ID = scanner.nextLine();
    int pos = searchDealer(ID);
    if (pos < 0) {
        System.out.println("NOT FOUND!");
    } else {
        System.out.println(this.get(pos).toString());
    }
}

}