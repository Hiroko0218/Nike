package cn.tedu.egmybatis3.pojo;


public class OrdersVO {
    private String orderId;
    private String status;
    private Double amt;
    private String custName;
    private String address;

    @Override
    public String toString() {
        return "OrdersVO{" +
                "orderId='" + orderId + '\'' +
                ", status='" + status + '\'' +
                ", amt=" + amt +
                ", custName='" + custName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
