package cn.tedu.egmybatis3.pojo;

public class CustomersVO {
    private String custName;
    private String address;

    @Override
    public String toString() {
        return "CustomersVO{" +
                "custName='" + custName + '\'' +
                ", address='" + address + '\'' +
                '}';
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
