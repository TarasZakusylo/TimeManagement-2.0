package Zakk.TimeManagement2;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class NovaPodia extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel l_fon;
	private JLabel l_kartunka;
	private JLabel l_Hapka;
	private JButton b_Menu;
	private JTextField t_NazvaPod;

	private JDatePickerImpl datePicker;

	Date date = new Date();

	private JTextField t_God;
	private JTextField t_Hv;
	private JButton b_Ochustutu;
	private JLabel l_ChasPod;
	private JLabel l_Pruoritet;
	private JLabel l_TruvalistPod;
	private JLabel l_God;
	private JLabel l_Hv;
	private JLabel l_NazvaPod;
	private JButton b_Gotovo;
	private Choice ch_TruvalistPod;
	private JSlider slider_Pruoritet;

	private int i_slider_Pruoritet;

	SliderListener slider = new SliderListener();

	@SuppressWarnings("deprecation")

	public NovaPodia(String s) {
		super(s);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		l_kartunka = new JLabel("");
		l_kartunka.setBounds(488, 115, 481, 419);
		l_kartunka.setHorizontalAlignment(SwingConstants.CENTER);
		l_kartunka.setIcon(new ImageIcon("C:\\Users\\ZakkZakk\\Desktop\\graphs_46879.png"));
		getContentPane().add(l_kartunka);

		l_Hapka = new JLabel("Вкажіть, буль ласка, параметри події :");
		l_Hapka.setForeground(new Color(165, 42, 42));
		l_Hapka.setHorizontalAlignment(SwingConstants.CENTER);
		l_Hapka.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		l_Hapka.setBounds(0, 36, 552, 42);
		getContentPane().add(l_Hapka);

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

		t_NazvaPod = new JTextField();
		t_NazvaPod.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		t_NazvaPod.setBounds(214, 106, 207, 27);
		getContentPane().add(t_NazvaPod);
		t_NazvaPod.setColumns(10);

		l_NazvaPod = new JLabel("Назва події :");
		l_NazvaPod.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_NazvaPod.setBounds(74, 105, 127, 30);
		getContentPane().add(l_NazvaPod);

		UtilDateModel model = new UtilDateModel();
		model.setDate(1900 + date.getYear(), date.getMonth(), 3 + date.getDay());
		model.setSelected(true);

		// System.out.println(date.getDay());

		JDatePanelImpl datePanel = new JDatePanelImpl(model);

		// datePicker = new JDatePickerImpl(datePanel);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJFormattedTextField().setFont(new Font("Times New Roman", Font.ITALIC, 20));
		datePicker.getJFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);

		datePicker.setSize(230, 27);
		datePicker.setLocation(135, 185);

		getContentPane().add(datePicker);

		b_Gotovo = new JButton("Готово");
		b_Gotovo.setFont(new Font("Impact", Font.PLAIN, 25));
		b_Gotovo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// for UtilDateModel, the value returned is of type java.util.Date
				Date selectedDate = (Date) datePicker.getModel().getValue();

				//////
				// for UtilCalendarModel, the value returned is of type java.util.Calendar
				// Calendar selectedValue = (Calendar) datePicker.getModel().getValue();
				// Date selectedDate = selectedValue.getTime();

				// for SqlDateModel, the value returned is of type java.sql.Date
				// java.sql.Date selectedDate = (java.sql.Date)
				// datePicker.getModel().getValue();
				//////

				// JOptionPane.showMessageDialog(null, selectedDate);

				String line = "" + selectedDate;
				String[] words = line.split(" ");

				try {

					for (int i = 0; i < line.length(); i++) {
						if (i == 1) {
							System.out.println(words[i]);
						}
						if (i == 2) {
							System.out.println(words[i]);
						}
						if (i == 5) {
							System.out.println(words[i]);
						}
					}

					String s_NazvaPod = t_NazvaPod.getText();
					String s_God = t_God.getText();
					String s_Hv = t_Hv.getText();
					String s_TruvalistPod = ch_TruvalistPod.getSelectedItem();

					// i_slider_Pruoritet

					int i_God = Integer.parseInt(s_God);
					int i_Hv = Integer.parseInt(s_Hv);

					if (s_NazvaPod.equals("") || s_NazvaPod.equals(" ") || s_NazvaPod.equals("  ")
							|| s_NazvaPod.equals("   ")) {
						JOptionPane.showMessageDialog(null, "Помилка назви");
					} else {
						String line_s_NazvaPod = "" + s_NazvaPod;
						String[] words_line_s_NazvaPod = line_s_NazvaPod.split(" ");
						if (words_line_s_NazvaPod.length >= 2) {
							JOptionPane.showMessageDialog(null, "Назва повинна складатись з одного слова.");
						} else {
							if (i_God < 0 || i_God >= 24 || i_Hv < 0 || i_Hv >= 60) {
								JOptionPane.showMessageDialog(null, "Помилка часу події");
							} else {
								
								System.out.println("Захист пройдено");
								
								/*
								 * 
								 * 
								 */
								
							}
						}

					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Помилка");
				}

			}
		});
		b_Gotovo.setBounds(247, 510, 174, 42);
		getContentPane().add(b_Gotovo);

		t_God = new JTextField();
		t_God.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		t_God.setColumns(10);
		t_God.setBounds(225, 263, 28, 27);
		getContentPane().add(t_God);

		t_Hv = new JTextField();
		t_Hv.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		t_Hv.setColumns(10);
		t_Hv.setBounds(338, 263, 28, 27);
		getContentPane().add(t_Hv);

		ch_TruvalistPod = new Choice();
		ch_TruvalistPod.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		ch_TruvalistPod.setBounds(235, 340, 207, 30);
		getContentPane().add(ch_TruvalistPod);
		ChoiceAdd();

		b_Ochustutu = new JButton("Очистити");
		b_Ochustutu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				t_NazvaPod.setText(null);
				t_God.setText(null);
				t_Hv.setText(null);
				ch_TruvalistPod.removeAll();
				slider_Pruoritet.setValue(1);
				ChoiceAdd();
			}
		});
		b_Ochustutu.setFont(new Font("Impact", Font.PLAIN, 25));
		b_Ochustutu.setBounds(74, 510, 174, 42);
		getContentPane().add(b_Ochustutu);

		l_ChasPod = new JLabel("Час події :");
		l_ChasPod.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_ChasPod.setBounds(89, 260, 118, 30);
		getContentPane().add(l_ChasPod);

		l_Pruoritet = new JLabel("Пріоритет :");
		l_Pruoritet.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_Pruoritet.setBounds(194, 402, 118, 30);
		getContentPane().add(l_Pruoritet);

		l_TruvalistPod = new JLabel("Тривалість події :");
		l_TruvalistPod.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_TruvalistPod.setBounds(59, 340, 181, 30);
		getContentPane().add(l_TruvalistPod);

		l_God = new JLabel("год.");
		l_God.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_God.setBounds(265, 262, 43, 30);
		getContentPane().add(l_God);

		l_Hv = new JLabel("хв.");
		l_Hv.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_Hv.setBounds(378, 262, 43, 30);
		getContentPane().add(l_Hv);

		slider_Pruoritet = new JSlider(SwingConstants.HORIZONTAL, 1, 5, 1);
		slider_Pruoritet.setForeground(Color.BLACK);
		slider_Pruoritet.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		slider_Pruoritet.setPaintTicks(true);
		slider_Pruoritet.setOpaque(false);
		slider_Pruoritet.setPaintLabels(true);
		slider_Pruoritet.setMinorTickSpacing(1);
		slider_Pruoritet.setMajorTickSpacing(1);
		slider_Pruoritet.setBounds(74, 445, 349, 51);
		getContentPane().add(slider_Pruoritet);

		slider_Pruoritet.addChangeListener(slider);

		l_fon = new JLabel("");
		l_fon.setIcon(new ImageIcon("C:\\Users\\ZakkZakk\\Desktop\\fon_Avtoruzacia.jpg"));
		l_fon.setBounds(0, 0, 994, 566);
		l_fon.setForeground(Color.WHITE);
		// l_fon.setIcon(new ImageIcon("res/fon/fon_Avtoruzacia.jpg"));
		getContentPane().add(l_fon);

		setVisible(true);
	}

	class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			slider_Pruoritet = (JSlider) e.getSource();
			i_slider_Pruoritet = (int) slider_Pruoritet.getValue();
		}
	}

	void ChoiceAdd() {
		ch_TruvalistPod.add("5 хвилин");
		ch_TruvalistPod.add("15 хвилин");
		ch_TruvalistPod.add("30 хвилин");
		ch_TruvalistPod.add("45 хвилин");
		ch_TruvalistPod.add("1 годину");
		ch_TruvalistPod.add("1 год. 30 хв.");
		ch_TruvalistPod.add("2 години");
		ch_TruvalistPod.add("2 год. 30 хв.");
		ch_TruvalistPod.add("3 години");
		ch_TruvalistPod.add("4 години");
		ch_TruvalistPod.add("5 години");
		ch_TruvalistPod.add("6 години");
		ch_TruvalistPod.add("8 години");
		ch_TruvalistPod.add("9 години");
		ch_TruvalistPod.add("12 години");
		ch_TruvalistPod.add("24 години (доба)");
		ch_TruvalistPod.add("36 години");
		ch_TruvalistPod.add("48 години (2 доби)");
		ch_TruvalistPod.add("72 години (3 доби)");
		ch_TruvalistPod.add("тиждень");
		ch_TruvalistPod.add("1,5 тижні");
		ch_TruvalistPod.add("2 тижні");
		ch_TruvalistPod.add("місяць");
		ch_TruvalistPod.add("2 місяці");
		ch_TruvalistPod.add("3 місяці");
		ch_TruvalistPod.add("6 місяців");
		ch_TruvalistPod.add("9 місяців");
		ch_TruvalistPod.add("рік");
		ch_TruvalistPod.add("2 роки");
		ch_TruvalistPod.add("3 роки");
		ch_TruvalistPod.add("5 років");
	}
}
