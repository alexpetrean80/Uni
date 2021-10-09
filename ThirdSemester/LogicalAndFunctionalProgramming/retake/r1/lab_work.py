def sum_of_odd_digits(n: int) -> int:
    if n < 10:
        return n
    if n % 2 == 0:
        return sum_of_odd_digits(n // 10)
    return n % 10 + sum_of_odd_digits(n // 10)


def compute_nr_occurences(el: int, l: list) -> int:
    if l == []:
        return 0
    if l[0] != el:
        return compute_nr_occurences(el, l[1:])
    return 1 + compute_nr_occurences(el, l[1:])


if __name__ == "__main__":
    print("Sum of odd digits:")
    print(sum_of_odd_digits(1234))
    print("Number of occurences:")
    print(compute_nr_occurences(2, [1, 2, 2, 2, 3, 2, 4, 2]))
