package string;

public class MatchDemo {
    /**
     *  正則表達式
     *  正則表達式用於描述一個字符串的格式
     *  正則表達式主要任務: 驗證一個實際的字符串内容是否符合格式要求
     *  比如:手機號碼的驗證，Email格式的驗證
     *
     *  正則表達式基本語法
     *  1:
     *  []:用来表示1個字符。[]中可以指定該字符的内容範圍
     *  例如:
     *  [abc]:該字符要麼是a要麼是b要麼是c
     *  [a-z]:"-"表示範圍，a-z:表示abcdefg.....xyz  任意小寫字母
     *  [a-zA-Z]:任意一個字母
     *  [0-9]:表示任意一個數字
     *
     *  2:預定義字符
     *  \d:表示一個數字，等價與[0-9]
     *  \s:表示一個空白字符。空格，縮進，Enter(占位但是看不見的字符)
     *  \w:表示一個單詞字符(數字，字符，下劃線) 等價於[a-zA-Z0-9_]
     *  .:表示任意一個字符
     *
     *
     *  取反:
     *  \D:表示不是數字
     *  \S:表示不是空白字符
     *  \W:表示不是單詞字符
     *
     *  量詞:
     *  +:表示它前面的内容出現(1~多次，即:一次以上)
     *  *:表示它前面的内容出现任意次(0~多次)
     *  {n}:表示它前面的内容出現n次。n是一個數字
     *  {n,m}:表示它前面的内容出現n-m次。(>=n&&<=m)  n和m是一個數字
     *  {n,}:忽略m則表示n次以上(>=n)
     *  {0,m}:最多m次
     *
     *
     *  例如:
     *  [abc]+
     *  它可以匹配:
     *  "a"  可以
     *  "abcbabcbacbabcbacbabc" 可以的，字符是a或b或c，次數1次以上
     *  "" 不行！至少要寫1個字符
     *  "abcd" 不行!因为其中一個字符出現了"d"
     *
     *
     *  [abc]*
     *  它可以匹配:
     *  "a"  可以
     *  "abcbabcbacbabcbacbabc" 可以的，字符是a或b或c
     *  "" 可以
     *  "abcd" 不行!因为其中一個字符出現了"d"
     *
     *
     *  [abc]{3}
     *  它可以匹配:
     *  "aaa" 可以
     *  "abc" 可以
     *  "aaba"或"ab" 不可以，因为次數要求只能3次
     *  "abd" 不可以，因為其中一個字符是"d"
     *
     *
     *  [abc]{3,5}
     *  "aaa" 可以
     *  "abcbc" 可以
     *  "aa"或 "abcbcb" 不可以，次數只能在3-5次之間
     *
     *
     *  分组:
     *  ():可以將其内容看作一個整體
     *  (X|Y):"|"表達"或"的意思 這個整體可以是X也可以是Y
     *
     *  例如:
     *  (abc){3}
     *  abcabcabc 可以:abc這個整體要出現3次
     *  abcabc 不行，因此次數不夠
     *  abcabcdef 不行，次數夠，但有一次的内容不是abc
     *
     *  (abc|def){3}
     *  abcabcabc:可以
     *  abcdefabc:可以
     *
     *
     *  邊界匹配:
     *  ^:^出現在字符串最開始，表示字符串從這裡開始
     *  $:$表示字符串到這裡結束
     *
     *  例如:
     *  [abc]{3}
     *  aaaaaaaaaa 不行  實際正則表達式匹配時是通過的，因為字符串只要部分内容符合即可
     *
     *  ^[abc]{3}
     *  aaadnmsduhj 可以 字符串最開始前三個字符符合正則表達式要求
     *
     *  [abc]{3}$
     *  ahjsdjhaaa 可以 字符串最後的三個字符符合正則表達式要求
     *
     *  ^[abc]{3}$
     *  abc 可以 正則要求字符串從頭到尾只有3個字符
     *  abcsdasdabc 不可以的，超過3個字符
     *
     *  ^[abc]{3}.*[abc]{3}$
     *  abcsdasdabc 可以
     *
     *  String提供了使用正则表达式的相关功能。
     *
     *  Email
     *  fan_cq123@tedu.com
     *  用戶名@域名.com
     *
     *  用戶名:可以是數字，字母，_。至少寫一個字符
     *  域名:字母，數字。至少一個字符
     *  .com(後綴):.a(字母)的组合。該组合要出現一次以上
     *
     *  用户名@域名.co
     *  [a-zA-Z0-9_]+@[a-zA-Z0-9]+(\.[a-zA-Z]+)+
     */

    public static void main(String[] args) {
        String email = "fancq@tedu.com.cn";
        String regex = "[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\\.[a-zA-Z]+)+";
        /*
            boolean matches(String regex)
            使用给定的正則表達式regex來驗證當前字符串是否符合格式要求
            满足格式方法返回true否則返回false。
            注意:正則表達式不加邊界匹配符"^"和"$"也是完全匹配的效果
         */
        boolean match = email.matches(regex);
        if (match) {
            System.out.println("是Email");
        } else {
            System.out.println("不是Email");
        }
    }
}
