package map;

import java.util.HashMap;
import java.util.Map;

/**
 * java.util.Map 查找表
 * Map是java集合框架的一員 ，但 Map和 Collection不存在繼承關係！！！
 *
 * Map體現的結構是一個多行兩列的表格，左列稱為key，右列稱為value
 * Map總是以 key-value對的形式保存數據
 * Map總是數據 key獲取對應的value
 * Map要求key不允許重複(重複的依據是equals方法)
 *
 * Map是一個接口，下面有常用的現實類:
 * java.util.HashMap: 散列表，哈希表。是使用散列算法實現的Map
 * java.util.TreeMap: 使用二叉樹算法實現的Map
 */
public class MapDemo {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        /*
            V put(K k,V v)
            將一组鍵值對保存到Map中
            該方法返回值為Value的類型:
            由於Map要求key不允許重複，如果使用已有的key存放鍵值對時，為替換value操作
            此時方法會將被替換的value返回。否則返回值為null。
         */
        Integer value = map.put("語文",99);//如果key不重複時返回值為null
        System.out.println(value);
        map.put("數學",98);
        map.put("英語",97);
        map.put("物理",96);
        map.put("化學",99);
        System.out.println(map);

        value = map.put("英語",77);//已有的key則是替換value
        System.out.println("value:"+value);//97
        System.out.println(map);

        /*
            V get(K k)
            根據给定的key獲取對應的value
            如果给定的key在Map中不存在，則返回值為null
         */
        value = map.get("數學");
        System.out.println("數學:"+value);
        value = map.get("體育");
        System.out.println("體育:"+value);

        /*
            V remove(Object key)
            根據给定的key將Map中對應的鍵值對删除。返回值為這個key對應的value
         */
        value = map.remove("數學");
        System.out.println("被删除的key對應的value是:"+value);
        System.out.println(map);

        //獲取Map的元素個數
        int size = map.size();
        System.out.println("size:"+size);
        //判斷Map是否包含指定的key或value
        boolean ck = map.containsKey("化學");
        boolean cv = map.containsValue(94);
        System.out.println("是否包含key："+ck);
        System.out.println("是否包含value："+cv);
        //清空Map
        map.clear();
        System.out.println(map);
    }
}
