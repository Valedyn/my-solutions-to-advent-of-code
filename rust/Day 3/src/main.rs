use std::fs;
use std::num::ParseIntError;
use std::ops::Index;

fn main() {
  let string = fs::read_to_string("input.txt").unwrap_or_default();
  //println!("{string}");

  let lines: Vec<&str> = string.split("\n").collect();
let mut total_sum = 0;
  for (pos, line) in lines.iter().enumerate(){
    println!("{:?}", all_available_positions(&line));
    for v in all_available_positions(&line){
      let line_before_sum = adjacent_numbers(lines.get(pos-1).unwrap_or(&""), v);

      let mut sum = 0;
      for num in line_before_sum{
        sum += num;
      }
      println!("{sum}");
      let line_on_sum = adjacent_numbers(lines.get(pos).unwrap_or(&""), v);
      for num in line_on_sum{
        sum += num;
      }

      println!("{sum}");
      let line_after_sum = adjacent_numbers(lines.get(pos+1).unwrap_or(&""), v);

      for num in line_after_sum{
        sum += num;
      }
      println!("{sum}");

    total_sum += sum;

    }
    println!();
  }

  println!("{total_sum}");
}

fn adjacent_numbers(str: &str, pos: usize) -> Vec<usize>{
  let string = String::from(str);


  let mut numbers: Vec<usize> = Vec::new();
  let up_to_pos = &string[..pos];
  let on_pos: char = string[pos.. pos+1].chars().next().unwrap_or(' ');
  let after_pos = &string[pos+1..];
  //println!("{up_to_pos}\n{on_pos}\n{after_pos}");

  // should've just done some funky split stuff instead. only got the idea to do that after finishing this, though. (would've taken way less code)
// before
  let mut numbers_to_add_before: String = String::new();
  for letter in up_to_pos.chars().rev(){
    if letter.is_ascii_digit() {
      numbers_to_add_before.push(letter);
    }else{
      break;
    }
  }
  let numbers_to_add_before: String = numbers_to_add_before.chars().rev().collect();
// after
  let mut numbers_to_add_after: String = String::new();
  for letter in after_pos.chars(){
    if letter.is_ascii_digit() {
      numbers_to_add_after.push(letter);
    }else{
      break;
    }
  }
// onpos
    if on_pos.is_ascii_digit(){
      let mut final_number = String::from(on_pos);
      if numbers_to_add_before.chars().count() > 0{
        final_number = numbers_to_add_before.as_str().to_owned() + &*final_number;
      }

      if numbers_to_add_after.chars().count() > 0{
        final_number.push_str(numbers_to_add_after.as_str());
      }
      match final_number.parse::<usize>(){
        Ok(val) => {numbers.push(val);}
        Err(_) => {}
      }
    }else {
      match numbers_to_add_before.parse::<usize>(){
        Ok(val) => {numbers.push(val);}
        Err(_) => {}
      }
      match numbers_to_add_after.parse::<usize>(){
        Ok(val) => {numbers.push(val);}
        Err(_) => {}
      }
    }

return numbers
}

  fn positions(str: &str, char: char) -> Vec<usize>{
    let mut vec: Vec<usize> = Vec::new();
    let mut last_star_pos: isize = 0;
    let mut star_pos: isize = 0;
    while star_pos != -1{
      star_pos = match str[last_star_pos as usize..].find(char){
        None => -1,
        Some(val) => (val+1) as isize
      };

      if star_pos > -1 {
        last_star_pos += star_pos;
        vec.push((last_star_pos-1) as usize);
      }
    }
    vec
  }

fn all_available_positions(str: &str) -> Vec<usize>{
  let symbols = "!§$%&/()=@#-+*";

  let mut vec: Vec<usize> = Vec::new();
  for char in symbols.chars(){
      vec.append(&mut positions(str, char));
  }
  vec
}

