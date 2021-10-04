#include "guardian_statue_validator.h"

void GuardianStatueValidator::validate(const GuardianStatue &statue) {
    std::string errors{};
    if (statue.get_age() < 0) {
        errors += "Age can't be negative.\n";
    }
    if (!errors.empty()) {
        throw ValidatorException(errors);
    }
}
