
package co.com.hyunseda.market.presentation;

import co.com.huynseda.market.access.Factory;
import co.com.huynseda.market.access.ICartRepository;
import co.com.huynseda.market.access.IProductRepository;
import co.com.hyunseda.infra.Subject;
import co.com.hyunseda.market.service.CartService;
import co.com.hyunseda.plugin.manager.PaymentPluginManager;
import co.com.hyunseda.market.service.ProductService;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Libardo Pantoja
 */
public class Main extends Subject{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String basePath = getBaseFilePath();
        PaymentPluginManager.init(basePath);
        ICartRepository reposiitory = Factory.getInstance().getReposiiitory("jose"); 
        CartService cartService = new CartService(reposiitory);
        GUICurrentCart guiCurrentCart = new GUICurrentCart();
        
        GUIOtraVista guiOtra = new GUIOtraVista();
        guiOtra.setSize(400,300);
        guiOtra.setVisible(true);
        
        GUICarrito2 gui2 = new GUICarrito2();
        gui2.setSize(400,300);
        gui2.setVisible(true);        
        
        cartService.addObserver(guiCurrentCart);
        cartService.addObserver(guiOtra);
        cartService.addObserver(gui2);
        
        try {
            IProductRepository repository = Factory.getInstance().getRepository("default");
            ProductService productService = new ProductService(repository);
            GUIProducts instance = new GUIProducts(productService, cartService);
            instance.setVisible(true);
            GUICurrentCart iinstance = new GUICurrentCart();
            iinstance.setVisible(true);

        } catch (Exception ex) {
            Logger.getLogger("Application").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }
    }
    
    
     private static String getBaseFilePath() {
        try {
            String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = URLDecoder.decode(path, "UTF-8"); //This should solve the problem with spaces and special characters.
            File pathFile = new File(path);
            if (pathFile.isFile()) {
                path = pathFile.getParent();
                
                if (!path.endsWith(File.separator)) {
                    path += File.separator;
                }
                
            }

            return path;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error al eliminar espacios en la ruta del archivo", ex);
            return null;
        }
    }
    
}
