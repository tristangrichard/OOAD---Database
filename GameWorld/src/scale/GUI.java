package scale;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUpper;
	private JTextField txtLower;
	private JTextField textBrutto;
	private JButton btnOk;
	private JButton btnCancel;
	private GUI frame;
	private String input = "";
	private String response = "";
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private Scale myScale;

	/**
	 * Create the frame.
	 */
	public GUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 235);
		contentPane = new JPanel();
		contentPane.setLocation(new Point(5, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnDot = new JButton(".");
		buttonGroup_1.add(btnDot);
		btnDot.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JButton btn_0 = new JButton("0");
		buttonGroup_1.add(btn_0);

		JButton btnClear = new JButton("Clear");
		buttonGroup_1.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input = "";
				txtLower.setText(input);
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JButton btn_2 = new JButton("2");
		buttonGroup_1.add(btn_2);

		JButton btn_3 = new JButton("3");
		buttonGroup_1.add(btn_3);

		JButton btn_1 = new JButton("1");
		buttonGroup_1.add(btn_1);

		JButton btn_4 = new JButton("4");
		buttonGroup_1.add(btn_4);

		JButton btn_5 = new JButton("5");
		buttonGroup_1.add(btn_5);

		JButton btn_6 = new JButton("6");
		buttonGroup_1.add(btn_6);

		JButton btn_9 = new JButton("9");
		buttonGroup_1.add(btn_9);

		JButton btn_8 = new JButton("8");
		buttonGroup_1.add(btn_8);

		JButton btn_7 = new JButton("7");
		buttonGroup_1.add(btn_7);

		JButton btnT = new JButton("<T>");

		textBrutto = new JTextField();
		textBrutto.setEditable(true);
		textBrutto.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		JLabel lblBruttoVgt = new JLabel("Brutto v\u00E6gt");

		txtUpper = new JTextField();
		txtUpper.setEditable(false);
		txtUpper.setMargin(new Insets(2, 10, 2, 10));
		txtUpper.setHorizontalAlignment(SwingConstants.RIGHT);
		txtUpper.setText("0.000 kg");
		txtUpper.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUpper.setForeground(Color.CYAN);
		txtUpper.setDisabledTextColor(Color.CYAN);
		txtUpper.setBackground(Color.BLACK);
		txtUpper.setColumns(10);

		txtLower = new JTextField();
		txtLower.setEditable(false);
		txtLower.setMargin(new Insets(2, 10, 2, 10));
		txtLower.setHorizontalAlignment(SwingConstants.LEFT);
		txtLower.setForeground(Color.CYAN);
		txtLower.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtLower.setDisabledTextColor(Color.CYAN);
		txtLower.setColumns(10);
		txtLower.setBackground(Color.BLACK);
		txtLower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				response = e.getActionCommand();
				myScale.put(response);
				txtLower.setEditable(false);
			}
		});

		JButton btnTransfer = new JButton("Transfer");

		btnOk = new JButton("OK");
		btnOk.setVisible(false);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				response = txtLower.getText();
				Scale.updateWeight();
				txtLower.setText("");
				myScale.put(response);
				btnOk.setVisible(false);
				btnCancel.setVisible(false);
				response = "";
				input = "";
			}
		});
		btnCancel = new JButton("Cancel");
		btnCancel.setVisible(false);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				response = "RM20 B";
				Scale.updateWeight();
				txtLower.setText("");
				myScale.put(response);
				btnOk.setVisible(false);
				btnCancel.setVisible(false);
				response = "";
				input = "";
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(10)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtUpper, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtLower, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnTransfer)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnOk))
										.addComponent(btnTransfer)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(btnT, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
												.addGap(20)
												.addComponent(textBrutto, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblBruttoVgt, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnCancel)))
												.addGap(18)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_contentPane.createSequentialGroup()
																.addGap(2)
																.addComponent(btn_7, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(btn_8, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(btn_9, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_contentPane.createSequentialGroup()
																		.addGap(1)
																		.addComponent(btn_4, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
																		.addGap(6)
																		.addComponent(btn_5, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
																		.addGap(6)
																		.addComponent(btn_6, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
																		.addGroup(gl_contentPane.createSequentialGroup()
																				.addGap(1)
																				.addComponent(btn_1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
																				.addGap(6)
																				.addComponent(btn_2, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
																				.addGap(6)
																				.addComponent(btn_3, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
																				.addGroup(gl_contentPane.createSequentialGroup()
																						.addGap(1)
																						.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
																						.addGap(6)
																						.addComponent(btn_0, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
																						.addGap(6)
																						.addComponent(btnDot, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
																						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(btn_9, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
												.addComponent(btn_8, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
												.addComponent(btn_7, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
												.addGap(6)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(btn_4, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
														.addComponent(btn_5, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
														.addComponent(btn_6, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(txtUpper, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
																.addGap(6)
																.addComponent(txtLower, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
																.addGap(6)
																.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_contentPane.createSequentialGroup()
																				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																						.addComponent(btn_1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
																						.addComponent(btn_2, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
																						.addComponent(btn_3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
																						.addGap(6)
																						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																								.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
																								.addComponent(btn_0, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
																								.addComponent(btnDot, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
																								.addComponent(btnCancel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
																								.addGroup(gl_contentPane.createSequentialGroup()
																										.addComponent(btnTransfer, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
																										.addGap(5)
																										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																												.addComponent(btnT, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
																												.addGroup(gl_contentPane.createSequentialGroup()
																														.addGap(5)
																														.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
																																.addComponent(lblBruttoVgt, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
																																.addComponent(textBrutto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
																																.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
																																.addGap(32))
				);

		contentPane.setLayout(gl_contentPane);
		textBrutto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Scale.setBrutto(Double.parseDouble(e.getActionCommand()));
				} catch (NumberFormatException error) {
					error.getMessage();
				}
			}
		});
		btnT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Scale.k3) {
					Scale.setTara(Scale.getBrutto());
				} else {
					System.out.println("K C 3 sendt");
					Scale.outstream("K C 3" + "\r\n");
				}
			}
		});
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!Scale.k3) {
					// Skal ikke sende noget hvis ikke K 3
				} else {
					System.out.println("K C 4 sendt");
					Scale.outstream("K C 4" + "\r\n");
				}
			}
		});
		GUINumberButtons gnb = new GUINumberButtons();
		btn_0.addActionListener(gnb);
		btn_2.addActionListener(gnb);
		btn_3.addActionListener(gnb);
		btn_1.addActionListener(gnb);
		btn_4.addActionListener(gnb);
		btn_5.addActionListener(gnb);
		btn_6.addActionListener(gnb);
		btn_9.addActionListener(gnb);
		btn_8.addActionListener(gnb);
		btn_7.addActionListener(gnb);
	}

	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void getrmTwentyResponse(Scale s) {
		btnOk.setVisible(true);
		btnCancel.setVisible(true);
		txtLower.setEditable(true);
		myScale = s;
	}

	public void setUpperField(String arg) {
		txtUpper.setText(arg);
	}

	private class GUINumberButtons implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			input = input + arg0.getActionCommand();
			txtLower.setText(input);
		}
	}
}
