#include "UI.h"



/**
 * \brief reads a command from the console and puts it into a DynamicArray of strings
 * \param command DynamicArray in which the command will reside
 */
void UI::read_command(DynamicVector<std::string>& command) const
{
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
void UI::run_console()
{
  DynamicVector<std::string> command;
  while (true) {
    read_command(command);
    if (command.at(0) == "mode") {
      if (command.get_size() == 2) {
        if (command.at(1) == "A" || command.at(1) == "B") {
          service_.set_mode(command.at(1));
        } else {
          std::cout << "Invalid mode.\n";
        }
        
       } else {
          std::cout << "Invalid arguments.\n";
        }
    } else if (command.at(0) == "add") {
      if (command.get_size() == 5) {
        try {
        service_.add_statue(command.at(1), command.at(2), std::stoi(command.at(3)),
                           command.at(4));
        } catch (...) {
            std::cout << "An error has occured.\n";
        }
      } else {
        std::cout << "Invalid arguments.\n";
      }
    } else if (command.at(0) == "update") {
      if (command.get_size() == 5) {
        try {
          service_.update_statue(command.at(1), command.at(2),
                                std::stoi(command.at(3)), command.at(4));
        } catch (...) {
              std::cout << "An error has occured.\n";
          }
      } else {
        std::cout << "Invalid arguments.\n";
      }
    } else if (command.at(0) == "delete") {
      if (command.get_size() == 2) {
        try {
          service_.delete_statue(command.at(1));
        } catch (...) {
          std::cout << "An error has occured.\n";
        }
      } else {
        std::cout << "Invalid arguments.\n";
      }
    } else if (command.at(0) == "list") {
        if (command.get_size() == 1) {
        try {
            auto statues = service_.get_statues();
          for (auto i = 0; i < statues.get_size(); i++) {
              std::cout << statues.at(i).to_string() << '\n';
          }
        } catch (...) {
          std::cout << "An error has occured.\n";
        }
      } else {
        std::cout << "Invalid arguments.\n";
      }
    } else if (command.at(0) == "exit") {
      return;
    }
  }
}
