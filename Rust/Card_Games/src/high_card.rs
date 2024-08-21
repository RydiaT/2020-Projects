use crate::helpers::{get_deck, get_player_card};

pub fn run() {
    let mut deck= get_deck();

    let blank_choice: Vec<i32> = Vec::new();
    let mut player_score = 0;
    let mut op_score = 0;

    deck.generate_hands();

    loop {
        let played_card = get_player_card(&mut deck);

        if played_card != blank_choice {
            deck.set_card(0, played_card[0], played_card[1]);

            let op_card = deck.choose_random_card();
            deck.set_card(1, op_card[0], op_card[1]);

            let result = deck.play_high();

            match result {
                0 => {
                    println!("\nYou lost.");
                    op_score += 1;
                },
                1 => {
                    println!("\nYou won!");
                    player_score += 1;
                },
                2 => println!("\nIt's a tie!"),
                _ => println!("\nSomething went wrong.")
            }

            println!("\nCurrent Standings: {}-{}", player_score, op_score)
        } else {
            break;
        }
    }
}