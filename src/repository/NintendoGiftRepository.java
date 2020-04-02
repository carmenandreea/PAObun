/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;
import model.NintendoGift;
import model.NintendoVoucher;
import model.LoyaltyPoints;
import java.util.ArrayList;

/**
 *
 * @author Windows10
 */
public class NintendoGiftRepository {
    
    
    private ArrayList<NintendoGift> gifts;
    
    
    public NintendoGiftRepository() {
        gifts = new ArrayList<>();
    }
    
    
    public void remove(NintendoGift points) {
        gifts.remove(points);
    }
    
    public ArrayList<NintendoGift> getAllGifts() {
        return gifts;
    }
    
}
