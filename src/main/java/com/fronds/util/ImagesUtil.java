package com.fronds.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ImagesUtil {

	private ImagesUtil() {
	}
	
	public static void writeImageToResponse(byte[] img, HttpServletResponse response, Logger logger) {
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		try {
			response.getOutputStream().write(img);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public static void saveThumbnails(String originalPath, String resultPath, boolean profile) throws IOException {

		BufferedImage originalImg = ImageIO.read(new File(originalPath));
		int type = originalImg.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImg.getType();
		BufferedImage squaredImg = cropToSquare(originalImg);

		// 200x200
		ImageIO.write(resizeImage(squaredImg, type, 200, 200), "jpg", new File(resultPath + "m"));
		if (profile) {
			// 125x125
			ImageIO.write(resizeImage(squaredImg, type, 125, 125), "jpg", new File(resultPath + "s"));
			// 50x50
			ImageIO.write(resizeImage(squaredImg, type, 50, 50), "jpg", new File(resultPath + "xs"));
		}

	}

	private static BufferedImage cropToSquare(BufferedImage originalImg) {
		int x = originalImg.getWidth();
		int y = originalImg.getHeight();
		if (x == y)
			return originalImg;
		int diff = Math.abs(x - y);
		int cutLeft = 0;
		int cutRight = 0;
		int cutTop = 0;
		int cutBottom = 0;
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
		return originalImg.getSubimage(cutLeft, cutTop, x - cutRight - cutLeft, y - cutTop - cutBottom);
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int type, int newWidth, int newHeight) {
		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
		g.dispose();
		return resizedImage;
	}
}
