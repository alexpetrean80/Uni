#ifndef LAB7_FILE_REPOSITORY_H
#define LAB7_FILE_REPOSITORY_H

#include "../domain/Exception.h"
#include "../domain/guardian_statue.h"
#include <fstream>
#include <string>
#include <vector>

class FileRepository{
private:
    int index_;
    std::vector<GuardianStatue> my_list_;
    std::string file_name_;
    std::ifstream input;
    std::ofstream output;
    std::vector<GuardianStatue> read();
    void write(const std::vector<GuardianStatue>& guardian_statues);
public:
    explicit FileRepository(const std::string& file_name);
    FileRepository();
    ~FileRepository() = default;
    void add_guardian_statue(GuardianStatue const &guardian_statue);
    void delete_guardian_statue(const std::string& power_word_name);
    void update_guardian_statue(GuardianStatue const &new_guardian_statue);
    std::vector<GuardianStatue> get_all_guardian_statues();
    void set_file_name(const std::string& file_name);
    std::string get_file_name();
    GuardianStatue next();
    void save();
    std::vector<GuardianStatue> get_my_list();
};


#endif//LAB7_FILE_REPOSITORY_H
