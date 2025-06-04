package assessment.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class FileLineStreamIO implements AutoCloseable {

  private BufferedReader reader;
  private BufferedWriter writer;

  public BufferedReader getReader(String filePath) throws IOException {
    if (reader == null || reader.ready()) {
      reader = Files.newBufferedReader(Paths.get(filePath));
    }
    return reader;
  }

  public BufferedWriter getWriter(String filePath) throws IOException {
    if (writer == null) {
      writer = Files.newBufferedWriter(Paths.get(filePath));
    }
    return writer;
  }

  public Optional<String> readLine(String filePath) throws IOException {
    BufferedReader r = getReader(filePath);
    String line = r.readLine();
    return Optional.ofNullable(line);
  }

  public void writeLine(String filePath, String line) throws IOException {
    BufferedWriter w = getWriter(filePath);
    w.write(line);
    w.newLine();
  }

  @Override
  public void close() throws IOException {
    if (reader != null) {
      reader.close();
      reader = null;
    }
    if (writer != null) {
      writer.close();
      writer = null;
    }
  }

  public void cleanup() throws IOException {
    close();
  }

  public boolean hasOpenStreams() {
    return reader != null || writer != null;
  }
}
