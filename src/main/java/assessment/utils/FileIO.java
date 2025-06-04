package assessment.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileIO {

  private FileIO() {
  }

  public static String readFromFile(String filePath) throws IOException {
    return Files.readString(Path.of(filePath));
  }

  public static void writeToFile(String content, String filePath) throws IOException {
    Files.writeString(Path.of(filePath), content);
  }
}
