
package mng;
import java.util.ArrayList;
import java.util.Scanner;
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
        Scanner sc = new Scanner(System.in);

    do {
        try {
             System.out.println(title);
            choice = sc.nextInt();
            valid = true;
        } catch (NumberFormatException e) {
            System.out.println("Nhập không hợp lệ. Vui lòng nhập số.");
        }
    } while (!valid);
    return choice;
}
   public void printMenu() {
    System.out.println("===== MENU =====");
    for (int i = 0; i < this.size(); i++) {
        System.out.println((i + 1) + ". " + this.get(i));
    }
    System.out.println("0. Quit");
}

}
