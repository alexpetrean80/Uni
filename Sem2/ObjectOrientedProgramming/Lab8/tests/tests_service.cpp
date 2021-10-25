#include "tests_service.h"


void test__set_mode__valid_mode__mode_is_set() {
        Service service{};
        service.set_mode('A');
        assert(service.get_mode() == 'A');
}

void test__set_repo_file_name__valid_file_name__file_name_is_set() {
    Service service{};
    service.set_repo_file("test.txt");
    assert(service.get_repo_file() == "test.txt");
}

void test__set_my_list_file_name__valid_file_name__file_name_is_set() {
    Service service{};
    service.set_my_list_file("test.csv");
    assert(service.get_my_list_file() == "test.csv");
}

void test__add_guardian_statue__mode_A__guardian_statue_added() {
    Service service{};
    service.set_mode('A');
    service.set_repo_file("test.txt");
    service.add_guardian_statue("aa", "bb", 1, "cc");
    assert((service.get_guardian_statues().at(0) == GuardianStatue{"aa", "bb", 1, "cc"}));
    std::remove("test.txt");
}

void test__add_guardian_statue__mode_B__exception_is_thrown() {
    Service service{};
    service.set_mode('B');
    service.set_repo_file("test.txt");
    try {
        service.add_guardian_statue("aa", "bb", 1, "cc");
        assert(false);
    } catch (ServiceException& exception) {
    }
}

void test__delete_guardian_statue__mode_A__guardian_statue_deleted() {
    Service service{};
    service.set_mode('A');
    service.set_repo_file("test.txt");
    service.add_guardian_statue("aa", "bb", 1, "cc");
    service.delete_guardian_statue("aa");
    assert(service.get_guardian_statues().empty());
    std::remove("test.txt");
}

void test__delete_guardian_statue__mode_B__exception_is_thrown() {
    Service service{};
    service.set_mode('A');
    service.set_repo_file("test.txt");
    service.add_guardian_statue("aa", "bb", 1, "cc");
    service.set_mode('B');
    try {
        service.delete_guardian_statue("aa");
        assert(false);
    } catch (ServiceException& exception) {
    }
    std::remove("test.txt");
}

void test__update_guardian_statue__mode_A__guardian_statue_updated() {
    Service service{};
    service.set_mode('A');
    service.set_repo_file("test.txt");
    service.add_guardian_statue("aa", "bb", 1, "cc");
    service.update_guardian_statue("aa", "bc", 2, "cd");
    assert((service.get_guardian_statues().at(0) == GuardianStatue{"aa", "bc", 2, "cd"}));
    std::remove("test.txt");
}

void test__update_guardian_statue__mode_B__exception_is_thrown() {
    Service service{};
    service.set_mode('A');
    service.set_repo_file("test.txt");
    service.add_guardian_statue("aa", "bb", 1, "cc");
    service.set_mode('B');
    try {
        service.update_guardian_statue("aa", "bc", 2, "cd");
        assert(false);
    } catch (ServiceException& exception) {
    }
    std::remove("test.txt");
}

void test__next__mode_B__guardian_statue_returned() {
    Service service{};
    service.set_mode('A');
    service.set_repo_file("test.txt");
    service.add_guardian_statue("aa", "bb", 1, "cc");
    service.set_mode('B');
    assert((service.next() == GuardianStatue{"aa", "bb", 1, "cc"}));
    std::remove("test.txt");
}

void test__next__mode_A__exception_is_thrown() {
    Service service{};
    service.set_mode('A');
    service.set_repo_file("test.txt");
    service.add_guardian_statue("aa", "bb", 1, "cc");
    try {
        service.next();
        assert(false);
    } catch (ServiceException& exception) {
    }
    std::remove("test.txt");
}

void test__save__mode_B__guardian_statue_saved() {
    Service service{};
    service.set_mode('A');
    service.set_repo_file("test.txt");
    service.set_my_list_file("test.csv");
    service.add_guardian_statue("aa", "bb", 1, "cc");
    service.set_mode('B');
    service.save("aa");
    assert((service.get_my_list().at(0) == GuardianStatue{"aa", "bb", 1, "cc"}));
    std::remove("test.txt");
    std::remove("test.csv");
}

void test__save__mode_A__exception_is_thrown() {
        Service service{};
        service.set_mode('A');
        service.set_repo_file("test.txt");
        service.set_my_list_file("test.csv");
        service.add_guardian_statue("aa", "bb", 1, "cc");
        try {
            service.save("aa");
            assert(false);
        } catch (ServiceException& exception) {
        }
        std::remove("test.txt");
        std::remove("test.csv");
}

