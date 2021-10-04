#include "item.h"

Item *create_item(int catalogue_number, char *type, char *state, int value) {
    Item *item = (Item *)malloc(sizeof(Item));
    item->catalogue_number = catalogue_number;
    item->type = (char *)malloc(strlen(type) + 1);
    strcpy_s(item->type, strlen(type) + 1, type);
    item->state = (char *)malloc(strlen(type) + 1);
    strcpy_s(item->state, strlen(state) + 1, state);
    item->value = value;
    return item;
}
Item *copy_item(Item *item) {
    Item *item_copy = (Item *)malloc(sizeof(Item));
    item_copy->catalogue_number = item->catalogue_number;
    item_copy->type = (char *)malloc(strlen(item->type) + 1);
    strcpy_s(item_copy->type, strlen(item->type) + 1, item->type);
    item_copy->state = (char *)malloc(strlen(item->type) + 1);
    strcpy_s(item_copy->state, strlen(item->state) + 1, item->state);
    item_copy->value = item->value;
    return item_copy;
}

void destroy_item(Item *item) {
    if (item != NULL) {
        if (item->type != NULL) {
            free(item->type);
        }
        if (item->state != NULL) {
            free(item->state);
        }
        free(item);
        item = NULL;
    }
}
char *to_string(Item *item) {
    size_t size = strlen(item->type) + strlen(item->state) + 2 * sizeof(int) + 4;
    size += strlen(item->type);
    size += strlen(item->state);
    size += 2 * sizeof(int);
    char *item_as_string = (char *)malloc(size);
    sprintf_s(item_as_string, size, "%d %s %s %d", item->catalogue_number, item->type, item->state, item->value);
    return item_as_string;
}
