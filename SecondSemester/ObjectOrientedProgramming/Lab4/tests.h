#pragma once

#include <assert.h>
#include <string.h>

#include "item.h"
#include "item_list.h"
#include "repo.h"
#include "service.h"

void tests();

void tests_item();
void tests_item_list();
void tests_repository();
void tests_service();

void test_create_item__valid_parameters__item_is_created_successfully();
void test_copy_item__valid_item__item_copy_is_created_successfully();
void test_item_to_string__valid_item__string_is_correct();

void test_create_item_list__item_list_created_successfully__success();
void test_copy_item_list__valid_initial_list__success();
void test_resize_item_list__valid_initial_list__capacity_doubled();
void test_add_item_to_list__item_does_not_exist__returns_0();
void test_add_item_to_list__item_exists__returns_1();
void test_delete_item_from_list__item_exists__returns_0();
void test_delete_item_from_list__item_does_not_exist__returns_1();
void test_update_item_from_list__item_exists__returns_0();
void test_update_item_from_list__item_does_not_exist__returns_1();
void test_search_item_from_list__item_exists__returns_position();
void test_search_item_from_list__item_exists__returns_negative_1();

void test_add_item_to_repository__item_added_successfully__returns_0();
void test_add_item_to_repository__item_not_added__returns_1();
void test_delete_item_from_repository__item_deleted_successfully__returns_0();
void test_delete_item_from_repository__item_not_deleted__returns_1();
void test_update_item_from_repository__item_updated_successfully__returns_0();
void test_update_item_from_repository__item_not_updated__returns_1();
void test_get_list_of_items__list_of_items_returned__success();
void test_set_list_of_items__list_modified__success();

void test_add_item_service__item_added_successfully__returns_0();
void test_add_item_service__item_not_added__returns_1();
void test_delete_item_service__item_deleted_successfully__returns_0();
void test_delete_item_service__item_not_deleted__returns_1();
void test_update_item_service__item_updated_successfully__returns_0();
void test_update_item_service__item_not_updated__returns_1();
void test_get_all_items_service__item_list_gotten__success();
void test_undo__repo_modified__success();
void test_redo__repo_modified__success();