public class Rational {
    private int numerator;
    private int denominator;
    public Rational(){
        numerator = 0;
        denominator = 1;
    }
    public Rational(int n, int d){
        numerator = n;
        denominator = d;
    }
    public int getNumerator(){
        return numerator;
    }
    public int getDenominator(){
        return denominator;
    }
    public void setNumerator(int num){
        numerator = num;
    }
    public void setDenominator(int denom){
        denominator = denom;
    }
    public void printRational(){
        System.out.println(this);
    }
    public String toString(){
        return String.format("%d / %d", numerator, denominator);
    }
    private int getGCD(int a, int b) {
        if (b==0) return a;
        return getGCD(b,a%b);
    }
    public void negate(){
        numerator *= -1;
    }
    public void invert(){
        int temp = numerator;
        numerator = denominator;
        denominator = temp;
    }
    public double toDouble(){
        return (double) numerator / denominator;
    }
    public void reduce(){
        int gcd = getGCD(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
    }
    public Rational add(Rational target){
        Rational out = new Rational();
        int newNum = (numerator * target.getDenominator()) + (denominator * target.getNumerator());
        int newDenom = (denominator * target.getDenominator());
        out.setNumerator(newNum);
        out.setDenominator(newDenom);
        out.reduce();
        return out;
    }
}
