package GUI;

import java.awt.Point;
import java.awt.geom.Line2D;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Label;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.SwitchScreenController;
import BLL.check_action;
import DAO.DBConnect;
import DAO.StaffDao;
import DAO.add_account;
import DAO.roleDao;
import DTO.Account;
import DTO.Level;
import DTO.MenuBean;
import DTO.Staff;
import DTO.role;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mess_role extends JPanel {
	private List<String> list_fuc_str = null;
	private List<String> list_fuc_actionname = null;
	private List<Integer> list_checkcodeString = null;

	private Account account;
	private int level_NOW;
	private JCheckBox[] checkBoxes;
	private int list_checkbox[][];
	private Label[] labels;
	private JLabel lblNewLabel;
	private int id_user = 1;
	private int i;
	private int size_action_code;
	private int size_fuc;

	public mess_role(int id) {
		this.id_user = id;

		add_account add_account = new add_account();
		account = add_account.add(id_user);

		level_NOW = account.getLevel();

		setLayout(null);
		Font myFont1 = new Font("Serif", Font.BOLD, 20);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 672);
		setLayout(null);

		list_fuc_str = add_account.get_ALL_NameFuncByIdFuncArr(add_account.getIdFuncByMaLevel(id));
		list_fuc_actionname = add_account.getAllActionCodes(add_account.getIdFuncByMaLevel(id));

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 880, 652);
		panel.setBorder(new TitledBorder(null, "Nhập nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(29, 11, 137, 54);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(myFont1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(29, 123, 480, 460);
		panel.add(panel_1);
		panel_1.setVisible(true);

		checkBoxes = new JCheckBox[list_fuc_str.size() * list_fuc_actionname.size()];
		labels = new Label[list_fuc_str.size()];
		panel_1.setLayout(new GridLayout(list_fuc_str.size(), list_fuc_actionname.size()));
		System.out.println(list_fuc_str);

		int k = 0;
		int m = 0;
		for (String s : list_fuc_str) {

			labels[m] = new Label(s);
			labels[m].setFont(myFont1);
			panel_1.add(labels[m]);
			m++;

			for (int i = 0; i < list_fuc_actionname.size(); i++) {

				checkBoxes[k] = new JCheckBox();

				panel_1.add(checkBoxes[k]);
				k++;
			}

		}

		String level;

		level = add_account.out_name_level(id);

		lblNewLabel.setText(level);

		JPanel panel2 = new JPanel();
		panel2.setBounds(124, 59, 385, 63);
		panel.add(panel2);
		panel2.setVisible(true);

		System.out.println(list_fuc_actionname);
		panel2.setLayout(new GridLayout(1, 1));

		JButton btn_update = new JButton("UPDATE");
		btn_update.setOpaque(true);
		btn_update.setForeground(Color.WHITE);
		btn_update.setFont(new Font("Arial", Font.BOLD, 18));
		btn_update.setBorder(null);
		btn_update.setBackground(new Color(12, 129, 160));
		btn_update.setBounds(519, 198, 144, 49);
		panel.add(btn_update);

		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (check_action.check_action_user_by_level(account.getLevel(), 11, "update") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//						btn_add.enable(false);
				} else {

					save_checkbox();
					load_checkbox(level_NOW);

					JOptionPane.showMessageDialog(panel, "UPDATE THANH CONG !!!");
				}
			}
		});

		JButton btn_find = new JButton("SEARCH");
		btn_find.setOpaque(true);
		btn_find.setForeground(Color.WHITE);
		btn_find.setFont(new Font("Arial", Font.BOLD, 18));
		btn_find.setBorder(null);
		btn_find.setBackground(new Color(12, 129, 160));
		btn_find.setBounds(519, 273, 144, 49);
		panel.add(btn_find);

		btn_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i = -1;
				String[] options = new String[5];
				add_account.all_name_level(options);

				String selectedOption = (String) JOptionPane.showInputDialog(panel, "Chọn level muốn tìm:", "Tìm kiếm",
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (selectedOption != null && !selectedOption.equals("")) {

					i = add_account.get_maLevel_from_tenLevel(selectedOption);
					lblNewLabel.setText(selectedOption);
					level_NOW = i;

				} else {
					level_NOW = account.getLevel();
					lblNewLabel.setText(add_account.out_name_level(level_NOW));
				}

//				account = add_account.add(id_user);

				load_checkbox(level_NOW);

			}

		});

		JButton btn_find_1 = new JButton("SETTING");
		btn_find_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_find_1.setOpaque(true);
		btn_find_1.setForeground(Color.WHITE);
		btn_find_1.setFont(new Font("Arial", Font.BOLD, 18));
		btn_find_1.setBorder(null);
		btn_find_1.setBackground(new Color(12, 129, 160));
		btn_find_1.setBounds(519, 447, 144, 49);
		panel.add(btn_find_1);

		JButton btn_update_1 = new JButton("ADD");
		btn_update_1.setOpaque(true);
		btn_update_1.setForeground(Color.WHITE);
		btn_update_1.setFont(new Font("Arial", Font.BOLD, 18));
		btn_update_1.setBorder(null);
		btn_update_1.setBackground(new Color(12, 129, 160));
		btn_update_1.setBounds(519, 123, 144, 49);
		panel.add(btn_update_1);

		JButton btn_update_1_1 = new JButton("DELETE");
		btn_update_1_1.setOpaque(true);
		btn_update_1_1.setForeground(Color.WHITE);
		btn_update_1_1.setFont(new Font("Arial", Font.BOLD, 18));
		btn_update_1_1.setBorder(null);
		btn_update_1_1.setBackground(new Color(12, 129, 160));
		btn_update_1_1.setBounds(519, 359, 144, 49);
		panel.add(btn_update_1_1);

		btn_update_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check_action.check_action_user_by_level(account.getLevel(), 11, "delete") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {
					int i = -1;
					String[] options = new String[5];
					add_account.all_name_level(options);

					String selectedOption = (String) JOptionPane.showInputDialog(panel, "Chọn level xóa:", "Tìm kiếm",
							JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

					if (selectedOption != null && !selectedOption.equals("")) {

						i = add_account.get_maLevel_from_tenLevel(selectedOption);
						roleDao.deleteLevel(i);

						JOptionPane.showMessageDialog(panel, "Xóa Thành Công");
					} else
						JOptionPane.showMessageDialog(panel, "error");

					load_checkbox(level_NOW);

				}
			}

		});

		btn_update_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check_action.check_action_user_by_level(account.getLevel(), 11, "add") == false) {
					JOptionPane.showMessageDialog(panel, "Bạn không có quyền truy cập !!!");
//					btn_add.enable(false);
				} else {
					String keyword = JOptionPane.showInputDialog(panel, "Nhập Level muốn thêm :", "Thêm Level",
							JOptionPane.PLAIN_MESSAGE);

					if (keyword != null && !keyword.equals("")) {

						roleDao.insert_mess_role(keyword, list_fuc_str, list_fuc_actionname);
						JOptionPane.showMessageDialog(panel, "Thêm chức năng mới thành công!!!");
					}

					load_checkbox(level_NOW);

				}
			}
		});

		size_action_code = 0;
		for (String s : list_fuc_actionname) {
			JLabel label = new JLabel(s);
			label.setFont(myFont1);
			panel2.add(label);
			size_action_code++;

		}
		System.out.println(size_action_code);
		btn_find_1.addActionListener(new ActionListener() {
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
				panel.add(new Role_panel(id_user)); // Thêm panel Role_panel vào panel cha
				panel.revalidate(); // Cập nhật lại giao diện
				panel.repaint();
//				panel.setVisible(true);
			}

		});

		load_checkbox(level_NOW);

	}

	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub

	}

	public void save_checkbox() {
		int k = 0;
		for (int i = 0; i < list_fuc_str.size(); i++) {
			for (int j = 0; j < list_fuc_actionname.size(); j++) {

				if (checkBoxes[k].isSelected() == true) {
					list_checkbox[j][i] = 1;
				} else {
					list_checkbox[j][i] = 0;
				}

				k++;

			}

		}

		try {
			String sql = "UPDATE per_detail SET check_action = ? WHERE maLevel = ? AND id_function = ? AND action_code = ?";
			Connection conn = DBConnect.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < list_fuc_str.size(); i++) {
				for (int j = 0; j < list_fuc_actionname.size(); j++) {

					pstmt.setInt(1, list_checkbox[j][i]);
					pstmt.setInt(2, level_NOW);
					pstmt.setInt(3, i + 1);
					pstmt.setString(4, list_fuc_actionname.get(j));

					pstmt.executeUpdate();
				}
			}

			pstmt.close();
			conn.close();
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

	public void load_checkbox(int malevel) {
		list_checkbox = new int[list_fuc_actionname.size()][list_fuc_str.size()];

		String sql = "SELECT check_action FROM per_detail WHERE maLevel = ? AND id_function = ? AND action_code = ?";
		int a = 0;
		int b = 0;
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < list_fuc_actionname.size(); i++) {
				for (int ya = 0; ya < list_fuc_str.size(); ya++) {

					pstmt.setInt(1, malevel);
					pstmt.setInt(2, ya + 1);
					pstmt.setString(3, list_fuc_actionname.get(i));

					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						list_checkbox[a][b] = rs.getInt("check_action");

					}

					rs.close();
					b++;

				}

				a++;
				b = 0;
			}

			pstmt.close();
			conn.close();
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

		int k = 0;

		for (int i = 0; i < list_fuc_str.size(); i++) {
			for (int j = 0; j < list_fuc_actionname.size(); j++) {

				if (list_checkbox[j][i] == 1) {
					checkBoxes[k].setSelected(true);
				} else {
					checkBoxes[k].setSelected(false);
				}

//	                if ( checkBoxes[k].isSelected() == true) {
//	                	 System.out.print("1");
//	                }
//	                else {
//	                	System.out.print("0");
//					}
				k++;

			}

		}

	}

}
