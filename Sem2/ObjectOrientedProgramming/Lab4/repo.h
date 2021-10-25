#pragma once

#include "item_list.h"

typedef struct {
    ItemList *items;
} Repository;

/*
 * allocates a new repo on the heap
 * @return pointer to newly created repository
 */
Repository *create_repo();

/*
 * deallocates a repository from the heap
 * @param repo pointer to repo
 */
void destroy_repo(Repository *repo);

/*
 * adds a new item to the repository
 * @param repo pointer to repository
 * @param item pointer to item
 * @return 0 on success, 1 otherwise
 */
int add_item_to_repository(Repository *repo, Item *item);

/*
 * deletes an item from the repository by its catalogue number
 * @param repo pointer to repository
 * @param catalogue_number integer representing the catalogue number of the item
 * @return 0 on success, 1 otherwise
 */
int delete_item_from_repository(Repository *repo, int catalogue_number);

/*
 * updates an item from the repository
 * @param repo pointer to repository
 * @param new_item pointer to new item
 * @return 0 on success, 1 otherwise
 */
int update_item_from_repository(Repository *repo, Item *new_item);

/*
 * returns the items from the repo as an ItemList
 * @param repo pointer to repository
 * @return pointer to a deep copy of the list of items
 */
ItemList *get_list_of_items(Repository *repo);

/*
 * sets a new item list in the repository
 * @param repo pointer to repository
 * @param list pointer to list
 */
void set_list_of_items(Repository *repo, ItemList *items);
