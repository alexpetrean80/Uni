def is_perfect_number(number):
    '''checks if a given number is perfect or not

    :number: number to be checked
    '''
    sum = 1
    for i in range(2, number):
        if number % i == 0:
            sum += i
    return True if sum == number else False

def generate_perfect_number(start_number):
    '''computes the biggest perfect number smaller than the given number if it exists,
        else returns corresponding message
    :number: number from which the generation begins
    '''
    original_number = start_number
    start_number -= 1
    while is_perfect_number(start_number) == False and start_number >= 0:
        start_number -= 1
    return str(start_number) + ' is the largest perfect number smaller than '+str(original_number) if is_perfect_number(start_number) == True else 'There is no perfect number smaller than the given number'

if __name__ == '__main__':
    user_input = int(input('Enter the number: '))
    print(generate_perfect_number(user_input))

