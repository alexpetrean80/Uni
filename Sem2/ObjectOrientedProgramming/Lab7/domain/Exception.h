#ifndef LAB7_EXCEPTION_H
#define LAB7_EXCEPTION_H

#include <exception>
#include <string>
#include <utility>

class Exception : public std::exception{
private:
    std::string message_;
public:
    explicit Exception(std::string message);
    std::string what();
};

#endif//LAB7_EXCEPTION_H
