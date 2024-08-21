public class Circle {
    private double radius;
    private double x;
    private double y;

    Circle(double radius){
        this.radius = radius;
        this.x = 0;
        this.y = 0;
    }
    Circle(double radius, double x, double y){
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    public double[] getLocation(){
        return new double[]{this.x, this.y};
    }
    public void setLocation(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getArea(){
        return Math.PI * (this.radius * this.radius);
    }
    public double getRadius(){
        return this.radius;
    }
    public void setRadius(double r){
        this.radius = r;
    }
    public boolean isOverlapping(Circle check){
        double distance = Math.sqrt(Math.pow(this.x - check.x, 2) + Math.pow(this.y - check.y, 2));
        return distance < (this.radius + check.radius);
    }
    public String toString(){
        return String.format("Circle (%.1f Radius, (%.1f, %.1f) Location)", this.radius, this.x, this.y);
    }
}
