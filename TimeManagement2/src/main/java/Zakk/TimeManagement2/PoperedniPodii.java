package Zakk.TimeManagement2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class PoperedniPodii extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel l_fon;
	private static JLabel l_kartunka;
	private static JLabel l_komentar;
	private JButton b_Menu;

	private static JDatePickerImpl datePicker;

	Date date = new Date();

	private static String rik = null;
	private static String misac = null;
	private static String misac1;
	private static String den = null;

	private static int year;
	private static int month;
	private static int day;

	private static int type1 = JOptionPane.ERROR_MESSAGE;

	static private String PIP;

	private static Scanner scanner_file_Vukonanna;
	private static String s_file_Vukonanna;
	private static String[][] ss_file_Vukonanna = new String[1][4];

	private static String s_Name;

	private static JLabel l_data;
	private static JLabel l_data1;
	private static JLabel l_data2;
	private static JLabel l_data3;
	private static JLabel l_data4;
	private static JLabel l_data5;
	private static JLabel l_data6;
	private static JLabel l_data7;
	private static JLabel l_data8;
	private static JLabel l_data9;

	private static JLabel l_nazva = new JLabel("");
	private static JLabel l_nazva1 = new JLabel("");
	private static JLabel l_nazva2 = new JLabel("");
	private static JLabel l_nazva3 = new JLabel("");
	private static JLabel l_nazva4 = new JLabel("");
	private static JLabel l_nazva5 = new JLabel("");
	private static JLabel l_nazva6 = new JLabel("");
	private static JLabel l_nazva7 = new JLabel("");
	private static JLabel l_nazva8 = new JLabel("");
	private static JLabel l_nazva9 = new JLabel("");

	private static JLabel l_HapkaData;
	private static JLabel l_HapkaNazva;
	private static JLabel l_HapkaVukonanna;
	private static JLabel l_HapkaData1 = new JLabel("Дата :");
	private static JLabel l_HapkaNazva1 = new JLabel("Назва");
	private static JLabel l_HapkaVukonanna1 = new JLabel("Виконання :");

	private static JLabel l_nazva19 = new JLabel("");
	private static JLabel l_nazva18 = new JLabel("");
	private static JLabel l_nazva17 = new JLabel("");
	private static JLabel l_nazva16 = new JLabel("");
	private static JLabel l_nazva15 = new JLabel("");
	private static JLabel l_nazva14 = new JLabel("");
	private static JLabel l_nazva13 = new JLabel("");
	private static JLabel l_nazva12 = new JLabel("");
	private static JLabel l_nazva11 = new JLabel("");
	private static JLabel l_nazva10 = new JLabel("");
	private static JLabel l_data19 = new JLabel("");
	private static JLabel l_data18 = new JLabel("");
	private static JLabel l_data17 = new JLabel("");
	private static JLabel l_data16 = new JLabel("");
	private static JLabel l_data15 = new JLabel("");
	private static JLabel l_data14 = new JLabel("");
	private static JLabel l_data13 = new JLabel("");
	private static JLabel l_data12 = new JLabel("");
	private static JLabel l_data11 = new JLabel("");
	private static JLabel l_data10 = new JLabel("");

	private static JCheckBox checkBox = new JCheckBox("");;
	private static JCheckBox checkBox1 = new JCheckBox("");
	private static JCheckBox checkBox3 = new JCheckBox("");
	private static JCheckBox checkBox2 = new JCheckBox("");
	private static JCheckBox checkBox7 = new JCheckBox("");
	private static JCheckBox checkBox6 = new JCheckBox("");
	private static JCheckBox checkBox5 = new JCheckBox("");
	private static JCheckBox checkBox4 = new JCheckBox("");
	private static JCheckBox checkBox9 = new JCheckBox("");
	private static JCheckBox checkBox8 = new JCheckBox("");
	private static JCheckBox checkBox10 = new JCheckBox("");
	private static JCheckBox checkBox11 = new JCheckBox("");
	private static JCheckBox checkBox12 = new JCheckBox("");
	private static JCheckBox checkBox13 = new JCheckBox("");
	private static JCheckBox checkBox_14 = new JCheckBox("");
	private static JCheckBox checkBox_15 = new JCheckBox("");
	private static JCheckBox checkBox_16 = new JCheckBox("");
	private static JCheckBox checkBox_17 = new JCheckBox("");
	private static JCheckBox checkBox_18 = new JCheckBox("");
	private static JCheckBox checkBox_19 = new JCheckBox("");

	private static String s_save_checkBox;
	private static String s_save_checkBox1;
	private static String s_save_checkBox2;
	private static String s_save_checkBox3;
	private static String s_save_checkBox4;
	private static String s_save_checkBox5;
	private static String s_save_checkBox6;
	private static String s_save_checkBox7;
	private static String s_save_checkBox8;
	private static String s_save_checkBox9;
	private static String s_save_checkBox10;
	private static String s_save_checkBox11;
	private static String s_save_checkBox12;
	private static String s_save_checkBox13;
	private static String s_save_checkBox14;
	private static String s_save_checkBox15;
	private static String s_save_checkBox16;
	private static String s_save_checkBox17;
	private static String s_save_checkBox18;
	private static String s_save_checkBox19;

	private static Formatter formatter_k_podij;
	private static Scanner scanner_vukonanna;
	private static String[][] Reading_CheckBox = new String[1][4];
	private static String Reading_Name;
	private static String Reading_Prizvusko;
	private static String Reading_Prioritet;
	private static String s_vesFail;

	public PoperedniPodii(String s) {
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

		l_komentar = new JLabel("Оберіть, будь ласка, дату, що Вас цікавить :");
		l_komentar.setForeground(new Color(165, 42, 42));
		l_komentar.setHorizontalAlignment(SwingConstants.CENTER);
		l_komentar.setFont(new Font("Times New Roman", Font.ITALIC, 30));
		l_komentar.setBounds(102, 47, 793, 30);
		getContentPane().add(l_komentar);

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
		datePicker.setLocation(296, 90);

		getContentPane().add(datePicker);

		PIP = NovaPodia.ReturnPIP();

		l_data = new JLabel("");
		l_data.setHorizontalAlignment(SwingConstants.CENTER);
		l_data.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data1 = new JLabel("");
		l_data1.setHorizontalAlignment(SwingConstants.CENTER);
		l_data1.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data2 = new JLabel("");
		l_data2.setHorizontalAlignment(SwingConstants.CENTER);
		l_data2.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data3 = new JLabel("");
		l_data3.setHorizontalAlignment(SwingConstants.CENTER);
		l_data3.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data4 = new JLabel("");
		l_data4.setHorizontalAlignment(SwingConstants.CENTER);
		l_data4.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data5 = new JLabel("");
		l_data5.setHorizontalAlignment(SwingConstants.CENTER);
		l_data5.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data6 = new JLabel("");
		l_data6.setHorizontalAlignment(SwingConstants.CENTER);
		l_data6.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data7 = new JLabel("");
		l_data7.setHorizontalAlignment(SwingConstants.CENTER);
		l_data7.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data8 = new JLabel("");
		l_data8.setHorizontalAlignment(SwingConstants.CENTER);
		l_data8.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data9 = new JLabel("");
		l_data9.setHorizontalAlignment(SwingConstants.CENTER);
		l_data9.setFont(new Font("Times New Roman", Font.ITALIC, 20));

		l_data.setBounds(67, 168, 118, 27);
		getContentPane().add(l_data);
		l_data1.setBounds(67, 208, 118, 27);
		getContentPane().add(l_data1);
		l_data2.setBounds(67, 249, 118, 27);
		getContentPane().add(l_data2);
		l_data3.setBounds(67, 289, 118, 27);
		getContentPane().add(l_data3);
		l_data4.setBounds(67, 324, 118, 27);
		getContentPane().add(l_data4);
		l_data5.setBounds(67, 363, 118, 27);
		getContentPane().add(l_data5);
		l_data6.setBounds(67, 403, 118, 27);
		getContentPane().add(l_data6);
		l_data7.setBounds(67, 443, 118, 27);
		getContentPane().add(l_data7);
		l_data8.setBounds(67, 483, 118, 30);
		getContentPane().add(l_data8);
		l_data9.setBounds(67, 525, 118, 27);
		getContentPane().add(l_data9);

		l_nazva.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva.setBounds(199, 168, 118, 27);
		getContentPane().add(l_nazva);

		l_nazva1.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva1.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva1.setBounds(199, 208, 118, 27);
		getContentPane().add(l_nazva1);

		l_nazva2.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva2.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva2.setBounds(199, 249, 118, 27);
		getContentPane().add(l_nazva2);

		l_nazva3.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva3.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva3.setBounds(199, 289, 118, 27);
		getContentPane().add(l_nazva3);

		l_nazva4.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva4.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva4.setBounds(199, 324, 118, 27);
		getContentPane().add(l_nazva4);

		l_nazva5.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva5.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva5.setBounds(199, 363, 118, 27);
		getContentPane().add(l_nazva5);

		l_nazva6.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva6.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva6.setBounds(199, 403, 118, 27);
		getContentPane().add(l_nazva6);

		l_nazva7.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva7.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva7.setBounds(199, 445, 118, 27);
		getContentPane().add(l_nazva7);

		l_nazva8.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva8.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva8.setBounds(199, 485, 118, 30);
		getContentPane().add(l_nazva8);

		l_nazva9.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva9.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva9.setBounds(199, 527, 118, 27);
		getContentPane().add(l_nazva9);

		final JButton b_Gotovo = new JButton("Готово");
		b_Gotovo.setFont(new Font("Impact", Font.PLAIN, 20));
		b_Gotovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b_Gotovo();
			}
		});
		b_Gotovo.setBounds(566, 87, 110, 30);
		getContentPane().add(b_Gotovo);

		l_HapkaData = new JLabel("Дата :");
		l_HapkaData.setHorizontalAlignment(SwingConstants.CENTER);
		l_HapkaData.setFont(new Font("Times New Roman", Font.BOLD, 20));
		l_HapkaData.setBounds(0, 0, 0, 0);
		getContentPane().add(l_HapkaData);

		l_HapkaNazva = new JLabel("Назва");
		l_HapkaNazva.setHorizontalAlignment(SwingConstants.CENTER);
		l_HapkaNazva.setFont(new Font("Times New Roman", Font.BOLD, 20));
		l_HapkaNazva.setBounds(0, 0, 0, 0);
		getContentPane().add(l_HapkaNazva);

		l_HapkaVukonanna = new JLabel("Виконання :");
		l_HapkaVukonanna.setHorizontalAlignment(SwingConstants.CENTER);
		l_HapkaVukonanna.setFont(new Font("Times New Roman", Font.BOLD, 20));
		l_HapkaVukonanna.setBounds(0, 0, 0, 0);
		getContentPane().add(l_HapkaVukonanna);

		l_HapkaData1.setHorizontalAlignment(SwingConstants.CENTER);
		l_HapkaData1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		l_HapkaData1.setBounds(0, 0, 0, 0);
		getContentPane().add(l_HapkaData1);

		l_HapkaNazva1.setHorizontalAlignment(SwingConstants.CENTER);
		l_HapkaNazva1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		l_HapkaNazva1.setBounds(0, 0, 0, 0);
		getContentPane().add(l_HapkaNazva1);

		l_HapkaVukonanna1.setHorizontalAlignment(SwingConstants.CENTER);
		l_HapkaVukonanna1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		l_HapkaVukonanna1.setBounds(0, 0, 0, 0);
		getContentPane().add(l_HapkaVukonanna1);

		l_nazva19.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva19.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva19.setBounds(664, 525, 118, 27);

		getContentPane().add(l_nazva19);
		l_nazva18.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva18.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva18.setBounds(664, 483, 118, 30);

		getContentPane().add(l_nazva18);
		l_nazva17.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva17.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva17.setBounds(664, 443, 118, 27);

		getContentPane().add(l_nazva17);
		l_nazva16.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva16.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva16.setBounds(664, 401, 118, 27);

		getContentPane().add(l_nazva16);
		l_nazva15.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva15.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva15.setBounds(664, 361, 118, 27);

		getContentPane().add(l_nazva15);
		l_nazva14.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva14.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva14.setBounds(664, 322, 118, 27);

		getContentPane().add(l_nazva14);
		l_nazva13.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva13.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva13.setBounds(664, 287, 118, 27);

		getContentPane().add(l_nazva13);
		l_nazva12.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva12.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva12.setBounds(664, 247, 118, 27);

		getContentPane().add(l_nazva12);
		l_nazva11.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva11.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva11.setBounds(664, 206, 118, 27);

		getContentPane().add(l_nazva11);
		l_nazva10.setHorizontalAlignment(SwingConstants.CENTER);
		l_nazva10.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_nazva10.setBounds(664, 166, 118, 27);

		getContentPane().add(l_nazva10);
		l_data19.setHorizontalAlignment(SwingConstants.CENTER);
		l_data19.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data19.setBounds(532, 523, 118, 27);

		getContentPane().add(l_data19);
		l_data18.setHorizontalAlignment(SwingConstants.CENTER);
		l_data18.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data18.setBounds(532, 481, 118, 30);

		getContentPane().add(l_data18);
		l_data17.setHorizontalAlignment(SwingConstants.CENTER);
		l_data17.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data17.setBounds(532, 441, 118, 27);

		getContentPane().add(l_data17);
		l_data16.setHorizontalAlignment(SwingConstants.CENTER);
		l_data16.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data16.setBounds(532, 401, 118, 27);

		getContentPane().add(l_data16);
		l_data15.setHorizontalAlignment(SwingConstants.CENTER);
		l_data15.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data15.setBounds(532, 361, 118, 27);

		getContentPane().add(l_data15);
		l_data14.setHorizontalAlignment(SwingConstants.CENTER);
		l_data14.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data14.setBounds(532, 322, 118, 27);

		getContentPane().add(l_data14);
		l_data13.setHorizontalAlignment(SwingConstants.CENTER);
		l_data13.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data13.setBounds(532, 287, 118, 27);

		getContentPane().add(l_data13);
		l_data12.setHorizontalAlignment(SwingConstants.CENTER);
		l_data12.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data12.setBounds(532, 247, 118, 27);

		getContentPane().add(l_data12);
		l_data11.setHorizontalAlignment(SwingConstants.CENTER);
		l_data11.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data11.setBounds(532, 206, 118, 27);

		getContentPane().add(l_data11);
		l_data10.setHorizontalAlignment(SwingConstants.CENTER);
		l_data10.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		l_data10.setBounds(532, 166, 118, 27);

		getContentPane().add(l_data10);

		ochystyty();

		checkBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (checkBox.isSelected() == true) {
					SaveCheckBox(s_save_checkBox, "+");
				} else {
					SaveCheckBox(s_save_checkBox, "-");
				}
			}
		});

		checkBox.setOpaque(false);
		getContentPane().add(checkBox);

		checkBox1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox1.isSelected() == true) {
					SaveCheckBox(s_save_checkBox1, "+");
				} else {
					SaveCheckBox(s_save_checkBox1, "-");
				}
			}
		});
		checkBox1.setOpaque(false);
		getContentPane().add(checkBox1);

		checkBox3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox3.isSelected() == true) {
					SaveCheckBox(s_save_checkBox3, "+");
				} else {
					SaveCheckBox(s_save_checkBox3, "-");
				}
			}
		});
		checkBox3.setOpaque(false);
		getContentPane().add(checkBox3);

		checkBox2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox2.isSelected() == true) {
					SaveCheckBox(s_save_checkBox2, "+");
				} else {
					SaveCheckBox(s_save_checkBox2, "-");
				}
			}
		});
		checkBox2.setOpaque(false);
		getContentPane().add(checkBox2);

		checkBox7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox7.isSelected() == true) {
					SaveCheckBox(s_save_checkBox7, "+");
				} else {
					SaveCheckBox(s_save_checkBox7, "-");
				}
			}
		});
		checkBox7.setOpaque(false);
		getContentPane().add(checkBox7);

		checkBox6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox6.isSelected() == true) {
					SaveCheckBox(s_save_checkBox6, "+");
				} else {
					SaveCheckBox(s_save_checkBox6, "-");
				}
			}
		});
		checkBox6.setOpaque(false);
		getContentPane().add(checkBox6);

		checkBox5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox5.isSelected() == true) {
					SaveCheckBox(s_save_checkBox5, "+");
				} else {
					SaveCheckBox(s_save_checkBox5, "-");
				}
			}
		});
		checkBox5.setOpaque(false);
		getContentPane().add(checkBox5);

		checkBox4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox4.isSelected() == true) {
					SaveCheckBox(s_save_checkBox4, "+");
				} else {
					SaveCheckBox(s_save_checkBox4, "-");
				}
			}
		});
		checkBox4.setOpaque(false);
		getContentPane().add(checkBox4);

		checkBox9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox9.isSelected() == true) {
					SaveCheckBox(s_save_checkBox9, "+");
				} else {
					SaveCheckBox(s_save_checkBox9, "-");
				}
			}
		});
		checkBox9.setOpaque(false);
		getContentPane().add(checkBox9);

		checkBox8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox8.isSelected() == true) {
					SaveCheckBox(s_save_checkBox8, "+");
				} else {
					SaveCheckBox(s_save_checkBox8, "-");
				}
			}
		});
		checkBox8.setOpaque(false);
		getContentPane().add(checkBox8);

		checkBox10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox10.isSelected() == true) {
					SaveCheckBox(s_save_checkBox10, "+");
				} else {
					SaveCheckBox(s_save_checkBox10, "-");
				}
			}
		});
		checkBox10.setOpaque(false);
		getContentPane().add(checkBox10);

		checkBox11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox11.isSelected() == true) {
					SaveCheckBox(s_save_checkBox11, "+");
				} else {
					SaveCheckBox(s_save_checkBox11, "-");
				}
			}
		});
		checkBox11.setOpaque(false);
		getContentPane().add(checkBox11);

		checkBox12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox12.isSelected() == true) {
					SaveCheckBox(s_save_checkBox12, "+");
				} else {
					SaveCheckBox(s_save_checkBox12, "-");
				}
			}
		});
		checkBox12.setOpaque(false);
		getContentPane().add(checkBox12);

		checkBox13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox13.isSelected() == true) {
					SaveCheckBox(s_save_checkBox13, "+");
				} else {
					SaveCheckBox(s_save_checkBox13, "-");
				}
			}
		});
		checkBox13.setOpaque(false);
		getContentPane().add(checkBox13);

		checkBox_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox_14.isSelected() == true) {
					SaveCheckBox(s_save_checkBox14, "+");
				} else {
					SaveCheckBox(s_save_checkBox14, "-");
				}
			}
		});
		checkBox_14.setOpaque(false);
		getContentPane().add(checkBox_14);

		checkBox_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox_15.isSelected() == true) {
					SaveCheckBox(s_save_checkBox15, "+");
				} else {
					SaveCheckBox(s_save_checkBox15, "-");
				}
			}
		});
		checkBox_15.setOpaque(false);
		getContentPane().add(checkBox_15);

		checkBox_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox_16.isSelected() == true) {
					SaveCheckBox(s_save_checkBox16, "+");
				} else {
					SaveCheckBox(s_save_checkBox16, "-");
				}
			}
		});
		checkBox_16.setOpaque(false);
		getContentPane().add(checkBox_16);

		checkBox_17.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox_17.isSelected() == true) {
					SaveCheckBox(s_save_checkBox17, "+");
				} else {
					SaveCheckBox(s_save_checkBox17, "-");
				}
			}
		});
		checkBox_17.setOpaque(false);
		getContentPane().add(checkBox_17);

		checkBox_18.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox_18.isSelected() == true) {
					SaveCheckBox(s_save_checkBox18, "+");
				} else {
					SaveCheckBox(s_save_checkBox18, "-");
				}
			}
		});
		checkBox_18.setOpaque(false);
		getContentPane().add(checkBox_18);

		checkBox_19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkBox_19.isSelected() == true) {
					SaveCheckBox(s_save_checkBox19, "+");
				} else {
					SaveCheckBox(s_save_checkBox19, "-");
				}
			}
		});
		checkBox_19.setOpaque(false);
		getContentPane().add(checkBox_19);

		l_kartunka = new JLabel("");
		l_kartunka.setIcon(new ImageIcon("res/kartunka/kartunta_PoperedniPodii.png"));
		l_kartunka.setBounds(532, 149, 354, 363);
		l_kartunka.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(l_kartunka);

		l_fon = new JLabel("");
