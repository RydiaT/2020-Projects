// Check if we're on the graphing calculator page
if (window.location.href.includes("desmos.com/calculator")) {
    // Wait until the Desmos calculator object is available
    window.addEventListener("load", () => {
        if (window.Calc) {
            // Change axis colors through the Desmos API
            Calc.updateSettings({
                xAxisColor: "white",  // Change x-axis to white
                yAxisColor: "white",  // Change y-axis to white
            });
            console.log("Axis colors updated.");
        } else {
            console.error("Desmos Calc object not found.");
        }
    });
}
