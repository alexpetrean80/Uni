import platform
from os import system

from Domain.entities import complex_number_to_string, create_complex_number, set_complex_number, get_shallow_copy_of_complex_number, InputError
from Domain.operations import search_real_numbers, search_numbers_modulo, sum_of_elements_in_list, product_of_elements_in_list
from UI.console import run_command
from UI.menu import console_menu


def list_elements(complex_numbers):
    for number in complex_numbers:
        print(complex_number_to_string(number), " ")


def list_real_numbers(complex_numbers, starting_position, ending_position):
    list_elements(search_real_numbers(complex_numbers[int(starting_position):int(ending_position) + 1]))


def list_numbers_modulo(complex_numbers, comparison, value):
    list_elements(search_numbers_modulo(complex_numbers, comparison, value))


def clear_console():
    if platform == 'windows':
        system('cls')
    else:
        system('clear')


def exit_application():
    exit(0)


def print_sum_of_elements_in_list(complex_numbers, start, end):
    print(sum_of_elements_in_list(complex_numbers, start, end))


def print_product_of_elements_in_list(complex_numbers, start, end):
    print(product_of_elements_in_list(complex_numbers, start, end))


def populate_list(complex_numbers):
    number = create_complex_number()
    set_complex_number(number, 2, -2)
    complex_numbers.append(get_shallow_copy_of_complex_number(number))
    set_complex_number(number, 2)
    complex_numbers.append(get_shallow_copy_of_complex_number(number))
    set_complex_number(number, 3)
    complex_numbers.append(get_shallow_copy_of_complex_number(number))
    set_complex_number(number, -1)
    complex_numbers.append(get_shallow_copy_of_complex_number(number))
    set_complex_number(number, 6, -2)
    complex_numbers.append(get_shallow_copy_of_complex_number(number))
    set_complex_number(number, 2, -3)
    complex_numbers.append(get_shallow_copy_of_complex_number(number))
    set_complex_number(number, 5, 3)
    complex_numbers.append(get_shallow_copy_of_complex_number(number))
    set_complex_number(number, 3, -1)
    complex_numbers.append(get_shallow_copy_of_complex_number(number))
    set_complex_number(number, 5)
    complex_numbers.append(get_shallow_copy_of_complex_number(number))
    set_complex_number(number, 31, -21)
    complex_numbers.append(get_shallow_copy_of_complex_number(number))


def check_ui():
    while True:
        user_input = int(input("Choose which user interface do you prefer: \n 1.Console menu;\n 2.Command menu.\n Input:"))
        try:
            if user_input == 1 or user_input == 2:
                user_choice = user_input
                break
            else:
                raise InputError
        except InputError:
            clear_console()
            print("Invalid input\n")
    clear_console()
    if user_choice == 1:
        console_menu()
    else:
        run_command()
