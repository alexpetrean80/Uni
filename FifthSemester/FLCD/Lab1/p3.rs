// This program retrieves the
// longest string from strings sent as arguments.
fn main(args: []str) -> void {
    let max_length: int = 0;
    let max_length_str: str = "";

    let aux: str;
    let length: int;
    for arg in args {
        aux = arg;
        length = 0;
        while aux != "" {
            length++;
            aux = aux[:-1];
        }
        if length > max_length {
            max_length = length;
            max_length_str = arg;
        }
    }

    print(max_length_str);
}
