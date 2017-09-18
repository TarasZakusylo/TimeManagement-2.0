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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.xeiam.xchart.BitmapEncoder;
import com.xeiam.xchart.Chart;
import com.xeiam.xchart.ChartBuilder;
import com.xeiam.xchart.StyleManager.ChartType;
import com.xeiam.xchart.StyleManager.LegendPosition;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class PrognizuvannaZavantagenosti extends JFrame {

	private static final long serialVersionUID = 1L;

	static JButton b_Prognoz1;

	static JButton b_Prognoz2;
	JLabel Vkazivka, Vkazivka1, Vkazivka2;

	static String s_day1;
	static String s_day2;
	static String s_day3;
	static String s_day4;
	static String s_day5;
	static String s_day6;
	static String s_day7;
	static String s_day8;

	static int type1 = JOptionPane.ERROR_MESSAGE; // Р•РњР‘Р›Р•РњРђ Р’Р†РљРќРђ

	static Calendar calendar = Calendar.getInstance();
	static int year = calendar.get(Calendar.YEAR);
	static int month = calendar.get(Calendar.MONTH) + 1;
	static int day = calendar.get(Calendar.DAY_OF_MONTH);

	static Scanner scn;
	static String[][] Reading = new String[1][4];
	String[][] Reading0 = new String[1][4];
	int[] Reading_P = new int[9];
	int[] Reading_P1 = new int[9];

	static double[] Result = new double[9];
	static double[] Result1 = new double[9];
	double[] grafic = new double[9];

	static Formatter x;

	eHandler handler = new eHandler();

	private JButton b_Menu;
	private JLabel l_fon;
	private JButton b_Nazad;
	private JLabel l_Sogodni;

	private static JDatePickerImpl datePicker;
	Date date = new Date();

	private static String rik = null;
	private static String misac = null;
	private static String den = null;

	static int i_misac = 0;

	private static String PIP;
	private JLabel label;
	private static JLabel l_kartunka;

	private String s_zavantajenist_Sogodni;

	private static JLabel l_ZavantajenistHI1;
	private static JLabel l_ZavantajenistHI2;
	private static JLabel l_ZavantajenistHI3;
	private static JLabel l_ZavantajenistHI4;

	private static JLabel l_MaybutneZavantajenna;

	private static JButton b_VidobrazutuGrafik;
	private static JButton b_ZberegtuGrafik;

	private static int i_rik;
	private static int i_den;

	private static Chart chart;

	public PrognizuvannaZavantagenosti(String s) {
		super(s);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		b_Prognoz1 = new JButton("Прогноз на основі нечіткої логіки");
		b_Prognoz1.setFont(new Font("Impact", Font.PLAIN, 20));
		b_Prognoz1.setBounds(133, 394, 411, 42);
		b_Prognoz2 = new JButton("Прогноз на основі нейронної мережі");
		b_Prognoz2.setFont(new Font("Impact", Font.PLAIN, 20));
		b_Prognoz2.setBounds(133, 436, 411, 42);

		Vkazivka = new JLabel("Вкажіть день для якого буде розраховано завантаженість:");
		Vkazivka.setHorizontalAlignment(SwingConstants.CENTER);
		Vkazivka.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Vkazivka.setBounds(42, 106, 655, 42);
		Vkazivka1 = new JLabel("(для забезпечення почності вказувати");
		Vkazivka1.setHorizontalAlignment(SwingConstants.CENTER);
		Vkazivka1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		Vkazivka1.setBounds(104, 242, 450, 20);
		Vkazivka2 = new JLabel("можна лише на найближчий тиждень)");
		Vkazivka2.setHorizontalAlignment(SwingConstants.CENTER);
		Vkazivka2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		Vkazivka2.setBounds(104, 275, 450, 20);

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
		datePicker.setLocation(219, 202);
		getContentPane().add(datePicker);

		l_Sogodni = new JLabel("Сьогоднішнє завантаження");
		l_Sogodni.setForeground(new Color(0, 0, 0));
		l_Sogodni.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		l_Sogodni.setHorizontalAlignment(SwingConstants.CENTER);
		l_Sogodni.setBounds(680, 161, 263, 42);
		getContentPane().add(l_Sogodni);

		String s_year = year + "";
		String s_day = day + "";

		try {
			s_zavantajenist_Sogodni = PrognizuvannaZavantagenosti.zavantajenist_dna(s_year, month, s_day);
		} catch (Exception e) {
			s_zavantajenist_Sogodni = "На сьогодні подій не зазначено";
		}

		label = new JLabel(s_zavantajenist_Sogodni);
		label.setForeground(new Color(165, 42, 42));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label.setBounds(680, 231, 263, 42);
		getContentPane().add(label);

		l_kartunka = new JLabel("");
		l_kartunka.setHorizontalAlignment(SwingConstants.CENTER);
		l_kartunka.setIcon(new ImageIcon("res/kartunka/kartunkaPrognozuvannaZavanajenocti.png"));
		l_kartunka.setBounds(702, 297, 225, 225);
		getContentPane().add(l_kartunka);

		l_ZavantajenistHI1 = new JLabel("Дуже велика завантаженість");
		l_ZavantajenistHI1.setForeground(new Color(165, 42, 42));
		l_ZavantajenistHI1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		l_ZavantajenistHI1.setHorizontalAlignment(SwingConstants.CENTER);
		// l_ZavantajenistHI1.setBounds(702, 297, 225, 225);
		l_ZavantajenistHI1.setBounds(0, 0, 0, 0);
		getContentPane().add(l_ZavantajenistHI1);

		l_ZavantajenistHI2 = new JLabel("Велика завантаженість");
		l_ZavantajenistHI2.setForeground(new Color(165, 42, 42));
		l_ZavantajenistHI2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		l_ZavantajenistHI2.setHorizontalAlignment(SwingConstants.CENTER);
		// l_ZavantajenistHI2.setBounds(702, 297, 225, 225);
		l_ZavantajenistHI2.setBounds(0, 0, 0, 0);
		getContentPane().add(l_ZavantajenistHI2);

		l_ZavantajenistHI3 = new JLabel("Завантаженість у межах норми");
		l_ZavantajenistHI3.setForeground(new Color(165, 42, 42));
		l_ZavantajenistHI3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		l_ZavantajenistHI3.setHorizontalAlignment(SwingConstants.CENTER);
		// l_ZavantajenistHI3.setBounds(702, 297, 225, 225);
		l_ZavantajenistHI3.setBounds(0, 0, 0, 0);
		getContentPane().add(l_ZavantajenistHI3);

		l_ZavantajenistHI4 = new JLabel("Низька завантаженість");
		l_ZavantajenistHI4.setForeground(new Color(165, 42, 42));
		l_ZavantajenistHI4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		l_ZavantajenistHI4.setHorizontalAlignment(SwingConstants.CENTER);
		// l_ZavantajenistHI4.setBounds(702, 297, 225, 225);
		l_ZavantajenistHI4.setBounds(0, 0, 0, 0);
		getContentPane().add(l_ZavantajenistHI4);

		b_VidobrazutuGrafik = new JButton("Відобразити графік");
		b_VidobrazutuGrafik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PrognizuvannaZavantagenostiGrafik(i_misac, i_den);
			}
		});
		b_VidobrazutuGrafik.setFont(new Font("Impact", Font.PLAIN, 15));
		// b_VidobrazutuGrafik.setBounds(133, 498, 205, 24);
		b_VidobrazutuGrafik.setBounds(0, 0, 0, 0);
		getContentPane().add(b_VidobrazutuGrafik);

		b_ZberegtuGrafik = new JButton("Зберегти графік");
		b_ZberegtuGrafik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Картинки", "png");
				JFileChooser fc = new JFileChooser();
				fc.setFileFilter(filter);
				if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						File file = fc.getSelectedFile();
						BitmapEncoder.savePNGWithDPI(chart, file.getAbsolutePath() + ".png", 70);
						JOptionPane.showMessageDialog(null, "Збережено");
					} catch (Exception e) {
						System.out.println("Всё погибло!");
					}
				}
			}
		});
		b_ZberegtuGrafik.setFont(new Font("Impact", Font.PLAIN, 15));
		// b_ZberegtuGrafik.setBounds(338, 498, 206, 24);
		b_ZberegtuGrafik.setBounds(0, 0, 0, 0);
		getContentPane().add(b_ZberegtuGrafik);

		l_MaybutneZavantajenna = new JLabel("Майбутнє завантаження");
		l_MaybutneZavantajenna.setHorizontalAlignment(SwingConstants.CENTER);
		l_MaybutneZavantajenna.setForeground(Color.BLACK);
		l_MaybutneZavantajenna.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		// l_MaybutneZavantajenna.setBounds(680, 296, 263, 42);
		l_MaybutneZavantajenna.setBounds(0, 0, 0, 0);
		getContentPane().add(l_MaybutneZavantajenna);

		l_fon = new JLabel("");
		// l_fon.setIcon(new
		// ImageIcon("C:\\Users\\ZakkZakk\\Desktop\\fon_Avtoruzacia.jpg"));
		l_fon.setBounds(0, 0, 994, 566);
		l_fon.setForeground(Color.WHITE);
		l_fon.setIcon(new ImageIcon("res/fon/fon_Avtoruzacia.jpg"));
		getContentPane().add(l_fon);

		b_Prognoz1.addActionListener(handler);
		b_Prognoz2.addActionListener(handler);

		setVisible(true);
	}

	public static class eHandler implements ActionListener {
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

			i_rik = Integer.parseInt(rik);
			i_den = Integer.parseInt(den);

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
						zbirDanuh("НМ");
					}
					if (e.getSource() == b_Prognoz1) {
						zbirDanuh("ШІ");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Невистачає даних. Зверніться, будь ласка, пізніше.", null,
							type1);
					// System.out.println(ex.getLocalizedMessage());
					// System.out.println(ex.getMessage());
				}
			}
		}

	}

	private static String zavantajenist_dna(String s_rik, int i_Misatc, String i_Chislo) {

		double d_zavantajenist_dna = 0;
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

		for (int i1 = 0; i1 < F.listFiles().length; i1++) {

			if (fList[i1].isFile()) {

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

				double i_TruvalistPodii = 0;

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
				default: {
					i_TruvalistPodii = 1440;
				}
				}

				scn.close();

				i_TruvalistPodii = i_TruvalistPodii / 60;
				int r3 = Integer.parseInt(prioriti);

				d_zavantajenist_dna = d_zavantajenist_dna + (i_TruvalistPodii * r3) * 100;
			}
		}

		File Path = new File("res/TumcasoviFaylu/Prognoz/Chart");
		Path.mkdirs();

		String Fayl = "res/TumcasoviFaylu/Prognoz/" + i_Misatc + "." + i_Chislo + ".txt";

		int i_double_zavantajenist_dna = (int) d_zavantajenist_dna;

		String s_zavantajenist_dna = "" + i_double_zavantajenist_dna;

		try {
			x = new Formatter(Fayl);

		} catch (Exception e) {
		}

		x.format(s_zavantajenist_dna);
		x.close();

		return s_zavantajenist_dna;
	}

	private static void perseptron(int s_Misatc, int i_Chislo, String s_VubirMetodu) {

		double d1 = 0;

		String put0 = "res/TumcasoviFaylu/Prognoz/";
		String Reading1 = "";

		File[] fList;
		File F = new File(put0);

		fList = F.listFiles();

		double k9 = 0;
		double k1_1 = 0;
		double k1_2 = 0;
		double k1_3 = 0;
		double k1_4 = 0;

		int kk = 0;

		chart = new ChartBuilder().chartType(ChartType.Bar).width(1025).height(590).title("Діаграма прогнозування")
				.xAxisTitle("1-8 Завантаженість недавніх днів,       9 - прогноз")
				.yAxisTitle("Значення завантаженості дня").build();

		if (F.listFiles().length == 7) {
			for (int i1 = 0; i1 < 6; i1++) {

				String put = put0 + fList[i1].getName();

				try {
					scn = new Scanner(new File(put));
				} catch (Exception qe) {
					System.out.print("Errorrr1");
				}

				while (scn.hasNext()) {
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
					kk++;
				}
				Result[i1] = d1;

				scn.close();

			}
			Result[7] = Result[3];
			Result[6] = Result[2];
		} else {

			if (F.listFiles().length == 8) {
				for (int i1 = 0; i1 < 7; i1++) {

					String put = put0 + fList[i1].getName();

					try {
						scn = new Scanner(new File(put));
					} catch (Exception qe) {
						System.out.print("Errorrr2");
					}

					while (scn.hasNext()) {
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

						kk++;
					}
					Result[i1] = d1;
					Result[7] = Result[3];

					scn.close();

				}
			} else {
				for (int i1 = 0; i1 < 8; i1++) {

					String put = put0 + fList[i1].getName();

					try {
						scn = new Scanner(new File(put));
					} catch (Exception qe) {
						System.out.print("Errorrr3");
					}

					while (scn.hasNext()) {
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

						kk++;
					}
					Result[i1] = d1;

					scn.close();

				}
			}
		}

		k1_1 = k1_1 / 2;
		k1_2 = k1_2 / 2;
		k1_3 = k1_3 / 2;
		k1_4 = k1_4 / 2;

		k1_1 = (k1_1 + k1_2) / 2;
		k1_3 = (k1_3 + k1_4) / 2;

		k9 = (k1_3 + k1_1) / 2;

		if (s_VubirMetodu.equals("НМ")) {

			Result1[8] = k9;

			chart.addSeries("Вибірка", new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, Result);
			chart.addSeries("Прогноз", new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, Result1);

			chart.getStyleManager().setLegendPosition(LegendPosition.InsideNW);
			// new SwingWrapper(chart).displayChart();

			try {
				BitmapEncoder.savePNGWithDPI(chart,
						"res/TumcasoviFaylu/Prognoz/Chart/" + s_Misatc + "." + i_Chislo + ".png", 70);

				b_VidobrazutuGrafik.setBounds(133, 498, 205, 24);
				b_ZberegtuGrafik.setBounds(338, 498, 206, 24);
				new PrognizuvannaZavantagenostiGrafik(s_Misatc, i_Chislo);
			} catch (IOException e) {
			}

		} else {
			if (k9 >= 2500) {
				l_ZavantajenistHI1.setBounds(652, 297, 325, 225);
			} else {
				if (k9 >= 1500) {
					l_ZavantajenistHI2.setBounds(652, 297, 325, 225);
				} else {
					if (k9 >= 500) {
						l_ZavantajenistHI3.setBounds(652, 297, 325, 225);
					} else {
						l_ZavantajenistHI4.setBounds(652, 297, 325, 225);
					}
				}
			}

			l_kartunka.setBounds(0, 0, 0, 0);
			l_MaybutneZavantajenna.setBounds(680, 350, 263, 42);

		}

	}

	public static void zbirDanuh(String s_VubirMetodu) {

		for (File myFile : new File("res/TumcasoviFaylu/Prognoz/").listFiles()) {
			if (myFile.isFile()) {
				myFile.delete();
			}
		}

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
			if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {

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

		if (((i_den == day_max1 || i_den == day_max2 || i_den == day_max3 || i_den == day_max4 || i_den == day_max5
				|| i_den == day_max6 || i_den == day_max7) && i_rik == rik_max) && i_misac == misac_max) {

			String s_rik = "" + i_rik;

			if (month == 1) {

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

					s_rik = "" + (i_rik - 1);
					i_misac = 12;

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

					zavantajenist_dna(s_rik, 1, s_day1);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day2);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day3);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day4);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day5);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day6);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day7);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day8);

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
					zavantajenist_dna("" + (i_rik - 1), 12, s_day3);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day4);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day5);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day6);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day7);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day8);
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
					zavantajenist_dna("" + (i_rik - 1), 12, s_day4);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day5);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day6);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day7);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day8);
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
					zavantajenist_dna("" + (i_rik - 1), 12, s_day5);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day6);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day7);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day8);
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
					zavantajenist_dna("" + (i_rik - 1), 12, s_day5);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day6);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day7);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day8);
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
					zavantajenist_dna("" + (i_rik - 1), 12, s_day5);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day6);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day7);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day8);
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
					zavantajenist_dna("" + (i_rik - 1), 12, s_day6);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day7);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day8);
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
					zavantajenist_dna("" + (i_rik - 1), 12, s_day7);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day8);
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
					zavantajenist_dna("" + (i_rik - 1), 12, s_day7);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day8);
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
					zavantajenist_dna("" + (i_rik - 1), 12, s_day7);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day8);
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
					zavantajenist_dna("" + (i_rik - 1), 12, s_day7);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day8);
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
					zavantajenist_dna("" + (i_rik - 1), 12, s_day7);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day8);
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
					zavantajenist_dna("" + (i_rik - 1), 12, s_day7);
					zavantajenist_dna("" + (i_rik - 1), 12, s_day8);
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
					zavantajenist_dna("" + (i_rik - 1), 12, s_day8);
				}
					break;
				default: {
					s_day1 = s_day1 + (day - 1);
					s_day2 = s_day2 + (day - 2);
					s_day3 = s_day3 + (day - 3);
					s_day4 = s_day4 + (day - 4);
					s_day5 = s_day5 + (i_den - 7);
					s_day6 = s_day6 + (i_den - 8);
					s_day7 = s_day7 + (i_den - 14);
					s_day8 = s_day8 + (i_den - 15);

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
						s_day5 = s_day5 + (i_den - 7);
						s_day6 = s_day6 + (i_den - 8);
						s_day7 = s_day7 + (i_den - 14);
						s_day8 = s_day8 + (i_den - 15);

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
					if (month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {

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
							s_day5 = s_day5 + (i_den - 7);
							s_day6 = s_day6 + (i_den - 8);
							s_day7 = s_day7 + (i_den - 14);
							s_day8 = s_day8 + (i_den - 15);

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
							s_day5 = s_day5 + (i_den - 7);
							s_day6 = s_day6 + (i_den - 8);
							s_day7 = s_day7 + (i_den - 14);
							s_day8 = s_day8 + (i_den - 15);

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
			}
			perseptron(i_misac, i_den, s_VubirMetodu);

		} else {
			JOptionPane.showMessageDialog(null, "Прогнозувати можли тільки на 7 днів у перед");
		}

	}
}