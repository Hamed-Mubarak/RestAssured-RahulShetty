package E2E_ecommerceAPI;

import java.util.List;

public class CreateOrderResponse {
    private String message;
    private List<String> orders;
    private List<String> productOrderId;
    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}
    public List<String> getOrders() {return orders;}
    public void setOrders(List<String> orders) {this.orders = orders;}
    public List<String> getProductOrderId() {return productOrderId;}
    public void setProductOrderId(List<String> productOrderId) {this.productOrderId = productOrderId;}
}
