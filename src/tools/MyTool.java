package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MyTool {

    public static final Scanner SC = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    public static boolean validPassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        }
        return str.matches(".*[a-zA-Z]+.*")
                && // at least 1 char
                str.matches(".*[\\d]+.*")
                && // at least 1 digit
                str.matches(".*[\\W]+.*");// at least 1 special char
    }// nếu str khớp với tất cả thì sẽ return str đó.

    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        // tạo ra một OBJ SimpleDateFormat từ định dạng dateFormat ,chính xác với định
        // dạng của
        // dateFormat mà ta truyền vào
        dF.applyPattern(dateFormat); // biến dF dùng để apply format của tham số và áp dụng nó thông qua class
                                     // SimpleDateFormat
        // nếu dateFormat có giá trị là "dd/MM/yyyy", thì phương thức applyPattern() sẽ
        // đặt định dạng của đối tượng SimpleDateFormat thành "dd/MM/yyyy"
        try {
            long t = dF.parse(dateStr).getTime();
            // Phương thức parse có chức năng chuyển đổi một chuỗi đại diện cho ngày tháng
            // thành một đối tượng Date(đại diện cho thời gian nhất định)
            return new Date(t);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    }

    // phương thức parseDate này có chức năng chuyển đổi một chuỗi đại diện cho ngày
    // tháng thành
    // một đối tượng Date dựa trên định dạng được chỉ định.Khi gọi phương thức
    // parseDate, bạn cần cung cấp một chuỗi
    // đại diện cho ngày tháng và một chuỗi định dạng để chỉ định cách đọc và chuyển
    // đổi chuỗi đó thành đối tượng Date.
    // convert date to String by using method format(date)
    public static String dataToStr(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String dateStr = sdf.format(date);
        // Phương thức format(date)sử dụng để chuyển đổi đối tượng Date thành chuỗi ngày
        // tháng.
        // đã đc chỉ định.
        return dateStr;
    }

    // convert bool string to boolean
    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    // Tools for inputting data
    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.print(message + ": ");
            input = SC.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    // hàm kiểm tra xem chuỗi nhập vào có rỗng không bằng cách sử dụng phương thức
    // isEmpty()
    public static String readPattern(String message, String pattern) {
        // message: chuỗi thông báo để yêu cầu người dùng nhập chuỗi.
        // pattern: chuỗi định dạng mẫu để kiểm tra xem chuỗi nhập vào có phù hợp với
        // định dạng đó hay không.
        String input = "";
        boolean valid;
        do {
            System.out.println(message + ":");
            input = SC.nextLine().trim();
            valid = validStr(input, pattern);// đúng nếu hàm validStr kiểm tra str đã khớp với regEx
        } while (!valid);
        return input;
    }

    public static boolean readBool(String message) {
        String input;
        System.out.print(message + "[1/0-Y/N-T/F]: ");
        input = SC.nextLine().trim();
        if (input.isEmpty())
            return false;
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }

    /*
     * Method for reading lines from text file
     * Create an array list, named as list
     * Open file
     * While ( still read succesfu lly a line in the file){
     * trim the line;
     * if line is not emply, add line to the list
     * }
     * Close file return list;
     * Đọc các dòng văn bản từ một tệp được chỉ định và trả về một danh sách các
     * dòng văn bản đó.
     */
    public static List<String> readLinesFromFile(String filename) {
        List<String> list = new ArrayList<>();
        // sử dụng class ArrayList để triển khai danh sách List<String>.
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            // FileReader là phương thức để tạo đối tượn đọc tệp văn bản được chỉ định bởi
            // biến filename
            String line = reader.readLine();
            while (line != null) {
                line = line.trim();
                if (!line.isEmpty()) { // nếu dòng đó ko rỗng thì thêm chuỗi line vào list
                    list.add(line);
                }
                line = reader.readLine();// để đọc dòng kế tiếp
            }
            reader.close();// sau khi hết vòng lặp. null đóng class
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
     * Method for writing a list to a text fine line-by-line
     * Open the file for writing
     * For each object in the list, write the object to file
     * Close the file
     * ghi các dữ liệu đã được khai báo của object vào 1 tệp
     */
    public static void writeFile(String filename, List<String> list) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (String item : list) {
                writer.write(item);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) { // ngoại lệ được sinh ra bởi các hoạt động I/O
            e.printStackTrace();// in ra lỗi
        }
    }

    public static void main(String[] args) {
        // //Phone: 9 or 11 digits -OK
        // System.out.println("Tests with phone number");
        // System.out.println(validStr("094990559","\\d{9}|\\d{11}"));//t
        // System.out.println(validStr("01234567891","\\d{9}|\\d{11}"));//t
        // System.out.println(validStr("12345678","\\d{9}|\\d{11}"));//f
        //
        // //Test password - OK
        // System.out.println(validPassword("qwerty",8));//f
        // System.out.println(validPassword("qweqeqwASD",8));//f
        // System.out.println(validPassword("123123412",8));//f
        // System.out.println(validPassword("qbc123456",8));//f
        // System.out.println(validPassword("qbc@123456",8));//t
        //
        // //ID format D000 - OK
        // System.out.println("Tests with IDs:");
        // System.out.println(validStr("A012","D\\d{3}"));//f
        // System.out.println(validStr("10101","D\\d{3}"));//f
        // System.out.println(validStr("D012","D\\d{3}"));//t
        // System.out.println(validStr("D0001","D\\d{3}"));//f

        // Test date format - OK
        // Date d = parseDate("2022:12:07","yyyy:MM:dd");
        // System.out.println(d);
        // System.out.println(dataToStr(d,"dd/MM/yyyy"));//OK, dataToStr sử dụng phương
        // thức format(date)để chuyển nó thành chuỗi đã chỉ định sẵn
        // d = parseDate("12/07/2022","MM/dd/yyyy");
        // System.out.println(d);
        // d = parseDate("2022/07/12","yyyy/MM/dd");
        // System.out.println(d);
        // d = parseDate("2000/29/02","yyyy/MM/dd");
        // System.out.println(d);
        // d = parseDate("2012/30/02","yyyy/MM/dd");
        // System.out.println(d);
        // d = parseDate("2000/40/16","yyyy/MM/dd");
        // System.out.println(d);

        // Text input data - OK
        // String input = readNonBlank("Input a non-blank string");
        // System.out.println(input);
        // input = readPattern("Phone 9/11 digits","\\d{9}|\\d{11}");
        // System.out.println(input);
        // input = readPattern("ID-format X00000","X\\d{5}");
        // System.out.println(input);
        // boolean b = parseBool("Input boolean");
        // System.out.println(b);

    }
}
