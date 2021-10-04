from Domain.entities import string_to_complex_number, create_complex_number, get_real_part, get_imaginary_part
from Domain.operations import add_element_to_list, insert_in_list, remove_elements, replace_element, search_real_numbers, search_numbers_modulo, sum_of_two_complex_numbers, sum_of_elements_in_list, product_of_two_complex_numbers, product_of_elements_in_list, filter_real_numbers_in_list, \
    filter_numbers_modulo_in_list, add_element_to_stack, undo


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


def test_sum_of_two_complex_numbers():
    operand1, operand2 = create_complex_number(3, 5), create_complex_number(2, 3)
    assert get_real_part(sum_of_two_complex_numbers(operand1, operand2)) == 5
    assert get_imaginary_part(sum_of_two_complex_numbers(operand1, operand2)) == 8


def test_sum_of_elements_in_list():
    complex_numbers = [create_complex_number(2, 3), create_complex_number(5, 2), create_complex_number(2, 1), create_complex_number(5, 3)]
    assert get_real_part(sum_of_elements_in_list(complex_numbers, 1, 3)) == 12
    assert get_imaginary_part(sum_of_elements_in_list(complex_numbers, 0, 2)) == 6


def test_product_of_two_complex_numbers():
    operand1, operand2 = create_complex_number(2, 1), create_complex_number(3, 2)
    assert get_real_part(product_of_two_complex_numbers(operand1, operand2)) == 4
    assert get_imaginary_part(product_of_two_complex_numbers(operand1, operand2)) == 7


def test_product_of_elements_in_list():
    complex_numbers = [create_complex_number(2, 3), create_complex_number(5, 2), create_complex_number(2, 1), create_complex_number(5, 3)]
    assert get_real_part(product_of_elements_in_list(complex_numbers, 1, 3)) == 13
    assert get_imaginary_part(product_of_elements_in_list(complex_numbers, 1, 3)) == 69


def test_filter_real_numbers_in_list():
    complex_numbers = [create_complex_number(2), create_complex_number(5, -2), create_complex_number(1)]
    filter_real_numbers_in_list(complex_numbers, 0, 2)
    assert len(complex_numbers) == 2


def test_filter_numbers_modulo_in_list():
    complex_numbers = [create_complex_number(1, 2), create_complex_number(3, 0), create_complex_number(-2, 1), create_complex_number(5, 9)]
    filter_numbers_modulo_in_list(complex_numbers, "=", 2)
    assert len(complex_numbers) == 2


def test_add_element_to_stack():
    stack = [2, 3, 5]
    add_element_to_stack(8, stack)
    assert stack[-1] == 8


def test_undo():
    stack = [2, 5, 7, 9]
    assert undo(stack) == 7
