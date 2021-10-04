#pragma once

#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#include "service.h"

/*
 * reads the command from the commandline and passes it as an array
 * @return the number of elements in the array
 */
int readCommand(char[][255]);

/*
 * reads an item from the command line and passes it to the service
 */
void addItemUI(int, char*, char* ,int);

/*
 * reads a catalogue number from the commandline and passes it to the service
 */
void deleteItemUI(int);

/*
 * reads an item from the command line and passes it to the service 
 */
void updateItemUI(int, char*, char*, int);

void listItems();

void listItemsByType(char*);


/*
 * centralizes all UI level functions and runs the app;
 * */
void run();

