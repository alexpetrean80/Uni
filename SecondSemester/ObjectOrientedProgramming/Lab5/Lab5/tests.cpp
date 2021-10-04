#include "tests.h"

#include <iostream>




void test_create_guardian_statue__valid_data__guardian_statue_created()
{
    auto *statue = new GuardianStatue("aaa", "bbb", 1, "ccc");
    assert(statue->get_power_word_name() == "aaa" &&
         statue->get_material() == "bbb" && statue->get_age() == 1 &&
         statue->get_corporeal_form() == "ccc");
    delete statue;
}

void test_to_string__guardian_statue_converted_to_string__success()
{
    auto *statue = new GuardianStatue("aaa", "bbb", 1, "ccc");
    assert(statue->to_string() == "aaa bbb 1 ccc");
    delete statue;
}

void test_operator_equality__guardian_statues_equal__true()
{
  auto *firstStatue = new GuardianStatue("aaa", "bbb", 1, "ccc");
  auto *secondStatue = new GuardianStatue("aaa", "bbb", 1, "ccc");
  assert(*firstStatue == *secondStatue);
  delete firstStatue;
  delete secondStatue;
}

void test_operator_equality__guardian_statues_not_equal__false()
{
  auto *first_statue = new GuardianStatue("aaa", "bbb", 1, "ccc");
  auto *second_statue = new GuardianStatue("aba", "bcb", 12, "cdc");
  assert(!(*first_statue == *second_statue));
  delete first_statue;
  delete second_statue;
}

void test_operator_non_equality__guardian_statues_not_equal__true()
{
  auto *firstStatue = new GuardianStatue("aaa", "bbb", 1, "ccc");
  auto *secondStatue = new GuardianStatue("aba", "bcb", 12, "cdc");
  assert(*firstStatue != *secondStatue);
  delete firstStatue;
  delete secondStatue;
}

void test_operator_non_equality__guardian_statues_equal__false()
{
  auto *firstStatue = new GuardianStatue("aaa", "bbb", 1, "ccc");
  auto *secondStatue = new GuardianStatue("aaa", "bbb", 1, "ccc");
  assert(!(*firstStatue != *secondStatue));
  delete firstStatue;
  delete secondStatue;
}

void test_operator_initialization__guardian_statue_initialized__true()
{
  auto *statue = new GuardianStatue("aaa", "bbb", 1, "ccc");
  auto new_statue = *statue;
  assert(new_statue == *statue);
  delete statue;
}

void testOperatorEquality_GuardianStatuesNotEqual_True()
{
  auto *first_statue = new GuardianStatue("aaa", "bbb", 1, "ccc");
  auto *second_statue = new GuardianStatue("bbb", "bccb", 21, "ccdc");
  assert(*first_statue != *second_statue);
  delete first_statue;
  delete second_statue;
}

void tests_guardian_statue() {
  test_create_guardian_statue__valid_data__guardian_statue_created();
  test_to_string__guardian_statue_converted_to_string__success();
  test_operator_equality__guardian_statues_equal__true();
  test_operator_equality__guardian_statues_not_equal__false();
  test_operator_non_equality__guardian_statues_not_equal__true();
  test_operator_non_equality__guardian_statues_equal__false();
  test_operator_initialization__guardian_statue_initialized__true();
}


void test_create_dynamic_vector_default_constructor_dynamic_vector_is_created()
{
  auto* dynamic_vector = new DynamicVector<int>();
  assert(dynamic_vector->get_size() == 0);
  delete dynamic_vector;
}

void test_create_dynamic_vector_copy_constructor_dynamic_vector_is_created()
{
    auto* dynamic_vector = new DynamicVector<int>();
  dynamic_vector->push_back(1);
    auto *copy_dynamic_vector = new DynamicVector<int>(*dynamic_vector);
  delete dynamic_vector;
  assert(copy_dynamic_vector->get_size() == 1);
  delete copy_dynamic_vector;
}

void test_push_back_one_int_int_is_added()
{
    auto* dynamic_vector = new DynamicVector<int>();
    dynamic_vector->push_back(1);
    assert(dynamic_vector->get_size() == 1);
    delete dynamic_vector;
}

void test_push_back_many_int_all_int_are_added()
{
    auto* dynamic_vector = new DynamicVector<int>();
  for (auto i = 0; i < 1000; i++) {
      dynamic_vector->push_back(i);
  }
    assert(dynamic_vector->get_size() == 1000);
    delete dynamic_vector;
}

void test_pop_back_valid_position_item_on_position_is_removed()
{
    auto* dynamic_vector = new DynamicVector<int>();
    for (int i = 0; i < 1000; i++) {
      dynamic_vector->push_back(i);
  }
    dynamic_vector->pop_back(52);
  assert(dynamic_vector->get_size() == 999);
    delete dynamic_vector;
}

void test_pop_back_invalid_position_exception_thrown()
{
     auto* dynamic_vector = new DynamicVector<int>();
  for (int i = 0; i < 1000; i++) {
      dynamic_vector->push_back(i);
  }
  try {
    dynamic_vector->pop_back(1001);
    assert(false);
  } catch (std::invalid_argument&) {
  }
  delete dynamic_vector;
}

void test_at_valid_position_item_on_position_is_returned()
{
    auto* dynamic_vector = new DynamicVector<int>();
    for (int i = 0; i < 1000; i++) {
      dynamic_vector->push_back(i);
  }
    assert(dynamic_vector->at(52) == 52);
  delete dynamic_vector;
}

