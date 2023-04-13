package data;

import java.util.List;
import tools.MyTool;

public class Config {
    // Hằng số này được sử dụng để định vị tệp tin cấu hình của ứng dụng.
    private static final String CONFIG_FILE = "src/DealerData/config.txt";
    private String accountFile;
    private String dealerFile;
    private String deliveryFile;

    public Config() {
        readData();
    }

    // Hàm readData trong class Config có tác dụng đọc dữ liệu từ tệp cấu hình
    // config.txt,
    // phân tích và lưu trữ thông tin về các tệp dữ liệu (accountFile, dealerFile,
    // deliveryFile).
    private void readData() {
        // Với mỗi dòng trong lines, hàm này sẽ kiểm tra xem dòng đó có chứa từ khóa
        // "ACCOUNT", "DEALER" hay "DELIVER" không
        List<String> lines = MyTool.readLinesFromFile(CONFIG_FILE);// đọc dữ liệu từ tệp cấu hình CONFIG_FILE
        for (String line : lines) {
            line = line.toUpperCase();
            String[] parts = line.split(":");
            if (line.indexOf("ACCOUNT") >= 0)
                accountFile = "src/DealerData/" + parts[1].trim().toLowerCase();
            // hàm sẽ cập nhật giá trị của biến accountFile bằng cách lấy phần tử thứ hai
            // trong mảng parts sau khi đã split, thêm vào đường dẫn tương đối đến thư mục
            // "DealerData".
            else if (line.indexOf("DEALER") >= 0)
                dealerFile = "src/DealerData/" + parts[1].trim().toLowerCase();
            else if (line.indexOf("DELIVER") >= 0)
                deliveryFile = "src/DealerData/" + parts[1].trim().toLowerCase();
        }
        /*
         * config.txt
         * ACCOUNT: account_data.csv
         * DEALER: dealer_data.csv
         * DELIVER: delivery_data.csv
         * 0 - 1
         * accountFile sẽ được gán giá trị "DealerData/account_data.csv",
         * dealerFile sẽ được gán giá trị "DealerData/dealer_data.csv"
         * deliveryFile sẽ được gán giá trị "DealerData/delivery_data.csv".
         */
    }

    public String getAccountFile() {
        return accountFile;
    }

    public String getDealerFile() {
        return dealerFile;
    }

    public String getDeliveryFile() {
        return deliveryFile;
    }

}
