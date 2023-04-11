    package data;
    import java.util.List;
    import tools.MyTool;

    public class AccountChecker {

        private String accFile;
        private static String SEPARATOR = ",";
        public AccountChecker(){
            setupAccFile();
        }
        private void setupAccFile(){
            Config cR = new Config();
            accFile = cR.getAccountFile();
        }
        //Check validadity od an account 
    //Phương thức này đọc dữ liệu từ file tài khoản được chỉ định trong biến accFile 
        public boolean check(Account acc){
        List<String> lines = MyTool.readLinesFromFile(accFile);
        //phương thức này trả về một List các chuỗi đại diện cho từng dòng trong file.
        for(String line: lines){
        String[] parts = line.split(this.SEPARATOR);
      //sử dụng để tách chuỗi line thành một mảng các chuỗi con dựa trên giá trị của biến SEPARATOR.
        if(parts.length < 3) return false;
        if(parts[0].equalsIgnoreCase(acc.getAccName()) &&
           parts[1].equals(acc.getPwd()) &&
           parts[2].equalsIgnoreCase(acc.getRole()))
           return true;
    }
        return false;
    }
        public static void main(String[] args) {
        
        try{
            AccountChecker aChk = new AccountChecker();
            Account acc = new Account("E001","12345678","BOSS");
            boolean valid = aChk.check(acc);
            System.out.println("Needs OK, OK?: " + valid);
            acc = new Account("E002","23456789","ACC-1"); 
            valid = aChk.check(acc);
            System.out.println("Needs OK, OK?: " + valid);
            acc = new Account("E003","123456789","ACC-2");
            valid = aChk.check(acc);
            System.out.println("Needs OK, OK?: " + valid);
        }
        catch(Exception e){
           e.printStackTrace();
        }
        }
    }
