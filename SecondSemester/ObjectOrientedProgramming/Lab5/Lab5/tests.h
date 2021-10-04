#pragma once

#include <cassert>

#include "GuardianStatue.h"
#include "DynamicVector.h"
#include "Repo.h"
#include "Service.h"

//guardian statue tests
void test_create_guardian_statue__valid_data__guardian_statue_created();
void test_to_string__guardian_statue_converted_to_string__success();
void test_operator_equality__guardian_statues_equal__true();
void test_operator_equality__guardian_statues_not_equal__false();
void test_operator_non_equality__guardian_statues_not_equal__true();
void test_operator_non_equality__guardian_statues_equal__false();
void test_operator_initialization__guardian_statue_initialized__true();

void tests_guardian_statue();

//dynamic vector tests
void test_create_dynamic_vector_default_constructor_dynamic_vector_is_created();
void test_create_dynamic_vector_copy_constructor_dynamic_vector_is_created();
void test_push_back_one_int_int_is_added();
void test_push_back_many_int_all_int_are_added();
void test_pop_back_valid_position_item_on_position_is_removed();
void test_pop_back_invalid_position_exception_thrown();
void test_at_valid_position_item_on_position_is_returned();
void test_at_invalid_position_exception_thrown();
void test_insert_valid_position_item_is_added_on_position();
void test_insert_invalid_position_exception_thrown();

void tests_dynamic_vector();

//repo tests
void test_add_repo_valid_statue_statue_added();
void test_remove_repo_valid_string_statue_deleted();
void test_update_repo_valid_statue_statue_updated();
void test_get_statues_repo_valid_statues_returned();
void tests_repo();

//service tests
void test_add_service_mode_a_statue_added();
void test_add_service_mode_b_exception_thrown();
void test_delete_service_mode_a_statue_deleted();
void test_delete_service_mode_b_exception_thrown();
void test_update_service_mode_a_statue_updated();
void test_update_service_mode_b_exception_thrown();
void test_get_statues_service_valid_statues_returned();
void tests_service();

void tests();