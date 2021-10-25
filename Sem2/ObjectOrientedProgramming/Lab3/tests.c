#include "tests.h"

 // tests regarding an item
void testItem() {
  Item item = {1, "bread", "moldy", 2};
  assert(item.catalogueNumber == 1);
  assert(strcmp(item.type, "bread") == 0);
  assert(strcmp(item.state, "moldy") == 0);
  assert(item.value == 2);
}

void testToString() {
  Item item = {1, "bread", "moldy", 2};
  char itemAsString[255];
  toString(item, itemAsString);
  assert(strcmp(itemAsString, "1-bread-moldy-2") == 0); 
 } 

  // tests regarding the repo 
 void testAddItemRepo() { 
   Repo repo;
   repo.size = 0;
   Item item = {1, "bread", "moldy", 2}; 
   assert(addItemRepo(&repo, item) == 0); 
   assert(addItemRepo(&repo, item) == 1); 
   assert(repo.size == 1); 
 } 

 void testDeleteItemRepo() { 
   Repo repo;
   repo.size = 0;
   Item item = {1, "bread", "moldy", 2}; 
   addItemRepo(&repo, item); 
   assert(deleteItemRepo(&repo, 3) == 1); 
   assert(deleteItemRepo(&repo, 1) == 0); 
   assert(repo.size == 0); 
 } 

void testUpdateItemRepo() {
   Repo repo;
   repo.size = 0;
   Item item = {1, "bread", "moldy", 2}; 
   Item newItem = {1, "carrots", "fresh", 10}; 
   Item newItemFail = {3, "aaa", "bbb", 3}; 
   addItemRepo(&repo, item); 
   assert(updateItemRepo(&repo, 3, newItemFail) == 1); 
   assert(updateItemRepo(&repo, 1, newItem) == 0); 
   assert(strcmp(repo.items[0].type, "carrots") == 0); 
   assert(strcmp(repo.items[0].state, "fresh") == 0); 
   assert(repo.items[0].value == 10); 
 } 

 void testSearchItemRepo() { 
   Repo repo;
   repo.size = 0;
   Item item = {1, "bread", "moldy", 2}; 
   addItemRepo(&repo, item); 
   assert(searchItem(&repo, 1) == 0); 
   assert(searchItem(&repo, 4) == -1); 
 } 

 void testListItemsRepo() { 
   Repo repo;
   repo.size = 0;
   Item item = {1, "bread", "moldy", 2}; 
   addItemRepo(&repo, item); 
   Item items[MAX_CAPACITY]; 
   listItemsRepo(&repo, items); 
   assert(items[0].catalogueNumber == 1); 
   assert(strcmp(items[0].type, "bread") == 0); 
   assert(strcmp(items[0].state, "moldy") == 0); 
   assert(items[0].value == 2); 
 } 

 // tests regarding the service 
 void testAddItemService() { 
   assert(addItemService(1, "bread", "moldy", 2) == 0); 
   assert(addItemService(1, "bread", "moldy", 2) == 1); 
 } 

 void testDeleteItemService() { 
   assert(deleteItemService(1) == 0); 
   assert(deleteItemService(2) == 1); 
 } 

 void testUpdateItemService() { 
   assert(updateItemService(1, "carrots", "fresh", 10) == 0); 
   assert(updateItemService(3, "aaa", "bbb", 3) == 1); 
 } 

  void testListItemsService() { 
    char items[MAX_CAPACITY][255]; 
    assert(listItemsService(items) == 1); 
  } 

   // one test to rule them all 
  void testAll() { 
    testItem(); 
    testToString(); 
    testAddItemRepo(); 
    testDeleteItemRepo(); 
    testUpdateItemRepo(); 
    testSearchItemRepo(); 
    testListItemsRepo(); 
    testAddItemService(); 
    testUpdateItemService(); 
    testListItemsService(); 
    testDeleteItemService();
}
