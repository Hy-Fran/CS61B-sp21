package deque;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> constructComparator;

    public MaxArrayDeque(Comparator<T> c){
        constructComparator = c;
    }

    /**
     * 返回由先前给定的 `Comparator` 管理的双端队列中的最大元素。如果 `MaxArrayDeque` 为空，则返回 `null`。
     * @return 最大元素
     */
    public T max(){
        return max(constructComparator);
    }

    /**
     * 返回由参数 `Comparator c` 管理的双端队列中的最大元素。如果 `MaxArrayDeque` 为空，则返回 `null`。
     * @param c - 比较器
     * @return 比较器决定的最大元素
     */
    public T max(Comparator<T> c){
        T max = get(0);
        for (int i = 1; i < size(); i++){
            T item = get(i);
            if (c.compareTo(item, max) > 0) {
                max = item;
            }
        }
        return max;
    }
}
