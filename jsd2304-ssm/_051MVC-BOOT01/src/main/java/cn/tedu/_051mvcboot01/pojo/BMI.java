package cn.tedu._051mvcboot01.pojo;

public class BMI {
    private Double height;
    private Double weight;

    @Override
    public String toString() {
        return "BMI{" +
                "height=" + height +
                ", weight=" + weight +
                '}';
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
