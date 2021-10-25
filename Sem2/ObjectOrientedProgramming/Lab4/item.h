#pragma once

#include <stdlib.h>
#include <string.h>
#include <stdio.h>

typedef struct {
    int catalogue_number;
    char *type;
    char *state;
    int value;
} Item;

/*
 * allocates a new item on the heap and returns it's address on the heap as a pointer
 * @param1 catalogue_number integer representing the catalogue number of the item
 * @param2 type string representing the type of the item
 * @param3 state string representing the state of the item
 * @param4 value integer representing the value of the item
 * @return pointer to item from the heap
 */
Item *create_item(int catalogue_number, char *type, char *state, int value);

/*
 * creates a deep copy of the item, and returns the copy's address on the heap as a pointer
 * @param1 item pointer to item on the heap
 * @return pointer to the copy of the item on the heap
 */
Item * copy_item(Item *item);

/*
 * deallocates the memory of the item from the heap, and stores NULL to the item pointer
 * @param item pointer to item on the heap
 */
void destroy_item(Item *item);

/*
 * returns a string containing the attributes of the item
 * @param item pointer to item on the heap
 * @return string representing the item
 */
char *to_string(Item *item);