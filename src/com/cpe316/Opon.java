package com.cpe316;

import static java.lang.Thread.sleep;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;


public class Opon {
    //FuzzyLogic logic = new FuzzyLogic();
    public static int[] board = new int[13];
    public static int[] board1 = new int[13];
    private int playerOne;
    private int playerTwo;
    static int count, selection, value;


    public boolean isRowEmpty(int a, int b){
        int check =0;
        for (int i = a; i<=b; i++){
            check +=board1[i];
         }
        return check == 0;
    }

    public void intialize() {
//        final int startSeed = 4;
//        for (int x = 0; x < board.length; x++) {
//            board[x] = startSeed;
//        }
        Arrays.fill(board,4);
    }


    public void display() throws InterruptedException {

        sleep(2000);

        for (int x = 6; x >= 1; x--) {
            System.out.printf("%2s    ", String.valueOf(board[x]));
            //System.out.println();
        }
        System.out.println();
        for (int x = 7; x <= 12; x++) {
            System.out.printf("%2s    ", String.valueOf(board[x]));
            //System.out.println();
        }
        System.out.printf("%nCaptures by COM: %s%nCaptures by USER: %s%n",
                playerOne, playerTwo);


    }
//    public void start(){
//        System.out.printf(
//                "     ******%n" +
//                "    ********%n" +
//                "  ***        **%n" +
//                "  **");
//    }

    public void play(int[] board) {
        count = selection;
        int boardContent = board[selection];
        board[selection] = 0;
        do {
            selection++;
            if (selection > 12) selection = 1;
            if (selection == count) continue;
            board[selection]++;
            boardContent--;

        } while (boardContent != 0);


    }

    public void capture(int value) {
        int player1=0, player2 = 0;

        int territory = selection;
        System.arraycopy(board, 0, board1, 0, 13);
        if (value < 7 && territory > 6) {
            while (board1[territory] <= 3 & board1[territory]>1) {
                player1 += board1[territory];
                board1[territory]=0;
                territory--;
                if (board1[territory]==0) break;
                if (territory<=6) break;

            }
            if (!isRowEmpty(1, 6))
                System.arraycopy(board1, 0, board, 0 , 13);
            playerOne += player1; playerTwo += player2;
        }
        else if (value > 6 && territory < 7) {
            while (board1[territory] <= 3 & board1[territory]>1) {
                player2 += board1[territory];
                board1[territory]=0;
                territory--;
                if (board1[territory]==0) break;
                if (territory>=7) break;

            }
            if (!isRowEmpty(7, 12))
                System.arraycopy(board1, 0, board, 0 , 13);
            playerOne += player1; playerTwo += player2;
        }

        //Arrays.stream(board1).filter(e-> e<=6).reduce(0,(a,b)-> a+b);

    }



    public static void  main(String[] args) throws InterruptedException {
        FuzzyLogic log = new FuzzyLogic();
        var random = new Random();
        Opon ayo = new Opon();
        Scanner input = new Scanner(System.in);
        ayo.intialize();
        ayo.display();
       while (true) {
           boolean b = true;
           do {System.out.println("Enter a number to play");




               try {value = input.nextInt()+6 ;


                   if (value>12 || value < 7 )
                       throw new OutOfRangeException();
                   else if (Opon.board[value]==0) throw new EmptyContentException();
                   b = false;
                   selection = value;
                }
                catch (OutOfRangeException e)  {
                   System.err.println("XXX Please input a No within range 1 and 6");

                }
                catch (EmptyContentException e){
                   //e.printStackTrace();
                   System.err.println( " This hole is" +
                           " empty, please select another");
                }


               catch (InputMismatchException e){
                   System.err.println("Input is not valid");
               }



           } while (b);
           ayo.play(Opon.board);
           ayo.capture(value);
           ayo.display();
           sleep(2000);

           // computer Plays.....................................
           value = log.play();
           if (value == 0 || Opon.board[value]==0) {

               do {
                   value = 1 + random.nextInt(6);

               } while (Opon.board[value] == 0);
           }
           selection = value;
           System.out.println(value );
           //System.out.print(log.copyBoard);
           ayo.play(Opon.board);
           ayo.capture(value);
           ayo.display();




       }

    }

}

