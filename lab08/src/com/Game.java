package com;

import javax.management.MBeanServer;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by kamil on 29.11.16.
 */
public class Game extends NotificationBroadcasterSupport implements GameMBean  {

    Scanner scanner = new Scanner(System.in);
    private static MBeanServer mbs = null;
    static final int ROWS = 6;
    static final int COLUMNS = 7;
    int[][] fields = new int[ROWS][COLUMNS];
    static Game g;
    static Random random = new Random();

    public static void main(String[] args) {

        mbs = ManagementFactory.getPlatformMBeanServer();

        // Unique identification of MBeans
        g = new Game();
        //Hello helloBean = new Hello();
        ObjectName helloName = null;

        try {
            // Uniquely identify the MBeans and register them with the platform MBeanServer
            helloName = new ObjectName("FOO:name=GRA");
            mbs.registerMBean(g, helloName);
        } catch(Exception e) {
            e.printStackTrace();
        }
        g.runGame();
        waitForEnterPressed();
       // g.showFields();

        // System.out.println(g.checkWin(1,ROWS-1, 0));

        //g.enter(1,1);
   //     System.out.println(g.checkWin(1,ROWS-1, 1));
        //g.showFields();
    }

    public void runGame(){
        while(true){
            g.showFields();
            if(!g.userMove())
                break;

            if(g.checkWin(1)){
                Notification n = new Notification("Wygral gracz",this,1,2,"asd");
                sendNotification(n);
                System.out.println("Wygral gracz, brawo");
                return;
            }

            if(!g.computerMove())
                break;
            if(g.checkWin(2)){
                Notification n = new Notification("Wygral komputer",this,1,2,"asd");
                sendNotification(n);
                System.out.println("Wygral komputer, brawo");
                return;
            }
        }
        Notification n = new Notification("Remis",this,1,2,"asd");
        sendNotification(n);
        System.out.println("Koniec gry - remis");
    }

    public boolean userMove(){
        Notification n = new Notification("Twoj ruch, wybierz kolumne",this,1,2,"asd");
        sendNotification(n);
        System.out.println("Twoj ruch, wybierz kolumne");
        int col = scanner.nextInt();
        System.out.println(col);
        if(g.enter(col,1) ==0)
            return false;
        return true;
    }

    public boolean computerMove(){
//        System.out.println("Computer move");
//        int col = random.nextInt(7);
//        System.out.println(col);
//        if(g.enter(col,2) ==0)
//            return false;
//        return true;
        Notification n = new Notification("Twoj komputera, wybierz kolumne",this,1,2,"asd");
        sendNotification(n);
        System.out.println("Twoj komputera, wybierz kolumne");
        int col = scanner.nextInt();
        System.out.println(col);
        if(g.enter(col,2) ==0)
            return false;
        return true;
    }

    public void showFields(){
        System.out.println("---------------");
        for (int i=0; i< ROWS;i++) {
            for(int j=0; j< COLUMNS;j++){
                System.out.print(fields[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Zwraca ile jest wolnych pol w kolumnie
     * @param column
     * @param player
     * @return
     */
    public int enter(int column, int player){
        for(int i=ROWS-1; i>=0; i--)
            if(fields[i][column] == 0){
                fields[i][column] = player;
                return i;
            }
        return -1;
    }

//    boolean checkAllRows(int player){
//        int count=0;
//        for(int j=0; j< ROWS; j++) {
//            for (int i = 0; i < COLUMNS; i++) {
//                if (fields[j][i] == player)
//                    count++;
//                else
//                    count = 0;
//                if (count >= 4)
//                    return true;
//            }
//        }
//        return false;
//    }
//
//    boolean checkAllColumns(int player){
//        int count=0;
//        for (int j = 0; j < COLUMNS; j++) {
//            for (int i = 0; i < ROWS; i++) {
//                if (fields[i][j] == player)
//                    count++;
//                else
//                    count = 0;
//                if (count >= 4)
//                    return true;
//            }
//        }
//        return false;
//    }

    public boolean checkWin(int player){

        int count=0;
        for(int j=0; j< ROWS; j++) {
            for (int i = 0; i < COLUMNS; i++) {
                if (fields[j][i] == player)
                    count++;
                else
                    count = 0;

                if (count >= 4)
                    return true;
            }
        }
//Vertical check
        for (int j = 0; j < COLUMNS; j++) {
            for (int i = 0; i < ROWS; i++) {
                if (fields[i][j] == player)
                    count++;
                else
                    count = 0;

                if (count >= 4)
                    return true;
            }
        }
        // top-left to bottom-right - green diagonals
        for(int  rowStart = 0; rowStart <=  ROWS - 4; rowStart++){
            count = 0;
            int rowa, col;
            for( rowa = rowStart, col = 0; rowa < ROWS && col < COLUMNS; rowa++, col++ ){
                if(fields[rowa][col] == player){
                    count++;
                    if(count >= 4) return true;
                }
                else {
                    count = 0;
                }
            }
        }

// top-left to bottom-right - red diagonals
        for(int  colStart = 1; colStart <= COLUMNS - 4; colStart++){
            count = 0;
            int rowa, col;
            for( rowa = 0, col = colStart; rowa < ROWS && col < COLUMNS; rowa++, col++ ){
                if(fields[rowa][col] == player){
                    count++;
                    if(count >= 4) return true;
                }
                else {
                    count = 0;
                }
            }
        }

//        // top-left to bottom-left - top-right
//        for(int  rowStart = 0; rowStart <=  ROWS - 4; rowStart++){
//            count = 0;
//            int rowa, col;
//            for( rowa = rowStart, col = 0; rowa < ROWS && col < COLUMNS; rowa++, col++ ){
//                if(fields[rowa][col] == player){
//                    count++;
//                    if(count >= 4) return true;
//                }
//                else {
//                    count = 0;
//                }
//            }
//        }
//
//// top-left to bottom-right - red diagonals
//        for(int  colStart = 1; colStart <= COLUMNS - 4; colStart++){
//            count = 0;
//            int rowa, col;
//            for( rowa = 0, col = colStart; rowa < ROWS && col < COLUMNS; rowa++, col++ ){
//                if(fields[rowa][col] == player){
//                    count++;
//                    if(count >= 4) return true;
//                }
//                else {
//                    count = 0;
//                }
//            }
//        }
        return false;
    }

    @Override
    public String getBoard() {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i< ROWS;i++) {
            for(int j=0; j< COLUMNS;j++){
                builder.append(fields[i][j]);
            }
            builder.append(":");
        }
        return builder.toString();
    }

    @Override
    public void makeMove(int column) {
        System.out.println("wybrano kolumne " + column);
    }
    private static void waitForEnterPressed() {
        try {
            System.out.println("Press  to continue...");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
