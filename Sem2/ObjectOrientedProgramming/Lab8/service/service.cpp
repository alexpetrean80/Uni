#include "service.h"

void Service::set_mode(char mode) {
    mode_ = mode;
}


void Service::set_repo_file(const std::string &file) {
    repo_.set_repo_file(file);
}


void Service::set_my_list_file(const std::string &file) {
    repo_.set_my_list_file(file);
}

void Service::add_guardian_statue(const std::string &power_word_name, const std::string &material, int age,
                                  const std::string &corporeal_form) {
    if (mode_ == 'A') {
        GuardianStatue temporary_statue{power_word_name, material, age, corporeal_form};
        GuardianStatueValidator::validate(temporary_statue);
        repo_.add_guardian_statue(temporary_statue);
    } else {
        throw ServiceException("Invalid mode.");
    }
}

void Service::delete_guardian_statue(const std::string &power_word_name) {
    if (mode_ == 'A') {
        repo_.delete_guardian_statue(power_word_name);
    } else {
        throw ServiceException("Invalid mode.");
    }
}

void Service::update_guardian_statue(const std::string &power_word_name, const std::string &material, int age,
                                     const std::string &corporeal_form) {
    if (mode_ == 'A') {
        GuardianStatue temporary_statue{power_word_name, material, age, corporeal_form};
        GuardianStatueValidator::validate(temporary_statue);
        repo_.update_guardian_statue(temporary_statue);
    } else {
        throw ServiceException("Invalid mode.");
    }
}

std::vector<GuardianStatue> Service::get_guardian_statues() {
    return repo_.get_guardian_statues();
}

GuardianStatue Service::next() {
    if (mode_ == 'B') {
        return repo_.next();
    }
    throw ServiceException("Invalid mode.");
}

std::vector<GuardianStatue> Service::get_my_list() {
    int position = repo_.get_my_list_file().find_last_of('.');
    if (position == std::string::npos) {
        throw ServiceException("Invalid file format.");
    }
    std::string file_extension =
            repo_.get_my_list_file().substr(position, repo_.get_my_list_file().size() - position + 1);
    if (file_extension == ".html" || file_extension == ".csv") {
        system(repo_.get_my_list_file().c_str());
    } else {
        throw ServiceException("Invalid file format.");
    }
    return repo_.get_my_list();
}
void Service::save(const std::string &power_word_name) {
    if (mode_ == 'B') {
        repo_.save(power_word_name);
    } else {
        throw ServiceException("Invalid mode.");
    }
}
char Service::get_mode() const {
    return mode_;
}
std::string Service::get_repo_file() {
    return repo_.get_repo_file();
}
std::string Service::get_my_list_file() {

    return repo_.get_my_list_file();
}
