package Zakk.TimeManagement2;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;

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

import com.xeiam.xchart.BitmapEncoder;
import com.xeiam.xchart.Chart;
import com.xeiam.xchart.ChartBuilder;
import com.xeiam.xchart.StyleManager.ChartType;
import com.xeiam.xchart.StyleManager.LegendPosition;

public class PruynattaRihen extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel l_fon;
	private JButton b_Menu;

	JButton Pogoda, Karta, Gotovo, Nazad;
	JButton b_tabluca, b_grafik, b_hvudko, b_anketa;
	JLabel l_Nazva1, l_Nazva2, l_Kohtovnist, l_Pruemnist, l_Dopomoga, l_Vagkist;
	JLabel l_Predstavlenna;
	JTextField t_Nazva1, t_Nazva2;

	String s_Nazva1, s_Nazva2;

	JSlider sl_Kohtovnistl = new JSlider(0, 1, 5, 1);
	int i_Kohtovnistl = 1;
	JSlider sl_Kohtovnist2 = new JSlider(0, 1, 5, 1);
	int i_Kohtovnist2 = 1;
	JSlider sl_Pruemnist1 = new JSlider(0, 1, 5, 1);
	int i_Pruemnist1 = 1;
	JSlider sl_Pruemnist2 = new JSlider(0, 1, 5, 1);
	int i_Pruemnist2 = 1;
	JSlider sl_Vagkist1 = new JSlider(0, 1, 5, 1);
	int i_Vagkist1 = 1;
	JSlider sl_Vagkist2 = new JSlider(0, 1, 5, 1);
	int i_Vagkist2 = 1;

	double u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12;
	double q1, q2, q3;

	eHandler handler = new eHandler();

	SliderListener1 slider1 = new SliderListener1();
	SliderListener2 slider2 = new SliderListener2();
	SliderListener3 slider3 = new SliderListener3();
	SliderListener4 slider4 = new SliderListener4();
	SliderListener5 slider5 = new SliderListener5();
	SliderListener6 slider6 = new SliderListener6();

	Desktop d = Desktop.getDesktop();

	private JLabel label;
	private JLabel l_kartunka;

	public PruynattaRihen(String s) {
		super();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		Pogoda = new JButton("Погода");
		Pogoda.setFont(new Font("Impact", Font.PLAIN, 15));
		Karta = new JButton("Карта");
		Karta.setFont(new Font("Impact", Font.PLAIN, 15));
		Gotovo = new JButton("Готово");
		Gotovo.setFont(new Font("Impact", Font.PLAIN, 25));

		b_hvudko = new JButton("Швидко");
		b_tabluca = new JButton("Таблично");
		b_grafik = new JButton("Графічно");
		Nazad = new JButton("Назад");
		Nazad.setForeground(Color.BLACK);
		Nazad.setFont(new Font("Impact", Font.PLAIN, 25));

		b_hvudko.setFont(new Font("Impact", Font.PLAIN, 25));
		b_hvudko.setForeground(new Color(0, 0, 0));
		b_tabluca.setFont(new Font("Impact", Font.PLAIN, 25));
		b_tabluca.setForeground(new Color(0, 0, 0));
		b_grafik.setFont(new Font("Impact", Font.PLAIN, 25));
		b_grafik.setForeground(new Color(0, 0, 0));

		Pogoda.setBounds(211, 477, 269, 24);
		Karta.setBounds(522, 477, 269, 24);
		Gotovo.setBounds(712, 526, 282, 40);
		Nazad.setBounds(0, 0, 0, 0);

		l_Nazva1 = new JLabel("Назва 1 події:");
		l_Nazva1.setHorizontalAlignment(SwingConstants.CENTER);
		l_Nazva1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		l_Nazva2 = new JLabel("Назва 2 події:");
		l_Nazva2.setHorizontalAlignment(SwingConstants.CENTER);
		l_Nazva2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		l_Pruemnist = new JLabel("Інтуїтивно приблизно оцініть приємність / необхідність події");
		l_Pruemnist.setHorizontalAlignment(SwingConstants.CENTER);
		l_Pruemnist.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		l_Dopomoga = new JLabel("Для допомоги при визначеності ви можете використати :");
		l_Dopomoga.setForeground(new Color(165, 42, 42));
		l_Dopomoga.setHorizontalAlignment(SwingConstants.CENTER);
		l_Dopomoga.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		l_Vagkist = new JLabel("Інтуїтивно приблизно оцініть важкість виконання події(підготовки)");
		l_Vagkist.setHorizontalAlignment(SwingConstants.CENTER);
		l_Vagkist.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		l_Nazva1.setBounds(101, 55, 300, 25);
		l_Nazva2.setBounds(619, 55, 300, 25);
		l_Pruemnist.setBounds(20, 211, 970, 25);
		l_Dopomoga.setBounds(12, 440, 970, 26);
		l_Vagkist.setBounds(12, 302, 970, 25);

		t_Nazva1 = new JTextField("Назва1");
		t_Nazva1.setHorizontalAlignment(SwingConstants.CENTER);
		t_Nazva1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		t_Nazva2 = new JTextField("Назва2");
		t_Nazva2.setHorizontalAlignment(SwingConstants.CENTER);
		t_Nazva2.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		t_Nazva1.setBounds(101, 83, 300, 25);
		t_Nazva2.setBounds(619, 83, 300, 25);
		sl_Kohtovnistl.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		sl_Kohtovnistl.setMajorTickSpacing(5);
		sl_Kohtovnistl.setMinorTickSpacing(1);
		sl_Kohtovnistl.setPaintTicks(true);
		sl_Kohtovnistl.setPaintLabels(true);
		sl_Kohtovnistl.setLabelTable(sl_Kohtovnistl.createStandardLabels(1, 1));
		sl_Kohtovnistl.setOpaque(false);
		sl_Kohtovnist2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		sl_Kohtovnist2.setMajorTickSpacing(5);
		sl_Kohtovnist2.setMinorTickSpacing(1);
		sl_Kohtovnist2.setPaintTicks(true);
		sl_Kohtovnist2.setPaintLabels(true);
		sl_Kohtovnist2.setLabelTable(sl_Kohtovnist2.createStandardLabels(1, 1));
		sl_Kohtovnist2.setOpaque(false);
		sl_Pruemnist1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		sl_Pruemnist1.setMajorTickSpacing(5);
		sl_Pruemnist1.setMinorTickSpacing(1);
		sl_Pruemnist1.setPaintTicks(true);
		sl_Pruemnist1.setPaintLabels(true);
		sl_Pruemnist1.setLabelTable(sl_Pruemnist1.createStandardLabels(1, 1));
		sl_Pruemnist1.setOpaque(false);
		sl_Pruemnist2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		sl_Pruemnist2.setMajorTickSpacing(5);
		sl_Pruemnist2.setMinorTickSpacing(1);
		sl_Pruemnist2.setPaintTicks(true);
		sl_Pruemnist2.setPaintLabels(true);
		sl_Pruemnist2.setLabelTable(sl_Pruemnist2.createStandardLabels(1, 1));
		sl_Pruemnist2.setOpaque(false);
		sl_Vagkist1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		sl_Vagkist1.setMajorTickSpacing(5);
		sl_Vagkist1.setMinorTickSpacing(1);
		sl_Vagkist1.setPaintTicks(true);
		sl_Vagkist1.setPaintLabels(true);
		sl_Vagkist1.setLabelTable(sl_Vagkist1.createStandardLabels(1, 1));
		sl_Vagkist1.setOpaque(false);
		sl_Vagkist2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		sl_Vagkist2.setMajorTickSpacing(5);
		sl_Vagkist2.setMinorTickSpacing(1);
		sl_Vagkist2.setPaintTicks(true);
		sl_Vagkist2.setPaintLabels(true);
		sl_Vagkist2.setLabelTable(sl_Vagkist2.createStandardLabels(1, 1));
		sl_Vagkist2.setOpaque(false);

		sl_Kohtovnistl.setBounds(20, 158, 450, 40);
		getContentPane().add(sl_Kohtovnistl);
		sl_Kohtovnist2.setBounds(522, 158, 460, 40);
		getContentPane().add(sl_Kohtovnist2);
		b_anketa = new JButton("Бажаєте перейти до фіксування події ?");
		b_anketa.setFont(new Font("Impact", Font.PLAIN, 20));
		b_anketa.setBounds(0, 0, 0, 0);
		getContentPane().add(b_anketa);
		b_anketa.addActionListener(handler);

		sl_Pruemnist1.setBounds(20, 249, 450, 40);
		getContentPane().add(sl_Pruemnist1);
		sl_Pruemnist2.setBounds(522, 249, 460, 40);
		getContentPane().add(sl_Pruemnist2);
		sl_Vagkist1.setBounds(20, 343, 460, 40);
		getContentPane().add(sl_Vagkist1);
		sl_Vagkist2.setBounds(522, 343, 460, 40);
		getContentPane().add(sl_Vagkist2);

		getContentPane().add(Pogoda);
		getContentPane().add(Karta);
		getContentPane().add(Gotovo);
		getContentPane().add(Nazad);

		getContentPane().add(b_tabluca);
		getContentPane().add(b_grafik);
		getContentPane().add(b_hvudko);

		l_Predstavlenna = new JLabel("Оберіть вид представлення результатів");
		l_Predstavlenna.setHorizontalAlignment(SwingConstants.CENTER);
		l_Predstavlenna.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		l_Predstavlenna.setForeground(Color.BLUE);
		l_Predstavlenna.setBounds(0, 0, 0, 0);

		getContentPane().add(l_Predstavlenna);
		l_Kohtovnist = new JLabel("Інтуїтивно приблизно оцініть коштовність події");
		l_Kohtovnist.setHorizontalAlignment(SwingConstants.CENTER);
		l_Kohtovnist.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		l_Kohtovnist.setBounds(12, 121, 970, 25);
		getContentPane().add(l_Kohtovnist);

		getContentPane().add(l_Nazva1);
		getContentPane().add(l_Nazva2);
		getContentPane().add(l_Pruemnist);
		getContentPane().add(l_Dopomoga);
		getContentPane().add(l_Vagkist);

		getContentPane().add(t_Nazva1);
		getContentPane().add(t_Nazva2);

		sl_Kohtovnistl.addChangeListener(slider1);
		sl_Kohtovnist2.addChangeListener(slider2);
		sl_Pruemnist1.addChangeListener(slider3);
		sl_Pruemnist2.addChangeListener(slider4);
		sl_Vagkist1.addChangeListener(slider5);
		sl_Vagkist2.addChangeListener(slider6);
		Pogoda.addActionListener(handler);
		Karta.addActionListener(handler);
		Gotovo.addActionListener(handler);
		b_tabluca.addActionListener(handler);
		b_grafik.addActionListener(handler);
		b_hvudko.addActionListener(handler);
		Nazad.addActionListener(handler);

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

		label = new JLabel("Врахуйте, що для деяких подій необхідно враховувати час на дорогу !");
		label.setForeground(new Color(165, 42, 42));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label.setBounds(12, 396, 970, 26);
		getContentPane().add(label);

		l_kartunka = new JLabel("");
		l_kartunka.setHorizontalAlignment(SwingConstants.CENTER);
		l_kartunka.setIcon(new ImageIcon("res/kartunka/PruynattaRihen.png"));
		l_kartunka.setBounds(0, 0, 0, 0);
		getContentPane().add(l_kartunka);

		l_fon = new JLabel("");
		l_fon.setBounds(0, 0, 994, 566);
		l_fon.setForeground(Color.WHITE);
		l_fon.setIcon(new ImageIcon("res/fon/fon_Avtoruzacia.jpg"));
		getContentPane().add(l_fon);

		setVisible(true);

	}

	class SliderListener1 implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			sl_Kohtovnistl = (JSlider) e.getSource();
			i_Kohtovnistl = (int) sl_Kohtovnistl.getValue();
		}
	}

	class SliderListener2 implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			sl_Kohtovnist2 = (JSlider) e.getSource();
			i_Kohtovnist2 = (int) sl_Kohtovnist2.getValue();
		}
	}

	class SliderListener3 implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			sl_Pruemnist1 = (JSlider) e.getSource();
			i_Pruemnist1 = (int) sl_Pruemnist1.getValue();
		}
	}

	class SliderListener4 implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			sl_Pruemnist2 = (JSlider) e.getSource();
			i_Pruemnist2 = (int) sl_Pruemnist2.getValue();
		}
	}

	class SliderListener5 implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			sl_Vagkist1 = (JSlider) e.getSource();
			i_Vagkist1 = (int) sl_Vagkist1.getValue();
		}
	}

	class SliderListener6 implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			sl_Vagkist2 = (JSlider) e.getSource();
			i_Vagkist2 = (int) sl_Vagkist2.getValue();
		}
	}

	public class eHandler implements ActionListener {

		private String PIP;

		public void actionPerformed(ActionEvent e) {

			s_Nazva1 = t_Nazva1.getText();
			s_Nazva2 = t_Nazva2.getText();

			try {
				if (e.getSource() == Nazad) {

					l_Nazva1.setBounds(101, 55, 300, 25);
					l_Nazva2.setBounds(619, 55, 300, 25);
					l_Kohtovnist.setBounds(12, 121, 970, 25);
					l_Pruemnist.setBounds(20, 211, 970, 25);
					l_Dopomoga.setBounds(12, 440, 970, 26);
					l_Vagkist.setBounds(12, 302, 970, 25);
					label.setBounds(12, 396, 970, 26);

					t_Nazva1.setBounds(101, 83, 300, 25);
					t_Nazva2.setBounds(619, 83, 300, 25);

					Pogoda.setBounds(211, 477, 269, 24);
					Karta.setBounds(522, 477, 269, 24);
					Gotovo.setBounds(712, 526, 282, 40);

					sl_Kohtovnistl.setBounds(20, 158, 450, 40);
					sl_Kohtovnist2.setBounds(522, 158, 460, 40);
					sl_Pruemnist1.setBounds(20, 249, 450, 40);
					sl_Pruemnist2.setBounds(522, 249, 460, 40);
					sl_Vagkist1.setBounds(20, 343, 460, 40);
					sl_Vagkist2.setBounds(522, 343, 460, 40);

					Nazad.setBounds(0, 0, 0, 0);
					b_tabluca.setBounds(0, 0, 0, 0);
					b_grafik.setBounds(0, 0, 0, 0);
					b_hvudko.setBounds(0, 0, 0, 0);
					l_Predstavlenna.setBounds(0, 0, 0, 0);
					b_anketa.setBounds(0, 0, 0, 0);

					l_kartunka.setBounds(0, 0, 0, 0);

				}
				if (e.getSource() == Pogoda) {

					try {
						d.browse(new URI(
								"https://sinoptik.ua/%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0-%D0%B2%D0%B8%D0%BD%D0%BD%D0%B8%D1%86%D0%B0"));
					} catch (Exception e1) {
					}
				}
				if (e.getSource() == Karta) {
					try {
						d.browse(new URI(
								"https://www.google.com.ua/maps/place/%D0%92%D0%B8%D0%BD%D0%BD%D0%B8%D1%86%D0%B0,+%D0%92%D0%B8%D0%BD%D0%BD%D0%B8%D1%86%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C/@49.2276433,28.4378695,13z/data=!4m2!3m1!1s0x472d5b65195a6489:0xcbd4e159eedd74fe"));
					} catch (Exception e1) {
					}
				}
				if (e.getSource() == Gotovo) {

					if ((i_Pruemnist1 == i_Pruemnist2) && (i_Kohtovnistl == i_Kohtovnist2)
							&& (i_Vagkist1 == i_Vagkist2)) {
						JOptionPane.showMessageDialog(null,
								"       Помилка всі дані однакові,\nабо не змінені із початкового значення");
					} else {

						i_Kohtovnistl = 6 - i_Kohtovnistl;
						i_Kohtovnist2 = 6 - i_Kohtovnist2;
						i_Vagkist1 = 6 - i_Vagkist1;
						i_Vagkist2 = 6 - i_Vagkist2;

						u5 = i_Kohtovnistl + i_Pruemnist1 + i_Vagkist1;
						u6 = i_Kohtovnistl + i_Pruemnist1 + i_Vagkist1;
						u7 = i_Kohtovnistl + i_Pruemnist1 + i_Vagkist1;
						u8 = i_Kohtovnistl + i_Pruemnist1 + i_Vagkist1;

						u1 = i_Kohtovnist2 + i_Pruemnist2 + i_Vagkist2;
						u2 = i_Kohtovnist2 + i_Pruemnist2 + i_Vagkist2;
						u3 = i_Kohtovnist2 + i_Pruemnist2 + i_Vagkist2;
						u4 = i_Kohtovnist2 + i_Pruemnist2 + i_Vagkist2;

						u9 = (0.5 * (i_Kohtovnistl)) + (i_Pruemnist1) + (0.5 * (i_Vagkist1));
						u10 = (0.5 * (i_Kohtovnist2)) + (i_Pruemnist2) + (0.5 * (i_Vagkist2));
						u11 = (0.5 * ((i_Kohtovnistl + i_Kohtovnist2) / 2)) + (2 * ((i_Pruemnist1 + i_Pruemnist2) / 2))
								+ (0.5 * ((i_Vagkist1 + i_Vagkist2) / 2));
						u12 = (0.5 * ((i_Kohtovnistl + i_Kohtovnist2) / 2))
								+ (0.5 * ((i_Pruemnist1 + i_Pruemnist2) / 2)) + (0.5 * ((i_Vagkist1 + i_Vagkist2) / 2));

						u1 = u1 * 0.01;
						u2 = u2 * 0.889;
						u3 = u3 * 0.001;
						u4 = u4 * 0.1;
						u5 = u5 * 0.889;
						u6 = u6 * 0.01;
						u7 = u7 * 0.001;
						u8 = u8 * 0.1;

						q1 = u1 * 0.01 + u2 * 0.889 + u3 * 0.001 + u4 * 0.1;
						q2 = u5 * 0.889 + u6 * 0.01 + u7 * 0.001 + u8 * 0.1;
						q3 = u9 * 0.25 + u10 * 0.25 + u11 * 0.25 + u12 * 0.25;

						// String s = u1 + " " + u2 + " " + u3 + " " + u4 + "\n" + u5 + " " + u6 + " " +
						// u7 + " "
						// + u8 + "\n" + u9 + " " + u10 + " " + u11 + " " + u12;

						// String s1 = q1 + " " + q2 + " " + q3;

						if (q1 == q2) {
							if (q1 <= q3) {
								JOptionPane.showMessageDialog(null,
										"Найкращою альтернативою є виконання\nобох подій одночасно чи поетапно.");
							} else {
								u5 = i_Kohtovnistl + (2 * i_Pruemnist1) + i_Vagkist1;
								u6 = i_Kohtovnistl + i_Pruemnist1 + i_Vagkist1;
								u7 = i_Kohtovnistl + (2 * i_Pruemnist1) + i_Vagkist1;
								u8 = i_Kohtovnistl + i_Pruemnist1 + i_Vagkist1;

								u1 = i_Kohtovnist2 + (2 * i_Pruemnist2) + i_Vagkist2;
								u2 = i_Kohtovnist2 + i_Pruemnist2 + i_Vagkist2;
								u3 = i_Kohtovnist2 + (2 * i_Pruemnist2) + i_Vagkist2;
								u4 = i_Kohtovnist2 + i_Pruemnist2 + i_Vagkist2;

								u1 = u1 * 0.01;
								u2 = u2 * 0.889;
								u3 = u3 * 0.001;
								u4 = u4 * 0.1;
								u5 = u5 * 0.889;
								u6 = u6 * 0.01;
								u7 = u7 * 0.001;
								u8 = u8 * 0.1;
								u9 = u9 * 0.25;

								q1 = u1 * 0.01 + u2 * 0.889 + u3 * 0.001 + u4 * 0.1;
								q2 = u5 * 0.889 + u6 * 0.01 + u7 * 0.001 + u8 * 0.1;

							}
						}

						sl_Kohtovnistl.setBounds(0, 0, 0, 0);
						sl_Kohtovnist2.setBounds(0, 0, 0, 0);
						sl_Pruemnist1.setBounds(0, 0, 0, 0);
						sl_Pruemnist2.setBounds(0, 0, 0, 0);
						sl_Vagkist1.setBounds(0, 0, 0, 0);
						sl_Vagkist2.setBounds(0, 0, 0, 0);

						Pogoda.setBounds(0, 0, 0, 0);
						Karta.setBounds(0, 0, 0, 0);
						Gotovo.setBounds(0, 0, 0, 0);

						l_Nazva1.setBounds(0, 0, 0, 0);
						l_Nazva2.setBounds(0, 0, 0, 0);
						l_Kohtovnist.setBounds(0, 0, 0, 0);
						l_Pruemnist.setBounds(0, 0, 0, 0);
						l_Dopomoga.setBounds(0, 0, 0, 0);
						l_Vagkist.setBounds(0, 0, 0, 0);

						t_Nazva1.setBounds(0, 0, 0, 0);
						t_Nazva2.setBounds(0, 0, 0, 0);

						Nazad.setBounds(712, 42, 282, 42);
						label.setBounds(0, 0, 0, 0);

						b_tabluca.setBounds(367, 463, 250, 50);
						b_grafik.setBounds(618, 463, 250, 50);
						b_hvudko.setBounds(117, 463, 250, 50);

						l_kartunka.setBounds(318, 131, 325, 319);
						l_Predstavlenna.setBounds(12, 88, 970, 30);

					}
				}

				if (e.getSource() == b_anketa) {
					new NovaPodia("Times");
					setVisible(false);
				}

				if (e.getSource() == b_tabluca) {

					b_anketa.setBounds(211, 514, 580, 25);

					new PruynattaRihenRezult(s_Nazva1, s_Nazva2, u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, q1,
							q2, q3);

				}
				if (e.getSource() == b_grafik) {

					b_anketa.setBounds(211, 514, 580, 25);

					double[] Result = new double[3];
					double[] Result1 = new double[3];
					double[] Result2 = new double[3];

					Result[0] = q1;
					Result1[1] = q2;
					Result2[2] = q3;

					Chart chart = new ChartBuilder().chartType(ChartType.Area).width(1025).height(590)
							.title("Діаграма прийняття рішень")
							.xAxisTitle("1 -Відмінити (перенести) " + s_Nazva1 + ",     2 -Відмінити (перенести) "
									+ s_Nazva2 + ",     3 - виконувати обидві події одночасно(поетапно)")
							.yAxisTitle("Значення за критеріями").build();

					chart.addSeries("Відмінити (перенести) " + s_Nazva1, new double[] { 1, 2, 3 }, Result);
					chart.addSeries("Відмінити (перенести) " + s_Nazva2, new double[] { 1, 2, 3 }, Result1);
					chart.addSeries("Виконувати обидві події одночасно(поетапно)", new double[] { 1, 2, 3 }, Result2);

					chart.getStyleManager().setLegendPosition(LegendPosition.InsideNW);

					java.util.Properties properties = System.getProperties();
					String s_UserName = properties.getProperty("user.name");

					PIP = NovaPodia.ReturnPIP();

					File Path = new File("C:\\Users\\" + s_UserName + "\\Documents\\Times\\" + PIP + "\\Rihenna\\");
					Path.mkdirs();

					// new SwingWrapper(chart).displayChart();

					try {
						BitmapEncoder.savePNGWithDPI(chart, "C:\\Users\\" + s_UserName + "\\Documents\\Times\\" + PIP
								+ "\\Rihenna\\" + s_Nazva1 + " vs " + s_Nazva2 + ".png", 70);
						new PruynattaRihenGrafik("C:\\Users\\" + s_UserName + "\\Documents\\Times\\" + PIP
								+ "\\Rihenna\\" + s_Nazva1 + " vs " + s_Nazva2 + ".png");
						JOptionPane.showMessageDialog(null, "Результати записано у \"Документах\"");
					} catch (IOException eq) {
					}

				}
				if (e.getSource() == b_hvudko) {
					b_anketa.setBounds(211, 514, 580, 25);
					if (q1 < q2) {
						if (q2 < q3) {
							JOptionPane.showMessageDialog(null,
									"Найкращою альтернативою є виконання обох подій одночасно,\n або поетапно, зменшивши час виконання");
						} else {
							JOptionPane.showMessageDialog(null,
									"Найкращою альтернативою є відміна(перенесення)події - " + s_Nazva2);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Найкращою альтернативою є відміна(перенесення)події - " + s_Nazva1);
					}

				}
			} catch (Exception ex) {
			}
		}
	}
}
