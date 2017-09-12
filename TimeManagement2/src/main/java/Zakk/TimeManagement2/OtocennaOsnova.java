package Zakk.TimeManagement2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class OtocennaOsnova {

	private static OtochennaWindow window;
	private static BufferedImage image;

	public OtocennaOsnova() {
		setWindow(new OtochennaWindow());
	}

	public static void setImage(URL url) {
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setImage(File file) {
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveImage(String s_file) {
		try {
			File file = new File(s_file);
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static OtochennaWindow getWindow() {
		return window;
	}

	public static void setWindow(OtochennaWindow window) {
		OtocennaOsnova.window = window;
	}

	public static BufferedImage getImage() {
		return image;
	}

	public static void setImage(BufferedImage image) {
		OtocennaOsnova.image = image;
	}

}
