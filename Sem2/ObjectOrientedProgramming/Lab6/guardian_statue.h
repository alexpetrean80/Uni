#ifndef LAB6_GUARDIAN_STATUE_H
#define LAB6_GUARDIAN_STATUE_H

#include <string>
#include <sstream>

class GuardianStatue {
private:
    std::string power_word_name_;
    std::string material_;
    int age_{};
    std::string corporeal_form_;

public:
    GuardianStatue();
    GuardianStatue(std::string power_word_name, std::string material, int age, std::string corporeal_form);
    GuardianStatue(GuardianStatue const &guardian_statue);
    ~GuardianStatue() = default;
    std::string get_power_word_name() const;
    void set_power_word_name(std::string power_word_name);
    std::string get_material() const;
    void set_material(std::string material);
    int get_age() const;
    void set_age(int age);
    std::string get_corporeal_form() const;
    void set_corporeal_form(std::string material);
    std::string to_string() const;
    bool operator==(GuardianStatue const &other_statue) const;
    bool operator!=(GuardianStatue const &other_statue) const;
    GuardianStatue& operator=(GuardianStatue const &other_statue);
};

#endif//LAB6_GUARDIAN_STATUE_H
