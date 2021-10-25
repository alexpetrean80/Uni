#include "repo.h"


int addItemRepo(Repo *repo, Item item) {
    if (repo->size == MAX_CAPACITY || searchItem(repo, item.catalogueNumber) != -1) {
        return 1; //if the repo is full, a new item cannot be added
}
    repo->items[repo->size++] = item; // item is added and size is incremented
    return 0;
}

int deleteItemRepo(Repo* repo, int catalogueNumber) {
    int index = searchItem(repo, catalogueNumber);
    if (index == -1) {
        return 1; // the item doesn't exist
    }
    int count;
    for (count = index; count < repo->size - 1; count ++) {
        repo->items[count] = repo->items[count + 1];
    } // from the element to be deleted untill the end, every element overrides its precedent
    repo->size--; // size is decremented by one
    return 0;
}

int updateItemRepo(Repo* repo, int catalogueNumber, Item newItem) {
    int index = searchItem(repo, catalogueNumber);
    if (index == -1) {
        return 1; // the item doesn't exist
    }
    repo->items[index] = newItem; // item is replaced by the new one
    return 0;
}

int searchItem(Repo* repo, int catalogueNumber) {
    int count;
    for (count = 0; count < repo->size; count++) {
        if (repo->items[count].catalogueNumber == catalogueNumber)
            return count; // when the item is found, its index in the array is returned
    }
    return -1; // if the loop has ended, the element does not exist
}

int listItemsRepo(Repo* repo, Item items[MAX_CAPACITY]) {
    int count, size = 0;
    for (count = 0; count < repo->size; count++) {
        items[count] = repo->items[count];
        size++;
    }
    return size;
}
