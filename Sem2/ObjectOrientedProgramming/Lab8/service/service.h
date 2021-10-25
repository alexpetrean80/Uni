#ifndef LAB8_SERVICE_H
#define LAB8_SERVICE_H

#include "../domain/guardian_statue.h"
#include "../domain/guardian_statue_validator.h"
#include "../repository/repository.h"
#include <cstdlib>

class Service {
private:
    Repository repo_;
    char mode_;

public:
    char get_mode() const;
    std::string get_repo_file();
    std::string get_my_list_file();
    void set_mode(char mode);
    void set_repo_file(const std::string &file);
    void set_my_list_file(const std::string &file);
    void add_guardian_statue(const std::string &power_word_name, const std::string &material, int age,
                             const std::string &corporeal_form);
    void delete_guardian_statue(const std::string &power_word_name);
    void update_guardian_statue(const std::string &power_word_name, const std::string &material, int age,
                                const std::string &corporeal_form);
    std::vector<GuardianStatue> get_guardian_statues();
    GuardianStatue next();
    void save(const std::string& power_word_name);
    std::vector<GuardianStatue> get_my_list();
};


#endif//LAB8_SERVICE_H
