from math import sqrt


class InputError(Exception):
    pass


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
