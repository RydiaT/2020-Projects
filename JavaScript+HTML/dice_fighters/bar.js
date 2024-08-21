class Bar {
    constructor(id, MAX_FILL, currentFill, color) {
        this.id = id;

        this.MAX_FILL = MAX_FILL;
        this.currentVal = currentFill;

        this.baseColor = color;
        this.conditionalColor = "";
        this.color = "";

        this.conditional = -30;

        this.element = document.getElementById(this.id);

        this.updateColor();
    }

    setCurrent(val) {
        this.currentVal = val;
    }

    setMax(val) {
        this.MAX_FILL = val;
    }

    setBaseColor(color) {
        this.baseColor = color;

        this.updateColor();
    }

    updateColor() {
        let currentFill = (this.currentVal / this.MAX_FILL) * 100;

        if(this.conditional > 0 && currentFill <= this.conditional) {
            this.color = this.conditionalColor;
        } else {
            this.color = this.baseColor;
        }

        this.element.className = "progress-bar bg-" + this.color;
    }

    setConditionalColoring(threshold, color) {
        this.conditionalColor = color;
        this.conditional = threshold;
    }

    update() {
        let currentFill = (this.currentVal / this.MAX_FILL) * 100;
        this.element.style.width = currentFill + "%";
        this.element.innerHTML = currentFill + "%";

        this.updateColor();
    }
}