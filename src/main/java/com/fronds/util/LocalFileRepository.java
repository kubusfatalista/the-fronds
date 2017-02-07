package com.fronds.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LocalFileRepository implements FileRepository {
	
	private String defaultLocalStore = "C:\\Users\\Qbek\\JapoKsiazkaFtp";
	
	private static final Logger logger = Logger.getLogger(LocalFileRepository.class);
	
	@Override
	public void saveProfileImage(String path, String imageName) {
		try {
			Path originalImagePath = Paths.get(path + imageName);
			Files.copy(originalImagePath, Paths.get(defaultLocalStore + imageName), StandardCopyOption.REPLACE_EXISTING); // original one
			ImagesUtil.saveThumbnails(path + imageName, defaultLocalStore + imageName, true);
		} catch (IOException e) {
			logger.error(e);
		}
		File file = new File(path + imageName);
		if(!file.delete())
			logger.error("Nie udalo sie usunac pliku zapisanego w tempie");
	}

	@Override
	public void saveImage(String path, String imageName) {
		try {
			Files.copy(Paths.get(path + imageName), Paths.get(defaultLocalStore + imageName), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			logger.error(e);
		}
		File file = new File(path + imageName);
		if(!file.delete())
			logger.error("Nie udalo sie usunac pliku zapisanego w tempie");
	}

	@Override
	public byte[] getImage(String name) {
		Path path = Paths.get(defaultLocalStore + name);
		try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			logger.error(e);
		}
		return new byte[0];
	}

}
