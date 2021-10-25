import re
from copy import deepcopy

import UI.general_functions
from Domain.entities import InputError
from Domain.operations import add_element_to_list, insert_in_list, remove_elements, replace_element, filter_real_numbers_in_list, filter_numbers_modulo_in_list, undo, add_element_to_stack


def read_user_command():
    user_input = input()
    if user_input.find(' ') == -1:
        return user_input, []
    command, arguments = str(), list()
    user_input = re.split(r"\s", user_input)
    if re.match(r"\D", user_input[0]):
        command += user_input[0]
        if re.match(r"\D", user_input[1]):
            command += ' '
            command += user_input[1]
    arguments = [argument if re.match(r"[^a-hj-zA-Z]", argument) and argument != 'with' else None for argument in user_input[1:]]
    arguments = list(filter(lambda argument: argument is not None, arguments))
    return command, arguments


def run_command():
    complex_numbers = list()
    undo_stack = list()
    UI.general_functions.populate_list(complex_numbers)
    commands = {'add': add_element_to_list,
                'insert': insert_in_list,
                'remove': remove_elements,
                'replace': replace_element,
                'list': UI.general_functions.list_elements,
                'list real': UI.general_functions.list_real_numbers,
                'list modulo': UI.general_functions.list_numbers_modulo,
                'sum': UI.general_functions.print_sum_of_elements_in_list,
                'product': UI.general_functions.print_product_of_elements_in_list,
                'filter real': filter_real_numbers_in_list,
                'filter modulo': filter_numbers_modulo_in_list
                }
    while True:
        command, arguments = read_user_command()
        try:

            if command == 'exit':
                UI.general_functions.exit_application()
            if command == 'undo':
                complex_numbers = deepcopy(undo(undo_stack))
            elif command in commands:
                add_element_to_stack(complex_numbers[:], undo_stack)
                if len(arguments) == 0:
                    commands[command](complex_numbers)
                else:
                    commands[command](complex_numbers, *arguments)

            else:
                raise InputError
        except TypeError:
            UI.general_functions.clear_console()
            print("Invalid command.")
        except InputError:
            UI.general_functions.clear_console()
            print("Invalid command")
