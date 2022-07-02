package creatures.enemies;

import graphics.GameWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Vodianyk extends VerticalEnemy {

    private static final int DRAW_RATE = 10;
    private static final int VERTICAL_SPEED = 3;
    private static BufferedImage left_fst/*, left_snd*/, right_fst/*, right_snd*/;

    static {
        loadImage();
    }

    public Vodianyk(int x, int y) {
        super(x, y);
        super.solidArea = new Rectangle(1, 1, 2*GameWindow.blockSize - 2, GameWindow.blockSize - 2);
    }

    public int getDrawRate() {
        return DRAW_RATE;
    }

    @Override
    protected int getVerticalSpeed() {
        return VERTICAL_SPEED;
    }

    @Override
    public int getFigureWidth() {
        return 2*GameWindow.blockSize;
    }

    @Override
    public int getFigureHeight() {
        return GameWindow.blockSize;
    }

    @Override
    protected BufferedImage getImage() {
        if (this.velocityX < 0) {
            if (this.draw_counter < getDrawRate())
                return Vodianyk.left_fst;
            else
                return Vodianyk.left_fst;
        } else {
            if (this.draw_counter < getDrawRate())
                return Vodianyk.right_fst;
            else
                return Vodianyk.right_fst;
        }
    }

    private static void loadImage() {
        try {
            Vodianyk.left_fst = ImageIO.read(new File("heroes/VodianykL.png"));
            /*Vodianyk.left_snd = ImageIO.read(new File("heroes/VodianykL_2.png"));*/
            Vodianyk.right_fst = ImageIO.read(new File("heroes/VodianykR.png"));
            /*Vodianyk.right_snd = ImageIO.read(new File("heroes/VodianykR_2.png"));*/
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error while loading images for vodianyk",
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}