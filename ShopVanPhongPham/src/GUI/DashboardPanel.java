package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import DAO.DBConnect;
import DTO.Invoice;
import DTO.Product;
import DTO.Staff;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Font;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;





import DAO.DBConnect;
import DTO.Category;
import DTO.Invoice;
import DTO.InvoiceDetail;


public class DashboardPanel extends JPanel {
	List<Product> productList = new ArrayList<>();
	List<Staff> staffList = new ArrayList<>();
	DefaultTableModel tableModel;
	private JTable table_product;
	private JTable table_product1;
	private int index = -1;
	
	
    public static final int COLUMN_INDEX_ID         = 0;
    public static final int COLUMN_INDEX_TENSANPHAM     = 1;
    public static final int COLUMN_INDEX_SOLUONG     = 2;
    public static final int COLUMN_INDEX_DONGIA   = 3;
    public static final int COLUMN_INDEX_DVT      = 4;
    public static final int COLUMN_INDEX_MOTA      = 5;
    public static final int COLUMN_INDEX_MALOAI     = 6;
    public static final int COLUMN_INDEX_TRANGTHAI    = 7;
    public static final int COLUMN_INDEX_MANHACUNGCAP     = 8;

    private static CellStyle cellStyleFormatNumber = null;
    
	public DashboardPanel() {
		initComponet();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 625);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 880, 597);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Th\u1ED1ng k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel);
		panel.setLayout(null);
		
		JPanel jpnTongDoanhThu = new JPanel();
		jpnTongDoanhThu.setBackground(new Color(255, 255, 255));
		jpnTongDoanhThu.setBounds(30, 32, 500, 120);
		panel.add(jpnTongDoanhThu);
		jpnTongDoanhThu.setLayout(null);
		
		JLabel jlbTongDoanhThu = new JLabel("Doanh thu");
		jlbTongDoanhThu.setFont(new Font("Arial", Font.BOLD, 26));
		jlbTongDoanhThu.setBounds(10, 10, 129, 31);
		jpnTongDoanhThu.add(jlbTongDoanhThu);
		
		JLabel jlbTinhDT = new JLabel("000.000.000");
		jlbTinhDT.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbTinhDT.setForeground(new Color(255, 38, 0));
		jlbTinhDT.setFont(new Font("Arial", Font.BOLD, 22));
		jlbTinhDT.setBounds(10, 77, 140, 25);
		jpnTongDoanhThu.add(jlbTinhDT);
		
		JLabel jlbTinhDTT = new JLabel("000.000.000");
		jlbTinhDTT.setBounds(188, 77, 140, 25);
		jpnTongDoanhThu.add(jlbTinhDTT);
		jlbTinhDTT.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbTinhDTT.setForeground(new Color(255, 38, 0));
		jlbTinhDTT.setFont(new Font("Arial", Font.BOLD, 22));
		
		JLabel jlbTinhDTN = new JLabel("000.000.000");
		jlbTinhDTN.setBounds(354, 77, 140, 25);
		jpnTongDoanhThu.add(jlbTinhDTN);
		jlbTinhDTN.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbTinhDTN.setForeground(new Color(255, 38, 0));
		jlbTinhDTN.setFont(new Font("Arial", Font.BOLD, 22));
		
		JLabel lblNewLabel_1 = new JLabel("Năm");
		lblNewLabel_1.setBounds(20, 49, 34, 16);
		jpnTongDoanhThu.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tháng");
		lblNewLabel_1_1.setBounds(188, 49, 61, 16);
		jpnTongDoanhThu.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Ngày");
		lblNewLabel_1_2.setBounds(354, 49, 61, 16);
		jpnTongDoanhThu.add(lblNewLabel_1_2);
		
	    LocalDateTime current = LocalDateTime.now();
	    DateTimeFormatter formatterNam = DateTimeFormatter.ofPattern("yyyy");
	    String nam = current.format(formatterNam);
		
		JLabel jlbNam = new JLabel(nam);
		jlbNam.setForeground(new Color(4, 50, 255));
		jlbNam.setBounds(66, 49, 34, 16);
		jpnTongDoanhThu.add(jlbNam);
		
		DateTimeFormatter formatterThang = DateTimeFormatter.ofPattern("MM");
	    String thang = current.format(formatterThang);
		
		JLabel jlbThang = new JLabel(thang);
		jlbThang.setForeground(new Color(4, 50, 255));
		jlbThang.setBounds(242, 49, 34, 16);
		jpnTongDoanhThu.add(jlbThang);
		
		DateTimeFormatter formatterNgay = DateTimeFormatter.ofPattern("dd");
	    String ngay = current.format(formatterNgay);
		
		JLabel jlbNgay = new JLabel(ngay);
		jlbNgay.setForeground(new Color(4, 50, 255));
		jlbNgay.setBounds(401, 49, 34, 16);
		jpnTongDoanhThu.add(jlbNgay);
		
		JPanel jpnDoanhThuNgay = new JPanel();
		jpnDoanhThuNgay.setLayout(null);
		jpnDoanhThuNgay.setBackground(Color.WHITE);
		jpnDoanhThuNgay.setBounds(542, 32, 307, 122);
		panel.add(jpnDoanhThuNgay);
		
		JLabel jlbTongDoanhThu_3 = new JLabel("Doanh thu tìm kiếm");
		jlbTongDoanhThu_3.setFont(new Font("Arial", Font.BOLD, 26));
		jlbTongDoanhThu_3.setBounds(10, 10, 276, 31);
		
