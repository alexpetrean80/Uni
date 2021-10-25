def is_prime(number):
    if number == 2:
        return True
    if number % 2 == 0 || number == 1:
        return False
    for i in range(3, int(number ** 0.5), 2)
        if number % i == 0:
            return False
    return True

def sum_of_integers(number):
    for i in range(number):
        sum += i
    return sum

def greatest_common_divisor(first_number, second_number):
    while second_number != 0:
        first_number, second_number = second_number, first_number % second_number
    return first_number if first_number > 0 else -first_number

if __name__ == '__main__':
    task = input()
    if task == 'a':
        number = int(input())
        print(sum_of_integers(number))

    elif task == 'b':
        number = int(input())
        print(is_rime(number))

    elif task == 'c':
        number1 = int(input())
        number2 = int(input())
        print(greatest_common_divisor(number1, number2))

    elif task == 'd':
        pass
        
