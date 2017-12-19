package com.p.lodz.pl.bookexchange.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtils {
	private static Logger logger = LoggerFactory.getLogger(ImageUtils.class);

	private ImageUtils() {

	}

	public static byte[] extractBytes(String ImageName) throws IOException {

		File imgPath = new File(ImageName);
		BufferedImage bufferedImage = ImageIO.read(imgPath);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", baos);
		return baos.toByteArray();
	}

	public static String getDecodedImage(byte[] img) {

		String encodedImage = "";
		if (img != null && img.length > 0) {
			encodedImage = new String(Base64.getEncoder().encode(img));
			encodedImage = "data:image/png;base64," + encodedImage;
		}
		return encodedImage;
	}

	public static byte[] resizeImage(byte[] image) {

		try {
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(image));
			if (img.getWidth() > 128 || img.getHeight() > 128) {
				img = scaleImage(img, img.getType(), 128, 128);

			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, "png", baos);
			return baos.toByteArray();

		} catch (IOException e) {
			logger.error(e + "");
		}
		return new byte[0];
	}

	public static BufferedImage scaleImage(BufferedImage image, int imageType, int newWidth, int newHeight) {
		double thumbRatio = (double) newWidth / (double) newHeight;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		double aspectRatio = (double) imageWidth / (double) imageHeight;

		if (thumbRatio < aspectRatio) {
			newHeight = (int) (newWidth / aspectRatio);
		} else {
			newWidth = (int) (newHeight * aspectRatio);
		}

		// Draw the scaled image
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, imageType);
		Graphics2D graphics2D = newImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, newWidth, newHeight, null);

		return newImage;
	}
}
