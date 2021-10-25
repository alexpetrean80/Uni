#include "Repo.h"

/**
 * \brief adds a new GuardianStatue to the repository
 * \param statue new GuardianStatue passed as a const reference
 */
void Repo::add(GuardianStatue const& statue)
{
  elements_.push_back(statue);
}

/**
 * \brief removes a GuardianStatue from the repo by its power word name
 * \param power_word_name power word name of the GuardianStatue
 */
void Repo::remove(const std::string& power_word_name) {
  for (int i = 0; i < elements_.get_size(); i++) {
    if (elements_.at(i).get_power_word_name() == power_word_name) {
      elements_.pop_back(i);
    }
  }
}

/**
 * \brief updates a GuardianStatue having the same power word name as the given one
 * \param statue the new GuardianStatue which will replace the old one
 */
void Repo::update(GuardianStatue &statue) {
  for (auto i = 0; i < elements_.get_size(); i++) {
    if (elements_.at(i).get_power_word_name() == statue.get_power_word_name()) {
      elements_.at(i) = statue;
      return;
    }
  }
}

/**
 * \brief returns a const reference to the DynamicVector containing the elements in the repo
 * \return container with the elements of the repo inside
 */
DynamicVector<GuardianStatue> const& Repo::get_statues() const
{
  return elements_;
}
