#pragma once

#include "Manager.h"
#include "Operator.h"

class Wrapper{
public:
  Wrapper();
  ~Wrapper();

  void useOperator();
  void useManager();  

private:
  Operator m_operator;
  Manager m_manager;
};