from math import sqrt

def is_prime_number(number):
    '''checks wether a given number is prime or not

    :number: the number to be checked'''
    if number == 2:
        return True
    if number % 2 == 0 or number < 2:
        return False
    for i in range(3, int(sqrt(number)) + 1, 2):
        if number % i == 0:
            return False
    return True
    
def are_prime_twins(first_number, second_number):
    '''checks if two given numbers are prime twins

    :first_number: the first number to be checked
    :second_number: the second number to be checked
    '''
    return True if is_prime_number(first_number) == True and is_prime_number(second_number) == True and second_number - first_number == 2 else False

if __name__ == '__main__':
    user_input = int(input('Enter the number: '))
    prime_twin1, prime_twin2 = user_input + 1, user_input + 3
    while are_prime_twins(prime_twin1, prime_twin2) == False:
        prime_twin1, prime_twin2 = prime_twin1 + 1, prime_twin2 + 1
    print(prime_twin1, 'and', prime_twin2, 'are prime twins')

    
