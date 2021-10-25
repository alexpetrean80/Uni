#include "repo.h"

Repository *create_repo() {
    Repository *repo = (Repository *)malloc(sizeof(Repository));
    repo->items = create_list();
    return repo;
}
void destroy_repo(Repository *repo) {
    destroy_list(repo->items);
    free(repo);
    repo = NULL;
}
int add_item_to_repository(Repository *repo, Item *item) {
    return add_item_to_list(repo->items, item);
}
int delete_item_from_repository(Repository *repo, int catalogue_number) {
    return delete_item_from_list(repo->items, catalogue_number);
}
int update_item_from_repository(Repository *repo, Item *new_item) {
    return update_item_from_list(repo->items, new_item);
}
ItemList *get_list_of_items(Repository *repo) {
    return copy_list(repo->items);
}
void set_list_of_items(Repository *repo, ItemList *items) {
    destroy_list(repo->items);
    repo->items = copy_list(items);
}
