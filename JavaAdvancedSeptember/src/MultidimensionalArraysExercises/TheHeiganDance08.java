package MultidimensionalArraysExercises;

import java.util.Scanner;

import static java.lang.System.in;

public class TheHeiganDance08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);

        int playerHealth = 18500;
        double bossHealth = 3000000;
        double damage = Double.parseDouble(scanner.nextLine());

        char[][] field = new char[15][15];
        int[] playerPos = new int[2];
        playerPos[0] = 7;
        playerPos[1] = 7;

        boolean isInCloud=false;
        String lastSpell="";

        while (playerHealth > 0 ) {

            bossHealth -= damage;
            if (bossHealth<=0){
                break;
            }

            if (isInCloud){
                playerHealth-=3500;
                isInCloud=false;
            }
            if (playerHealth<=0){
                lastSpell="Cloud";
                break;
            }

                String[] tokens = scanner.nextLine().split(" ");

                String spell = tokens[0];
                int targetRow = Integer.parseInt(tokens[1]);
                int targetCol = Integer.parseInt(tokens[2]);

                lastSpell=spell;
                boolean isPlayerDamage = checkPlayerIsHit(playerPos[0],playerPos[1], targetRow, targetCol);
                int damageDone = 0;
                if (isPlayerDamage) {
                    if (spell.equals("Cloud")) {
                        damageDone = 3500;
                    } else {
                        damageDone = 6000;
                    }
                }
                boolean canMove = playerCanMove(playerPos, targetRow, targetCol);


                if (!canMove) {
                    playerHealth -= damageDone;
                    if (spell.equals("Cloud")){
                        isInCloud=true;
                    }
                }
            }
        if (bossHealth>0){
            System.out.printf("Heigan: %.2f%n",bossHealth);
            lastSpell=lastSpell.equals("Cloud")?"Plague Cloud":lastSpell;
            System.out.printf("Player: Killed by %s%n",lastSpell);
        }else {
            System.out.println("Heigan: Defeated!");
            System.out.printf("Player: %d%n",playerHealth);
        }
        System.out.printf("Final position: %d, %d",playerPos[0],playerPos[1]);
    }


    private static boolean playerCanMove(int[] position, int row, int col) {

        boolean canMove = true;
        int r = position[0];
        int c = position[1];
        if (r == row && c == col) {
            canMove = false;
        } else if (isInBounds(r - 1, c) && !checkPlayerIsHit(r-1,c,row,col)) {
            r--;
        }else if (isInBounds(r,c+1)&&!checkPlayerIsHit(r,c+1,row,col)){
            c++;
        }else if (isInBounds(r +1, c) && !checkPlayerIsHit(r+1,c,row,col)){
            r++;
        }else if (isInBounds(r , c-1) && !checkPlayerIsHit(r,c-1,row,col)){
            c--;
        }else {
            canMove=false;
        }
        position[0]=r;
        position[1]=c;
        return canMove;
    }

    private static boolean isInBounds(int r, int c) {
        return r >= 0 && r < 15 && c >= 0 && c < 15;
    }

    private static boolean checkPlayerIsHit(int r,int c, int targetRow, int targetCol) {
        return r>= targetRow - 1 && r <= targetRow + 1
                && c >= targetCol - 1 && c <= targetCol + 1;
    }
}
