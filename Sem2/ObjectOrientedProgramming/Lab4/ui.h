#pragma once

#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#include "service.h"

typedef struct {
    Service *service;
}UI;

UI *create_ui();
void destroy_ui(UI *ui);

/*
 * reads the command from the commandline and passes it as an array
 * @return the number of elements in the array
 */
int read_command(char[][255]);

/*
 * executes the add operation on the service
 * @param ui pointer to ui
 * @param catalogue_number integer representing the catalogue_number of the item
 * @param type string representing the type of the item
 * @param state string representing the state of the item
 * @param value integer representing the value of the item
 */
void add_item_ui(UI *ui, int catalogue_number, char *type, char *state, int value);

/*
 * executes the delete operation on the service
 * @param ui pointer to ui
 * @param catalogue_number integer representing the catalogue_number of the item
 */
void delete_item_ui(UI *ui, int catalogue_number);

/*
 * executes the update operation on the service
 * @param ui pointer to ui
 * @param catalogue_number integer representing the catalogue_number of the item
 * @param type string representing the type of the item
 * @param state string representing the state of the item
 * @param value integer representing the value of the item

 */
void update_item_ui(UI *ui, int catalogue_number, char *type, char *state, int value);

/*
 * lists all the items
 * @param ui pointer to ui
 */
void list_items(UI *ui);

/*
 * lists the item of a specified type
 * @param ui pointer to ui
 * @param type string representing the type of the items
 */
void list_items_by_type(UI *ui, char *type);

/*
 * lists the items of a value lower or equal to the specified value
 * @param ui pointer to ui
 * @param value integer representing the target value
 */
void filter_items(UI *ui, int value);

/*
 * centralizes all UI level functions and runs the app;
 */
void run(UI *ui);