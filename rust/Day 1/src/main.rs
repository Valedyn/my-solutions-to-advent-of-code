use crate::day1::day1_run;

mod day1;

fn main(){
//day1_run()
    let mut vec: Vec<u32> = vec![1, 2, 3, 4, 5];
    let mut vec2: Vec<u32> = vec.into_iter().filter(|&val| val > 2).map(|val| val *2).collect();
    println!("{:?}", vec2);
        ()
}