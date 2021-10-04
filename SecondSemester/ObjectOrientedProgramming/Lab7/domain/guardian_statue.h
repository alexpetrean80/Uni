#ifndef LAB7_GUARDIAN_STATUE_H
#define LAB7_GUARDIAN_STATUE_H

#include <string>

class GuardianStatue {
private:
    std::string power_word_name_;
    std::string material_;
    int age_{};
    std::string corporeal_form_;
public:
    GuardianStatue() = default;
    GuardianStatue(const std::string &power_word_name, const std::string &material, int age, const std::string &corporeal_form);
    GuardianStatue(GuardianStatue const &guardian_statue);
    virtual ~GuardianStatue() = default;
    const std::string &get_power_word_name() const;
    void set_power_word_name(const std::string &power_word_name);
    const std::string &get_material() const;
    void set_material(const std::string &material);
    int get_age() const;
    void set_age(int age);
    const std::string &get_corporeal_form() const;
    void set_corporeal_form(const std::string &corporeal_form);
    bool operator==(const GuardianStatue &other) const;
    bool operator!=(const GuardianStatue &other) const;
    friend std::istream& operator>>(std::istream& input, GuardianStatue& guardian_statue);
    friend std::ostream& operator<<(std::ostream& output, const GuardianStatue& guardian_statue);
    std::string to_string();
};


#endif//LAB7_GUARDIAN_STATUE_H
