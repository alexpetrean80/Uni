#ifndef LAB7_UI_H
#define LAB7_UI_H

#include "../service/service.h"
#include <algorithm>
#include <iostream>
#include <sstream>
class UI {
private:
    Service service_;
    static std::vector<std::string> read_command();
    void mode_ui(const std::vector<std::string> &command);
    void add_ui(const std::vector<std::string> &command);
    void update_ui(const std::vector<std::string> &command);
    void delete_ui(const std::vector<std::string> &command);
    void list_ui(const std::vector<std::string> &command);
    void set_file_ui(const std::vector<std::string> &command);
    void save_ui(const std::vector<std::string> &command);
    void next_ui(const std::vector<std::string> &command);
    void my_list_ui(const std::vector<std::string> &command);

public:
    UI();
    void run_console();
};


#endif//LAB7_UI_H
