#ifndef LAB6_REPOSITORY_H
#define LAB6_REPOSITORY_H

#include "guardian_statue.h"
#include "dynamic_vector.h"

class Repo {
private:
    DynamicVector<GuardianStatue> guardian_statues_;
    DynamicVector<GuardianStatue> currently_in_animation_;
    int position_;
public:
    Repo();
    void add(GuardianStatue const &guard);
    void remove(const std::string &power_word_name);
    void update(GuardianStatue &guardian_statue);
    DynamicVector<GuardianStatue> const &get_statues() const;
    GuardianStatue const &next();
    void save(const std::string &power_word_name);
    const DynamicVector<GuardianStatue> &get_currently_in_animation() const;
};

#endif//LAB6_REPOSITORY_H
