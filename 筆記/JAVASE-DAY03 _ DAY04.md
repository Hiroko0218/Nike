### 1. equlas 方法在 contains 和 remove 中的作用

- 在包含（contains）和移除（remove）方法中，equals 方法的作用是用於確定一個對象是否等於另一個對象。
- 在使用 contains 方法時，它會內部調用每個元素的 equals 方法來比較目標對象與集合中的每個元素是否相等。equals 方法的實現決定了對象之間相等的條件。如果沒有對equals 方法進行自定義的重寫，默認情況下它會使用對象的引用相等性（即兩個對象引用同一內存地址）進行比較。
- 在使用 remove 方法時，它也會依賴於 equals 方法來確定要移除的對象。它會在集合中查找與給定對象相等的元素，並將其移除。如果沒有重寫 equals 方法，默認情況下它會使用對象的引用相等性來進行比較，這可能會導致無法正確刪除對象。

```java
package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Objects;

public class Demo {
		public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        //往集合中存儲 3 個對象
        students.add(new Student("張三",18));
        students.add(new Student("李四",28));
        students.add(new Student("王五",38));

        //聲明一個學生對象判斷是否包含在 students 集合中
        Student stu = new Student("張三", 18);

        /**
          * boolean contains 方法 是否包含
          * 如果包含，則返回 true
          * 如果不包含，則返回 false
          * contains 的判斷標準是通過給定的元素和集合中
          * 現有的元素逐一進行 equals 比較，
          * 只要返回為 true，則表示包含
          * 只要返回為 false，則表示不包含
          */
        //重寫 equals 之前返回 false
        boolean contains = students.contains(stu);
        System.out.println("是否包含："+contains);

        /**
          * boolean remove(E e) 移除元素
          * 從集合中刪除與指定元素進行 equals 比較為 true 的元素
          * 注意：只會刪除一個，是從集合中順序比較，刪除第一個 equals
          * 比較為 true 的元素，刪除後立即停止後面的操作
          */

        //往集合中添加張三元素
        students.add(stu);
        System.out.println(students);
        System.out.println("-------刪除後");
        students.remove(stu);
        System.out.println(students);
		}
}
class Student{
    String name;
    int age;
    public Student(String name,int age){
        this.name=name;
        this.age=age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name,student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

因此，在使用包含和移除方法 時 ，重寫equals 方法非常重要，以便根據 對 象的屬性或內容來定義對象之間 的相等性，確保正確的行為 。

### 2. 集合保存對象的引用

集合中存放的是對象的引用,並不是對象複製品

```java
package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Collection;

