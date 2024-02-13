use std::fs;

pub fn day1_run() {

    let input = fs::read_to_string("input.txt").expect("ERR");

    let mut sum_part_two: u64 = 0;
    let mut sum_part_one: u64 = 0;
    for line in input.lines(){
        // part 1
        let parsed_numbers_part_1 = parse_numbers(&line);
        let number: String = format!("{}{}", parsed_numbers_part_1.0, parsed_numbers_part_1.1);
        sum_part_one += number.parse::<u64>().unwrap_or(0);

        // part 2
        let tuple = text_to_numbers_functional(line);
        let tuple = parse_numbers(tuple.as_str());
        let number: String = format!("{}{}", tuple.0, tuple.1);
        sum_part_two += number.parse::<u64>().unwrap_or(0);
    }
    println!("Part 1 = {sum_part_one}\nPart 2 = {sum_part_two}");
}

fn parse_numbers(str: &str) -> (char, char){
    let mut tuple: (char, char) = ('0', '0');
    for letter in str.chars(){
        if letter.is_digit(10){
            tuple.0 = letter;
            break;
        }
    }
    for letter in str.chars().rev(){
        if letter.is_digit(10){
            tuple.1 = letter;
            break;
        }
    }
    tuple
}
/*doesn't work because it replaces the strings. (oneight = 1, should be 18 though)
fn text_to_numbers(input: &str) -> String {
    let numbers = [("one", "one1one"), ("two", "2"), ("three", "3"), ("four", "4"), ("five", "5"),
        ("six", "6"), ("seven", "7"), ("eight", "8"), ("nine", "9")];
    let mut string: String = String::new();
    for letter in input.chars() {
        string.push(letter);
        for (number_written, number) in numbers {
            if string.contains(&number_written) && !string.contains(format!("{}{}{}", &number, &number_written, &number).as_str()) {
                string = string.replace(&number_written, &number);
            }
        }
    }
    return String::from(string);
}*/


fn text_to_numbers_functional(input: &str) -> String {
    let numbers = [("one", "1"), ("two", "2"), ("three", "3"), ("four", "4"), ("five", "5"),
        ("six", "6"), ("seven", "7"), ("eight", "8"), ("nine", "9")];

    let mut string: String = String::from(input);
        for(number_written, number) in numbers{
            if string.contains(&number_written){
                let mut insert_idx: usize = 0;
                loop {

                    match string[insert_idx..].find(&number_written){
                        None => break,
                        Some(val) => insert_idx = insert_idx + val,
                    }
                    println!("{insert_idx}");

                    string.insert_str(insert_idx +1, &number);

                    println!("{}", string);
                }
            }
        }
    string
}

fn main() {}