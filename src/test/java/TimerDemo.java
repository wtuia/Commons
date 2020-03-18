import java.time.LocalDateTime;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {

            TimerInClass timerInClass = new TimerInClass();
            @Override
            public void run() {
                System.out.println(LocalDateTime.now());
                timerInClass.setNumber(timerInClass.getNumber() + 1);
                System.out.println(timerInClass.getNumber());
            }
        }, 1 * 60 * 1000, 1 * 60 * 1000);
    }
}
