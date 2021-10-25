#pragma once
#include <iostream>
#include <sstream>
#include <algorithm>
#include "Service.h"

class UI
{
private:
  Service service_;
public:
  void read_command(DynamicVector<std::string>&) const;
  void run_console();
};
