package co.com.hyunseda.market.service;

import co.com.huynseda.market.access.ICartRepository;
import co.com.huynseda.microkernel.common.entities.Cart;
import co.com.huynseda.microkernel.common.entities.Product;
import co.com.huynseda.microkernel.common.interfaces.IPaymentPlugin;
import co.com.hyunseda.infra.Subject;
import co.com.hyunseda.plugin.manager.PaymentPluginManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CartService extends Subject{
    
    private ICartRepository repository;
    private Cart cart;
    
    Cart newCart = new Cart();
    List<Product> productos = new ArrayList<>();


    public CartService(ICartRepository repository) {
        this.repository = repository;
    }
   
    public boolean addCart(Product prod, int nroProductos) {

    // Add the product to the cart
    newCart.getProductos().add(prod); // Assuming getProductos() returns a List<Product> inside Cart

    // Set the number of products for the cart
    newCart.setNroProductos(nroProductos);

    // Set the cart to the repository
    setCart(newCart); // Assuming setCart() sets the current cart in the repository

    // Add the cart to the repository
    return repository.add(newCart); // Assuming add() method in the repository adds the cart
}

    
    public Cart getCart(){
        return cart;
    }
    
    public void setCart(Cart cart) {
        this.cart = cart;
        this.notifyAllObserves();
    } 
    
    public String paymentFunction(Cart cart) throws Exception {
    String paymentCode = cart.getPaymentCode();
    PaymentPluginManager manager = PaymentPluginManager.getInstance();

    // Check if manager is null before using it
    if (manager == null) {
        throw new Exception("El administrador de plugins de pago no está inicializado.");
    }

    IPaymentPlugin plugin = manager.getDeliveryPlugin(paymentCode);

    if (plugin == null) {
        throw new Exception("No hay un plugin disponible para: " + paymentCode);
    }

    return plugin.realizarPago(cart);
}

}
