#define COMMAND_INDEX 0
#define POWER_WORD_NAME_INDEX 1
#define MODE_INDEX 1
#define MATERIAL_INDEX 2
#define MATERIAL_APPRENTICE_INDEX 1
#define AGE_INDEX 3
#define AGE_APPRENTICE_INDEX 2
#define CORPOREAL_FORM_INDEX 4
#define MODE_COMMAND_SIZE 2
#define ADD_COMMAND_SIZE 5
#define DELETE_COMMAND_SIZE 2
#define UPDATE_COMMAND_SIZE 5
#define LIST_COMMAND_SIZE 1
#define LIST_MATERIAL_AND_AGE_SIZE 3
#define NEXT_COMMAND_SIZE 1
#define SAVE_COMMAND_SIZE 2
#define MY_LIST_SIZE 1
#define EXIT_COMMAND_SIZE 1

#include "ui.h"

/**
 * \brief reads a command from the console and puts it into a DynamicArray of strings
 * \param command DynamicArray in which the command will reside
 */
void UI::read_command(DynamicVector<std::string> &command) {
    while (command.get_size() != 0) {
        command.pop_back(command.get_size() - 1);
    }
    std::string input, token;
    std::getline(std::cin, input);
    std::stringstream input_stream(input);
    std::getline(input_stream, token, ' ');
    command.push_back(token);
    while (std::getline(input_stream, token, ',')) {
        token.erase(std::remove(token.begin(), token.end(), ' '), token.end());
        command.push_back(token);
    }
}

/**
 * \brief runs the app in the console
 */
void UI::run_console() {
    DynamicVector<std::string> command;
    while (true) {
        read_command(command);
        if (command.at(COMMAND_INDEX) == "mode") {
            mode_ui(command);
        } else if (command.at(COMMAND_INDEX) == "add") {
            add_ui(command);
        } else if (command.at(COMMAND_INDEX) == "update") {
            update_ui(command);
        } else if (command.at(COMMAND_INDEX) == "delete") {
            delete_ui(command);
        } else if (command.at(COMMAND_INDEX) == "list") {
            list_ui(command);
        } else if (command.at(COMMAND_INDEX) == "next") {
            next_ui(command);
        } else if (command.at(COMMAND_INDEX) == "save") {
            save_ui(command);
        } else if (command.at(COMMAND_INDEX) == "mylist") {
            mylist_ui(command);
        } else if(command.at(COMMAND_INDEX) == "exit") {
            if (command.get_size() == EXIT_COMMAND_SIZE) {
                return;
            } else {
                std::cout << "Invalid arguments.\n";
            }
        } else {
            std::cout << "An exception has occured.\n";
        }
    }
}
void UI::mylist_ui(const DynamicVector<std::string> &command) const {
    if (command.get_size() == MY_LIST_SIZE) {
        try {
            auto statues = service_.get_currently_in_animation();
            for (auto i = 0; i < statues.get_size(); i++) {
                std::cout << statues.at(i).to_string() << '\n';
            }
        } catch (...) {
            std::cout << "An exception has occurred.\n";
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}
void UI::save_ui(const DynamicVector<std::string> &command) {
    if (command.get_size() == SAVE_COMMAND_SIZE) {
        try {
            service_.save(command.at(POWER_WORD_NAME_INDEX));
        } catch (...) {
            std::cout << "An exception has occurred.\n";
        }
    } else {
        std::cout << "An exception has occurred.\n";
    }
}
void UI::next_ui(const DynamicVector<std::string> &command) {
    if (command.get_size() == NEXT_COMMAND_SIZE) {
        try {
            std::cout << service_.next().to_string();
        } catch (...) {
            std::cout << "An exception has occurred.\n";
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}
void UI::list_ui(const DynamicVector<std::string> &command) {
    if (command.get_size() == LIST_COMMAND_SIZE) {
        try {
            auto statues = service_.get_statues();
            for (auto i = 0; i < statues.get_size(); i++) {
                std::cout << statues.at(i).to_string() << '\n';
            }
        } catch (...) {
            std::cout << "An error has occurred.\n";
        }
    } else if (command.get_size() == LIST_MATERIAL_AND_AGE_SIZE) {
        try {
            auto statues = service_.get_statues();
            int count = 0;
            for (auto i = 0; i < statues.get_size(); i++) {
                if (statues.at(i).get_material() == command.at(MATERIAL_APPRENTICE_INDEX) && statues.at(i).get_age() <= std::stoi(command.at(AGE_APPRENTICE_INDEX))) {
                    std::cout << statues.at(i).to_string() << '\n';
                    count++;
                }
            }
            if (count == 0) {
                for (auto i = 0; i < statues.get_size(); i++) {
                    std::cout << statues.at(i).to_string() << '\n';
                }
            }
        } catch (...) {
            std::cout << "An error has occurred.\n";
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}
void UI::delete_ui(const DynamicVector<std::string> &command) {
    if (command.get_size() == DELETE_COMMAND_SIZE) {
        try {
            service_.delete_statue(command.at(POWER_WORD_NAME_INDEX));
        } catch (...) {
            std::cout << "An error has occurred.\n";
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}
void UI::update_ui(const DynamicVector<std::string> &command) {
    if (command.get_size() == UPDATE_COMMAND_SIZE) {
        try {
            service_.update_statue(command.at(POWER_WORD_NAME_INDEX), command.at(MATERIAL_INDEX),
                                   std::stoi(command.at(AGE_INDEX)), command.at(CORPOREAL_FORM_INDEX));
        } catch (...) {
            std::cout << "An error has occurred.\n";
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}
void UI::add_ui(const DynamicVector<std::string> &command) {
    if (command.get_size() == ADD_COMMAND_SIZE) {
        try {
            service_.add_statue(command.at(POWER_WORD_NAME_INDEX), command.at(MATERIAL_INDEX), std::stoi(command.at(AGE_INDEX)),
                                command.at(CORPOREAL_FORM_INDEX));
        } catch (...) {
            std::cout << "An error has occurred.\n";
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}
void UI::mode_ui(const DynamicVector<std::string> &command) {
    if (command.get_size() == MODE_COMMAND_SIZE) {
        if (command.at(MODE_INDEX) == "A" || command.at(MODE_INDEX) == "B") {
            service_.set_mode(command.at(MODE_INDEX));
        } else {
            std::cout << "Invalid mode.\n";
        }
    } else {
        std::cout << "Invalid arguments.\n";
    }
}
