package action;

/**
 * Created by lyn on 16-5-2.
 */
public interface InfoAction {
    String showInfo(String username);
    String dataByTime(String begin,String end);
    String showStatic(String username,String begin,String end,String bookType);

}
