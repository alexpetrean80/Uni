import re
from copy import deepcopy

import UI
from Domain.entities import InputError, is_int, string_to_complex_number
from Domain.operations import add_element_to_list, insert_in_list, remove_elements, replace_element, filter_real_numbers_in_list, filter_numbers_modulo_in_list, add_element_to_stack, undo
from UI import general_functions


def print_menu():
    print("Operations:\n"
          "     1.Add a complex number at the end of the list;\n"
          "     2.Insert a complex number at a certain position in list;\n"
          "     3.Remove a complex number from the list by specifying it's position;\n"
          "     4.Remove complex numbers from the list specifying the range of positions;\n"
          "     5.Replace a number from the list;\n"
          "     6.Print the entire list between a range of positions given;\n"
          "     7.Print the real numbers from the list;\n"
          "     8.Print the real numbers whose modulus is smaller than/equal to/bigger than than a given value;\n"
          "     9.Print the sum of real numbers from the list between a range of positions;\n"
          "     10.Print the product of real numbers from the list between a range of positions;\n"
          "     11.Filter the real numbers in the list(modifies the list);\n"
          "     12.Filter the numbers whose modulus is smaller than/equal to/bigger than a given value(modifies the list)."
          "     13.Undo the last operation made over the list.\n"
          "     0.Exit application")


def another_operation():
    while True:
        user_input = input("Do you want to make another operation? (y/n): ")
        try:
            if re.match(r"[yY]", user_input):
                UI.general_functions.clear_console()
                break
            elif re.match(r"[nN]", user_input):
                UI.general_functions.exit_application()
            else:
                raise InputError
        except InputError:
            print("Invalid input")


def read_user_input():
    while True:
        print_menu()
        operation = (input("Enter the operation which you want to apply: "))
        try:
            if is_int(operation):
                if 14 > int(operation) >= 0:
                    return operation
                else:
                    raise InputError
            else:
                raise TypeError
        except TypeError:
            UI.general_functions.clear_console()
            print("Input needs to be an integer")
        except InputError:
            UI.general_functions.clear_console()
            print("Input needs to be between 0 and 12.")


def console_menu():
    complex_numbers = list()
    stack = list()
    UI.general_functions.populate_list(complex_numbers)
    while True:
        add_element_to_stack(complex_numbers[:], stack)
        operation = int(read_user_input())
        if operation == 0:
            UI.general_functions.exit_application()
        elif operation == 1:
            while True:
                try:
                    complex_number = input("Enter the complex number to be added to the list: ")
                    add_element_to_list(complex_numbers, complex_number)
                    break
                except InputError:
                    print("Invalid input")
        elif operation == 2:
            while True:
                try:
                    complex_number = input("Enter the complex number to be inserted to the list: ")
                    position = input("Enter the position where it is to be inserted: ")
                    insert_in_list(complex_numbers, complex_number, position)
                    break
                except IndexError:
                    print("Position is too big.")
        elif operation == 3:
            while True:
                try:
                    position = input("Enter the position from where you want to remove a complex number: ")
                    remove_elements(complex_numbers, position)
                    break
                except IndexError:
                    print("Position is too big.")
        elif operation == 4:
            while True:
                try:
                    start_position = input("Enter the starting position: ")
                    end_position = input("Enter the ending position: ")
                    remove_elements(complex_numbers, start_position, end_position)
                    UI.general_functions.clear_console()
                    break
                except InputError:
                    print("Invalid input")
        elif operation == 5:
            while True:
                try:
                    number_to_replace = input("Enter the number which you wish to replace: ")
                    replacement_number = input("Enter the number which you want to be put in place: ")
                    if string_to_complex_number(number_to_replace) not in complex_numbers:
                        raise ValueError
                    replace_element(complex_numbers, number_to_replace, replacement_number)
                    break
                except ValueError:
                    print("The number is not in list.")
                except IndexError:
                    print("Invalid input.")
        elif operation == 6:
            UI.general_functions.list_elements(complex_numbers)
        elif operation == 7:
            while True:
                try:
                    start_position = input("Enter the starting position: ")
                    end_position = input("Enter the ending position: ")
                    if start_position > end_position or is_int(start_position) is False or is_int(end_position) is False:
                        raise InputError
                    UI.general_functions.list_real_numbers(complex_numbers, start_position, end_position)
                    break
                except InputError:
                    print("Invalid input")
        elif operation == 8:
            while True:
                try:
                    comparison = input("Enter the sign of the comparison (</=/>): ")
                    value = input("Enter the value which is to be compared: ")
                    UI.general_functions.list_numbers_modulo(complex_numbers, comparison, value)
                    break
                except InputError:
                    print("Invalid input")
        elif operation == 9:
            while True:
                try:
                    start_position = input("Enter the starting position: ")
                    end_position = input("Enter the ending position: ")
                    if start_position > end_position or is_int(start_position) is False or is_int(end_position) is False:
                        raise InputError
                    UI.general_functions.print_sum_of_elements_in_list(complex_numbers, start_position, end_position)
                    break
                except InputError:
                    print("Invalid input")
        elif operation == 10:
            while True:
                try:
                    start_position = input("Enter the starting position: ")
                    end_position = input("Enter the ending position: ")
                    if start_position > end_position or is_int(start_position) is False or is_int(end_position) is False:
                        raise InputError
                    UI.general_functions.print_product_of_elements_in_list(complex_numbers, start_position, end_position)
                    break
                except InputError:
                    print("Invalid input")
        elif operation == 11:
            while True:
                try:
                    start_position = int(input("Enter the starting position: "))
                    end_position = int(input("Enter the ending position (<" + str(len(complex_numbers) - 1) + ')' ))
                    if end_position > (len(complex_numbers) - 1):
                        raise ValueError("Ending position cannot be higher than the no. of elements in list.")
                    filter_real_numbers_in_list(complex_numbers, start_position, end_position)
                    break
                except ValueError:
                    print(ValueError)
                except TypeError:
                    print("Needs to be an integer.")
        elif operation == 12:
            while True:
                try:
                    comparison = input("Enter the sign of the comparison (</=/>): ")
                    if comparison not in ['<', '=', '>']:
                        raise InputError
                    value = input("Enter the value which is to be compared: ")
                    if is_int(value) is False:
                        raise TypeError
                    filter_numbers_modulo_in_list(complex_numbers, comparison, value)
                    break
                except TypeError:
                    print("Value needs to be an integer.")
                except InputError:
                    print("Invalid input")
        elif operation == 13:
            complex_numbers = deepcopy(undo(stack))
        another_operation()
