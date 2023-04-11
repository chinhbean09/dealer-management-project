/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.Collection;
import mng.LogIn;

public class DealerList extends ArrayList<Dealer>{
    LogIn loginObj= null;
//Biến "loginObj" có thể được sử dụng để lưu trữ một đối tượng "LogIn" để xác thực và xác định
//quyền truy cập của người dùng khi họ muốn truy cập vào danh sách này.
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private String dataFile="";
    boolean changed = false; //whether data in list change or not

   public DealerList(LogIn loginObj){
       this.loginObj = loginObj;
   }
   

}