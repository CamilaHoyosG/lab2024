package co.com.huynseda.market.access;

import co.com.huynseda.microkernel.common.entities.Cart;
import co.com.hyunseda.market.service.ProductService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class CartRepository implements ICartRepository {
    private Connection conn;
    
    public CartRepository(){
        initDatabase();
    }
    
     @Override
    public boolean add(Cart newCart) {
        try {    
        String sql = "INSERT INTO compras ( productoId, cantidad )"
                + "VALUES ( ?, ?)";
         PreparedStatement pstmt= conn.prepareStatement(sql);
            long productId = newCart.getProductos().get(0).getProductId();
            pstmt.setLong(1, productId); 
            pstmt.setInt(2, newCart.getNroProductos()); 
            pstmt.executeUpdate();
         return true;
    } catch (SQLException ex) {
        Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}
    private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS compras (\n"
                + "	compraId integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	productoId integer NOT NULL,\n"
                + "	cantidad integer NULL,\n"
                + "     FOREIGN KEY (productoId) REFERENCES products(productId)\n"
                + ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:./myDatabase.db"; //Para Linux/Mac
        //String url = "jdbc:sqlite:C:/sqlite/db/myDatabase.db"; //Para Windows
        String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
