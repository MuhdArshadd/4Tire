package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import database.MyDatabase;

public class Invoice extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection conn;
	private JTable Invoice_Table;
	private JScrollPane scrollPane;
	private JButton btnLoadTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Invoice frame = new Invoice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Invoice() throws ClassNotFoundException, SQLException {
		conn = MyDatabase.doConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 744, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInvoiceTitle = new JLabel("Customer Invoices\r\n");
		lblInvoiceTitle.setForeground(new Color(255, 255, 255));
		lblInvoiceTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblInvoiceTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvoiceTitle.setBackground(new Color(255, 255, 255));
		lblInvoiceTitle.setBounds(270, 0, 182, 51);
		contentPane.add(lblInvoiceTitle);
	
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(-20, 50, 764, 23);
		contentPane.add(separator);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 110, 623, 277);
		contentPane.add(scrollPane);
		
		Invoice_Table = new JTable();
		scrollPane.setViewportView(Invoice_Table);
		
		JButton btnBack = new JButton("Back\r\n");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        			StaffHomepageGui frame = new StaffHomepageGui();
					frame.setVisible(true);
					dispose();
        	}
        });
        btnBack.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnBack.setBounds(245, 398, 125, 37);
        contentPane.add(btnBack);
        setVisible(true);

		
		btnLoadTable = new JButton("Load Invoice\r\n");
		btnLoadTable.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnLoadTable.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String sql = "SELECT InvoiceID,SlotID,InvoiceDate,NormalAmount,Discount,TotalAmount FROM invoice";
		            PreparedStatement preparedStatement = conn.prepareStatement(sql);
		            ResultSet result = preparedStatement.executeQuery();

		            // Create a DefaultTableModel to store the data
		            DefaultTableModel model = new DefaultTableModel(
		                new String[] {"Invoice ID", "Slot ID", "Date", "Normal Price", "Discount", "Total"},
		                0
		            );

		            // Iterate through the ResultSet and add rows to the model
		            while (result.next()) {
		                String invoiceID = result.getString("InvoiceID");
		                String slotID = result.getString("SlotID");
		                String date = result.getString("InvoiceDate");
		                String normalPrice = "RM" + result.getString("NormalAmount");
		                String discount = "RM" + result.getString("Discount");
		                String total = "RM" + result.getString("TotalAmount");


		                model.addRow(new String[]{invoiceID, slotID, date, normalPrice, discount, total});
		            } 

		            // Set the populated model to the Invoice_Table
		            Invoice_Table.setModel(model);

		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});

		btnLoadTable.setBounds(380, 398, 125, 37);
		contentPane.add(btnLoadTable);
	
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Invoice.class.getResource("/Images/car-service-key-features-A-1920x1080_tcm-3154-1323224.jpg")));
		lblNewLabel.setBounds(0, 0, 741, 548);
		contentPane.add(lblNewLabel);
		
		
		
	}
}