package com.csc455.hw2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GridGenerator {
    static String[][] board = new String[100][100];
    static BufferedWriter bwriter;

    public static void generateFile() throws IOException {
        try {
            bwriter = new BufferedWriter(new FileWriter("../assets/data/grid3.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Random rand = new Random();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int r = rand.nextInt(9);
                board[i][j] = String.valueOf(r);
                if (board[i][j].equals("0")) {
                    board[i][j] = "F";
                }
            }
        }

        int teleporterNumber = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals("8")) {
                    if(rand.nextFloat() > .99) {
                        board[i][j] = "T" + teleporterNumber;

                        int x = rand.nextInt(board.length - 1);
                        int y = rand.nextInt(board.length - 1);

                        while (board[x][y].contains("F") || board[x][y].contains("T")) {
                            x = rand.nextInt(board.length - 1);
                            y = rand.nextInt(board.length - 1);
                        }

                        board[x][y] = "T" + teleporterNumber;
                        teleporterNumber++;
                    }
                }
                bwriter.write(board[i][j] + " ");
            }
            bwriter.newLine();
        }
        bwriter.close();
    }
}
