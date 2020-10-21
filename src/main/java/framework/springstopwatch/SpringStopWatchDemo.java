package framework.springstopwatch;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

public class SpringStopWatchDemo {

	public static void main(String[] args) {
		StopWatch watch = new StopWatch();
		watch.start();
		addEle();
		watch.stop();
		System.out.println(watch.prettyPrint());
	}

	public static void addEle() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			list.add(i);
		}
	}
}
