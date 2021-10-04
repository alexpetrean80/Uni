#pragma once

#include "item.h"

#define INITIAL_CAPACITY 10

typedef struct{
    Item **items;
    size_t size, capacity;
} ItemList;

/*
 * creates an empty list of pointers to items on the heap
 * @return pointer to newly created ItemList
 */
ItemList *create_list();

/*
 * creates a deep copy of the list on the heap
 * @param list pointer to list to be copied
 * @return pointer to deep copy of the list
 */
ItemList *copy_list(ItemList *list);

/*
 * deallocates the memory of the list
 * @param list pointer to list on the heap
 */
void destroy_list(ItemList *list);

/*
 * resizes the list by doubling it's capacity and reallocating a respective segment on the heap
 * @param list pointer to list
 */
void resize_list(ItemList *list);

/*
 * pushes a new pointer to item on the list
 * @param list pointer to list
 * @param item pointer to item
 * @return 0 on success, 1 otherwise
 */
int add_item_to_list(ItemList *list, Item *item);

/*
 * deletes an item from the list and deallocates it
 * @param list pointer to list
 * @param catalogue_number integer representing the catalogue number from the list
 * @return 0 on success, 1 otherwise
 */
int delete_item_from_list(ItemList *list, int catalogue_number);

/*
 * updates an item from the list with a new item by its catalogue number
 * @param list pointer to list
 * @param new_item pointer to new item
 * @return 0 on success, 1 otherwise
 */
int update_item_from_list(ItemList *list, Item *new_item);

/*
 * searches for an item in the list by its catalogue number
 * @param list pointer to list
 * @param catalogue_number integer representing the catalogue number of the item to be found
 * @return index of the item in list if it exists, -1 otherwise
 */
int search_item_from_list(ItemList *list, int catalogue_number);