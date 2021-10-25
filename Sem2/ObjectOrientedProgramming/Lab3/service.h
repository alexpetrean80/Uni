#pragma once

#include "repo.h"

/*
 * creates an item and passes it inside the repo
 * @param catalogue number of item
 * @param type of item
 * @param state of item
 * @param value of item
 * @return 0 on success, 1 otherwise
 */
int addItemService(int, char*, char*, int);

/*
 * gets the catalogue number of an item to be deleted and passes it to the repo
 * @param the catalogue number of the item
 * @return 0 on success, 1 otherwise
 */
int deleteItemService(int);

/*
 * creates the new item and passes it to the service along with the catalogue number
 of the item to be updated
 * @param the catalogue number of the items
 * @param the new type of the item
 * @param the new state of the item
 * @the new value of the item
 * @return 0 on success, 1 otherwise
 */
int updateItemService(int, char*, char*, int);

/*
 * gets a copy of the items in the repo
 * @param the array in which the items will be copied
 * @return number of items in the copied array
 */
int listItemsService(Item[MAX_CAPACITY]);
