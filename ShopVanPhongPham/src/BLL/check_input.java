package BLL;

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

//import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class check_input {

	// check xem coi toàn bộ phải là số hay không 
	public static boolean checknumber(String input) {
		 for (int i = 0; i < input.length(); i++) {
		        if (!Character.isDigit(input.charAt(i))) {
		            return false;
		        }
		    }
		    return true;
	}
	// hàm kiểm tra số điện thoại nè 
	public static boolean checkNumberLength(String input) {
	    if (input.length() != 9 && input.length() != 10) {
	        return false;
	    }

	    for (int i = 0; i < input.length(); i++) {
	        if (!Character.isDigit(input.charAt(i))) {
	            return false;
	        }
	    }

	    return true;
	}
	// kiểm tra xem coi có ký tự đặc biệt không
	public static boolean checkSpecialCharacters(String input) {
	    String specialCharacters = "!@#$%^&*()-_=+[{]};:'\",<.>/?";

	    for (int i = 0; i < input.length(); i++) {
	        char ch = input.charAt(i);
	        if (specialCharacters.contains(String.valueOf(ch))) {
	            return false;
	        }
	    }

	    return true;
	}

	// hàm check gmail
	public static boolean checkEmailFormat(String input) {
	    String emailSuffix = "@gmail.com";
	    
	    
	 
	    if (input.length() <= emailSuffix.length()) {
	        return false;
	    }

	  
	    String suffix = input.substring(input.length() - emailSuffix.length());

	   
	    return suffix.equals(emailSuffix);
	}

	
}