//		l_fon.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				b_Gotovo();
//			}
//		});
		// l_fon.setIcon(new
		// ImageIcon("C:\\Users\\ZakkZakk\\Desktop\\fon_Avtoruzacia.jpg"));
		l_fon.setBounds(0, 0, 994, 566);
		l_fon.setForeground(Color.WHITE);
		l_fon.setIcon(new ImageIcon("res/fon/fon_Avtoruzacia.jpg"));
		getContentPane().add(l_fon);

		setVisible(true);
	}

	public static String OpenFilesVukonanna(String file) {
		try {
			scanner_file_Vukonanna = new Scanner(new File(file));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Помилка введення");
		}

		while (scanner_file_Vukonanna.hasNext()) {
			for (int row = 0; row < ss_file_Vukonanna.length; row++) {
				for (int col = 0; col < ss_file_Vukonanna[row].length; col++) {
					ss_file_Vukonanna[row][col] = scanner_file_Vukonanna.next();
					if (col == 3) {
						s_file_Vukonanna = ss_file_Vukonanna[row][col];
					}
				}
			}
		}
		scanner_file_Vukonanna.close();

		return s_file_Vukonanna;
	}

	static void ochystyty() {
		checkBox.setBounds(0, 0, 0, 0);
		checkBox1.setBounds(0, 0, 0, 0);
		checkBox3.setBounds(0, 0, 0, 0);
		checkBox2.setBounds(0, 0, 0, 0);
		checkBox7.setBounds(0, 0, 0, 0);
		checkBox6.setBounds(0, 0, 0, 0);
		checkBox5.setBounds(0, 0, 0, 0);
		checkBox4.setBounds(0, 0, 0, 0);
		checkBox9.setBounds(0, 0, 0, 0);
		checkBox8.setBounds(0, 0, 0, 0);
		checkBox10.setBounds(0, 0, 0, 0);
		checkBox11.setBounds(0, 0, 0, 0);
		checkBox12.setBounds(0, 0, 0, 0);
		checkBox13.setBounds(0, 0, 0, 0);
		checkBox_14.setBounds(0, 0, 0, 0);
		checkBox_15.setBounds(0, 0, 0, 0);
		checkBox_16.setBounds(0, 0, 0, 0);
		checkBox_17.setBounds(0, 0, 0, 0);
		checkBox_18.setBounds(0, 0, 0, 0);
		checkBox_19.setBounds(0, 0, 0, 0);
	}

	public static void SaveCheckBox(String file, String s_ZnachennaCheckBox) {

		try {
			scanner_vukonanna = new Scanner(new File(file));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Помилка введення");
		}

		while (scanner_vukonanna.hasNext()) {
			for (int row = 0; row < Reading_CheckBox.length; row++) {
				for (int col = 0; col < Reading_CheckBox[row].length; col++) {
					Reading_CheckBox[row][col] = scanner_vukonanna.next();
					if (col == 0) {
						Reading_Name = Reading_CheckBox[row][col];
					}
					if (col == 1) {
						Reading_Prizvusko = Reading_CheckBox[row][col];
					}
					if (col == 1) {
						Reading_Prioritet = Reading_CheckBox[row][col];
					}
				}
			}
		}

		s_vesFail = Reading_Name + " " + Reading_Prizvusko + " " + Reading_Prioritet + " " + s_ZnachennaCheckBox;

		try {
			formatter_k_podij = new Formatter(new File(file));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Помилка береження файлу кількості всіх подій");
		}

		formatter_k_podij.format(s_vesFail);
		formatter_k_podij.close();
		scanner_vukonanna.close();

	}

	public static void b_Gotovo() {

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

			if (i_rik > year || i_rik == year && i_misac > month || i_rik == year && i_misac == month && i_den > day) {
				JOptionPane.showMessageDialog(null, "Можна вказувати тільки минулі дати !", null, type1);
			} else {

				String s_data = null;
				String s_data1 = null;
				String s_data2 = null;
				String s_data3 = null;
				String s_data4 = null;
				String s_data5 = null;
				String s_data6 = null;
				String s_data7 = null;
				String s_data8 = null;
				String s_data9 = null;
				String s_data10 = null;
				String s_data11 = null;
				String s_data12 = null;
				String s_data13 = null;
				String s_data14 = null;
				String s_data15 = null;
				String s_data16 = null;
				String s_data17 = null;
				String s_data18 = null;
				String s_data19 = null;

				String s_nazva = null;
				String s_nazva1 = null;
				String s_nazva2 = null;
				String s_nazva3 = null;
				String s_nazva4 = null;
				String s_nazva5 = null;
				String s_nazva6 = null;
				String s_nazva7 = null;
				String s_nazva8 = null;
				String s_nazva9 = null;
				String s_nazva10 = null;
				String s_nazva11 = null;
				String s_nazva12 = null;
				String s_nazva13 = null;
				String s_nazva14 = null;
				String s_nazva15 = null;
				String s_nazva16 = null;
				String s_nazva17 = null;
				String s_nazva18 = null;
				String s_nazva19 = null;

				String s_vukonanna = null;

				int kk = 0;

				misac1 = misac;
				ochystyty();

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

							s_file_Vukonanna = OpenFilesVukonanna(put);

							s_vukonanna = s_file_Vukonanna;

							switch (kk) {
							case 1:
								checkBox.setBounds(375, 171, 97, 25);
								s_data = misac + "/" + den + "  " + i + ":" + j;
								s_nazva = s_Name;
								s_save_checkBox = put;
								if (s_vukonanna.equals("+")) {
									checkBox.setSelected(true);
								} else {
									checkBox.setSelected(false);
								}
								break;
							case 2:
								checkBox1.setBounds(375, 211, 97, 25);
								s_data1 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva1 = s_Name;
								s_save_checkBox1 = put;
								if (s_vukonanna.equals("+")) {
									checkBox1.setSelected(true);
								} else {
									checkBox1.setSelected(false);
								}
								break;
							case 3:
								checkBox2.setBounds(375, 252, 97, 25);
								s_data2 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva2 = s_Name;
								s_save_checkBox2 = put;
								if (s_vukonanna.equals("+")) {
									checkBox2.setSelected(true);
								} else {
									checkBox2.setSelected(false);
								}
								break;
							case 4:
								checkBox3.setBounds(375, 292, 97, 25);
								s_data3 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva3 = s_Name;
								s_save_checkBox3 = put;
								if (s_vukonanna.equals("+")) {
									checkBox3.setSelected(true);
								} else {
									checkBox3.setSelected(false);
								}
								break;
							case 5:
								checkBox4.setBounds(375, 327, 97, 25);
								s_data4 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva4 = s_Name;
								s_save_checkBox4 = put;
								if (s_vukonanna.equals("+")) {
									checkBox4.setSelected(true);
								} else {
									checkBox4.setSelected(false);
								}
								break;
							case 6:
								checkBox5.setBounds(375, 366, 97, 25);
								s_data5 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva5 = s_Name;
								s_save_checkBox5 = put;
								if (s_vukonanna.equals("+")) {
									checkBox5.setSelected(true);
								} else {
									checkBox5.setSelected(false);
								}
								break;
							case 7:
								checkBox6.setBounds(375, 406, 97, 25);
								s_data6 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva6 = s_Name;
								s_save_checkBox6 = put;
								if (s_vukonanna.equals("+")) {
									checkBox6.setSelected(true);
								} else {
									checkBox6.setSelected(false);
								}
								break;
							case 8:
								checkBox7.setBounds(375, 446, 97, 25);
								s_data7 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva7 = s_Name;
								s_save_checkBox7 = put;
								if (s_vukonanna.equals("+")) {
									checkBox7.setSelected(true);
								} else {
									checkBox7.setSelected(false);
								}
								break;
							case 9:
								checkBox8.setBounds(375, 488, 97, 25);
								s_data8 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva8 = s_Name;
								s_save_checkBox8 = put;
								if (s_vukonanna.equals("+")) {
									checkBox8.setSelected(true);
								} else {
									checkBox8.setSelected(false);
								}
								break;
							case 10:
								checkBox9.setBounds(375, 528, 97, 25);
								s_data9 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva9 = s_Name;
								s_save_checkBox9 = put;
								if (s_vukonanna.equals("+")) {
									checkBox9.setSelected(true);
								} else {
									checkBox9.setSelected(false);
								}
								break;
							case 11:
								checkBox10.setBounds(850, 168, 97, 25);
								s_data10 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva10 = s_Name;
								s_save_checkBox10 = put;
								if (s_vukonanna.equals("+")) {
									checkBox10.setSelected(true);
								} else {
									checkBox10.setSelected(false);
								}
								break;
							case 12:
								checkBox11.setBounds(850, 208, 97, 25);
								s_data11 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva11 = s_Name;
								s_save_checkBox11 = put;
								if (s_vukonanna.equals("+")) {
									checkBox11.setSelected(true);
								} else {
									checkBox11.setSelected(false);
								}
								break;
							case 13:
								checkBox12.setBounds(850, 249, 97, 25);
								s_data12 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva12 = s_Name;
								s_save_checkBox12 = put;
								if (s_vukonanna.equals("+")) {
									checkBox12.setSelected(true);
								} else {
									checkBox12.setSelected(false);
								}
								break;
							case 14:
								checkBox13.setBounds(850, 289, 97, 25);
								s_data13 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva13 = s_Name;
								s_save_checkBox13 = put;
								if (s_vukonanna.equals("+")) {
									checkBox13.setSelected(true);
								} else {
									checkBox13.setSelected(false);
								}
								break;
							case 15:
								checkBox_14.setBounds(850, 324, 97, 25);
								s_data14 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva14 = s_Name;
								s_save_checkBox14 = put;
								if (s_vukonanna.equals("+")) {
									checkBox_14.setSelected(true);
								} else {
									checkBox_14.setSelected(false);
								}
								break;
							case 16:
								checkBox_15.setBounds(850, 363, 97, 25);
								s_data15 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva15 = s_Name;
								s_save_checkBox15 = put;
								if (s_vukonanna.equals("+")) {
									checkBox_15.setSelected(true);
								} else {
									checkBox_15.setSelected(false);
								}
								break;
							case 17:
								checkBox_16.setBounds(850, 403, 97, 25);
								s_data16 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva16 = s_Name;
								s_save_checkBox16 = put;
								if (s_vukonanna.equals("+")) {
									checkBox_16.setSelected(true);
								} else {
									checkBox_16.setSelected(false);
								}
								break;
							case 18:
								checkBox_17.setBounds(850, 443, 97, 25);
								s_data17 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva17 = s_Name;
								s_save_checkBox17 = put;
								if (s_vukonanna.equals("+")) {
									checkBox_17.setSelected(true);
								} else {
									checkBox_17.setSelected(false);
								}
								break;
							case 19:
								checkBox_18.setBounds(850, 485, 97, 25);
								s_data18 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva18 = s_Name;
								s_save_checkBox18 = put;
								if (s_vukonanna.equals("+")) {
									checkBox_18.setSelected(true);
								} else {
									checkBox_18.setSelected(false);
								}
								break;
							case 20:
								checkBox_19.setBounds(850, 525, 97, 25);
								s_data19 = misac + "/" + den + "  " + i + ":" + j;
								s_nazva19 = s_Name;
								s_save_checkBox19 = put;
								if (s_vukonanna.equals("+")) {
									checkBox_19.setSelected(true);
								} else {
									checkBox_19.setSelected(false);
								}
								break;
							default:
								JOptionPane.showMessageDialog(null, "Даний день є дуууже завантаженим.", null, type1);
								i = 70;
								j = 70;
								break;
							}
							misac = misac1;
						}
					}
				}

				l_data.setText(s_data);
				l_data1.setText(s_data1);
				l_data2.setText(s_data2);
				l_data3.setText(s_data3);
				l_data4.setText(s_data4);
				l_data5.setText(s_data5);
				l_data6.setText(s_data6);
				l_data7.setText(s_data7);
				l_data8.setText(s_data8);
				l_data9.setText(s_data9);
				l_data10.setText(s_data10);
				l_data11.setText(s_data11);
				l_data12.setText(s_data12);
				l_data13.setText(s_data13);
				l_data14.setText(s_data14);
				l_data15.setText(s_data15);
				l_data16.setText(s_data16);
				l_data17.setText(s_data17);
				l_data18.setText(s_data18);
				l_data19.setText(s_data19);

				l_nazva.setText(s_nazva);
				l_nazva1.setText(s_nazva1);
				l_nazva2.setText(s_nazva2);
				l_nazva3.setText(s_nazva3);
				l_nazva4.setText(s_nazva4);
				l_nazva5.setText(s_nazva5);
				l_nazva6.setText(s_nazva6);
				l_nazva7.setText(s_nazva7);
				l_nazva8.setText(s_nazva8);
				l_nazva9.setText(s_nazva9);
				l_nazva10.setText(s_nazva10);
				l_nazva11.setText(s_nazva11);
				l_nazva12.setText(s_nazva12);
				l_nazva13.setText(s_nazva13);
				l_nazva14.setText(s_nazva14);
				l_nazva15.setText(s_nazva15);
				l_nazva16.setText(s_nazva16);
				l_nazva17.setText(s_nazva17);
				l_nazva18.setText(s_nazva18);
				l_nazva19.setText(s_nazva19);

				if (kk <= 10) {
					l_kartunka.setBounds(532, 149, 354, 363);
					l_HapkaData1.setBounds(0, 0, 0, 0);
					l_HapkaNazva1.setBounds(0, 0, 0, 0);
					l_HapkaVukonanna1.setBounds(0, 0, 0, 0);

					l_HapkaData.setBounds(67, 130, 118, 27);
					l_HapkaNazva.setBounds(199, 130, 118, 27);
					l_HapkaVukonanna.setBounds(329, 130, 118, 27);
				} else {
					l_kartunka.setBounds(0, 0, 0, 0);
					l_HapkaData1.setBounds(532, 128, 118, 27);
					l_HapkaNazva1.setBounds(664, 128, 118, 27);
					l_HapkaVukonanna1.setBounds(794, 128, 118, 27);

					l_HapkaData.setBounds(67, 130, 118, 27);
					l_HapkaNazva.setBounds(199, 130, 118, 27);
					l_HapkaVukonanna.setBounds(329, 130, 118, 27);
				}

				if (kk == 0) {
					l_HapkaData.setBounds(0, 0, 0, 0);
					l_HapkaNazva.setBounds(0, 0, 0, 0);
					l_HapkaVukonanna.setBounds(0, 0, 0, 0);

					JOptionPane.showMessageDialog(null, "На цей день подій не зафіксовано");
				}

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Помилка");
		}

	}
}
