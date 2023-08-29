package GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import BLL.CustomerManagerController;
import BLL.check_action;
import BLL.check_all;
import DAO.AccountDao;
import DAO.CustomerDao;
import DAO.DBConnect;
import DAO.ProductDao;
import DAO.add_account;
import DAO.roleDao;
import DTO.Account;
import DTO.Customer;
import DTO.role;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class Role_panel extends JPanel {
	private int temp_id;
	private int temp_level = 0;
	private int temp_fun = 0;
	private int temp_check = 0;
	private String temp_acion_codeString = "";
	DefaultTableModel tableModel;
	private int id = 3;
	private int level = 0;
	private JTable table;
	private JTable table_product;
	List<role> list_account = new ArrayList<>();
	private check_action check_action = new check_action();

	private int index = -1;

	private JLabel tf_matk;

	private JLabel tf_sdt;

	private JTextField tf_pw;
	private check_all check_all = new check_all();

	private JPanel jpnSideBar;

	private int id_user;
	private JCheckBox c_box;

	public Role_panel(int id) {

		this.id_user = id;

		add_account add_account = new add_account();
		level = (add_account.add(id)).getLevel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 672);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 880, 652);
		panel.setBorder(new TitledBorder(null, "Nhập Account", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(null);

		Font font = new Font("Arial", Font.PLAIN, 16);
		tf_matk = new JLabel();
		tf_matk.setBounds(576, 50, 118, 26);
		tf_matk.setFont(font);
		panel.add(tf_matk);
		tf_sdt = new JLabel();
		tf_sdt.setBounds(576, 107, 118, 26);
		tf_sdt.setFont(font);
		panel.add(tf_sdt);

		JLabel lp_pw = new JLabel("Action Name");
		lp_pw.setFont(font);
		lp_pw.setBounds(465, 94, 101, 19);
		panel.add(lp_pw);
		tf_pw = new JTextField();
		tf_pw.setBounds(576, 87, 220, 26);
		panel.add(tf_pw);

		JLabel lp_lv = new JLabel("Check code");
		lp_lv.setFont(font);
		lp_lv.setBounds(465, 147, 91, 21);
		panel.add(lp_lv);

		c_box = new JCheckBox();
		c_box.setBounds(576, 145, 97, 23);
		panel.add(c_box);

		JButton btn_find = new JButton("ADD");
		btn_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String keyword = JOptionPane.showInputDialog(panel, "Nhập chức năng muốn thêm :", "Thêm chức năng",
						JOptionPane.PLAIN_MESSAGE);
				if (keyword  != null && !keyword .equals("")) {
					System.out.println("Cancel is pressed");

					roleDao.insert(keyword);

					JOptionPane.showMessageDialog(panel, "Thêm chức năng mới thành công!!!");
					loadProduct();
					showAll();
				}
			}

		});

		btn_find.setBounds(465, 211, 144, 49);
		btn_find.setFont(new Font("Arial", Font.BOLD, 18));
		btn_find.setForeground(new Color(255, 255, 255));
		btn_find.setBorder(null);
		btn_find.setOpaque(true);
		btn_find.setBackground(new Color(12, 129, 160));
		panel.add(btn_find);

		JButton btn_return = new JButton("RETURN");
		btn_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				setLayout(new BorderLayout());
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 900, 672);
				add(panel);
				panel.removeAll();
				panel.setBorder(null);
				panel.setLayout(new BorderLayout());
				panel.setBounds(10, 10, 880, 652);
				panel.add(new mess_role(id_user)); // Thêm panel Role_panel vào panel cha
				panel.revalidate(); // Cập nhật lại giao diện
				panel.repaint();
				panel.setVisible(true);
			}
		});
		btn_return.setBounds(465, 402, 144, 49);
		btn_return.setFont(new Font("Arial", Font.BOLD, 18));
		btn_return.setForeground(new Color(255, 255, 255));
		btn_return.setBorder(null);
		btn_return.setOpaque(true);
		btn_return.setBackground(new Color(12, 129, 160));
		panel.add(btn_return);

		table_product = new JTable();

		table_product.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, { null, null, null }, },
				new String[] { "id", "Action Name", "checkcode" }));

		TableColumnModel columnModel = table_product.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(75);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(100);

		table_product.setBounds(30, 320, 820, 300);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 42, 398, 500);
		scrollPane.setViewportView(table_product);
		panel.add(scrollPane);

		JButton btn_return_1 = new JButton("DELETE");
		btn_return_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int option = JOptionPane.showConfirmDialog(panel, "Bạn Có Muốn Xóa Chức Năng Này !!!");
				if (option == 0) {
					roleDao.delete(temp_acion_codeString);
					loadProduct();
					showAll();
				} else {
					loadProduct();
					showAll();
				}
			}
		});
		btn_return_1.setOpaque(true);
		btn_return_1.setForeground(Color.WHITE);
		btn_return_1.setFont(new Font("Arial", Font.BOLD, 18));
		btn_return_1.setBorder(null);
		btn_return_1.setBackground(new Color(12, 129, 160));
		btn_return_1.setBounds(465, 303, 144, 49);
		panel.add(btn_return_1);
		panel.setVisible(true);

		tableModel = (DefaultTableModel) table_product.getModel();

		loadProduct();
		showAll();

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

					role customer = list_account.get(index);
					temp_acion_codeString = customer.getAction_code();
					temp_id = customer.getId_per_detail();
					tf_pw.setText(customer.getAction_code().toString());

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

		panel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (!table_product.getBounds().contains(e.getPoint())) {
					loadProduct();
					showAll();
					tf_matk.setText("");
					c_box.setSelected(false);
					tf_sdt.setText("");
					tf_pw.setText("");
					tf_sdt.setText("");

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

	}

	private void loadProduct() {
		list_account.clear();
		try {
			Connection cons = DBConnect.getConnection();
			String sql = "SELECT DISTINCT id_per_detail, maLevel, id_function, action_code, check_action FROM per_detail GROUP BY action_code ORDER BY id_per_detail ASC";
			PreparedStatement pr = cons.prepareStatement(sql);
			ResultSet r1 = pr.executeQuery();
			while (r1.next()) {
				role staff = new role(r1.getInt("id_per_detail"), r1.getInt("maLevel"), r1.getInt("id_function"),
						r1.getString("action_code"), r1.getInt("check_action"));
				list_account.add(staff);
			}

			r1.close();
			cons.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void showAll() {
		tableModel.setRowCount(0);

		for (role row : list_account) {
			tableModel.addRow(new Object[] { row.getId_per_detail(),

					row.getAction_code(),

			});
		}

	}

	private List<role> searchCus(String columnName, String keyword) {
		List<role> result = new ArrayList<>();
		String query;
		PreparedStatement stmt;
		try {
			Connection cons = DBConnect.getConnection();
			if (isNumeric(keyword)) {
				query = "SELECT * FROM per_detail WHERE " + columnName + " = ?";
				stmt = cons.prepareStatement(query);
				stmt.setInt(1, Integer.parseInt(keyword));
			} else {
				query = "SELECT * FROM per_detail WHERE " + columnName + " LIKE ?";
				stmt = cons.prepareStatement(query);
				stmt.setString(1, "%" + keyword + "%");
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id_per_detail");
				int name = rs.getInt("maLevel");
				String phone = rs.getString("action_code");
				int level = rs.getInt("id_function");
				int c = rs.getInt("check_action");

				role customer = new role(id, name, level, phone, c);
				result.add(customer);
			}

			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
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

	public String getNameFuncByIdFunc(int idFunc) {
		String nameFunc = null;
		try {
			Connection conn = DBConnect.getConnection();
			String sql = "SELECT name_function FROM function WHERE id_function = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idFunc);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nameFunc = rs.getString("name_function");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nameFunc;
	}

	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub

	}
}