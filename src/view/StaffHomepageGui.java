package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTree;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Scrollbar;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import java.awt.List;
import java.awt.Choice;

public class StaffHomepageGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffHomepageGui frame = new StaffHomepageGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StaffHomepageGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setBounds(100, 100, 744, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(37, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnViewPendingJobs = new JButton("Pending Jobs");
		btnViewPendingJobs.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnViewPendingJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CarServicePending frame = new CarServicePending();
					frame.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnViewPendingJobs.setBounds(264, 66, 203, 54);
		contentPane.add(btnViewPendingJobs);
		
		
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(-12, 53, 764, 23);
		contentPane.add(separator);
		
		JButton btnOngoingJobs = new JButton("Ongoing Jobs");
		btnOngoingJobs.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnOngoingJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				try {
					CarServiceOngoing frame = new CarServiceOngoing();
					frame.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnOngoingJobs.setBounds(264, 130, 203, 54);
		contentPane.add(btnOngoingJobs);
		
		JButton btnViewCompletedJob = new JButton("Completed Jobs");
		btnViewCompletedJob.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnViewCompletedJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CarServiceCompleted frame = new CarServiceCompleted();
					frame.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnViewCompletedJob.setBounds(264, 194, 203, 54);
		contentPane.add(btnViewCompletedJob);
		
		JButton btnCustomerInvoice = new JButton("Customer Invoice");
		btnCustomerInvoice.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnCustomerInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Invoice frame = new Invoice();
					frame.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnCustomerInvoice.setBounds(264, 323, 203, 54);
		contentPane.add(btnCustomerInvoice);
		
		JButton btnViewCancelledJobs = new JButton("Cancelled Jobs");
		btnViewCancelledJobs.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnViewCancelledJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CarServiceCancel frame = new CarServiceCancel();
					frame.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnViewCancelledJobs.setBounds(264, 259, 203, 54);
		contentPane.add(btnViewCancelledJobs);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffLoginGui frame = null;
				try {
					frame = new StaffLoginGui();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(true);
				dispose();
			}
		});
		btnLogOut.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnLogOut.setBounds(264, 387, 203, 54);
		contentPane.add(btnLogOut);
		
		JLabel lblNewLabel_2 = new JLabel("STAFF HOME MENU");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_2.setBounds(275, 11, 192, 32);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(StaffHomepageGui.class.getResource("/Images/car-service-key-features-A-1920x1080_tcm-3154-1323224.jpg")));
		lblNewLabel.setBounds(0, 0, 741, 548);
		contentPane.add(lblNewLabel);


	}
	
	
	
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}