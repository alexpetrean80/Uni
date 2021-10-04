
#include "service.h"

Service *create_service() {
    Service *service = (Service *)malloc(sizeof(Service));
    service->repo = create_repo();
    service->undo_list = (ItemList **)malloc(sizeof(ItemList *) * INITIAL_CAPACITY);
    service->redo_list = (ItemList **)malloc(sizeof(ItemList *) * INITIAL_CAPACITY);
    service->undo_size = service->redo_size = 0;
    service->undo_capacity = service->redo_capacity = INITIAL_CAPACITY;
}

void destroy_service(Service *service) {
    destroy_repo(service->repo);
    for (size_t i = 0; i < service->undo_size; i++) {
        destroy_list(service->undo_list[i]);
    }
    for (size_t i = 0; i < service->redo_size; i++) {
        destroy_list(service->undo_list[i]);
    }
    free(service->undo_list);
    service->undo_list = NULL;
    free(service->redo_list);
    service->redo_list = NULL;
    free(service);
    service = NULL;
}

int add_item_service(Service *service, int catalogue_number, char *type, char *state, int value) {
    Item *item = create_item(catalogue_number, type, state, value);
    push_to_undo(service);
    return add_item_to_repository(service->repo, item);
}

int delete_item_service(Service *service, int catalogue_number) {
    push_to_undo(service);
    return delete_item_from_repository(service->repo, catalogue_number);
}

int update_item_service(Service *service, int catalogue_number, char *new_type, char *new_state, int new_value) {
    Item *new_item = create_item(catalogue_number, new_type, new_state, new_value);
    push_to_undo(service);
    return update_item_from_repository(service->repo, new_item);
}

ItemList *get_all_items_service(Service *service) {
    return get_list_of_items(service->repo);
}

void undo(Service *service) {
    push_to_redo(service);
    pop_from_undo(service);
}

void redo(Service *service) {
    pop_from_redo(service);
    if (service->undo_size) {
        for (size_t i = 0; i < service->undo_size; i++) {
            destroy_list(service->undo_list[i]);
        }
        service->undo_size = 0;
    }
}

void push_to_undo(Service *service) {
    if (service->undo_size == service->undo_capacity) {
        service->undo_capacity *= 2;
        service->undo_list = (ItemList **)realloc(service->undo_list, service->undo_capacity);
    }
    service->undo_list[service->undo_size++] = get_list_of_items(service->repo);
}

void pop_from_undo(Service *service) {
    set_list_of_items(service->repo, service->undo_list[service->undo_size - 1]);
    destroy_list(service->undo_list[--service->undo_size]);
}

void push_to_redo(Service *service) {
    if (service->redo_size == service->redo_capacity) {
        service->redo_capacity *= 2;
        service->redo_list = (ItemList **)realloc(service->redo_list, service->redo_capacity);
    }
    service->redo_list[service->redo_size++] = get_list_of_items(service->repo);
}

void pop_from_redo(Service *service) {
    set_list_of_items(service->repo, service->redo_list[service->redo_size - 1]);
    destroy_list(service->redo_list[--service->redo_size]);
}
