#ifndef LAB7_SERVICE_H
#define LAB7_SERVICE_H

#include "../repository/file_repository.h"
class Service {
private:
    FileRepository repository_;
    char mode_;
public:
    explicit Service(const std::string& file_name = "default.txt", char mode = 'A');
    void add_guardian_statue(const std::string& power_word_name, const std::string& material, int age, const std::string& corporeal_form);
    void delete_guardian_statue(const std::string& power_word_name);
    void update_guardian_statue(const std::string& power_word_name, const std::string& material, int age, const std::string& corporeal_form);
    std::vector<GuardianStatue> get_all_statues();
    char get_mode() const;
    void set_mode(char mode);
    std::string get_file();
    void set_file(const std::string& file);
    GuardianStatue next();
    void save();
    std::vector<GuardianStatue> get_my_list();
};


#endif//LAB7_SERVICE_H
