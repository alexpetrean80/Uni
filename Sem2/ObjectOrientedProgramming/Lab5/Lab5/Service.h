#pragma once
#include "Repo.h"

class Service
{
private:
  Repo repo_;
  std::string mode;

public:
  Service();
  void set_mode(const std::string& new_mode);
  void add_statue(std::string, std::string, int, std::string);
  void delete_statue(const std::string&);
  void update_statue(const std::string&, const std::string&, int, const std::string&);
  DynamicVector<GuardianStatue> const& get_statues() const;
};
