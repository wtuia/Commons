package java_lang.annotation;

@Table("123")
public class TableTest {

	@Table(value = "123", arg = {"1", "2"})
	public void test() throws NoSuchMethodException, ClassNotFoundException {
		TableMain.init();
	}
}
