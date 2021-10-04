#define COMMAND_INDEX 0
#define POWER_WORD_NAME_INDEX 1
#define MODE_INDEX 1
#define MATERIAL_INDEX 2
#define MATERIAL_APPRENTICE_INDEX 1
#define AGE_APPRENTICE_INDEX 2
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
#define MY_LIST_SIZE 1
#define LIST_MATERIAL_AND_AGE_SIZE 3
#define SAVE_COMMAND_SIZE 2
#define NEXT_COMMAND_SIZE 1

#include "ui.h"

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
            set_repo_file(command);
        } else if (command.at(COMMAND_INDEX) == "mylistLocation") {
            set_my_list_file(command);
        } else if (command.at(COMMAND_INDEX) == "mylist") {
            my_list_ui(command);
        } else if (command.at(COMMAND_INDEX) == "save") {
            save_ui(command);
        } else if (command.at(COMMAND_INDEX) == "next") {
            next_ui(command);
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
        if (command.at(MODE_INDEX).at(MODE_FIRST_LETTER) == 'A' ||
            command.at(MODE_INDEX).at(MODE_FIRST_LETTER) == 'B') {
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
            service_.add_guardian_statue(command.at(POWER_WORD_NAME_INDEX), command.at(MATERIAL_INDEX),
                                         std::stoi(command.at(AGE_INDEX)), command.at(CORPOREAL_FORM_INDEX));
        } catch (ValidatorException &validator_exception) {
            std::cout << validator_exception.what() << std::endl;
        } catch (RepositoryException &repository_exception) {
            std::cout << repository_exception.what() << std::endl;
        } catch (ServiceException &service_exception) {
            std::cout << service_exception.what() << std::endl;
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
        } catch (ValidatorException &validator_exception) {
            std::cout << validator_exception.what() << std::endl;
        } catch (RepositoryException &repository_exception) {
            std::cout << repository_exception.what() << std::endl;
        } catch (ServiceException &service_exception) {
            std::cout << service_exception.what() << std::endl;
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}

void UI::delete_ui(const std::vector<std::string> &command) {
    if (command.size() == DELETE_COMMAND_SIZE) {
        try {
            service_.delete_guardian_statue(command.at(POWER_WORD_NAME_INDEX));
        } catch (RepositoryException &repository_exception) {
            std::cout << repository_exception.what() << std::endl;
        } catch (ServiceException &service_exception) {
            std::cout << service_exception.what() << std::endl;
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}

void UI::list_ui(const std::vector<std::string> &command) {
    if (command.size() == LIST_COMMAND_SIZE) {
        auto statues = service_.get_guardian_statues();
        for (const auto &statue : statues) {
            std::cout << statue << '\n';
        }
    } else if (command.size() == LIST_MATERIAL_AND_AGE_SIZE) {
        try {
            auto statues = service_.get_guardian_statues();
            int count = 0;
            for (const auto &statue : statues) {
                if (statue.get_material() == command.at(MATERIAL_APPRENTICE_INDEX) &&
                    statue.get_age() <= std::stoi(command.at(AGE_APPRENTICE_INDEX))) {
                    std::cout << statue << '\n';
                    count++;
                }
            }
            if (count == 0) {
                for (const auto &statue : statues) {
                    std::cout << statue << '\n';
                }
            }
        } catch (ServiceException &service_exception) {
            std::cout << service_exception.what() << std::endl;
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}

void UI::set_repo_file(const std::vector<std::string> &command) {
    if (command.size() == SET_FILE_COMMAND_SIZE) {
        service_.set_repo_file(command.at(FILE_INDEX));
    } else {
        std::cout << "Invalid arguments.\n";
    }
}

void UI::set_my_list_file(const std::vector<std::string> &command) {
    if (command.size() == SET_FILE_COMMAND_SIZE) {
        service_.set_my_list_file(command.at(FILE_INDEX));
    } else {
        std::cout << "Invalid arguments.\n";
    }
}

void UI::my_list_ui(const std::vector<std::string> &command) {
    if (command.size() == MY_LIST_SIZE) {
        try {
            auto statues = service_.get_my_list();
            for (const auto &statue : statues) {
                std::cout << statue << std::endl;
            }
        } catch (ServiceException &service_exception) {
            std::cout << service_exception.what() << std::endl;
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}

void UI::save_ui(const std::vector<std::string> &command) {
    if (command.size() == SAVE_COMMAND_SIZE) {
        try {
            service_.save(command.at(POWER_WORD_NAME_INDEX));
        } catch (ServiceException &service_exception) {
            std::cout << service_exception.what() << std::endl;
        }
        catch (RepositoryException& repository_exception) {
            std::cout << repository_exception.what() << std::endl;
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}

void UI::next_ui(const std::vector<std::string> &command) {
    if (command.size() == NEXT_COMMAND_SIZE) {
        try {
            std::cout << service_.next() << std::endl;
        } catch (ServiceException &service_exception) {
            std::cout << service_exception.what() << std::endl;
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}