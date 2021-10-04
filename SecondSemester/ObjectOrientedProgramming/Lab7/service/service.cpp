#include "service.h"
Service::Service(const std::string& file_name, char mode) {
    repository_.set_file_name(file_name);
    mode_ = mode;
}
void Service::add_guardian_statue(const std::string& power_word_name, const std::string& material, int age, const std::string& corporeal_form) {
    if (mode_ == 'A') {
        repository_.add_guardian_statue(GuardianStatue{power_word_name, material, age, corporeal_form});
    } else {
        throw Exception("Invalid mode.");
    }
}

void Service::delete_guardian_statue(const std::string& power_word_name) {
    if (mode_ == 'A') {
        repository_.delete_guardian_statue(power_word_name);
    } else {
        throw Exception("Invalid mode.");
    }
}

void Service::update_guardian_statue(const std::string& power_word_name, const std::string& material, int age, const std::string& corporeal_form) {
    if (mode_ == 'A') {
        repository_.update_guardian_statue(GuardianStatue{power_word_name, material, age, corporeal_form});
    } else {
        throw Exception("Invalid mode.");
    }
}

std::vector<GuardianStatue> Service::get_all_statues() {
    return repository_.get_all_guardian_statues();
}
char Service::get_mode() const {
    return mode_;
}
void Service::set_mode(char mode) {
    mode_ = mode;
}
std::string Service::get_file() {
    return repository_.get_file_name();
}
void Service::set_file(const std::string& file) {
    repository_.set_file_name(file);
}
GuardianStatue Service::next() {
    if (mode_ == 'B') {
        return repository_.next();
    }
    throw Exception("Invalid mode.");
}
void Service::save() {
    if (mode_ == 'B') {
        repository_.save();
        return;
    }
    throw Exception("Invalid mode.");
}

std::vector<GuardianStatue> Service::get_my_list() {
    if (mode_ == 'B') {
        return repository_.get_my_list();
    }
    throw Exception("Invalid mode.");
}
