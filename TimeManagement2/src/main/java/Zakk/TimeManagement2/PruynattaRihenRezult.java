package Zakk.TimeManagement2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PruynattaRihenRezult extends JFrame {

	private static final long serialVersionUID = 1L;

	JButton Menu, Nazad;

	JLabel l_Nazva1, l_Nazva2, Nay;
	JLabel l_u1, l_u2, l_u3, l_u4, l_u5, l_u6, l_u7, l_u8, l_u9, l_u10, l_u11, l_u12, l_q1, l_q2, l_q3;

	String s_Nay = "";

	String Fayl;

	String f = "res/rihenna2.png";
	ImageIcon logo = new ImageIcon(f);
	JLabel l_logo = new JLabel(new ImageIcon("res/TPR_tabl.png"));

	String Re1, Re2;

	eHandler handler = new eHandler();
	private JLabel l_fon;

	@SuppressWarnings("unused")
	
	public PruynattaRihenRezult(String s_Nazva1, String s_Nazva2, double u1, double u2, double u3, double u4, double u5,
			double u6, double u7, double u8, double u9, double u10, double u11, double u12, double q1, double q2,
			double q3) {
		super();

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		Menu = new JButton("Меню");
		Menu.setFont(new Font("Impact", Font.PLAIN, 25));
		Nazad = new JButton("Назад");
		Nazad.setFont(new Font("Impact", Font.PLAIN, 25));

		l_Nazva1 = new JLabel("" + s_Nazva1);
		l_Nazva1.setHorizontalAlignment(SwingConstants.CENTER);
		l_Nazva1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		l_Nazva2 = new JLabel("" + s_Nazva2);
		l_Nazva2.setHorizontalAlignment(SwingConstants.CENTER);
		l_Nazva2.setFont(new Font("Times New Roman", Font.PLAIN, 25));

		l_u1 = new JLabel("" + u1);
		l_u1.setHorizontalAlignment(SwingConstants.CENTER);
		l_u1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		l_u2 = new JLabel("" + u2);
		l_u2.setHorizontalAlignment(SwingConstants.CENTER);
		l_u2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		l_u3 = new JLabel("" + u3);
		l_u3.setHorizontalAlignment(SwingConstants.CENTER);
		l_u3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		l_u4 = new JLabel("" + u4);
		l_u4.setHorizontalAlignment(SwingConstants.CENTER);
		l_u4.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		l_u5 = new JLabel("" + u5);
		l_u5.setHorizontalAlignment(SwingConstants.CENTER);
		l_u5.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		l_u6 = new JLabel("" + u6);
		l_u6.setHorizontalAlignment(SwingConstants.CENTER);
		l_u6.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		l_u7 = new JLabel("" + u7);
		l_u7.setHorizontalAlignment(SwingConstants.CENTER);
		l_u7.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		l_u8 = new JLabel("" + u8);
		l_u8.setHorizontalAlignment(SwingConstants.CENTER);
		l_u8.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		l_u9 = new JLabel("" + u9);
		l_u9.setHorizontalAlignment(SwingConstants.CENTER);
		l_u9.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		l_u10 = new JLabel("" + u10);
		l_u10.setHorizontalAlignment(SwingConstants.CENTER);
		l_u10.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		l_u11 = new JLabel("" + u11);
		l_u11.setHorizontalAlignment(SwingConstants.CENTER);
		l_u11.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		l_u12 = new JLabel("" + u12);
		l_u12.setHorizontalAlignment(SwingConstants.CENTER);
		l_u12.setFont(new Font("Times New Roman", Font.PLAIN, 25));

		l_q1 = new JLabel("" + q1);
		l_q1.setHorizontalAlignment(SwingConstants.CENTER);
		l_q1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		l_q2 = new JLabel("" + q2);
		l_q2.setHorizontalAlignment(SwingConstants.CENTER);
		l_q2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		l_q3 = new JLabel("" + q3);
		l_q3.setHorizontalAlignment(SwingConstants.CENTER);
		l_q3.setFont(new Font("Times New Roman", Font.PLAIN, 25));

		Menu.setBounds(712, 0, 282, 42);
		Nazad.setBounds(430, 0, 282, 42);

		l_Nazva1.setBounds(12, 271, 239, 29);
		l_Nazva2.setBounds(12, 373, 239, 29);

		l_u1.setBounds(263, 205, 137, 90);
		l_u2.setBounds(412, 205, 132, 90);
		l_u3.setBounds(556, 205, 138, 93);
		l_u4.setBounds(706, 208, 131, 90);
		l_u5.setBounds(265, 309, 135, 88);
		l_u6.setBounds(412, 308, 132, 89);
		l_u7.setBounds(556, 308, 138, 89);
		l_u8.setBounds(706, 311, 131, 86);
		l_u9.setBounds(263, 410, 137, 89);
		l_u10.setBounds(412, 410, 132, 89);
		l_u11.setBounds(556, 410, 138, 89);
		l_u12.setBounds(706, 410, 131, 89);

		l_q1.setBounds(852, 208, 132, 90);
		l_q2.setBounds(852, 308, 132, 89);
		l_q3.setBounds(852, 410, 132, 89);

		getContentPane().add(l_Nazva1);
		getContentPane().add(l_Nazva2);

		getContentPane().add(l_u1);
		getContentPane().add(l_u2);
		getContentPane().add(l_u3);
		getContentPane().add(l_u4);
		getContentPane().add(l_u5);
		getContentPane().add(l_u6);
		getContentPane().add(l_u7);
		getContentPane().add(l_u8);
		getContentPane().add(l_u9);
		getContentPane().add(l_u10);
		getContentPane().add(l_u11);
		getContentPane().add(l_u12);

		getContentPane().add(l_q1);
		getContentPane().add(l_q2);
		getContentPane().add(l_q3);

		getContentPane().add(Menu);
		getContentPane().add(Nazad);

		l_logo.setBounds(0, 44, 994, 465);

		int y = 0;

		if (q1 < q2) {
			if (q2 < q3) {
				s_Nay = "Найкращою альтернативою є виконання обох подій одночасно,\n або поетапно";
				y = 10;

			} else {
				s_Nay = ("Найкращою альтернативою є відміна (перенесення) події - " + s_Nazva2);
				y = 50;
			}
		} else {
			s_Nay = ("Найкращою альтернативою є відміна (перенесення) події - " + s_Nazva1);
			y = 50;
		}

		Nay = new JLabel(s_Nay);
		Nay.setForeground(new Color(165, 42, 42));
		Nay.setHorizontalAlignment(SwingConstants.CENTER);
		Nay.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 27));

		Nay.setBounds(12, 523, 970, 29);

		getContentPane().add(Nay);

		getContentPane().add(l_logo);

		l_fon = new JLabel("");
		l_fon.setIcon(new ImageIcon("res/fon/fon_Avtoruzacia.jpg"));
		l_fon.setBounds(0, 0, 994, 565);
		getContentPane().add(l_fon);

		Menu.addActionListener(handler);
		Nazad.addActionListener(handler);

		setVisible(true);
	}

	public class eHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			try {
				if (e.getSource() == Menu) {

					new Menu("");
					setVisible(false);

				}
				if (e.getSource() == Nazad) {

					setVisible(false);
				}

			} catch (Exception ex) {
				System.err.println("  WTF  ");
			}
		}
	}

}