void test_at_invalid_position_exception_thrown()
{
    auto* dynamic_vector = new DynamicVector<int>();
    for (int i = 0; i < 1000; i++) {
      dynamic_vector->push_back(i);
  }
    try {
    auto element = dynamic_vector->at(1001);
      assert(false);
    } catch (std::invalid_argument &) {

    }
  delete dynamic_vector;
}

void test_insert_valid_position_item_is_added_on_position()
{
    auto* dynamic_vector = new DynamicVector<int>();
    for (int i = 0; i < 1000; i++) {
      dynamic_vector->push_back(i);
  }
    dynamic_vector->insert(4, 74);
  assert(dynamic_vector->at(74) == 4);
    delete dynamic_vector;
}

void test_insert_invalid_position_exception_thrown()
{
    auto* dynamic_vector = new DynamicVector<int>();
    for (int i = 0; i < 1000; i++) {
      dynamic_vector->push_back(i);
    }
    try {
        dynamic_vector->insert(4, 1001);
        assert(false);
    } catch (std::invalid_argument &) {
    }
   
    delete dynamic_vector;
}

void tests_dynamic_vector() {
  test_create_dynamic_vector_default_constructor_dynamic_vector_is_created();
  test_create_dynamic_vector_copy_constructor_dynamic_vector_is_created();
  test_push_back_one_int_int_is_added();
  test_push_back_many_int_all_int_are_added();
  test_pop_back_valid_position_item_on_position_is_removed();
  test_pop_back_invalid_position_exception_thrown();
  test_at_valid_position_item_on_position_is_returned();
  test_at_invalid_position_exception_thrown();
  test_insert_valid_position_item_is_added_on_position();
  test_insert_invalid_position_exception_thrown();
}

void test_add_repo_valid_statue_statue_added()
{
    Repo repo;
    auto *statue = new GuardianStatue("aaa", "bbb", 1, "ccc");
    repo.add(*statue);
    assert(repo.get_statues().at(0) == *statue);
    delete statue;
}

void test_remove_repo_valid_string_statue_deleted()
{
    Repo repo;
    auto *statue = new GuardianStatue("aaa", "bbb", 1, "ccc");
    repo.add(*statue);
    repo.remove("aaa");
    assert(repo.get_statues().get_size() == 0);
    delete statue;
}

void test_update_repo_valid_statue_statue_updated()
{
    Repo repo;
    auto *statue = new GuardianStatue("aaa", "bbb", 1, "ccc");
    repo.add(*statue);
    auto *new_statue = new GuardianStatue("aaa", "bcb", 2, "ddd");
    repo.update(*new_statue);
    assert(repo.get_statues().at(0) == *new_statue);
    delete statue;
    delete new_statue;
}

void test_get_statues_repo_valid_statues_returned()
{
    Repo repo;
    auto *statue = new GuardianStatue("aaa", "bbb", 1, "ccc");
    repo.add(*statue);
    assert(repo.get_statues().get_size() == 1);
    delete statue;
}

void tests_repo()
{
    test_add_repo_valid_statue_statue_added();
    test_remove_repo_valid_string_statue_deleted();
    test_update_repo_valid_statue_statue_updated();
    test_get_statues_repo_valid_statues_returned();
}

void test_add_service_mode_a_statue_added()
{
    Service service;
  service.set_mode("A");
    service.add_statue("aaa", "bbb", 1, "ccc");
    assert(service.get_statues().at(0).get_power_word_name() == "aaa" &&
           service.get_statues().at(0).get_material() == "bbb" &&
           service.get_statues().at(0).get_age() == 1 &&
           service.get_statues().at(0).get_corporeal_form() == "ccc");
}

void test_add_service_mode_b_exception_thrown()
{
    Service service;
  service.set_mode("B");
    try {
        service.add_statue("aaa", "bbb", 1, "ccc");
      assert(false);
    } catch (...) {
    }
}

void test_delete_service_mode_a_statue_deleted()
{
    Service service;
  service.set_mode("A");
    service.add_statue("aaa", "bbb", 1, "ccc");
    service.delete_statue("aaa");
    assert(service.get_statues().get_size() == 0);
}

void test_delete_service_mode_b_exception_thrown()
{
    Service service;
  service.set_mode("A");
  service.add_statue("aaa", "bbb", 1, "ccc");
  service.set_mode("B");
    try {
    service.delete_statue("aaa");
      assert(false);
    } catch (...) {
    }
}

void test_update_service_mode_a_statue_updated()
{
    Service service;
  service.set_mode("A");
    service.add_statue("aaa", "bbb", 1, "ccc");
    service.update_statue("aaa", "def", 2, "ghi");
    assert(service.get_statues().at(0).get_power_word_name() == "aaa" &&
           service.get_statues().at(0).get_material() == "def" &&
           service.get_statues().at(0).get_age() == 2 &&
           service.get_statues().at(0).get_corporeal_form() == "ghi");
}

void test_update_service_mode_b_exception_thrown()
{
    Service service;
    service.set_mode("A");
    service.add_statue("aaa", "bbb", 1, "ccc");
    service.set_mode("B");
    try {
        service.update_statue("aaa", "def", 2, "ghi");
        assert(false);
    } catch (...) {
    }
}

void test_get_statues_service_valid_statues_returned()
{
    Service service;
    service.add_statue("aaa", "bbb", 1, "ccc");
    assert(service.get_statues().get_size() == 1);
}

void tests_service()
{
    test_add_service_mode_a_statue_added();
    test_delete_service_mode_a_statue_deleted();
    test_update_service_mode_a_statue_updated();
    test_get_statues_service_valid_statues_returned();
}

void tests() {
  tests_guardian_statue();
  tests_dynamic_vector();
  tests_repo();
  tests_service();
}