/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Windows10
 */
public class NintendoWorld {
    
    private NintendoWorld() {
    }
    
    public static NintendoWorld getInstance() {
        return NintendoWorldHolder.INSTANCE;
    }
    
    private static class NintendoWorldHolder {

        private static final NintendoWorld INSTANCE = new NintendoWorld();
    }
}
