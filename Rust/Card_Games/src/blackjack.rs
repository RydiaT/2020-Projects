use crate::card::Card;
use crate::deck::Deck;
use crate::helpers::{get_deck, read_console};

pub fn run() {
    let mut deck = get_deck();
    println!();

    let mut hand: Vec<Card> = deck.deal(1);
    hand = hit(&mut hand, &mut deck, 0);

    play(&mut hand, &mut deck);
}

fn play(mut hand: &mut Vec<Card>, mut deck: &mut Deck) {
    let mut playing = true;

    while playing {
        if !hand.is_empty() {
           println!("What do you want to do?\nHit\nStand\nSplit"); 
        } else {
            println!("Please hit enter.")
        }
        
        let input_raw = read_console();
        let mut input = input_raw.trim();
        
        if hand.is_empty() {
            input = "stand"
        }
        
        match input.to_lowercase().as_str() {
            "hit" => {
                *hand = hit(&mut hand, &mut *deck, 0);

                if hand.is_empty() {
                    dealer(&mut deck);
                }
            },
            "stand" => {
                let deal = dealer(deck);

                if total(hand.clone()) > deal {
                    println!("You won!");
                    playing = false;
                } else if total(hand.clone()) < deal {
                    println!("You lost.");
                    playing = false;
                } else {
                    println!("You tied!");
                    playing = false;
                }
            },
            "split" => {
                let mut temp = split(&hand);
                play(&mut temp[0], &mut deck);
                play(&mut temp[1], &mut deck);
            }
            _ => panic!("That's not an option!")
        }
    }
}

fn hit(stack: &mut Vec<Card>, deck: &mut Deck, id: i32) -> Vec<Card> {
    stack.push(deck.deal(1)[0]);

    println!();
    show_hand(&stack);

    if bust(stack.clone()) {
        if id == 0 {
            println!("You Busted!");
        } else {
            println!("Dealer Busted!");
        }
        return Vec::new();
    } else {
        stack.clone()
    }
}

fn total(mut stack: Vec<Card>) -> i32 {
    let mut out = 0;

    for card in stack {
        out += card.get_val();
    }

    out
}

fn bust(stack: Vec<Card>) -> bool {
    return total(stack) > 21;
}

fn can_split(stack: &Vec<Card>) -> bool {
    return stack.len() == 2 && stack[0].get_val() == stack[1].get_val()
}

fn split(stack: &Vec<Card>) -> Vec<Vec<Card>> {
    if can_split(stack) {
        let out = vec!(vec!(stack[0]), vec!(stack[1]));

        out.clone()
    } else {
        panic!("Couldn't Split! Stack: {:?}", stack)
    }
}

fn show_hand(stack: &Vec<Card>) {
    for card in stack {
        card.show();
    }
    println!("Total: {}", total(stack.clone()))
}

fn dealer(mut deck: &mut Deck) -> i32 {
    let mut hand: Vec<Card> = Vec::new();

    while !(total(hand.clone()) > 17) {
        hand = hit(&mut hand, &mut *deck, 1);

        if hand.is_empty() {
            break;
        }
    }

    if bust(hand.clone())  || hand.is_empty() {
        return 0
    } else {
        println!();
        show_hand(&hand);
        total(hand.clone())
    }
}