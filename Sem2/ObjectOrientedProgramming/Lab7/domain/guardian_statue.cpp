#include "guardian_statue.h"
#include <sstream>

GuardianStatue::GuardianStatue(const std::string &power_word_name, const std::string &material, int age, const std::string &corporeal_form) {
    power_word_name_ = power_word_name;
    material_ = material;
    age_ = age;
    corporeal_form_ = corporeal_form;
}

GuardianStatue::GuardianStatue(const GuardianStatue &guardian_statue) {
    power_word_name_ = guardian_statue.power_word_name_;
    material_ = guardian_statue.material_;
    age_ = guardian_statue.age_;
    corporeal_form_ = guardian_statue.corporeal_form_;
}

const std::string &GuardianStatue::get_power_word_name() const {
    return power_word_name_;
}

void GuardianStatue::set_power_word_name(const std::string &power_word_name) {
    power_word_name_ = power_word_name;
}

const std::string &GuardianStatue::get_material() const {
    return material_;
}

void GuardianStatue::set_material(const std::string &material) {
    material_ = material;
}

int GuardianStatue::get_age() const {
    return age_;
}

void GuardianStatue::set_age(int age) {
    age_ = age;
}

const std::string &GuardianStatue::get_corporeal_form() const {
    return corporeal_form_;
}

void GuardianStatue::set_corporeal_form(const std::string &corporeal_form) {
    corporeal_form_ = corporeal_form;
}

bool GuardianStatue::operator==(const GuardianStatue &other) const {
    return power_word_name_ == other.power_word_name_ &&
           material_ == other.material_ &&
           age_ == other.age_ &&
           corporeal_form_ == other.corporeal_form_;
}

bool GuardianStatue::operator!=(const GuardianStatue &other) const {
    return !(other == *this);
}

std::string GuardianStatue::to_string() {
    std::stringstream result;
    result << power_word_name_ << " ";
    result << material_ << " ";
    result << age_ << " ";
    result << corporeal_form_;
    return result.str();
}


std::istream &operator>>(std::istream &input, GuardianStatue &guardian_statue) {
   input >> guardian_statue.power_word_name_ >> guardian_statue.material_ >> guardian_statue.age_ >> guardian_statue.corporeal_form_;
   return input;
}

std::ostream &operator<<(std::ostream &output, const GuardianStatue &guardian_statue) {
    output << guardian_statue.power_word_name_ << ' ' << guardian_statue.material_ << ' ' << std::to_string(guardian_statue.age_) << ' ' << guardian_statue.corporeal_form_;
    return output;
}
