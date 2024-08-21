mod card;
mod deck;
mod high_card;
mod helpers;
mod blackjack;

use rand;
use crate::helpers::read_console;

fn main() {
    println!("Hello, world!");
    println!("What do you want to do?\n1: High Card\n2: Black Jack");
    let input_raw = read_console();
    let input = input_raw.trim();

    match input {
        "1" => high_card::run(),
        "2" => blackjack::run(),
        _ => panic!("That's not a game!")
    }
}
