#include "tests.h"

//main tests function
void tests() {
    tests_item();
    tests_item_list();
    tests_repository();
    tests_service();
}

// centralizer for item level tests
void tests_item() {
    test_create_item__valid_parameters__item_is_created_successfully();
    test_copy_item__valid_item__item_copy_is_created_successfully();
    test_item_to_string__valid_item__string_is_correct();
}

// centralizer for item list level tests
void tests_item_list() {
    test_create_item_list__item_list_created_successfully__success();
    test_copy_item_list__valid_initial_list__success();
    test_resize_item_list__valid_initial_list__capacity_doubled();
    test_add_item_to_list__item_does_not_exist__returns_0();
    test_add_item_to_list__item_exists__returns_1();
    test_delete_item_from_list__item_exists__returns_0();
    test_delete_item_from_list__item_does_not_exist__returns_1();
    test_update_item_from_list__item_exists__returns_0();
    test_update_item_from_list__item_does_not_exist__returns_1();
    test_search_item_from_list__item_exists__returns_position();
    test_search_item_from_list__item_exists__returns_negative_1();
}

//centralizer for repository level tests
void tests_repository() {
    test_add_item_to_repository__item_added_successfully__returns_0();
    test_add_item_to_repository__item_not_added__returns_1();
    test_delete_item_from_repository__item_deleted_successfully__returns_0();
    test_delete_item_from_repository__item_not_deleted__returns_1();
    test_update_item_from_repository__item_updated_successfully__returns_0();
    test_update_item_from_repository__item_not_updated__returns_1();
    test_get_list_of_items__list_of_items_returned__success();
    test_set_list_of_items__list_modified__success();
}

//centralizer for service level tests
void tests_service() {
    test_add_item_service__item_added_successfully__returns_0();
    test_add_item_service__item_not_added__returns_1();
    test_delete_item_service__item_deleted_successfully__returns_0();
    test_delete_item_service__item_not_deleted__returns_1();
    test_update_item_service__item_updated_successfully__returns_0();
    test_update_item_service__item_not_updated__returns_1();
    test_get_all_items_service__item_list_gotten__success();
    test_undo__repo_modified__success();
    test_redo__repo_modified__success();
}

//item level tests
void test_create_item__valid_parameters__item_is_created_successfully() {
    Item *item = create_item(1, "aaa", "bbb", 2);
    assert(item->catalogue_number == 1 && !strcmp(item->type, "aaa") && !strcmp(item->state, "bbb") && item->value == 2);
    destroy_item(item);
}
void test_copy_item__valid_item__item_copy_is_created_successfully() {
    Item *item = create_item(1, "aaa", "bbb", 2);
    Item *item_copy = copy_item(item);
    destroy_item(item);
    assert(item_copy->catalogue_number == 1 && !strcmp(item_copy->type, "aaa") && !strcmp(item_copy->state, "bbb") && item_copy->value == 2);
    destroy_item(item_copy);
}
void test_item_to_string__valid_item__string_is_correct() {
    Item *item = create_item(1, "aaa", "bbb", 2);
    char *item_as_string = to_string(item);
    assert(!strcmp(item_as_string, "1 aaa bbb 2"));
    destroy_item(item);
}

//item list level tests
void test_create_item_list__item_list_created_successfully__success() {
    ItemList *list = create_list();
    assert(list->size == 0 && list->capacity == INITIAL_CAPACITY);
    destroy_list(list);
}
void test_copy_item_list__valid_initial_list__success() {
    ItemList *list = create_list();
    Item *item = create_item(1, "aaa", "bbb", 2);
    add_item_to_list(list, item);
    ItemList *copy = copy_list(list);
    destroy_list(list);
    assert(copy->items[0]->catalogue_number == 1 && !strcmp(copy->items[0]->type, "aaa") && !strcmp(copy->items[0]->state, "bbb") && copy->items[0]->value == 2);
    destroy_list(copy);
}
void test_resize_item_list__valid_initial_list__capacity_doubled() {
    ItemList *list = create_list();
    resize_list(list);
    assert(list->capacity == 2 * INITIAL_CAPACITY);
    destroy_list(list);
}
void test_add_item_to_list__item_does_not_exist__returns_0() {
    ItemList *list = create_list();
    Item *item = create_item(1, "aaa", "bbb", 2);
    assert(!add_item_to_list(list, item));
    destroy_list(list);
}
void test_add_item_to_list__item_exists__returns_1() {
    ItemList *list = create_list();
    Item *item = create_item(1, "aaa", "bbb", 2);
    Item *copy = copy_item(item);
    add_item_to_list(list, item);
    assert(add_item_to_list(list, copy));
    destroy_list(list);
    destroy_item(copy);
}
void test_delete_item_from_list__item_exists__returns_0() {
    ItemList *list = create_list();
    Item *item = create_item(1, "aaa", "bbb", 2);
    add_item_to_list(list, item);
    assert(!delete_item_from_list(list, 1));
    destroy_list(list);
}
void test_delete_item_from_list__item_does_not_exist__returns_1() {
    ItemList *list = create_list();
    assert(delete_item_from_list(list, 1));
    destroy_list(list);
}
void test_update_item_from_list__item_exists__returns_0() {
    ItemList *list = create_list();
    Item *item = create_item(1, "aaa", "bbb", 2);
    Item *new_item = create_item(1, "abc", "bcd", 3);
    add_item_to_list(list, item);
    assert(!update_item_from_list(list, new_item));
    destroy_list(list);
}
void test_update_item_from_list__item_does_not_exist__returns_1() {
    ItemList *list = create_list();
    Item *new_item = create_item(1, "abc", "bcd", 3);
    assert(update_item_from_list(list, new_item));
    destroy_list(list);
}
void test_search_item_from_list__item_exists__returns_position() {
    ItemList *list = create_list();
    Item *item = create_item(1, "abc", "bcd", 3);
    add_item_to_list(list, item);
    assert(search_item_from_list(list, 1) == 0);
    destroy_list(list);
}
void test_search_item_from_list__item_exists__returns_negative_1() {
    ItemList *list = create_list();
    assert(search_item_from_list(list, 1) == -1);
    destroy_list(list);
}

