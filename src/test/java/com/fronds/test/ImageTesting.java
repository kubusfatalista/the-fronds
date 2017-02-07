package com.fronds.test;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageTesting {

	String koalaImg = "C:\\Koala.jpg";
	String tulipImg = "C:\\Kwiot.jpg";
	String mieszkoImg = "C:\\Mieszko.jpg";
	String resultFolder = "C:\\resizedImgs\\";

	@Test
	public void resizeAndSaveImgs() throws IOException {

		BufferedImage originalImg = ImageIO.read(new File(mieszkoImg));
		int type = originalImg.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImg.getType();
		BufferedImage squaredImg = cropToSquare(originalImg);
		ImageIO.write(squaredImg, "jpg", new File(resultFolder + "squared.jpg"));

		// 200x200
		ImageIO.write(resizeImage(squaredImg, type, 200, 200), "jpg", new File(resultFolder + "squared200.jpg"));
		// 125x125
		ImageIO.write(resizeImage(squaredImg, type, 125, 125), "jpg", new File(resultFolder + "squared125.jpg"));
		// 50x50
		ImageIO.write(resizeImage(squaredImg, type, 50, 50), "jpg", new File(resultFolder + "squared50.jpg"));

	}

	private BufferedImage cropToSquare(BufferedImage originalImg) {
		int x = originalImg.getWidth();
		int y = originalImg.getHeight();
		if (x == y)
			return originalImg;
		int diff = Math.abs(x - y);
		int cutLeft = 0, cutRight = 0, cutTop = 0, cutBottom = 0;
		if (x > y) {
			if (diff % 2 == 0)
				cutLeft = cutRight = diff / 2;
			else {
				cutLeft = cutRight = (diff - 1) / 2;
				cutLeft += 1;
			}
		} else {
			if (diff % 2 == 0)
				cutTop = cutBottom = diff / 2;
			else {
				cutTop = cutBottom = (diff - 1) / 2;
				cutTop += 1;
			}
		}
		return originalImg.getSubimage(cutLeft, cutTop, x - cutRight - cutLeft, y-cutTop-cutBottom);
	}

	private BufferedImage resizeImage(BufferedImage originalImage, int type, int newWidth, int newHeight) {
		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
		g.dispose();

		return resizedImage;
	}

}
