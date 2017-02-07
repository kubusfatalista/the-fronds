package com.fronds.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by Qbek on 2016-12-15.
 */
@Component
public class FtpFileRepository implements FileRepository {
	
	private static final Logger logger = Logger.getLogger(FtpFileRepository.class);

	public void saveImage(String path, String imageName) {
		FTPClient client = new FTPClient();

		try (FileInputStream fis = new FileInputStream(path + imageName)) {
			client.connect("localhost", 21);

			// After connection attempt, we have to check the reply code to
			// verify success.
			int reply = client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				logger.error("FTP server refused connection.");
			}

			client.login("test", "test");
			client.setFileType(FTP.BINARY_FILE_TYPE);
			BufferedInputStream bis = new BufferedInputStream(fis);
			if(!client.storeFile(imageName, bis))
				logger.error("Nie udalo sie zapisac pliku na serwerze ftp");
			bis.close();
			client.logout();
			File file = new File(path + imageName);
			if(!file.delete())
				logger.error("Nie udalo sie usunac pliku zapisanego w tempie");
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (client.isConnected()) {
				try {
					client.disconnect();
				} catch (IOException ioe) {
					logger.error(ioe);
					logger.error("Cannot disconnect from the host");
				}
			}

		}
	}

	public byte[] getImage(String name) {

		FTPClient client = new FTPClient();

		try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
			client.connect("localhost", 21);

			// After connection attempt, we have to check the reply code to
			// verify success.
			int reply = client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				logger.error("FTP server refused connection.");
			}

			client.login("test", "test");
			client.setFileType(FTP.BINARY_FILE_TYPE);

			InputStream in = client.retrieveFileStream(name);

			int nRead;
			byte[] data = new byte[1024];
			while ((nRead = in.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}

			buffer.flush();
			client.logout();
			return buffer.toByteArray();
		} catch (IOException e) {
			logger.error(e);
			return new byte[0];
		} finally {
			if (client.isConnected()) {
				try {
					client.disconnect();
				} catch (IOException ioe) {
					logger.error(ioe);
					logger.error("Cannot disconnect from the host");
				}
			}

		}

	}

	@Override
	public void saveProfileImage(String path, String imageName) {
		try {
			throw new UnsupportedOperationException("not implemented yet");
		} catch (Exception e) {
			logger.error(e);
		}
	}

}
