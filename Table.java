package Part2;

/**
 *  Author: Ryan Skelton, 40266093
 *
 *  This class is used to help me create formatted tables within my Vending machine class
 *  Therefore I can input data as a array, and input this within a table. I have created this to reduce time and keep
 *  data strucured similarly over the program.
 */
public class Table {

    private String title;
    private String title2;
    private String[] data;
    private int spacing;
    private int count;
    private int split;
    private boolean type;
    private int width;

    /**
     * Constructor for table class
     * @param title - Title of the table
     * @param title2 - Second title(optional)
     * @param data - Data being entered into table
     * @param spacing - spaces between title(determine width)
     * @param count - Carries a integer which fixes a problem with width
     * @param split - Determines a specific place of split within the table
     * @param type - Determines a type of table, either true or false.
     */
    public Table(String title, String title2, String[] data, int spacing, int count, int split, boolean type) {
        this.title = title;
        this.title2 = title2;
        this.data = data;
        this.spacing = spacing;
        this.count = count;
        this.type = type;
        this.split = split;
        tableData(title, data, spacing, count);
    }


    /**
     * Method that auto generates the table
     * @param title - title of the table
     * @param data - data being netered
     * @param spacing - Determine the spacing before and after the title.
     * @param count - Carries a integer which fixes a problem with width
     */
    private void tableData(String title, String[] data, int spacing, int count) {

        //Title
        System.out.println();
        for (int i = 0; i < 32 + title.length(); i++) {
            System.out.print("-");
            if (i == 0 || i == title.length() + 31)
                System.out.print("+");
        }

        System.out.format("\n%16s%s%14s", " ", title, " ");
        System.out.println();
        for (int i = 0; i < 32 + title.length(); i++) {
            System.out.print("-");
            if (i == 0 || i == title.length() + 31)
                System.out.print("+");
        }

        //Body
        int len = title.length() + 32;
        for (int j = 0; j < data.length; j++) {
            System.out.print("\n|");
            if (type && j == 0) {
                System.out.print("  " + title2 + "          |");
                System.out.print("\n|");
            }
            if (title.equals("Receipt"))
                this.width = 32;
            else if (title.equals("Vending Machine Items"))
                this.width = 72;
            else
                this.width = 47;
            if (j == split && title2 != null) {
                for (int index = 0; index < this.width + title.length() - data[j].length(); index++) {
                    System.out.print("-");
                    if (index == this.width + title.length() - data[j].length() - 1) {
                        System.out.print("|");
                        System.out.print("\n|  " + data[j]);
                    }
                }
            } else
                System.out.print("  " + data[j]);
            for (int i = 0; i < 32 + title.length(); i++) {
                if (j < data.length + 1) {
                    System.out.print(" ");
                    if (i == 29 + title.length() - data[j].length())
                        System.out.print("|");
                }
            }
        }

        System.out.println("");
        System.out.print(" ");
        for (int i = 0; i < spacing + title.length() + count; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
