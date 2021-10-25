# sequence 7 and 8 from assignment 2
from math import sqrt
from os import system
from sys import platform


def read_complex_number():
    # reads a string in the form "a+bi" where a and b are integers and puts a and b as only two elements of the list which is to be returned
    number = input('Enter a complex number with the form z=a+bi: ')
    complex_number = get_complex_number()
    # checking if the number has the proper structure to be a complex number
    if number[-1] == 'i':
        # searching for the sign between the real part and imaginary part and putting it into variable 'sign'
        for character in number:
            if character == '+':
                sign = '+'
            elif character == '-':
                sign = '-'
        # splitting the string inputted from the keyboard according to the sign, and returning the parts without the last 'i'
        if sign == '+':
            set_real_part(complex_number, int(number.rsplit(sign, 1)[0]))
            set_imaginary_part(complex_number, int(number.rsplit(sign, 1)[1][0:-1]))
            return complex_number
        if sign == '-':
            # if the sign is '-', we need to add it as a character at the beggining of the imaginary part
            set_real_part(complex_number, int(number.rsplit(sign, 1)[0]))
            set_imaginary_part(complex_number, int(sign + number.rsplit(sign, 1)[1][0:-1]))
            return complex_number
    # if the function does not return the list [real_part, imaginary_part], it means input not a complex number
    # error is thrown
    print('\nNot a complex number!!')
    # function is called recursively to make the user input a valid number
    return read_complex_number()


def read_list(complex_numbers):
    """read a given number of complex numbers, and add them to the list which is returned
    :list_of_complex_numbers: the list in which the new numbers are to be appended
    """
    # we get the number of items to be appended
    number_of_items = int(get_number_of_elements_in_list())
    # for every cycle of the loop, a complex number is read and appended to the list
    while number_of_items != 0:
        number = read_complex_number()
        complex_numbers.append(number)
        number_of_items -= 1
    # list is returned
    return complex_numbers


def print_complex_number(number):
    """returns a given complex number(a list of two integers) as a number of the form "a+bi"
    :complex_number: the complex number which is to be printed
    """
    # if the imaginary part is negative, we just concatenate the arguments and add 'i'
    if get_imaginary_part(number) < 0:
        number = str(get_real_part(number)) + str(get_imaginary_part(number)) + 'i'
    else:
        # if the imaginary part is positive, we add a plus between the arguments
        number = str(get_real_part(number)) + '+' + str(get_imaginary_part(number)) + 'i'
    # we print the string which holds the number
    print(number)


def print_list(complex_numbers):
    """prints a given list of complex numbers
    :list_of_complex_numbers: the list which is to be printed
    """
    # we check if the list is empty
    if len(complex_numbers) != 0:
        print('\nThe elements of the list are: ')
        # every element of the list is printed on a new line
        for number in complex_numbers:
            print_complex_number(number)
    else:
        # if the list is empty, error is trown
        print('\nList is empty.')


def get_real_part(number):
    """returns the real part of a given complex number
    :complex_number: the given complex number from which the real part should be extracted
    """
    return number[0]


def set_real_part(number, real_part):
    """changes the real part of the given complex number
    :complex_number: the given complex number whose real part is to be changed
    :real_part: the new value of the number's real part
    """
    number[0] = real_part


def get_imaginary_part(number):
    """returns the imaginary part of a given complex number
    :complex_number: the given complex number from which the imaginary part should be extracted
    """
    return number[1]


def set_imaginary_part(number, imaginary_part):
    """changes the imaginary part of the given complex number
    :complex_number: the given complex number whose imaginary part is to be changed
     imaginary_part: the new value of the number's imaginary part
    """
    number[1] = imaginary_part


def get_complex_number(real_part=0, imaginary_part=0):
    number = [real_part, imaginary_part]
    return number


def get_shallow_copy_of_number(number):
    return number[:]


