#include "ui.h"

int readCommand(char command[][255]) {
    char userInput[255];
    fgets(userInput, 255, stdin);
    userInput[strlen(userInput) - 1] = '\0';
    puts("");
    char* token = strtok(userInput, " ");
    int i = 0;
    while(token != NULL) {
        strcpy(command[i++], token);
        token = strtok(NULL, ", ");
    }
    return i;
}

void addItemUI(int catalogueNumber, char* type, char* state, int value) {
    if (addItemService(catalogueNumber, type, state, value)) {
            puts("No!");
        }
}

void deleteItemUI(int catalogueNumber) {
    if (deleteItemService(catalogueNumber)) {
        puts("No!");
    }
}

void updateItemUI(int catalogueNumber, char* type, char* state, int value) {
    if (updateItemService(catalogueNumber, type, state, value)) {
        puts("No!");
    }
}

void listItems() {
    Item items[MAX_CAPACITY];
    int size = listItemsService(items);
    char itemAsString[255];
    int count;
    for (count = 0; count < size; count++) {
        toString(items[count], itemAsString);
        puts(itemAsString);
    }
}

void listItemsByType(char* type) {
    Item items[MAX_CAPACITY];
    int size = listItemsService(items);
    int count;
    char itemAsString[255];
    for (count = 0; count < size; count++) {
        toString(items[count], itemAsString);
        if (strstr(itemAsString, type)) {
            puts(itemAsString);
        }
    }
}

void run() {
    char command[6][255];
    int size = 0;
    while (1) {
        size = readCommand(command);
        if (strcmp(command[0], "add") == 0) {
            addItemUI(atoi(command[1]), command[2], command[3], atoi(command[4]));
        } else if (strcmp(command[0], "update") == 0) {
            updateItemUI(atoi(command[1]), command[2], command[3], atoi(command[4]));
        } else if (strcmp(command[0], "delete") == 0) {
            deleteItemUI(atoi(command[1]));
        } else if (strcmp(command[0], "list") == 0) {
            if(size == 2) {
                listItemsByType(command[1]);
            } else {
                listItems();
            }
        } else if (strcmp(command[0], "exit") == 0) {
            return;
        }
    }
}
