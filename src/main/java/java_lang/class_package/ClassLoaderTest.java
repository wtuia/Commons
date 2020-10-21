package java_lang.class_package;

import java.lang.reflect.Method;
import java.util.Scanner;

public class ClassLoaderTest {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)){
			while (!sc.nextLine().equals("break")) {
				Class<?> clz = Class.forName("C:\\Users\\14244\\Desktop\\a.java");
			}


		}catch (Exception e ){

		}
	}
}
