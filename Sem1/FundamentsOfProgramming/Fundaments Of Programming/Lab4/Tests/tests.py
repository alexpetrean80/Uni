from Tests.Test_Domain.test_entities import test_string_to_complex_number, test_is_int, test_complex_number_to_string, test_get_modulus_of_number, test_get_imaginary_part, test_get_real_part, test_get_shallow_copy_of_complex_number, test_create_complex_number
from Tests.Test_Domain.test_operations import test_add_element_to_list, test_insert_in_list, test_remove_elements, test_replace_element, test_search_numbers_modulo, test_search_real_numbers, test_sum_of_two_complex_numbers, test_sum_of_elements_in_list, test_product_of_two_complex_numbers, \
    test_product_of_elements_in_list, test_filter_real_numbers_in_list, test_filter_numbers_modulo_in_list, test_add_element_to_stack, test_undo


def tests():
    test_create_complex_number()
    test_get_shallow_copy_of_complex_number()
    test_get_real_part()
    test_get_imaginary_part()
    test_get_modulus_of_number()
    test_string_to_complex_number()
    test_complex_number_to_string()
    test_is_int()
    test_add_element_to_list()
    test_insert_in_list()
    test_remove_elements()
    test_replace_element()
    test_search_numbers_modulo()
    test_search_real_numbers()
    test_sum_of_two_complex_numbers()
    test_sum_of_elements_in_list()
    test_product_of_two_complex_numbers()
    test_product_of_elements_in_list()
    test_filter_real_numbers_in_list()
    test_filter_numbers_modulo_in_list()
    test_add_element_to_stack()
    test_undo()