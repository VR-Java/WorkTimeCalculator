package engine;

import java.io.IOException;
import java.nio.file.*;

public abstract class CopyFile {

	private static final String ORIGINAL_RECORDING_SHEET_PATH = "D:\\Java\\Projects\\WorkTime\\files\\";
	private static final String ORIGINAL_RECORDING_SHEET_NAME = "RecordingSheet-COPY.xls";

	public static void copy(String newPath, String newName) {
		FileSystem system = FileSystems.getDefault();
		Path original = system.getPath(ORIGINAL_RECORDING_SHEET_PATH + ORIGINAL_RECORDING_SHEET_NAME);
		Path target = system.getPath(newPath + newName);

		try {
			// Throws an exception if the original file is not found.
			Files.copy(original, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			System.out.println("ERROR");
		}
	}

}
