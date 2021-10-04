#ifndef LAB8_UI_H
#define LAB8_UI_H

#include "../service/service.h"

class UI {
private:
    Service service_;
    static std::vector<std::string> read_command();
    void mode_ui(const std::vector<std::string> &command);
    void add_ui(const std::vector<std::string> &command);
    void update_ui(const std::vector<std::string> &command);
    void delete_ui(const std::vector<std::string> &command);
    void list_ui(const std::vector<std::string> &command);
    void my_list_ui(const std::vector<std::string> &command);
    void save_ui(const std::vector<std::string> &command);
    void next_ui(const std::vector<std::string> &command);
    void set_repo_file(const std::vector<std::string> &command);
    void set_my_list_file(const std::vector<std::string> &command);

public:
    UI() = default;
    void run_console();
};

#endif//LAB8_UI_H
