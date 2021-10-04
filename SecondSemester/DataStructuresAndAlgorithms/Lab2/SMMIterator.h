#pragma once

#include "SortedMultiMap.h"


class SMMIterator{
	friend class SortedMultiMap;
private:
	//DO NOT CHANGE THIS PART
	const SortedMultiMap& map;
	SMMIterator(const SortedMultiMap& map);
    List *current_list;
    Node *current_node;

public:
	void first();
	void next();
	bool valid() const;
   	TElem getCurrent() const;
};

