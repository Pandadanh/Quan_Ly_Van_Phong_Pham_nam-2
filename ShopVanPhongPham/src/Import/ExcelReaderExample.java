package Import;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import DTO.Product;

public class ExcelReaderExample {
    private static final String FILE_PATH = "C:/exceljava/product.xls";

    public static void main(String[] args) {
        List<Product> productList = readDataFromExcel(FILE_PATH);

        for (Product product : productList) {
            System.out.println(product.getDonGia());
        }
    }

    public static List<Product> readDataFromExcel(String filePath) {
        List<Product> productList = new ArrayList<>();

        try (Workbook workbook = getWorkbook(filePath)) {
            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rowCount; i++) { // Bắt đầu từ hàng thứ 2, bỏ qua tiêu đề
                Row row = sheet.getRow(i);

                int maSanPham = (int) row.getCell(0).getNumericCellValue();
                String tenSanPham = row.getCell(1).getStringCellValue();
                int soLuong = (int) row.getCell(2).getNumericCellValue();
                float donGia = (float) row.getCell(3).getNumericCellValue();
                String dVT = row.getCell(4).getStringCellValue();
                String moTa = row.getCell(5).getStringCellValue();
                int maLoai = (int) row.getCell(6).getNumericCellValue();
                boolean trangThai = row.getCell(7).getBooleanCellValue();
                String trangthai = String.valueOf(trangThai);
                int maNhaCungCap = (int) row.getCell(8).getNumericCellValue();

                Product product = new Product(maSanPham, tenSanPham, soLuong, donGia, dVT, moTa, maLoai, trangthai, maNhaCungCap);
                productList.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productList;
    }


    private static Workbook getWorkbook(String filePath) throws IOException {
        Workbook workbook = null;

        try (FileInputStream fis = new FileInputStream(filePath)) {
            if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis); // Đối với file .xls
            } else if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis); // Đối với file .xlsx
            }
        }

        return workbook;
    }
}
