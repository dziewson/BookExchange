package com.p.lodz.pl.bookexchange.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import  java.util.Base64;

public class ImageUtils {
	private static Logger logger = LoggerFactory.getLogger(ImageUtils.class);

	private ImageUtils() {

	}

	public static byte[] extractBytes(String ImageName) throws IOException {

		File imgPath = new File(ImageName);
		BufferedImage bufferedImage = ImageIO.read(imgPath);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", baos);
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
			BufferedImage resizedImage = new BufferedImage(128, 128, img.getType());
			if (img.getWidth() > 128 && img.getHeight() > 128) {
				Graphics2D g = resizedImage.createGraphics();
				g.drawImage(img, 0, 0, 128, 128, null);
				g.dispose();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(resizedImage, "jpg", baos);
				return baos.toByteArray();
			} else {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(img, "jpg", baos);
				return baos.toByteArray();
			}
		} catch (IOException e) {
			logger.error(e + "");
		}
		return new byte[0];
	}

}
