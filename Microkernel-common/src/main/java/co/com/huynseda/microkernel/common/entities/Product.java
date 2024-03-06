package co.com.huynseda.microkernel.common.entities;

/**
 *
 * @author Libardo Pantoja, Julio Hurtado
 */
public class Product {


    private Long productId;

    private String name;
    
    private String description;

    private int cantidad;
    
    private double price;
    
    public Product(Long productId, String name, String description, int cantidad, double price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.cantidad = cantidad;
        this.price = price;
    }

    public Product() {
        
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
