package cn.tedu.egmybatis2.pojo;

public class OrdersVO {
    private Integer id;
    private String state;

    @Override
    public String toString() {
        return "OrdersVO{" +
                "id=" + id +
                ", state='" + state + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
