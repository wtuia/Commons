package since.composition_forward;

import java.util.Set;

/**
 * 包装类
 * 从本质上讲，这个类把一个 Set 转变成了另 一个 Set ，同时增加了计数的功能 。
 * @param <E>
 */
public class InstrumentSet<E> extends ForwardingSet<E> {

    private int addCount = 0;

    public InstrumentSet(Set<E> set) {
        super(set);
    }
}
