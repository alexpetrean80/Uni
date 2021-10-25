#include "tests_domain.h"

void test__create_guardian_statue__default_constructor__empty_statue_created() {
    auto *guardian_statue = new GuardianStatue();
    assert(guardian_statue->get_power_word_name().empty() && guardian_statue->get_material().empty() && guardian_statue->get_age() == 0 && guardian_statue->get_corporeal_form().empty());
    delete guardian_statue;
}

void test__create_guardian_statue__explicit_constructor__statue_created() {
    auto *guardian_statue = new GuardianStatue("aa", "bb", 1, "cc");
    assert(guardian_statue->get_power_word_name() == "aa" && guardian_statue->get_material() == "bb" && guardian_statue->get_age() == 1 && guardian_statue->get_corporeal_form() == "cc");
    delete guardian_statue;
}

void test__create_guardian_statue__copy_constructor__copy_statue_created() {
    auto *guardian_statue = new GuardianStatue("aa", "bb", 1, "cc");
    auto *new_guardian_statue = new GuardianStatue(*guardian_statue);
    assert(new_guardian_statue->get_power_word_name() == "aa" && new_guardian_statue->get_material() == "bb" && new_guardian_statue->get_age() == 1 && new_guardian_statue->get_corporeal_form() == "cc");
    delete guardian_statue;
    delete new_guardian_statue;
}

void test__set_power_word_name__valid_power_word_name__power_word_name_set() {
    auto *guardian_statue = new GuardianStatue();
    guardian_statue->set_power_word_name("aaa");
    assert(guardian_statue->get_power_word_name() == "aaa");
    delete guardian_statue;
}

void test__set_material__valid_material__material_set() {
    auto *guardian_statue = new GuardianStatue();
    guardian_statue->set_material("aaa");
    assert(guardian_statue->get_material() == "aaa");
    delete guardian_statue;
}

void test__set_age__valid_age__age_set() {
    auto *guardian_statue = new GuardianStatue();
    guardian_statue->set_age(1);
    assert(guardian_statue->get_age() == 1);
    delete guardian_statue;
}

void test__set_corporeal_form__valid_corporeal_form__corporeal_form_set() {
    auto *guardian_statue = new GuardianStatue();
    guardian_statue->set_corporeal_form("aa");
    assert(guardian_statue->get_corporeal_form() == "aa");
    delete guardian_statue;
}

void test__equality__equal_statues__returns_true() {
    auto *first_guardian_statue = new GuardianStatue("aa", "bb", 1, "cc");
    auto *second_guardian_statue = new GuardianStatue("aa", "bb", 1, "cc");
    assert(*first_guardian_statue == *second_guardian_statue);
    delete first_guardian_statue;
    delete second_guardian_statue;
}

void test__equality__not_equal_statues__returns_false() {
    auto *first_guardian_statue = new GuardianStatue("aa", "bb", 1, "cc");
    auto *second_guardian_statue = new GuardianStatue("ab", "bc", 2, "cd");
    assert(!(*first_guardian_statue == *second_guardian_statue));
    delete first_guardian_statue;
    delete second_guardian_statue;
}

void test__not_equality__not_equal_statues__returns_true() {
    auto *first_guardian_statue = new GuardianStatue("aa", "bb", 1, "cc");
    auto *second_guardian_statue = new GuardianStatue("ab", "bc", 2, "cd");
    assert(*first_guardian_statue != *second_guardian_statue);
    delete first_guardian_statue;
    delete second_guardian_statue;
}

void test__not_equality__equal_statues__returns_false() {
    auto *first_guardian_statue = new GuardianStatue("aa", "bb", 1, "cc");
    auto *second_guardian_statue = new GuardianStatue("aa", "bb", 1, "cc");
    assert(!(*first_guardian_statue != *second_guardian_statue));
    delete first_guardian_statue;
    delete second_guardian_statue;
}

void test__to_string__valid_guardian_statue__returns_statue_as_string() {
    auto *guardian_statue = new GuardianStatue("aa", "bb", 1, "cc");
    assert(guardian_statue->to_string() == "aa bb 1 cc");
    delete guardian_statue;
}

void test__validate__valid_input__guardian_statue_validated() {
    GuardianStatue statue{"aa", "bb", 1, "cc"};
    GuardianStatueValidator::validate(statue);
    assert(true);
}

void test__to_csv__valid_guardian_statue__returns_statue_as_csv() {
    GuardianStatue statue {"aa", "bb", 1, "cc"};
    assert(statue.to_csv() == "aa,bb,1,cc");
}

void test__to_html__valid_guardian_statue__returns_statue_as_html_table_row() {
    GuardianStatue statue {"aa", "bb", 1, "cc"};
    assert(statue.to_html() == "<tr>\n<td>aa</td><td>bb</td><td>1</td><td>cc</td>\n</tr>");
}

void test__validate__invalid_input__exception_is_thrown() {
    try {
        GuardianStatue statue {"aa", "bb", -1, "cc"};
        GuardianStatueValidator::validate(statue);
        assert(false);
    } catch (ValidatorException& exception) {
    }
}

void test__validator_exception_what__valid_message__message_is_returned() {
    ValidatorException exception{"test"};
    assert(!strcmp(exception.what(), "test"));
}

void test__repository_exception_what__valid_message__message_is_returned() {
    RepositoryException exception{"test"};
    assert(!strcmp(exception.what(), "test"));
}

void test__service_exception_what__valid_message__message_is_returned() {
    ServiceException exception{"test"};
    assert(!strcmp(exception.what(), "test"));
}
