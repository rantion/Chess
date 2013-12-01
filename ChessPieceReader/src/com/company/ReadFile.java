package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/12/13
 * Time: 7:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReadFile {
    private Game game;
    private Controller controller;

    public ReadFile(String filePath, Game game){
        this.game = game;
        controller = new Controller(game);
        readInFile(filePath);

    }

    private void readInFile(String filePath){

        BufferedReader reader = null;

        try {
            File file = new File(filePath);
            reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                if(line!=null){
                    if(filePath.equals("/Users/Rachel/Documents/initialBoard.txt")){
                        controller.initialPlacement(line);
                    }
                    else{
                   controller.checkCommand(line);
                    }

                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
