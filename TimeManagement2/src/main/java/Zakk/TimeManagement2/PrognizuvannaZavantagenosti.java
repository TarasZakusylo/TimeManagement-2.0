package Zakk.TimeManagement2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.xeiam.xchart.BitmapEncoder;
import com.xeiam.xchart.Chart;
import com.xeiam.xchart.ChartBuilder;
import com.xeiam.xchart.StyleManager.ChartType;
import com.xeiam.xchart.StyleManager.LegendPosition;
import com.xeiam.xchart.SwingWrapper;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class PrognizuvannaZavantagenosti extends JFrame {

	private static final long serialVersionUID = 1L;

	JButton b_Prognoz1, b_Prognoz2;
	JLabel Vkazivka, Vkazivka1, Vkazivka2;

	String s_day1 = "";
	String s_day2 = "";
	String s_day3 = "";
	String s_day4 = "";
	String s_day5 = "";
	String s_day6 = "";
	String s_day7 = "";
	String s_day8 = "";

	int type1 = JOptionPane.ERROR_MESSAGE; // Р•РњР‘Р›Р•РњРђ Р’Р†РљРќРђ

	Calendar calendar = Calendar.getInstance();
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH) + 1;
	int day = calendar.get(Calendar.DAY_OF_MONTH);

	Scanner scn;
	String[][] Reading = new String[1][4];
	String[][] Reading0 = new String[1][4];
	int[] Reading_P = new int[9];
	int[] Reading_P1 = new int[9];

	static double[] Result = new double[9];
	static double[] Result1 = new double[9];
	double[] grafic = new double[9];

	Formatter x;

	eHandler handler = new eHandler();

	private JButton b_Menu;
	private JLabel l_fon;
	private JButton b_Nazad;
	private JLabel l_Cogodni;

	private JDatePickerImpl datePicker;
	Date date = new Date();

	private String rik = null;
	private String misac = null;
	private String den = null;

	int i_misac = 0;

	@SuppressWarnings("deprecation")

	public PrognizuvannaZavantagenosti(String s) {
		super(s);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		b_Prognoz1 = new JButton("Прогноз усередненим методом");
		b_Prognoz1.setBounds(366, 425, 260, 30);
		b_Prognoz2 = new JButton("Прогноз на основі нейронної мережі");
		b_Prognoz2.setBounds(366, 455, 260, 30);

		Vkazivka = new JLabel("Вкажіть день для якого буде розраховано завантаженість:");
		Vkazivka.setBounds(44, 169, 450, 20);
		Vkazivka1 = new JLabel("(для забезпечення почності вказувати");
		Vkazivka1.setBounds(72, 350, 450, 20);
		Vkazivka2 = new JLabel("можна лише на найближчий тиждень)");
		Vkazivka2.setBounds(83, 367, 450, 20);

		getContentPane().add(b_Prognoz1);
		getContentPane().add(b_Prognoz2);
		getContentPane().add(Vkazivka);
		getContentPane().add(Vkazivka1);
		getContentPane().add(Vkazivka2);

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

		b_Nazad = new JButton("Назад");
		b_Nazad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PrognozyvannaMenu("Times");
				setVisible(false);
			}
		});
		b_Nazad.setForeground(Color.BLACK);
		b_Nazad.setFont(new Font("Impact", Font.PLAIN, 25));
		b_Nazad.setBounds(712, 42, 282, 42);
		getContentPane().add(b_Nazad);

		UtilDateModel model = new UtilDateModel();
