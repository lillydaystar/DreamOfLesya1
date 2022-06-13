package graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DrawMap {
    GameWindow window;
    Block[] blocks;
    String[][] map;

    public DrawMap(GameWindow window){
        this.window = window;
        this.blocks = new Block[5];
        try {
            blocks[0] = new Block();
            blocks[0].image = ImageIO.read(new File("images/Grass.jpg"));
            blocks[1] = new Block();
            blocks[1].image = ImageIO.read(new File("images/TreeV.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.map = new String[window.columnsOnScreen][window.rowsOnScreen];
        configMap();
    }

    public void configMap(){
        FileReader fr;
        try {
            fr = new FileReader("worlds/map1.txt");
            BufferedReader br = new BufferedReader(fr);

            int cols = 0;
            int rows = 0;

            while(rows < window.rowsOnScreen){
                String s = br.readLine();
                String[] str = s.split("");
                for(;cols<window.columnsOnScreen; cols++){
                    if(cols >= str.length)
                        throw new IllegalArgumentException("Неправильний формат карти");
                    map[cols][rows] = str[cols];
                }
                cols = 0;
                rows++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void paintMap(Graphics2D g) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < window.columnsOnScreen && row < window.rowsOnScreen){
            String block = map[col][row];
            int number = -1;
            if(block.equals("A")){
                number = 0;
            }
            else if(block.equals("T")){
                number = 1;
            }
            if(number != -1) {
                g.drawImage(blocks[number].image, x, y, window.blockSize, window.blockSize, null);
            }
            x+=window.blockSize;
            col++;
            if(col == window.columnsOnScreen){
                col = 0;
                row++;
                x = 0;
                y += window.blockSize;
            }
        }



    }
}