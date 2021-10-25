#include <string.h>
#include <stdio.h>

#include "item.h"

void toString(Item item, char itemAsString[])
{
    sprintf(itemAsString, "%d-%s-%s-%d", item.catalogueNumber, item.type, item.state, item.value);
}