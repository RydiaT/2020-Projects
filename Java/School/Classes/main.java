public class main {
    public static void main(String[] args){
//        Cup bob = new Cup();
//        Cup joe = new Cup(32);
//
//        System.out.println(bob.toString());
//        System.out.println(joe.toString());
//
//        bob.setCapacity(60);
//        joe.fill(100);
//        System.out.println(bob.toString());
//        System.out.println(joe.toString());
//
//        bob.fill(30);
//
//        joe.pourInto(bob, 45.7);
//        System.out.println(bob.toString());
//        System.out.println(joe.toString());

//        Circle billy = new Circle(20);
//        Circle joanne = new Circle(10, 6, 7);
//
//        System.out.println(billy);
//        System.out.println(joanne);
//        System.out.println(billy.isOverlapping(joanne));

//        ArrayList nums = new ArrayList(new int[]{2,56,32,7,1,9,3});
//        nums.sort();
//        System.out.println(nums);
        Account wilfred = new Account(30);
        Account johnny = new Account(0, "Johnny");

        System.out.println(wilfred);
        System.out.println(johnny);

        System.out.println(wilfred.transfer(johnny, 20));
        System.out.println(johnny.withdraw(30));
    }
}