//		model.setDate(1900 + date.getYear(), date.getMonth(), 10 + date.getDay());
		model.setDate(year, month-1, day);
		model.setSelected(true);
		
		// System.out.println(date.getDay());

		JDatePanelImpl datePanel = new JDatePanelImpl(model);

		// datePicker = new JDatePickerImpl(datePanel);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJFormattedTextField().setFont(new Font("Times New Roman", Font.ITALIC, 20));
		datePicker.getJFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);

		datePicker.setSize(230, 27);
		datePicker.setLocation(367, 238);
		getContentPane().add(datePicker);

		l_fon = new JLabel("");
		l_fon.setBounds(0, 0, 994, 566);
		l_fon.setForeground(Color.WHITE);
		l_fon.setIcon(new ImageIcon("res/fon/fon_Avtoruzacia.jpg"));
		getContentPane().add(l_fon);

		l_Cogodni = new JLabel("Сьогоднішнє завантаження");
		l_Cogodni.setBounds(726, 258, 211, 42);
		getContentPane().add(l_Cogodni);

		b_Prognoz1.addActionListener(handler);
		b_Prognoz2.addActionListener(handler);

		setVisible(true);
	}

	public class eHandler implements ActionListener {

		private String PIP;

		public void actionPerformed(ActionEvent e) {

			// for UtilDateModel, the value returned is of type java.util.Date
			Date selectedDate = (Date) datePicker.getModel().getValue();

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

			for (int i = 0; i < line.length(); i++) {
				if (i == 1) {
					misac = words[i];
				}
				if (i == 2) {
					den = words[i];
				}
				if (i == 5) {
					rik = words[i];
				}
			}

			int i_rik = Integer.parseInt(rik);
			int i_den = Integer.parseInt(den);

			switch (misac) {
			case "Jan":
				i_misac = 1;
				break;
			case "Feb":
				i_misac = 2;
				break;
			case "Mar":
				i_misac = 3;
				break;
			case "Apr":
				i_misac = 4;
				break;
			case "May":
				i_misac = 5;
				break;
			case "Jun":
				i_misac = 6;
				break;
			case "Jul":
				i_misac = 7;
				break;
			case "Aug":
				i_misac = 8;
				break;
			case "Sep":
				i_misac = 9;
				break;
			case "Oct":
				i_misac = 10;
				break;
			case "Nov":
				i_misac = 11;
				break;
			case "Dec":
				i_misac = 12;
				break;
			}

			if (i_rik < year || i_rik == year && i_misac < month || i_rik == year && i_misac == month && i_den < day) {
				JOptionPane.showMessageDialog(null, "Не можна вказувати минулі дати !", null, type1);
			} else {

				try {
					if (e.getSource() == b_Prognoz2) {

						int day_max1 = 0;
						int day_max2 = 0;
						int day_max3 = 0;
						int day_max4 = 0;
						int day_max5 = 0;
						int day_max6 = 0;
						int day_max7 = day;
						int misac_max = month;
						int rik_max = year;

						s_day1 = "";
						s_day2 = "";
						s_day3 = "";
						s_day4 = "";
						s_day5 = "";
						s_day6 = "";
						s_day7 = "";
						s_day8 = "";

						if (month == 2) {
							switch (day) {
							case 22: {
								day_max1 = 22;
								day_max2 = 23;
								day_max3 = 24;
								day_max4 = 25;
								day_max5 = 26;
								day_max6 = 27;
								day_max7 = 28;

								misac_max++;
							}
								break;
							case 23: {
								day_max1 = 1;
								day_max2 = 23;
								day_max3 = 24;
								day_max4 = 25;
								day_max5 = 26;
								day_max6 = 27;
								day_max7 = 28;

								misac_max++;
							}
								break;
							case 24: {
								day_max1 = 1;
								day_max2 = 2;
								day_max3 = 24;
								day_max4 = 25;
								day_max5 = 26;
								day_max6 = 27;
								day_max7 = 28;

								misac_max++;
							}
								break;
							case 25: {
								day_max1 = 1;
								day_max2 = 2;
								day_max3 = 3;
								day_max4 = 25;
								day_max5 = 26;
								day_max6 = 27;
								day_max7 = 28;

								misac_max++;
							}
								break;
							case 26: {
								day_max1 = 1;
								day_max2 = 2;
								day_max3 = 3;
								day_max4 = 4;
								day_max5 = 26;
								day_max6 = 27;
								day_max7 = 28;

								misac_max++;
							}
								break;
							case 27: {
								day_max1 = 1;
								day_max2 = 2;
								day_max3 = 3;
								day_max4 = 4;
								day_max5 = 5;
								day_max6 = 27;
								day_max7 = 28;

								misac_max++;
							}
								break;
							case 28: {
								day_max1 = 1;
								day_max2 = 2;
								day_max3 = 3;
								day_max4 = 4;
								day_max5 = 5;
								day_max6 = 6;
								day_max7 = 28;

								misac_max++;
							}
								break;
							default: {
								day_max1 = day + 1;
								day_max2 = day + 2;
								day_max3 = day + 3;
								day_max4 = day + 4;
								day_max5 = day + 5;
								day_max6 = day + 6;
							}
							}
						} else {
							if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
									|| month == 12) {

								switch (day) {
								case 25: {
									day_max1 = 25;
									day_max2 = 26;
									day_max3 = 27;
									day_max4 = 28;
									day_max5 = 29;
									day_max6 = 30;
									day_max7 = 31;

									misac_max++;

									if (month == 12) {
										rik_max++;
									}
								}
									break;
								case 26: {
									day_max1 = 1;
									day_max2 = 26;
									day_max3 = 27;
									day_max4 = 28;
									day_max5 = 29;
									day_max6 = 30;
									day_max7 = 31;

									misac_max++;

									if (month == 12) {
										rik_max++;
									}
								}
									break;
								case 27: {
									day_max1 = 1;
									day_max2 = 2;
									day_max3 = 27;
									day_max4 = 28;
									day_max5 = 29;
									day_max6 = 30;
									day_max7 = 31;

									misac_max++;

									if (month == 12) {
										rik_max++;
									}
								}
									break;
								case 28: {
									day_max1 = 1;
									day_max2 = 2;
									day_max3 = 3;
									day_max4 = 28;
									day_max5 = 29;
									day_max6 = 30;
									day_max7 = 31;

									misac_max++;

									if (month == 12) {
										rik_max++;
									}
								}
									break;
								case 29: {
									day_max1 = 1;
									day_max2 = 2;
									day_max3 = 3;
									day_max4 = 4;
									day_max5 = 29;
									day_max6 = 30;
									day_max7 = 31;

									misac_max++;

									if (month == 12) {
										rik_max++;
									}
								}
									break;
								case 30: {
									day_max1 = 1;
									day_max2 = 2;
									day_max3 = 3;
									day_max4 = 4;
									day_max5 = 5;
									day_max6 = 30;
									day_max7 = 31;

									misac_max++;

									if (month == 12) {
										rik_max++;
									}
								}
									break;
								case 31: {
									day_max1 = 1;
									day_max2 = 2;
									day_max3 = 3;
									day_max4 = 4;
									day_max5 = 5;
									day_max6 = 6;
									day_max7 = 31;

									misac_max++;

									if (month == 12) {
										rik_max++;
									}

								}
									break;
								default: {
									day_max1 = day + 1;
									day_max2 = day + 2;
									day_max3 = day + 3;
									day_max4 = day + 4;
									day_max5 = day + 5;
									day_max6 = day + 6;
								}
								}
							} else {

								switch (day) {
								case 24: {
									day_max1 = 24;
									day_max2 = 25;
									day_max3 = 26;
									day_max4 = 27;
									day_max5 = 28;
									day_max6 = 29;
									day_max7 = 30;

									misac_max++;

									if (month == 12) {
										rik_max++;
									}
								}
									break;
								case 25: {
									day_max1 = 1;
									day_max2 = 25;
									day_max3 = 26;
									day_max4 = 27;
									day_max5 = 28;
									day_max6 = 29;
									day_max7 = 30;

									misac_max++;

									if (month == 12) {
										rik_max++;
									}
								}
									break;
								case 26: {
									day_max1 = 1;
									day_max2 = 2;
									day_max3 = 26;
									day_max4 = 27;
									day_max5 = 28;
									day_max6 = 29;
									day_max7 = 30;

									misac_max++;

									if (month == 12) {
										rik_max++;
									}
								}
									break;
								case 27: {
									day_max1 = 1;
									day_max2 = 2;
									day_max3 = 3;
									day_max4 = 27;
									day_max5 = 28;
									day_max6 = 29;
									day_max7 = 30;

									misac_max++;

									if (month == 12) {
										rik_max++;
									}
								}
									break;
								case 28: {
									day_max1 = 1;
									day_max2 = 2;
									day_max3 = 3;
									day_max4 = 4;
									day_max5 = 28;
									day_max6 = 29;
									day_max7 = 30;

									misac_max++;

									if (month == 12) {
										rik_max++;
									}
								}
									break;
								case 29: {
									day_max1 = 1;
									day_max2 = 2;
									day_max3 = 3;
									day_max4 = 4;
									day_max5 = 5;
									day_max6 = 29;
									day_max7 = 30;

									misac_max++;

									if (month == 12) {
										rik_max++;
									}
								}
									break;
								case 30: {
									day_max1 = 1;
									day_max2 = 2;
									day_max3 = 3;
									day_max4 = 4;
									day_max5 = 5;
									day_max6 = 6;
									day_max7 = 30;

									misac_max++;

									if (month == 12) {
										rik_max++;
									}
								}
									break;
								default: {
									day_max1 = day + 1;
									day_max2 = day + 2;
									day_max3 = day + 3;
									day_max4 = day + 4;
									day_max5 = day + 5;
									day_max6 = day + 6;
								}
								}
							}
						}

						if (((i_den == day_max1 || i_den == day_max2 || i_den == day_max3 || i_den == day_max4
								|| i_den == day_max5 || i_den == day_max6 || i_den == day_max7) && i_rik == rik_max)
								&& i_misac == misac_max) {

							// String s_day = "" + (day - 1);
							// String s_month = "" + i_misac;
							// zavantajenist_dna1(s_month, s_day);

							String s_rik = "" + i_rik;

							if (month == 2) {

								// System.out.println(28);

								switch (i_den) {
								case 1: {

									s_day1 = s_day1 + 28;
									s_day2 = s_day2 + 27;
									s_day3 = s_day3 + 26;
									s_day4 = s_day4 + 25;
									s_day5 = s_day5 + 22;
									s_day6 = s_day6 + 21;
									s_day7 = s_day7 + 14;
									s_day8 = s_day8 + 13;

									--i_misac;

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac, s_day2);
									zavantajenist_dna(s_rik, i_misac, s_day3);
									zavantajenist_dna(s_rik, i_misac, s_day4);
									zavantajenist_dna(s_rik, i_misac, s_day5);
									zavantajenist_dna(s_rik, i_misac, s_day6);
									zavantajenist_dna(s_rik, i_misac, s_day7);
									zavantajenist_dna(s_rik, i_misac, s_day8);

								}
									break;
								case 2: {
									s_day1 = s_day1 + 1;
									s_day2 = s_day2 + 28;
									s_day3 = s_day3 + 27;
									s_day4 = s_day4 + 26;
									s_day5 = s_day5 + 23;
									s_day6 = s_day6 + 22;
									s_day7 = s_day7 + 16;
									s_day8 = s_day8 + 15;

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac - 1, s_day2);
									zavantajenist_dna(s_rik, i_misac - 1, s_day3);
									zavantajenist_dna(s_rik, i_misac - 1, s_day4);
									zavantajenist_dna(s_rik, i_misac - 1, s_day5);
									zavantajenist_dna(s_rik, i_misac - 1, s_day6);
									zavantajenist_dna(s_rik, i_misac - 1, s_day7);
									zavantajenist_dna(s_rik, i_misac - 1, s_day8);

								}
									break;
								case 3: {
									s_day1 = s_day1 + 2;
									s_day2 = s_day2 + 1;
									s_day3 = s_day3 + (31 - 3);
									s_day4 = s_day4 + (30 - 3);
									s_day5 = s_day5 + (27 - 3);
									s_day6 = s_day6 + (26 - 3);
									s_day7 = s_day7 + (20 - 3);
									s_day8 = s_day8 + (19 - 3);

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac, s_day2);
									zavantajenist_dna(s_rik, i_misac - 1, s_day3);
									zavantajenist_dna(s_rik, i_misac - 1, s_day4);
									zavantajenist_dna(s_rik, i_misac - 1, s_day5);
									zavantajenist_dna(s_rik, i_misac - 1, s_day6);
									zavantajenist_dna(s_rik, i_misac - 1, s_day7);
									zavantajenist_dna(s_rik, i_misac - 1, s_day8);
								}
									break;
								case 4: {
									s_day1 = s_day1 + 3;
									s_day2 = s_day2 + 2;
									s_day3 = s_day3 + 1;
									s_day4 = s_day4 + (31 - 3);
									s_day5 = s_day5 + (28 - 3);
									s_day6 = s_day6 + (27 - 3);
									s_day7 = s_day7 + (21 - 3);
									s_day8 = s_day8 + (20 - 3);

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac, s_day2);
									zavantajenist_dna(s_rik, i_misac, s_day3);
									zavantajenist_dna(s_rik, i_misac - 1, s_day4);
									zavantajenist_dna(s_rik, i_misac - 1, s_day5);
									zavantajenist_dna(s_rik, i_misac - 1, s_day6);
									zavantajenist_dna(s_rik, i_misac - 1, s_day7);
									zavantajenist_dna(s_rik, i_misac - 1, s_day8);
								}
									break;
								case 5: {
									s_day1 = s_day1 + 4;
									s_day2 = s_day2 + 3;
									s_day3 = s_day3 + 2;
									s_day4 = s_day4 + 1;
									s_day5 = s_day5 + (29 - 3);
									s_day6 = s_day6 + (28 - 3);
									s_day7 = s_day7 + (22 - 3);
									s_day8 = s_day8 + (21 - 3);

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac, s_day2);
									zavantajenist_dna(s_rik, i_misac, s_day3);
									zavantajenist_dna(s_rik, i_misac, s_day4);
									zavantajenist_dna(s_rik, i_misac - 1, s_day5);
									zavantajenist_dna(s_rik, i_misac - 1, s_day6);
									zavantajenist_dna(s_rik, i_misac - 1, s_day7);
									zavantajenist_dna(s_rik, i_misac - 1, s_day8);
								}
									break;
								case 6: {
									s_day1 = s_day1 + 5;
									s_day2 = s_day2 + 4;
									s_day3 = s_day3 + 3;
									s_day4 = s_day4 + 2;
									s_day5 = s_day5 + (30 - 3);
									s_day6 = s_day6 + (29 - 3);
									s_day7 = s_day7 + (23 - 3);
									s_day8 = s_day8 + (22 - 3);

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac, s_day2);
									zavantajenist_dna(s_rik, i_misac, s_day3);
									zavantajenist_dna(s_rik, i_misac, s_day4);
									zavantajenist_dna(s_rik, i_misac - 1, s_day5);
									zavantajenist_dna(s_rik, i_misac - 1, s_day6);
									zavantajenist_dna(s_rik, i_misac - 1, s_day7);
									zavantajenist_dna(s_rik, i_misac - 1, s_day8);
								}
									break;
								case 7: {
									s_day1 = s_day1 + 6;
									s_day2 = s_day2 + 5;
									s_day3 = s_day3 + 4;
									s_day4 = s_day4 + 3;
									s_day5 = s_day5 + (31 - 3);
									s_day6 = s_day6 + (30 - 3);
									s_day7 = s_day7 + (24 - 3);
									s_day8 = s_day8 + (23 - 3);

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac, s_day2);
									zavantajenist_dna(s_rik, i_misac, s_day3);
									zavantajenist_dna(s_rik, i_misac, s_day4);
									zavantajenist_dna(s_rik, i_misac - 1, s_day5);
									zavantajenist_dna(s_rik, i_misac - 1, s_day6);
									zavantajenist_dna(s_rik, i_misac - 1, s_day7);
									zavantajenist_dna(s_rik, i_misac - 1, s_day8);
								}
									break;
								case 8: {
									s_day1 = s_day1 + 7;
									s_day2 = s_day2 + 6;
									s_day3 = s_day3 + 5;
									s_day4 = s_day4 + 4;
									s_day5 = s_day5 + 1;
									s_day6 = s_day6 + (31 - 3);
									s_day7 = s_day7 + (25 - 3);
									s_day8 = s_day8 + (24 - 3);

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac, s_day2);
									zavantajenist_dna(s_rik, i_misac, s_day3);
									zavantajenist_dna(s_rik, i_misac, s_day4);
									zavantajenist_dna(s_rik, i_misac, s_day5);
									zavantajenist_dna(s_rik, i_misac - 1, s_day6);
									zavantajenist_dna(s_rik, i_misac - 1, s_day7);
									zavantajenist_dna(s_rik, i_misac - 1, s_day8);
								}
									break;
								case 9: {
									s_day1 = s_day1 + 8;
									s_day2 = s_day2 + 7;
									s_day3 = s_day3 + 6;
									s_day4 = s_day4 + 5;
									s_day5 = s_day5 + 2;
									s_day6 = s_day6 + 1;
									s_day7 = s_day7 + (26 - 3);
									s_day8 = s_day8 + (25 - 3);

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac, s_day2);
									zavantajenist_dna(s_rik, i_misac, s_day3);
									zavantajenist_dna(s_rik, i_misac, s_day4);
									zavantajenist_dna(s_rik, i_misac, s_day5);
									zavantajenist_dna(s_rik, i_misac, s_day6);
									zavantajenist_dna(s_rik, i_misac - 1, s_day7);
									zavantajenist_dna(s_rik, i_misac - 1, s_day8);
								}
									break;
								case 10: {
									s_day1 = s_day1 + 9;
									s_day2 = s_day2 + 8;
									s_day3 = s_day3 + 7;
									s_day4 = s_day4 + 6;
									s_day5 = s_day5 + 3;
									s_day6 = s_day6 + 2;
									s_day7 = s_day7 + (27 - 3);
									s_day8 = s_day8 + (26 - 3);

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac, s_day2);
									zavantajenist_dna(s_rik, i_misac, s_day3);
									zavantajenist_dna(s_rik, i_misac, s_day4);
									zavantajenist_dna(s_rik, i_misac, s_day5);
									zavantajenist_dna(s_rik, i_misac, s_day6);
									zavantajenist_dna(s_rik, i_misac - 1, s_day7);
									zavantajenist_dna(s_rik, i_misac - 1, s_day8);
								}
									break;
								case 11: {
									s_day1 = s_day1 + 10;
									s_day2 = s_day2 + 9;
									s_day3 = s_day3 + 8;
									s_day4 = s_day4 + 7;
									s_day5 = s_day5 + 4;
									s_day6 = s_day6 + 3;
									s_day7 = s_day7 + (28 - 3);
									s_day8 = s_day8 + (27 - 3);

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac, s_day2);
									zavantajenist_dna(s_rik, i_misac, s_day3);
									zavantajenist_dna(s_rik, i_misac, s_day4);
									zavantajenist_dna(s_rik, i_misac, s_day5);
									zavantajenist_dna(s_rik, i_misac, s_day6);
									zavantajenist_dna(s_rik, i_misac - 1, s_day7);
									zavantajenist_dna(s_rik, i_misac - 1, s_day8);
								}
									break;
								case 12: {
									s_day1 = s_day1 + 11;
									s_day2 = s_day2 + 10;
									s_day3 = s_day3 + 9;
									s_day4 = s_day4 + 8;
									s_day5 = s_day5 + 5;
									s_day6 = s_day6 + 4;
									s_day7 = s_day7 + (29 - 3);
									s_day8 = s_day8 + (28 - 3);

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac, s_day2);
									zavantajenist_dna(s_rik, i_misac, s_day3);
									zavantajenist_dna(s_rik, i_misac, s_day4);
									zavantajenist_dna(s_rik, i_misac, s_day5);
									zavantajenist_dna(s_rik, i_misac, s_day6);
									zavantajenist_dna(s_rik, i_misac - 1, s_day7);
									zavantajenist_dna(s_rik, i_misac - 1, s_day8);
								}
									break;
								case 13: {
									s_day1 = s_day1 + 12;
									s_day2 = s_day2 + 11;
									s_day3 = s_day3 + 10;
									s_day4 = s_day4 + 9;
									s_day5 = s_day5 + 6;
									s_day6 = s_day6 + 5;
									s_day7 = s_day7 + (30 - 3);
									s_day8 = s_day8 + (29 - 3);

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac, s_day2);
									zavantajenist_dna(s_rik, i_misac, s_day3);
									zavantajenist_dna(s_rik, i_misac, s_day4);
									zavantajenist_dna(s_rik, i_misac, s_day5);
									zavantajenist_dna(s_rik, i_misac, s_day6);
									zavantajenist_dna(s_rik, i_misac - 1, s_day7);
									zavantajenist_dna(s_rik, i_misac - 1, s_day8);
								}
									break;
								case 14: {
									s_day1 = s_day1 + 13;
									s_day2 = s_day2 + 12;
									s_day3 = s_day3 + 11;
									s_day4 = s_day4 + 10;
									s_day5 = s_day5 + 7;
									s_day6 = s_day6 + 6;
									s_day7 = s_day7 + (31 - 3);
									s_day8 = s_day8 + (30 - 3);

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac, s_day2);
									zavantajenist_dna(s_rik, i_misac, s_day3);
									zavantajenist_dna(s_rik, i_misac, s_day4);
									zavantajenist_dna(s_rik, i_misac, s_day5);
									zavantajenist_dna(s_rik, i_misac, s_day6);
									zavantajenist_dna(s_rik, i_misac - 1, s_day7);
									zavantajenist_dna(s_rik, i_misac - 1, s_day8);
								}
									break;
								case 15: {
									s_day1 = s_day1 + 14;
									s_day2 = s_day2 + 13;
									s_day3 = s_day3 + 12;
									s_day4 = s_day4 + 11;
									s_day5 = s_day5 + 8;
									s_day6 = s_day6 + 7;
									s_day7 = s_day7 + 1;
									s_day8 = s_day8 + (31 - 3);

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac, s_day2);
									zavantajenist_dna(s_rik, i_misac, s_day3);
									zavantajenist_dna(s_rik, i_misac, s_day4);
									zavantajenist_dna(s_rik, i_misac, s_day5);
									zavantajenist_dna(s_rik, i_misac, s_day6);
									zavantajenist_dna(s_rik, i_misac, s_day7);
									zavantajenist_dna(s_rik, i_misac - 1, s_day8);
								}
									break;
								default: {
									s_day1 = s_day1 + (day - 1);
									s_day2 = s_day2 + (day - 2);
									s_day3 = s_day3 + (day - 3);
									s_day4 = s_day4 + (day - 4);
									s_day5 = s_day5 + (day - 7);
									s_day6 = s_day6 + (day - 8);
									s_day7 = s_day7 + (day - 14);
									s_day8 = s_day8 + (day - 15);

									zavantajenist_dna(s_rik, i_misac, s_day1);
									zavantajenist_dna(s_rik, i_misac, s_day2);
									zavantajenist_dna(s_rik, i_misac, s_day3);
									zavantajenist_dna(s_rik, i_misac, s_day4);
									zavantajenist_dna(s_rik, i_misac, s_day5);
									zavantajenist_dna(s_rik, i_misac, s_day6);
									zavantajenist_dna(s_rik, i_misac, s_day7);
									zavantajenist_dna(s_rik, i_misac, s_day8);
								}
								}

							} else {
								if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
										|| month == 12) {

									// System.out.println(31);

									switch (i_den) {
									case 1: {

										s_day1 = s_day1 + 31;
										s_day2 = s_day2 + 30;
										s_day3 = s_day3 + 29;
										s_day4 = s_day4 + 28;
										s_day5 = s_day5 + 25;
										s_day6 = s_day6 + 24;
										s_day7 = s_day7 + 18;
										s_day8 = s_day8 + 17;

										--i_misac;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac, s_day7);
										zavantajenist_dna(s_rik, i_misac, s_day8);

									}
										break;
									case 2: {
										s_day1 = s_day1 + 1;
										s_day2 = s_day2 + 31;
										s_day3 = s_day3 + 30;
										s_day4 = s_day4 + 29;
										s_day5 = s_day5 + 26;
										s_day6 = s_day6 + 25;
										s_day7 = s_day7 + 19;
										s_day8 = s_day8 + 18;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac - 1, s_day2);
										zavantajenist_dna(s_rik, i_misac - 1, s_day3);
										zavantajenist_dna(s_rik, i_misac - 1, s_day4);
										zavantajenist_dna(s_rik, i_misac - 1, s_day5);
										zavantajenist_dna(s_rik, i_misac - 1, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);

									}
										break;
									case 3: {
										s_day1 = s_day1 + 2;
										s_day2 = s_day2 + 1;
										s_day3 = s_day3 + 31;
										s_day4 = s_day4 + 30;
										s_day5 = s_day5 + 27;
										s_day6 = s_day6 + 26;
										s_day7 = s_day7 + 20;
										s_day8 = s_day8 + 19;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac - 1, s_day3);
										zavantajenist_dna(s_rik, i_misac - 1, s_day4);
										zavantajenist_dna(s_rik, i_misac - 1, s_day5);
										zavantajenist_dna(s_rik, i_misac - 1, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 4: {
										s_day1 = s_day1 + 3;
										s_day2 = s_day2 + 2;
										s_day3 = s_day3 + 1;
										s_day4 = s_day4 + 31;
										s_day5 = s_day5 + 28;
										s_day6 = s_day6 + 27;
										s_day7 = s_day7 + 21;
										s_day8 = s_day8 + 20;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac - 1, s_day4);
										zavantajenist_dna(s_rik, i_misac - 1, s_day5);
										zavantajenist_dna(s_rik, i_misac - 1, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 5: {
										s_day1 = s_day1 + 4;
										s_day2 = s_day2 + 3;
										s_day3 = s_day3 + 2;
										s_day4 = s_day4 + 1;
										s_day5 = s_day5 + 29;
										s_day6 = s_day6 + 28;
										s_day7 = s_day7 + 22;
										s_day8 = s_day8 + 21;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac - 1, s_day5);
										zavantajenist_dna(s_rik, i_misac - 1, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 6: {
										s_day1 = s_day1 + 5;
										s_day2 = s_day2 + 4;
										s_day3 = s_day3 + 3;
										s_day4 = s_day4 + 2;
										s_day5 = s_day5 + 30;
										s_day6 = s_day6 + 29;
										s_day7 = s_day7 + 23;
										s_day8 = s_day8 + 22;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac - 1, s_day5);
										zavantajenist_dna(s_rik, i_misac - 1, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 7: {
										s_day1 = s_day1 + 6;
										s_day2 = s_day2 + 5;
										s_day3 = s_day3 + 4;
										s_day4 = s_day4 + 3;
										s_day5 = s_day5 + 31;
										s_day6 = s_day6 + 30;
										s_day7 = s_day7 + 24;
										s_day8 = s_day8 + 23;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac - 1, s_day5);
										zavantajenist_dna(s_rik, i_misac - 1, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 8: {
										s_day1 = s_day1 + 7;
										s_day2 = s_day2 + 6;
										s_day3 = s_day3 + 5;
										s_day4 = s_day4 + 4;
										s_day5 = s_day5 + 1;
										s_day6 = s_day6 + 31;
										s_day7 = s_day7 + 25;
										s_day8 = s_day8 + 24;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac - 1, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 9: {
										s_day1 = s_day1 + 8;
										s_day2 = s_day2 + 7;
										s_day3 = s_day3 + 6;
										s_day4 = s_day4 + 5;
										s_day5 = s_day5 + 2;
										s_day6 = s_day6 + 1;
										s_day7 = s_day7 + 26;
										s_day8 = s_day8 + 25;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 10: {
										s_day1 = s_day1 + 9;
										s_day2 = s_day2 + 8;
										s_day3 = s_day3 + 7;
										s_day4 = s_day4 + 6;
										s_day5 = s_day5 + 3;
										s_day6 = s_day6 + 2;
										s_day7 = s_day7 + 27;
										s_day8 = s_day8 + 26;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 11: {
										s_day1 = s_day1 + 10;
										s_day2 = s_day2 + 9;
										s_day3 = s_day3 + 8;
										s_day4 = s_day4 + 7;
										s_day5 = s_day5 + 4;
										s_day6 = s_day6 + 3;
										s_day7 = s_day7 + 28;
										s_day8 = s_day8 + 27;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 12: {
										s_day1 = s_day1 + 11;
										s_day2 = s_day2 + 10;
										s_day3 = s_day3 + 9;
										s_day4 = s_day4 + 8;
										s_day5 = s_day5 + 5;
										s_day6 = s_day6 + 4;
										s_day7 = s_day7 + 29;
										s_day8 = s_day8 + 28;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 13: {
										s_day1 = s_day1 + 12;
										s_day2 = s_day2 + 11;
										s_day3 = s_day3 + 10;
										s_day4 = s_day4 + 9;
										s_day5 = s_day5 + 6;
										s_day6 = s_day6 + 5;
										s_day7 = s_day7 + 30;
										s_day8 = s_day8 + 29;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 14: {
										s_day1 = s_day1 + 13;
										s_day2 = s_day2 + 12;
										s_day3 = s_day3 + 11;
										s_day4 = s_day4 + 10;
										s_day5 = s_day5 + 7;
										s_day6 = s_day6 + 6;
										s_day7 = s_day7 + 31;
										s_day8 = s_day8 + 30;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 15: {
										s_day1 = s_day1 + 14;
										s_day2 = s_day2 + 13;
										s_day3 = s_day3 + 12;
										s_day4 = s_day4 + 11;
										s_day5 = s_day5 + 8;
										s_day6 = s_day6 + 7;
										s_day7 = s_day7 + 1;
										s_day8 = s_day8 + 31;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									default: {
										s_day1 = s_day1 + (day - 1);
										s_day2 = s_day2 + (day - 2);
										s_day3 = s_day3 + (day - 3);
										s_day4 = s_day4 + (day - 4);
										s_day5 = s_day5 + (day - 7);
										s_day6 = s_day6 + (day - 8);
										s_day7 = s_day7 + (day - 14);
										s_day8 = s_day8 + (day - 15);

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac, s_day7);
										zavantajenist_dna(s_rik, i_misac, s_day8);
									}
									}
								} else {

									// System.out.println(30);

									switch (i_den) {
									case 1: {

										s_day1 = s_day1 + 31;
										s_day2 = s_day2 + 30;
										s_day3 = s_day3 + 29;
										s_day4 = s_day4 + 28;
										s_day5 = s_day5 + 25;
										s_day6 = s_day6 + 24;
										s_day7 = s_day7 + 18;
										s_day8 = s_day8 + 17;

										--i_misac;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac, s_day7);
										zavantajenist_dna(s_rik, i_misac, s_day8);

									}
										break;
									case 2: {
										s_day1 = s_day1 + 1;
										s_day2 = s_day2 + 31;
										s_day3 = s_day3 + 30;
										s_day4 = s_day4 + 29;
										s_day5 = s_day5 + 26;
										s_day6 = s_day6 + 25;
										s_day7 = s_day7 + 19;
										s_day8 = s_day8 + 18;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac - 1, s_day2);
										zavantajenist_dna(s_rik, i_misac - 1, s_day3);
										zavantajenist_dna(s_rik, i_misac - 1, s_day4);
										zavantajenist_dna(s_rik, i_misac - 1, s_day5);
										zavantajenist_dna(s_rik, i_misac - 1, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);

									}
										break;
									case 3: {
										s_day1 = s_day1 + 2;
										s_day2 = s_day2 + 1;
										s_day3 = s_day3 + 31;
										s_day4 = s_day4 + 30;
										s_day5 = s_day5 + 27;
										s_day6 = s_day6 + 26;
										s_day7 = s_day7 + 20;
										s_day8 = s_day8 + 19;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac - 1, s_day3);
										zavantajenist_dna(s_rik, i_misac - 1, s_day4);
										zavantajenist_dna(s_rik, i_misac - 1, s_day5);
										zavantajenist_dna(s_rik, i_misac - 1, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 4: {
										s_day1 = s_day1 + 3;
										s_day2 = s_day2 + 2;
										s_day3 = s_day3 + 1;
										s_day4 = s_day4 + 31;
										s_day5 = s_day5 + 28;
										s_day6 = s_day6 + 27;
										s_day7 = s_day7 + 21;
										s_day8 = s_day8 + 20;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac - 1, s_day4);
										zavantajenist_dna(s_rik, i_misac - 1, s_day5);
										zavantajenist_dna(s_rik, i_misac - 1, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 5: {
										s_day1 = s_day1 + 4;
										s_day2 = s_day2 + 3;
										s_day3 = s_day3 + 2;
										s_day4 = s_day4 + 1;
										s_day5 = s_day5 + 29;
										s_day6 = s_day6 + 28;
										s_day7 = s_day7 + 22;
										s_day8 = s_day8 + 21;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac - 1, s_day5);
										zavantajenist_dna(s_rik, i_misac - 1, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 6: {
										s_day1 = s_day1 + 5;
										s_day2 = s_day2 + 4;
										s_day3 = s_day3 + 3;
										s_day4 = s_day4 + 2;
										s_day5 = s_day5 + 30;
										s_day6 = s_day6 + 29;
										s_day7 = s_day7 + 23;
										s_day8 = s_day8 + 22;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac - 1, s_day5);
										zavantajenist_dna(s_rik, i_misac - 1, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 7: {
										s_day1 = s_day1 + 6;
										s_day2 = s_day2 + 5;
										s_day3 = s_day3 + 4;
										s_day4 = s_day4 + 3;
										s_day5 = s_day5 + 31;
										s_day6 = s_day6 + 30;
										s_day7 = s_day7 + 24;
										s_day8 = s_day8 + 23;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac - 1, s_day5);
										zavantajenist_dna(s_rik, i_misac - 1, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 8: {
										s_day1 = s_day1 + 7;
										s_day2 = s_day2 + 6;
										s_day3 = s_day3 + 5;
										s_day4 = s_day4 + 4;
										s_day5 = s_day5 + 1;
										s_day6 = s_day6 + 31;
										s_day7 = s_day7 + 25;
										s_day8 = s_day8 + 24;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac - 1, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 9: {
										s_day1 = s_day1 + 8;
										s_day2 = s_day2 + 7;
										s_day3 = s_day3 + 6;
										s_day4 = s_day4 + 5;
										s_day5 = s_day5 + 2;
										s_day6 = s_day6 + 1;
										s_day7 = s_day7 + 26;
										s_day8 = s_day8 + 25;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 10: {
										s_day1 = s_day1 + 9;
										s_day2 = s_day2 + 8;
										s_day3 = s_day3 + 7;
										s_day4 = s_day4 + 6;
										s_day5 = s_day5 + 3;
										s_day6 = s_day6 + 2;
										s_day7 = s_day7 + 27;
										s_day8 = s_day8 + 26;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 11: {
										s_day1 = s_day1 + 10;
										s_day2 = s_day2 + 9;
										s_day3 = s_day3 + 8;
										s_day4 = s_day4 + 7;
										s_day5 = s_day5 + 4;
										s_day6 = s_day6 + 3;
										s_day7 = s_day7 + 28;
										s_day8 = s_day8 + 27;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 12: {
										s_day1 = s_day1 + 11;
										s_day2 = s_day2 + 10;
										s_day3 = s_day3 + 9;
										s_day4 = s_day4 + 8;
										s_day5 = s_day5 + 5;
										s_day6 = s_day6 + 4;
										s_day7 = s_day7 + 29;
										s_day8 = s_day8 + 28;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 13: {
										s_day1 = s_day1 + 12;
										s_day2 = s_day2 + 11;
										s_day3 = s_day3 + 10;
										s_day4 = s_day4 + 9;
										s_day5 = s_day5 + 6;
										s_day6 = s_day6 + 5;
										s_day7 = s_day7 + 30;
										s_day8 = s_day8 + 29;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 14: {
										s_day1 = s_day1 + 13;
										s_day2 = s_day2 + 12;
										s_day3 = s_day3 + 11;
										s_day4 = s_day4 + 10;
										s_day5 = s_day5 + 7;
										s_day6 = s_day6 + 6;
										s_day7 = s_day7 + 31;
										s_day8 = s_day8 + 30;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac - 1, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									case 15: {
										s_day1 = s_day1 + 14;
										s_day2 = s_day2 + 13;
										s_day3 = s_day3 + 12;
										s_day4 = s_day4 + 11;
										s_day5 = s_day5 + 8;
										s_day6 = s_day6 + 7;
										s_day7 = s_day7 + 1;
										s_day8 = s_day8 + 31;

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac, s_day7);
										zavantajenist_dna(s_rik, i_misac - 1, s_day8);
									}
										break;
									default: {
										s_day1 = s_day1 + (day - 1);
										s_day2 = s_day2 + (day - 2);
										s_day3 = s_day3 + (day - 3);
										s_day4 = s_day4 + (day - 4);
										s_day5 = s_day5 + (day - 7);
										s_day6 = s_day6 + (day - 8);
										s_day7 = s_day7 + (day - 14);
										s_day8 = s_day8 + (day - 15);

										zavantajenist_dna(s_rik, i_misac, s_day1);
										zavantajenist_dna(s_rik, i_misac, s_day2);
										zavantajenist_dna(s_rik, i_misac, s_day3);
										zavantajenist_dna(s_rik, i_misac, s_day4);
										zavantajenist_dna(s_rik, i_misac, s_day5);
										zavantajenist_dna(s_rik, i_misac, s_day6);
										zavantajenist_dna(s_rik, i_misac, s_day7);
										zavantajenist_dna(s_rik, i_misac, s_day8);
									}
									}
								}
							}

							// for (File myFile : new File("prognoz/").listFiles())// РѕС‡РёС‰Р°С”РјРѕ
							// РїР°РїР°РєСѓ prognoz
							// if (myFile.isFile()) myFile.delete();

							// String s_day = "" + (day - 1);
							// String s_month = "" + month;
							// zavantajenist_dna1(s_month, s_day);
							//
							// String s_day1 = "" + (day - 2);
							// zavantajenist_dna1(s_month, s_day1);
							//
							// String s_day2 = "" + (day - 3);
							// zavantajenist_dna1(s_month, s_day2);
							//
							// String s_day3 = "" + (day - 4);
							// zavantajenist_dna1(s_month, s_day3);

							int i_den1 = i_den;
							int i_den2 = i_den;
							int i_den3 = i_den;
							int i_den4 = i_den;

							String s_month = "" + i_misac;

							String s_month1 = s_month;
							String s_month2 = s_month;
							String s_month3 = s_month;
							String s_month4 = s_month;

							if (i_den < 8) {
								i_den1 = i_den + 30;
								i_den2 = i_den + 30;
								i_den3 = i_den + 30;
								i_den4 = i_den + 30;
								s_month = "" + (month - 1);
								s_month1 = s_month;
								s_month2 = s_month;
								s_month3 = s_month;
								s_month4 = s_month;
							}

							if (i_den == 8) {
								i_den1 = i_den;
								i_den2 = i_den + 30;
								i_den3 = i_den + 30;
								i_den4 = i_den + 30;
								s_month = "" + (month - 1);
								s_month1 = "" + month;
								s_month2 = s_month;
								s_month3 = s_month;
								s_month4 = s_month;
							}
							if (i_den >= 9) {
								i_den1 = i_den;
								i_den2 = i_den;
								i_den3 = i_den + 30;
								i_den4 = i_den + 30;
								s_month = "" + (month - 1);
								s_month1 = "" + month;
								s_month2 = s_month1;
								s_month3 = s_month;
								s_month4 = s_month;
							}
							if (i_den >= 10) {
								i_den1 = i_den;
								i_den2 = i_den;
								i_den3 = i_den + 30;
								i_den4 = i_den + 30;
								s_month = "" + (month - 1);
								s_month1 = "" + month;
								s_month2 = s_month1;
								s_month3 = s_month;
								s_month4 = s_month;
							}

							// String s_day4 = "" + (i_den1 - 7);
							// zavantajenist_dna(s_month1, s_day4);
							//
							// String s_day5 = "" + (i_den2 - 8);
							// zavantajenist_dna(s_month2, s_day5);
							//
							// String s_day6 = "" + (i_den3 - 14);
							// zavantajenist_dna(s_month3, s_day6);
							//
							// String s_day7 = "" + (i_den4 - 15);
							// zavantajenist_dna(s_month4, s_day7);

							perseptron1();

						} else {
							JOptionPane.showMessageDialog(null, "Прогнозувати можли тільки на 7 днів у перед");
						}

					}
					if (e.getSource() == b_Prognoz1) {

						// // String s_den = t_den.getText();
						// String s_den = "";
						//
						// int i_den = Integer.parseInt(s_den);

						int perevirka = day + 7;

						switch (i_den) {

						}

						// if (i_den > perevirka) {
						// JOptionPane.showMessageDialog(null,
						// "РќРµРєРѕСЂРµРєС‚РЅРµ РІРІРµРґРµРЅРЅСЏ");
						// } else {
						//
						// int i_max = 0;
						//
						// if (i_den > 23 ) {
						// switch (day) {
						// case 24:
						// i_max = 1;
						// break;
						// case 25:
						// i_max = 2;
						// break;
						// case 26:
						// i_max = 3;
						// break;
						// case 27:
						// i_max = 4;
						// break;
						// case 28:
						// i_max = 5;
						// break;
						// case 29:
						// i_max = 6;
						// break;
						// case 30:
						// i_max = 7;
						// break;
						// }
						//
						// if (i_max < i_den) {
						// JOptionPane.showMessageDialog(null,
						// "РќРµРєРѕСЂРµРєС‚РЅРµ РІРІРµРґРµРЅРЅСЏ");
						// System.out.println("cd");
						//
						// }
						// }
						//
						// }

						for (File myFile : new File("prognoz/").listFiles())// РѕС‡РёС‰Р°С”РјРѕ РїР°РїР°РєСѓ prognoz
							if (myFile.isFile())
								myFile.delete();
						/*
						 * String s_day = "" + (day - 1); String s_month = "" + month;
						 * zavantajenist_dna(s_month, s_day);
						 * 
						 * String s_day1 = "" + (day - 2); zavantajenist_dna(s_month, s_day1);
						 * 
						 * String s_day2 = "" + (day - 3); zavantajenist_dna(s_month, s_day2);
						 * 
						 * String s_day3 = "" + (day - 4); zavantajenist_dna(s_month, s_day3);
						 * 
						 * int i_den1 = i_den; int i_den2 = i_den; int i_den3 = i_den; int i_den4 =
						 * i_den;
						 * 
						 * String s_month1 = s_month; String s_month2 = s_month; String s_month3 =
						 * s_month; String s_month4 = s_month;
						 * 
						 * if (i_den < 8) { i_den1 = i_den + 30; i_den2 = i_den + 30; i_den3 = i_den +
						 * 30; i_den4 = i_den + 30; s_month = "" + (month - 1); s_month1 = s_month;
						 * s_month2 = s_month; s_month3 = s_month; s_month4 = s_month; }
						 * 
						 * if (i_den == 8) { i_den1 = i_den; i_den2 = i_den + 30; i_den3 = i_den + 30;
						 * i_den4 = i_den + 30; s_month = "" + (month - 1); s_month1 = "" + month;
						 * s_month2 = s_month; s_month3 = s_month; s_month4 = s_month; } if (i_den >= 9)
						 * { i_den1 = i_den; i_den2 = i_den; i_den3 = i_den + 30; i_den4 = i_den + 30;
						 * s_month = "" + (month - 1); s_month1 = "" + month; s_month2 = s_month1;
						 * s_month3 = s_month; s_month4 = s_month; } if (i_den >= 10) { i_den1 = i_den;
						 * i_den2 = i_den; i_den3 = i_den + 30; i_den4 = i_den + 30; s_month = "" +
						 * (month - 1); s_month1 = "" + month; s_month2 = s_month1; s_month3 = s_month;
						 * s_month4 = s_month; }
						 * 
						 * // String s_day4 = "" + (i_den1 - 7); // zavantajenist_dna(s_month1, s_day4);
						 * // // String s_day5 = "" + (i_den2 - 8); // zavantajenist_dna(s_month2,
						 * s_day5); // // String s_day6 = "" + (i_den3 - 14); //
						 * zavantajenist_dna(s_month3, s_day6); // // String s_day7 = "" + (i_den4 -
						 * 15); // zavantajenist_dna(s_month4, s_day7);
						 */
						perseptron();

					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Некоректне введення", null, type1);
				}
			}
		}

		private void zavantajenist_dna(String s_rik, int i_Misatc, String i_Chislo) {

			int i_zavantajenist_dna = 0;
			String prioriti = "";
			String s_TruvalistPodii = "";
			PIP = NovaPodia.ReturnPIP();

			String s_Misatc = null;

			switch (i_Misatc) {
			case 1:
				s_Misatc = "Jan";
				break;
			case 2:
				s_Misatc = "Feb";
				break;
			case 3:
				s_Misatc = "Mar";
				break;
			case 4:
				s_Misatc = "Apr";
				break;
			case 5:
				s_Misatc = "May";
				break;
			case 6:
				s_Misatc = "Jun";
				break;
			case 7:
				s_Misatc = "Jul";
				break;
			case 8:
				s_Misatc = "Aug";
				break;
			case 9:
				s_Misatc = "Sep";
				break;
			case 10:
				s_Misatc = "Oct";
				break;
			case 11:
				s_Misatc = "Nov";
				break;
			case 12:
				s_Misatc = "Dec";
				break;
			}

			String put0 = "res/Dani/" + PIP + "/" + s_rik + "/" + s_Misatc + "/" + i_Chislo + "/";
			
			File[] fList;
			File F = new File(put0);

			fList = F.listFiles();
			
			System.out.println(put0);

			for (int i1 = 0; i1 < F.listFiles().length; i1++) {
				// РќСѓР¶РЅС‹ С‚РѕР»СЊРєРѕ РїР°РїРєРё РІ РјРµСЃС‚Рѕ isFile() РїРёС€РёРј
				// isDirectory()
				
				System.out.println(1);
				
				if (fList[i1].isFile()) {

					// System.out.println(String.valueOf(i1) + " - " +
					// fList[i1].getName());

					String put = put0 + fList[i1].getName();
				
					try {
						scn = new Scanner(new File(put));
					} catch (Exception qe) {
						System.out.print("Error");
					}

					while (scn.hasNext()) {
						for (int row = 0; row < Reading.length; row++) {

							for (int col = 0; col < Reading[row].length; col++) {

								Reading[row][col] = scn.next();
								// System.out.print(Reading[row][col]);

								if (col == 2) {
									prioriti = Reading[row][col];

								}
								if (col == 1) {
									s_TruvalistPodii = Reading[row][col]; // тривалсть події

								}
							}
						}
					}
					
					System.out.println(prioriti);
					System.out.println(s_TruvalistPodii);
					
					int i_TruvalistPodii = 0;
					
					switch (s_TruvalistPodii) {
					case "5хвилин":
						i_TruvalistPodii = 5;
						break;
					case "15хвилин":
						i_TruvalistPodii = 15;
						break;
					case "30хвилин":
						i_TruvalistPodii = 30;
						break;
					case "45хвилин":
						i_TruvalistPodii = 45;
						break;
					case "1годину":
						i_TruvalistPodii = 60;
						break;
					case "1год.30хв.":
						i_TruvalistPodii = 90;
						break;
					case "2години":
						i_TruvalistPodii = 120;
						break;
					case "2год.30хв.":
						i_TruvalistPodii = 150;
						break;
					case "3години":
						i_TruvalistPodii = 180;
						break;
					case "4години":
						i_TruvalistPodii = 240;
						break;
					case "5години":
						i_TruvalistPodii = 300;
						break;
					case "6години":
						i_TruvalistPodii = 360;
						break;
					case "8години":
						i_TruvalistPodii = 480;
						break;
					case "9години":
						i_TruvalistPodii = 540;
						break;
					case "12години":
						i_TruvalistPodii = 720;
						break;
					case "24години(доба)":
						i_TruvalistPodii = 1440;
						break;
					}

					scn.close();

					i_TruvalistPodii = 100 / (60*i_TruvalistPodii);
					int r3 = Integer.parseInt(prioriti);

					i_zavantajenist_dna = i_zavantajenist_dna + (i_TruvalistPodii * r3);
				}
			}

			String Fayl = "prognoz/" + i_Misatc + "." + i_Chislo + ".txt";

			String s_zavantajenist_dna = "" + i_zavantajenist_dna;

			try {
				x = new Formatter(Fayl);

			} catch (Exception e) {
			}

			x.format(s_zavantajenist_dna);
			x.close();

		}

		private void perseptron() {

			double d1 = 0;

			String put0 = "prognoz/";
			String Reading1 = "";

			File[] fList;
			File F = new File(put0);

			fList = F.listFiles();
			int i1 = 0;
			double k = 0;

			Chart chart = new ChartBuilder().chartType(ChartType.Bar).width(800).height(600)
					.title("Діаграма прогнозування").xAxisTitle("1-8 Завантаженість недавніх днів,       9 - прогноз")
					.yAxisTitle("Значення завантаженості дня").build();

			for (; i1 < 4; i1++) {
				// РќСѓР¶РЅС‹ С‚РѕР»СЊРєРѕ РїР°РїРєРё РІ РјРµСЃС‚Рѕ isFile() РїРёС€РёРј
				// isDirectory()
				if (fList[i1].isFile()) {
				}
				// System.out.println(String.valueOf(i1) + " - " + fList[i1].getName());

				String put = "prognoz/" + fList[i1].getName();

				try {
					scn = new Scanner(new File(put));
				} catch (Exception qe) {
					System.out.print("Error");
				}

				while (scn.hasNext()) {
					for (int row = 0; row < 1; row++) {

						// Reading1 = Reading1 +" "+ scn.next();
						Reading1 = scn.next();

						int r = Integer.parseInt(Reading1);
						k = k + r;

						d1 = Double.parseDouble(Reading1);

					}
				}
				Result[i1] = d1;
				// System.out.println(Result[i1]);
				scn.close();

			}

			// k=k/8;
			k = k / 4;

			Result1[8] = k;

			chart.addSeries("Вибірка", new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, Result);
			chart.addSeries("Прогноз", new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, Result1);

			chart.getStyleManager().setLegendPosition(LegendPosition.InsideNW);
			new SwingWrapper(chart).displayChart();
			try {
				BitmapEncoder.savePNGWithDPI(chart, "prognoz/Chart.png", 400);
			} catch (IOException e) {
			}

		}

		private void perseptron1() {

			double d1 = 0;

			String put0 = "prognoz/";
			String Reading1 = "";

			File[] fList;
			File F = new File(put0);

			fList = F.listFiles();
			int i1 = 0;
			double k9 = 0;
			double k1_1 = 0;
			double k1_2 = 0;
			double k1_3 = 0;
			double k1_4 = 0;

			int kk = 0;

			Chart chart = new ChartBuilder().chartType(ChartType.Bar).width(800).height(600)
					.title("Діаграма прогнозування").xAxisTitle("1-8 Завантаженість недавніх днів,       9 - прогноз")
					.yAxisTitle("Значення завантаженості дня").build();

			for (; i1 < 8; i1++) {

				String put = "prognoz/" + fList[i1].getName();

				try {
					scn = new Scanner(new File(put));
				} catch (Exception qe) {
					System.out.print("Errorrr");
				}

				while (scn.hasNext()) {
					for (int row = 0; row < 1; row++) {

						Reading1 = scn.next();

						int r = Integer.parseInt(Reading1);

						if (kk == 0 || kk == 1) {
							k1_1 = k1_1 + r;
						}
						if (kk == 3 || kk == 2) {
							k1_2 = k1_2 + r;
						}
						if (kk == 5 || kk == 4) {
							k1_3 = k1_3 + r;
						}
						if (kk == 6 || kk == 7) {
							k1_4 = k1_4 + r;
						}

						d1 = Double.parseDouble(Reading1);

					}
					kk++;
				}
				Result[i1] = d1;

				scn.close();

			}

			k1_1 = k1_1 / 2;
			k1_2 = k1_2 / 2;
			k1_3 = k1_3 / 2;
			k1_4 = k1_4 / 2;

			k1_1 = (k1_1 + k1_2) / 2;
			k1_3 = (k1_3 + k1_4) / 2;

			k9 = (k1_3 + k1_1) / 2;

			Result1[8] = k9;

			chart.addSeries("Вибірка", new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, Result);
			chart.addSeries("Прогноз", new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, Result1);

			chart.getStyleManager().setLegendPosition(LegendPosition.InsideNW);
			new SwingWrapper(chart).displayChart();
			try {
				BitmapEncoder.savePNGWithDPI(chart, "prognoz/Chart.png", 400);
			} catch (IOException e) {
			}

		}
	}
}