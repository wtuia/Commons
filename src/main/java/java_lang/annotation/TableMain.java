package java_lang.annotation;

public class TableMain {

	public static void main(String[] args) {
		TableTest tableTest = new TableTest();
		Table table = tableTest.getClass().getAnnotation(Table.class);
		System.out.println(table.value());
	}
}