jpnDoanhThuNgay.add(jlbTongDoanhThu_3);
		
		JLabel jlbTinhDTN_1 = new JLabel("000.000.000");
		jlbTinhDTN_1.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbTinhDTN_1.setForeground(new Color(255, 38, 0));
		jlbTinhDTN_1.setFont(new Font("Arial", Font.BOLD, 22));
		jlbTinhDTN_1.setBounds(146, 50, 140, 25);
		jpnDoanhThuNgay.add(jlbTinhDTN_1);
		
		JLabel lblNewLabel = new JLabel("Từ ngày");
		lblNewLabel.setBounds(30, 279, 60, 16);
		panel.add(lblNewLabel);
		
		JLabel lblnNgy = new JLabel("Đến ngày");
		lblnNgy.setBounds(390, 279, 60, 16);
		panel.add(lblnNgy);
		
		JPanel jpnSanPham = new JPanel();
		jpnSanPham.setLayout(null);
		jpnSanPham.setBackground(Color.WHITE);
		jpnSanPham.setBounds(30, 164, 250, 90);
		panel.add(jpnSanPham);
		
		JLabel lblSnPhm = new JLabel("Sản phẩm");
		lblSnPhm.setFont(new Font("Arial", Font.BOLD, 26));
		lblSnPhm.setBounds(10, 10, 129, 31);
		jpnSanPham.add(lblSnPhm);
		
		String tongProduct = showProduct();
		JLabel jlbTongSanPham = new JLabel(tongProduct);
		jlbTongSanPham.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbTongSanPham.setForeground(new Color(255, 38, 0));
		jlbTongSanPham.setFont(new Font("Arial", Font.BOLD, 22));
		jlbTongSanPham.setBounds(144, 15, 90, 25);
		jpnSanPham.add(jlbTongSanPham);
		
		JButton btnTongSP = new JButton("Xuất file");
		btnTongSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final List<Product> product = getProducts();
		        final String excelFilePath = "C:/exceljava/product.xls";
		        try {
					writeExcel(product, excelFilePath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        JOptionPane.showMessageDialog(panel, "Xuất file thành công  !!!");
			}
		});
		btnTongSP.setBounds(71, 53, 117, 29);
		jpnSanPham.add(btnTongSP);
		
		JPanel jpnNhanVien = new JPanel();
		jpnNhanVien.setLayout(null);
		jpnNhanVien.setBackground(Color.WHITE);
		jpnNhanVien.setBounds(315, 162, 250, 90);
		panel.add(jpnNhanVien);
		
		JLabel lblNhnVin = new JLabel("Nhân Viên");
		lblNhnVin.setFont(new Font("Arial", Font.BOLD, 26));
		lblNhnVin.setBounds(10, 10, 129, 31);
		jpnNhanVien.add(lblNhnVin);
		
		String tongStaff = showStaff();
		JLabel jlbTongNhanVien = new JLabel(tongStaff);
		jlbTongNhanVien.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbTongNhanVien.setForeground(new Color(255, 38, 0));
		jlbTongNhanVien.setFont(new Font("Arial", Font.BOLD, 22));
		jlbTongNhanVien.setBounds(144, 15, 90, 25);
		jpnNhanVien.add(jlbTongNhanVien);
		
		JLabel lblNewLabel_2 = new JLabel("Sản phẩm bán chạy");
		lblNewLabel_2.setForeground(new Color(12, 129, 160));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_2.setBounds(40, 317, 240, 29);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Sản phẩm sắp hết");
		lblNewLabel_2_1.setForeground(new Color(255, 38, 0));
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(460, 317, 154, 29);
		panel.add(lblNewLabel_2_1);
		
		JPanel jpnNhanVien_1 = new JPanel();
		jpnNhanVien_1.setLayout(null);
		jpnNhanVien_1.setBackground(Color.WHITE);
		jpnNhanVien_1.setBounds(599, 166, 250, 90);
		panel.add(jpnNhanVien_1);
		
		JLabel lblnHng = new JLabel("Đơn hàng");
		lblnHng.setFont(new Font("Arial", Font.BOLD, 26));
		lblnHng.setBounds(10, 10, 129, 31);
		jpnNhanVien_1.add(lblnHng);
		
		String tongInvoice = showOrder();
		JLabel jlbDonHang = new JLabel(tongInvoice);
		jlbDonHang.setHorizontalAlignment(SwingConstants.RIGHT);
		jlbDonHang.setForeground(new Color(255, 38, 0));
		jlbDonHang.setFont(new Font("Arial", Font.BOLD, 22));
		jlbDonHang.setBounds(144, 15, 90, 25);
		jpnNhanVien_1.add(jlbDonHang);
		
		//table san pham ban chay
		table_product = new JTable();
		table_product.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null}, {null, null, null, null}
			},
			new String[] {
					"Mã SP", "Tên sản phẩm", "Số lượng", "Đơn giá"
			}
		));
		table_product.setBounds(30, 320, 820, 300);
		JTableHeader header = table_product.getTableHeader();
		header.setBackground(Color.yellow);
		
		tableModel = (DefaultTableModel) table_product.getModel();
		
		JScrollPane jpnSanPhamBanChay = new JScrollPane(table_product);
		jpnSanPhamBanChay.setBounds(30, 345, 400, 220);
		jpnSanPhamBanChay.setViewportView(table_product);
		panel.add(jpnSanPhamBanChay);
		panel.setVisible(true);
		
		loadProduct();
		showAll();
		
		// table san pham sap het
		table_product1 = new JTable();
		table_product1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null}, {null, null, null, null}
			},
			new String[] {
					"Mã SP", "Tên sản phẩm", "Số lượng", "Đơn giá"
			}
		));
		table_product1.setBounds(30, 320, 820, 300);
		
		JTableHeader header1 = table_product1.getTableHeader();
		header1.setBackground(Color.red);
		
		tableModel = (DefaultTableModel) table_product1.getModel();
		
		JScrollPane jpnSanPhamSapHet = new JScrollPane(table_product1);
		jpnSanPhamSapHet.setBounds(449, 345, 400, 220);
		panel.add(jpnSanPhamSapHet);
		
		loadProductOver();
		showAll();
		
		JDateChooser date_start = new JDateChooser();
		date_start.setBounds(102, 269, 250, 36);
		panel.add(date_start);
		
		JDateChooser date_end = new JDateChooser();
		date_end.setBounds(462, 269, 250, 36);
		panel.add(date_end); 
		JButton jpnTim = new JButton("Tìm");
