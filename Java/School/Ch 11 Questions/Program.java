public class Program {
    public static void main(String[] args){
        // Tile Stuff
        System.out.println("Tile Testing:");
        Tile bob = new Tile("Z", 10);
        bob.printTile();
        System.out.println("\n");
        // Date Stuff
        System.out.println("Date Testing:");
        Date birthday = new Date(2006, 11, 17);
        System.out.println(birthday);
        System.out.println("\n");
        // Rational Stuff
        System.out.println("printRational Testing:");
        Rational john = new Rational(6, 8);
        john.printRational();
        System.out.println("\n");
        // Negation Testing
        System.out.println("Negation Testing:");
        john.negate();
        john.printRational();
        System.out.println("\n");
        // Inversion Testing
        System.out.println("Inversion Testing:");
        john.invert();
        john.printRational();
        System.out.println("\n");
        // toDouble Testing
        System.out.println("toDouble Testing:");
        System.out.println(john.toDouble());
        System.out.println("\n");
        // Reduction Testing
        System.out.println("Reduction Testing:");
        john.reduce();
        john.printRational();
        System.out.println("\n");
        // Addition Testing
        System.out.println("Addition Testing:");
        System.out.println("2/6 + 3/2");
        Rational a = new Rational(2, 6);
        Rational b = new Rational(3, 2);
        System.out.println(a.add(b));
        System.out.println("\n");
    }
}
