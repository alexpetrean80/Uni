#include "tests.h"

void domain_tests() {
    test__create_guardian_statue__default_constructor__empty_statue_created();
    test__create_guardian_statue__explicit_constructor__statue_created();
    test__create_guardian_statue__copy_constructor__copy_statue_created();
    test__set_power_word_name__valid_power_word_name__power_word_name_set();
    test__set_material__valid_material__material_set();
    test__set_age__valid_age__age_set();
    test__set_corporeal_form__valid_corporeal_form__corporeal_form_set();
    test__equality__equal_statues__returns_true();
    test__equality__not_equal_statues__returns_false();
    test__not_equality__not_equal_statues__returns_true();
    test__not_equality__equal_statues__returns_false();
    test__to_string__valid_guardian_statue__returns_statue_as_string();
    test__create_exception__valid_message__exception_created();
}

void file_repo_tests() {
    test__add_guardian_statue_file__guardian_statue_does_not_exist__guardian_statue_added();
    test__add_guardian_statue_file__guardian_statue_exists__exception_is_thrown();
    test__delete_guardian_statue_file__guardian_statue_exists__guardian_statue_removed();
    test__delete_guardian_statue_file__guardian_statue_does_not_exist__exception_is_thrown();
    test__update_guardian_statue_file__guardian_statue_exists__guardian_statue_updated();
    test__update_guardian_statue_file__guardian_statue_does_not_exist__exception_is_thrown();
    test__set_file_name__valid_name__file_name_updated();
    test__next__first_guardian_statue__first_guardian_statue_is_returned();
    test__next__last_guardian_statue__first_guardian_statue_is_returned();
    test__save__repository_not_empty__guardian_statue_is_saved();
    test__save__repository_empty__exception_is_thrown();
}

void service_tests() {
    test__add_guardian_statue__mode_A__guardian_statue_is_added();
    test__add_guardian_statue__mode_B__exception_is_thrown();
    test__delete_guardian_statue__mode_A__guardian_statue_is_deleted();
    test__delete_guardian_statue__mode_B__exception_is_thrown();
    test__update_guardian_statue__mode_A__guardian_statue_is_updated();
    test__update_guardian_statue__mode_B__exception_is_thrown();
    test__get_mode__valid_mode__mode_is_returned();
    test__get_file__valid_file__file_is_returned();
    test__set_file__valid_file__file_is_set();
    test__save__mode_B__guardian_statue_is_saved();
    test__save__mode_A__exception_is_thrown();
    test__next__mode_B__guardian_statue_is_returned();
    test__next__mode_A__exception_is_thrown();
    test__get_my_list__mode_A__exception_is_thrown();
}

void tests() {
    domain_tests();
    file_repo_tests();
    service_tests();
}