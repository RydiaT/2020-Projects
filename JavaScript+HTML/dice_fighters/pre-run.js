let player;
let enemy;
let enemyName;
let log;
let log_container;

function pre_run() {
    player = new Creature("You", "playerHP", [25, 25, 5, 7]);
    player.expBar =  new Bar("expBar", player.maxEXP, player.exp, "warning");

    enemy = new Creature("Blank", "enemyHP", [30, 30, 10, 10]);
    enemyName = document.getElementById("enemyName");

    log = document.getElementById("log");
    log_container = document.getElementById("log-container");
}

function update() {
    console.log(player.toString());
    player.update();

    console.log(enemy.toString());
    enemy.update();

    enemyName.innerHTML = enemy.name;

    player.expBar.update();
}