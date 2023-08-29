package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.MatteBorder;

import DAO.DBConnect;
import DTO.Account;
import DTO.Category;

import javax.swing.BoxLayout;

public class Login extends JFrame implements ActionListener{
	public static String idLogin = null;
	public int id = -1;
	List<Account> accountList = new ArrayList<>();
	private JPanel contentPane;
	private JTextField txtUsername;
	private JLabel jlbPassWord;
	private JPasswordField passwordField;
	private JTextField Username;
	private JPasswordField Password;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		new Login();
	}

	
	public Login() {
		setTitle("LOGIN");
		setBounds(100, 100, 1200, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(12, 129, 160));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(254, 255, 255));
		panel.setBounds(600, 0, 600, 672);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblStore = new JLabel("Stationery Store");
		lblStore.setBounds(60, 55, 250, 36);
		lblStore.setForeground(new Color(12, 91, 160));
		lblStore.setFont(new Font("SansSerif", Font.BOLD, 30));
		panel.add(lblStore);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(new Color(12, 91, 160));
		lblLogin.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblLogin.setBounds(256, 150, 88, 36);
		panel.add(lblLogin);
		
		Username = new JTextField();
		Username.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(12, 91, 160)));
		Username.setFont(new Font("SansSerif", Font.PLAIN, 14));
		Username.setBounds(130, 278, 340, 36);
		panel.add(Username);
		Username.setColumns(10);
		
		JLabel jlbSDT = new JLabel("Username");
		jlbSDT.setForeground(new Color(12, 91, 160));
		jlbSDT.setFont(new Font("SansSerif", Font.PLAIN, 12));
		jlbSDT.setBounds(130, 250, 62, 16);
		panel.add(jlbSDT);
		
		Password = new JPasswordField();
		Password.setFont(new Font("SansSerif", Font.PLAIN, 14));
		Password.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(12, 91, 160)));
		Password.setBounds(130, 380, 340, 36);
		panel.add(Password);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setForeground(new Color(12, 91, 160));
		lblPass.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblPass.setBounds(130, 359, 62, 16);
		panel.add(lblPass);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(254, 255, 255));
		panel_1.setBorder(null);
		panel_1.setBounds(150, 535, 300, 36);
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setActionCommand("login");
		btnLogin.addActionListener(this);
		btnLogin.setForeground(new Color(254, 255, 255));
		btnLogin.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnLogin.setBorder(null);
		btnLogin.setOpaque(true);
		btnLogin.setBackground(new Color(12, 91, 160)); 
		btnLogin.setBounds(130, 487, 340, 36);
		panel.add(btnLogin);
		
		JLabel lblStore_1 = new JLabel("Stationery Store");
		lblStore_1.setForeground(new Color(254, 255, 255));
		lblStore_1.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblStore_1.setBounds(135, 325, 330, 36);
		contentPane.add(lblStore_1);
		
		JLabel lblStore_1_1 = new JLabel("Welcome to");
		lblStore_1_1.setForeground(new Color(254, 255, 255));
		lblStore_1_1.setFont(new Font("Shree Devanagari 714", Font.PLAIN, 20));
		lblStore_1_1.setBounds(245, 303, 109, 24);
		contentPane.add(lblStore_1_1);
		
		JLabel lblStore_1_1_1 = new JLabel("Login your account");
		lblStore_1_1_1.setForeground(new Color(254, 255, 255));
		lblStore_1_1_1.setFont(new Font("Shree Devanagari 714", Font.PLAIN, 20));
		lblStore_1_1_1.setBounds(210, 372, 180, 24);
		contentPane.add(lblStore_1_1_1);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String username = Username.getText();
		String password = new String(Password.getPassword());
		StringBuilder sb = new StringBuilder();
		
		
		
		if(username.equals("")) {
			sb.append("Vui long nhap username \n");
			Username.requestFocus();
		}
		if(password.equals("")) {
			sb.append("Vui long nhap password \n");
			
		}
			
		if(sb.length()>0)
		{
			JOptionPane.showMessageDialog(this,sb.toString(),"Invalidation", JOptionPane.ERROR_MESSAGE);
			return ;
			
		}
		
		
		
		if( checkLogin(username, password)) {
			
			Main main = new Main(id);
			main.setVisible(true);
			
		    this.dispose();
		}
		
		else {
			JOptionPane.showMessageDialog(this, "Username hoặc Password không đúng xin nhập lại");
		}
		
	}
		
		
	public boolean checkLogin(String username, String password) {
	   
	    try {
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanphongpham", "root", "");
	        PreparedStatement pstmt = conn.prepareStatement("SELECT maTaiKhoan FROM account WHERE sDT = ? AND passWord = ?");
	        pstmt.setString(1, username);
	        pstmt.setString(2, password);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	        	id = rs.getInt("maTaiKhoan");
	        }
	        
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
	    if(id == -1)
	    	return false;
	    return true;
	}


	
}
