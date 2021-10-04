#pragma once

#include "item.h"

#define MAX_CAPACITY 100

typedef struct {
    Item items[MAX_CAPACITY];
    int size;
}Repo;

/*
 * adds an item into the repository
 * @param address of repository
 * @param the item to be added
 * @return 0 on success, 1 otherwise
 */
int addItemRepo(Repo*, Item);

/*
 * deletes an item from the repository
 * @param address of repository
 * @param the catalogue number of the item to be deleted
 * @return 0 on success, 1 otherwise
 */
int deleteItemRepo(Repo*, int);

/*
 * updates an element from the repository with new values
 * @param address of repository
 * @param the catalogue number of the item to be updated, and
 the new item
 * @return 0 on success, 1 otherwise
 */
int updateItemRepo(Repo*, int, Item);

/*
 * searches an item from the repository by its catalogue number
 * @param address of repository
 * @param the catalogue number of the item
 * @return the index of the item if it is found, -1 otherwise
 */
int searchItem(Repo*, int);

/*
 * copies the array of items in repo
 * @param address of repo
 * @paramempty list of items where the elements of the repo are
 to be copied
 * @return the number of elements copied
 */
int listItemsRepo(Repo*, Item[MAX_CAPACITY]);
