package Zakk.TimeManagement2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Formatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Reestrasia extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel label_NazvaVikna;
	private JLabel label_Login;
	private JTextField textField_Login;
	private JLabel label_Password;
	private JPasswordField passwordField_Password;
	private JButton button_Nazad;
	private JLabel label_PIP;
	private JTextField textField_PIP;
	private JLabel label_email;
	private JTextField textField_mail;
	private JButton button_Gotovo;
	private JLabel l_fon;
	private JLabel l_kartunka;

	public Reestrasia(String s) {
		super(s);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		label_NazvaVikna = new JLabel("Реєстрація");
		label_NazvaVikna.setForeground(new Color(0, 0, 0));
		label_NazvaVikna.setBounds(12, 13, 497, 88);
		label_NazvaVikna.setFont(new Font("Impact", Font.PLAIN, 55));
		label_NazvaVikna.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(label_NazvaVikna);

		label_Login = new JLabel("Логін");
		label_Login.setForeground(new Color(165, 42, 42));
		label_Login.setBounds(68, 122, 369, 29);
		label_Login.setHorizontalAlignment(SwingConstants.CENTER);
		label_Login.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		getContentPane().add(label_Login);

		textField_Login = new JTextField();
		textField_Login.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		textField_Login.setBounds(68, 164, 369, 36);
		textField_Login.setToolTipText("");
		textField_Login.setText("1");
		textField_Login.setColumns(10);
		getContentPane().add(textField_Login);

		label_Password = new JLabel("Пароль");
		label_Password.setForeground(new Color(165, 42, 42));
		label_Password.setBounds(68, 213, 369, 29);
		label_Password.setHorizontalAlignment(SwingConstants.CENTER);
		label_Password.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		getContentPane().add(label_Password);

		passwordField_Password = new JPasswordField();
		passwordField_Password.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		passwordField_Password.setBounds(68, 255, 369, 36);
		passwordField_Password.setToolTipText("");
		passwordField_Password.setText("1");
		getContentPane().add(passwordField_Password);

		button_Nazad = new JButton("Назад");
		button_Nazad.setForeground(new Color(0, 0, 0));
		button_Nazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Avtoruzacia("Times");
				setVisible(false);
			}
		});
		button_Nazad.setBounds(34, 501, 219, 36);
		button_Nazad.setFont(new Font("Impact", Font.PLAIN, 25));
		getContentPane().add(button_Nazad);

		label_PIP = new JLabel("Ім'я та Прізвище");
		label_PIP.setForeground(new Color(165, 42, 42));
		label_PIP.setBounds(68, 304, 369, 29);
		label_PIP.setHorizontalAlignment(SwingConstants.CENTER);
		label_PIP.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		getContentPane().add(label_PIP);

		textField_PIP = new JTextField();
		textField_PIP.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		textField_PIP.setBounds(68, 346, 369, 36);
		textField_PIP.setToolTipText("");
		textField_PIP.setText("1");
		textField_PIP.setColumns(10);
		getContentPane().add(textField_PIP);

		label_email = new JLabel("e-mail");
		label_email.setForeground(new Color(165, 42, 42));
		label_email.setBounds(68, 395, 369, 29);
		label_email.setHorizontalAlignment(SwingConstants.CENTER);
		label_email.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		getContentPane().add(label_email);

		textField_mail = new JTextField();
		textField_mail.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		textField_mail.setBounds(68, 437, 369, 36);
		textField_mail.setToolTipText("");
		textField_mail.setText("1");
		getContentPane().add(textField_mail);

		button_Gotovo = new JButton("Готово");
		button_Gotovo.setForeground(new Color(0, 0, 0));
		button_Gotovo.setBounds(252, 501, 214, 36);
		button_Gotovo.addActionListener(new ActionListener() {

			private String s_Login;
			private String s_Password;
			private String s_PIP;
			private String s_mail;

			private Formatter formatter_reestracia;
			private String s_reestracia;

			private int i_korektnist = 0;
			private int i_korektnist1 = 0;

			@SuppressWarnings("deprecation")

			public void actionPerformed(ActionEvent arg0) {

				s_Login = textField_Login.getText();
				s_Password = passwordField_Password.getText();
				s_PIP = textField_PIP.getText();
				s_mail = textField_mail.getText();

				File file = new File("res/Avtoruzacia/" + s_Login + ".txt");

				if (file.exists()) {
					JOptionPane.showMessageDialog(null, "Уже зареєстрований користувач під даним логіном");
				} else {

					int blockCount = s_PIP.split(" ").length;

					if (blockCount != 2) {
						JOptionPane.showMessageDialog(null, "Некоректне ім'я чи прізвище");
					} else {
						char[] chars = s_PIP.toCharArray();
						for (int i = 0; i < chars.length; i++) {
							try {
								if (chars[i] == '1' || chars[i] == '0' || chars[i] == '(' || chars[i] == ')'
										|| chars[i] == '+' || chars[i] == '=' || chars[i] == '/' || chars[i] == '.'
										|| chars[i] == ',') {
									i_korektnist++;
								}
							} catch (Exception e) {
							}
						}
						char[] chars1 = s_mail.toCharArray();
						for (int i = 0; i < chars1.length; i++) {
							try {
								if (chars1[i] == '@') {
									i_korektnist1 = 1;
								}
							} catch (Exception e) {
							}
						}
						if (i_korektnist == 0 && i_korektnist1 == 1) {

							try {
								formatter_reestracia = new Formatter("res/Avtoruzacia/" + s_Login + ".txt");
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "System Error");
							}

							s_reestracia = s_Password + " " + s_PIP + " " + s_mail;

							formatter_reestracia.format(s_reestracia);
							formatter_reestracia.close();

							JOptionPane.showMessageDialog(null, "Зареєстровано");
							new Avtoruzacia("Times");
							setVisible(false);

						} else {
							JOptionPane.showMessageDialog(null, "Перевірте введення");
						}
						i_korektnist = 0;

					}
				}

			}
		});
		button_Gotovo.setFont(new Font("Impact", Font.PLAIN, 25));
		getContentPane().add(button_Gotovo);

		l_kartunka = new JLabel("");
		l_kartunka.setIcon(new ImageIcon("res/kartunka/kartunka_Reestracia.png"));
		l_kartunka.setBounds(449, 0, 546, 565);
		getContentPane().add(l_kartunka);

		l_fon = new JLabel("");
		l_fon.setForeground(Color.WHITE);
		l_fon.setIcon(new ImageIcon("res/fon/fon_Avtoruzacia.jpg"));
		l_fon.setBounds(0, 0, 995, 565);
		getContentPane().add(l_fon);

		setVisible(true);
	}
}
