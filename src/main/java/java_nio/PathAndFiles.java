package java_nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class PathAndFiles {

	public static void main(String[] args) {

	}

	@Test
	public void readFile() throws IOException {
		Path path = Paths.get("temp", "test.txt");
		System.out.println(Files.readAllLines(path, StandardCharsets.UTF_8));
		System.out.println(new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
	}

	@Test
	public void saveData2File() throws IOException {
		Path path = Paths.get("temp", "test1.txt");
		List<String> s = new ArrayList<>();
		s.add("c");
		s.add("b");
		// StandardOpenOption.APPEND 追加模式
		Files.write(path, s, StandardCharsets.UTF_8, StandardOpenOption.APPEND);

	}
}