// repository level tests
void test_add_item_to_repository__item_added_successfully__returns_0() {
    Repository *repo = create_repo();
    Item *item = create_item(1, "abc", "bcd", 3);
    assert(!add_item_to_repository(repo, item));
    destroy_repo(repo);
}
void test_add_item_to_repository__item_not_added__returns_1() {
    Repository *repo = create_repo();
    Item *item = create_item(1, "abc", "bcd", 3);
    Item *new_item = copy_item(item);
    add_item_to_repository(repo, item);
    assert(add_item_to_repository(repo, new_item));
    destroy_repo(repo);
    destroy_item(new_item);
}
void test_delete_item_from_repository__item_deleted_successfully__returns_0() {
    Repository *repo = create_repo();
    Item *item = create_item(1, "abc", "bcd", 3);
    add_item_to_repository(repo, item);
    assert(!delete_item_from_repository(repo, 1));
    destroy_repo(repo);
}
void test_delete_item_from_repository__item_not_deleted__returns_1() {
    Repository *repo = create_repo();
    assert(delete_item_from_repository(repo, 1));
    destroy_repo(repo);
}
void test_update_item_from_repository__item_updated_successfully__returns_0() {
    Repository *repo = create_repo();
    Item *item = create_item(1, "abc", "bcd", 3);
    add_item_to_repository(repo, item);
    Item *new_item = create_item(1, "abc", "bcd", 20);
    assert(!update_item_from_repository(repo, new_item));
    destroy_repo(repo);
}
void test_update_item_from_repository__item_not_updated__returns_1() {
    Repository *repo = create_repo();
    Item *new_item = create_item(1, "abc", "bcd", 20);
    assert(update_item_from_repository(repo, new_item));
    destroy_repo(repo);
}
void test_get_list_of_items__list_of_items_returned__success() {
    Repository *repo = create_repo();
    Item *item = create_item(1, "aaa", "bbb", 3);
    add_item_to_repository(repo, item);
    ItemList *list = get_list_of_items(repo);
    assert(list->items[0]->catalogue_number == item->catalogue_number && !strcmp(list->items[0]->type, item->type) && !strcmp(list->items[0]->state, item->state) && list->items[0]->value == item->value);
    destroy_repo(repo);
    destroy_list(list);
}
void test_set_list_of_items__list_modified__success() {
    Repository *repo = create_repo();
    ItemList *list = create_list();
    Item *item = create_item(1, "aaa", "bbb", 3);
    add_item_to_list(list, item);
    set_list_of_items(repo, list);
    assert(list->items[0]->catalogue_number == item->catalogue_number && !strcmp(list->items[0]->type, item->type) && !strcmp(list->items[0]->state, item->state) && list->items[0]->value == item->value);
    destroy_list(list);
    destroy_repo(repo);
}

// service level tests
void test_add_item_service__item_added_successfully__returns_0() {
    Service *service = create_service();
    assert(!add_item_service(service, 1, "aaa", "bbb", 2));
    destroy_service(service);
}
void test_add_item_service__item_not_added__returns_1() {
    Service *service = create_service();
    add_item_service(service, 1, "aaa", "bbb", 2);
    assert(add_item_service(service, 1, "aaa", "bbb", 2));
    destroy_service(service);
}
void test_delete_item_service__item_deleted_successfully__returns_0() {
    Service *service = create_service();
    add_item_service(service, 1, "aaa", "bbb", 2);
    assert(!delete_item_service(service, 1));
    destroy_service(service);
}
void test_delete_item_service__item_not_deleted__returns_1() {
    Service *service = create_service();
    assert(delete_item_service(service, 1));
    destroy_service(service);
}
void test_update_item_service__item_updated_successfully__returns_0() {
    Service *service = create_service();
    add_item_service(service, 1, "aaa", "bbb", 2);
    assert(!update_item_service(service, 1, "abc", "bcd", 3));
    destroy_service(service);
}
void test_update_item_service__item_not_updated__returns_1() {
    Service *service = create_service();
    assert(update_item_service(service, 1, "abc", "bcd", 3));
    destroy_service(service);
}
void test_get_all_items_service__item_list_gotten__success() {
    Service *service = create_service();
    add_item_service(service, 1, "aaa", "bbb", 2);
    ItemList *list = get_all_items_service(service);
    assert(list->size == 1);
    destroy_service(service);
}

void test_undo__repo_modified__success() {
    Service *service = create_service();
    add_item_service(service, 1, "aaa", "bbb", 2);
    undo(service);
    ItemList *list = get_all_items_service(service);
    assert(list->size == 0);
    destroy_service(service);
}

void test_redo__repo_modified__success() {
    Service *service = create_service();
    add_item_service(service, 1, "aaa", "bbb", 2);
    undo(service);
    redo(service);
    ItemList *list = get_all_items_service(service);
    assert(list->size == 1 && service->undo_size == 0);
    destroy_service(service);
}
