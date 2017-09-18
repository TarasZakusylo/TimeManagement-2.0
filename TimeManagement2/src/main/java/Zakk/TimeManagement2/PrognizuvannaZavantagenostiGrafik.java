package Zakk.TimeManagement2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PrognizuvannaZavantagenostiGrafik extends JFrame {

	private static final long serialVersionUID = 1L;

	JButton Menu, Nazad;

	eHandler handler = new eHandler();

	private JLabel l_fon;

	public PrognizuvannaZavantagenostiGrafik(int s_Misatc, int i_Chislo) {
		super();

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		// setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		Menu = new JButton("Меню");
		Menu.setFont(new Font("Impact", Font.PLAIN, 25));
		Nazad = new JButton("Назад");
		Nazad.setFont(new Font("Impact", Font.PLAIN, 25));

		Menu.setBounds(712, 0, 282, 42);
		Nazad.setBounds(712, 39, 282, 42);

		getContentPane().add(Menu);
		getContentPane().add(Nazad);

		l_fon = new JLabel("");
		l_fon.setIcon(new ImageIcon("res/TumcasoviFaylu/Prognoz/Chart/" + s_Misatc + "." + i_Chislo + ".png"));
		l_fon.setBounds(0, 0, 994, 565);
		getContentPane().add(l_fon);

		Menu.addActionListener(handler);
		Nazad.addActionListener(handler);

		File myFile1 = new File("res/TumcasoviFaylu/Prognoz/Chart");
		delete(myFile1);

		setVisible(true);
	}

	private void delete(File file) {
		if (!file.exists())
			return;
		if (file.isDirectory()) {
			for (File f : file.listFiles())
				delete(f);
			file.delete();
		} else {
			file.delete();
		}

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
