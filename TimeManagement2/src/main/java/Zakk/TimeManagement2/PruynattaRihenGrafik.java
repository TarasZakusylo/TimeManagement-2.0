package Zakk.TimeManagement2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PruynattaRihenGrafik extends JFrame {

	private static final long serialVersionUID = 1L;

	JButton Nazad;

	eHandler handler = new eHandler();

	private JLabel l_fon;

	public PruynattaRihenGrafik(String put) {
		super();

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		// setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		l_fon = new JLabel("");
		l_fon.setIcon(new ImageIcon(put));
		l_fon.setBounds(0, 0, 994, 565);
		getContentPane().add(l_fon);

		Nazad = new JButton("Назад");
		Nazad.setFont(new Font("Impact", Font.PLAIN, 25));
		Nazad.setBounds(712, 0, 282, 42);
		getContentPane().add(Nazad);

		Nazad.addActionListener(handler);

		setVisible(true);
	}

	public class eHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() == Nazad) {
					setVisible(false);
				}
			} catch (Exception ex) {
				System.err.println("  WTF  ");
			}
		}
	}
}
