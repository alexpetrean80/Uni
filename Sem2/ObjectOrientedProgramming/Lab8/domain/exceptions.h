#ifndef LAB8_EXCEPTIONS_H
#define LAB8_EXCEPTIONS_H

#include <string>

class ValidatorException : public std::exception {
private:
    std::string message_;

public:
    explicit ValidatorException(const std::string &message);
    const char *what() const noexcept override;
};

class RepositoryException : public std::exception {
private:
    std::string message_;

public:
    explicit RepositoryException(const std::string &message);
    const char *what() const noexcept override;
};

class ServiceException : public std::exception {
private:
    std::string message_;

public:
    explicit ServiceException(const std::string &message);
    const char *what() const noexcept override;
};
#endif//LAB8_EXCEPTIONS_H
