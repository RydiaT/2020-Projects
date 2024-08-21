public class Comparator {
    private int state = -1;
    private int val1;
    private int val2;

    public Comparator(int val1, int val2){
        this.val1 = val1;
        this.val2 = val2;

        check();
    }

    public void check() {
        if(val1 == val2) {
            state = 0;
        } else if (val1 > val2) {
            state = 1;
        } else {
            state = 2;
        }
    }

    public void changeValue(int id, int val) {
        if (id == 1) {
            val1 = val;
        } else {
            val2 = val;
        }

        check();
    }

    public boolean greater() {
        return this.state == 1;
    }

    public boolean lesser() {
        return this.state == 2;
    }

    public boolean equals() {
        return this.state == 0;
    }

    public String toString() {
        return String.format("%d, &d: %d", val1, val2, state);
    }
}
