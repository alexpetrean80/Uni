#include "ui.h"

#define COMMAND_INDEX 0
#define CATALOGUE_NUMBER_INDEX 1
#define TYPE_INDEX 2
#define STATE_INDEX 3
#define VALUE_INDEX 4
#define LIST_BY_TYPE_INDEX 1
#define FILTER_BY_VALUE_INDEX 1

UI *create_ui() {
    UI *ui = (UI *)malloc(sizeof(UI));
    ui->service = create_service();
    return ui;
}

void destroy_ui(UI *ui) {
    destroy_service(ui->service);
    free(ui);
    ui = NULL;
}

int read_command(char command[][255]) {
    char user_input[255];
    fgets(user_input, 255, stdin);
    user_input[strlen(user_input) - 1] = '\0';
    puts("");
    char *token, *next_token;
    token = strtok_s(user_input, " ", &next_token);
    int i = 0;
    while (token != NULL) {
        strcpy_s(command[i++], strlen(token) + 1, token);
        token = strtok_s(NULL, ", ", &next_token);
    }
    return i;
}

void add_item_ui(UI *ui, int catalogue_number, char *type, char *state, int value) {
    if (add_item_service(ui->service, catalogue_number, type, state, value)) {
        puts("No!");
    }
}

void delete_item_ui(UI *ui, int catalogue_number) {
    if (!delete_item_service(ui->service, catalogue_number)) {
        puts("No!");
    }
}

void update_item_ui(UI *ui, int catalogue_number, char *type, char *state, int value) {
    if (update_item_service(ui->service, catalogue_number, type, state, value)) {
        puts("No!");
    }
}

void list_items(UI *ui) {
    ItemList *list = get_all_items_service(ui->service);
    for (size_t i = 0; i < list->size; i++) {
        puts(to_string(list->items[i]));
    }
    destroy_list(list);
}

void list_items_by_type(UI *ui, char *type) {
    ItemList *list = get_all_items_service(ui->service);
    for (size_t i = 0; i < list->size; i++) {
        if (!strcmp(list->items[i]->type, type)) {
            puts(to_string(list->items[i]));
        }
    }
    destroy_list(list);
}

void filter_items(UI *ui, int value) {
    ItemList *list = get_all_items_service(ui->service);
    for (size_t i = 0; i < list->size; i++) {
        if (list->items[i]->value <= value) {
            puts(to_string(list->items[i]));
        }
    }
    destroy_list(list);
}

void run(UI *ui) {
    char command[6][255];
    int size = 0;
    while (1) {
        size = read_command(command);
        if (!strcmp(command[COMMAND_INDEX], "add")) {
            add_item_ui(ui, atoi(command[CATALOGUE_NUMBER_INDEX]), command[TYPE_INDEX], command[STATE_INDEX], atoi(command[VALUE_INDEX]));
        } else if (!strcmp(command[COMMAND_INDEX], "update")) {
            update_item_ui(ui, atoi(command[CATALOGUE_NUMBER_INDEX]), command[TYPE_INDEX], command[STATE_INDEX], atoi(command[VALUE_INDEX]));
        } else if (!strcmp(command[COMMAND_INDEX], "delete")) {
            delete_item_ui(ui, atoi(command[CATALOGUE_NUMBER_INDEX]));
        } else if (!strcmp(command[COMMAND_INDEX], "list")) {
            if (size == 2) {
                //check whether the word of the command can be converted to an integer
                if (!strtol(command[LIST_BY_TYPE_INDEX], NULL, 10)) {
                    list_items_by_type(ui, command[LIST_BY_TYPE_INDEX]);
                } else {
                    filter_items(ui, atoi(command[FILTER_BY_VALUE_INDEX]));
                }
            } else {
                list_items(ui);
            }

        } else if (!strcmp(command[COMMAND_INDEX], "undo")) {
            undo(ui->service);
        }
        else if (!strcmp(command[COMMAND_INDEX], "redo")) {
            redo(ui->service);
        }
        else if (!strcmp(command[COMMAND_INDEX], "exit")) {
            break;
        }
    }
}


