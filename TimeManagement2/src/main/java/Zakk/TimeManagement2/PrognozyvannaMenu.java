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

public class PrognozyvannaMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel l_fon;
	private JLabel l_kartunka;
	private JButton b_Menu;
	private JButton b_PrognizuvannaZavantagenosti;
	private JButton b_PtognozuvannaPodij;

	public PrognozyvannaMenu(String s) {
		super(s);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		l_kartunka = new JLabel("");
		l_kartunka.setBounds(0, 66, 994, 341);
		l_kartunka.setHorizontalAlignment(SwingConstants.CENTER);
		l_kartunka.setIcon(new ImageIcon("res/kartunka/kartunta_PrognozMenu.png"));
		getContentPane().add(l_kartunka);

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

		b_PrognizuvannaZavantagenosti = new JButton("Прогнозування  завантаженості  дня");
		b_PrognizuvannaZavantagenosti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PrognizuvannaZavantagenosti("Times");
				setVisible(false);
			}
		});
		b_PrognizuvannaZavantagenosti.setFont(new Font("Impact", Font.PLAIN, 25));
		b_PrognizuvannaZavantagenosti.setBounds(12, 420, 482, 42);
		getContentPane().add(b_PrognizuvannaZavantagenosti);

		b_PtognozuvannaPodij = new JButton("Прогнозування подій");
		b_PtognozuvannaPodij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PtognozuvannaPodij("Times");
				setVisible(false);
			}
		});
		b_PtognozuvannaPodij.setFont(new Font("Impact", Font.PLAIN, 25));
		b_PtognozuvannaPodij.setBounds(494, 420, 485, 42);
		getContentPane().add(b_PtognozuvannaPodij);

		l_fon = new JLabel("");
		l_fon.setBounds(0, 0, 994, 566);
		l_fon.setForeground(Color.WHITE);
		l_fon.setIcon(new ImageIcon("res/fon/fon_Avtoruzacia.jpg"));
		getContentPane().add(l_fon);

		setVisible(true);
	}
}
