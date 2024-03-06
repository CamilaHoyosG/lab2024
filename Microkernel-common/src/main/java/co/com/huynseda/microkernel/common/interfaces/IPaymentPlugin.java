/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.huynseda.microkernel.common.interfaces;

import co.com.huynseda.microkernel.common.entities.Cart;
/**
 *
 * @author User
 */
public interface IPaymentPlugin {
    String realizarPago(Cart cart);
}
