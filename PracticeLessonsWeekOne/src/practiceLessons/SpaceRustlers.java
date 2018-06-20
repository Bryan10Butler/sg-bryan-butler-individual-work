package practiceLessons;

public class SpaceRustlers {
    public static void main(String[] args) {

        int spaceships = 10;
        int aliens = 25;
        int cows = 100;

        //IF evaluates a condition as true or false and else acts as the output if IF is false
        //else if will evaluate if a condition if true or false

        if (aliens > spaceships) {
            System.out.println("Vrroom, vroom! Let's get going!");
        } else {
            System.out.println("There aren't enough green guys to drive these ships!");
        }

        if (cows == spaceships) {
            System.out.println("Wow, way to plan ahead! JUST enough room for all these walking hamburgers!");

            //removing the else will still print because the condition is true and either IF or ELSE IF will eval
            //true or false
        } else if (cows > spaceships) {
            System.out.println("Dangit! I don't know how we're going to fit all these cows in here!");
        } else {
            System.out.println("Too many ships! Not enough cows");
        }
        if (aliens < cows) {
            System.out.println("Hurrah, we've got the grub! Hamburger party on Alpha Centauri!\" if the aliens outnumber the cows but if the cows equal or outnumber the aliens print out \"Oh no! The herds got restless and took over! Looks like _we're_ hamburger now!");
        } else {
            System.out.println("Space Balls!");
        }

    }
}
