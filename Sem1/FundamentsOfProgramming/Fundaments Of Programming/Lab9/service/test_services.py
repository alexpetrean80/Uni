from dataclasses import dataclass
from datetime import date
from unittest import TestCase

from domain.assignment import Assignment
from domain.grade import Grade
from domain.student import Student
from repo.console_repo.repository import Repo
from repo.console_repo.test_repo import DummyValidator
from service.services import Service


@dataclass
class DummyObject:
    id: int
    value: object


class TestService(TestCase):
    def setUp(self) -> None:
        self.grade_repo = Repo(DummyValidator)
        self.student_repo = Repo(DummyValidator)
        self.assignment_repo = Repo(DummyValidator)
        self.dummy_service = Service(self.student_repo, self.assignment_repo, self.grade_repo)
        self.dummy_service.populate_students()
        self.dummy_service.populate_assignments()
        self.dummy_service.give_assignment_to_student(8, 1)
        self.dummy_service.give_assignment_to_student(9, 1)
        self.dummy_service.grade_student(8, 1, 8)

    def test__add_student__correct__student_is_added(self):
        self.dummy_service.add_student(DummyObject(11, 11))
        self.assertIn(DummyObject(11, 11), self.dummy_service.list_students())

    def test__delete_student__correct__student_is_deleted(self):
        self.dummy_service.delete_student_by_id(8)
        self.assertNotIn(Student(8, "Alexandru Campeanu", 914), self.dummy_service.list_students())

    def test__delete_student__correct__assignment_is_also_deleted(self):
        self.dummy_service.delete_student_by_id(8)
        self.assertNotIn(Grade(1, 8, 8), list(self.grade_repo.find_all()))

    def test__update_student__correct_student_is_updated(self):
        self.dummy_service.update_student(1, DummyObject(1, 1))
        self.assertIn(DummyObject(1, 1), self.dummy_service.list_students())

    def test__list_students__correct__all_students_are_listed(self):
        students = self.dummy_service.list_students()
        self.assertListEqual(students, [Student(1, "Ghita Popescu", 912),
                                        Student(2, "George Pasca", 914),
                                        Student(3, "Andreea Ciurdas", 913),
                                        Student(4, "Silviu Ioan", 911),
                                        Student(5, "Vlad Georgescu", 912),
                                        Student(6, "Alin Lupu", 912),
                                        Student(7, "Paul Mardar", 913),
                                        Student(8, "Alexandru Campeanu", 914),
                                        Student(9, "Ioana Avram", 911),
                                        Student(10, "Cristina Stanciu", 911)])

    def test__add_assignment__correct__assignment_is_added(self):
        self.dummy_service.add_assignment(DummyObject(11, 11))
        self.assertIn(DummyObject(11, 11), self.dummy_service.list_assignments())

    def test__delete_assignment__correct__assignment_is_deleted(self):
        self.dummy_service.delete_assignment_by_id(1)
        self.assertNotIn(Assignment(1, "Lab1", date(2019, 10, 5)), self.dummy_service.list_assignments())

    def test__delete_assignment__correct__grade_is_also_deleted(self):
        self.dummy_service.delete_assignment_by_id(1)
        self.assertNotIn(Grade(1, 8, 8), list(self.grade_repo.find_all()))

    def test__update_assignment__correct_assignment_is_updated(self):
        self.dummy_service.update_assignment(1, DummyObject(1, 1))
        self.assertIn(DummyObject(1, 1), self.dummy_service.list_assignments())

    def test__list_assignments__correct__all_assignments_are_listed(self):
        assignments = self.dummy_service.list_assignments()
        self.assertListEqual(assignments, [Assignment(1, "Lab1", date(2019, 10, 5)),
                                           Assignment(2, "Lab2", date(2019, 10, 14)),
                                           Assignment(3, "Lab3", date(2019, 11, 2)),
                                           Assignment(4, "Lab4", date(2019, 11, 25)),
                                           Assignment(5, "Lab5", date(2019, 12, 4)),
                                           Assignment(6, "Lab6", date(2019, 12, 10)),
                                           Assignment(7, "Lab7", date(2019, 12, 28)),
                                           Assignment(8, "Lab8", date(2020, 1, 5)),
                                           Assignment(9, "Lab9", date(2020, 1, 23)),
                                           Assignment(10, "Lab10", date(2020, 2, 3))])
