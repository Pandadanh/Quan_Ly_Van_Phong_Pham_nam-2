package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import BLL.ProviderManagerController;
import BLL.check_action;
import BLL.check_all;
import BLL.check_input;
import DAO.CategoryDao;
import DAO.DBConnect;
import DAO.ProviderDao;
import DAO.add_account;
import DTO.Category;
import DTO.Provider;
import javax.swing.JLabel;

public class ProviderPanel extends JPanel {

	private String list_trangthai[] = new String[3];
	List<Provider> providerList = new ArrayList<>();
	private TableRowSorter<TableModel> rowSorter = null;
	DefaultTableModel tableModel;
	private JLabel jlMaNhaCungCap;
	private JTextField jtfTenNhaCungCap;
	private JTextField jtfSDT;
	private JTextField jtfEmail;
	private JTextField jtfDiaChi;
	private JTextField jtfGhiChu;
	private JTable table_provider;
	private int index = -1;

	private check_all check_all = new check_all();
	private JComboBox jtfTrangthai;
	private int id;

	public ProviderPanel(int id_user) {
		this.id = id_user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// add thuộc tính

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 880, 652);
		panel.setBorder((Border) new TitledBorder(null, "Thông tin nhà cung cấp", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(null);
		setLayout(null);

		jlMaNhaCungCap = new JLabel();
		jlMaNhaCungCap.setBounds(113, 33, 151, 24);
		panel.add(jlMaNhaCungCap);

		JLabel lblMaNhaCungCap = new JLabel("Mã NCC");
		lblMaNhaCungCap.setBounds(30, 38, 64, 13);
		panel.add(lblMaNhaCungCap);

		jtfTenNhaCungCap = new JTextField();
		jtfTenNhaCungCap.setBounds(113, 77, 151, 24);
		panel.add(jtfTenNhaCungCap);
		jtfTenNhaCungCap.setColumns(10);

		JLabel lblTenNhaCungCap = new JLabel("Tên NCC");
		lblTenNhaCungCap.setBounds(31, 82, 64, 13);
		panel.add(lblTenNhaCungCap);

		jtfSDT = new JTextField();
		jtfSDT.setBounds(113, 124, 151, 24);
		panel.add(jtfSDT);
		jtfSDT.setColumns(10);

		JLabel lbSDT = new JLabel("SĐT");
		lbSDT.setBounds(31, 129, 64, 13);
		panel.add(lbSDT);

		jtfEmail = new JTextField();
		jtfEmail.setBounds(113, 171, 151, 24);
		panel.add(jtfEmail);
		jtfEmail.setColumns(10);

		JLabel lblemail = new JLabel("email");
		lblemail.setBounds(31, 176, 64, 13);
		panel.add(lblemail);

		jtfDiaChi = new JTextField();
		jtfDiaChi.setBounds(428, 33, 151, 24);
		panel.add(jtfDiaChi);
		jtfDiaChi.setColumns(10);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setBounds(330, 38, 56, 13);
		panel.add(lblDiaChi);

		jtfGhiChu = new JTextField();
		jtfGhiChu.setBounds(428, 78, 151, 70);
		panel.add(jtfGhiChu);
		jtfGhiChu.setColumns(10);

		JLabel lblGhiChu = new JLabel("Ghi chú");
		lblGhiChu.setBounds(330, 82, 56, 13);
		panel.add(lblGhiChu);

		jtfTrangthai = new JComboBox();
		jtfTrangthai.setBounds(428, 170, 151, 25);
		panel.add(jtfTrangthai);
		JLabel lblTrangthai = new JLabel("Trạng thái");
		lblTrangthai.setBounds(330, 176, 88, 13);
		panel.add(lblTrangthai);

		list_trangthai[0] = "Off";
		list_trangthai[1] = "On";
		DefaultComboBoxModel<Object> comboBoxModel1 = (DefaultComboBoxModel) jtfTrangthai.getModel();
		for (String row : list_trangthai) {

			comboBoxModel1.addElement(row);
		}

		// thêm nút bấm

		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Arial", Font.BOLD, 18));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBorder(null);
		btnAdd.setOpaque(true);
		btnAdd.setBackground(new Color(12, 129, 160));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int level = 1;
				level = add_account.add(id).getLevel();
				if (check_action.check_action_user_by_level(level, 10, "add") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {
					tableModel = (DefaultTableModel) table_provider.getModel();

					int index = 0;
					if (jtfTenNhaCungCap.getText().isEmpty()) {
						JOptionPane.showMessageDialog(panel, "Chưa nhập tên");
						index = -1;
					} else {

						if (!check_input.checkSpecialCharacters(jtfTenNhaCungCap.getText())) {
							JOptionPane.showMessageDialog(panel, "Tên nhà cung cấp không sử dụng kí tự đặc biệt");
							index = -1;
						}
					}

					if (jtfSDT.getText().isEmpty()) {
						JOptionPane.showMessageDialog(panel, "Chưa nhập số điện thoại");
						index = -1;
					} else {
						if (!check_input.checknumber(jtfSDT.getText())) {
							JOptionPane.showMessageDialog(panel, "Sai cú pháp số điện thoại");
							index = -1;
						} else

						if (!check_input.checkNumberLength(jtfSDT.getText())) {
							JOptionPane.showMessageDialog(panel, "Số điện thoại không đúng");
							System.out.println(" wtf");
							index = -1;
						}
					}
					if (jtfEmail.getText().isEmpty()) {
						JOptionPane.showMessageDialog(panel, "Chưa nhập email");
						index = -1;
					} else {
						if (!check_input.checkEmailFormat(jtfEmail.getText())) {
							JOptionPane.showMessageDialog(panel, "sai email");
							index = -1;
						}
					}
					if (index == 0) {
						int length = providerList.size() + 1;
						String manhacungcap = String.valueOf(length);
						String tennhacungcap = jtfTenNhaCungCap.getText();
						String sdt = jtfSDT.getText();
						String email = jtfEmail.getText();
						String diachi = jtfDiaChi.getText();
						String ghichu = jtfGhiChu.getText();
						int idtrangthai = jtfTrangthai.getSelectedIndex();
						if (check_all.checkDataExist("provider", "tennhacungcap", tennhacungcap)) {
							JOptionPane.showMessageDialog(panel, "Tên loại đã có xin nhập lại !!!");
						} else {
							ProviderDao.insert(manhacungcap, tennhacungcap, sdt, email, diachi, ghichu, idtrangthai);
							loadProvider();
							showAll();
							jtfTenNhaCungCap.setText("");
							jtfSDT.setText("");
							jtfEmail.setText("");
							jtfDiaChi.setText("");
							jtfGhiChu.setText("");
							JOptionPane.showMessageDialog(panel, "Thêm sản phẩm thành công!!!");
						}
					}
				}
			}
		});
		btnAdd.setBounds(674, 33, 120, 36);
		panel.add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int level = 1;
				level = add_account.add(id).getLevel();
				if (check_action.check_action_user_by_level(level, 10, "update") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {
					int selectedIndex = table_provider.getSelectedRow();
					if (selectedIndex >= 0) {

						Provider provider = providerList.get(selectedIndex);
						int index = 0;
						if (jtfTenNhaCungCap.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập tên");
							index = -1;
						} else {

							if (!check_input.checkSpecialCharacters(jtfTenNhaCungCap.getText())) {
								JOptionPane.showMessageDialog(panel, "Tên nhà cung cấp không sử dụng kí tự đặc biệt");
								index = -1;
							}
						}

						if (jtfSDT.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập số điện thoại");
							index = -1;
						} else {
							if (!check_input.checknumber(jtfSDT.getText())) {
								JOptionPane.showMessageDialog(panel, "Sai cú pháp số điện thoại");
								index = -1;
							} else

							if (!check_input.checkNumberLength(jtfSDT.getText())) {
								JOptionPane.showMessageDialog(panel, "Số điện thoại không đúng");
								System.out.println(" wtf");
								index = -1;
							}
						}
						if (jtfEmail.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập email");
							index = -1;
						} else {
							if (!check_input.checkEmailFormat(jtfEmail.getText())) {
								JOptionPane.showMessageDialog(panel, "sai email");
								index = -1;
							}
						}
						if (jtfDiaChi.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập địa chỉ");
							index = -1;
						}
						if (jtfGhiChu.getText().isEmpty()) {
							JOptionPane.showMessageDialog(panel, "Chưa nhập ghi chú");
							index = -1;
						}
						if (index == 0) {
							int option = JOptionPane.showConfirmDialog(panel, "Bạn có muốn update sản phẩm này!!!");
							if (option == 0) {
								String manhacungcap = Integer.toString(provider.getMaNhaCungCap());
								String tennhacungcap = jtfTenNhaCungCap.getText();
								String sdt = jtfSDT.getText();
								String email = jtfEmail.getText();
								String diachi = jtfDiaChi.getText();
								String ghichu = jtfGhiChu.getText();
								int idtrangthai = jtfTrangthai.getSelectedIndex();
								ProviderDao.update(manhacungcap, tennhacungcap, sdt, email, diachi, ghichu,
										idtrangthai);
								loadProvider();
								showAll();
								jtfTenNhaCungCap.setText("");
								jtfSDT.setText("");
								jtfEmail.setText("");
								jtfDiaChi.setText("");
								jtfGhiChu.setText("");
								JOptionPane.showMessageDialog(panel, "Update sản phẩm thành công!!!");
							}
						}
					}
				}
			}
		});
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 18));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBorder(null);
		btnUpdate.setOpaque(true);
		btnUpdate.setBackground(new Color(12, 129, 160));
		btnUpdate.setBounds(674, 162, 120, 36);
		panel.add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int level = 1;
				level = add_account.add(id).getLevel();
				if (check_action.check_action_user_by_level(level, 10, "delete") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {
					int selectedIndex = table_provider.getSelectedRow();
					if (selectedIndex >= 0) {

						Provider provider = providerList.get(selectedIndex);
						int option = JOptionPane.showConfirmDialog(panel, "Bạn Có Muốn Xóa Sản Phẩm Này !!!");
						if (option == 0) {
							ProviderDao.delete(provider.getMaNhaCungCap());
							loadProvider();
							showAll();
						}
					}
				}

			}
		});
		btnDelete.setFont(new Font("Arial", Font.BOLD, 18));
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBorder(null);
		btnDelete.setOpaque(true);
		btnDelete.setBackground(new Color(12, 129, 160));
		btnDelete.setBounds(674, 92, 120, 36);
		panel.add(btnDelete);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Arial", Font.BOLD, 18));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBorder(null);
		btnSearch.setOpaque(true);
		btnSearch.setBackground(new Color(12, 129, 160));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] options = { "maNhaCungCap", "tenNhaCungCap", "sDT", "email", "diaChi", "ghiChu", "trangThai" };
				String selectedOption = (String) JOptionPane.showInputDialog(panel, "Chọn cột để tìm kiếm:", "Tìm kiếm",
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (selectedOption != null && !selectedOption.equals("")) {

					String keyword = JOptionPane.showInputDialog(panel, "Nhập từ khóa cần tìm:", "Tìm kiếm",
							JOptionPane.PLAIN_MESSAGE);
					if (keyword != null && !keyword.equals("")) {

						List<Provider> providerList = searchProvider(selectedOption, keyword);

						if (providerList.size() > 0) {

							tableModel.setRowCount(0);

							for (Provider row : providerList) {
								tableModel.addRow(
										new Object[] { row.getMaNhaCungCap(), row.getTenNhaCungCap(), row.getsDT(),
												row.getEmail(), row.getDiaChi(), row.getGhiChu(), row.getTrangThai() });
							}

						} else {
							JOptionPane.showMessageDialog(panel, "Không tìm thấy kết quả nào!");
						}
					} else
						JOptionPane.showMessageDialog(panel, "error");
				}
			}

		});
		btnSearch.setBounds(674, 225, 120, 36);
		panel.add(btnSearch);

		// tạo bảng

		table_provider = new JTable();
		table_provider.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, },
				new String[] { "Mã NCC", "Tên NCC", "SĐT", "Email", "Diachi", "Ghi chú", "Trạng Thái" }));
		table_provider.setBounds(30, 320, 820, 300);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 291, 820, 278);
		scrollPane.setViewportView(table_provider);
		panel.add(scrollPane);
		panel.setVisible(true);

		tableModel = (DefaultTableModel) table_provider.getModel();
		loadProvider();
		showAll();

		table_provider.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				index = table_provider.getSelectedRow();
				if (index >= 0) {
					Provider provider = providerList.get(index);

					int sl = provider.getMaNhaCungCap();
					String vl = String.valueOf(sl);
					jlMaNhaCungCap.setText(vl.toString());

					jtfTenNhaCungCap.setText(provider.getTenNhaCungCap().toString());

					int s2 = provider.getsDT();
					String v2 = String.valueOf(s2);
					jtfSDT.setText(v2.toString());

					jtfEmail.setText(provider.getEmail().toString());
					jtfDiaChi.setText(provider.getDiaChi().toString());
					jtfGhiChu.setText(provider.getGhiChu().toString());

					int trangthai = provider.getTrangThai();
					String tt = String.valueOf(trangthai);
					if (trangthai == 1) {
						jtfTrangthai.setSelectedIndex(1);
					} else {
						jtfTrangthai.setSelectedIndex(0);
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

	private void loadProvider() {
		providerList.clear();
		try {

			Connection cons = DBConnect.getConnection();

			String sql = "SELECT * FROM `provider` WHERE 1";
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

	private void showAll() {
		tableModel.setRowCount(0);

		for (Provider provider : providerList) {
			tableModel.addRow(new Object[] { provider.getMaNhaCungCap(), provider.getTenNhaCungCap(), provider.getsDT(),
					provider.getEmail(), provider.getDiaChi(), provider.getGhiChu(), provider.getTrangThai()

			});
		}

	}

	private List<Provider> searchProvider(String columnName, String keyword) {
		List<Provider> result = new ArrayList<>();
		String query;
		PreparedStatement stmt;
		try {
			Connection cons = DBConnect.getConnection();
			if (isNumeric(keyword)) {
				query = "SELECT * FROM provider WHERE " + columnName + " = ?";
				stmt = cons.prepareStatement(query);
				stmt.setInt(1, Integer.parseInt(keyword));
			} else {
				query = "SELECT * FROM provider WHERE " + columnName + " LIKE ?";
				stmt = cons.prepareStatement(query);
				stmt.setString(1, "%" + keyword + "%");
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("maNhaCungCap");
				String name = rs.getString("tenNhaCungCap");
				int sdt = rs.getInt("sDT");
				String email = rs.getString("Email");
				String diachi = rs.getString("diaChi");
				String ghichu = rs.getString("ghiChu");
				int trangthai = rs.getInt("trangThai");
				Provider provider = new Provider(id, name, sdt, email, diachi, ghichu, trangthai);
				result.add(provider);
			}
			stmt.close();
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

		return result;
	}

	private boolean isNumeric(String str) {
		try {
			int num = Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void initComponet() {
		// TODO Auto-generated method stub

	}
}
