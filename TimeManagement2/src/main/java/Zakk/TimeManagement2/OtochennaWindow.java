package Zakk.TimeManagement2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class OtochennaWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane;
	private JPanel panel_Stvorutu;
	private JTextArea txtrEnter;
	private JButton b_izMereji;
	private JButton b_izPamyati;
	private JPanel panel_vidkkrutu;
	private JButton b_vuvestu;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JLabel imageLabel;
	private JTextArea t__harakteristici;
	private JScrollPane scrollPane_1;
	private JButton b_ochustutu;

	private JLabel l_fon;

	String s_zberegtu = "";
	String s_inkognito = "res/inkognito.png";
	String s_nazva = "otochenna/";
	String s__harakteristici = "";
	String s_nayavnistPodii = "";

	Formatter formatter_harakteristici;

	int k_nayavnistPodii = 0;
	private JTextField t_Nazva;

	Formatter formatter_k_otocenna;
	int k_otocenna;
	String s_k_otocenna;
	Scanner scanner_k_otocenna;
	private JPanel panel_foto;
	private JPanel panel_harakteristiki;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JLabel l_harakteristiki;

	private OtocennaVidkrutu model;
	private JList<String> list;

	
	StringBuffer sb = new StringBuffer();
	private JButton b_vuvestuProfil;

	String s_k_vuvestuProfil = "";

	private static BufferedImage image_vuvestuProfil;

	private JLabel l_vuvestuProfilPhoto;

	private JButton b_menu;
	private JLabel lblNewLabel;

	private String PIP;

	private File folder;

	private File[] listOfFiles = null;
	private JLabel l_fon1;
	private JLabel l_fon2;

	public OtochennaWindow() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 42, 994, 524);
		getContentPane().add(tabbedPane);
		tabbedPane.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tabbedPane.setForeground(Color.BLACK);

		panel_Stvorutu = new JPanel();
		tabbedPane.addTab("Створити профіль", null, panel_Stvorutu, null);
		panel_Stvorutu.setLayout(null);

		txtrEnter = new JTextArea();
		txtrEnter.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		txtrEnter.setBounds(20, 59, 329, 34);
		txtrEnter.setText("\u0412\u0432\u0435\u0434\u0456\u0442\u044C URL...");
		panel_Stvorutu.add(txtrEnter);

		b_izMereji = new JButton(
				"\u0417\u0430\u0432\u0430\u043D\u0442\u0430\u0436\u0438\u0442\u0438 \u0444\u043E\u0442\u043E \u0456\u0437 \u043C\u0435\u0440\u0435\u0436\u0456");
		b_izMereji.setFont(new Font("Impact", Font.PLAIN, 20));
		b_izMereji.setBounds(10, 11, 481, 35);
		b_izMereji.addActionListener(e -> {
			try {
				OtocennaOsnova.setImage(new URL(txtrEnter.getText()));
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Некоректне введення, або відсутнє з'єднання із Інтернетом !");
			}
		});
		panel_Stvorutu.add(b_izMereji);

		b_izPamyati = new JButton(
				"\u0417\u0430\u0432\u0430\u043D\u0442\u0430\u0436\u0438\u0442\u0438 \u0444\u043E\u0442\u043E \u0456\u0437 \u043F\u0430\u043C'\u044F\u0442\u0456");
		b_izPamyati.setFont(new Font("Impact", Font.PLAIN, 20));
		b_izPamyati.setBounds(492, 11, 484, 35);
		b_izPamyati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int reply = chooser.showOpenDialog(null);
				if (reply == JFileChooser.APPROVE_OPTION) {
					OtocennaOsnova.setImage(chooser.getSelectedFile());
				}
			}
		});
		panel_Stvorutu.add(b_izPamyati);

		b_vuvestu = new JButton("\u0412\u0438\u0432\u0435\u0441\u0442\u0438 \u0444\u043E\u0442\u043E");
		b_vuvestu.setFont(new Font("Impact", Font.PLAIN, 15));
		b_vuvestu.setBounds(351, 66, 138, 24);
		panel_Stvorutu.add(b_vuvestu);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 104, 481, 329);
		panel_Stvorutu.add(scrollPane);

		panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		imageLabel = new JLabel("");
		panel_2.add(imageLabel);

		PIP = NovaPodia.ReturnPIP();
		s_nazva = s_nazva + PIP + "/" ;
		folder = new File(s_nazva);
		folder.mkdirs();
		listOfFiles = folder.listFiles();
		
		JButton b_zberegtu = new JButton("\u0417\u0431\u0435\u0440\u0435\u0433\u0442\u0438");
		b_zberegtu.setFont(new Font("Impact", Font.PLAIN, 20));
		b_zberegtu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (OtocennaOsnova.getImage() == null) {
					File file = new File(s_inkognito);
					OtocennaOsnova.setImage(file);
					imageLabel.setIcon(new ImageIcon(OtocennaOsnova.getImage()));
					imageLabel.updateUI();
					switch (t_Nazva.getText()) {
					case "":
					case " ":
					case "  ":
						JOptionPane.showMessageDialog(null, "Не введено назву, або ім'я !");
						break;
					default:
						zberegtu();
						break;
					}

				} else {
					switch (t_Nazva.getText()) {
					case "":
					case " ":
					case "  ":
						JOptionPane.showMessageDialog(null, "Не введено назву, або ім'я !");
						break;
					default:
						zberegtu();
						break;
					}
				}
			}
		});
		b_zberegtu.setBounds(492, 438, 485, 35);
		panel_Stvorutu.add(b_zberegtu);

		JLabel label_1 = new JLabel(
				"\u041A\u043E\u0440\u043E\u0442\u043A\u0456 \u0445\u0430\u0440\u0430\u043A\u0442\u0435\u0440\u0438\u0441\u0442\u0438\u043A\u0438");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label_1.setBounds(492, 104, 485, 23);
		panel_Stvorutu.add(label_1);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(492, 140, 485, 293);
		panel_Stvorutu.add(scrollPane_1);

		t__harakteristici = new JTextArea();
		t__harakteristici.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		scrollPane_1.setViewportView(t__harakteristici);

		b_ochustutu = new JButton("\u041E\u0447\u0438\u0441\u0442\u0438\u0442\u0438");
		b_ochustutu.setFont(new Font("Impact", Font.PLAIN, 20));
		b_ochustutu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ochstutu();
			}
		});
		b_ochustutu.setBounds(10, 438, 481, 35);
		panel_Stvorutu.add(b_ochustutu);

		t_Nazva = new JTextField();
		t_Nazva.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		t_Nazva.setBounds(707, 57, 270, 34);
		panel_Stvorutu.add(t_Nazva);
		t_Nazva.setColumns(10);

		lblNewLabel = new JLabel("Вкажіть назву, або ім'я");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(492, 59, 215, 32);
		panel_Stvorutu.add(lblNewLabel);
		
		l_fon1 = new JLabel("");
		l_fon1.setIcon(new ImageIcon("res/fon/fon_Otochenna.jpg"));
		l_fon1.setBounds(0, 0, 989, 486);
		panel_Stvorutu.add(l_fon1);
		b_vuvestu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (OtocennaOsnova.getImage() == null) {
					return;
				}
				imageLabel.setIcon(new ImageIcon(OtocennaOsnova.getImage()));
				imageLabel.updateUI();
			}
		});

		panel_vidkkrutu = new JPanel();
		tabbedPane.addTab("Відкрити профіль", null, panel_vidkkrutu, null);
		panel_vidkkrutu.setLayout(null);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(347, 11, 630, 296);
		panel_vidkkrutu.add(scrollPane_2);

		panel_foto = new JPanel();
		scrollPane_2.setViewportView(panel_foto);
		panel_foto.setLayout(new BorderLayout(0, 0));

		l_vuvestuProfilPhoto = new JLabel("");
		panel_foto.add(l_vuvestuProfilPhoto);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(347, 331, 630, 142);
		panel_vidkkrutu.add(scrollPane_3);

		panel_harakteristiki = new JPanel();
		scrollPane_3.setViewportView(panel_harakteristiki);
		panel_harakteristiki.setLayout(new BorderLayout(0, 0));

		JTextArea textArea_vuvestuProfil = new JTextArea();
		textArea_vuvestuProfil.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_harakteristiki.add(textArea_vuvestuProfil, BorderLayout.CENTER);

		l_harakteristiki = new JLabel(
				"\u041A\u043E\u0440\u043E\u0442\u043A\u0456 \u0445\u0430\u0440\u0430\u043A\u0442\u0435\u0440\u0438\u0441\u0442\u0438\u043A\u0438 :");
		l_harakteristiki.setHorizontalAlignment(SwingConstants.CENTER);
		l_harakteristiki.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		l_harakteristiki.setBounds(347, 307, 630, 24);
		panel_vidkkrutu.add(l_harakteristiki);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 11, 325, 384);
		panel_vidkkrutu.add(scrollPane_4);

		model = new OtocennaVidkrutu();
		list = new JList<String>(model);
		list.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		scrollPane_4.setViewportView(list);

		JButton b__udalutu = new JButton("Видалити профіль");
		b__udalutu.setFont(new Font("Impact", Font.PLAIN, 20));
		b__udalutu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File folder1 = new File(s_nazva + list.getSelectedValue());
				removeElement(list.getSelectedValue());
				delete(folder1);
				list.updateUI();
			}
		});
		b__udalutu.setBounds(10, 438, 325, 35);
		panel_vidkkrutu.add(b__udalutu);

		b_vuvestuProfil = new JButton(
				"\u0412\u0438\u0432\u0435\u0441\u0442\u0438 \u043F\u0440\u043E\u0444\u0456\u043B\u044C");
		b_vuvestuProfil.setFont(new Font("Impact", Font.PLAIN, 20));
		b_vuvestuProfil.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				BufferedReader reader;
				try {
					reader = new BufferedReader(
							new FileReader(s_nazva + list.getSelectedValue() + "/" + "harakteristici.txt"));

					String line;
					List<String> lines = new ArrayList<String>();

					while ((line = reader.readLine()) != null) {
						lines.add(line);
					}
					String[] linesAsArray = lines.toArray(new String[lines.size()]);

					for (int i = 0; i < linesAsArray.length; i++) {
						s_k_vuvestuProfil = s_k_vuvestuProfil + linesAsArray[i] + "\n";
					}

				} catch (Exception e1) {
				}

				File file_vuvestuProfil = new File(s_nazva + list.getSelectedValue() + "/" + "photo.png");

				try {
					image_vuvestuProfil = ImageIO.read(file_vuvestuProfil);
				} catch (Exception e1) {
				}

				try {
					l_vuvestuProfilPhoto.setIcon(new ImageIcon(image_vuvestuProfil));
					l_vuvestuProfilPhoto.updateUI();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Виберіть профіль");
				}

				textArea_vuvestuProfil.setText(s_k_vuvestuProfil);
				s_k_vuvestuProfil = "";
				image_vuvestuProfil = null;
			}
		});
		b_vuvestuProfil.setBounds(10, 399, 325, 35);
		panel_vidkkrutu.add(b_vuvestuProfil);
		
		l_fon2 = new JLabel("");
		l_fon2.setIcon(new ImageIcon("C:\\Users\\ZakkZakk\\Desktop\\fon_Avtoruzacia – копія.jpg"));
		l_fon2.setBounds(0, 0, 989, 486);
		panel_vidkkrutu.add(l_fon2);

		for (int i = 0; i < listOfFiles.length; i++) {
			addElement(listOfFiles[i].getName());
		}

		b_menu = new JButton("\u041C\u0435\u043D\u044E");
		b_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Menu("Times");
				setVisible(false);
			}
		});
		b_menu.setFont(new Font("Impact", Font.PLAIN, 25));
		b_menu.setForeground(Color.BLACK);
		b_menu.setBounds(712, 0, 282, 42);
		getContentPane().add(b_menu);

		l_fon = new JLabel("");
		l_fon.setBounds(0, 0, 994, 566);
		l_fon.setForeground(Color.WHITE);
		l_fon.setIcon(new ImageIcon("res/fon/fon_Avtoruzacia.jpg"));
		getContentPane().add(l_fon);

		setVisible(true);

	}

	void ochstutu() {
		imageLabel.setIcon(new ImageIcon(""));
		txtrEnter.setText(null);
		t__harakteristici.setText(null);
		t_Nazva.setText(null);

		panel_2.add(imageLabel);
		panel_Stvorutu.add(scrollPane_1);
		panel_Stvorutu.add(txtrEnter);
		panel_Stvorutu.add(t_Nazva);
	}

	void zberegtu() {

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].getName().equals(t_Nazva.getText())) {
				k_nayavnistPodii = k_nayavnistPodii + 1;
			}
			sb.append(listOfFiles[i].getName() + " ");
		}
		if (k_nayavnistPodii == 0) {
			File Path = new File(s_nazva + t_Nazva.getText());
			Path.mkdirs();
			OtocennaOsnova.saveImage(s_nazva + t_Nazva.getText() + "/photo.png");
			s__harakteristici = t__harakteristici.getText();
			try {
				formatter_harakteristici = new Formatter(s_nazva + t_Nazva.getText() + "/harakteristici.txt");
				formatter_harakteristici.format(s__harakteristici);
				formatter_harakteristici.close();
			} catch (Exception e) {
			}
			JOptionPane.showMessageDialog(null, "Збережено");
			
			try {
				scanner_k_otocenna = new Scanner(new File("res/Dani/" + PIP + "/кількість оточення.txt"));
			} catch (Exception ez1) {
				JOptionPane.showMessageDialog(null, "Помилка відкриття файлу кількість оточення");
			}
			
			while (scanner_k_otocenna.hasNext()) {
				s_k_otocenna = scanner_k_otocenna.next();
			}
			scanner_k_otocenna.close();

			k_otocenna = Integer.parseInt(s_k_otocenna);
			k_otocenna++;
			s_k_otocenna = "" + k_otocenna;

			try {
				formatter_k_otocenna = new Formatter("res/Dani/" + PIP + "/кількість оточення.txt");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "System Error");
			}
			formatter_k_otocenna.format(s_k_otocenna);
			formatter_k_otocenna.close();

			// ochstutu();

			setVisible(false);
			new OtocennaOsnova();

		} else {
			JOptionPane.showMessageDialog(null, "Уже існує така назва, ім'я");
		}
		k_nayavnistPodii = 0;
	}

	void addElement(String s) {
		model.addElements(s);
	}

	void removeElement(int index) {
		model.remuveElements(index);
	}

	void removeElement(String obj) {
		model.remuveElements(obj);
	}

	void delete(File file) {

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
}
