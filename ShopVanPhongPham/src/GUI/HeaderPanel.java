package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.MatteBorder;

import DAO.DBConnect;
import DAO.add_account;
import DTO.Account;
import DTO.Staff;

import javax.swing.JButton;
import javax.swing.JFrame;

public class HeaderPanel extends JPanel {
private int id = 1;

private Account account;
	
	public HeaderPanel(int id) {
		this.id = id;
		setLayout(null);
		
		JPanel jpnHeader = new JPanel();
		jpnHeader.setBackground(new Color(12, 129, 160));
		jpnHeader.setBounds(0, 0, 1200, 55);
		add(jpnHeader);
		jpnHeader.setLayout(null);

		JLabel jlbLogo = new JLabel("Stationery Store");
		jlbLogo.setForeground(new Color(254, 255, 255));
		jlbLogo.setFont(new Font("Arial", Font.BOLD, 36));
		jlbLogo.setBounds(30, 6, 278, 42);
		jpnHeader.add(jlbLogo);
		
		JButton btnExit = new JButton("Logout");
		btnExit.setFocusPainted(false);
		btnExit.setForeground(new Color(12, 129, 160));
		btnExit.setFont(new Font("SansSerif", Font.BOLD, 22));
		btnExit.setBounds(1050, 8, 120, 40);
		jpnHeader.add(btnExit);
		
		
		add_account add_account = new add_account();
		account=add_account.add(id);
		
		String level ;
		System.out.println(account.getLevel());
		if(account.getLevel() == 1) {
			level = "Nhân viên";
		}
		else if (account.getLevel() == 2) {
			level = "Admin";
		}
		else {
			level = "Quản lý";
		}
		
		JLabel jlbInfor = new JLabel("Mã tài khoản : " + account.getMaTaiKhoan() + "  "+"SĐT : " +account.getsDT() + "  "+"LEVEL : "+ level );
		jlbInfor.setForeground(new Color(254, 255, 255));
		jlbInfor.setFont(new Font("Arial", Font.PLAIN, 16));
		jlbInfor.setBounds(700, 8, 350, 40);
		jpnHeader.add(jlbInfor);
		
		btnExit.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
		        Login login = new Login();
		        login.setVisible(true);
		        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(HeaderPanel.this);
		        frame.dispose();  
		    }
		});



	
		
	}
	

	

	
	
}