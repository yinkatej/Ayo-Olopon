package com.cpe316;

import java.util.Arrays;

/* import static com.cpe316.Opon; */


public class FuzzyLogic {
   int copyBoard[] = new int[13];
   int[] array = new int[6];
   int[] Array = new int[6];
   Opon opon = new Opon();

   public int play() {
       Arrays.fill(array, 0);
       Arrays.fill(Array, 0);

       for (int i = 0; i < array.length; i++) {
           System.arraycopy(Opon.board, 0, copyBoard, 0 , 13);
           if (copyBoard[i+1]!=0) {
               Opon.selection = i + 1;
               opon.play(copyBoard);
               int territory = Opon.selection;
               if (territory > 6) {
                   while (copyBoard[territory] <= 3 & copyBoard[territory] > 1) {
                       array[i] += copyBoard[territory];
                       copyBoard[territory] = 0;
                       territory--;
                       if (copyBoard[territory] == 0) break;
                       if (territory <= 6) break;

                   }


               }

           }



       }

       for (int i = 0; i < Array.length; i++) {
           System.arraycopy(Opon.board, 0, copyBoard, 0, 13);
           if (copyBoard[i+7] != 0) {
               Opon.selection = i + 7;
               opon.play(copyBoard);
               int territory = Opon.selection;
               if (territory < 7) {
                   while (copyBoard[territory] <= 3 & copyBoard[territory] > 1) {

                       Array[i] += copyBoard[territory];
                       copyBoard[territory] = 0;
                       territory--;
                       if (copyBoard[territory] == 0) break;
                       //if (territory <= 6) break;

                   }
               }
           }
       }



       int max = 0, a = 0;
       int max1 = 0 ;
       for (int i=0; i< 6; i++){

           if (array[i]==0) continue;
           if (array[i]>max){
               max = array[i];
               a=i+1;

           }
       }
       for (int i =0; i < Array.length; i++){
          // b = i + 6;
           if (Array[i]==0) continue;
           if (Array[i]> max1) max1 = Array[i];
       }
       if (max1 > max) {
           System.arraycopy(Opon.board, 0, copyBoard, 0, 13);
           for ( int i = 0;  i < Array.length; i++){
               if (Array[i] == max1) {
                   Opon.selection = i+7;
                   opon.play(copyBoard);
               }

           }
           return Opon.selection;
       }
       else if (max!=0 ) return a;
       else  return 0;
   }

}
