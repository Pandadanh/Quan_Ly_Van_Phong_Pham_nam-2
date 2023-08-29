package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import DAO.ClassTableModel;
import DAO.DBConnect;
import DAO.ProductDao;
import DAO.ProductDaoImpl;
import DAO.add_account;
import DTO.Category;
import DTO.Product;
import DTO.Provider;
import DTO.Staff;
import Import.ExcelReaderExample;

import javax.swing.border.BevelBorder;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import BLL.CategoryManagerController;
import BLL.ProductManagerController;
import BLL.check_action;
import BLL.check_input;
import DAO.DBConnect;
import DTO.Category;
import DTO.Product;
import DTO.Provider;

public class ProductPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	List<Category> categoryList = new ArrayList<>();
	List<Provider> providerList = new ArrayList<>();
	List<Product> productList = new ArrayList<>();
	DefaultTableModel tableModel;
	private JPanel contentPane;
	private JTextField jtfTenSanPham;
	private JTextField jtfDonGia;
	private JComboBox jtfTrangThai;
	private JTextField jtfDVT;
	private JTextField jtfMoTa;
	private JTable table_product;
	private int index = -1;
	private int id;

//	public ProductPanel() {
//		setLayout(null);
//		
//		jtfSearch = new JTextField();
//		jtfSearch.setBounds(50, 20, 250, 50);
//		add(jtfSearch);
//		jtfSearch.setColumns(10);
//		
//		JButton btnAdd = new JButton("+ Add");
//		btnAdd.setFont(new Font("Arial", Font.BOLD, 18));
//		btnAdd.setForeground(new Color(255, 255, 255));
//		btnAdd.setBorder(null);
//		btnAdd.setOpaque(true);
//		btnAdd.setBackground(new Color(12, 129, 160));
//		btnAdd.setBounds(730, 28, 120, 36);
//		add(btnAdd);
//		
//		JPanel jpnView = new JPanel();
//		jpnView.setBounds(50, 90, 800, 552);
//		add(jpnView);
//		
//		initComponet();
//		ProductManagerController controller = new ProductManagerController(jpnView, btnAdd, jtfSearch);
//		controller.setDataToModel();
//		controller.setEvent();
//	}
//
//	private void initComponet() {
//		// TODO Auto-generated method stub
//		
//	}
	public ProductPanel(int id_user) {
		this.id = id_user;
		initComponet();
//		tableModel = (DefaultTableModel) table_product.getModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 672);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 880, 652);
		panel.setBorder(new TitledBorder(null, "Nh\u1EADp s\u1EA3n ph\u1EA9m ", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		add(panel);
		panel.setLayout(null);

		JLabel lbl_tensanpham = new JLabel("Tên sản phẩm");
		lbl_tensanpham.setBounds(31, 47, 83, 16);
		panel.add(lbl_tensanpham);

		jtfTenSanPham = new JTextField();
		jtfTenSanPham.setBounds(118, 46, 220, 26);
		panel.add(jtfTenSanPham);
		jtfTenSanPham.setColumns(10);

		JLabel lbl_mancc = new JLabel("Tên NCC");
		lbl_mancc.setBounds(385, 49, 60, 13);
		panel.add(lbl_mancc);

		JLabel lbl_dongia = new JLabel("Đơn Giá");
		lbl_dongia.setBounds(33, 152, 60, 13);
		panel.add(lbl_dongia);

		jtfDonGia = new JTextField();
		jtfDonGia.setColumns(10);
		jtfDonGia.setBounds(118, 145, 220, 26);
		panel.add(jtfDonGia);

		jtfTrangThai = new JComboBox();
		jtfTrangThai.setBounds(118, 188, 220, 26);
		panel.add(jtfTrangThai);

		String list_trangthai[] = new String[2];
		list_trangthai[0] = "lock";
		list_trangthai[1] = "true";
		DefaultComboBoxModel<Object> comboBoxModel11 = (DefaultComboBoxModel) jtfTrangThai.getModel();
		for (String row : list_trangthai) {

			comboBoxModel11.addElement(row);
		}

		JLabel lbl_trangthai = new JLabel("Trạng Thái");
		lbl_trangthai.setBounds(31, 193, 77, 16);
		panel.add(lbl_trangthai);

//		JComboBox providerName = new JComboBox();
//		providerName.setBounds(118, 80, 96, 21);
//		panel.add(providerName);

		JLabel lbl_maloai = new JLabel("Mã Loại");
		lbl_maloai.setBounds(31, 102, 45, 13);
		panel.add(lbl_maloai);

		JLabel lbl_dvt = new JLabel("DVT");
		lbl_dvt.setBounds(385, 102, 45, 13);
		panel.add(lbl_dvt);

		JLabel lbl_mota = new JLabel("Mô Tả");
		lbl_mota.setBounds(385, 152, 45, 13);
		panel.add(lbl_mota);

		jtfDVT = new JTextField();
		jtfDVT.setColumns(10);
		jtfDVT.setBounds(461, 95, 220, 26);
		panel.add(jtfDVT);

		jtfMoTa = new JTextField();
		jtfMoTa.setColumns(10);
		jtfMoTa.setBounds(461, 146, 220, 26);
		panel.add(jtfMoTa);

//		JComboBox categoryName = new JComboBox();
//		categoryName.setBounds(461, 45, 96, 21);
//		panel.add(categoryName);

		initComponet();
		listCategory();
		listProvider();

		JComboBox categoryName = new JComboBox();
		categoryName.setBounds(118, 95, 220, 26);
		panel.add(categoryName);

		JComboBox providerName = new JComboBox();
		providerName.setEditable(true);
		providerName.setBounds(461, 43, 220, 26);
		panel.add(providerName);
//		JComboBox categoryName = new JComboBox();
//		categoryName.setBounds(605, 87, 194, 43);
//		panel_1.add(categoryName);
//		
//		JComboBox providerName = new JComboBox();
//		providerName.setBounds(178, 176, 200, 40);
//		panel_1.add(providerName);
//		DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) categoryName.getModel();
//		comboBoxModel.removeAllElements();
		DefaultComboBoxModel<Object> comboBoxModel = (DefaultComboBoxModel) categoryName.getModel();

		for (Category category : categoryList) {
			comboBoxModel.addElement(category.getTenLoai());
		}

		DefaultComboBoxModel<Object> comboBoxModel1 = (DefaultComboBoxModel) providerName.getModel();
		comboBoxModel1.removeAllElements();
		for (Provider provider : providerList) {
			comboBoxModel1.addElement(provider.getTenNhaCungCap());
		}

		JButton btn_add = new JButton("ADD");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int level = 1;
				level = add_account.add(id).getLevel();
				if (check_action.check_action_user_by_level(level, 6, "add") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {

					tableModel = (DefaultTableModel) table_product.getModel();
					int index = 0;
					if (jtfTenSanPham.getText().isEmpty()) {
						JOptionPane.showMessageDialog(panel, "Chưa nhập tên sản phẩm ");
						index = -1;
					} else {
						if (!check_input.checkSpecialCharacters(jtfTenSanPham.getText())) {
							JOptionPane.showMessageDialog(panel, "Tên sản phẩm không sử dụng kí tự đặc biệt");
						}
					}
					if (jtfDonGia.getText().isEmpty()) {
						JOptionPane.showMessageDialog(panel, "Chưa đơn giá ");
						index = -1;
					} else {
						if (!check_num(jtfDonGia.getText())) {
							JOptionPane.showMessageDialog(panel, "Sai đơn giá");

							index = -1;
						}
					}
					if (jtfDVT.getText().isEmpty()) {
						JOptionPane.showMessageDialog(panel, "Chưa đơn vị tính");
						index = -1;
					}
					if (jtfMoTa.getText().isEmpty()) {
						JOptionPane.showMessageDialog(panel, "Chưa mô tả");
						index = -1;
					}
					if (index == 0) {
						String tensp = jtfTenSanPham.getText();
						int sl = 0;
						String dvt = jtfDVT.getText();
						String dongia = jtfDonGia.getText();
						String mota = jtfMoTa.getText();
						int trangthai = jtfTrangThai.getSelectedIndex();
						String ttString = "";
						if (trangthai == 1) {
							ttString = "true";
						} else {
							ttString = "lock";
						}
						System.out.println("" + ttString);
						Category category = categoryList.get(categoryName.getSelectedIndex());
						int idCategory = category.getMaLoai();
						Provider provider = providerList.get(providerName.getSelectedIndex());
						int idProvider = provider.getMaNhaCungCap();
						ProductDao.insert(tensp, dvt, dongia, mota, ttString, sl, idCategory, idProvider);
						loadProduct();
						showAll();
						jtfTenSanPham.setText("");
						jtfDVT.setText("");
						jtfDonGia.setText("");
						jtfMoTa.setText("");
						jtfTrangThai.setSelectedIndex(1);

						JOptionPane.showMessageDialog(panel, "Thêm sản phẩm thành công!!!");
					}

				}
			}
		});
		btn_add.setBounds(700, 23, 120, 36);
		btn_add.setFont(new Font("Arial", Font.BOLD, 18));
		btn_add.setForeground(new Color(255, 255, 255));
		btn_add.setBorder(null);
		btn_add.setOpaque(true);
		btn_add.setBackground(new Color(12, 129, 160));
		panel.add(btn_add);

		JButton btn_update = new JButton("UPDATE");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int level = 1;
				level = add_account.add(id).getLevel();
				if (check_action.check_action_user_by_level(level, 6, "update") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {
					int selectedIndex = table_product.getSelectedRow();
					if (selectedIndex >= 0) {

						Product product = productList.get(selectedIndex);
						int index = 0;
						if (jtfTenSanPham.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập tên");
							index = -1;
						} else {
							if (!check_input.checkSpecialCharacters(jtfTenSanPham.getText())) {
								JOptionPane.showMessageDialog(panel, "Tên sản phẩm không sử dụng kí tự đặc biệt");
							}
						}
						if (jtfDonGia.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa đơn giá ");
							index = -1;
						} else {
							if (!check_num(jtfDonGia.getText())) {
								JOptionPane.showMessageDialog(panel, "Sai đơn giá");

								index = -1;
							}
						}
						if (jtfDVT.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa đơn vị tính");
							index = -1;
						}
						if (jtfMoTa.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa mô tả");
							index = -1;
						}
						if (index == 0) {
							int option = JOptionPane.showConfirmDialog(panel, "Bạn có muốn update sản phẩm này !!!");
							if (option == 0) {

								int masp = product.getMaSanPham();
								String tensp = jtfTenSanPham.getText();
								int sl = 0;
								String dvt = jtfDVT.getText();
								String dongia = jtfDonGia.getText();
								String mota = jtfMoTa.getText();
								int trangthai = jtfTrangThai.getSelectedIndex();
								String ttString = "";
								if (trangthai == 1) {
									ttString = "true";
								} else {
									ttString = "lock";
								}
								Category category1 = categoryList.get(categoryName.getSelectedIndex());
								int idCategory = category1.getMaLoai();
								Provider provider1 = providerList.get(providerName.getSelectedIndex());
								int idProvider = provider1.getMaNhaCungCap();
								int maloai = -1;
								int mancc = -1;
								for (Category category : categoryList) {
									if (category.getTenLoai() == categoryName.getSelectedItem()) {
										maloai = category.getMaLoai();
									}
								}
								for (Provider provider : providerList) {
									if (provider.getTenNhaCungCap() == providerName.getSelectedItem()) {
										mancc = provider.getMaNhaCungCap();
									}
								}

								ProductDao.update(masp, tensp, dvt, dongia, mota, ttString, sl, maloai, mancc);
								loadProduct();
								showAll();
								jtfTenSanPham.setText("");
								jtfDVT.setText("");
								jtfDonGia.setText("");
								jtfMoTa.setText("");
								jtfTrangThai.setSelectedIndex(1);
								JOptionPane.showMessageDialog(panel, "Update sản phẩm thành công!!!");
							}

						}
					}
				}
			}
		});
		btn_update.setBounds(700, 79, 120, 36);
		btn_update.setFont(new Font("Arial", Font.BOLD, 18));
		btn_update.setForeground(new Color(255, 255, 255));
		btn_update.setBorder(null);
		btn_update.setOpaque(true);
		btn_update.setBackground(new Color(12, 129, 160));
		panel.add(btn_update);

		JButton btn_delete = new JButton("DELETE");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int level = 1;
				level = add_account.add(id).getLevel();
				if (check_action.check_action_user_by_level(level, 6, "delete") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {
					int selectedIndex = table_product.getSelectedRow();
					if (selectedIndex >= 0) {

						Product product = productList.get(selectedIndex);
						int option = JOptionPane.showConfirmDialog(panel, "Bạn Có Muốn Xóa Sản Phẩm Này !!!");
						if (option == 0) {
							ProductDao.delete(product.getMaSanPham());
							loadProduct();
							showAll();
						}
					}
				}
			}
		});
		btn_delete.setBounds(700, 138, 120, 36);
		btn_delete.setFont(new Font("Arial", Font.BOLD, 18));
		btn_delete.setForeground(new Color(255, 255, 255));
		btn_delete.setBorder(null);
		btn_delete.setOpaque(true);
		btn_delete.setBackground(new Color(12, 129, 160));
		panel.add(btn_delete);

		table_product = new JTable();
		table_product.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null }, },
				new String[] { "Mã SP", "Mã loại", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Đơn giá", "Mô tả",
						"Trạng thái", "Mã NCC" }));
		table_product.setBounds(30, 320, 820, 300);

