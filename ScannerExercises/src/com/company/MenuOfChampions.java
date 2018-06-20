package com.company;

public class MenuOfChampions {
    public static void main(String[] args) {
        String item1Name = "Slice of Big Rico Pizza ";
        String item2Name = "Invisible Strawberry Pie ";
        String item3Name = "Denver Omlet ";
        String resName = "NIGHT VALE";
        float item1Price = 500.00f;
        float item2Price = 2.50f;
        float item3Price = 1.50f;

        System.out.println("  .-.-.   .-.-.   .-.-.   .-.-.   .-.-.   .-.-.   .-.-.   .-.-\n" +
                " / / \\ \\ / / \\ \\ / / \\ \\ / / \\ \\ / / \\ \\ / / \\ \\ / / \\ \\ / / \\\n" +
                "`-'   `-`-'   `-`-'   `-`-'   `-`-'   `-`-'   `-`-'   `-`-'");
        System.out.println("WELCOME TO RESTAURANT " + resName + " !");
        System.out.println("        Today's Menu is...");//figure out what line break is

        System.out.println("  .-.-.   .-.-.   .-.-.   .-.-.   .-.-.   .-.-.   .-.-.   .-.-\n" +
                " / / \\ \\ / / \\ \\ / / \\ \\ / / \\ \\ / / \\ \\ / / \\ \\ / / \\ \\ / / \\\n" +
                "`-'   `-`-'   `-`-'   `-`-'   `-`-'   `-`-'   `-`-'   `-`-'");

        //"%.2f, value that is floating 2 decimals
        System.out.printf("\n" + item1Name + " $" + "%.2f", item1Price);
        System.out.printf("\n" + item2Name + "  $" + "%.2f", item2Price);
        System.out.printf("\n" + item3Name + " $" + "%.2f", item3Price);
    }
}
