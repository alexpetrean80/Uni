from math import sqrt

def is_prime_number(number):
    '''checks whether a given number is prime or not
    
    :number: the number to be checked
    '''
    if number == 2:
        return True
    if number % 2 == 0:
        return False
    for i in range(3, int(sqrt(number)) + 1, 2):
        if number % i == 0:
            return False
    return True

def generate_prime_number(number):
    '''generates the first prime number greater than the given number

    :number: the number from which the generation beggins'''
    number += 1
    while is_prime_number(number) == False:
        number += 1
    return number

if __name__ == '__main__':
    user_input = int(input('Enter the number: '))
    print('The smallest prime number larger than the given number is: ', generate_prime_number(user_input))
