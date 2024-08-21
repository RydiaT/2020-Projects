class Creature {
    constructor(name, barID, stats) {
        this.name = name;

        this.max = stats[0];
        this.cur = stats[1];
        this.atk = stats[2];
        this.def = stats[3];
        this.maxEXP = 20;
        this.exp = 10;

        this.hpBar = new Bar(barID, this.max, this.cur, "success");
        this.hpBar.setConditionalColoring(25, "danger");
    }

    toString() {
        return `${this.name} - HP: ${this.cur} / ${this.max}, Attack: ${this.atk}, Defense: ${this.def}, Exp: ${this.exp} / ${this.maxEXP}`;
    }

    isDead() {
        return this.cur <= 0;
    }

    levelUp() {
        if(this.exp >= this.maxEXP) {
            this.exp = (this.exp - this.maxEXP);
        }
    }

    update() {
        this.hpBar.setCurrent(this.cur);
        this.hpBar.update();
    }

    takeDamage(val) {
        this.cur -= val;
        update();
    }
}