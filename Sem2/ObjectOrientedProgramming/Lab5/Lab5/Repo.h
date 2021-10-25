#pragma once
#include "DynamicVector.h"
#include "GuardianStatue.h"

class Repo
{
private:
  DynamicVector<GuardianStatue> elements_;

public:
  Repo() = default;
  void add(GuardianStatue const&);
  void remove(const std::string&);
  void update(GuardianStatue &);
  DynamicVector<GuardianStatue> const& get_statues() const;
};
