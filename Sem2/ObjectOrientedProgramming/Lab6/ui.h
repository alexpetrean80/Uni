#ifndef LAB6_UI_H
#define LAB6_UI_H

#include <iostream>
#include <sstream>
#include <algorithm>
#include "Service.h"

class UI
{
private:
    Service service_;
    static void read_command(DynamicVector<std::string>&);
    void mode_ui(const DynamicVector<std::string> &command);
    void add_ui(const DynamicVector<std::string> &command);
    void update_ui(const DynamicVector<std::string> &command);
    void delete_ui(const DynamicVector<std::string> &command);
    void list_ui(const DynamicVector<std::string> &command);
    void next_ui(const DynamicVector<std::string> &command);
    void save_ui(const DynamicVector<std::string> &command);
public:
    void run_console();

    void mylist_ui(const DynamicVector<std::string> &command) const;
};

#endif//LAB6_UI_H
