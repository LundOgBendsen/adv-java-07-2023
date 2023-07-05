package dk.logb.qr.generator;

import net.glxn.qrgen.javase.QRCode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

@SpringBootApplication
public class GeneratorApplication {

	public static void main(String[] args) throws Exception {
		//BufferedImage qrcode = generateQRCodeImage("Hello world!");
		//write qrcode to file
		//ImageIO.write(qrcode, "PNG", new File("qrcode.png"));

		SpringApplication.run(GeneratorApplication.class, args);
	}


	public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
		ByteArrayOutputStream stream = QRCode
				.from(barcodeText)
				.withSize(250, 250)
				.stream();
		ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

		return ImageIO.read(bis);
	}
}
