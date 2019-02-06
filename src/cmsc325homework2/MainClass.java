package cmsc325homework2;

/**
 * File: MainClass.java
 * Author: Dillan Cobb
 * Date: 9/14/2018
 * Purpose: Create the frame and handle the drawing of the images to the user.
 * Then, preform controlled animations of the images of translating, scaling
 * and rotating the images for desired animated effect per frame.
 */

// Imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainClass extends JPanel {

    // Variables
    private int frameNumber;
    
    private long elapsedTimeMillis;
    
    private float pixelSize;
    
    static int translateX = 0;
    static int translateY = 0;
    static double rotation = 0.0;
    static double scaleX = 1.0;
    static double scaleY = 1.0;
    
    // Constructs the images for use
    Images myImages = new Images();
    BufferedImage triforceImage = myImages.drawImage(Images.triforceImage);
    BufferedImage smileyImage = myImages.drawImage(Images.smileyImage);
    BufferedImage letterDImage = myImages.drawImage(Images.letterDImage);
    BufferedImage flagImage = myImages.drawImage(Images.flagImage);
    BufferedImage swordIconImage = myImages.drawImage(Images.swordIconImage);
    
    public static void main(String[] args) {
        // Generate the frame for the user to see the images on
        JFrame window;
        window = new JFrame("Homework 2");
        final MainClass panel = new MainClass();
        
        window.setContentPane(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setResizable(false);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(
                (screen.width - window.getWidth()) / 2,
                (screen.height - window.getHeight()) / 2);
        // Timer that counts frames to determine the animation
        Timer animationTimer;
        final long startTime = System.currentTimeMillis();
        
        animationTimer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (panel.frameNumber > 4) {
                    panel.frameNumber = 0;
                } else {
                    panel.frameNumber++;
                }
                panel.elapsedTimeMillis = System.currentTimeMillis();
                panel.repaint();
            }
        });
        // Make the window visible and start the animation timer
        window.setVisible(true);
        animationTimer.start();
    }
    
    // MainClass sets the default demensions of the screen to 800x600 pixels
    public MainClass() {
        setPreferredSize(new Dimension(800, 600));
    }
    
    // paintComponent method from the examples, modified for the homework
    // assignment. Controls the graphic output and generates it for drawing.
    // Also handles each event per frame to get the desired animation effect.
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Controlling the graphical detail
        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
               
        g2.setRenderingHints(rh);
        
        // Fills the background to be solid white to the size of the frame
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        applyWindowToViewportTransformation(g2, -100, 100, -100, 100, true);
        
        AffineTransform savedTransform = g2.getTransform();
        
        // Counts and displays the frame according to the timer. Different
        // frames call for different effects in the animation
        System.out.println("Frame is " + frameNumber);
        switch (frameNumber) {
            case 0: // First frame is the default image.
                translateX = 0;
                translateY = 0;
                scaleX = 1.0;
                scaleY = 1.0;
                rotation = 0;
                break;
            case 1: // Second frame moves the image -10x and +12y translated.
                translateX = -10;
                translateY = 12;
                scaleX = 1.0;
                scaleY = 1.0;
                rotation = 0;
                break;
            case 2: // Third frame rotates the image 55 degrees. 
                // Counter clockwise
                translateX = -10;
                translateY = 12;
                scaleX = 1.0;
                scaleY = 1.0;
                rotation = 55;
                break;
            case 3: // Fourth frame rotates the image -75 degrees.
                // Clockwise
                translateX = -10;
                translateY = 12;
                scaleX = 1.0;
                scaleY = 1.0;
                rotation = -75;
                break;
            case 4: // Fifth frame scales the image 3.0 to the x, 
                // and 1.5 to the y
                translateX = -10;
                translateY = 12;
                scaleX = 3.0;
                scaleY = 1.5;
                rotation = -75;
                break;
            default:
                break;
        }
        
        // Animation handled in accordance to the switch setting the options
        
        // Handles the triforceImage
        g2.translate(translateX, translateY); // Move image.
        g2.rotate(Math.toRadians(rotation)); // Rotate image.
        g2.scale(scaleX, scaleY); // Scale image.
        g2.drawImage(triforceImage, 0, 0, this); // Draw image.
        g2.setTransform(savedTransform);

        // Handles the smileyImage
        g2.translate(translateX, translateY); // Move image.
        g2.translate(-75,50); // Offset so the images do not overlap
        g2.rotate(Math.toRadians(rotation)); // Rotate image.
        g2.scale(scaleX, scaleY); // Scale image.
        g2.drawImage(smileyImage, 0, 0, this); // Draw image.
        g2.setTransform(savedTransform);
        
        // Handles the letterDImage
        g2.translate(translateX, translateY); // Move image.
        g2.translate(50,50); // Offset so the images do not overlap
        g2.rotate(Math.toRadians(rotation)); // Rotate image.
        g2.scale(scaleX, scaleY); // Scale image.
        g2.drawImage(letterDImage, 0, 0, this); // Draw image.
        g2.setTransform(savedTransform);
        
        // Handles the flagImage
        g2.translate(translateX, translateY); // Move image.
        g2.translate(-75,-50); // Offset so the images do not overlap
        g2.rotate(Math.toRadians(rotation)); // Rotate image.
        g2.scale(scaleX, scaleY); // Scale image.
        g2.drawImage(flagImage, 0, 0, this); // Draw image.
        g2.setTransform(savedTransform);
        
        // Handles the swordIconImage
        g2.translate(translateX, translateY); // Move image.
        g2.translate(50,-50); // Offset so the images do not overlap
        g2.rotate(Math.toRadians(rotation)); // Rotate image.
        g2.scale(scaleX, scaleY); // Scale image.
        g2.drawImage(swordIconImage, 0, 0, this); // Draw image.
        g2.setTransform(savedTransform);
    }

    // Method taken directly from AnimationStarter.java Code
    private void applyWindowToViewportTransformation(Graphics2D g2,
            double left, double right, double bottom, double top,
            boolean preserveAspect) {
        int width = getWidth();   // The width of this drawing area, in pixels.
        int height = getHeight(); // The height of this drawing area, in pixels.
        if (preserveAspect) {
            // Adjust the limits to match the aspect ratio of the drawing area.
            double displayAspect = Math.abs((double) height / width);
            double requestedAspect = Math.abs((bottom - top) / (right - left));
            if (displayAspect > requestedAspect) {
                // Expand the viewport vertically.
                double excess = (bottom - top) * (displayAspect / requestedAspect - 1);
                bottom += excess / 2;
                top -= excess / 2;
            } else if (displayAspect < requestedAspect) {
                // Expand the viewport vertically.
                double excess = (right - left) * (requestedAspect / displayAspect - 1);
                right += excess / 2;
                left -= excess / 2;
            }
        }
        g2.scale(width / (right - left), height / (bottom - top));
        g2.translate(-left, -top);
        double pixelWidth = Math.abs((right - left) / width);
        double pixelHeight = Math.abs((bottom - top) / height);
        pixelSize = (float) Math.max(pixelWidth, pixelHeight);
    }
}
