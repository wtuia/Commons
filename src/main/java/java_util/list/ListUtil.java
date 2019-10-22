package java_util.list;

import java.util.List;

/**
 * list 工具类
 */
public class ListUtil {

    /**
     * list原生的isEmpty不支持对null的判断
     *
     *  public boolean isEmpty() {
     *         return size == 0;
     *  }
     */
    public boolean isEmpty(List<?> list) {
        if (list == null) {
            return true;
        }
        return list.isEmpty();
    }

}
