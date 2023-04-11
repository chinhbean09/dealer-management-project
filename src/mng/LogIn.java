
package mng;

import data.Account;

public class LogIn {
       private Account acc= null;
//giá trị ban đầu là null. Biến "acc" được sử dụng để lưu trữ đối tượng của lớp
//"Account" mà đối tượng của lớp "LogIn" sử dụng để đăng nhập
    public LogIn(Account acc) {
        this.acc = acc;
    }

}
