package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import BLL.SwitchScreenController;
import DTO.MenuBean;

public class SideBarPanel extends JPanel {

	private JLabel jlbSell;
	private JPanel jpnSell;

	public SideBarPanel(int id) {
		
		setLayout(null);
		
		JPanel jpnMenu = new JPanel();
		jpnMenu.setBackground(new Color(12, 129, 160));
		jpnMenu.setBounds(0, 54, 300, 618);
		add(jpnMenu);
		jpnMenu.setLayout(null);
		
		JPanel jpnDashboard = new JPanel();
		jpnDashboard.setBackground(new Color(254, 255, 255));
		jpnDashboard.setBounds(20, 15, 260, 40);
		jpnMenu.add(jpnDashboard);
		
		
		GridBagLayout gbl_jpnDashboard = new GridBagLayout();
		gbl_jpnDashboard.columnWidths = new int[]{260, 0};
		gbl_jpnDashboard.rowHeights = new int[]{50, 0};
		gbl_jpnDashboard.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_jpnDashboard.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		jpnDashboard.setLayout(gbl_jpnDashboard);
		
		JLabel jlbDashboard = new JLabel("Dashboard");
		jlbDashboard.setForeground(new Color(12, 91, 160));
		jlbDashboard.setFont(new Font("Arial", Font.PLAIN, 30));
		GridBagConstraints gbc_jlbDashboard = new GridBagConstraints();
		gbc_jlbDashboard.gridx = 0;
		gbc_jlbDashboard.gridy = 0;
		jpnDashboard.add(jlbDashboard, gbc_jlbDashboard);
		
		JPanel jpnAccount = new JPanel();
		jpnAccount.setBackground(new Color(254, 255, 255));
		jpnAccount.setBounds(20, 67, 260, 40);
		jpnMenu.add(jpnAccount);
		GridBagLayout gbl_jpnAccount = new GridBagLayout();
		gbl_jpnAccount.columnWidths = new int[]{260, 0};
		gbl_jpnAccount.rowHeights = new int[]{50, 0};
		gbl_jpnAccount.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_jpnAccount.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		jpnAccount.setLayout(gbl_jpnAccount);
		
		JLabel jlbAccount = new JLabel("Account");
		jlbAccount.setForeground(new Color(12, 91, 160));
		jlbAccount.setFont(new Font("Arial", Font.PLAIN, 30));
		GridBagConstraints gbc_jlbAccount = new GridBagConstraints();
		gbc_jlbAccount.gridx = 0;
		gbc_jlbAccount.gridy = 0;
		jpnAccount.add(jlbAccount, gbc_jlbAccount);
		
		JPanel jpnCustomer = new JPanel();
		jpnCustomer.setBackground(new Color(254, 255, 255));
		jpnCustomer.setBounds(20, 119, 260, 40);
		jpnMenu.add(jpnCustomer);
		GridBagLayout gbl_jpnCustomer = new GridBagLayout();
		gbl_jpnCustomer.columnWidths = new int[]{260, 0};
		gbl_jpnCustomer.rowHeights = new int[]{50, 0};
		gbl_jpnCustomer.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_jpnCustomer.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		jpnCustomer.setLayout(gbl_jpnCustomer);
		
		JLabel jlbCustomer = new JLabel("Customer");
		jlbCustomer.setForeground(new Color(12, 91, 160));
		jlbCustomer.setFont(new Font("Arial", Font.PLAIN, 30));
		GridBagConstraints gbc_jlbCustomer = new GridBagConstraints();
		gbc_jlbCustomer.gridx = 0;
		gbc_jlbCustomer.gridy = 0;
		jpnCustomer.add(jlbCustomer, gbc_jlbCustomer);
		
		JPanel jpnStaff = new JPanel();
		jpnStaff.setBackground(new Color(254, 255, 255));
		jpnStaff.setBounds(20, 171, 260, 40);
		jpnMenu.add(jpnStaff);
		
		
		
		GridBagLayout gbl_jpnStaff = new GridBagLayout();
		gbl_jpnStaff.columnWidths = new int[]{260, 0};
		gbl_jpnStaff.rowHeights = new int[]{50, 0};
		gbl_jpnStaff.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_jpnStaff.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		jpnStaff.setLayout(gbl_jpnStaff);
		
		JLabel jlbStaff = new JLabel("Staff");
		jlbStaff.setForeground(new Color(12, 91, 160));
		jlbStaff.setFont(new Font("Arial", Font.PLAIN, 30));
		GridBagConstraints gbc_jlbStaff = new GridBagConstraints();
		gbc_jlbStaff.gridx = 0;
		gbc_jlbStaff.gridy = 0;
		jpnStaff.add(jlbStaff, gbc_jlbStaff);
		
		JPanel jpnCategory = new JPanel();
		jpnCategory.setBackground(new Color(254, 255, 255));
		jpnCategory.setBounds(20, 223, 260, 40);
		jpnMenu.add(jpnCategory);
		GridBagLayout gbl_jpnCategory = new GridBagLayout();
		gbl_jpnCategory.columnWidths = new int[]{260, 0};
		gbl_jpnCategory.rowHeights = new int[]{50, 0};
		gbl_jpnCategory.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_jpnCategory.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		jpnCategory.setLayout(gbl_jpnCategory);
		
		JLabel jlbCategory = new JLabel("Category");
		jlbCategory.setForeground(new Color(12, 91, 160));
		jlbCategory.setFont(new Font("Arial", Font.PLAIN, 30));
		GridBagConstraints gbc_jlbCategory = new GridBagConstraints();
		gbc_jlbCategory.gridx = 0;
		gbc_jlbCategory.gridy = 0;
		jpnCategory.add(jlbCategory, gbc_jlbCategory);
		
		JPanel jpnProduct = new JPanel();
		jpnProduct.setBackground(new Color(254, 255, 255));
		jpnProduct.setBounds(20, 275, 260, 40);
		jpnMenu.add(jpnProduct);
		GridBagLayout gbl_jpnProduct = new GridBagLayout();
		gbl_jpnProduct.columnWidths = new int[]{260, 0};
		gbl_jpnProduct.rowHeights = new int[]{50, 0};
		gbl_jpnProduct.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_jpnProduct.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		jpnProduct.setLayout(gbl_jpnProduct);
		
		JLabel jlbProduct = new JLabel("Product");
		jlbProduct.setForeground(new Color(12, 91, 160));
		jlbProduct.setFont(new Font("Arial", Font.PLAIN, 30));
		GridBagConstraints gbc_jlbProduct = new GridBagConstraints();
		gbc_jlbProduct.gridx = 0;
		gbc_jlbProduct.gridy = 0;
		jpnProduct.add(jlbProduct, gbc_jlbProduct);
		
		JPanel jpnInvoice = new JPanel();
		jpnInvoice.setBackground(new Color(254, 255, 255));
		jpnInvoice.setBounds(20, 379, 260, 40);
		jpnMenu.add(jpnInvoice);
		GridBagLayout gbl_jpnInvoice = new GridBagLayout();
		gbl_jpnInvoice.columnWidths = new int[]{260, 0};
		gbl_jpnInvoice.rowHeights = new int[]{50, 0};
		gbl_jpnInvoice.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_jpnInvoice.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		jpnInvoice.setLayout(gbl_jpnInvoice);
		
		JLabel jlbInvoice = new JLabel("Invoice");
		jlbInvoice.setForeground(new Color(12, 91, 160));
		jlbInvoice.setFont(new Font("Arial", Font.PLAIN, 30));
		GridBagConstraints gbc_jlbInvoice = new GridBagConstraints();
		gbc_jlbInvoice.gridx = 0;
		gbc_jlbInvoice.gridy = 0;
		jpnInvoice.add(jlbInvoice, gbc_jlbInvoice);
		
		JPanel jpnImport = new JPanel();
		jpnImport.setBackground(new Color(254, 255, 255));
		jpnImport.setBounds(20, 431, 260, 40);
		jpnMenu.add(jpnImport);
		GridBagLayout gbl_jpnImport = new GridBagLayout();
		gbl_jpnImport.columnWidths = new int[]{260, 0};
		gbl_jpnImport.rowHeights = new int[]{50, 0};
		gbl_jpnImport.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_jpnImport.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		jpnImport.setLayout(gbl_jpnImport);
		
		JLabel jlbImport = new JLabel("Import");
		jlbImport.setForeground(new Color(12, 91, 160));
		jlbImport.setFont(new Font("Arial", Font.PLAIN, 30));
		GridBagConstraints gbc_jlbImport = new GridBagConstraints();
		gbc_jlbImport.gridx = 0;
		gbc_jlbImport.gridy = 0;
		jpnImport.add(jlbImport, gbc_jlbImport);
		
		JPanel jpnProvider = new JPanel();
		jpnProvider.setBackground(new Color(254, 255, 255));
		jpnProvider.setBounds(20, 483, 260, 40);
		jpnMenu.add(jpnProvider);
		GridBagLayout gbl_jpnProvider = new GridBagLayout();
		gbl_jpnProvider.columnWidths = new int[]{260, 0};
		gbl_jpnProvider.rowHeights = new int[]{50, 0};
		gbl_jpnProvider.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_jpnProvider.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		jpnProvider.setLayout(gbl_jpnProvider);
		
		JLabel jlbProvider = new JLabel("Provider");
		jlbProvider.setForeground(new Color(12, 91, 160));
		jlbProvider.setFont(new Font("Arial", Font.PLAIN, 30));
		GridBagConstraints gbc_jlbProvider = new GridBagConstraints();
		gbc_jlbProvider.gridx = 0;
		gbc_jlbProvider.gridy = 0;
		jpnProvider.add(jlbProvider, gbc_jlbProvider);
		
		JPanel jpnView = new JPanel();
		jpnView.setBackground(new Color(254, 255, 255));
		jpnView.setBounds(300, 55, 900, 617);
		add(jpnView);
		jpnView.setLayout(null);
		
		jpnSell = new JPanel();
		jpnSell.setBackground(new Color(254, 255, 255));
		jpnSell.setBounds(20, 327, 260, 40);
		jpnMenu.add(jpnSell);
		GridBagLayout gbl_jpnSell = new GridBagLayout();
		gbl_jpnSell.columnWidths = new int[]{260, 0};
		gbl_jpnSell.rowHeights = new int[]{50, 0};
		gbl_jpnSell.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_jpnSell.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		jpnSell.setLayout(gbl_jpnSell);
		
		jlbSell = new JLabel("Sell");
		jlbSell.setForeground(new Color(12, 91, 160));
		jlbSell.setFont(new Font("Arial", Font.PLAIN, 30));
		GridBagConstraints gbc_lblSell = new GridBagConstraints();
		gbc_lblSell.gridx = 0;
		gbc_lblSell.gridy = 0;
		jpnSell.add(jlbSell, gbc_lblSell);
		
		
		
		JPanel jpnfunction = new JPanel();
		jpnfunction.setBackground(new Color(254, 255, 255));
		jpnfunction.setBounds(20, 535, 260, 40);
		jpnMenu.add(jpnfunction);
		GridBagLayout gbl_fun = new GridBagLayout();
		gbl_fun.columnWidths = new int[]{260, 0};
		gbl_fun.rowHeights = new int[]{50, 0};
		gbl_fun.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_fun.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		jpnCategory.setLayout(gbl_fun);
		
		JLabel jlbfunc = new JLabel("Role");
		jlbfunc.setForeground(new Color(12, 91, 160));
		jlbfunc.setFont(new Font("Arial", Font.PLAIN, 30));
		GridBagConstraints gbcfunc = new GridBagConstraints();
		gbcfunc.gridx = 0;
		gbcfunc.gridy = 0;
		jpnfunction.add(jlbfunc, gbcfunc);
		
		SwitchScreenController controller = new SwitchScreenController(jpnView,id);
		controller.setView(jpnDashboard, jlbDashboard);
		
		List<MenuBean> listItem = new ArrayList<>();
		listItem.add(new MenuBean("Dashboard", jpnDashboard, jlbDashboard));
		listItem.add(new MenuBean("Account", jpnAccount, jlbAccount));
		listItem.add(new MenuBean("Customer", jpnCustomer, jlbCustomer));
		listItem.add(new MenuBean("Staff", jpnStaff, jlbStaff));
		listItem.add(new MenuBean("Category", jpnCategory, jlbCategory));
		listItem.add(new MenuBean("Product", jpnProduct, jlbProduct));
		listItem.add(new MenuBean("Invoice", jpnInvoice, jlbInvoice));
		listItem.add(new MenuBean("Import", jpnImport, jlbImport));
		listItem.add(new MenuBean("Provider", jpnProvider, jlbProvider));
		listItem.add(new MenuBean("Sell", jpnSell, jlbSell));
		listItem.add(new MenuBean("Role", jpnfunction, jlbfunc));
		
		controller.setEvent(listItem);
	}
}