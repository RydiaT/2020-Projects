use std::io;
use rand::prelude::*;

pub fn run() {
    let prefixes = ["You may not know this but ", "You know, I heard that ", "EVERYONE knows that ", "Believe it or not, ", "There exists a universe where ", "", ""];
    let suffixes = [". Source: Trust me bro.", ", according to my mailman.", ", but of course you wouldn't know that.", ". I'm surprised this isn't common knowledge.", ". Do I really need to explain?", ". The proof is left as an exercise to the reader.", "", ""];
    let prefix = get_item(&prefixes);
    let suffix = get_item(&suffixes);

    println!("Give me a math problem! (x +-*/ y)");
    let response = read_console();
    let trimmed_response = response.trim();

    let chunks: Vec<&str> = trimmed_response.split(" ").collect();

    let num1;
    let op;
    let num2;

    if chunks[0] == trimmed_response{
        let symbol_split = try_split(trimmed_response);

        num1 = to_int(symbol_split[0]);
        op = &trimmed_response[symbol_split[0].len()..=symbol_split[0].len()];
        num2 = to_int(symbol_split[1]);
    } else {
        num1 = to_int(chunks[0]);
        op = chunks[1];
        num2 = to_int(chunks[2]);
    }

    println!("{0}{1} {2} {3} = {4}{5}", prefix, num1, op, num2, lie(num1, num2, op), suffix);
}

fn get_max(a: i32, b: i32) -> i32 {
    if a > b{
        a
    } else {
        b
    }
}

fn try_split(thing: &str) -> Vec<&str> {
    let mut split: Vec<&str> = thing.split("+").collect();

    if split[0] == thing {
        split = thing.split("-").collect();

        if split[0] == thing {
            split = thing.split("*").collect();

            if split[0] == thing {
                split = thing.split("/").collect();

                if split[0] == thing {
                    panic!("No split found")
                }
            }
        }
    }

    split
}

fn get_item<'a>(things: &'a [&'a str]) -> &'a str {
    let mut rng = thread_rng();
    let index = rng.gen_range(0..things.len());

    things[index]
}

fn read_console() -> String {
    let mut input = String::new();

    io::stdin()
        .read_line(&mut input)
        .expect("Failed to read line.");

    input
}

fn to_int(thing: &str) -> i32{
    thing.parse().expect("Parse Failed.")
}

fn lie(num1: i32, num2: i32, op: &str) -> i32 {
    let mut rng = thread_rng();

    let mut random_number: i32 = 0;

    if rng.gen_range(0..=100) >= 25 {
        let upper: i32 = (get_max(num1, num2) / 2) + 1;
        random_number = rng.gen_range((upper * -1)..=upper);
    }

    match op {
        "+" => (num1 + num2) + random_number,
        "-" => (num1 - num2) + random_number,
        "*" => (num1 * num2) + random_number,
        "/" => (num1 / num2) + random_number,
        _ => panic!("Invalid Operator!")
    }
}