package Zakk.TimeManagement2;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Calendar extends JFrame implements ItemListener {

	private static final long serialVersionUID = 1L;

	private JLabel l_fon;
	private JLabel l_kartunka;

	JPanel p2;
	JComboBox<String> month = new JComboBox<String>();
	JComboBox<Integer> year;
	int days[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	String weekdays[] = { "Неділя", "Понеділок", "Вівторок", "Середа", "Четвер", "П'ятниця", "Субота" };
	String months[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };
	private JButton btnNewButton;

	private JButton b_menu;
	private JLabel l_komentar;

	@SuppressWarnings("deprecation")

	public Calendar(String s) {
		super(s);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		for (int i = 0; i < months.length; i++) {
			month.addItem(months[i]);

		}
		month.setFont(new Font("Impact", Font.PLAIN, 20));
		month.setBounds(221, 102, 210, 28);
		month.addItemListener(this);
		year = new JComboBox<Integer>();
		year.setFont(new Font("Impact", Font.PLAIN, 20));
		year.setBounds(443, 102, 210, 28);
		for (int i = 1980; i <= 2099; i++) {
			year.addItem(i);
		}
		year.addItemListener(this);
		getContentPane().setLayout(null);
		getContentPane().add(month);
		getContentPane().add(year);
		p2 = new JPanel();
		// p2.setBackground(Color.RED);
		p2.setBounds(221, 149, 554, 152);
		p2.setLayout(new GridLayout(0, 7, 5, 5));
		final Date date = new Date();

		drawCalendar(months[date.getMonth()], (1900 + date.getYear()));
		year.setSelectedItem((1900 + date.getYear()));
		month.setSelectedItem(months[date.getMonth()]);
		// Container c = getContentPane();

		getContentPane().add(p2);

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

		b_menu = new JButton("Меню");
		b_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Menu("Times");
				setVisible(false);
			}
		});
		b_menu.setForeground(Color.BLACK);
		b_menu.setFont(new Font("Impact", Font.PLAIN, 25));
		b_menu.setBounds(712, 0, 282, 42);
		getContentPane().add(b_menu);

		btnNewButton = new JButton("Сьогодні");
		btnNewButton.setFont(new Font("Impact", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawCalendar(months[date.getMonth()], (1900 + date.getYear()));
				year.setSelectedItem((1900 + date.getYear()));
				month.setSelectedItem(months[date.getMonth()]);
			}
		});
		btnNewButton.setBounds(665, 101, 110, 30);
		getContentPane().add(btnNewButton);

		l_fon = new JLabel("");
		l_fon.setBounds(0, 0, 994, 566);
		l_fon.setForeground(Color.WHITE);
		l_fon.setIcon(new ImageIcon("res/fon/fon_Avtoruzacia.jpg"));
		getContentPane().add(l_fon);

		setVisible(true);
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			drawCalendar((String) month.getSelectedItem(), (Integer) year.getSelectedItem());
		}
	}

	@SuppressWarnings({ "deprecation", "unused" })

	public void drawCalendar(String inputMonth, int inputYear) {
		p2.removeAll();
		for (int i = 0; i < weekdays.length; i++) {
			JLabel label = new JLabel(weekdays[i]);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("Impact", Font.PLAIN, 15));
			p2.add(label);
		}
		Date date = new Date("01-" + inputMonth + "-" + inputYear);
		int noOfDaysInMonth = days[date.getMonth()];
		if (date.getYear() % 4 == 0 && date.getMonth() == 1) {
			noOfDaysInMonth = 29;
		}

		for (int i = 1, day = 1; day <= noOfDaysInMonth; i++) {
			for (int j = 0; j < 7; j++) {
				if (day == 1) {
					if (j == date.getDay()) {
						JLabel label = new JLabel(String.valueOf(day));
						label.setHorizontalAlignment(SwingConstants.CENTER);
						label.setFont(new Font("Impact", Font.PLAIN, 15));
						p2.add(label);
						day++;
					} else {
						JLabel label = new JLabel("");
						p2.add(label);
					}
				} else {
					JLabel label = new JLabel(String.valueOf(day));
					label.setHorizontalAlignment(SwingConstants.CENTER);
					label.setFont(new Font("Impact", Font.PLAIN, 15));
					p2.add(label);
					day++;
				}
				if (day > noOfDaysInMonth) {
					break;
				}
			}
		}
		p2.validate();

		// setTitle(inputMonth + ", " + inputYear);
	}
}
