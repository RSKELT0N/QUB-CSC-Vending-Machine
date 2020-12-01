package Part2;

import java.util.Scanner;

/**
 * This class represents ...a console-based menu
 *
 * @author
 * @version V1.0
 */
public class Menu {
    private String options[];    // array of strings representing user options
    private String title;        // menu title
    private Scanner input;        // for KB input

    /**
     * Constructor for class
     *
     * @param title   - the menu title
     * @param options - the options for user selection
     */
    public Menu(String title, String options[]) {
        this.title = title;
        copyOptions(options);
        input = new Scanner(System.in);
    }

    /**
     * Requests and read a user choice
     *
     * @return - the user choice
     */
    public int getChoice() {
        display();
        System.out.print("\n\nEnter choice: ");
        int choice = Integer.parseInt(input.nextLine());
        return choice;
    }

    /**
     * displays the menu title followed by the user
     * option for selection
     */
    public void display() {
        if (title != null && options != null) {

            //Title
            System.out.println();
            for (int i = 0; i < 32 + title.length(); i++) {
                System.out.print("-");
                if (i == 0 || i == title.length() + 31)
                    System.out.print("+");
            }

            System.out.format("\n%16s%s%16s", " ", title, " ");
            System.out.println();
            for (int i = 0; i < 32 + title.length(); i++) {
                System.out.print("-");
                if (i == 0 || i == title.length() + 31)
                    System.out.print("+");
            }

            //Body
            int len = title.length() + 32;
            for (int j = 0; j < options.length; j++) {
                System.out.print("\n|");
                System.out.print("  " + (j + 1) + ". " + options[j]);
                for (int i = 0; i < 32 + title.length(); i++) {
                    if (j < 6) {
                        System.out.print(" ");
                        if (i == 26 + title.length() - options[j].length())
                            System.out.print("|");
                    }
                }
            }

            //Footer
            System.out.println("");
            for(int i = 0; i < 32+title.length()+2; i++)
                System.out.print("-");
        }
    }

    /**
     * will initialise the options array by copying
     * contents of data
     *
     * @param data - array of Strings - options for user to select from
     */
    private void copyOptions(String data[]) {
        if (data != null) {
            options = new String[data.length];
            for (int index = 0; index < data.length; index++) {
                options[index] = data[index];
            }
        } else {
            options = null;
        }
    }

}