def set_complex_number(number, real_part, imaginary_part):
    """changes the whole complex number given
    :complex_number: the complex number
    :real_part: real part of the complex number
    :imaginary_part: imaginary part of the complex number
    """
    set_real_part(number, real_part)
    set_imaginary_part(number, imaginary_part)


def modulus_of_complex_number(number):
    """calculate the modulus of a complex number given
    :complex_number: the number whose modulus is to be calculated
    """
    return int(sqrt(get_real_part(number) ** 2 + get_imaginary_part(number) ** 2))


def is_prime(number):
    """checks whether the given number is prime or not
    :number: the number which is to be checked
    """
    # if the number is smaller than 2 it cannot be prime
    if number < 2:
        return False
    # 2 is the only even prime number, so it and the other even numbers are taken as particular cases
    if number == 2:
        return True
    if number % 2 == 0:
        return False
    # divisors of the number are to be found until the square root of the numbers
    for i in range(3, int(sqrt(number)) + 1, 2):
        if number % i == 0:
            return False
    # if divisors of the number haven't been found, the number is prime
    return True


def find_sequence_modulus_difference_is_prime(complex_numbers):
    """searches for the longest sequence in which the difference between two
    consecutive numbers in the list is a prime numbers
    :list: the list in which the sequence is searched
    """
    length_of_maximum_sequence, length_of_processed_sequence, maximum_sequence_start_position, position = 0, 1, 0, 0
    while position < len(complex_numbers) - 1:
        if is_prime(modulus_of_complex_number(complex_numbers[position + 1]) - modulus_of_complex_number(complex_numbers[position])):
            length_of_processed_sequence += 1
            position += 1
        else:
            if length_of_processed_sequence > length_of_maximum_sequence:
                length_of_maximum_sequence = length_of_processed_sequence
                maximum_sequence_start_position = position - length_of_processed_sequence + 1
            length_of_processed_sequence = 1
            position += 1
    if length_of_processed_sequence > length_of_maximum_sequence:
        length_of_maximum_sequence = length_of_processed_sequence
        maximum_sequence_start_position = position - length_of_processed_sequence + 1
    return complex_numbers[maximum_sequence_start_position: maximum_sequence_start_position + length_of_maximum_sequence + 1]


def find_sequence_modulus_smaller_than_10(complex_numbers):
    """searches for the longest sequence in which modulus of numbers is between 0 and 10
    :list: the list in which the sequence is searched
    """
    length_of_maximum_sequence, length_of_processed_sequence, maximum_sequence_start_position, position = 0, 1, 0, 0
    while position < len(complex_numbers):
        if modulus_of_complex_number(complex_numbers[position]) <= 10:
            length_of_processed_sequence += 1
            position += 1
        else:
            if length_of_processed_sequence > length_of_maximum_sequence:
                length_of_maximum_sequence = length_of_processed_sequence
                maximum_sequence_start_position = position - length_of_processed_sequence + 1
            length_of_processed_sequence = 1
            position += 1
    if length_of_processed_sequence > length_of_maximum_sequence:
        length_of_maximum_sequence = length_of_processed_sequence
        maximum_sequence_start_position = position - length_of_processed_sequence + 1
    return complex_numbers[maximum_sequence_start_position: maximum_sequence_start_position + length_of_maximum_sequence + 1]


def get_number_of_elements_in_list():
    # read from the keyboard the number of the elements which are to be added to the list, and returns it as an integer
    number = input('\nEnter the number of elements to be added to a list: ')
    return number


# ui functions
def check_operations(complex_numbers):
    """checks whether the user wants to execute another operation on the list
    :list: the list on which the operation are to be executed
    """
    # message is shown and we get user's input
    user_input = input('\nDo you want to make another operation? (y/n): ')
    # if the input is positive, the principal menu is shown again
    if user_input == 'y':
        menu(complex_numbers)
    # if the input is negative, the application is exited
    elif user_input == 'n':
        exit_application()
    # in case of invalid input, the function is recursively run
    else:
        return check_operations(complex_numbers)


