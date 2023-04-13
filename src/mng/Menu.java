
package mng;
import java.util.ArrayList;
public class Menu extends ArrayList<String> {
    public Menu(){
        super();
    }
    public Menu(String[] items){
        super();
        for(String item: items) this.add(item);
    }
   public static int getChoice(String title) {
    int choice = 0;
    boolean valid = false;
    do {
        try {
            System.out.println(title);
            choice = Integer.parseInt(System.console().readLine());
            valid = true;
        } catch (NumberFormatException e) {
            System.out.println("Nhập không hợp lệ. Vui lòng nhập số.");
        }
    } while (!valid);
    return choice;
}

}
