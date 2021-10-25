#pragma once

#include "repo.h"

typedef struct {
    Repository *repo;
    ItemList **undo_list;
    ItemList **redo_list;
    size_t undo_size, undo_capacity, redo_size, redo_capacity;
} Service;

/*
 * allocates a service on the heap
 * @return pointer to created service
 */
Service *create_service();

/*
 * deallocates a service from the heap
 * @param service pointer to service
 */
void destroy_service(Service *service);

/*
 * creates a new item and adds it to the repo
 * @param service pointer to service
 * @param catalogue_number integer representing the catalogue_number of the item
 * @param type string representing the type of the item
 * @param state string representing the state of the item
 * @param value integer representing the value of the item
 * return 0 on success, 1 otherwise
 */
int add_item_service(Service *service, int catalogue_number, char *type, char *state, int value);

/*
 * deletes an item from the repo
 * @param service pointer to service
 * @param catalogue_number integer representing the catalogue number of the item
 * @return 0 on success, 1 otherwise
 */
int delete_item_service(Service *service, int catalogue_number);

/*
 * updates an item from the repo with new attributes
 * @param service pointer to service
 * @param catalogue_number integer representing the catalogue_number of the item
 * @param type string representing the type of the item
 * @param state string representing the state of the item
 * @param value integer representing the value of the item
 * return 0 on success, 1 otherwise
 */
int update_item_service(Service *service, int catalogue_number, char *new_type, char *new_state, int new_value);

/*
 * returns all the items from the repository
 * @param service pointer to service
 * @return list of items
 */
ItemList *get_all_items_service(Service *service);

/*
 * restores the repository to a previous state
 * @param service pointer to service
 */
void undo(Service *service);

/*
 * restores the repository to a state before an undo operation and clears the undo stack
 * @param service pointer to service
 */
void redo(Service *service);

/*
 * pushes the items from the repo on the undo stack
 * @param service pointer to service
 */
void push_to_undo(Service *service);

/*
 * pushes the last list on the undo_stack inside the repo
 * @param service pointer to service
 */
void pop_from_undo(Service *service);

/*
 * pushes the items from the repo on the redo stack
 * param service pointer to service
 */
void push_to_redo(Service *service);

/*
 * pops the last list of items from the redo stack on the repo
 * param service pointer to service
 */
void pop_from_redo(Service *service);




