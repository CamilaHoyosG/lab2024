/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.huynseda.market.access;

import co.com.huynseda.microkernel.common.entities.Cart;

/**
 *
 * @author User
 */
public interface ICartRepository {
    boolean add(Cart newCart);
}
