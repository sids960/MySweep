//Siddharth Singh

import java.util.*;
public class MySweep {
    String arr1[][];
    String arr2[][];

    public void generate(int size, int bomb) {

        arr1 = new String[size+4][size+4];
        arr2 = new String[size+4][size+4];
        for(int i = 2; i<(arr1.length-2); i++)
        {
            arr1[0][i] = arr1[i][0] = Integer.toString(i-1);
        }

        for(int i = 1; i<=(arr1.length-2); i++) {
            for(int j = 1; j<=(arr1.length-2); j++) {
                arr1[i][j] = "0";
            }
        }

        int bombcount = 0;
        while(bombcount < bomb) {
            int bomb1 = (int) Math.floor((Math.random())*(arr1.length-5)) + 2;
            int bomb2 = (int) Math.floor((Math.random())*(arr1.length-5)) + 2;
            if(arr1[bomb1][bomb2].equals("X"))
                continue;
            else {
                arr1[bomb1][bomb2] = "X";
                bombcount++;
            }
        }

        for(int i = 2; i<(arr1.length-2); i++) {
            for(int j = 2; j<(arr1.length-2); j++) {
                int temp = 0;
                if(arr1[i][j].equals("X"))
                    continue;
                if(arr1[i-1][j-1].equals("X"))
                    temp++;
                if(arr1[i-1][j].equals("X"))
                    temp++;
                if(arr1[i-1][j+1].equals("X"))
                    temp++;
                if(arr1[i][j-1].equals("X"))
                    temp++;
                if(arr1[i][j+1].equals("X"))
                    temp++;
                if(arr1[i+1][j-1].equals("X"))
                    temp++;
                if(arr1[i+1][j].equals("X"))
                    temp++;
                if(arr1[i+1][j+1].equals("X"))
                    temp++;
                arr1[i][j] = Integer.toString(temp);
            }
        }

        for(int i = 0; i<(arr2.length); i++) {
            for(int j = 0; j<(arr2.length); j++) {
                arr2[i][j] = arr1[i][j];
            }
        }


        for(int i = 2; i<(arr2.length-2); i++) {
            for(int j = 2; j<(arr2.length-2); j++) {
                arr2[i][j] = "□" ;
            }
        }


	/* MySweep testobj = new MySweep();
	testobj.display(arr1); */
    }

    public void game(int size, int bomb)
    {
        System.out.println("Welcome to MySweep!");
        System.out.println("How to Play: To mine type the row number (left) and then type the column number (top).");
        System.out.println("Press F and then enter the coordinates to flag a block.");
        System.out.println("Press U and then enter the coordinates to unflag  block.");
        System.out.println("Clear all blocks and flag all mines to win the game!");
        System.out.println();
        System.out.println("Generating...");
        System.out.println();
        MySweep obj = new MySweep();
        obj.display(arr2);

        Scanner sc = new Scanner(System.in);
        int n = 0;
        int norm = (size * size) - bomb;

        int b = 0;

        while(n < norm || b < bomb) {
            String a = sc.nextLine();
            char Fcheck = Character.toUpperCase(a.charAt(0));
            char Ucheck = Character.toUpperCase(a.charAt(0));


            if(Fcheck == 'F') {
                int x = a.charAt(1) - '0';
                int y = a.charAt(2) - '0';
                x++;
                y++;
                arr2[x][y] = "■";
                obj.display(arr2);
                b++;


                continue;
            }

            if(Ucheck == 'U') {
                int x = a.charAt(1) - '0';
                int y = a.charAt(2) - '0';
                x++;
                y++;
                arr2[x][y] = "□";
                obj.display(arr2);
                b--;


                continue;
            }

            int x = Integer.parseInt(a) / 10;
            int y = Integer.parseInt(a) % 10;
            x++;
            y++;

            if(arr2[x][y].equals("■"))
            {
                System.out.println("Cannot mine as block is flagged.");
                System.out.println();
                System.out.println();
                continue;
            }

            if(arr1[x][y].equals("X")) {
                arr2[x][y] = arr1[x][y];
                obj.display(arr2);
                System.out.println("Mine Explosion!");
                System.out.println("Game Over");
                System.exit(0);
            }

            else {

                arr2[x][y] = arr1[x][y];
    	/* if(arr2[x][y].equals("0")) {

    		if(arr1[x-1][y-1].equals("0")) {
    			arr2[x-1][y-1] = arr1[x-1][y-1];
				n++;
    		}
			if(arr1[x-1][y].equals("0")) {
				arr2[x-1][y] = arr1[x-1][y];
				n++;
			}
			if(arr1[x-1][y+1].equals("0")) {
				arr2[x-1][y+1] = arr1[x-1][y+1];
				n++;
			}
			if(arr1[x][y-1].equals("0")) {
				arr2[x][y-1] = arr1[x][y-1];
				n++;
			}
			if(arr1[x][y+1].equals("0")) {
				arr2[x][y+1] = arr1[x][y+1];
				n++;
			}
			if(arr1[x+1][y-1].equals("0")) {
				arr2[x+1][y-1] = arr1[x+1][y-1];
				n++;
			}
			if(arr1[x+1][y].equals("0")) {
				arr2[x+1][y] = arr1[x+1][y];
				n++;
			}
			if(arr1[x+1][y+1].equals("0")) {
				arr2[x+1][y+1] = arr1[x+1][y+1];
				n++;
			}

    		*/

            }
            obj.display(arr2);
            n++;
        }
        System.out.println("Mine Cleared!");
        System.out.println("You Win!");


    }

    public void display(String a[][])
    {
        for(int i = 0; i<(a.length); i++) {
            for(int j = 0; j<(a.length); j++) {
                if(a[i][j]==null) {
                    System.out.print("   ");
                    continue;
                }
                if((i == 1)||(j == 1)||(i == a.length-2)||(j == a.length-2))
                {
                    System.out.print("  ");
                    continue;
                }
                System.out.print(a[i][j] + "   ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        MySweep obj2 =new MySweep();
        obj2.generate(9, 10);
        obj2.game(9, 10);
    }
}