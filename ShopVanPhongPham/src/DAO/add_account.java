package DAO;

import java.awt.color.ICC_ColorSpace;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import DTO.Account;
import DTO.Level;

import java.util.Set;

//import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JLabel;


public class add_account {

	public static Account add(int id) {
		 
		Account account = new Account();
	    try {
	        Connection cons = DBConnect.getConnection();
	        String sql = "select * from account where maTaiKhoan = " + id ;
	        PreparedStatement pr = cons.prepareStatement(sql);
	        ResultSet r1 = pr.executeQuery();
	        
	        if (r1.next()) { // add this line to move the cursor to the first row
	            account = new Account(r1.getInt("maTaiKhoan"),r1.getInt("sDT"),r1.getString("passWord"),r1.getInt("level"));
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
		return account;
}
	
	public static String out_name_level(int id) {
		
		add_account add_account = new add_account();
		Account account = add_account.add(id);
		
		Level level = null;
		 try {
		        Connection cons = DBConnect.getConnection();
		        String sql = "select * from level where maLevel = " + account.getLevel();
		        PreparedStatement pr = cons.prepareStatement(sql);
		        ResultSet r1 = pr.executeQuery();
		        
		        if (r1.next()) { // add this line to move the cursor to the first row
		            level = new Level(r1.getInt("maLevel"),r1.getString("tenLevel"));
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();

		        // Hiển thị JDialog với thông báo lỗi
		        JDialog dialog = new JDialog();
		        dialog.setTitle("Lỗi");
		        dialog.setModal(true);
		        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		        JLabel label = new JLabel("Đã xảy ra lỗi: " + ex.getMessage());
		        dialog.getContentPane().add(label);

		        dialog.setSize(300, 150);
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
		    }
		 
			return level.getTenLevel();
		
	
	}
	
	
    public static ArrayList<Integer> getIdFuncByMaLevel(int maLevel) {
    	 Set<Integer> idFuncSet = new HashSet<>();
         try {
        	 Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             String query = "SELECT id_function FROM per_detail WHERE maLevel = " + maLevel + " AND `check_action` = 1";
             ResultSet rs = stmt.executeQuery(query);
             while (rs.next()) {
                 int idFunc = rs.getInt("id_function");
                 idFuncSet.add(idFunc);
             }
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
         return new ArrayList<>(idFuncSet);
    }
    
    public static ArrayList<String> getNameFuncByIdFuncArr(List<Integer> list_fuc_Int) {
        ArrayList<String> nameFuncList = new ArrayList<>();
        try {
        	 Connection conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            for (int idFunc : list_fuc_Int) {
                String query = "SELECT name_function FROM function WHERE id_function = " + idFunc;
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String nameFunc = rs.getString("name_function");
                    nameFuncList.add(nameFunc);
                }
            }
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
        return nameFuncList;
    }
    
    
    public static List<String> getAllActionCodes(ArrayList<Integer> arrayList) {
        List<String> actionCodes = new ArrayList<>();
        
        try {
           
        	 Connection conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
           String sql = "SELECT DISTINCT action_code FROM per_detail";
            ResultSet rs = stmt.executeQuery(sql);
      
            while (rs.next()) {
                String actionCode = rs.getString("action_code");
                actionCodes.add(actionCode);
            }
              rs.close();
            stmt.close();
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
        return actionCodes;
    }

    
    public static ArrayList<String> get_ALL_NameFuncByIdFuncArr(List<Integer> list_fuc_Int) {
        ArrayList<String> nameFuncList = new ArrayList<>();
        try {
        	 Connection conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
           
                String query = "SELECT name_function FROM function  " ;
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String nameFunc = rs.getString("name_function");
                    nameFuncList.add(nameFunc);
                
            }
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
        return nameFuncList;
    }
    public static String[] all_name_level(String[] all_levelString) {
        Set<String> levelStrings = new HashSet<>();

        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT DISTINCT tenLevel FROM level";
            PreparedStatement pr = cons.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                String levelString = rs.getString("tenLevel");
                levelStrings.add(levelString);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

            // Hiển thị JDialog với thông báo lỗi
            JDialog dialog = new JDialog();
            dialog.setTitle("Lỗi");
            dialog.setModal(true);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JLabel label = new JLabel("Đã xảy ra lỗi: " + ex.getMessage());
            dialog.getContentPane().add(label);

            dialog.setSize(300, 150);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);

            // Thoát chương trình
            System.exit(0);
        }

        // Copy các giá trị từ Set vào mảng all_levelString
        levelStrings.toArray(all_levelString);

        return all_levelString;
    }

    
    public static int get_maLevel_from_tenLevel(String tenLevel) {
        int maLevel = -1;

        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT maLevel FROM level WHERE tenLevel = ?";
            PreparedStatement pr = cons.prepareStatement(sql);
            pr.setString(1, tenLevel);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                maLevel = rs.getInt("maLevel");
            }
        }catch (SQLException ex) {
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

        return maLevel;
    }
    
}
