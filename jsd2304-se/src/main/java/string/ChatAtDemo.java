package string;

public class ChatAtDemo {
    public static void main(String[] args) {
        //            0123456789012345
        String line ="thinking in java";
        char c = line.charAt(7);
        System.out.println(c);//g
        c= line.charAt(3);
        System.out.println(c);//n

        String str = "上海自來水來自海上";
        for (int i = 0; i<str.length()/2;i++){
            char st = str.charAt(i);
            char ed = str.charAt(str.length()-1-i);
//            if (st == ed){
//                System.out.println("是回文!");
//                break;
//            }
//            System.out.println("不是回文!");

            String r1 = st == ed ? "是回文" : "不是回文";
            System.out.println(r1);//Y
            break;



            //1  8 = 9-1-0
            //2  7 = 9-1-1
            //3  6 = 9-1-2
            //4  5 = 9-1-3

        }
    }
}