//		panel.add(table_product);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 291, 820, 278);
		scrollPane.setViewportView(table_product);
		panel.add(scrollPane);
		panel.setVisible(true);

		JButton btnTimKiem = new JButton("SEARCH");

		btnTimKiem.setBounds(701, 191, 120, 36);
		btnTimKiem.setFont(new Font("Arial", Font.BOLD, 18));
		btnTimKiem.setForeground(new Color(255, 255, 255));
		btnTimKiem.setBorder(null);
		btnTimKiem.setOpaque(true);
		btnTimKiem.setBackground(new Color(12, 129, 160));
		panel.add(btnTimKiem);

		JButton btn_add_1 = new JButton("IMPORT");
		btn_add_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Chọn file để import");

				int userSelection = fileChooser.showOpenDialog(null);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					String filePath = fileChooser.getSelectedFile().getAbsolutePath();
					productList = ExcelReaderExample.readDataFromExcel(filePath);
					showAll();

				}
			}
		});
		btn_add_1.setOpaque(true);
		btn_add_1.setForeground(Color.WHITE);
		btn_add_1.setFont(new Font("Arial", Font.BOLD, 18));
		btn_add_1.setBorder(null);
		btn_add_1.setBackground(new Color(12, 129, 160));
		btn_add_1.setBounds(700, 239, 120, 36);
		panel.add(btn_add_1);

		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] options = { "maSanPham", "tenSanPham", "soLuong", "donGia", "dVT", "moTa", "maLoai",
						"trangThai", "maNhaCungCap" };
				String selectedOption = (String) JOptionPane.showInputDialog(panel, "Chọn cột để tìm kiếm:", "Tìm kiếm",
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (selectedOption != null && !selectedOption.equals("")) {
					String keyword = "";

					if (selectedOption.equals("trangThai")) {
						String[] options1 = { "all", "true", "false" };

						String selectedOption1 = (String) JOptionPane.showInputDialog(panel, "Chọn trạng thái:",
								"Tìm kiếm", JOptionPane.PLAIN_MESSAGE, null, options1, options1[0]);

						keyword = selectedOption1;

					} else {
						keyword = JOptionPane.showInputDialog(panel, "Nhập từ khóa cần tìm:", "Tìm kiếm",
								JOptionPane.PLAIN_MESSAGE);
					}

					if (keyword != null && !keyword.equals("")) {
//								

						if (keyword.equals("all")) {
							loadProduct_all();
							showAll();
						} else {
							productList = searchStaff(selectedOption, keyword);

							if (productList.size() > 0) {

								tableModel.setRowCount(0);

								for (Product row : productList) {
									tableModel.addRow(new Object[] { row.getTenSanPham(), row.getMaLoai(),
											row.getTenSanPham(), row.getdVT(), row.getSoLuong(), row.getDonGia(),
											row.getMoTa(), row.getTrangThai(), row.getMaNhaCungCap()

									});
								}

							} else {
								JOptionPane.showMessageDialog(panel, "Không tìm thấy kết quả nào!");
							}
						}

					} else
						JOptionPane.showMessageDialog(panel, "error");
				}

			}
		});
		tableModel = (DefaultTableModel) table_product.getModel();

		loadProduct();
		showAll();

		panel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (!table_product.getBounds().contains(e.getPoint())) {
					loadProduct();
					showAll();
					jtfDonGia.setText("");
					jtfDVT.setText("");
					jtfMoTa.setText("");
					jtfTenSanPham.setText("");

					jtfTrangThai.setSelectedIndex(1);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		table_product.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				index = table_product.getSelectedRow();
				if (index >= 0) {
					Product product = productList.get(index);
					jtfTenSanPham.setText(product.getTenSanPham().toString());

					int sl = product.getSoLuong();
					String vl = String.valueOf(sl);

					float dongia = product.getDonGia();
					String dg = String.valueOf(dongia);

					String trangthai = product.getTrangThai();
					String tt = String.valueOf(trangthai);

					jtfDVT.setText(product.getdVT().toString());
					jtfDonGia.setText(dg.toString());
					jtfMoTa.setText(product.getMoTa().toString());

					String trangthai1 = product.getTrangThai();
					if (trangthai1.equals("true")) {
						jtfTrangThai.setSelectedIndex(1);
					} else {
						jtfTrangThai.setSelectedIndex(0);
					}

					DefaultComboBoxModel<String> comboBoxModel = (DefaultComboBoxModel<String>) categoryName.getModel();
					comboBoxModel.removeAllElements();
					int ml = product.getMaLoai();

					for (Category category : categoryList) {
						if (category.getMaLoai() == ml) {
							comboBoxModel.addElement(category.getTenLoai());
						}
					}
					for (Category category : categoryList) {
						if (category.getMaLoai() != ml) {
							comboBoxModel.addElement(category.getTenLoai());
						}
					}
					//
					int ncc = product.getMaNhaCungCap();
					DefaultComboBoxModel<String> comboBoxModel1 = (DefaultComboBoxModel<String>) providerName
							.getModel();
					comboBoxModel1.removeAllElements();
					for (Provider provider : providerList) {
						if (provider.getMaNhaCungCap() == ncc) {
							comboBoxModel1.addElement(provider.getTenNhaCungCap());
						}
					}
					for (Provider provider : providerList) {
						if (provider.getMaNhaCungCap() != ncc) {
							comboBoxModel1.addElement(provider.getTenNhaCungCap());
						}
					}

				} else {
					System.out.println("dsadok");
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}

	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub

	}

	public void search(String item) {
		productList.clear();
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT * FROM `product` where tenSanPham LIKE '%" + item + "%'";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {

				Product product = new Product(r1.getInt("maSanPham"), r1.getString("tenSanPham"), r1.getInt("soLuong"),
						r1.getFloat("donGia"), r1.getString("dVT"), r1.getString("moTa"), r1.getInt("maLoai"),
						r1.getString("trangThai"), r1.getInt("maNhaCungCap"));
				productList.add(product);

			}

			pr.close();
			r1.close();
			cons.close();

		} catch (SQLException ex) {
			ex.printStackTrace();

			// Hiển thị JDialog với thông báo lỗi
			JDialog dialog = new JDialog();
			dialog.setTitle("Lỗi");
			dialog.setModal(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel label = new JLabel("Lỗi: " + ex.getMessage());
			dialog.getContentPane().add(label);

			dialog.setSize(300, 150);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);

		}

	}

	private void listCategory() {
		categoryList.clear();
		try {

			Connection cons = DBConnect.getConnection();

			String sql = "select * from category where trangthai = 1 ";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				Category category = new Category(r1.getInt("maLoai"), r1.getString("tenLoai"), r1.getInt("trangThai"));
				categoryList.add(category);
			}

			r1.close();

			cons.close();

		} catch (SQLException ex) {
			ex.printStackTrace();

			// Hiển thị JDialog với thông báo lỗi
			JDialog dialog = new JDialog();
			dialog.setTitle("Lỗi");
			dialog.setModal(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel label = new JLabel("Lỗi: " + ex.getMessage());
			dialog.getContentPane().add(label);

			dialog.setSize(300, 150);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);

		}

	}

	private void listProvider() {
		providerList.clear();
		try {

			Connection cons = DBConnect.getConnection();

			String sql = "select * from provider where trangthai = 1  ";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {

				Provider provider = new Provider(r1.getInt("maNhaCungCap"), r1.getString("tenNhaCungCap"),
						r1.getInt("sDT"), r1.getString("email"), r1.getString("diaChi"), r1.getString("ghiChu"),
						r1.getInt("trangThai"));
				providerList.add(provider);

			}

			r1.close();

			cons.close();

		} catch (SQLException ex) {
			ex.printStackTrace();

			// Hiển thị JDialog với thông báo lỗi
			JDialog dialog = new JDialog();
			dialog.setTitle("Lỗi");
			dialog.setModal(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel label = new JLabel("Lỗi: " + ex.getMessage());
			dialog.getContentPane().add(label);

			dialog.setSize(300, 150);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);

		}

	}

	private void loadProduct() {
		productList.clear();
		try {

			Connection cons = DBConnect.getConnection();

			String sql = "SELECT product.*, category.maLoai AS idCategory, provider.maNhaCungCap AS idProvider "
					+ "FROM product " + "INNER JOIN category ON product.maLoai = category.maLoai "
					+ "INNER JOIN provider ON product.maNhaCungCap = provider.maNhaCungCap "
					+ "WHERE product.trangThai != 'false'";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {

				Product product = new Product(r1.getInt("maSanPham"), r1.getString("tenSanPham"), r1.getInt("soLuong"),
						r1.getFloat("donGia"), r1.getString("dVT"), r1.getString("moTa"), r1.getInt("idCategory"),
						r1.getString("trangThai"), r1.getInt("idProvider"));
				productList.add(product);

			}

			r1.close();

			cons.close();

		} catch (SQLException ex) {
			ex.printStackTrace();

			// Hiển thị JDialog với thông báo lỗi
			JDialog dialog = new JDialog();
			dialog.setTitle("Lỗi");
			dialog.setModal(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel label = new JLabel("Lỗi: " + ex.getMessage());
			dialog.getContentPane().add(label);

			dialog.setSize(300, 150);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);

		}

	}

	private void showAll() {
		tableModel.setRowCount(0);

		for (Product product : productList) {
			tableModel.addRow(new Object[] { product.getMaSanPham(), product.getMaLoai(), product.getTenSanPham(),
					product.getdVT(), product.getSoLuong(), product.getDonGia(), product.getMoTa(),
					product.getTrangThai(), product.getMaNhaCungCap()

			});
		}

	}

	private List<Product> searchStaff(String columnName, String keyword) {
		List<Product> result = new ArrayList<>();
		String query;
		PreparedStatement stmt;
		try {
			Connection cons = DBConnect.getConnection();
			if (isNumeric(keyword)) {
				query = "SELECT product.*, category.maLoai AS idCategory, provider.maNhaCungCap AS idProvider "
						+ "FROM product " + "INNER JOIN category ON product.maLoai = category.maLoai "
						+ "INNER JOIN provider ON product.maNhaCungCap = provider.maNhaCungCap " + "WHERE product."
						+ columnName + " = ? ";
				stmt = cons.prepareStatement(query);
				stmt.setInt(1, Integer.parseInt(keyword));
			} else {
				query = "SELECT product.*, category.maLoai AS idCategory, provider.maNhaCungCap AS idProvider "
						+ "FROM product " + "INNER JOIN category ON product.maLoai = category.maLoai "
						+ "INNER JOIN provider ON product.maNhaCungCap = provider.maNhaCungCap " + "WHERE product."
						+ columnName + " LIKE ? ";
				stmt = cons.prepareStatement(query);
				stmt.setString(1, "%" + keyword + "%");
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Product product = new Product(rs.getInt("maSanPham"), rs.getString("tenSanPham"), rs.getInt("soLuong"),
						rs.getFloat("donGia"), rs.getString("dVT"), rs.getString("moTa"), rs.getInt("idCategory"),
						rs.getString("trangThai"), rs.getInt("idProvider"));
				result.add(product);
			}

			stmt.close();
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();

			// Hiển thị JDialog với thông báo lỗi
			JDialog dialog = new JDialog();
			dialog.setTitle("Lỗi");
			dialog.setModal(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel label = new JLabel("Lỗi: " + ex.getMessage());
			dialog.getContentPane().add(label);

			dialog.setSize(300, 150);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);

		}

		return result;
	}

	private void loadProduct_all() {
		productList.clear();
		try {

			Connection cons = DBConnect.getConnection();

			String sql = "SELECT product.*, category.maLoai AS idCategory, provider.maNhaCungCap AS idProvider "
					+ "FROM product " + "INNER JOIN category ON product.maLoai = category.maLoai "
					+ "INNER JOIN provider ON product.maNhaCungCap = provider.maNhaCungCap ";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {

				Product product = new Product(r1.getInt("maSanPham"), r1.getString("tenSanPham"), r1.getInt("soLuong"),
						r1.getFloat("donGia"), r1.getString("dVT"), r1.getString("moTa"), r1.getInt("idCategory"),
						r1.getString("trangThai"), r1.getInt("idProvider"));
				productList.add(product);

			}

			r1.close();

			cons.close();

		} catch (SQLException ex) {
			ex.printStackTrace();

			// Hiển thị JDialog với thông báo lỗi
			JDialog dialog = new JDialog();
			dialog.setTitle("Lỗi");
			dialog.setModal(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			JLabel label = new JLabel("Lỗi: " + ex.getMessage());
			dialog.getContentPane().add(label);

			dialog.setSize(300, 150);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);

		}

	}

	private boolean isNumeric(String str) {
		try {
			int num = Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean check_num(String input) {
		try {
			// Kiểm tra chuỗi có thể chuyển đổi thành số nguyên hay không
			int number = Integer.parseInt(input);
			return number >= 0;
		} catch (NumberFormatException e) {
			try {
				// Kiểm tra chuỗi có thể chuyển đổi thành số thực hay không
				float number = Float.parseFloat(input);
				return number >= 0;
			} catch (NumberFormatException ex) {
				return false;
			}
		}
	}

	private void initComponet() {

	}
}
