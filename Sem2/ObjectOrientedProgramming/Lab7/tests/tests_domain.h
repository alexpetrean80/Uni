#ifndef LAB7_TESTS_DOMAIN_H
#define LAB7_TESTS_DOMAIN_H

#include <cassert>

#include "../domain/Exception.h"
#include "../domain/guardian_statue.h"

void test__create_guardian_statue__default_constructor__empty_statue_created();
void test__create_guardian_statue__explicit_constructor__statue_created();
void test__create_guardian_statue__copy_constructor__copy_statue_created();
void test__set_power_word_name__valid_power_word_name__power_word_name_set();
void test__set_material__valid_material__material_set();
void test__set_age__valid_age__age_set();
void test__set_corporeal_form__valid_corporeal_form__corporeal_form_set();
void test__equality__equal_statues__returns_true();
void test__equality__not_equal_statues__returns_false();
void test__not_equality__not_equal_statues__returns_true();
void test__not_equality__equal_statues__returns_false();
void test__to_string__valid_guardian_statue__returns_statue_as_string();
void test__create_exception__valid_message__exception_created();
#endif//LAB7_TESTS_DOMAIN_H
