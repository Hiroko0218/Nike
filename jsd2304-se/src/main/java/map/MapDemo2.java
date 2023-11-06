package map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Map的遍歷
 * Map支持三種遍歷方式
 * 1:遍歷所有的key
 * 2:遍歷每一组鍵值對
 * 3:遍歷所有的value(不常用)
 */
public class MapDemo2 {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("語文",99);
        map.put("數學",98);
        map.put("英文",97);
        map.put("物理",96);
        map.put("化學",99);
        System.out.println(map);

        /*
            遍歷所有的key
            Set keySet()
            將當前Map中所有的key以一个Set集合形式返回
         */
        Set<String> keySet = map.keySet();
        for(String key : keySet){
            System.out.println("key:"+key);
        }

        /*
            遍歷每一組鍵值對
            Set entrySet()
            將當前Map中每一组鍵值對以一個Entry實例形式表示，再將它們以Set集合形式
            返回。
            Map.Entry的每一個實例表示Map中的一组鍵值對，有兩個重要的方法
            K getKey(): 獲取對應的key
            V getValue(): 獲取對應的value
         */
        Set<Map.Entry<String,Integer>> entrySet = map.entrySet();
        for(Map.Entry<String,Integer> e: entrySet){
            String key = e.getKey();
            Integer value = e.getValue();
            System.out.println(key+":"+value);
        }

        /*
            遍歷所有的value
            Collection values()
            該方法會將Map中所有的value以一個集合形式返回
         */
        Collection<Integer> values = map.values();
        for(Integer value : values){
            System.out.println("value:"+value);
        }

        /*
            JDK8之後Map也支持forEach方法基於lambda表達式遍歷
         */
        map.forEach((k,v)->System.out.println(k+":"+v));
    }
}
