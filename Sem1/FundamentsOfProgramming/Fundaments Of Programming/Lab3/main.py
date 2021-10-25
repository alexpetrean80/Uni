import re
from math import sqrt


# getters and setters
def create_complex_number(real_part=0, imaginary_part=0):
    """
    generates a list of two numbers, representing a complex number
    :param real_part: the real part of the complex number represented
    :param imaginary_part: the imaginary part of the complex number represented
    :return: a list of two items, the first of them being the real part, and the second one the imaginary part of the number
    """
    return [real_part, imaginary_part]


def get_shallow_copy_of_complex_number(complex_number):
    """
    generates a shallow copy of the number represented as a list
    :param complex_number: list consisting of real and imaginary parts
    :return: shallow copy of the list
    """
    return complex_number[:]


def get_real_part(number):
    """
    gets the real part of a complex number represented as a list
    :param number: the complex number
    :return: integer being the value of the real part of the number
    """
    return number[0]


def get_imaginary_part(number):
    """
    gets the imaginary part of a complex number represented as a list
    :param number: the complex number
    :return: integer being the value of the imaginary part of the number
    """
    return number[1]


def get_modulus_of_number(number):
    """
    computes the modulus of a complex number given, using a formula
    :param number: complex number given
    :return: integer being the value of the modulus of the number
    """
    return int(sqrt(get_real_part(number) ** 2 + get_imaginary_part(number) ** 2))


def set_complex_number(complex_number, real_part, imaginary_part=0):
    """
    sets new values for a given complex number
    :param complex_number: complex number given
    :param real_part: integer being the new real part
    :param imaginary_part: integer being the new imaginary part(default 0 for real numbers)
    :return: None
    """
    set_real_part(complex_number, real_part)
    set_imaginary_part(complex_number, imaginary_part)


def set_real_part(number, real_part):
    """
    sets new value for the real part of a given complex number
    :param number: the complex number given
    :param real_part: integer being the new real part of the number
    :return: None
    """
    number[0] = real_part


def set_imaginary_part(number, imaginary_part):
    """
    sets new value for the imaginary part of a given complex number
    :param number: the complex number given
    :param imaginary_part: integer being the new real part of the number
    :return: None
    """
    number[1] = imaginary_part


# functionality
def string_to_complex_number(number_as_string):
    """
    transforms a string of form z=a+bi into a complex number
    :param number_as_string: string representing the number
    :return: complex number represented according to the getters and setters
    """
    complex_number = create_complex_number()
    sign = ''
    if number_as_string[-1] == 'i':
        for character in number_as_string:
            if character == '+' or character == '-':
                sign = character
        if sign == '+':
            set_complex_number(complex_number, int(number_as_string.rsplit(sign, 1)[0]), int(number_as_string.rsplit(sign, 1)[1][:-1]))
        elif sign == '-':
            set_complex_number(complex_number, int(number_as_string.rsplit(sign, 1)[0]), int('-' + number_as_string.rsplit(sign, 1)[1][:-1]))
    else:
        try:
            number_as_string = int(number_as_string)
            set_complex_number(complex_number, number_as_string)
        except ValueError:
            print("Not a number")
    return complex_number





def complex_number_to_string(complex_number):
    """
    transforms a complex number represented according to the getters and setters into a string of form z=a+bi
    :param complex_number: complex number
    :return: string representing the complex number
    """
    number_as_string = str()
    if get_imaginary_part(complex_number) > 0:
        number_as_string = str(get_real_part(complex_number)) + '+' + str(get_imaginary_part(complex_number)) + 'i'
    elif get_imaginary_part(complex_number) < 0:
        number_as_string = str(get_real_part(complex_number)) + str(get_imaginary_part(complex_number)) + 'i'
    elif get_imaginary_part(complex_number) == 0:
        number_as_string = str(get_real_part(complex_number))
    return number_as_string


def is_int(number):
    """
    check if a string can be represented as an integer
    :param number: string
    :return: True or False whether the string can or cannot be represented as an integer
    """
    if number[0] == '-':
        return number[1:].isdigit()
    return number.isdigit()


def add_element_to_list(complex_numbers, number):
    """
    adds a given complex number to a given list on the last position
    :param complex_numbers: list of complex numbers
    :param number: complex number to be appended
    :return: None
    """
    complex_numbers.append(string_to_complex_number(number))


def insert_in_list(complex_numbers, number, position):
    """
    inserts a given complex number into a given list at a given position
    :param complex_numbers: list of complex numbers
    :param number: complex number to be inserted
    :param position: position at which the number is to be inserted
    :return: None
    """
    complex_numbers.insert(int(position), string_to_complex_number(number))


def remove_elements(elements, starting_position, ending_position='-1'):
    """
    removes one or more elements from a given list
    if the ending position is left default, only the element at the starting position is to be removed from the list
    if not, the elements from the starting position to the ending position inclusively will be deleted
    :param elements: list of elements
    :param starting_position: starting position from which the elements should be deleted(position of the element to be deleted)
    :param ending_position: last position from which the elements should be deleted(default -1)
    :return: None
    """
    starting_position, ending_position = int(starting_position), int(ending_position)
    if ending_position <= starting_position:
        elements.remove(elements[starting_position])
    else:
        copy_of_elements = list(elements)
        for index in range(starting_position, ending_position + 1):
            elements.remove(copy_of_elements[index])


