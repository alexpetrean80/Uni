#pragma once

typedef struct {
    int catalogueNumber;
    char type[50];
    char state[50];
    int value;
}Item;

/*
 * transforms a given item into a string and returns the pointer to that string
 * @param item
 * @return string representing the item attributes
 */
void toString(Item, char[]);
