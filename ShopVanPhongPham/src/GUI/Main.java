	package GUI;
	
	import java.awt.Color;
	import java.awt.EventQueue;
	
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;
	
	public class Main extends JFrame {
		public static int id = 3;
		private JPanel jpnSideBar;
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						
						Main frame = new Main(id);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		public Main(int id) {
			this.id = id;
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1200, 700);
			jpnSideBar = new SideBarPanel(id);
			jpnSideBar.setBackground(new Color(255, 255, 255));
			jpnSideBar.setBorder(new EmptyBorder(5, 5, 5, 5));
	
			setContentPane(jpnSideBar);
			jpnSideBar.setLayout(null);
			
			initComponets();
			setTitle("QUAN LY BAN HANG VAN PHONG PHAM"); 
			
			JPanel jpnHeader = new HeaderPanel(id);
			jpnHeader.setBackground(new Color(255, 255, 255));
			jpnHeader.setBorder(new EmptyBorder(5, 5, 5, 5));
			jpnHeader.setBounds(0, 0, 1200, 55);
			jpnSideBar.add(jpnHeader);
			
		}
	
		
	
		private void initComponets() {}
	}