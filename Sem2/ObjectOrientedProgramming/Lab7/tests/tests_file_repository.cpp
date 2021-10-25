#include "tests_file_repository.h"

void test__add_guardian_statue_file__guardian_statue_does_not_exist__guardian_statue_added() {
    std::remove("test.txt");
    FileRepository repository{"test.txt"};
    GuardianStatue statue{"aa", "bb", 1, "cc"};
    repository.add_guardian_statue(statue);
    assert(repository.get_all_guardian_statues().at(0) == statue);
    std::remove("test.txt");
}

void test__add_guardian_statue_file__guardian_statue_exists__exception_is_thrown() {
    std::remove("test.txt");
    FileRepository repository{"test.txt"};
    GuardianStatue statue{"aa", "bb", 1, "cc"};
    repository.add_guardian_statue(statue);
    try {
        repository.add_guardian_statue(statue);
    } catch (Exception& exception) {
        assert(exception.what() == "Guardian statue exists.");
    }
    std::remove("test.txt");
}

void test__delete_guardian_statue_file__guardian_statue_exists__guardian_statue_removed() {
    std::remove("test.txt");
    FileRepository repository{"test.txt"};
    GuardianStatue statue{"aa", "bb", 1, "cc"};
    repository.add_guardian_statue(statue);
    repository.delete_guardian_statue("aa");
    assert(repository.get_all_guardian_statues().empty());
    std::remove("test.txt");
}

void test__delete_guardian_statue_file__guardian_statue_does_not_exist__exception_is_thrown() {
    std::remove("test.txt");
    FileRepository repository{"test.txt"};
    try {
        repository.delete_guardian_statue("aa");
    } catch (Exception& exception) {
        assert(exception.what() == "Guardian statue doesn't exist.");
    }
    std::remove("test.txt");
}

void test__update_guardian_statue_file__guardian_statue_exists__guardian_statue_updated() {
    std::remove("test.txt");
    FileRepository repository{"test.txt"};
    GuardianStatue statue{"aa", "bb", 1, "cc"};
    GuardianStatue new_statue{"aa", "bc", 3, "cd"};
    repository.add_guardian_statue(statue);
    repository.update_guardian_statue(new_statue);
    auto statues = repository.get_all_guardian_statues();
    assert(statues.at(0) == new_statue);
    std::remove("test.txt");
}

void test__update_guardian_statue_file__guardian_statue_does_not_exist__exception_is_thrown() {
    std::remove("test.txt");
    FileRepository repository{"test.txt"};
    try {
        repository.update_guardian_statue(GuardianStatue{"aa", "bb", 1, "cc"});
    } catch (Exception& exception) {
        assert(exception.what() == "Guardian statue doesn't exist.");
    }
    std::remove("test.txt");
}
void test__set_file_name__valid_name__file_name_updated() {
    std::remove("test.txt");
    FileRepository repository{"aaaa"};
    repository.set_file_name("bbb");
    assert(repository.get_file_name() == "bbb");
    std::remove("test.txt");
}
void test__next__first_guardian_statue__first_guardian_statue_is_returned() {
    std::remove("test.txt");
    FileRepository repository{"test.txt"};
    GuardianStatue statue{"aa", "bb", 1, "cc"};
    repository.add_guardian_statue(statue);
    assert(repository.next() == statue);
    std::remove("test.txt");
}
void test__next__last_guardian_statue__first_guardian_statue_is_returned() {
    std::remove("test.txt");
    FileRepository repository{"test.txt"};
    GuardianStatue statue{"aa", "bb", 1, "cc"};
    repository.add_guardian_statue(statue);
    repository.add_guardian_statue(GuardianStatue{"ab", "cd", 3, "ds"});
    repository.next();
    repository.next();
    assert(repository.next() == statue);
    std::remove("test.txt");
}
void test__save__repository_not_empty__guardian_statue_is_saved() {
    std::remove("test.txt");
    FileRepository repository{"test.txt"};
    GuardianStatue statue{"aa", "bb", 1, "cc"};
    repository.add_guardian_statue(statue);
    repository.save();
    assert(repository.get_my_list().at(0) == statue);
    std::remove("test.txt");
}
void test__save__repository_empty__exception_is_thrown() {
    std::remove("test.txt");
    FileRepository repository{"test.txt"};
    GuardianStatue statue{"aa", "bb", 1, "cc"};
    try {
        repository.save();
        assert(false);
    } catch (Exception& exception) {
    }
    std::remove("test.txt");
}
