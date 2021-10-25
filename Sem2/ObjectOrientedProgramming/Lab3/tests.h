#pragma once

#include <assert.h>
#include <string.h>

#include "item.h"
#include "repo.h"
#include "service.h"

// tests regarding an item
void testItem();
void testToString();

// tests regarding the repo
void testAddItemRepo();
void testDeleteItemRepo();
void testUpdateItemRepo();
void testSearchItemRepo();
void testListItemsRepo();

//tests regarding the service
void testAddItemService();
void testDeleteItemService();
void testUpdateItemService();
void testListItemsService();

//one test to rule them all
void testAll();
