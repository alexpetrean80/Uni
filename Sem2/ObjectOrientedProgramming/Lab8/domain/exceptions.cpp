#include "exceptions.h"
ValidatorException::ValidatorException(const std::string &message) {
    message_ = message;
}

const char *ValidatorException::what() const noexcept {
    return message_.c_str();
}

RepositoryException::RepositoryException(const std::string &message) {
    message_ = message;
}

const char *RepositoryException::what() const noexcept {
    return message_.c_str();
}

ServiceException::ServiceException(const std::string &message) {
    message_ = message;
}

const char *ServiceException::what() const noexcept {
    return message_.c_str();
}
