use crate::high_card::Card;
use rand::prelude::*;

#[derive(Clone, Debug)]
pub struct Deck {
    cards: Vec<Card>,
    live_cards: Vec<Card>,
    dead_cards: Vec<Card>,
    // [Player Hand, Opponent Hand]
    hands: Vec<Vec<Card>>,
    player_card: Card,
    opponent_card: Card
}

impl Deck {
    // Decks
    // Standard 52 Card Deck
    pub fn standard() -> Deck {
        let mut normal = Vec::new();
        let mut live = Vec::new();

        for i in 1..=4 {
            for j in 1..=14 {
                normal.push(Card::full(j, i));
                live.push(Card::full(j, i));
                
            }
        }

        Deck { cards: normal, live_cards: live, dead_cards: Vec::new(), hands: vec!(vec!(), vec!()), player_card: Card::blank(0), opponent_card: Card::blank(0) }
    }
    
    // Only Even Cards (2-Ace)
    pub fn evens() -> Deck {
        let mut normal = Vec::new();
        let mut live = Vec::new();
        
        for i in 1..=4 {
            for j in (2..=14).step_by(2) {
                normal.push(Card::full(j, i));
                live.push(Card::full(j, i));
            }
        }
        
        Deck { cards: normal, live_cards: live, dead_cards: Vec::new(), hands: vec!(vec!(), vec!()), player_card: Card::blank(0), opponent_card: Card::blank(0) }
    }
    
    // Only Odd Cards (Ace - King)
    pub fn odds() -> Deck {
        let mut normal = Vec::new();
        let mut live = Vec::new();
        
        for i in 1..=4 {
            for j in (1..=13).step_by(2) {
                normal.push(Card::full(j, i));
                live.push(Card::full(j, i));
            }
        }
        
        Deck { cards: normal, live_cards: live, dead_cards: Vec::new(), hands: vec!(vec!(), vec!()), player_card: Card::blank(0), opponent_card: Card::blank(0) }
    }
    
    // Only low (Ace-4) and high (10-Ace) Cards
    pub fn cursed() -> Deck {
        let mut normal = Vec::new();
        let mut live = Vec::new();
        
        for i in 1..=4 {
            for j in 1..=4 {
                normal.push(Card::full(j, i));
                live.push(Card::full(j, i));
            }
        }
        
        for i in 1..=4 {
            for j in 10..=14 {
                normal.push(Card::full(j, i));
                live.push(Card::full(j, i));
            }
        }
        
        Deck { cards: normal, live_cards: live, dead_cards: Vec::new(), hands: vec!(vec!(), vec!()), player_card: Card::blank(0), opponent_card: Card::blank(0) }
    }
    
    // 52 Randomly Generated Cards
    pub fn random() -> Deck {
        let mut rng = thread_rng();
        let mut normal = Vec::new();
        let mut live = Vec::new();
        
        for _ in 0..=52 {
            let rank = rng.gen_range(1..=14);
            let suit = rng.gen_range(1..=4);
            
            normal.push(Card::full(rank, suit));
            live.push(Card::full(rank, suit));
        }
        
        Deck { cards: normal, live_cards: live, dead_cards: Vec::new(), hands: vec!(vec!(), vec!()), player_card: Card::blank(0), opponent_card: Card::blank(0) }
    }

    // Custom - Built from a Vec of Cards
    pub fn custom(custom: Vec<Card>) -> Deck {
        let mut normal = Vec::new();
        let mut live = Vec::new();

        for card in custom {
            normal.push(card);
            live.push(card);
        }

        Deck { cards:normal, live_cards:live, dead_cards: Vec::new(), hands: vec!(vec!(), vec!()), player_card: Card::blank(0), opponent_card: Card::blank(0) }
    }

    pub fn size(&self) -> i32 {
        self.live_cards.len() as i32
    }

    pub fn print(self){
        for card in self.cards {
            card.print();
        }
    }

    pub fn show(self) {
        for card in self.live_cards {
            card.show();
        }
    }

    pub fn discards(&self) {
        for card in &self.dead_cards {
            card.show();
        }
    }

    pub fn deal(&mut self, n: i32) -> Vec<Card> {
        let mut hand = Vec::new();

        for _ in 1..=n{
           if !self.live_cards.is_empty() {
                let dealt = self.live_cards.pop().unwrap();
                self.dead_cards.push(dealt);

                hand.push(dealt)
           } else {
                println!("Draw Pile Empty!");
                hand.push(Card::blank(0))
            }
        }

        hand.clone()
    }
    
    pub fn shuffle(&mut self) {
        let mut rng = thread_rng();
        
        for i in 0..self.live_cards.len() {
            let index = rng.gen_range(0..self.live_cards.len());
            let temp = self.live_cards[i];
            self.live_cards[i] = self.live_cards[index];
            self.live_cards[index] = temp;
        }
    }

    pub fn generate_hands(&mut self) {
        let player = self.deal(5);
        let opponent = self.deal(5);
        self.hands[0] = player;
        self.hands[1] = opponent;
    }

    pub fn show_hand(&self, id: usize){
        let temp = Deck::custom(self.hands[id].clone());

        temp.show()
    }
    
    pub fn discard_card(&mut self, card: Card, id: usize) {
        let mut hand_index = 30;
        let mut discard_index = 30;
        
        for i in 0..self.hands[id].len() {
            if self.hands[id][i].equals(card) {
                // println!("Card found at index {}. Player: {}", i, id);
                hand_index = i;
            }
        }
        
        if hand_index != 30 {
            for j in 0..self.dead_cards.len() {
                if self.dead_cards[j].equals(card) {
                    discard_index = j;
                }
            }
            
            self.hands[id].remove(hand_index);
            self.live_cards.push(self.dead_cards[discard_index]);
            self.dead_cards.remove(discard_index);
        }
    }
    
    pub fn set_card(&mut self, id: usize, val: i32, suit: i32) {
        let test_card = Card::full(val, suit);
        self.discard_card(test_card, id);
        
        if id == 0 {
            self.player_card = test_card;
        } else {
            self.opponent_card = test_card;
        }
        
    }
    
    pub fn choose_random_card(&self) -> Vec<i32> {
        let cards = self.hands[1].clone();
        let card = Deck::get_card(cards);
        
        println!("\nOpponent's Card: ");
        card.show();
        
        let mut out : Vec<i32> = Vec::new();
        
        out.push(card.get_val());
        out.push(card.get_suit());
        
        out
    }
    
    pub fn play(&self) -> i32 {
        self.player_card.compare(self.opponent_card)
    }
    
    fn get_card(cards: Vec<Card>) -> Card {
        let mut rng = thread_rng();
        let index = rng.gen_range(0..cards.len());
    
        cards[index]
    }
    
    pub fn check_empty_hand(&self, id: usize) -> bool {
        return self.hands[id].is_empty();
    }
}