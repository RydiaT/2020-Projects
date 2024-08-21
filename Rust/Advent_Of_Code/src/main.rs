use std::fs::File;
use std::io::{self, BufRead, BufReader};
use std::path::Path;
use std::process::exit;

fn main() {
    // // Specify the path to your file
    // let path = "day_1.txt";
    // 
    // // Open the file
    // let file = File::open(&path)?;
    // 
    // // Create a buffered reader
    // let reader = io::BufReader::new(file);
    // 
    // // Initialize a vector to store the numbers
    // let mut nums: Vec<i32> = Vec::new();
    // 
    // // Iterate over each line in the file
    // for line in reader.lines() {
    //     // Parse the line into an integer and add to the vector
    //     if let Ok(num) = line?.parse::<i32>() {
    //         nums.push(num);
    //     } else {
    //         println!("Skipping invalid number.");
    //     }
    // }
    // 
    // 
    // 
    // 
    // if let Some((a,b,c)) = find_numbers(&mut nums) {
    //     println!("{} + {} + {} = 2020", a, b, c);
    //     println!("{} * {} * {} = {}", a, b, c, a*b*c)
    // } else {
    //     println!("Now match found")
    // }
    // 
    // Ok(())
    println!("{:?}", get_input())
}

// Methods
fn get_input() -> Vec<String>{
    let path = "day_1.txt";
    let file = File::open(&path).expect("Couldn't Read");
    let reader = io::BufReader::new(file);
    let mut vec: Vec<String> = Vec::new();
    for line in reader.lines() {
        match line {
            Ok(line) => vec.push(line),
            Err(e) => eprintln!("Error reading line: {}", e)
        }
    }
    
    return vec
}

/*
fn strArrToInt(Vec<String> vec) -> Vec<i32> {
    let int_vec: Vec<i32> = Vec::new();
    while vec.len() > 0 {
        
    }
}*/


fn worse_find_numbers(nums: Vec<i32>) -> i32 {
    let mode = 2;

    if mode == 1 {
        for num in &nums {
            let temp = 2020 - num;

            if nums.contains(&temp) {
                let result = num * temp;
                println!("{} * {} = {}", num, temp, result);
                return result;
            }
        }
    } else {
        for num in &nums {
            for other in &nums {
                let temp = 2020 - (num + other);

                if nums.contains(&temp) {
                    let result = num * other * temp;
                    println!("{} * {} * {} = {}", num, other, temp, result);
                    return result;
                }
            }
        }
    }

    return -1;
}


fn find_numbers(nums: &mut Vec<i32>) -> Option<(i32, i32, i32)> {
    nums.sort();
    for i in 0..nums.len() - 2 {
        let mut left = i + 1;
        let mut right = nums.len() - 1;

        while left < right {
            let sum = nums[i] + nums[left] + nums[right];
            if sum == 2020 {
                return Some((nums[i], nums[left], nums[right]));
            } else if sum < 2020 {
                left += 1;
            } else {
                right -= 1;
            }
        }
    }
    None
}

