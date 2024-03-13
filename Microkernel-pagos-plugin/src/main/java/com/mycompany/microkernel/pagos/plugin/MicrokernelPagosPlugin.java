package com.mycompany.microkernel.pagos.plugin;

import co.com.huynseda.microkernel.common.entities.Cart;
import co.com.huynseda.microkernel.common.entities.Product;
import co.com.huynseda.microkernel.common.interfaces.IPaymentPlugin;
import com.sun.tools.javac.Main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class MicrokernelPagosPlugin implements IPaymentPlugin {

    @Override
    public String realizarPago(Cart cart) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre de usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();
        
        if (verificarCredenciales(usuario, contrasena)) return "PAGO REALIZADO";
     
            return("PAGO DENEGADO!!!!");
    }
    
    public static boolean verificarCredenciales(String usuario, String contrasena) {
    try (InputStream inputStream = MicrokernelPagosPlugin.class.getResourceAsStream("credenciales.txt");
         BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            String usuarioArchivo = datos[0];
            String contrasenaArchivo = datos[1];
            if (usuario.equals(usuarioArchivo) && contrasena.equals(contrasenaArchivo)) {
                return true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
}

    
}
