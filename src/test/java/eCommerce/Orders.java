package eCommerce;

import java.util.List;

public class Orders
{
    private List<OrderDetails> orderdetails;

    public List<OrderDetails> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(List<OrderDetails> orderdetails) {
        this.orderdetails = orderdetails;
    }
}