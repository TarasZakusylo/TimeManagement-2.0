package Zakk.TimeManagement2;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

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
	private JButton b_ZajnatujChas;

	private JLabel l_vilnuy;
	private JLabel l_vilnuy1;
	private JLabel l_vilnuy2;
	private JLabel l_vilnuy3;
	private JLabel l_vilnuy4;
	private JLabel l_vilnuy5;
	private JLabel l_vilnuy6;
	private JLabel l_vilnuy7;
	private JLabel l_vilnuy8;
	private JLabel l_vilnuy9;
	private JLabel l_vilnuy10;
	private JLabel l_vilnuy11;
	private JLabel l_vilnuy12;
	private JLabel l_vilnuy15;
	private JLabel l_vilnuy14;
	private JLabel l_vilnuy13;
	private JLabel l_vilnuy17;
	private JLabel l_vilnuy16;
	private JLabel l_vilnuy18;
	private JLabel l_vilnuy19;

	private int i_slider_Pruoritet = 1;

	private Formatter formatter_dani;
	private static Scanner scanner_Korustuvac;
	private static String[][] Reading_PIP = new String[1][2];
	private static String Reading_Name;
	private static String Reading_Prizvusko;
	private static String PIP;
	private Scanner scanner_Dani;
	String[][] Reading_Dani = new String[1][4];
	private String Reading_1;
	private String Reading_2;
	private String Reading_3;

	private String rik = null;
	private String misac = null;
	private String misac1 = null;
	private String den = null;

	private String s_NazvaPod;
	private String s_God;
	private String s_Hv;;
	private String s_TruvalistPod;

	private int i_God;
	private int i_Hv;

	int year;
	int month;
	int day;

	int type1 = JOptionPane.ERROR_MESSAGE;

	SliderListener slider = new SliderListener();

	private static Scanner scanner_Name;
	private static String[][] ss_Name = new String[1][4];
	private static String s_Name;

	private JLabel l_koment_ZajnatujChas;
	private Scanner sc_k_podij;
	private String s_k_podij;
	private int i_k_podij = 0;
	private Formatter formatter_k_podij;

	public NovaPodia(String s) {
		super(s);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DAY_OF_MONTH);

		l_Hapka = new JLabel("Вкажіть, будь ласка, параметри події :");
		l_Hapka.setForeground(new Color(165, 42, 42));
		l_Hapka.setHorizontalAlignment(SwingConstants.CENTER);
		l_Hapka.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		l_Hapka.setBounds(0, 36, 552, 42);
		getContentPane().add(l_Hapka);

		b_Menu = new JButton("Меню");
		b_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				s_NazvaPod = t_NazvaPod.getText();
				s_God = t_God.getText();
				s_Hv = t_Hv.getText();

				if (s_NazvaPod.equals("") && s_God.equals("") && s_Hv.equals("")) {
					new Menu("Times");
					setVisible(false);
				} else {
					Object[] options = { "Так, вийти", "Ні, залишитись" };
					int i_menu = JOptionPane.showOptionDialog(null, "Бажаєте вийти ? Введені дані не збережуться.",
							"Попередження", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							options, null);
					if (i_menu == 0) {
						new Menu("Times");
						setVisible(false);
					}
				}
			}
		});
		b_Menu.setForeground(Color.BLACK);
		b_Menu.setFont(new Font("Impact", Font.PLAIN, 25));
		b_Menu.setBounds(712, 0, 282, 42);
		getContentPane().add(b_Menu);

		t_NazvaPod = new JTextField();
		t_NazvaPod.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		t_NazvaPod.setBounds(241, 106, 207, 27);
		getContentPane().add(t_NazvaPod);
		t_NazvaPod.setColumns(10);

		l_NazvaPod = new JLabel("Назва події :");
		l_NazvaPod.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_NazvaPod.setBounds(101, 105, 127, 30);
		getContentPane().add(l_NazvaPod);

		UtilDateModel model = new UtilDateModel();
		// model.setDate(1900 + date.getYear(), date.getMonth(), 10 + date.getDay());
		model.setDate(year, month - 1, day);
		model.setSelected(true);

		// System.out.println(date.getDay());

		JDatePanelImpl datePanel = new JDatePanelImpl(model);

		// datePicker = new JDatePickerImpl(datePanel);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJFormattedTextField().setFont(new Font("Times New Roman", Font.ITALIC, 20));
		datePicker.getJFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);

		datePicker.setSize(230, 27);
		datePicker.setLocation(162, 185);

		getContentPane().add(datePicker);

		PIP = NovaPodia.ReturnPIP();

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
					int i_misac = 0;

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

					if (i_rik < year || i_rik == year && i_misac < month
							|| i_rik == year && i_misac == month && i_den < day) {
						JOptionPane.showMessageDialog(null, "Можна вказувати тільки майбутні дати !", null, type1);
					} else {

						s_NazvaPod = t_NazvaPod.getText();
						s_God = t_God.getText();
						s_Hv = t_Hv.getText();
						s_TruvalistPod = ch_TruvalistPod.getSelectedItem();

						// i_slider_Pruoritet

						i_God = Integer.parseInt(s_God);
						i_Hv = Integer.parseInt(s_Hv);

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

									final File file = new File("res/Dani/" + PIP + "/" + rik + "/" + misac + "/" + den
											+ "/" + i_God + "." + i_Hv + ".txt");

									String s_slider_Pruoritet = i_slider_Pruoritet + "";

									if (file.exists()) {

										try {
											scanner_Dani = new Scanner(file);
										} catch (Exception e) {
											JOptionPane.showMessageDialog(null, "Помилка введення");
										}

										while (scanner_Dani.hasNext()) {
											for (int row = 0; row < Reading_Dani.length; row++) {
												for (int col = 0; col < Reading_Dani[row].length; col++) {
													Reading_Dani[row][col] = scanner_Dani.next();
													switch (col) {
													case 0:
														Reading_1 = Reading_Dani[row][col];
														break;
													case 1:
														Reading_2 = Reading_Dani[row][col];
														break;
													case 2:
														Reading_3 = Reading_Dani[row][col];
														break;
													}
												}
											}
										}
										scanner_Dani.close();

										String s_Reading_3 = "";

										switch (Reading_3) {
										case "1":
											s_Reading_3 = "Дуже низький";
											break;
										case "2":
											s_Reading_3 = "Низький";
											break;
										case "3":
											s_Reading_3 = "Нормальний";
											break;
										case "4":
											s_Reading_3 = "Високий";
											break;
										case "5":
											s_Reading_3 = "Дуже високий";
											break;
										}

										int i_Reading_3 = Integer.parseInt(Reading_3);
										String s_bilchuyPruoritet = "";

										if (i_Reading_3 > i_slider_Pruoritet) {
											s_bilchuyPruoritet = "\nУВАГА, Ви намагаєтесь замінити подію із більшим пріоритетом !";
										}

										if (s_NazvaPod.equals(Reading_1) && s_TruvalistPod.equals(Reading_2)
												&& s_slider_Pruoritet.equals(Reading_3)) {
											JOptionPane.showMessageDialog(null, "Дана подія уже зафіксована");
										} else {
											Object[] options = { "Так, замінити", "Ні, повернутись" };
											int i_menu = JOptionPane.showOptionDialog(null,
													"На даний момент уже заплановано подію : " + Reading_1 + "\n"
															+ "Її пріоритет : " + s_Reading_3 + "\n"
															+ "Бажаєте замінити цю подію ?" + s_bilchuyPruoritet,
													"Попередження", JOptionPane.YES_NO_CANCEL_OPTION,
													JOptionPane.QUESTION_MESSAGE, null, options, null);
											if (i_menu == 0) {
												SaveFile(s_NazvaPod, s_TruvalistPod, s_slider_Pruoritet);
											}
										}
									} else {
										// System.out.println("Файл не существует.");
										// System.out.println("res/Dani/" + PIP + "/" + rik + "/" + misac + "/"
										// + den + "/" + i_God + "." + i_Hv + ".txt");

										SaveFile(s_NazvaPod, s_TruvalistPod, s_slider_Pruoritet);
									}
								}
							}
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Помилка");
				}

			}
		});
		b_Gotovo.setBounds(190, 509, 181, 42);
		getContentPane().add(b_Gotovo);

		t_God = new JTextField();
		t_God.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		t_God.setColumns(10);
		t_God.setBounds(252, 263, 28, 27);
		getContentPane().add(t_God);

		t_Hv = new JTextField();
		t_Hv.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		t_Hv.setColumns(10);
		t_Hv.setBounds(365, 263, 28, 27);
		getContentPane().add(t_Hv);

		ch_TruvalistPod = new Choice();
		ch_TruvalistPod.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		ch_TruvalistPod.setBounds(262, 340, 207, 30);
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
		b_Ochustutu.setBounds(9, 509, 181, 42);
		getContentPane().add(b_Ochustutu);

		l_ChasPod = new JLabel("Час події :");
		l_ChasPod.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_ChasPod.setBounds(116, 260, 118, 30);
		getContentPane().add(l_ChasPod);

		l_Pruoritet = new JLabel("Пріоритет :");
		l_Pruoritet.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_Pruoritet.setBounds(221, 402, 118, 30);
		getContentPane().add(l_Pruoritet);

		l_TruvalistPod = new JLabel("Тривалість події :");
		l_TruvalistPod.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_TruvalistPod.setBounds(86, 340, 181, 30);
		getContentPane().add(l_TruvalistPod);

		l_God = new JLabel("год.");
		l_God.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_God.setBounds(292, 262, 43, 30);
		getContentPane().add(l_God);

		l_Hv = new JLabel("хв.");
		l_Hv.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_Hv.setBounds(405, 262, 43, 30);
		getContentPane().add(l_Hv);

		slider_Pruoritet = new JSlider(SwingConstants.HORIZONTAL, 1, 5, 1);
		slider_Pruoritet.setForeground(Color.BLACK);
		slider_Pruoritet.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		slider_Pruoritet.setPaintTicks(true);
		slider_Pruoritet.setOpaque(false);
		slider_Pruoritet.setPaintLabels(true);
		slider_Pruoritet.setMinorTickSpacing(1);
		slider_Pruoritet.setMajorTickSpacing(1);
		slider_Pruoritet.setBounds(101, 445, 349, 51);
		getContentPane().add(slider_Pruoritet);

		slider_Pruoritet.addChangeListener(slider);

		b_ZajnatujChas = new JButton("Зайнятий час");
		b_ZajnatujChas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Date selectedDate = (Date) datePicker.getModel().getValue();

				String line = "" + selectedDate;
				String[] words = line.split(" ");

				try {

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
					int i_misac = 0;

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

					if (i_rik < year || i_rik == year && i_misac < month
							|| i_rik == year && i_misac == month && i_den < day) {
						JOptionPane.showMessageDialog(null, "Можна вказувати тільки майбутні дати !", null, type1);
					} else {

						String vuvid = "";
						String vuvid1 = "";
						String vuvid2 = "";
						String vuvid3 = "";
						String vuvid4 = "";
						String vuvid5 = "";
						String vuvid6 = "";
						String vuvid7 = "";
						String vuvid8 = "";
						String vuvid9 = "";
						String vuvid10 = "";
						String vuvid11 = "";
						String vuvid12 = "";
						String vuvid13 = "";
						String vuvid14 = "";
						String vuvid15 = "";
						String vuvid16 = "";
						String vuvid17 = "";
						String vuvid18 = "";
						String vuvid19 = "";

						int kk = 0;

						misac1 = misac;

						for (int i = 0; i < 24; i++) {
							for (int j = 0; j < 60; j++) {
								String put = "res/Dani/" + PIP + "/" + rik + "/" + misac + "/" + den + "/" + i + "." + j
										+ ".txt";
								File file = new File(put);

								if (file.exists()) {
									kk++;

									switch (misac) {
									case "Jan":
										misac = "Січ";
										break;
									case "Feb":
										misac = "Лют";
										break;
									case "Mar":
										misac = "Бер";
										break;
									case "Apr":
										misac = "Квіт";
										break;
									case "May":
										misac = "Трав";
										break;
									case "Jun":
										misac = "Черв";
										break;
									case "Jul":
										misac = "Лип";
										break;
									case "Aug":
										misac = "Серп";
										break;
									case "Sep":
										misac = "Вер";
										break;
									case "Oct":
										misac = "Жов";
										break;
									case "Nov":
										misac = "Лист";
										break;
									case "Dec":
										misac = "Груд";
										break;
									}

									// new OpenFile(put);
									s_Name = NovaPodia.OpenFile(put);

									switch (kk) {
									case 1:
										vuvid = vuvid + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 2:
										vuvid1 = vuvid1 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 3:
										vuvid2 = vuvid2 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 4:
										vuvid3 = vuvid3 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 5:
										vuvid4 = vuvid4 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 6:
										vuvid5 = vuvid5 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 7:
										vuvid6 = vuvid6 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 8:
										vuvid7 = vuvid7 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 9:
										vuvid8 = vuvid8 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 10:
										vuvid9 = vuvid9 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 11:
										vuvid10 = vuvid10 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 12:
										vuvid11 = vuvid11 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 13:
										vuvid12 = vuvid12 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 14:
										vuvid13 = vuvid13 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 15:
										vuvid14 = vuvid14 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 16:
										vuvid15 = vuvid15 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 17:
										vuvid16 = vuvid16 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 18:
										vuvid17 = vuvid17 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 19:
										vuvid18 = vuvid18 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									case 20:
										vuvid19 = vuvid19 + misac + "/" + den + "  " + i + ":" + j + " - " + s_Name;
										break;
									default:
										JOptionPane.showMessageDialog(null, "Даний день є дуууже завантаженим.", null,
												type1);
										i = 70;
										j = 70;
										break;
									}
									misac = misac1;
								}
							}
						}

						l_vilnuy.setText(vuvid);
						l_vilnuy1.setText(vuvid1);
						l_vilnuy2.setText(vuvid2);
						l_vilnuy3.setText(vuvid3);
						l_vilnuy4.setText(vuvid4);
						l_vilnuy5.setText(vuvid5);
						l_vilnuy6.setText(vuvid6);
						l_vilnuy7.setText(vuvid7);
						l_vilnuy8.setText(vuvid8);
						l_vilnuy9.setText(vuvid9);
						l_vilnuy10.setText(vuvid10);
						l_vilnuy11.setText(vuvid11);
						l_vilnuy12.setText(vuvid12);
						l_vilnuy13.setText(vuvid13);
						l_vilnuy14.setText(vuvid14);
						l_vilnuy15.setText(vuvid15);
						l_vilnuy16.setText(vuvid16);
						l_vilnuy17.setText(vuvid17);
						l_vilnuy18.setText(vuvid18);
						l_vilnuy19.setText(vuvid19);

						if (kk == 0) {
							l_kartunka.setBounds(505, 91, 464, 443);
							l_koment_ZajnatujChas.setBounds(0, 0, 0, 0);
							JOptionPane.showMessageDialog(null, "На цей день подій не зафіксовано");
						} else {
							l_kartunka.setBounds(0, 0, 0, 0);
							l_koment_ZajnatujChas.setBounds(527, 71, 442, 30);
						}
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Помилка");
				}
			}
		});
		b_ZajnatujChas.setFont(new Font("Impact", Font.PLAIN, 25));
		b_ZajnatujChas.setBounds(371, 509, 181, 42);
		getContentPane().add(b_ZajnatujChas);

		l_vilnuy = new JLabel("");
		l_vilnuy.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy1 = new JLabel("");
		l_vilnuy1.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy1.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy2 = new JLabel("");
		l_vilnuy2.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy2.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy3 = new JLabel("");
		l_vilnuy3.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy3.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy4 = new JLabel("");
		l_vilnuy4.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy4.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy5 = new JLabel("");
		l_vilnuy5.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy5.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy6 = new JLabel("");
		l_vilnuy6.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy6.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy7 = new JLabel("");
		l_vilnuy7.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy7.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy8 = new JLabel("");
		l_vilnuy8.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy8.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy9 = new JLabel("");
		l_vilnuy9.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy9.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy10 = new JLabel("");
		l_vilnuy10.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy10.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy11 = new JLabel("");
		l_vilnuy11.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy11.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy12 = new JLabel("");
		l_vilnuy12.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy12.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy13 = new JLabel("");
		l_vilnuy13.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy13.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy14 = new JLabel("");
		l_vilnuy14.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy14.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy15 = new JLabel("");
		l_vilnuy15.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy15.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy16 = new JLabel("");
		l_vilnuy16.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy16.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy17 = new JLabel("");
		l_vilnuy17.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy17.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy18 = new JLabel("");
		l_vilnuy18.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy18.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_vilnuy19 = new JLabel("");
		l_vilnuy19.setHorizontalAlignment(SwingConstants.CENTER);
		l_vilnuy19.setFont(new Font("Times New Roman", Font.ITALIC, 20));

		l_vilnuy.setBounds(511, 112, 230, 27);
		getContentPane().add(l_vilnuy);
		l_vilnuy1.setBounds(511, 152, 230, 27);
		getContentPane().add(l_vilnuy1);
		l_vilnuy2.setBounds(511, 193, 230, 27);
		getContentPane().add(l_vilnuy2);
		l_vilnuy3.setBounds(511, 233, 230, 27);
		getContentPane().add(l_vilnuy3);
		l_vilnuy4.setBounds(511, 268, 230, 27);
		getContentPane().add(l_vilnuy4);
		l_vilnuy5.setBounds(511, 307, 230, 27);
		getContentPane().add(l_vilnuy5);
		l_vilnuy6.setBounds(511, 347, 230, 27);
		getContentPane().add(l_vilnuy6);
		l_vilnuy7.setBounds(511, 387, 230, 27);
		getContentPane().add(l_vilnuy7);
		l_vilnuy8.setBounds(511, 427, 230, 30);
		getContentPane().add(l_vilnuy8);
		l_vilnuy9.setBounds(511, 469, 230, 27);
		getContentPane().add(l_vilnuy9);
		l_vilnuy10.setBounds(753, 112, 229, 27);
		getContentPane().add(l_vilnuy10);
		l_vilnuy11.setBounds(753, 152, 229, 27);
		getContentPane().add(l_vilnuy11);
		l_vilnuy12.setBounds(753, 193, 229, 27);
		getContentPane().add(l_vilnuy12);
		l_vilnuy13.setBounds(753, 230, 229, 27);
		getContentPane().add(l_vilnuy13);
		l_vilnuy14.setBounds(753, 268, 229, 27);
		getContentPane().add(l_vilnuy14);
		l_vilnuy15.setBounds(753, 307, 229, 27);
		getContentPane().add(l_vilnuy15);
		l_vilnuy16.setBounds(753, 347, 229, 27);
		getContentPane().add(l_vilnuy16);
		l_vilnuy17.setBounds(753, 387, 229, 27);
		getContentPane().add(l_vilnuy17);
		l_vilnuy18.setBounds(753, 429, 229, 27);
		getContentPane().add(l_vilnuy18);
		l_vilnuy19.setBounds(753, 469, 229, 27);
		getContentPane().add(l_vilnuy19);

		l_koment_ZajnatujChas = new JLabel("Події вказані на :");
		l_koment_ZajnatujChas.setFont(new Font("Times New Roman", Font.BOLD, 23));
		l_koment_ZajnatujChas.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(l_koment_ZajnatujChas);
		// l_koment_ZajnatujChas.setBounds(527, 71, 442, 30);

		l_kartunka = new JLabel("");
		l_kartunka.setBounds(505, 91, 464, 443);
		l_kartunka.setHorizontalAlignment(SwingConstants.CENTER);
		l_kartunka.setIcon(new ImageIcon("res/kartunka/NovaPodia.png"));
		getContentPane().add(l_kartunka);

		l_fon = new JLabel("");
		l_fon.setBounds(0, 0, 994, 566);
		l_fon.setForeground(Color.WHITE);
		l_fon.setIcon(new ImageIcon("res/fon/fon_Avtoruzacia.jpg"));
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
		ch_TruvalistPod.add("5хвилин");
		ch_TruvalistPod.add("15хвилин");
		ch_TruvalistPod.add("30хвилин");
		ch_TruvalistPod.add("45хвилин");
		ch_TruvalistPod.add("1годину");
		ch_TruvalistPod.add("1год.30хв.");
		ch_TruvalistPod.add("2години");
		ch_TruvalistPod.add("2год.30хв.");
		ch_TruvalistPod.add("3години");
		ch_TruvalistPod.add("4години");
		ch_TruvalistPod.add("5години");
		ch_TruvalistPod.add("6години");
		ch_TruvalistPod.add("8години");
		ch_TruvalistPod.add("9години");
		ch_TruvalistPod.add("12години");
		ch_TruvalistPod.add("24години(доба)");
		ch_TruvalistPod.add("36години");
		ch_TruvalistPod.add("48години(2доби)");
		ch_TruvalistPod.add("72години(3доби)");
		ch_TruvalistPod.add("тиждень");
		ch_TruvalistPod.add("1,5тижні");
		ch_TruvalistPod.add("2тижні");
		ch_TruvalistPod.add("місяць");
		ch_TruvalistPod.add("2місяці");
		ch_TruvalistPod.add("3місяці");
		ch_TruvalistPod.add("6місяців");
		ch_TruvalistPod.add("9місяців");
		ch_TruvalistPod.add("рік");
		ch_TruvalistPod.add("2роки");
		ch_TruvalistPod.add("3роки");
		ch_TruvalistPod.add("5років");
	}

	void SaveFile(String Name, String Truvalist, String Pruoritet) {
		File file1 = new File("res/Dani/" + PIP + "/" + rik + "/" + misac + "/" + den + "/");
		file1.mkdirs();

		try {
			formatter_dani = new Formatter(
					"res/Dani/" + PIP + "/" + rik + "/" + misac + "/" + den + "/" + i_God + "." + i_Hv + ".txt");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "System Error");
		}
		formatter_dani.format(Name + " " + Truvalist + " " + Pruoritet + " " + "-");
		formatter_dani.close();

		// System.out.println(s_NazvaPod + " " + s_TruvalistPod + " " +
		// i_slider_Pruoritet);

		File file_k_Podij = new File("res/Dani/" + PIP + "/кількість всіх подій.txt");

		if (file_k_Podij.exists()) {
			try {
				sc_k_podij = new Scanner(new File("res/Dani/" + PIP + "/кількість всіх подій.txt"));
			} catch (Exception ez) {
				JOptionPane.showMessageDialog(null, "Помилка відкриття файлу кількості всіх подій");
			}

			while (sc_k_podij.hasNext()) {
				s_k_podij = sc_k_podij.next();
			}
			sc_k_podij.close();

			i_k_podij = Integer.parseInt(s_k_podij);
		}

		i_k_podij++;
		s_k_podij = "" + i_k_podij;

		try {
			formatter_k_podij = new Formatter("res/Dani/" + PIP + "/кількість всіх подій.txt");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Помилка береження файлу кількості всіх подій");
		}
		formatter_k_podij.format(s_k_podij);
		formatter_k_podij.close();

		Object[] options = { "Так, бажаю", "Ні, перейти до меню" };
		int i_menu = JOptionPane.showOptionDialog(null, "Подію збережено. Бажаєте вказати ше події ?", "Попередження",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
		if (i_menu == 1) {
			new Menu("Times");
			setVisible(false);
		}
	}

	public static String OpenFile(String file) {
		try {
			scanner_Name = new Scanner(new File(file));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Помилка введення");
		}

		while (scanner_Name.hasNext()) {
			for (int row = 0; row < ss_Name.length; row++) {
				for (int col = 0; col < ss_Name[row].length; col++) {
					ss_Name[row][col] = scanner_Name.next();
					if (col == 0) {
						s_Name = ss_Name[row][col];
					}
				}
			}
		}
		scanner_Name.close();
		return s_Name;
	}

	public static String ReturnPIP() {
		try {
			scanner_Korustuvac = new Scanner(new File("res/TumcasoviFaylu/Korustuvac.txt"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Помилка введення");
		}

		while (scanner_Korustuvac.hasNext()) {
			for (int row = 0; row < Reading_PIP.length; row++) {
				for (int col = 0; col < Reading_PIP[row].length; col++) {
					Reading_PIP[row][col] = scanner_Korustuvac.next();
					if (col == 0) {
						Reading_Name = Reading_PIP[row][col];
					}
					if (col == 1) {
						Reading_Prizvusko = Reading_PIP[row][col];
					}
				}
			}
		}

		PIP = Reading_Name + " " + Reading_Prizvusko;

		scanner_Korustuvac.close();

		return PIP;
	}
}