//		java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
//		date_start.setDate(date2);
		jpnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				date_start.getDate();
//				System.out.println(""+date_start.getDate());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
				String date_bd = sdf.format(date_start.getDate());
				String date_kt = sdf.format(date_end.getDate());
				String abc = totalsearch(date_bd,date_kt);
				jlbTinhDTN_1.setText(abc);
//				String top = jtfTop.getText();
//				topproduct(top);
				topsale(date_bd,date_kt);
				tableModel = (DefaultTableModel) table_product.getModel();
				showAll();
				
				
			}
		});
		jpnTim.setBounds(732, 274, 117, 29);
		panel.add(jpnTim);
		
		jlbTinhDTT.setText(totalmonth());
		jlbTinhDTN.setText(totalday());
		jlbTinhDT.setText(total());

		//		totalinvoice();
//		jlbTongSanPham.setText(totalinvoice());
		
	}
	public static void writeExcel(List<Product> products, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);
 
        // Create sheet
        Sheet sheet = workbook.createSheet("Products"); // Create sheet with sheet name
 
        int rowIndex = 0;
         
        // Write header
        writeHeader(sheet, rowIndex);
 
        // Write data
        rowIndex++;
        for (Product product : products) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeBook(product, row);
            rowIndex++;
        }
         
        // Write footer
