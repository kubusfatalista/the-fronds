package com.fronds.util;

public interface FileRepository {
	public void saveImage(String path, String imageName);
	public void saveProfileImage(String path, String imageName);
	public byte[] getImage(String name);
}
