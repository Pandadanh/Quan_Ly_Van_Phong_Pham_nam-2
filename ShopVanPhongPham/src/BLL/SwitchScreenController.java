package BLL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.security.Provider;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

import DAO.add_account;
import DTO.Account;
import DTO.MenuBean;
import DTO.role;
import GUI.AccountPanel;
import GUI.CategoryPanel;
import GUI.CustomerPanel;
import GUI.DashboardPanel;
import GUI.ImportPanel;
import GUI.InvoicePanel;
import GUI.ProductPanel;
import GUI.ProviderPanel;
import GUI.Role_panel;
import GUI.SellPanel;
import GUI.StaffPanel;
import GUI.mess_role;

public class SwitchScreenController {
	private JPanel root;
	private String kindSelected = "";
	private int id_user = 1;
	private List<MenuBean> listItem = null;

	private List<String> list_fuc_str = null;
	add_account add_account = new add_account();
	
	Account account = new Account();
	public SwitchScreenController(JPanel jpnRoot,int id_user) {
		this.root = jpnRoot;
		this.id_user=id_user;
		account =  add_account.add(id_user);
		
		list_fuc_str=add_account.getNameFuncByIdFuncArr(add_account.getIdFuncByMaLevel(id_user));
		System.out.println(list_fuc_str);
	}
	
	public void setView(JPanel jpnItem, JLabel jlbItem) {
		kindSelected = "Dashboard";
		
//		jpnItem.setBackground(new Color(255, 255, 255));
		
		root.removeAll();
		root.setLayout(new BorderLayout());
		root.add(new DashboardPanel());
		root.validate();
		root.repaint();
	}
	
	public void setEvent(List<MenuBean> listItem) {
		this.listItem = listItem;
		
		for (MenuBean item : listItem) {
			item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
		}
	}
	
	class LabelEvent implements MouseListener {
		private JPanel node;
		private String kind;
		
		private JPanel jpnItem;
		private JLabel jlbItem;
		
		
		
		
		
		public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
			this.kind = kind;
			this.jpnItem = jpnItem;
			this.jlbItem = jlbItem;
		}

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			switch (kind) {
			case "Dashboard": {
				node = new DashboardPanel();
				break;
			}
			case "Account": {
				if(list_fuc_str.contains("account") == false) {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập !!!");
					node = new DashboardPanel();
				}
				else
					node = new AccountPanel(id_user);
				
				break;
			}
			case "Customer": {
				if(list_fuc_str.contains("customer") == false) {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập !!!");
					node = new DashboardPanel();
				}
				else
					node = new CustomerPanel(id_user);
				break;
			}
			case "Staff": {
				if(list_fuc_str.contains("staff") == false) {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập !!!");
					node = new DashboardPanel();
				}
				else
				node = new StaffPanel(id_user);
				break;
			}
			case "Category": {
				if(list_fuc_str.contains("category") == false) {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập !!!");
					node = new DashboardPanel();
				}
				else
				node = new CategoryPanel(id_user);
				break;
			}
			case "Product": {
				if(list_fuc_str.contains("product") == false) {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập !!!");
					node = new DashboardPanel();
				}
				else
				node = new ProductPanel(id_user);
				break;
			}case "Import": {
				if(list_fuc_str.contains("import") == false) {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập !!!");
					node = new DashboardPanel();
				}
				else
				node = new ImportPanel(id_user);
				break;
			}case "Invoice": {
				if(list_fuc_str.contains("invoice") == false) {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập !!!");
					node = new DashboardPanel();
				}
				else
				node = new InvoicePanel(id_user);
				break;
			}case "Provider": {
				if(list_fuc_str.contains("provider") == false) {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập !!!");
					node = new DashboardPanel();
				}
				else
				node = new ProviderPanel(id_user);
				break;
			}case "Sell": {
				if(list_fuc_str.contains("sell") == false) {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập !!!");
					node = new DashboardPanel();
				}
				else
				node = new SellPanel(id_user);
				break;
			}
			case "Role": {
				if(list_fuc_str.contains("role") == false) {
					JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập !!!");
					node = new DashboardPanel();
				}
				else
				{
					
						node = new mess_role(id_user);

				}
				break;
			}
			
			default:
				node = new DashboardPanel();
				break;
			}
			root.removeAll();
			root.setLayout(new BorderLayout());
			root.add(node);
			root.repaint();
			root.validate();
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			kindSelected = kind;
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