public class StudentDemo {
    public static void main(String[] args) {
        Collection<Student> students = new ArrayList<>();
        Student stu = new Student("迪麗熱巴", 28);
        students.add(stu);
        System.out.println("學生對象："+stu);
        System.out.println("集合："+students);

        /**
          * 重新修改對象的屬性
          * 總結：
          * 集合中存放的是對象的引用
          * 如果對對象的屬性進行修改，同樣會影響集合中對象的屬性
          * 因為指向的是同一個對象
          */
        stu.setName("楊冪");
        System.out.println("學生對象："+stu);
        System.out.println("集合："+students);
    }
}
```

### 3. 集合和數組的轉換

#### 集合轉為數組

List 的 toArray 方法用於將集合轉換為數組。但實際上該方法是在 Collection 中定義的，所以所有的集合都具備這個功能。 其有兩個方法:

```java
Object[] toArray()
<T>T[] toArray(T[] a)
```

其中第二個方法是比較常用的，我們可以傳入一個指定類型的數組，該數組的元素類型應與集合的元素類型一致。返回值則是轉換後的數組，該數組會保存集合中所有的元素。

```java
package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ArrayDemo {
    public static void main(String[] args) {
        Collection<Integer> nums =new ArrayList<Integer>();
        for (int i = 0; i <10 ; i++) {
            nums.add(i);
        }
        System.out.println("集合元素："+nums);

        /**
        	將集合轉換為數組
        	toArray(); 不常用
        */
        Object[] objects = nums.toArray();
        System.out.println("數組元素："+Arrays.toString(objects));

        /**
          * <T> T[] toArray(T[] a);
          * 可以傳入一個指定類型的數組
          * 但是該數組的元素類型應該與集合中元素類型一致 否則會拋出數組存儲類型不兼容的異常
          * 返回值則是轉換後的數組,該數組會保存集合中的所有元素
          *
          * 原理：
          * 在執行 toArray(new Integer[40])方法時，
          * 1. 會默認在底層創建該長度的數組
          * 2. 遍歷集合元素，依次存儲到數組中
          * 如果數組長度超過集合的長度,剩余元素則自動用默認值填充
          * 如果數組長度小於集合的長度,則又創建一個新數組，類型和數組相同，大小和集合相同
        	*/
        // String[] strings = nums.toArray(new String[10]); 報錯
        Integer[] i1=nums.toArray(new Integer[40]); //數組長度>集合長度默認值填充
        System.out.println(Arrays.toString(i1));
        Integer[] i2 = nums.toArray(new Integer[5]); //不會進行截取
        System.out.println(Arrays.toString(i2));
    }
}
```

#### 數組轉為集合

Arrays 類中提供了一個靜態方法 asList，使用該方法我們可以將一個數組轉換為對應的 List 集合。

返回的 List 的集合元素類型由傳入的數組的元素類型決定。

```java
static <T>List<T> asList<T… a>
```

需要注意的是，返回的集合我們不能對其增刪元素，否則會拋出異常。並且對集合的元素進行的修改會影響數組對應的元素。

```java
package cn.tedu.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        String[] arr = {"張三","李四","王五","趙六"};
        System.out.println("數組元素："+ Arrays.toString(arr));

        /**
					* 將數組轉為集合
        	* <T> List<T> asList(T... a)
        	*/
        List<String> strings = Arrays.asList(arr);
        System.out.println("集合元素："+ strings);

        /**
         	* 對集合的操作就是對源數組的操作
         	*/
      	strings.set(1, "2");
        System.out.println("修改後的 list 集合:"+ strings);
        System.out.println("修改後的 arr 數組:"+ Arrays.toString(arr));

        /**
          * 從數組轉換過來的集合是不能添加新元素的
          * 否則會拋出不受支持的操作異常
          * UnsupportedOperationException
          * 因為對集合元素操作就是對源數組操作
          * 添加元素會導致數組擴容,從而表示不了源數組
          */

        // strings.add("翠花");
        //如果想添加新元素,需要自行創建一個集合

        List<String> list2 = new ArrayList<String>(); //也可以直接在()中加入 list
        list2.addAll(strings);
        System.out.println("list2:"+list2);
        list2.add("4");
        System.out.println("修改後的 list2:"+list2);
    }
}
```

#### 4. Lambda表達式

##### **4.1** 語法特點

Lambda 表達式，也可稱為閉包，它是推動 Java 8 發布的最重要新特性。在 Java 中，Lambda 表達式是一種函數式編程的語法特性，用於簡化函數式接口的實現。Lambda 表達式可以用來創建匿名函數或簡潔地表示函數式接口的實現。

```java
(參數列表) -> {方法體}
```

- "參數列表" 可以是多個或者 0 個，參數的類型可以顯示聲明，也可以根據上下文推斷，如果沒有參數，直接寫( )

-  -> 是 Lambda 表達式的箭頭操作符，用於分隔參數列表和方法體。

- {方法體} 是 Lambda 表達式的方法體，可以是一個表達式或一段代碼塊。如果方法體只有一條表達式，可以省略花括號 {}。如果方法體是一段代碼塊，則需要使用花括號包裹，並且需要使用 return 關鍵字來返回結果。


##### **4.2** 何時使用

Lambda 表達式主要用於簡化函數式編程，當滿足以下條件時可以使用 Lambda 表達式：

- 代碼上下文要求使用函數式接口（Functional Interface），即只包含一個抽象方法的接口。

- Lambda 表達式的參數類型可以顯式聲明，也可以根據上下文進行推斷。

- Lambda 表達式的方法體可以是一個表達式或一段代碼塊。


##### **4.3 {}** 的省略原則

在 Lambda 表達式中，如果方法體只有一條表達式，可以省略花括號 {}。這種情況下，表達式的結果將作為 Lambda 表達式的返回值。

```java
Function<Integer, Integer> square = x -> x * x; // 省略了花括號
```

##### **4.4** 參數列表中 **"()"** 的省略原則：

在 Lambda 表達式中，如果參數列表為空或只有一個參數，可以省略參數列表中的圓括號 ()。但當參數列表中有多個參數時，不能省略圓括號。

```java
// 參數列表為空
Runnable runnable = () -> System.out.println("Hello Lambda!");// 參數列表只有一個參數
Consumer<String> printer = s -> System.out.println(s);

// 參數列表有多個參數
BinaryOperator<Integer> add = (a, b) -> a + b;
```

#### 5. ArrayList 和 LinkedList 的區別（需要背）

ArrayList 和 LinkedList 是 Java 集合框架中的兩個常用列表（List）實現，它們之間有以下區別：

1. 底層數據結構：ArrayList 底層使用數組實現，而 LinkedList 底層使用雙向鏈表實現。
2. 訪問效率：ArrayList 在隨機訪問元素時效率較高，因為它可以根據索引直接定位到元素的位置，而 LinkedList 在隨機訪問時需要從頭或尾開始遍歷鏈表

3. 插入和刪除效率：LinkedList 在插入和刪除元素時效率較高，因為它只需要調整鏈表中的指針，而 ArrayList 在插入和刪除元素時需要進行數據的移動和覆制。

4. 內存占用：LinkedList 在每個元素中存儲了額外的鏈表指針，因此在存儲大量元素時會占用較多的內存。ArrayList 則直接使用數組存儲元素，不需要額外的指針，因此內存占用相對較少。

5. 叠代性能：LinkedList 在叠代操作（如使用叠代器或 for-each 循環）時性能較差，因為每次叠代都需要從頭或尾開始遍歷鏈表。ArrayList 則可以通過索引快速定位元素，叠代性能較好。

綜上所述，ArrayList 適用於隨機訪問和讀取操作較多的場景，而 LinkedList 適用於頻繁的插入、刪除的場景。根據具體的需求，可以選擇適合的列表實現。
