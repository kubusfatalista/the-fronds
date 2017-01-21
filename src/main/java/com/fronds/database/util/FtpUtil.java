package com.fronds.database.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;

/**
 * Created by Qbek on 2016-12-15.
 */
public class FtpUtil {

	public static void saveImage(String path, String imageName) {
		FTPClient client = new FTPClient();

		try {
			client.connect("localhost", 21);

			// After connection attempt, we have to check the reply code to
			// verify success.
			int reply = client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				System.err.println("FTP server refused connection.");
			}

			client.login("test", "test");
			client.setFileType(FTP.BINARY_FILE_TYPE);
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path + imageName));
			boolean result = client.storeFile(imageName, bis);
			bis.close();
			client.logout();
			File file = new File(path + imageName);
			file.delete();
		} catch (SocketException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (client.isConnected()) {
				try {
					client.disconnect();
				} catch (IOException ioe) {
					// swallow exception
					System.err.println("Cannot disconnect from the host");
				}
			}

		}
	}

	public static byte[] getImage(String path, String name) {

		FTPClient client = new FTPClient();

		try {
			client.connect("localhost", 21);

			// After connection attempt, we have to check the reply code to
			// verify success.
			int reply = client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				System.err.println("FTP server refused connection.");
			}

			client.login("test", "test");
			client.setFileType(FTP.BINARY_FILE_TYPE);

			InputStream in = client.retrieveFileStream(name);

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int nRead;
			byte[] data = new byte[1024];
			while ((nRead = in.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}

			buffer.flush();
			client.logout();
			return buffer.toByteArray();
		} catch (SocketException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (client.isConnected()) {
				try {
					client.disconnect();
				} catch (IOException ioe) {
					// swallow exception
					System.err.println("Cannot disconnect from the host");
				}
			}

		}

	}

}
