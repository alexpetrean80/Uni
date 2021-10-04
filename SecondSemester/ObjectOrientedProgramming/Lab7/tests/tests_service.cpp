#include "tests_service.h"

void test__add_guardian_statue__mode_A__guardian_statue_is_added() {
    Service service{"test.txt", 'A'};
    service.add_guardian_statue("aa", "bb", 1, "dd");
    assert((service.get_all_statues().at(0) == GuardianStatue{"aa", "bb", 1, "dd"}));
    std::remove("test.txt");
}

void test__add_guardian_statue__mode_B__exception_is_thrown() {
    Service service{"test.txt", 'B'};
    try {
        service.add_guardian_statue("aa", "bb", 1, "dd");
    } catch (Exception& exception) {
        assert(exception.what() == "Invalid mode.");
    }
    std::remove("test.txt");
}

void test__delete_guardian_statue__mode_A__guardian_statue_is_deleted() {
    Service service{"test.txt", 'A'};
    service.add_guardian_statue("aa", "bb", 1, "dd");
    service.delete_guardian_statue("aa");
    assert(service.get_all_statues().empty());
    std::remove("test.txt");
}

void test__delete_guardian_statue__mode_B__exception_is_thrown() {
    Service service{"test.txt", 'A'};
    service.add_guardian_statue("aa", "bb", 1, "dd");
    service.set_mode('B');
    try {
        service.delete_guardian_statue("aa");
    } catch (Exception& exception) {
        assert(exception.what() == "Invalid mode.");
    }
    std::remove("test.txt");
}

void test__update_guardian_statue__mode_A__guardian_statue_is_updated() {
    Service service{"test.txt", 'A'};
    service.add_guardian_statue("aa", "bb", 1, "dd");
    service.update_guardian_statue("aa", "bc", 2, "cd");
    assert((service.get_all_statues().at(0) == GuardianStatue{"aa", "bc", 2, "cd"}));
    std::remove("test.txt");
}

void test__update_guardian_statue__mode_B__exception_is_thrown() {
    Service service{"test.txt", 'A'};
    service.add_guardian_statue("aa", "bb", 1, "dd");
    service.set_mode('B');
    try {
        service.update_guardian_statue("aa", "bc", 2, "cd");
    } catch (Exception& exception) {
        assert(exception.what() == "Invalid mode.");
    }
    std::remove("test.txt");
}
void test__get_mode__valid_mode__mode_is_returned() {
    Service service{"test.txt", 'A'};
    assert(service.get_mode() == 'A');
    std::remove("test.txt");
}
void test__get_file__valid_file__file_is_returned() {
    Service service{"test.txt", 'A'};
    assert(service.get_file() == "test.txt");
}
void test__set_file__valid_file__file_is_set() {
    Service service{};
    service.set_file("test.txt");
    assert(service.get_file() == "test.txt");
    std::remove("test.txt");
}
void test__save__mode_B__guardian_statue_is_saved() {
    Service service{"test.txt", 'A'};
    service.add_guardian_statue("aa", "bb", 1, "dd");
    service.set_mode('B');
    service.save();
    assert((service.get_my_list().at(0) == GuardianStatue{"aa", "bb", 1, "dd"}));
    std::remove("test.txt");
}
void test__save__mode_A__exception_is_thrown() {
    Service service{"test.txt", 'A'};
    service.add_guardian_statue("aa", "bb", 1, "dd");
    try {
        service.save();
        assert(false);
    } catch (Exception& exception) {
    }
    std::remove("test.txt");
}
void test__next__mode_B__guardian_statue_is_returned() {
    Service service{"test.txt", 'A'};
    service.add_guardian_statue("aa", "bb", 1, "dd");
    service.set_mode('B');
    assert((service.next() == GuardianStatue{"aa", "bb", 1, "dd"}));
    std::remove("test.txt");
}
void test__next__mode_A__exception_is_thrown() {
    Service service{"test.txt", 'A'};
    service.add_guardian_statue("aa", "bb", 1, "dd");
    try {
        service.next();
        assert(false);
    } catch (Exception& exception) {
    }
    std::remove("test.txt");
}
void test__get_my_list__mode_A__exception_is_thrown() {
    Service service{"test.txt", 'A'};
    service.add_guardian_statue("aa", "bb", 1, "dd");
    try {
        service.get_my_list();
        assert(false);
    } catch (Exception& exception) {
    }
    std::remove("test.txt");
}
