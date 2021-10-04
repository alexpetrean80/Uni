#include "repository.h"

Repo::Repo() {
    position_ = 0;
}

/**
 * \brief adds a new GuardianStatue to the repository
 * \param guardian_statue new GuardianStatue passed as a const reference
 */
void Repo::add(GuardianStatue const &guardian_statue) {
    guardian_statues_.push_back(guardian_statue);
}

/**
 * \brief removes a GuardianStatue from the repo by its power word name
 * \param power_word_name power word name of the GuardianStatue
 */
void Repo::remove(const std::string &power_word_name) {
    for (int i = 0; i < guardian_statues_.get_size(); i++) {
        if (guardian_statues_.at(i).get_power_word_name() == power_word_name) {
            guardian_statues_.pop_back(i);
        }
    }
}

/**
 * \brief updates a GuardianStatue having the same power word name as the given one
 * \param guardian_statue the new GuardianStatue which will replace the old one
 */
void Repo::update(GuardianStatue &guardian_statue) {
    for (auto i = 0; i < guardian_statues_.get_size(); i++) {
        if (guardian_statues_.at(i).get_power_word_name() == guardian_statue.get_power_word_name()) {
            guardian_statues_.at(i) = guardian_statue;
            return;
        }
    }
}

/**
 * \brief returns a const reference to the DynamicVector containing the elements in the repo
 * \return container with the elements of the repo inside
 */
DynamicVector<GuardianStatue> const &Repo::get_statues() const {
    return guardian_statues_;
}
GuardianStatue const &Repo::next() {
    if (guardian_statues_.get_size() == 0) {
        throw std::exception();
    }
    if (position_ == guardian_statues_.get_size()) {
        position_ = 0;
    }
    return guardian_statues_.at(position_++);
}
void Repo::save(const std::string &power_word_name) {
    for (auto i = 0; i < guardian_statues_.get_size(); i++) {
        if (power_word_name == get_statues().at(i).get_power_word_name()) {
            currently_in_animation_.push_back(guardian_statues_.at(i));
            return;
        }
    }
    throw std::exception();
}
const DynamicVector<GuardianStatue> &Repo::get_currently_in_animation() const {
    return currently_in_animation_;
}
