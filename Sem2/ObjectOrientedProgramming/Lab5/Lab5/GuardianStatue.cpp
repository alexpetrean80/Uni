#include "GuardianStatue.h"

/**
 * \brief creates a new GuardianStatue object with empty values
 */
GuardianStatue::GuardianStatue() {

  this->power_word_name_ = "";
  this->material_ = "";
  this->age_ = 0;
  this->corporeal_form_ = "";
}

/**
 * \brief creates a GuardianStatue object from the given parameters
 * \param powerWordName the power word name of the GuardianStatue
 * \param material the material of the GuardianStatue
 * \param age the age of the GuardianStatue
 * \param corporealForm the corporeal form of the GuardianStatue
 */
GuardianStatue::GuardianStatue(std::string powerWordName, std::string material, int age, std::string corporealForm) {
  this->power_word_name_ = powerWordName;
  this->material_ = material;
  this->age_ = age;
  this->corporeal_form_ = corporealForm;
}


/**
 * \brief creates a new GuardianStatue object as a copy from the given one
 * \param guardian the given GuardianStatue object passed as a constant reference
 */
GuardianStatue::GuardianStatue(GuardianStatue const& guardian) {
  *this = guardian;
}

/**
 * \brief returns the power word name of the object 
 * \return the power word name of the object
 */
std::string GuardianStatue::get_power_word_name() const {
  return this->power_word_name_;
}

/**
 * \brief sets a new value for the power word name of the object
 * \param power_word_name the new power word name
 */
void GuardianStatue::set_power_word_name(std::string power_word_name) {
  this->power_word_name_ = std::move(power_word_name);
}

/**
 * \brief returns the material of the object
 * \return the material of the object
 */
std::string GuardianStatue::get_material() const
{
  return this->material_;
}

/**
 * \brief sets a new value for the material of the object
 * \param material the new value for the material of the object
 */
void GuardianStatue::set_material(std::string material) {
  this->material_ = std::move(material);
}

/**
 * \brief return the age of the object
 * \return the age of the object
 */
int GuardianStatue::get_age() const
{
  return this->age_;
}

/**
 * \brief sets a new value for the age of the object
 * \param age the new value for the age of the object
 */
void GuardianStatue::set_age(int age) {
  this->age_ = age;
}

/**
 * \brief returns the corporeal form of the object
 * \return the corporeal form of the object
 */
std::string GuardianStatue::get_corporeal_form() const
{
  return this->corporeal_form_;
}

/**
 * \brief sets a new value for the corporeal form of the object
 * \param corporealForm the new value for the corporeal form of the object
 */
void GuardianStatue::set_corporeal_form(std::string corporealForm) {
  this->corporeal_form_ = std::move(corporealForm);
}

/**
 * \brief creates a string object containing all the attributes of the object
 * \return the resulted string
 */
std::string GuardianStatue::to_string() const
{
  std::stringstream result;
  result << power_word_name_ << " ";
  result << material_ << " ";
  result << age_ << " ";
  result << corporeal_form_;
  return result.str();
}

/**
 * \brief compares the object to a given object of the same type
 * \param statue the second object passed as a const reference
 * \return true if equal, else false
 */
bool GuardianStatue::operator==(GuardianStatue const& statue) const
{
  return power_word_name_ == statue.power_word_name_ && material_ == statue.material_ && age_ == statue.age_ && corporeal_form_ == statue.corporeal_form_;
}

/**
 * \brief compares the object to a given object of the same type
 * \param statue the second object passed as a const reference
 * \return true if not equal, else false
 */
bool GuardianStatue::operator!=(GuardianStatue const& statue) const
{
    return power_word_name_ != statue.power_word_name_ || material_ != statue.material_ || age_ != statue.age_ || corporeal_form_ != statue.corporeal_form_;
}

/**
 * \brief assigns a new value to the entire object
 * \param statue the new value of the object
 * \return reference to this
 */
GuardianStatue& GuardianStatue::operator=(GuardianStatue const& statue)
{
  if (this == &statue) {
    return *this;
  }
  power_word_name_ = statue.power_word_name_;
  material_ = statue.material_;
  age_ = statue.age_;
  corporeal_form_ = statue.corporeal_form_;
  return *this;
}