def replace_element(elements, element_to_be_replaced, element_to_replace):
    """
    replaces certain elements with a given value in a list
    :param elements: list of elements
    :param element_to_be_replaced: the element which is to be changed
    :param element_to_replace: the element which takes the place of the element which is to be changed
    :return: None
    """
    elements[:] = [string_to_complex_number(element_to_replace) if element == string_to_complex_number(element_to_be_replaced) else element for element in elements]


def search_real_numbers(complex_numbers):
    """
    generates a list containing all the integers from a list of complex numbers
    :param complex_numbers: list of complex numbers
    :return: list of integers
    """
    return list(filter(lambda x: get_imaginary_part(x) == 0, complex_numbers))


def search_numbers_modulo(complex_numbers, comparison, value):
    """
    generates a list of complex numbers where their modulus is </>/= with a given value
    :param complex_numbers: list of complex numbers
    :param comparison: the sign of comparison(<,>,=)
    :param value: the value to be compared to the modulus
    :return: list of complex numbers with their modulus respecting the condition
    """
    numbers = list()
    if comparison == '<':
        numbers = list(filter(lambda x: get_modulus_of_number(x) < int(value), complex_numbers))
    elif comparison == '>':
        numbers = list(filter(lambda x: get_modulus_of_number(x) > int(value), complex_numbers))
    elif comparison == '=':
        numbers = list(filter(lambda x: get_modulus_of_number(x) == int(value), complex_numbers))
    return numbers


# ui
def list_elements(complex_numbers):
    for number in complex_numbers:
        print(complex_number_to_string(number), " ")


def list_real_numbers(complex_numbers, starting_position, ending_position):
    list_elements(search_real_numbers(complex_numbers[int(starting_position):int(ending_position) + 1]))


def list_numbers_modulo(complex_numbers, comparison, value):
    list_elements(search_numbers_modulo(complex_numbers, comparison, value))


def read_user_command():
    user_input = input()
    if user_input.find(' ') == -1:
        return user_input, []
    command, args = str(), list()
    user_input = re.split(r"\s", user_input)
    if re.match(r"\D", user_input[0]):
        command += user_input[0]
        if re.match(r"\D", user_input[1]):
            command += ' '
            command += user_input[1]
    args = [arg if re.match(r"[^a-hj-zA-Z]", arg) and arg != 'with' else None for arg in user_input[1:]]
    args = list(filter(lambda x: x is not None, args))
    # args = [int(arg) if is_int(arg) else string_to_complex_number(arg) for arg in args]
    return command, args


def run_command():
    complex_numbers = list()
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
    commands = {'add': add_element_to_list,
                'insert': insert_in_list,
                'remove': remove_elements,
                'replace': replace_element,
                'list': list_elements,
                'list real': list_real_numbers,
                'list modulo': list_numbers_modulo}
    while True:
        command, args = read_user_command()
        if command == 'exit':
            exit(0)
        if len(args) == 0:
            commands[command](complex_numbers)
        else:
            commands[command](complex_numbers, *args)


# tests
def test_string_to_complex_number():
    complex_number = string_to_complex_number('2')
    assert complex_number == [2, 0]


def test_add_element_to_list():
    elements = list()
    add_element_to_list(elements, "2+3i")
    assert len(elements) == 1
    assert elements[0] == string_to_complex_number('2+3i')


def test_insert_in_list():
    elements = list()
    add_element_to_list(elements, '2-3i')
    insert_in_list(elements, "5-2i", '0')
    assert len(elements) == 2


def test_remove_elements():
    elements = [2, 3, 5, 7]
    remove_elements(elements, '2', '3')
    assert len(elements) == 2


def test_replace_element():
    elements = [string_to_complex_number('2+3i'), string_to_complex_number('2')]
    replace_element(elements, '2', '4-2i')
    assert elements[1] == string_to_complex_number('4-2i')


def test_search_real_numbers():
    complex_numbers = [string_to_complex_number('2-3i'), string_to_complex_number('2'), string_to_complex_number('3')]
    real_numbers = search_real_numbers(complex_numbers)
    assert len(real_numbers) == 2


def test_search_numbers_modulo():
    numbers = [string_to_complex_number('2-3i'), string_to_complex_number('1+2i'), string_to_complex_number('2+3i')]
    numbers_mod_smaller_than_3 = search_numbers_modulo(numbers, '<', 3)
    assert len(numbers_mod_smaller_than_3) == 1
    numbers_mod_equal_to_1 = search_numbers_modulo(numbers, '=', 2)
    assert len(numbers_mod_equal_to_1) == 1
    numbers_mod_bigger_than_0 = search_numbers_modulo(numbers, '>', 0)
    assert len(numbers_mod_bigger_than_0) == 3


def tests():
    test_string_to_complex_number()
    test_add_element_to_list()
    test_insert_in_list()
    test_remove_elements()
    test_replace_element()
    test_search_numbers_modulo()
    test_search_real_numbers()


# entry point
if __name__ == '__main__':
    tests()
    run_command()
