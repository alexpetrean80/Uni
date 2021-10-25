#ifndef LAB6_SERVICE_H
#define LAB6_SERVICE_H

#include "repository.h"
class Service
{
private:
    Repo repo_;
    std::string mode_;
public:
    Service();
    void set_mode(const std::string &new_mode);
    void add_statue(const std::string &power_word_name, const std::string &material, int age, const std::string &corporeal_form);
    void delete_statue(const std::string &power_word_name);
    void update_statue(const std::string &power_word_name, const std::string &material, int age, const std::string &corporeal_form);
    DynamicVector<GuardianStatue> const &get_statues() const;
    GuardianStatue const &next();
    void save(const std::string &power_word_name);
    DynamicVector<GuardianStatue> const &get_currently_in_animation() const;
};
#endif//LAB6_SERVICE_H
