package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import DAO.DBConnect;
import DAO.InvoiceDao;
import DTO.ImportDetail;
import DTO.Imports;
import DTO.Invoice;
import DTO.InvoiceDetail;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InvoicePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	List<Invoice> invoiceList = new ArrayList<>();
	List<InvoiceDetail> invoiceDetailList = new ArrayList<>();
	DefaultTableModel tableModel;
	private JTable table_invoice;
	private DefaultTableModel table_model;
	DefaultTableModel tableModelDetail;
	private JTable table_invoiceDetail;
	private DefaultTableModel table_modelDetail;
	private int index = -1;
	private JTextField jtfmaHoaDon;

	public InvoicePanel(int id) {
		initComponet();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 617);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 16, 880, 601);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ho\u00E1 \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel);
		panel.setLayout(null);
		
		table_invoice = new JTable(table_model);
		table_invoice.setLayout(new BorderLayout());
		table_invoice.setBorder(null);
		
		Object data[][] = { { "1", "1", "1", "1", "1"}, { "1", "1", "1", "1", "1", "1"} };
		Object listColumn[] = { "Mã hoá đơn", "Ngày bán","Mã nhân viên", "Mã khách hàng", "Tổng tiền"};
	
		table_invoice.setModel(new DefaultTableModel(data, listColumn));
		table_invoice.setBounds(31, 64, 820, 505);
		JTableHeader header2 = table_invoice.getTableHeader();
		header2.setBackground(Color.yellow);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(31, 84, 799, 228);
		scrollPane.setViewportView(table_invoice);
		panel.add(scrollPane);
		panel.setVisible(true);
		
		tableModel = (DefaultTableModel) table_invoice.getModel();
		
		loadInvoice();
		tableModel.setRowCount(0);
	
		for (Invoice invoice : invoiceList) {
			Date ngay = invoice.getNgayban();
			Time gio = invoice.getNgayBan();
			String ngaygio1 = String.valueOf(ngay);
			String ngaygio2 = String.valueOf(gio);
			String trungian = " - ";
			String ngaygio3 = ngaygio1.concat(trungian);
			String ngaygio = ngaygio3.concat(ngaygio2);
			
			tableModel.addRow(new Object[] {invoice.getMaHoaDon(), ngaygio, invoice.getMaNhanVien(), 
					invoice.getMaKhachHang(), invoice.getTongTien()});
		}
		
		table_invoiceDetail = new JTable(table_modelDetail);
		table_invoiceDetail.setLayout(new BorderLayout());
		table_invoiceDetail.setBorder(null);
		
		Object data1[][] = { { null, null, null, null, null}, { null, null, null, null, null} };
		Object listColumn1[] = { "Mã CTHD", "Mã đon hàng", "Mã sản phẩm", "Số lượng","Thành tiền"};
	
		table_invoiceDetail.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 CTHD", "M\u00E3 \u0111on h\u00E0ng", "M\u00E3 s\u1EA3n ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng", "Th\u00E0nh ti\u1EC1n"
			}
		));
		table_invoiceDetail.setBounds(31, 64, 820, 505);
		JTableHeader header1 = table_invoiceDetail.getTableHeader();
		header1.setBackground(Color.yellow);
		
		tableModelDetail = (DefaultTableModel) table_invoiceDetail.getModel();
		
		JScrollPane scrollPane_1 = new JScrollPane(table_invoiceDetail);
		scrollPane_1.setBorder(null);
		scrollPane_1.setBounds(31, 442, 799, 129);
		panel.add(scrollPane_1);
		
		jtfmaHoaDon = new JTextField();
		jtfmaHoaDon.setColumns(10);
		jtfmaHoaDon.setBounds(102, 379, 112, 30);
		jtfmaHoaDon.enable(false);
		panel.add(jtfmaHoaDon);
		
		JLabel lblNewLabel = new JLabel("Danh sách hóa đơn");
		lblNewLabel.setBounds(35, 61, 108, 13);
		panel.add(lblNewLabel);
		
		JLabel lblChiTitHa = new JLabel("Chi tiết hóa đơn");
		lblChiTitHa.setBounds(31, 419, 112, 13);
		panel.add(lblChiTitHa);
		
		JLabel lblMHan = new JLabel("Mã hóa đơn");
		lblMHan.setBounds(31, 387, 112, 13);
		panel.add(lblMHan);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(btnDelete, "Bạn Có Muốn Xóa Sản Phẩm Này !!!");
				if(option == 0) {
				String mahd = jtfmaHoaDon.getText();
				int mahoadon = Integer.valueOf(mahd);
				InvoiceDao.delete(mahoadon);
				tableModel = (DefaultTableModel) table_invoice.getModel();
				
				loadInvoice();
				tableModel.setRowCount(0);

				for (Invoice invoice : invoiceList) {
					tableModel.addRow(new Object[] {invoice.getMaHoaDon(), invoice.getNgayBan(), invoice.getMaNhanVien(), 
							invoice.getMaKhachHang(), invoice.getTongTien()});
				}
				
				invoiceDetailList.clear();
				tableModelDetail = (DefaultTableModel) table_invoiceDetail.getModel();
				for (InvoiceDetail invoiceDetail : invoiceDetailList) {
					tableModelDetail.addRow(new Object[] {invoiceDetail.getMaChiTietHoaDon(), invoiceDetail.getMaHoaDon(),
							invoiceDetail.getMaSanPham(), invoiceDetail.getSoLuong(), invoiceDetail.getThanhTien()});
				}
				JOptionPane.showMessageDialog(panel, "Xóa hóa đơn thành công  !!!");
				}
			}
		});
		btnDelete.setBounds(224, 379, 104, 30);
		panel.add(btnDelete);
		
		table_invoice.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				index  = table_invoice.getSelectedRow();
				if(index >= 0) {
					Invoice invoice = invoiceList.get(index);
					int ma = invoice.getMaHoaDon();
					
					loadInvoiceDetail(ma);
					tableModelDetail.setRowCount(0);
					String mahd = String.valueOf(ma);
					jtfmaHoaDon.setText(mahd);
					for (InvoiceDetail invoiceDetail : invoiceDetailList) {
						tableModelDetail.addRow(new Object[] {invoiceDetail.getMaChiTietHoaDon(), invoiceDetail.getMaHoaDon(),
								invoiceDetail.getMaSanPham(), invoiceDetail.getSoLuong(), invoiceDetail.getThanhTien()});
					}
				}else
					System.out.println("dsadok");
			}

			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
	}

	private void setDefaultCloseOperation(int exitOnClose) {}
	private void initComponet() {}
	
	private void loadInvoice() {
		invoiceList.clear();
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `Invoice`";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				Invoice invoice = new Invoice(r1.getInt("maHoaDon"),r1.getDate("ngayban"), r1.getTime("ngayBan"),
						r1.getInt("maNhanVien"), r1.getInt("maKhachHang"), r1.getFloat("tongTien"));
				invoiceList.add(invoice);
				System.out.println(""+r1.getDate("ngayban")+ r1.getTime("ngayBan"));
			}
			r1.close();
			cons.close();

		} catch (SQLException e) {
			Logger.getLogger(InvoicePanel.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	private void loadInvoiceDetail(int ma) {
		invoiceDetailList.clear();
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `InvoiceDetail`, `Invoice` WHERE `Invoice`.`maHoaDon`= `InvoiceDetail`.`maHoaDon`"
					+ "AND `InvoiceDetail`.`maHoaDon` = " + ma;
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				InvoiceDetail invoiceDetail = new InvoiceDetail(r1.getInt("maChiTietHoaDon"), r1.getInt("maHoaDon"),
						r1.getInt("maSanPham"), r1.getInt("soLuong"), r1.getFloat("thanhTien"));
				invoiceDetailList.add(invoiceDetail);
			}
			r1.close();
			cons.close();
		}catch (SQLException e) {
			Logger.getLogger(InvoicePanel.class.getName()).log(Level.SEVERE, null, e);
		}
	
	}
}
