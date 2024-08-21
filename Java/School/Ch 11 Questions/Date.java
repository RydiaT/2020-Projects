public class Date {
    private int year;
    private int month;
    private int day;
    public Date(){
        year = 0;
        month = 1;
        day = 1;
    }
    public Date(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public int getYear(){
        return year;
    }
    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }
    public void setYear(int year){
        this.year = year;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public void setDay(int day){
        this.day = day;
    }
    public String toString(){
        String yearString = String.valueOf(java.time.Year.of(year));
        String monthString = String.valueOf(java.time.Month.of(month));
        String dayString = String.valueOf(day);

        return String.format("%s %s, %s", dayString, monthString, yearString);
    }
}
