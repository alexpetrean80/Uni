#include "item_list.h"


ItemList *create_list() {
    ItemList *list = (ItemList *)malloc(sizeof(ItemList));
    list->items = (Item **)malloc(sizeof(Item *) * INITIAL_CAPACITY);
    list->size = 0;
    list->capacity = INITIAL_CAPACITY;
    return list;
}
ItemList *copy_list(ItemList *list) {
    ItemList *list_copy = (ItemList *)malloc(sizeof(ItemList));
    list_copy->items = (Item **)malloc(sizeof(Item *) * list->capacity);
    for (size_t i = 0; i < list->size; i++) {
        list_copy->items[i] = copy_item(list->items[i]);
    }
    list_copy->size = list->size;
    list_copy->capacity = list->capacity;
    return list_copy;
}
void destroy_list(ItemList *list) {
    if (list->size) {
        for (size_t i = 0; i < list->size; i++) {
            destroy_item(list->items[i]);
        }
    }
    free(list->items);
    list->items = NULL;
    free(list);
    list = NULL;
}
void resize_list(ItemList *list) {
    list->items = (Item **)realloc(list->items, 2 * list->capacity);
    list->capacity *= 2;
}
int add_item_to_list(ItemList *list, Item *item) {
    if (list->size == list->capacity) {
        resize_list(list);
    }
    if (search_item_from_list(list, item->catalogue_number) == -1) {
        list->items[list->size++] = item;
        return 0;
    }
    return 1;
}
int delete_item_from_list(ItemList *list, int catalogue_number) {
    int index = search_item_from_list(list, catalogue_number);
    if (index == -1) {
        return 1;
    }
    destroy_item(list->items[index]);
    for (size_t i = index; i < list->size - 1; i++) {
        list->items[i] = list->items[i + 1];
    }
    list->size--;
    return 0;
}
int search_item_from_list(ItemList *list, int catalogue_number) {
    for (size_t i = 0; i < list->size; i++) {
        if (list->items[i]->catalogue_number == catalogue_number) {
            return i;
        }
    }
    return -1;
}
int update_item_from_list(ItemList *list, Item *new_item) {
    int index = search_item_from_list(list, new_item->catalogue_number);
    if (index == -1) {
        return 1;
    }
    destroy_item(list->items[index]);
    list->items[index] = new_item;
    return 0;
}
