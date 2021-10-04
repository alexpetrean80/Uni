#define COMMAND_INDEX 0
#define POWER_WORD_NAME_INDEX 1
#define MODE_INDEX 1
#define MATERIAL_INDEX 2
#define AGE_INDEX 3
#define CORPOREAL_FORM_INDEX 4
#define MODE_COMMAND_SIZE 2
#define ADD_COMMAND_SIZE 5
#define DELETE_COMMAND_SIZE 2
#define UPDATE_COMMAND_SIZE 5
#define LIST_COMMAND_SIZE 1
#define EXIT_COMMAND_SIZE 1
#define MODE_FIRST_LETTER 0
#define SET_FILE_COMMAND_SIZE 2
#define FILE_INDEX 1
#define SAVE_COMMAND_SIZE 1
#define NEXT_COMMAND_SIZE 1
#define MY_LIST_COMMAND_SIZE 1

#include "ui.h"

UI::UI() = default;

void UI::run_console() {
    std::vector<std::string> command;
    while (true) {
        command = read_command();
        if (command.at(COMMAND_INDEX) == "mode") {
            mode_ui(command);
        } else if (command.at(COMMAND_INDEX) == "add") {
            add_ui(command);
        } else if (command.at(COMMAND_INDEX) == "delete") {
            delete_ui(command);
        } else if (command.at(COMMAND_INDEX) == "update") {
            update_ui(command);
        } else if (command.at(COMMAND_INDEX) == "list") {
            list_ui(command);
        } else if (command.at(COMMAND_INDEX) == "fileLocation") {
            set_file_ui(command);
        } else if (command.at(COMMAND_INDEX) == "save") {
            save_ui(command);
        } else if (command.at(COMMAND_INDEX) == "next") {
            next_ui(command);
        } else if (command.at(COMMAND_INDEX) == "mylist") {
            my_list_ui(command);
        } else if (command.at(COMMAND_INDEX) == "exit") {
            if (command.size() == EXIT_COMMAND_SIZE) {
                return;
            } else {
                std::cout << "Invalid arguments.";
            }
        } else {
            std::cout << "Invalid command.";
        }
    }
}

std::vector<std::string> UI::read_command() {
    std::vector<std::string> command;
    std::string input, token;
    std::getline(std::cin, input);
    std::stringstream input_stream(input);
    std::getline(input_stream, token, ' ');
    command.push_back(token);
    while (std::getline(input_stream, token, ',')) {
        auto begin = token.find_first_not_of(' ');
        auto end = token.find_last_not_of(' ');
        auto range = end - begin + 1;
        command.push_back(token.substr(begin, range));
    }
    return command;
}

void UI::mode_ui(const std::vector<std::string> &command) {
    if (command.size() == MODE_COMMAND_SIZE) {
        if (command.at(MODE_INDEX).at(MODE_FIRST_LETTER) == 'A' || command.at(MODE_INDEX).at(MODE_FIRST_LETTER) == 'B') {
            service_.set_mode(command.at(MODE_INDEX).at(MODE_FIRST_LETTER));
        } else {
            std::cout << "Invalid mode.\n";
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}

void UI::add_ui(const std::vector<std::string> &command) {
    if (command.size() == ADD_COMMAND_SIZE) {
        try {
            service_.add_guardian_statue(command.at(POWER_WORD_NAME_INDEX), command.at(MATERIAL_INDEX), std::stoi(command.at(AGE_INDEX)),
                                         command.at(CORPOREAL_FORM_INDEX));
        } catch (Exception &exception) {
            std::cout << exception.what() << std::endl;
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}

void UI::update_ui(const std::vector<std::string> &command) {
    if (command.size() == UPDATE_COMMAND_SIZE) {
        try {
            service_.update_guardian_statue(command.at(POWER_WORD_NAME_INDEX), command.at(MATERIAL_INDEX),
                                            std::stoi(command.at(AGE_INDEX)), command.at(CORPOREAL_FORM_INDEX));
        } catch (Exception &exception) {
            std::cout << exception.what() << std::endl;
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}

void UI::delete_ui(const std::vector<std::string> &command) {
    if (command.size() == DELETE_COMMAND_SIZE) {
        try {
            service_.delete_guardian_statue(command.at(POWER_WORD_NAME_INDEX));
        } catch (Exception &exception) {
            std::cout << exception.what() << std::endl;
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}

void UI::list_ui(const std::vector<std::string> &command) {
    if (command.size() == LIST_COMMAND_SIZE) {
        try {
            auto statues = service_.get_all_statues();
            for (const auto &statue : statues) {
                std::cout << statue << std::endl;
            }
        } catch (...) {
            std::cout << "An error has occurred.\n";
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}

void UI::set_file_ui(const std::vector<std::string> &command) {
    if (command.size() == SET_FILE_COMMAND_SIZE) {
        service_.set_file(command.at(FILE_INDEX));
    } else {
        std::cout << "Invalid arguments.\n";
    }
}
void UI::save_ui(const std::vector<std::string> &command) {
    if (command.size() == SAVE_COMMAND_SIZE) {
        try {
            service_.save();
        } catch (Exception& exception) {
            std::cout << exception.what() << std::endl;
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}
void UI::next_ui(const std::vector<std::string> &command) {
    if (command.size() == NEXT_COMMAND_SIZE) {
        try {
            std::cout << service_.next();
        } catch (Exception& exception) {
            std::cout << exception.what() << std::endl;
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}
void UI::my_list_ui(const std::vector<std::string> &command) {
    if (command.size() == MY_LIST_COMMAND_SIZE) {
        try {
            auto statues = service_.get_my_list();
            for (const auto& statue : statues) {
                std::cout << statue << std::endl;
            }
        } catch (Exception& exception) {
            std::cout << exception.what() << std::endl;
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}
