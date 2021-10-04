//
// Created by Dan on 4/23/2020.
//

#ifndef LAB8_REPOSITORY_H
#define LAB8_REPOSITORY_H

#include "../domain/exceptions.h"
#include "../domain/guardian_statue.h"
#include <algorithm>
#include <fstream>
#include <iostream>
#include <regex>
#include <sstream>
#include <string>
#include <vector>

class Repository {
private:
    std::string repo_file_;
    std::string my_list_file_;
    std::ifstream input_;
    std::ofstream output_;
    int index_;
    static std::vector<std::string> tokenize(const std::string &statue_as_string, const std::string &separator);
    std::vector<GuardianStatue> read_from_file();
    void write_to_file(const std::vector<GuardianStatue> &statues);
    std::vector<GuardianStatue> read_from_csv();
    void write_to_csv(const std::vector<GuardianStatue> &statues);
    std::vector<GuardianStatue> read_from_html();
    void write_to_html(const std::vector<GuardianStatue> &statues);

public:
    Repository();
    std::string get_repo_file();
    void set_repo_file(const std::string &file);
    std::string get_my_list_file();
    void set_my_list_file(const std::string &file);
    void add_guardian_statue(const GuardianStatue &statue);
    void delete_guardian_statue(const std::string &power_word_name);
    void update_guardian_statue(const GuardianStatue &new_statue);
    std::vector<GuardianStatue> get_guardian_statues();
    GuardianStatue next();
    void save(const std::string& power_word_name);
    std::vector<GuardianStatue> get_my_list();
};


#endif//LAB8_REPOSITORY_H
