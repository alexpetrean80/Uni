from functools import reduce

from Domain.entities import string_to_complex_number, get_imaginary_part, get_modulus_of_number, create_complex_number, get_real_part


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
    return list(filter(lambda number: get_imaginary_part(number) == 0, complex_numbers))


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
        numbers = list(filter(lambda number: get_modulus_of_number(number) < int(value), complex_numbers))
    elif comparison == '>':
        numbers = list(filter(lambda number: get_modulus_of_number(number) > int(value), complex_numbers))
    elif comparison == '=':
        numbers = list(filter(lambda number: get_modulus_of_number(number) == int(value), complex_numbers))
    return numbers


def sum_of_two_complex_numbers(first_number, second_number):
    """
    computes the sum of two complex numbers using the formula "a+bi + c+di = (a+c) + (b+d)i"
    :param first_number: first operand of the computation
    :param second_number: second operand of the computation
    :return: the sum of the numbers
    """
    real_part_of_result = get_real_part(first_number) + get_real_part(second_number)
    imaginary_part_of_result = get_imaginary_part(first_number) + get_imaginary_part(second_number)
    return create_complex_number(real_part_of_result, imaginary_part_of_result)


def sum_of_elements_in_list(complex_numbers, start_index, end_index):
    """
    computes the sum of complex numbers in a given list between the given indexes
    :param complex_numbers: list of complex numbers
    :param start_index: the index from which the computation is to occur
    :param end_index: the index to which the computation is to occur
    :return: the result of the computation
    """
    return reduce(lambda first_operand, second_operand: sum_of_two_complex_numbers(first_operand, second_operand), complex_numbers[start_index:end_index + 1])


def product_of_two_complex_numbers(first_number, second_number):
    """
    computes the product of two complex numbers using the formula "(a+bi) * (c+di) = (a*c - b*d)+(a*d + b*c)i"
    :param first_number: first operand of the computation
    :param second_number: second operand of the computation
    :return: the product of the operands
    """
    real_part_of_result = get_real_part(first_number) * get_real_part(second_number) - get_imaginary_part(first_number) * get_imaginary_part(second_number)
    imaginary_part_of_result = get_real_part(first_number) * get_imaginary_part(second_number) + get_real_part(second_number) * get_imaginary_part(first_number)
    return create_complex_number(real_part_of_result, imaginary_part_of_result)


def product_of_elements_in_list(complex_numbers, start_index, end_index):
    """
        computes the product of complex numbers in a given list between the given indexes
        :param complex_numbers: list of complex numbers
        :param start_index: the index from which the computation is to occur
        :param end_index: the index to which the computation is to occur
        :return: the result of the computation
        """
    return reduce(lambda first_operand, second_operand: product_of_two_complex_numbers(first_operand, second_operand), complex_numbers[start_index:end_index + 1])


def filter_real_numbers_in_list(complex_numbers, starting_position, ending_position):
    """
    modifies the list given so that it contains only real numbers
    :param ending_position: starting position
    :param starting_position: ending position
    :param complex_numbers: the list to be modified
    :return: None
    """
    complex_numbers_copy = complex_numbers[:]
    for number in complex_numbers_copy[starting_position:ending_position]:
        if get_imaginary_part(number) != 0:
            complex_numbers.remove(number)


def filter_numbers_modulo_in_list(complex_numbers, comparison, value):
    """
    modifies the list given so that it contains only numbers whose modulus is smaller than/equal to/bigger than the given value
    :param complex_numbers: list of complex numbers
    :param comparison: the comparison sign(</=/>)
    :param value: the value to be compared
    :return: None
    """
    for number in complex_numbers[:]:
        if comparison == '<' and get_modulus_of_number(number) >= value:
            complex_numbers.remove(number)
        elif comparison == '=' and get_modulus_of_number(number) != value:
            complex_numbers.remove(number)
        elif comparison == '>' and get_modulus_of_number(number) <= value:
            complex_numbers.remove(number)


def add_element_to_stack(element, stack):
    """
    push an element on top of a stack if it is not on top of the stack already
    :param element: element to be pushed on top f the stack
    :param stack: stack
    :return: None
    """
    if len(stack) == 0:
        stack.append(element)
    elif element != stack[-1]:
        stack.append(element)


def undo(undo_stack):
    """
    pop the last item from the stack and return the resulting last item
    :param undo_stack: stack
    :return: the last element after a pop
    """
    undo_stack.pop()
    return undo_stack[-1] if len(undo_stack) != 0 else []