//        writeFooter(sheet, rowIndex);
 
        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
 
        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }
 
    // Create dummy data
 
	 private static List<Product> getProducts() {
	        List<Product> listProduct = new ArrayList<>();
	        try {
				Connection cons = DBConnect.getConnection();
				String sql = "SELECT * FROM product";
				PreparedStatement pr = cons.prepareStatement(sql);
				ResultSet r1 = pr.executeQuery();
				while (r1.next()) {
					Product product = new Product(r1.getInt("maSanPham"),r1.getString("tenSanPham"),r1.getInt("soLuong"),
							r1.getFloat("donGia"),r1.getString("dVT"),r1.getString("moTa"),r1.getInt("maLoai"),
							r1.getString("trangThai"),r1.getInt("maNhaCungCap"));
					listProduct.add(product);
				}
				r1.close();
				cons.close();

			} catch (SQLException e) {
				Logger.getLogger(StaffPanel.class.getName()).log(Level.SEVERE, null, e);
			}
	        return listProduct;
	    }
    // Create workbook
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;
 
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
 
        return workbook;
    }
 
    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);
         
        // Create row
        Row row = sheet.createRow(rowIndex);
         
        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("ID_SP");
        
      
    
        cell = row.createCell(COLUMN_INDEX_TENSANPHAM);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("TÊN SP");
 
        cell = row.createCell(COLUMN_INDEX_SOLUONG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("SỐ LƯỢNG");
 
        cell = row.createCell(COLUMN_INDEX_DONGIA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("ĐƠN GIÁ");
 
        cell = row.createCell(COLUMN_INDEX_DVT);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("ĐƠN VỊ TÍNH");
        
        cell = row.createCell(COLUMN_INDEX_MOTA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("MÔ TẢ");

        cell = row.createCell(COLUMN_INDEX_MALOAI);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("MÃ LOẠI");
        
        cell = row.createCell(COLUMN_INDEX_TRANGTHAI);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("TRẠNG THÁI");
        
        cell = row.createCell(COLUMN_INDEX_MANHACUNGCAP);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("MÃ NHÀ CUNG CẤP");
    }
 
    // Write data
    private static void writeBook(Product product, Row row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");
             
            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
         
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(product.getMaSanPham());
 
        cell = row.createCell(COLUMN_INDEX_TENSANPHAM);
        cell.setCellValue(product.getTenSanPham());
 
        cell = row.createCell(COLUMN_INDEX_SOLUONG);
        cell.setCellValue(product.getSoLuong());
        
        cell = row.createCell(COLUMN_INDEX_DONGIA);
        cell.setCellValue(product.getDonGia());
        cell.setCellStyle(cellStyleFormatNumber);

         
     
        cell = row.createCell(COLUMN_INDEX_DVT);
        cell.setCellValue(product.getdVT());
        
        cell = row.createCell(COLUMN_INDEX_MOTA);
        cell.setCellValue(product.getMoTa());
        
        cell = row.createCell(COLUMN_INDEX_MALOAI);
        cell.setCellValue(product.getMaLoai());
        
        cell = row.createCell(COLUMN_INDEX_TRANGTHAI);
        cell.setCellValue(product.getTrangThai());
        
        cell = row.createCell(COLUMN_INDEX_MANHACUNGCAP);
        cell.setCellValue(product.getMaNhaCungCap());

//        int currentRow = row.getRowNum() + 1;
//        String columnPrice = CellReference.convertNumToColString(COLUMN_INDEX_PRICE);
//        String columnQuantity = CellReference.convertNumToColString(COLUMN_INDEX_QUANTITY);
//        cell.setCellFormula(columnPrice + currentRow + "*" + columnQuantity + currentRow);
    }
 
    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
    	org.apache.poi.ss.usermodel.Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman"); 
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color
 
        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
    // Write footer
//    private static void writeFooter(Sheet sheet, int rowIndex) {
//        // Create row
//        Row row = sheet.createRow(rowIndex);
//        Cell cell = row.createCell(COLUMN_INDEX_TOTAL, CellType.FORMULA);
//        cell.setCellFormula("SUM(E2:E6)");
//    }
     
    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
     
    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
 

	private void loadProduct() {
		productList.clear();
		try {
			Connection cons = DBConnect.getConnection();
//			String sql = "SELECT Product.*,`Category`.`maLoai` idCategory, Provider.`maNhaCungCap` idProvider  from Product "
//					+ "INNER JOIN Category ON Product.`maLoai` = Category.`maLoai` INNER JOIN Provider "
//					+ "ON Product.`maNhaCungCap` = Provider.`maNhaCungCap`";
			String sql = "select product.*, sum(invoicedetail.soLuong) as SL from product inner join invoicedetail on invoicedetail.maSanPham =product.maSanPham WHERE product.trangThai = 'true' group by product.maSanPham order by sum(invoicedetail.soLuong) desc";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while(r1.next()) {
				Product product = new Product(r1.getInt("maSanPham"),r1.getString("tenSanPham"),r1.getInt("SL"),
						r1.getFloat("donGia"),r1.getString("dVT"),r1.getString("moTa"),r1.getInt("maLoai"),
						r1.getString("trangThai"),r1.getInt("maNhaCungCap"));
				productList.add(product);
			
			}
			r1.close();
		 	cons.close();
		} catch (SQLException e) {
			Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private void topproduct(String top)
	{
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT TOP '" + top + "'  FROM invoice ";
			
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while(r1.next()) {
				Product product = new Product(r1.getInt("maSanPham"),r1.getString("tenSanPham"),r1.getInt("soLuong"),
						r1.getFloat("donGia"),r1.getString("dVT"),r1.getString("moTa"),r1.getInt("idCategory"),
						r1.getString("trangThai"),r1.getInt("idProvider"));
				productList.add(product);
			
			}
			r1.close();
		 	cons.close();
		} catch (SQLException e) {
			Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	private void loadProductOver() {
		productList.clear();
		try {
			Connection cons = DBConnect.getConnection(); 
			String sql = "select product.*,category.maLoai idCategory, provider.maNhaCungCap idProvider  from product INNER JOIN category 	"
					+ "ON  product.maLoai = category.maLoai INNER JOIN provider 	"
					+ "ON product.maNhaCungCap = provider.maNhaCungCap AND product.soLuong < 5 "+" WHERE product.trangThai = 'true'";
			
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while(r1.next()) {
				Product product = new Product(r1.getInt("maSanPham"),r1.getString("tenSanPham"),r1.getInt("soLuong"),
						r1.getFloat("donGia"),r1.getString("dVT"),r1.getString("moTa"),r1.getInt("idCategory"),
						r1.getString("trangThai"),r1.getInt("idProvider"));
				productList.add(product);
			
			}
			r1.close();
		 	cons.close();
		} catch (SQLException e) {
			Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	private void showAll(){
		tableModel.setRowCount(0);
		for(Product product : productList) 
			tableModel.addRow(new Object[] {
				product.getMaSanPham(),
				product.getTenSanPham(),
				product.getSoLuong(),
				product.getDonGia(),
			});
	}
	
	private String showStaff() {
		String tong = null;
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT COUNT(*) FROM Staff";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				tong = r1.getString(1);
			}
			r1.close();
			cons.close();
			return tong;
		} catch (SQLException e) {
			Logger.getLogger(DashboardPanel.class.getName()).log(Level.SEVERE, null, e);
		}
		return tong;
	}
	
	private String showProduct() {
		String tong = null;
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT COUNT(*) FROM Product"+" WHERE product.trangThai = 'true'";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				tong = r1.getString(1);
			}
			r1.close();
			cons.close();
			return tong;
		} catch (SQLException e) {
			Logger.getLogger(DashboardPanel.class.getName()).log(Level.SEVERE, null, e);
		}
		return tong;
	}
	
	private String showOrder() {
		String tong = null;
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT COUNT(*) FROM Invoice";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				tong = r1.getString(1);
			}
			r1.close();
			cons.close();
			return tong;
		} catch (SQLException e) {
			Logger.getLogger(DashboardPanel.class.getName()).log(Level.SEVERE, null, e);
		}
		return tong;
	}
	private String total()	
	{
		String tt = null;
		try {
			Connection cons = DBConnect.getConnection();
//			String sql = "SELECT COUNT(maHoaDon)  FROM invoice  where ngayBan >= '"+date_bd+"' and  ngayBan <= '"+date_kt+"'" ;
			String sql = "SELECT SUM(tongTien)  FROM invoice  ";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				tt = r1.getString(1);
			}
			r1.close();
			cons.close();
		
		} catch (SQLException e) {
			Logger.getLogger(DashboardPanel.class.getName()).log(Level.SEVERE, null, e);
		}
		return tt;
		
	}
	private String totalmonth()	
	{
		String tt = null;
		try {
			Connection cons = DBConnect.getConnection();
			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
			String formatted = date.format(formatter);
			System.out.println(""+formatted);
			String sql = "SELECT SUM(tongTien)  FROM invoice  where Month(ngayBan) = '"+formatted+"'" ;
//			String sql = "SELECT SUM(tongtien)  FROM invoice  ";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				tt = r1.getString(1);
			}
			r1.close();
			cons.close();
		
		} catch (SQLException e) {
			Logger.getLogger(DashboardPanel.class.getName()).log(Level.SEVERE, null, e);
		}
		System.out.println(""+tt);

		return tt;

	}
	private String totalday()	
	{
		String tt = null;
		try {
			Connection cons = DBConnect.getConnection();
//			String sql = "SELECT COUNT(maHoaDon)  FROM invoice  where ngayBan >= '"+date_bd+"' and  ngayBan <= '"+date_kt+"'" ;
			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
			String formatted = date.format(formatter);
			System.out.println(""+formatted);
			String sql = "SELECT SUM(tongTien)  FROM invoice  where Day(ngayBan) = '"+formatted+"'" ;
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				tt = r1.getString(1);
			}
			r1.close();
			cons.close();
		
		} catch (SQLException e) {
			Logger.getLogger(DashboardPanel.class.getName()).log(Level.SEVERE, null, e);
		}
		return tt;
		
	}
	private void topsale(String date_bd , String date_kt)
	{
		productList.clear();
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "select  product.* , sum(invoicedetail.soLuong) AS SL from product inner join invoicedetail on invoicedetail.maSanPham = product.maSanPham inner join invoice on invoice.maHoaDon = invoicedetail.maHoaDon WHERE ngayBan between '"+date_bd+"' and   '"+date_kt+"' group by product.maSanPham order by sum(invoicedetail.soLuong) DESC";
//			String sql = "SELECT SUM(tongtien)  FROM invoice  where ngayBan between '"+date_bd+"' and   '"+date_kt+"'" ;
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while(r1.next()) {
				Product product = new Product(r1.getInt("maSanPham"),r1.getString("tenSanPham"),r1.getInt("SL"),r1.getFloat("donGia"),r1.getString("dVT"),r1.getString("moTa"),r1.getInt("maLoai"),r1.getString("trangThai"),r1.getInt("maNhaCungCap"));
				productList.add(product);

			}
			r1.close();
			cons.close();
		
		} catch (SQLException e) {
			Logger.getLogger(DashboardPanel.class.getName()).log(Level.SEVERE, null, e);
		}
		
	}
	private String totalsearch(String date_bd , String date_kt)	
	{
		String tt = null;
		try {
			Connection cons = DBConnect.getConnection();
//			String sql = "SELECT COUNT(maHoaDon)  FROM invoice  where ngayBan >= '"+date_bd+"' and  ngayBan <= '"+date_kt+"'" ;
			String sql = "SELECT SUM(tongtien)  FROM invoice  where ngayBan between '"+date_bd+"' and   '"+date_kt+"'" ;

			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				tt = r1.getString(1);
			}
			r1.close();
			cons.close();
		
		} catch (SQLException e) {
			Logger.getLogger(DashboardPanel.class.getName()).log(Level.SEVERE, null, e);
		}
		return tt;
		
	}
//	
	private void setDefaultCloseOperation(int exitOnClose) {}
	private void initComponet() {}
}