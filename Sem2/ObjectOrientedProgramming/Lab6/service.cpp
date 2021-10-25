#include "service.h"

/*
 * \brief creates a new service object with default mode "A"
 */
Service::Service() {
    mode_ = "A";
}

/*
 * \brief sets a new mode of execution
 * \param new_mode new mode of execution
 */
void Service::set_mode(const std::string& new_mode) {
    mode_ = new_mode;
}

/*
 * \brief creates a new statue and adds it to the repo; throws an exception if the mode is inappropriate
 * \param power_word_name power word name of the statue
 * \param material material of the statue
 * \param age age of the statue
 * \param corporeal_form corporeal form of the statue
 */
void Service::add_statue(const std::string &power_word_name, const std::string &material, int age, const std::string &corporeal_form) {
    if (mode_ == "A") {
        auto* statue = new GuardianStatue(power_word_name, material, age, corporeal_form);
        repo_.add(*statue);
        delete statue;
    } else {
        throw std::exception();
    }
}

/*
 * \brief deletes a statue by its power word name; if on inappropriate mode throws error
 * \param power_word_name power word name of the statue to be deleted passed as a const reference
 */
void Service::delete_statue(const std::string &power_word_name) {
    if (mode_ == "A") {
        repo_.remove(power_word_name);
    } else {
        throw std::exception();
    }
}

/**
 * \brief creates a new statue and updates the old one from the repo; throws an exception if inappropriate mode
 * \param power_word_name power word name of the new statue
 * \param material material of the new statue
 * \param age age of the new statue
 * \param corporeal_form corporeal form of the new statue
 */
void Service::update_statue(const std::string& power_word_name, const std::string& material, const int age, const std::string& corporeal_form) {
    if (mode_ == "A") {
        auto *statue = new GuardianStatue(power_word_name, material, age, corporeal_form);
        repo_.update(*statue);
        delete statue;
    } else {
        throw std::exception();
    }
}

/**
 * \brief returns the elements from the repo
 * \return elements from the repo
 */
DynamicVector<GuardianStatue> const &Service::get_statues() const {
    return repo_.get_statues();
}
GuardianStatue const &Service::next() {
    if (mode_ == "B") {
        return repo_.next();
    }
    throw std::exception();
}
void Service::save(const std::string &power_word_name) {
    if (mode_ == "B") {
        repo_.save(power_word_name);
        return;
    }
    throw std::exception();
}
DynamicVector<GuardianStatue> const &Service::get_currently_in_animation() const {
    if (mode_ == "B") {
        return repo_.get_currently_in_animation();
    }
    throw std::exception();
}
