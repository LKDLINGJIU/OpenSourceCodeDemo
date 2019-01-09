package android.yc.eventbuslib;

import java.util.List;

/**
 * Created by lingjiu on 2019/1/9.
 */
public class Utils {


    public static <T extends Comparable> void addAndSort(List<T> list, T t) {
        if (list.size() == 0) {
            list.add(t);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).compareTo(t) <= 0) {
                list.add(i, t);
                return;
            }
        }
        list.add(t);
    }

}
