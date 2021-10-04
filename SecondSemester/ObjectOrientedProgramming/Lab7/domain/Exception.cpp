#include "Exception.h"

Exception::Exception(std::string message) : message_(std::move(message)) {}

std::string Exception::what() {
    return message_;
}
