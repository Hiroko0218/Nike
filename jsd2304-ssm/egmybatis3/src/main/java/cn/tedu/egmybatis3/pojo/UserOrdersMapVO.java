package cn.tedu.egmybatis3.pojo;

import java.util.List;

public class UserOrdersMapVO {
    private String custId;
    private String custName;
    private String address;
    private List<UserOrders> userOrdersList;

    @Override
    public String toString() {
        return "UserOrdersMapVO{" +
                "custId='" + custId + '\'' +
                ", custName='" + custName + '\'' +
                ", address='" + address + '\'' +
                ", userOrdersList=" + userOrdersList +
                '}';
    }

    public List<UserOrders> getUserOrdersList() {
        return userOrdersList;
    }

    public void setUserOrdersList(List<UserOrders> userOrdersList) {
        this.userOrdersList = userOrdersList;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
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
