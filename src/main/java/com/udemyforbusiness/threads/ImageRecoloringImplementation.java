package com.udemyforbusiness.threads;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ImageRecoloringImplementation {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedImage input = ImageIO
                .read(new File("C:\\Users\\praka\\Projects-GFG\\ds-algo\\src\\main\\resources\\many-flowers.jpg"));
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);

        Instant start = Instant.now();
        // recolorSingleThreaded(input, output);
        recolorMultiThreaded(input, output, 8);
        File outputFile = new File(
                "C:\\Users\\praka\\Projects-GFG\\ds-algo\\src\\main\\resources\\output\\many-flowers.jpg");
        outputFile.createNewFile();
        ImageIO.write(output, "jpg", outputFile);
        Instant end = Instant.now();

        System.out.println(Duration.between(start, end).toMillis());
    }

    private static void recolorMultiThreaded(BufferedImage input, BufferedImage output, int numberOfThreads)
            throws InterruptedException {
        int height = input.getHeight() / numberOfThreads;
        List<Thread> threads = new ArrayList(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            final int threadMultiplier = i;

            threads.add(new Thread(() -> {
                recolorImage(input, output, 0, threadMultiplier * height, input.getWidth(), height);
            }));
        }

        for (int i = 0; i < numberOfThreads; i++) {
            threads.get(i).start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            threads.get(i).join();
        }
    }

    private static void recolorSingleThreaded(BufferedImage input, BufferedImage output) {
        recolorImage(input, output, 0, 0, input.getWidth(), input.getHeight());
    }

    private static void recolorImage(BufferedImage input, BufferedImage output, int leftCorner, int topCorner,
            int width, int height) {
        for (int i = leftCorner; i < leftCorner + width; i++) {
            for (int j = topCorner; j < topCorner + height; j++) {
                recolor(input, output, i, j);
            }
        }
    }

    private static void recolor(BufferedImage input, BufferedImage output, int x, int y) {
        int rgb = input.getRGB(x, y);

        int red = getRed(rgb);
        int green = getGreen(rgb);
        int blue = getBlue(rgb);

        if (isShadeOfGrey(red, green, blue)) {
            red = Math.min(255, red + 10);
            green = Math.max(0, green - 80);
            blue = Math.max(0, blue - 20);
        }

        int newRgb = createRGB(red, green, blue);

        output.getRaster().setDataElements(x, y, output.getColorModel().getDataElements(newRgb, null));

    }

    private static boolean isShadeOfGrey(int red, int green, int blue) {
        return Math.abs(red - green) < 30 && Math.abs(red - blue) < 30 && Math.abs(green - blue) < 30;
    }

    private static int getBlue(int rgb) {
        int blue = rgb & 0x000000FF;
        return blue;
    }

    private static int getGreen(int rgb) {
        int green = rgb & 0x0000FF00;
        green >>= 8;
        return green;
    }

    private static int getRed(int rgb) {
        int red = rgb & 0x00FF0000;
        red >>= 16;
        return red;
    }

    private static int createRGB(int red, int green, int blue) {
        int rgb = 0x00000000;

        rgb |= blue;
        rgb |= green << 8;
        rgb |= red << 16;

        return rgb;
    }
}
