#include "repository.h"

Repository::Repository() {
    index_ = 0;
}

std::vector<std::string> Repository::tokenize(const std::string &statue_as_string, const std::string &separator) {
    std::vector<std::string> split_statue;
    unsigned long long start_index = 0;
    unsigned long long end_index = 0;
    while ((end_index = statue_as_string.find(separator, start_index)) < statue_as_string.size()) {
        std::string value = statue_as_string.substr(start_index, end_index - start_index);
        if (!value.empty()) {
            split_statue.push_back(value);
        }
        start_index = end_index + separator.size();
    }
    if (start_index < statue_as_string.size()) {
        std::string value = statue_as_string.substr(start_index);
        if (!value.empty()) {
            split_statue.push_back(value);
        }
    }
    return split_statue;
}

std::string Repository::get_repo_file() {
    return repo_file_;
}

void Repository::set_repo_file(const std::string &file) {
    repo_file_ = file;
}

std::string Repository::get_my_list_file() {
    return my_list_file_;
}

void Repository::set_my_list_file(const std::string &file) {
    my_list_file_ = file;
}

std::vector<GuardianStatue> Repository::read_from_file() {

    output_.open(repo_file_, std::ios::app);
    output_.close();
    input_.open(repo_file_);
    std::vector<GuardianStatue> guardian_statues;
    GuardianStatue statue{};
    while (input_ >> statue) {
        guardian_statues.emplace_back(statue);
    }
    input_.close();
    return guardian_statues;
}

void Repository::write_to_file(const std::vector<GuardianStatue> &statues) {
    output_.open(repo_file_, std::ios::trunc);
    for (const auto &statue : statues) {
        output_ << statue << std::endl;
    }
    output_.close();
}

std::vector<GuardianStatue> Repository::read_from_csv() {
    output_.open(my_list_file_, std::ios::app);
    output_.close();
    input_.open(my_list_file_);
    std::string statue_as_csv;
    std::vector<GuardianStatue> statues;
    std::vector<std::string> statues_as_string;
    while (std::getline(input_, statue_as_csv)) {
        statues_as_string = tokenize(statue_as_csv, ",");
        statues.emplace_back(statues_as_string[0], statues_as_string[1], std::stoi(statues_as_string[2]),
                             statues_as_string[3]);
    }
    input_.close();
    return statues;
}

void Repository::write_to_csv(const std::vector<GuardianStatue> &statues) {
    output_.open(my_list_file_, std::ios::trunc);
    for (auto statue : statues) {
        output_ << statue.to_csv() << std::endl;
    }
    output_.close();
}

std::vector<GuardianStatue> Repository::read_from_html() {
    output_.open(my_list_file_, std::ios::app);
    output_.close();
    input_.open(my_list_file_);
    std::vector<GuardianStatue> statues;
    std::vector<std::string> split_statue;
    std::string statue{};
    while (getline(input_, statue)) {
        if (statue.find("<td>") != std::string::npos) {
            statue.erase(std::remove(statue.begin(), statue.end(), '/'), statue.end());
            split_statue = tokenize(statue, "<td>");
            statues.emplace_back(split_statue.at(0), split_statue.at(1), std::stoi(split_statue.at(2)),
                                 split_statue.at(3));
        }
    }
    input_.close();
    return statues;
}

void Repository::write_to_html(const std::vector<GuardianStatue> &statues) {
    output_.open(my_list_file_, std::ios::trunc);
    output_ << "<!DOCTYPE html>"
               "<html>"
               "<head>"
               "<title>Mylist</title>"
               "</head>"
               "<body>"
               "<table border=\"1\">";
    for (auto statue : statues) {
        output_ << statue.to_html() << std::endl;
    }
    output_ << "</table>\n"
               "</body>\n"
               "\n"
               "</html>";
    output_.close();
}
void Repository::add_guardian_statue(const GuardianStatue &statue) {
    std::vector<GuardianStatue> statues = read_from_file();
    for (const auto &guardian_statue : statues) {
        if (guardian_statue == statue) {
            throw RepositoryException("Guardian statue already exists.");
        }
    }
    statues.emplace_back(statue);
    write_to_file(statues);
}

void Repository::delete_guardian_statue(const std::string &power_word_name) {
    std::vector<GuardianStatue> statues = read_from_file();
    auto position = std::find_if(statues.begin(), statues.end(), [power_word_name](const GuardianStatue &statue) {
        return statue.get_power_word_name() == power_word_name;
    });
    if (position == statues.end()) {
        throw RepositoryException("Guardian statue doesn't exist.");
    }
    statues.erase(position);
    write_to_file(statues);
}

void Repository::update_guardian_statue(const GuardianStatue &new_statue) {
    std::vector<GuardianStatue> statues = read_from_file();
    auto exists = false;
    for (auto &statue : statues) {
        if (statue.get_power_word_name() == new_statue.get_power_word_name()) {
            exists = true;
            statue.set_material(new_statue.get_material());
            statue.set_age(new_statue.get_age());
            statue.set_corporeal_form(new_statue.get_corporeal_form());
        }
    }
    if (!exists) {
        throw RepositoryException("Guardian statue does not exist.");
    }
    write_to_file(statues);
}

std::vector<GuardianStatue> Repository::get_guardian_statues() {
    return read_from_file();
}

GuardianStatue Repository::next() {
    std::vector<GuardianStatue> statues = read_from_file();
    if (index_ == statues.size() - 1) {
        index_ = 0;
    }
    return statues.at(index_++);
}

void Repository::save(const std::string &power_word_name) {
    std::vector<GuardianStatue> statues_from_my_list;
    std::vector<GuardianStatue> statues_from_repo = read_from_file();
    int position = my_list_file_.find_last_of('.');
    if (position == std::string::npos) {
        throw RepositoryException("Invalid file format.");
    }
    std::string file_extension = my_list_file_.substr(position, my_list_file_.size() - position + 1);
    if (file_extension == ".csv") {
        statues_from_my_list = read_from_csv();
    } else if (file_extension == ".html") {
        statues_from_my_list = read_from_html();
    } else {
        throw RepositoryException("Invalid file format for my list.");
    }
    bool found = false;
    for (const auto &statue : statues_from_repo) {
        if (statue.get_power_word_name() == power_word_name) {
            if (std::find(statues_from_my_list.begin(), statues_from_my_list.end(), statue) ==
                statues_from_my_list.end()) {
                statues_from_my_list.push_back(statue);
                found = true;
            } else {
                throw RepositoryException("Statue already in my list.");
            }
        }
    }
    if (!found) {
        throw RepositoryException("Statue does not exist.");
    }
    if (file_extension == ".csv") {
        write_to_csv(statues_from_my_list);
    } else if (file_extension == ".html") {
        write_to_html(statues_from_my_list);
    }
}
std::vector<GuardianStatue> Repository::get_my_list() {
    std::vector<GuardianStatue> statues_from_my_list;
    std::vector<GuardianStatue> statues_from_repo;
    int position = my_list_file_.find_last_of('.');
    if (position == std::string::npos) {
        throw RepositoryException("Invalid file format.");
    }
    std::string file_extension = my_list_file_.substr(position, my_list_file_.size() - position + 1);
    if (file_extension == ".csv") {
        return read_from_csv();
    } else if (file_extension == ".html") {
        return read_from_html();
    } else {
        throw RepositoryException("Invalid file format.");
    }
}
