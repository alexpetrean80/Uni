#include <string.h>

#include "service.h"

static Repo repo;

int addItemService(int catalogueNumber, char* type, char* state, int value) {
    Item item;
    item.catalogueNumber = catalogueNumber;
    strcpy(item.type, type);
    strcpy(item.state, state);
    item.value = value;
    return addItemRepo(&repo, item);
}

int deleteItemService(int catalogueNumber) {
    return deleteItemRepo(&repo, catalogueNumber);
}

int updateItemService(int catalogueNumber, char* type, char* state, int value) {
    Item item;
    item.catalogueNumber = catalogueNumber;
    strcpy(item.type, type);
    strcpy(item.state, state);
    item.value = value;
    return updateItemRepo(&repo, catalogueNumber, item);
}

int listItemsService(Item items[MAX_CAPACITY]) {
    return listItemsRepo(&repo, items);
}
