function run() {
    toLog("Running!");

    for (let i = 0; i < 50; i++) {
        toLog("Test " + i);
    }
}

function toLog(text) {
    log.innerHTML += "> " + text + "<br>";

    log_container.scrollTop = log_container.scrollHeight;
}