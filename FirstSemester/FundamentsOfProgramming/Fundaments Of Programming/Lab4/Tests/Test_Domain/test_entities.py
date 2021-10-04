from Domain.entities import string_to_complex_number, is_int, complex_number_to_string, create_complex_number, get_modulus_of_number, get_imaginary_part, get_real_part, get_shallow_copy_of_complex_number


def test_create_complex_number():
    complex_number = create_complex_number(2, 3)
    assert complex_number == [2, 3]


def test_get_shallow_copy_of_complex_number():
    complex_number = create_complex_number(5, 2)
    copy_of_complex_number = get_shallow_copy_of_complex_number(complex_number)
    assert complex_number == copy_of_complex_number


def test_get_real_part():
    complex_number = create_complex_number(2, 3)
    assert get_real_part(complex_number) == 2


def test_get_imaginary_part():
    complex_number = create_complex_number(2, 3)
    assert get_imaginary_part(complex_number) == 3


def test_get_modulus_of_number():
    complex_number = create_complex_number(3, 2)
    assert get_modulus_of_number(complex_number) == 3


def test_string_to_complex_number():
    complex_number = string_to_complex_number('2')
    assert complex_number == [2, 0]


def test_complex_number_to_string():
    complex_number = create_complex_number(2, -3)
    assert complex_number_to_string(complex_number) == "2-3i"


def test_is_int():
    assert is_int("23") is True
    assert is_int("fp") is False
