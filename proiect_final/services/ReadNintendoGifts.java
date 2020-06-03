/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import model.NintendoGift;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import model.NintendoVoucher;
/**
 *
 * @author Windows10
 */
public class ReadNintendoGifts {
     private static final ReadNintendoGifts instance = new ReadNintendoGifts();

    private ReadNintendoGifts() {}

    public static ReadNintendoGifts getReadingInstance() {
        return instance;
    }

    public Path getPath() {
        return Paths.get("gifts.csv");
    }
    
    public ArrayList <NintendoGift> readAllGifts() {
        ArrayList <NintendoGift> gifts = new ArrayList<>();

        File file = new File(String.valueOf(getPath()));
        if(!file.exists())
            return gifts;

        try (BufferedReader csvReader = new BufferedReader(new FileReader(String.valueOf(getPath())))) {
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");

                //gifts.add(new NintendoVoucher (Integer.parseInt(data[0]), data[1], data[2]));
            }
        } catch (IOException e){
        }
        return gifts;
    }
    
}
