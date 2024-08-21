use std::io;

mod lying_calc;
mod high_card;

fn main() {
    println!("Hello, world!");
    println!("What to run?\n1: Very Helpful Calculator\n2: High Card");
    let input_raw = read_console();
    let input = input_raw.trim();

    if input == "1" {
        lying_calc::run();
    } else if input == "2"{
        high_card::run();
    } else {
        panic!("Not a runnable program.");
    }

}

fn read_console() -> String {
    let mut input = String::new();

    io::stdin()
        .read_line(&mut input)
        .expect("Failed to read line.");

    input
}
