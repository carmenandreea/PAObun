/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import model.NintendoGift;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.ArrayMap;
import java.util.*;

/**
 *
 * @author Windows10
 */
public class WriteNintendoGifts {
    private static  WriteNintendoGifts instance = new WriteNintendoGifts();

    private WriteNintendoGifts() {}

    
    public static WriteNintendoGifts getWritingInstance()
    {
        return instance;
    }

    
    
    
    public Path getPath() {
        return Paths.get("gifts.csv");
    }

    public void writeRoom(NintendoGift gift) {
        try (FileWriter csvWriter = new FileWriter(String.valueOf(getPath()), true)) {
            List<String>line = Arrays.asList(gift.getUser(), Integer.toString(gift.getGiftId()),Integer.toString(gift.getCampaign()));
            csvWriter.append(String.join(",", line));
            csvWriter.append("\n");
            csvWriter.flush();
        } catch (IOException e) {
        }
    }

}
