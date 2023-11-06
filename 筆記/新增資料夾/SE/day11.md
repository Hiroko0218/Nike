# day11

### Map 查找表

Map體現的結構是一個多行兩列的表格,其中左列稱為key,右列稱為value.

- Map總是成對保存數據,並且總是根據key獲取對應的value.因此我們可以將查詢的條件作為key查詢對應的結果作為value保存到Map中.
- Map有一個要求:key不允許重覆(equals比較的結果)

java.util.Map接口,是所有Map的頂級接口,規定了Map的相關功能.

常用實現類:

- java.util.HashMap:稱為散列表,使用散列算法實現的Map,當今查詢速度最快的數據結構.
- java.util.TreeMap:使用二叉樹實現的Map

```java
package map;

import java.util.HashMap;
import java.util.Map;

/**
 * java.util.Map接口  查找表
 * Map體現的結構像是一個多行兩列的表格，其中左列稱為key，右列稱為value
 * Map總是成對兒(key-value鍵值對)保存數據，並且總是根據key獲取其對應的value
 *
 * 常用實現類:
 * java.util.HashMap:稱為散列表，使用散列算法實現的Map，當今查詢速度最快的
 *                   數據結構。
 */
public class MapDemo {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        /*
            V put(K k,V v)
            將給定的鍵值對兒存入Map
            Map有一個要求，即：Key不允許重覆(Key的equals比較)
            因此如果使用重覆的key存入value，則是替換value操作，此時put方法
            的返回值就是被替換的value。否則返回值為null。
         */
        /*
            注意，如果value的類型是包裝類，切記不要用基本類型接收返回值，
            避免因為自動拆箱導致的空指針
         */
        Integer value = map.put("語文",99);
        System.out.println(value);//null
        map.put("數學",98);
        map.put("英語",97);
        map.put("物理",96);
        map.put("化學",98);
        System.out.println(map);

        value = map.put("物理",66);
        System.out.println(value);//96,物理被替換的值
        System.out.println(map);


        /*
            V get(Object key)
            根據給定的key獲取對應的value。若給定的key不存在則返回值為null
         */
        value = map.get("語文");
        System.out.println("語文:"+value);

        value = map.get("體育");
        System.out.println("體育:"+value);//null

        int size = map.size();
        System.out.println("size:"+size);
        /*
            V remove(Object key)
            刪除給定的key所對應的鍵值對，返回值為這個key對應的value
         */
        value = map.remove("語文");
        System.out.println(map);
        System.out.println(value);

        /*
            boolean containsKey(Object key)
            判斷當前Map是否包含給定的key
            boolean containsValue(Object value)
            判斷當前Map是否包含給定的value
         */
        boolean ck = map.containsKey("英語");
        System.out.println("包含key:"+ck);
        boolean cv = map.containsValue(97);
        System.out.println("包含value:"+cv);
    }
}
```



### Map的遍歷

Map支持三種遍歷方式:

- 遍歷所有的key
- 遍歷所有的鍵值對
- 遍歷所有的value(相對不常用)

```java
package map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Map的遍歷
 * Map提供了三種遍歷方式
 * 1:遍歷所有的key
 * 2:遍歷每一組鍵值對
 * 3:遍歷所有的value(不常用)
 */
public class MapDemo2 {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("語文",99);
        map.put("數學",98);
        map.put("英語",97);
        map.put("物理",96);
        map.put("化學",98);
        System.out.println(map);
        /*
            遍歷所有的key
            Set keySet()
            將當前Map中所有的key以一個Set集合形式返回。遍歷該集合就等同於
            遍歷了所有的key
         */
        Set<String> keySet = map.keySet();
        for(String key : keySet){
            System.out.println("key:"+key);
        }

        /*
            遍歷每一組鍵值對
            Set<Entry> entrySet()
            將當前Map中每一組鍵值對以一個Entry實例形式存入Set集合後返回。
            java.util.Map.Entry
            Entry的每一個實例用於表示Map中的一組鍵值對，其中有兩個常用方法:
            getKey()和getValue()
         */
        Set<Map.Entry<String,Integer>> entrySet = map.entrySet();
        for(Map.Entry<String,Integer> e : entrySet){
            String key = e.getKey();
            Integer value = e.getValue();
            System.out.println(key+":"+value);
        }
        /*
            JDK8之後集合框架支持了使用lambda表達式遍歷。因此Map和Collection都
            提供了foreach方法，通過lambda表達式遍歷元素。
         */
        map.forEach(
             (k,v)-> System.out.println(k+":"+v)
        );


        /*
            遍歷所有的value
            Collection values()
            將當前Map中所有的value以一個集合形式返回
         */
        Collection<Integer> values = map.values();
//        for(Integer i : values){
//            System.out.println("value:"+i);
//        }
        /*
            集合在使用foreach遍歷時並不要求過程中不能通過集合的方法增刪元素。
            而之前的叠代器則有此要求，否則可能在遍歷過程中拋出異常。
         */
        values.forEach(
                i -> System.out.println("value:"+i)
        );
    }
}

```



## 總結

### java.util.Map 查找表

特點:	體現的結構是一個多行兩列的表格，其中左列稱為key，右列稱為value。

Map中的key不允許重覆。判定重覆的標準是根據key的equals方法判定的。

常用的實現類:	java.util.HashMap 散列表

### 常用方法:

V put(K k,V v):	向Map中添加一組鍵值對,使用重覆的key存入新的value時，那麽就是替換value操作。此時put方							法，返回值為被替換的value。否則返回值為null。

V get(K k):	根據給定的key獲取對應的value。如果給定的key不存在則返回值為null

V remove(K k):	根據給定key從Map中刪除對應的鍵值對，返回值為該key對應的value。

int size():	返回當前Map中的元素個數

void clear():	清空Map

boolean containsKey(Object key):	判斷當前的Map是否包含給定的key

boolean containsValue(Object value):	判斷當前Map是否包含給定的value

Set keySet():	遍歷key使用的方法，將當前Map中所有的key以一個Set集合形式返回

Set entrySet():	遍歷每一組鍵值對的方法，將當前Map中每一組鍵值對(Entry實例)以一個Set集合形式返回

Collection values():	遍歷所有value使用的方法，將當前Map中所有的value以一個集合形式返回

forEach():	基於lambda表達式遍歷Map