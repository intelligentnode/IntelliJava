package com.intellijava.core.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * 
 * AudioHelper is a class to process and test the generated audio from speech synthesis models.
 * 
 * It is recommended to play the generated audio using a suitable java third-party audio library
 * and use this class only to decode the base64 model output.
 * 
 * @author github.com/Barqawiz
 */
public class AudioHelper {

	private static String fileTempAudio = "temp/audio.mp3";
	
	public static boolean isLog = true;
	
	public static byte[] decode(String audioContent) {
		return Base64.getDecoder().decode(audioContent);
	}
	
	public static boolean updateGlobalTempLocation(String fileTempAudio) {
		boolean res = false;
		if (fileTempAudio.endsWith(".mp3") || fileTempAudio.endsWith(".wav")) {
			AudioHelper.fileTempAudio = fileTempAudio;
			res = true;
		} else if (isLog){
			System.out.print("Unsupported audio format, send mp3 or wav");
		}
		
		return res;
		
	}

	public static boolean saveTempAudio(byte[] decodedAudio) {
		boolean res = true;
		try (FileOutputStream fos = new FileOutputStream(fileTempAudio)) {
			fos.write(decodedAudio);
		} catch (IOException e) {
			res = false;
			if (isLog) e.printStackTrace();
		}
		return res;
	}

	public static void deleteTempAudio() {
		
		File file = new File(fileTempAudio);
		if (file.exists()) { 
			file.delete();
		}
		
	}
}
