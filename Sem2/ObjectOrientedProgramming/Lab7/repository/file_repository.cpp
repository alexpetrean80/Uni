#include "file_repository.h"
#include <algorithm>
#include <iostream>
#include <queue>

FileRepository::FileRepository(const std::string& file_name) {
    file_name_ = file_name;
    index_ = 0;
}

void FileRepository::add_guardian_statue(const GuardianStatue &guardian_statue) {
    std::vector<GuardianStatue> statues = read();
    for (const auto& statue : statues) {
        if (statue == guardian_statue) {
            throw Exception("Guardian statue exists.");
        }
    }
    statues.emplace_back(guardian_statue);
    write(statues);
}

void FileRepository::delete_guardian_statue(const std::string& power_word_name) {
    std::vector<GuardianStatue> statues = read();
    auto position = std::find_if(statues.begin(), statues.end(), [power_word_name](const GuardianStatue& statue) {
       return statue.get_power_word_name() == power_word_name;
    });
    if (position == statues.end()) {
        throw Exception("Guardian statue doesn't exist.");
    }
    statues.erase(position);
    write(statues);
}

void FileRepository::update_guardian_statue(const GuardianStatue &new_guardian_statue) {
    std::vector<GuardianStatue> statues = read();
    auto exists = false;
    for (auto& statue : statues) {
        if (statue.get_power_word_name() == new_guardian_statue.get_power_word_name()) {
            statue.set_material(new_guardian_statue.get_material());
            statue.set_age(new_guardian_statue.get_age());
            statue.set_corporeal_form(new_guardian_statue.get_corporeal_form());
            exists = true;
        }
    }
    if (!exists) {
        throw Exception("Guardian statue doesn't exist.");
    }
    write(statues);
}

std::vector<GuardianStatue> FileRepository::get_all_guardian_statues() {
    return read();
}

std::vector<GuardianStatue> FileRepository::read() {
    output.open(file_name_, std::ios::app);
    output.close();
    input.open(file_name_);
    std::vector<GuardianStatue> guardian_statues;
    GuardianStatue statue{};
    while(input >> statue) {
        guardian_statues.emplace_back(statue);
    }
    input.close();
    return guardian_statues;
}

void FileRepository::write(const std::vector<GuardianStatue>& guardian_statues) {
    output.open(file_name_, std::ios::trunc);
    for (const auto& statue : guardian_statues) {
        output << statue << std::endl;
    }
    output.close();
}
void FileRepository::set_file_name(const std::string& file_name) {
    file_name_ = file_name;
}
std::string FileRepository::get_file_name() {
    return file_name_;
}
FileRepository::FileRepository() {
    file_name_ = "";
    index_ = 0;
}
GuardianStatue FileRepository::next() {
    std::vector<GuardianStatue> statues = read();
    if (index_ == statues.size() - 1) {
        index_ = 0;
    }
    return statues.at(index_++);

}
void FileRepository::save() {
    std::vector<GuardianStatue> statues = read();
    if (statues.empty()) {
        throw Exception("There is no statue to be saved.");
    }
    my_list_.emplace_back(statues.at(index_));
}
std::vector<GuardianStatue> FileRepository::get_my_list() {
    return my_list_;
}
