public class broski {
    private fella[] fellas;

    public broski(fella[] fellas) {
        this.fellas = fellas;
    }

    public broski(int fellaPop) {
        this.fellas = new fella[fellaPop];

        for(int i = 0; i <= fellaPop - 1; i++) {
            int rand1 = (int) Math.floor(Math.random() * 10);
            int rand2 = (int) Math.floor(Math.random() * 20);
            int rand3 = (int) Math.floor(Math.random() * 20);
            fella temp = new fella(i + 1, rand1, rand2, rand3);

            fellas[i] = temp;
        }
    }
}
