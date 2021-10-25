// This program computes GCD of two numbers
// read from the command line using the iterative Euclid algorithm.
fn main() -> void {
    let x: int = read();
    let y: int = read();

    while x != 0 {
        let old_x = x;
        x = y % x;
        y = old_x;
    }

    match y {
        y < 0 => print(-y),
        _ => print(y)
    }
}
