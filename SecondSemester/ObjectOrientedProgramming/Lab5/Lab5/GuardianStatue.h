#pragma once

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
  GuardianStatue(std::string, std::string, int, std::string);
  GuardianStatue(GuardianStatue const&);
  ~GuardianStatue() = default;
  std::string get_power_word_name() const;
  void set_power_word_name(std::string);
  std::string get_material() const;
  void set_material(std::string);
  int get_age() const;
  void set_age(int);
  std::string get_corporeal_form() const;
  void set_corporeal_form(std::string);
  std::string to_string() const;
  bool operator==(GuardianStatue const&) const;
  bool operator!=(GuardianStatue const &) const;
  GuardianStatue& operator=(GuardianStatue const&);
};

