// This program reads an integer from the command
// line and checks if it's 0, a digit or something else.
fn main() -> void {
    let x: int = read();

    let result: str = match x {
        0 => "It's zero",
        1..10 => "It's a digit",
        _ => "It's something else"
    };

    print(result);
}
