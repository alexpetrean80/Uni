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
    test__to_csv__valid_guardian_statue__returns_statue_as_csv();
    test__to_html__valid_guardian_statue__returns_statue_as_html_table_row();
    test__validate__valid_input__guardian_statue_validated();
    test__validate__invalid_input__exception_is_thrown();
    test__validator_exception_what__valid_message__message_is_returned();
    test__repository_exception_what__valid_message__message_is_returned();
    test__service_exception_what__valid_message__message_is_returned();
}

void file_repo_tests() {
    test__set_repo_file__valid_file__repo_file_is_modified();
    test__set_my_list_file__valid_file__my_list_file_is_modified();
    test__add_guardian_statue__guardian_statue_does_not_exist__guardian_statue_added();
    test__add_guardian_statue__guardian_statue_exists__exception_is_thrown();
    test__delete_guardian_statue__guardian_statue_exists__guardian_statue_removed();
    test__delete_guardian_statue__guardian_statue_does_not_exist__exception_is_thrown();
    test__update_guardian_statue__guardian_statue_exists__guardian_statue_updated();
    test__update_guardian_statue__guardian_statue_does_not_exist__exception_is_thrown();
    test__next__index_in_range__guardian_statue_on_index_is_returned();
    test__next__index_out_of_range__guardian_statue_on_first_position_is_returned();
    test__save__csv_file__guardian_statue_saved();
    test__save__html_file__guardian_statue_saved();
    test__save__wrong_file_type__exception_is_thrown();
    test__save__no_file_type__exception_is_thrown();
    test__save__guardian_statue_already_exists_in_my_file__exception_is_thrown();
    test__save__guardian_statue_does_not_exist_in_repo__exception_is_thrown();
    test__my_list__no_file_type__exception_is_thrown();
    test__my_list__wrong_file_type__exception_is_thrown();
}

void service_tests() {
    test__set_mode__valid_mode__mode_is_set();
    test__set_repo_file_name__valid_file_name__file_name_is_set();
    test__set_my_list_file_name__valid_file_name__file_name_is_set();
    test__add_guardian_statue__mode_A__guardian_statue_added();
    test__add_guardian_statue__mode_B__exception_is_thrown();
    test__delete_guardian_statue__mode_A__guardian_statue_deleted();
    test__delete_guardian_statue__mode_B__exception_is_thrown();
    test__update_guardian_statue__mode_A__guardian_statue_updated();
    test__update_guardian_statue__mode_B__exception_is_thrown();
    test__next__mode_B__guardian_statue_returned();
    test__next__mode_A__exception_is_thrown();
    test__save__mode_B__guardian_statue_saved();
    test__save__mode_A__exception_is_thrown();
}

void tests() {
    domain_tests();
    file_repo_tests();
    service_tests();
}