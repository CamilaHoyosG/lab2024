/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.huynseda.microkernel.common.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Cart {
    private int nroProductos;
    private List<Product> productos;
    private String paymentCode;
    private int valorTotal;
    
    
    /*public Cart(int nroProductos){
        this.nroProductos = nroProductos;
        productos = new ArrayList<>();
    }*/
    public Cart(){
        productos = new ArrayList<>();
    }
    public Cart(int valorTotal, String paymentCode){
        this.valorTotal = valorTotal;
        this.paymentCode = paymentCode;
    }
    /*public Cart(int nroProductos, String paymentCode){
        this.nroProductos = nroProductos;
        productos = new ArrayList<>();
        this.paymentCode = paymentCode;
    }*/
    
    public int getNroProductos(){
        return nroProductos;
    }
    
    public void setNroProductos(int nroProductos){
        this.nroProductos = nroProductos;
    }
    
    public List<Product> getProductos(){
        return productos;
    }
    
    public void setProductos(List<Product> productos){
        this.productos = productos;
    }
    public String getPaymentCode(){
        return paymentCode;
    }
}
