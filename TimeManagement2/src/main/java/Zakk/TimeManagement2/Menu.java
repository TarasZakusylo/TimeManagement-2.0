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

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel l_fon;
	private JLabel l_kartunka;
	private JButton b_NovaPodia;
	private JButton b_PoperedniPodii;
	private JButton b_PruynattaRihen;
	private JButton b_Calendar;
	private JButton b_Prognozyvanna;
	private JButton b_MaybutniPodii;
	private JButton b_Zvit;
	private JButton b_Nalahtuvanna;
	private JButton b_Otocenna;

	public Menu(String s) {
		super(s);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		b_PoperedniPodii = new JButton("Попередні події");
		b_PoperedniPodii.setFont(new Font("Impact", Font.PLAIN, 25));
		b_PoperedniPodii.setForeground(new Color(0, 0, 0));
		b_PoperedniPodii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PoperedniPodii("Times");
				setVisible(false);
			}
		});
		b_PoperedniPodii.setBounds(37, 125, 282, 42);
		getContentPane().add(b_PoperedniPodii);

		b_PruynattaRihen = new JButton("Прийняття рішень");
		b_PruynattaRihen.setFont(new Font("Impact", Font.PLAIN, 25));
		b_PruynattaRihen.setForeground(new Color(0, 0, 0));
		b_PruynattaRihen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PruynattaRihen("Times");
				setVisible(false);
			}
		});
		b_PruynattaRihen.setBounds(37, 210, 282, 42);
		getContentPane().add(b_PruynattaRihen);

		b_Calendar = new JButton("Календар");
		b_Calendar.setFont(new Font("Impact", Font.PLAIN, 25));
		b_Calendar.setForeground(new Color(0, 0, 0));
		b_Calendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Calendar("Times");
				setVisible(false);
			}
		});
		b_Calendar.setBounds(667, 292, 282, 42);
		getContentPane().add(b_Calendar);

		b_Prognozyvanna = new JButton("Прогнозування");
		b_Prognozyvanna.setFont(new Font("Impact", Font.PLAIN, 25));
		b_Prognozyvanna.setForeground(new Color(0, 0, 0));
		b_Prognozyvanna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Prognozyvanna("Times");
				setVisible(false);
			}
		});
		b_Prognozyvanna.setBounds(667, 210, 282, 42);
		getContentPane().add(b_Prognozyvanna);

		b_MaybutniPodii = new JButton("Майбутні події");
		b_MaybutniPodii.setFont(new Font("Impact", Font.PLAIN, 25));
		b_MaybutniPodii.setForeground(new Color(0, 0, 0));
		b_MaybutniPodii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MaybutniPodii("Times");
				setVisible(false);
			}
		});
		b_MaybutniPodii.setBounds(667, 125, 282, 42);
		getContentPane().add(b_MaybutniPodii);

		b_NovaPodia = new JButton("Вказати нову подію");
		b_NovaPodia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new NovaPodia("Times");
				setVisible(false);
			}
		});
		b_NovaPodia.setForeground(Color.BLACK);
		b_NovaPodia.setFont(new Font("Impact", Font.PLAIN, 25));
		b_NovaPodia.setBounds(65, 40, 843, 42);
		getContentPane().add(b_NovaPodia);

		b_Zvit = new JButton("Звіт");
		b_Zvit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Zvit("Times");
				setVisible(false);
			}
		});
		b_Zvit.setForeground(Color.BLACK);
		b_Zvit.setFont(new Font("Impact", Font.PLAIN, 25));
		b_Zvit.setBounds(37, 377, 282, 42);
		getContentPane().add(b_Zvit);

		b_Nalahtuvanna = new JButton("Налаштування");
		b_Nalahtuvanna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Nalahtuvanna("Times");
				setVisible(false);
			}
		});
		b_Nalahtuvanna.setForeground(Color.BLACK);
		b_Nalahtuvanna.setFont(new Font("Impact", Font.PLAIN, 25));
		b_Nalahtuvanna.setBounds(667, 377, 282, 42);
		getContentPane().add(b_Nalahtuvanna);

		b_Otocenna = new JButton("Оточення");
		b_Otocenna.setFont(new Font("Impact", Font.PLAIN, 25));
		b_Otocenna.setForeground(new Color(0, 0, 0));
		b_Otocenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OtocennaOsnova();
				setVisible(false);
			}
		});
		b_Otocenna.setBounds(37, 292, 282, 42);
		getContentPane().add(b_Otocenna);

		l_kartunka = new JLabel("");
		l_kartunka.setHorizontalAlignment(SwingConstants.CENTER);
		l_kartunka.setIcon(new ImageIcon("C:\\Users\\ZakkZakk\\Desktop\\vremya-dengi.png"));
		l_kartunka.setBounds(-1, 263, 995, 302);
		getContentPane().add(l_kartunka);

		l_fon = new JLabel("");
		l_fon.setForeground(Color.WHITE);
		l_fon.setIcon(new ImageIcon("C:\\Users\\ZakkZakk\\Desktop\\fon_Avtoruzacia.jpg"));
		l_fon.setBounds(-1, 0, 995, 565);
		getContentPane().add(l_fon);

		setVisible(true);
	}
}
