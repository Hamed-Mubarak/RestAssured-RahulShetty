package E2E_ecommerceAPI;

public class GetOrderResponse {
    private GetOrderData data;
    private String message;

    public GetOrderData getData() {
        return data;
    }

    public void setData(GetOrderData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
