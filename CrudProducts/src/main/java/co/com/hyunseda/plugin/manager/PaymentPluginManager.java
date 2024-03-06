/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.plugin.manager;

import co.com.huynseda.microkernel.common.interfaces.IPaymentPlugin;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class PaymentPluginManager {
    
    private static final String FILE_NAME = "plugin.properties";
    private static PaymentPluginManager instance;

    private Properties pluginProperties;

    private PaymentPluginManager() {
        pluginProperties = new Properties();
    }

    public static PaymentPluginManager getInstance() {
        return instance;
    }

    public static void init(String basePath) throws Exception {

        instance = new PaymentPluginManager();
        instance.loadProperties(basePath);
        if (instance.pluginProperties.isEmpty()) {
            throw new Exception("Could not initialize plugins");
        }

    }
    
    
    public IPaymentPlugin getDeliveryPlugin(String paymentCode) {
    if (pluginProperties == null) {
        // Handle the case where pluginProperties is not initialized
        return null;
    }

    // Verify if there's an entry in the properties file for the given payment code.
    String propertyName = "payment." + paymentCode.toLowerCase();
    if (!pluginProperties.containsKey(propertyName)) {
        return null;
    }

    IPaymentPlugin plugin = null;
    // Get the class name of the plugin.
    String pluginClassName = pluginProperties.getProperty(propertyName);

    try {
        // Get a reference to the plugin class.
        Class<?> pluginClass = Class.forName(pluginClassName);
        if (pluginClass != null) {
            // Create a new instance of the plugin.
            Object pluginObject = pluginClass.getDeclaredConstructor().newInstance();
            if (pluginObject instanceof IPaymentPlugin) {
                plugin = (IPaymentPlugin) pluginObject;
            }
        }
    } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException
            | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
        Logger.getLogger("PaymentPluginManager").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
    }

    return plugin;
}
    
     private void loadProperties(String basePath) {

        String filePath = basePath + FILE_NAME;
        try (FileInputStream stream = new FileInputStream(filePath)) {

            pluginProperties.load(stream);

        } catch (IOException ex) {
            Logger.getLogger("PaymentPluginManager").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }

    }
}
