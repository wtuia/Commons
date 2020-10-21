package java_lang.class_package;

import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

public class CompileClassLoader extends ClassLoader{
	private static final Logger logger = LoggerFactory.getLogger(CompileClassLoader.class);
	private File file;

	// 读取class文件的二进制内容
	private byte[] getBytes(String fileName) {
		File file = new File(fileName);
		long len = file.length();
		byte[] raw = new byte[(int) len];
		try (FileInputStream fis = new FileInputStream(file)){
			int r = fis.read(raw);
			if (r != len) {
				throw new IOException("无法读取全部文件");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return raw;
	}
	
	private boolean compile(String javaFile) {
		logger.error("正在编译:{}", javaFile);
		int ret = -1;
		try {
			Process p = Runtime.getRuntime().exec("javac " + javaFile);
			p.waitFor();
			// javac的线程退出值
			ret = p.exitValue();
		} catch (IOException e) {
			logger.error("",e);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 是否编译成功
		return ret == 0;

	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> clz = null;
		String fileStub = name.replace(".", "/");
		String javaFileName = fileStub + ".java";
		String classFileName = fileStub + ".class";
		File javaFile = new File(javaFileName);
		File classFile = new File(classFileName);
		if (javaFile.exists() &&
				(!classFile.exists() || javaFile.lastModified() > classFile.lastModified())) {
			if (!compile(javaFileName) || !classFile.exists()) {
				throw new ClassNotFoundException("ClassNotFoundException:" + javaFileName);
			}
		}
		if (classFile.exists()) {
			byte[] raw = getBytes(classFileName);
			try {
				clz = Objects.requireNonNull(defineClass(name, raw, 0, raw.length));
			}catch (Exception e) {
				throw new ClassNotFoundException(name);
			}
		}
		return clz;
	}

	public static void main(String[] args) throws Exception{
		Configurator.initialize("log4j2.xml", System.getProperty("user.dir") + File.separator + "log4j2.xml");
		CompileClassLoader classLoader = new CompileClassLoader();
		Class<?> clz = classLoader.loadClass("Test.Test");
		Method method = clz.getMethod("print");
		method.invoke(null);
	}
}
