import java.util.Timer;
import java.util.TimerTask;

public class Control {

    public void controlUpdate() {

    }

    private final Timer timer = new Timer();

    public void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                controlUpdate();
            }
        }, 0, ControlConstants.TimeContstants.MILLISECONDS_PER_FRAME); 
    }

    public void stop() {
        timer.cancel();
    }
}