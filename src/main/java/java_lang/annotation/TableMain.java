package java_lang.annotation;

import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;
import java.util.Arrays;

public class TableMain {

	public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
		TableTest tableTest = new TableTest();
		tableTest.test();
	}


	public static void init() throws NoSuchMethodException, ClassNotFoundException {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
		Table table1 = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).
				getMethod(Thread.currentThread().getStackTrace()[2].getMethodName()).getAnnotation(Table.class);
		System.out.println(Arrays.toString(table1.arg()));
		System.out.println(table1.value());
	}


}