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
    import data.Dealer;

    public class DealerList extends ArrayList<Dealer> {
        LogIn loginObj = null;
        // Biến "loginObj" có thể được sử dụng để lưu trữ một đối tượng "LogIn" để xác
        // thực và xác định
        // quyền truy cập của người dùng khi họ muốn truy cập vào danh sách này.
        private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
        private String dataFile = "";
        boolean changed = false; // whether data in list change or not

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
//nó sẽ đọc dữ liệu đại lý từ tệp được lưu trữ trước đó và tạo ra một danh sách các đại lý trong chương trình từ dữ liệu đó
       public void initWithFile(){
        Config cR = new Config();//vì contructor của Config() có readData về tệp chứa danh sách đại lý
        dataFile = cR.getDealerFile(); //get address file containing dealer
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
    public void addDealer(){
        String ID;
        String name;
        String addr;
        String phone;
        boolean continuing;  
        int pos; 
        do{//input data
            ID = MyTool.readPattern("ID of new dealer", Dealer.ID_FORMAT);
            ID = ID.toUpperCase();
            pos = searchDealer(ID);
            if(pos >= 0) System.out.println("ID is duplicated!");
        }
        while (pos >= 0);
        name = MyTool.readNonBlank("Name of new dealer ".toUpperCase());
        addr = MyTool.readNonBlank("Address of new dealer ");
        phone = MyTool.readPattern("Phone number", Dealer.PHONE_FORMAT);
        continuing = true; //default value for new dealer
        Dealer d = new Dealer(ID,name,addr,phone,continuing);
        this.add(d); //add d vào dealer và sẽ được lưu vào list DealerList
        System.out.println("New dealer has been added");
        changed = true;
    }
    /*remove a dealer: Assign continuing = false 
    Input ID
    pos = search (ID)
    if (pos<0) output "Not Found!"
    else set field continuing of the pos(th) element to FALSE
    output "Removed"
    changed = true; 
    }
    */
    public void removeDealer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID of dealer to remove: ");
        String ID = sc.nextLine();
        int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("Not Found!");
        } else {
            this.get(pos).setContinuing(false);
            System.out.println("Removed");
            changed = true;
        }
    }

    public void updateDealer(){
        System.out.print("Dealer's ID needs updating: ");
        String ID = MyTool.SC.nextLine();
        int pos = searchDealer(ID);
        if(pos < 0) {
            System.out.println("Dealer " + ID + " not found!");
            return;
        }
        Dealer d = this.get(pos);

        //Update Name
        System.out.print("New name, ENTER for omitting: ");
        String newName = MyTool.SC.nextLine().trim().toUpperCase();
        if(!newName.isEmpty()){
            d.setName(newName);
            changed = true;
        }

        //Update Address
        System.out.print("New address, ENTER for omitting: ");
        String newAddr = MyTool.SC.nextLine().trim().toUpperCase();
        if(!newAddr.isEmpty()){
            d.setAddr(newAddr);
            changed = true;
        }

        //Update Phone
        System.out.print("New phone, ENTER for omitting: ");
        String newPhone = MyTool.SC.nextLine().trim().toUpperCase();
        if(!newPhone.isEmpty()){
            d.setPhone(newPhone);
            changed = true;
        }

    }
        public void printAllDealer(){
            if(this.isEmpty()) System.out.println("Empty List");
            else System.out.println(this);
        }
        public void printContinuingDealers(){
            this.getContinuingList().printAllDealer();
        }
        public void printUnContinuingDealers(){
            this.getUnContinuingList().printAllDealer();
        }
        //Write dealer list to file 
        public void writeDealerToFile(){
            if(changed){
//                /ghi từ this sang dataFile
                MyTool.writeFile(dataFile, this);
                changed = false;
            }
        }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
        
    }