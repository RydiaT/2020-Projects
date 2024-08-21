use std::io;
use crate::deck::Deck;

pub fn read_console() -> String {
    let mut input = String::new();

    io::stdin()
        .read_line(&mut input)
        .expect("Failed to read line.");

    input
}

pub fn get_player_card(deck: &mut Deck) -> Vec<i32> {
    if deck.check_empty_hand(0) {
        println!("You ran out of cards! Have some more!");
        deck.generate_hands();
    } else if deck.check_empty_hand(1) {
        println!("Your opponent ran out of cards! Have some more!");
        deck.generate_hands();
    }

    if deck.size() <= 10 {
        println!("Whoops, deck's outa cards!");
        let out: Vec<i32> = Vec::new();
        return out
    }

    println!("\nYour Hand:");
    deck.show_hand(0);
    println!("\nPlay What Card? (ex. Queen of Hearts)");
    let input_raw = read_console();
    let input = input_raw.trim();

    if input == "quit" {
        println!("Catch ya on the flip side!");
        let out: Vec<i32> = Vec::new();
        return out
    }

    let chunks: Vec<&str> = input.split(" of ").collect();

    translate_card(chunks)
}

fn translate_card(card_data: Vec<&str>) -> Vec<i32> {
    let mut out = Vec::new();
    let val = card_data[0].to_lowercase();
    let check_val = val.trim();
    let suit = card_data[1].to_lowercase();
    let check_suit = suit.trim();

    match check_val {
        "two" => out.push(2),
        "three" => out.push(3),
        "four" => out.push(4),
        "five" => out.push(5),
        "six" => out.push(6),
        "seven" => out.push(7),
        "eight" => out.push(8),
        "nine" => out.push(9),
        "ten" => out.push(10),
        "jack" => out.push(11),
        "queen" => out.push(12),
        "king" => out.push(13),
        "ace" => out.push(14),
        _ => out.push(0)
    }

    match check_suit {
        "hearts" => out.push(1),
        "diamonds" => out.push(2),
        "clubs" => out.push(3),
        "spades" => out.push(4),
        _ => out.push(0)
    }

    out
}

pub fn get_deck() -> Deck {
    println!("What deck do you want?\n1: Standard\n2: Random\n3: Evens\n4: Odds\n5: Cursed");
    let input_raw = read_console();
    let input = input_raw.trim();
    let mut deck: Deck;

    match input {
        "1" =>{
            deck = Deck::standard();
            deck.shuffle();
        },
        "2" => {
            deck = Deck::random();
            println!("Generated Deck:");
            deck.clone().show();
        },
        "3" => {
            deck = Deck::evens();
            deck.shuffle();
        },
        "4" => {
            deck = Deck::odds();
            deck.shuffle();
        },
        "5" => {
            deck = Deck::cursed();
            deck.shuffle();
        },
        _ => panic!("That's no deck!"),
    }

    deck.clone()
}