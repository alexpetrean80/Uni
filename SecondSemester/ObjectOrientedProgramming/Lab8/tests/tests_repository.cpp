#include "tests_repository.h"

void test__set_repo_file__valid_file__repo_file_is_modified() {
        Repository repo{};
        repo.set_repo_file("aaa");
        assert(repo.get_repo_file() == "aaa");
}

void test__set_my_list_file__valid_file__my_list_file_is_modified() {
        Repository repo{};
        repo.set_my_list_file("aaa");
        assert(repo.get_my_list_file() == "aaa");
}

void test__add_guardian_statue__guardian_statue_does_not_exist__guardian_statue_added() {
        Repository repo{};
        repo.set_repo_file("test.txt");
        GuardianStatue statue{"aa", "bb", 1, "cc"};
        repo.add_guardian_statue(statue);
        assert(repo.get_guardian_statues().at(0) == statue);
        std::remove("test.txt");
}

void test__add_guardian_statue__guardian_statue_exists__exception_is_thrown() {
        Repository repo{};
        repo.set_repo_file("test.txt");
        GuardianStatue statue{"aa", "bb", 1, "cc"};
        repo.add_guardian_statue(statue);
        try {
            repo.add_guardian_statue(statue);
            assert(false);
        } catch (RepositoryException &exception) {
        }
        std::remove("test.txt");
}

void test__delete_guardian_statue__guardian_statue_exists__guardian_statue_removed() {
        Repository repo{};
        repo.set_repo_file("test.txt");
        GuardianStatue statue{"aa", "bb", 1, "cc"};
        repo.add_guardian_statue(statue);
        repo.delete_guardian_statue("aa");
        assert(repo.get_guardian_statues().empty());
        std::remove("test.txt");
}

void test__delete_guardian_statue__guardian_statue_does_not_exist__exception_is_thrown() {
        Repository repo{};
        repo.set_repo_file("test.txt");
        try {
            repo.delete_guardian_statue("aa");
            assert(false);
        } catch (RepositoryException &exception) {
        }
        std::remove("test.txt");
}

void test__update_guardian_statue__guardian_statue_exists__guardian_statue_updated() {
        Repository repo{};
        repo.set_repo_file("test.txt");
        GuardianStatue statue{"aa", "bb", 1, "cc"};
        GuardianStatue new_statue{"aa", "bc", 2, "cd"};
        repo.add_guardian_statue(statue);
        repo.update_guardian_statue(new_statue);
        assert(repo.get_guardian_statues().at(0) == new_statue);
        std::remove("test.txt");
}

void test__update_guardian_statue__guardian_statue_does_not_exist__exception_is_thrown() {
        Repository repo{};
        repo.set_repo_file("test.txt");
        try {
            repo.update_guardian_statue(GuardianStatue{"aa", "bc", 2, "cd"});
            assert(false);
        } catch (RepositoryException &exception) {
        }
        std::remove("test.txt");
}

void test__next__index_in_range__guardian_statue_on_index_is_returned() {
        Repository repo{};
        repo.set_repo_file("test.txt");
        GuardianStatue statue{"aa", "bb", 1, "cc"};
        repo.add_guardian_statue(statue);
        assert(repo.next() == statue);
        std::remove("test.txt");
}
void test__next__index_out_of_range__guardian_statue_on_first_position_is_returned() {
        Repository repo{};
        repo.set_repo_file("test.txt");
        GuardianStatue statue{"aa", "bb", 1, "cc"};
        repo.add_guardian_statue(statue);
        repo.add_guardian_statue(GuardianStatue{"ab", "cd", 2, "dc"});
        repo.next();
        repo.next();
        assert(repo.next() == statue);
        std::remove("test.txt");
}

void test__save__csv_file__guardian_statue_saved() {
        Repository repo{};
        repo.set_repo_file("test.txt");
        repo.set_my_list_file("test.csv");
        GuardianStatue statue{"aa", "bb", 1, "cc"};
        repo.add_guardian_statue(statue);
        repo.save("aa");
        assert(repo.get_my_list().at(0) == statue);
        std::remove("test.txt");
        std::remove("test.csv");
}

void test__save__html_file__guardian_statue_saved() {
        Repository repo{};
        repo.set_repo_file("test.txt");
        repo.set_my_list_file("test.html");
        GuardianStatue statue{"aa", "bb", 1, "cc"};
        repo.add_guardian_statue(statue);
        repo.save("aa");
        assert(repo.get_my_list().at(0) == statue);
        std::remove("test.txt");
        std::remove("test.html");
}

void test__save__wrong_file_type__exception_is_thrown() {
        Repository repo{};
        repo.set_repo_file("test.txt");
        repo.set_my_list_file("test.doc");
        GuardianStatue statue{"aa", "bb", 1, "cc"};
        repo.add_guardian_statue(statue);
        try {
            repo.save("aa");
            assert(false);
        } catch (RepositoryException& exception) {
        }
        std::remove("test.txt");
        std::remove("test.doc");
}
void test__save__no_file_type__exception_is_thrown() {
        Repository repo{};
        repo.set_repo_file("test");
        repo.set_my_list_file("test");
        GuardianStatue statue{"aa", "bb", 1, "cc"};
        repo.add_guardian_statue(statue);
        try {
            repo.save("aa");
            assert(false);
        } catch (RepositoryException& exception) {
        }
        std::remove("test.txt");
        std::remove("test");
}

void test__my_list__wrong_file_type__exception_is_thrown() {
        Repository repo{};
        repo.set_my_list_file("test.doc");
        try {
            repo.get_my_list();
            assert(false);
        } catch (RepositoryException& exception) {
        }
        std::remove("test.doc");
}
void test__my_list__no_file_type__exception_is_thrown() {
        Repository repo{};
        repo.set_my_list_file("test");
        try {
            repo.get_my_list();
            assert(false);
        } catch (RepositoryException& exception) {
        }
        std::remove("test");
}
void test__save__guardian_statue_already_exists_in_my_file__exception_is_thrown() {
    Repository repo{};
    repo.set_repo_file("test.txt");
    repo.set_my_list_file("test.csv");
    repo.add_guardian_statue(GuardianStatue{"aa", "bb", 2, "cc"});
    repo.save("aa");
    try {
        repo.save("aa");
        assert(false);
    } catch (RepositoryException &exception) {
    }
    std::remove("test.txt");
    std::remove("test.csv");
}

void test__save__guardian_statue_does_not_exist_in_repo__exception_is_thrown() {
    Repository repo{};
    repo.set_repo_file("test.txt");
    repo.set_my_list_file("test.csv");
    try {
        repo.save("ab");
        assert(false);
    } catch (RepositoryException &exception) {
    }
    std::remove("test.txt");
    std::remove("test.csv");
}
