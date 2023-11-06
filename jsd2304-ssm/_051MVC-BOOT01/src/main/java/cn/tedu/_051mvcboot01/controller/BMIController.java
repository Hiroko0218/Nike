package cn.tedu._051mvcboot01.controller;

import cn.tedu._051mvcboot01.pojo.BMI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BMIController {
//    @RequestMapping("/bmi")
//    @ResponseBody
//    public String bmi(Double height, Double weight) {
//        //soutp + Enter
//        System.out.println("height = " + height + ", weight = " + weight);
//        //計算
//        Double bmi = weight / (height * height);
//        //判斷
//        if (bmi < 18.5) {
//            return "偏瘦";
//        }
//        if (bmi < 24) {
//            return "正常";
//        }
//        if (bmi < 27) {
//            return "微胖";
//        }
//        return "該減肥了";
//    }
//}


    @RequestMapping("/bmi")
    @ResponseBody
    public String BMI(BMI bmi) {
        Double height = bmi.getHeight();
        Double weight = bmi.getWeight();
        Double bmi1 = weight / (height * height);

        if (bmi1 < 18.5) {
            return "偏瘦";
        }
        if (bmi1 < 24) {
            return "正常";
        }
        if (bmi1 < 27) {
            return "微胖";
        }
        return "該減肥了";
    }
}
