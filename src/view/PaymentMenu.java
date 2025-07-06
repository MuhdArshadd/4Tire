package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.PaymentController;
import model.Customer;
import model.Payment;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.awt.Desktop;
import java.io.File;
import java.awt.Color;


public class PaymentMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textInputPoint;
	private static Customer customer;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnEnter;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private static int ID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentMenu frame = new PaymentMenu(customer, ID);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public PaymentMenu(Customer customer, int ID) throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PAYMENT PAGE");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel.setBounds(235, 10, 166, 37);
		contentPane.add(lblNewLabel);
		
		textInputPoint = new JTextField();
		textInputPoint.setFont(new Font("Courier New", Font.PLAIN, 15));
		textInputPoint.setBounds(320, 201, 173, 30);
		contentPane.add(textInputPoint);
		textInputPoint.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Normal Amount (RM):");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_3.setBounds(92, 113, 206, 30);
		contentPane.add(lblNewLabel_3);
		
		JTextArea textNormalAmount = new JTextArea();
		textNormalAmount.setFont(new Font("Courier New", Font.PLAIN, 15));
		textNormalAmount.setText("0");
		textNormalAmount.setBounds(320, 121, 173, 30);
		contentPane.add(textNormalAmount);
		PaymentController paymentController = new PaymentController();
		new Payment();
		String amount = Double.toString(paymentController.actual(ID));
		
		double ori = paymentController.actual(ID);
		//String amount = Integer.toString(ID);
		textNormalAmount.setText(amount);
		
		JTextArea textPoint = new JTextArea();
		textPoint.setFont(new Font("Courier New", Font.PLAIN, 15));
		textPoint.setBounds(320, 161, 173, 30);
		contentPane.add(textPoint);
		//String data = Integer.toString(paymentController.LoyaltyPoint(payment, customer));
		String data = Integer.toString(customer.getLoyaltypoint());
		textPoint.setText(data);
		
		lblNewLabel_1 = new JLabel("Loyalty Point Available:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_1.setBounds(85, 161, 213, 30);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Redeem Loyalty Point:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_2.setBounds(92, 201, 200, 30);
		contentPane.add(lblNewLabel_2);
		
    	JTextArea textDiscount = new JTextArea();
    	textDiscount.setFont(new Font("Courier New", Font.PLAIN, 15));
		textDiscount.setText("0");
		textDiscount.setBounds(320, 253, 173, 30);
		contentPane.add(textDiscount);
		
		JTextArea textTotalAmount = new JTextArea();
		textTotalAmount.setFont(new Font("Courier New", Font.PLAIN, 15));
		textTotalAmount.setText("0");
		textTotalAmount.setBounds(320, 302, 173, 30);
		contentPane.add(textTotalAmount);
		
		JButton btnDoPayment = new JButton("PAY");
		btnDoPayment.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnDoPayment.setBounds(253, 425, 124, 37);
		contentPane.add(btnDoPayment);
		
		btnEnter = new JButton("ENTER");
		btnEnter.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String points = textInputPoint.getText();
				int point = Integer.valueOf(points);
				Payment payment = new Payment();
				if(point > customer.getLoyaltypoint())
				{
					JOptionPane.showMessageDialog(btnEnter, "The number of point exceed the available point");
                }
                else {
                	payment.setUsedLoyalty(point);
                	payment.setNormalAmount(Double.parseDouble(amount));

            		double dc = paymentController.discount(payment, customer);
            		String disc = Double.toString(paymentController.discount(payment, customer));
            		textDiscount.setText(disc);
            		payment.setDiscount(dc); 
            	
            		String total = Double.toString(paymentController.total(ori , dc));
            		
            		textTotalAmount.setText(total);
            		payment.setTotalAmount(Double.parseDouble(total));
            		
            		btnDoPayment.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent e) {
            				//JOptionPane.showMessageDialog(btnDoPayment, "Payment successful!");
            				//Payment payment = new Payment();
            				
            				PaymentController paymentController = new PaymentController();
            				try {
            					if(paymentController.DoPayment(payment, customer, ID) == true) {
            						JOptionPane.showMessageDialog(btnDoPayment, "Payment successful!");
            						paymentController.UpdatePoint(payment, customer);
            						paymentController.PlusPoint(payment, customer, ID);
            						int confirmation = JOptionPane.showConfirmDialog(btnDoPayment, "Do you want to print the receipt?", "Confirmation", JOptionPane.YES_NO_OPTION);
            						if (confirmation == JOptionPane.YES_OPTION) {
            							//test(customer, payment);
            							//printReceipt(customer, payment);
            							generateReceipt(customer, payment, ID);
            						}
            						ViewBooking frame = new ViewBooking(customer);
            						frame.setVisible(true);
            						dispose();
            					}
            					else if(paymentController.DoPayment(payment, customer, ID) == false) {
            						JOptionPane.showMessageDialog(btnDoPayment, "Payment unsuccessful.");
            					}
            				} catch(ClassNotFoundException | SQLException e1) {
            					e1.printStackTrace();
            				}
            			}
            		});
				}
			}
		});
		btnEnter.setBounds(253, 375, 124, 37);
		contentPane.add(btnEnter);
		
		lblNewLabel_4 = new JLabel("Discount:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_4.setBounds(201, 245, 97, 30);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Total Amount (RM) : ");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_5.setBounds(110, 295, 200, 37);
		contentPane.add(lblNewLabel_5);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnBack.setBounds(10, 23, 118, 37);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PaymentMenu.class.getResource("/Images/car-service-key-features-A-1920x1080_tcm-3154-1323224.jpg")));
		lblNewLabel_1.setBounds(0, 0, 625, 507);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel1 = new JLabel("New label");
		lblNewLabel1.setIcon(new ImageIcon("C:\\Users\\Acer\\Downloads\\imageOOP\\background.jpg"));
		lblNewLabel1.setBounds(0, 0, 733, 537);
		contentPane.add(lblNewLabel1);
		
		
	}
	
	/*private void printReceipt(Customer customer, Payment payment) {
	    PrinterJob job = PrinterJob.getPrinterJob();

	    
	    job.setPrintable((graphics, pageFormat, pageIndex) -> {
	        // Your receipt content drawing logic here using graphics object
	        // You can use the customer and payment information to construct the receipt
	    	
	    	graphics.setFont(new Font("Arial", Font.PLAIN, 12));
	        int y = 20;

	        graphics.drawString("Receipt", 100, y += 20);
	        graphics.drawString("Date: " + java.time.LocalDateTime.now(), 100, y += 20);
	        graphics.drawString("Customer: " + customer.getFirstName() + " " + customer.getLastName(), 100, y += 20);
	        graphics.drawString("Amount: $" + payment.getNormalAmount(), 100, y += 20);
	        graphics.drawString("Discount: $" + payment.getDiscount(), 100, y += 20);
	        graphics.drawString("Used Loyalty Points: " + payment.getUsedLoyalty(), 100, y += 20);
	        graphics.drawString("Total Amount: $" + payment.getTotalAmount(), 100, y += 20);

	        // Add more details as needed

	        graphics.drawString("Thank you for your purchase!", 100, y += 30);
	        graphics.drawString("Receipt Content", 100, 100);
	        return Printable.PAGE_EXISTS;
	    });

	    boolean doPrint = job.printDialog();
	    if (doPrint) {
	        try {
	            job.print();
	        } catch (PrinterException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error while printing receipt.");
	        }
	    }
	}*/
	
	public static void generateReceipt(Customer customer, Payment payment, int ID) {
        Document document = new Document();

        try {
        	PaymentController paymentController = new PaymentController();
            String serviceName = paymentController.getServiceName(ID);
            PdfWriter.getInstance(document, new FileOutputStream("receipt" + ID + ".pdf"));
            document.open();

            // Add receipt content
            addHeader(document);
            addInvoiceDetails(document, customer);
            addServiceDetails(document, serviceName, payment);
            addFooter(document);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the document outside the try block
            if (document != null && document.isOpen()) {
                document.close();

                // Open the generated PDF with the default PDF viewer
                try {
                    File file = new File("receipt" + ID + ".pdf");
                    Desktop.getDesktop().open(file);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private static void addHeader(Document document) throws DocumentException {
        Paragraph header = new Paragraph("CAR SERVICE AND REPAIR", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);

        Paragraph invoice = new Paragraph("INVOICE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
        invoice.setAlignment(Element.ALIGN_CENTER);
        document.add(invoice);

        document.add(Chunk.NEWLINE);
    }

    private static void addInvoiceDetails(Document document, Customer customer) throws DocumentException {
        document.add(new Paragraph("Invoice to: "));
        document.add(Chunk.NEWLINE);
        document.add(new Paragraph("Customer: " + customer.getFirstName() + " " + customer.getLastName()));
        document.add(new Paragraph("DATE: " + java.time.LocalDate.now()));
        document.add(Chunk.NEWLINE);
    }

   private static void addServiceDetails(Document document, String name, Payment payment) throws DocumentException {
    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(100);

    // Add Service Details header row
    table.addCell(createCell("Service Name :", Element.ALIGN_LEFT));
    table.addCell(createCell(name, Element.ALIGN_RIGHT));


    // Add service details
    table.addCell(createCell("Amount Price (RM) :", Element.ALIGN_LEFT));
    table.addCell(createCell(String.valueOf(payment.getNormalAmount()), Element.ALIGN_RIGHT));

    table.addCell(createCell("Used Loyalty Points :", Element.ALIGN_LEFT));
    table.addCell(createCell(String.valueOf(payment.getUsedLoyalty()), Element.ALIGN_RIGHT));

    table.addCell(createCell("Discount :", Element.ALIGN_LEFT));
    table.addCell(createCell(String.valueOf(payment.getDiscount()), Element.ALIGN_RIGHT));


    // Add Total Amount row
    table.addCell(createCell("Total Amount (RM) :", Element.ALIGN_LEFT));
    table.addCell(createCell(String.valueOf(payment.getTotalAmount()), Element.ALIGN_RIGHT));

    // Add the table to the document
    document.add(table);
    document.add(Chunk.NEWLINE);
}

    private static void addFooter(Document document) throws DocumentException {
        Paragraph thankYou = new Paragraph("THANK YOU!", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20));
        thankYou.setAlignment(Element.ALIGN_CENTER);
        document.add(thankYou);
    }

    private static PdfPCell createCell(String content, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(content, FontFactory.getFont(FontFactory.HELVETICA, 12)));
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(0);
        return cell;
    }
    
}
