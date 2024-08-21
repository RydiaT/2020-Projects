use std::fmt::*;
use rand::prelude::*;

#[derive(Clone, Copy, Default, Debug)]
pub struct Card {
    value: Value,
    suit: Suit,
}

#[derive(Clone, Copy, Debug, Default, PartialOrd, PartialEq,)]
enum Suit {
    #[default]
    Blank,
    Hearts,
    Diamonds,
    Clubs,
    Spades
}

impl Suit {
    fn new(id: i32) -> Self {
        match id {
            0 => Self::Blank,
            1 => Self::Hearts,
            2 => Self::Diamonds,
            3 => Self::Clubs,
            4 => Self::Spades,
            _ => panic!("Suit's weird. {}", id)
        }
    }
}
#[derive(Clone, Copy, Debug, PartialOrd, PartialEq, Default)]
enum Value {
    #[default]
    Blank = 0,
    Two = 2,
    Three = 3,
    Four = 4,
    Five = 5,
    Six = 6,
    Seven = 7,
    Eight = 8,
    Nine = 9,
    Ten = 10,
    Jack = 11,
    Queen = 12,
    King = 13,
    Ace = 14,
}

impl Value {
    fn new(id: i32) -> Self {
        match id {
            0 => Self::Blank,
            1 => Self::Ace,
            2 => Self::Two,
            3 => Self::Three,
            4 => Self::Four,
            5 => Self::Five,
            6 => Self::Six,
            7 => Self::Seven,
            8 => Self::Eight,
            9 => Self::Nine,
            10 => Self::Ten,
            11 => Self::Jack,
            12 => Self::Queen,
            13 => Self::King,
            14 => Self::Ace,
            _ => panic!("Value's Weird. {}", id)
        }
    }
}

impl Card {
    pub fn full (val: i32, id: i32) -> Card {
        Card {value:Value::new(val), suit:Suit::new(id)}
    }

    pub fn blank(v: i32) -> Card {
        Card {value:Value::new(v), suit:Suit::new(0)}
    }

    pub fn print(&self){
        println!("Value: {:?}\nSuit: {:?}\n", self.value as i32, self.suit as i32)
    }

    pub fn show(&self){
        println!("{:?} of {:?}", self.value, self.suit)
    }

    pub fn auto_fill(&mut self){
        let mut rng = thread_rng();

        let suit_id = rng.gen_range(1..=4);

        self.suit = Suit::new(suit_id);
    }

    pub fn get_val(&self) -> i32 {
        self.value as i32
    }

    pub fn get_suit(&self) -> i32 {
        self.suit as i32
    }

    pub fn equals(&self, other: Card) -> bool {
        if self.value == other.value && self.suit == other.suit {
            true
        } else {
            false
        }
    }

    pub fn compare(&self, other: Card) -> i32 {
        if self.value > other.value {
            1
        } else if self.value < other.value {
            0
        } else if self.value == other.value {
            2
        } else {
            println!("Not to sure what happened.");
            3
        }
    }
}