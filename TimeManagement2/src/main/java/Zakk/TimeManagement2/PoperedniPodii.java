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

public class PoperedniPodii extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel l_fon;
	private JLabel l_kartunka;
	private JLabel l_komentar;
	private JButton b_Menu;

	public PoperedniPodii(String s) {
		super(s);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		l_kartunka = new JLabel("");
		l_kartunka.setBounds(0, 319, 994, 247);
		l_kartunka.setHorizontalAlignment(SwingConstants.CENTER);
		l_kartunka.setIcon(new ImageIcon("res/kartunka/kartunta_Calendar.png"));
		getContentPane().add(l_kartunka);

		l_komentar = new JLabel("Оберіть, буль ласка, дату, що Вас цікавить :");
		l_komentar.setForeground(new Color(165, 42, 42));
		l_komentar.setHorizontalAlignment(SwingConstants.CENTER);
		l_komentar.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		l_komentar.setBounds(102, 47, 793, 42);
		getContentPane().add(l_komentar);

		b_Menu = new JButton("Меню");
		b_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Menu("Times");
				setVisible(false);
			}
		});
		b_Menu.setForeground(Color.BLACK);
		b_Menu.setFont(new Font("Impact", Font.PLAIN, 25));
		b_Menu.setBounds(712, 0, 282, 42);
		getContentPane().add(b_Menu);

		l_fon = new JLabel("");
		l_fon.setBounds(0, 0, 994, 566);
		l_fon.setForeground(Color.WHITE);
		l_fon.setIcon(new ImageIcon("res/fon/fon_Avtoruzacia.jpg"));
		getContentPane().add(l_fon);

		setVisible(true);
	}
}