def exit_application():
    # prints message and exits the app gracefully
    print('\nExiting application...\n')
    exit(0)


def clear_console():
    # checks the platform on which the app is run and performs the calls the corresponding command to clear the clear_console

    # linux and mac share the same shell
    if platform == 'linux' or platform == 'darwin':
        # calls bash command to clear the console
        system('clear')
    # checks if the platform is windows
    elif platform == 'windows':
        # calls the CMD/Powershell command to clear the console
        system('cls')


def sequence_menu(complex_numbers):
    """submenu for point 3, as it can be one of two sequences shown
    :list:list in which the sequences are searched
    """
    clear_console()
    print('''   --Menu--
a. The difference between the modulus of consecutive numbers is a prime number.
b. The modulus of all elements is in the [0, 10] range.''')
    user_input = input('Enter the operation which you want to execute (A/B): ')
    # checks user input and runs the corresponding process
    if user_input == 'a' or user_input == 'A':
        print_list(find_sequence_modulus_difference_is_prime(complex_numbers))
        check_operations(complex_numbers)
    elif user_input == 'b' or user_input == 'B':
        print_list(find_sequence_modulus_smaller_than_10(complex_numbers))
        check_operations(complex_numbers)
    else:
        # if input is invalid message is shown and the function is rerun recursively until input is valid
        user_input = input('Choose either \"A\" or \"B\"!')
        sequence_menu(complex_numbers)


def menu(complex_numbers):
    """prints the main menu of the application
    :list: the list on which the opperations are computed
    """
    clear_console()
    print('''   --Menu--
    1. Add complex numbers to the list.
    2. Display the entire list of complex numbers.
    3. Display the sequence  of complex numbers where:
        a. The difference between the modulus of consecutive numbers is a prime number.
        b. The modulus of all elements is in the [0, 10] range.
    4. Exit the application.\n''')
    user_input = int(input('Enter the operation which you want to execute (1-4): '))
    # checks the user input and computes the corresponding processlist_of_complex_numbers[i]
    if user_input == 1:
        complex_numbers = read_list(complex_numbers)
    elif user_input == 2:
        print_list(complex_numbers)
    elif user_input == 3:
        sequence_menu(complex_numbers)
    elif user_input == 4:
        exit_application()
    else:
        # if input is invalid message is shown and the menu is rerun recursively
        print('\nChoose a number between 1 and 4!\n')
        menu(complex_numbers)
    check_operations(complex_numbers)


if __name__ == '__main__':
    list_of_complex_numbers = list()
    complex_number = get_complex_number()
    set_complex_number(complex_number, 3, 2)
    list_of_complex_numbers.append(get_shallow_copy_of_number(complex_number))
    set_complex_number(complex_number, 1, -8)
    list_of_complex_numbers.append(get_shallow_copy_of_number(complex_number))
    set_complex_number(complex_number, -1, 1)
    list_of_complex_numbers.append(get_shallow_copy_of_number(complex_number))
    set_complex_number(complex_number, 4, 3)
    list_of_complex_numbers.append(get_shallow_copy_of_number(complex_number))
    set_complex_number(complex_number, 7, -2)
    list_of_complex_numbers.append(get_shallow_copy_of_number(complex_number))
    set_complex_number(complex_number, 6, 5)
    list_of_complex_numbers.append(get_shallow_copy_of_number(complex_number))
    set_complex_number(complex_number, -3, -1)
    list_of_complex_numbers.append(get_shallow_copy_of_number(complex_number))
    set_complex_number(complex_number, -2, 5)
    list_of_complex_numbers.append(get_shallow_copy_of_number(complex_number))
    set_complex_number(complex_number, 3, -3)
    list_of_complex_numbers.append(get_shallow_copy_of_number(complex_number))
    set_complex_number(complex_number, 8, 2)
    list_of_complex_numbers.append(get_shallow_copy_of_number(complex_number))
    menu(list_of_complex_numbers)
