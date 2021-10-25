from datetime import date
from functools import reduce

from domain.assignment import Assignment
from domain.exceptions import ServiceException
from domain.grade import Grade
from domain.student import Student


class Service:
    def __init__(self, students, assignments, grades):
        self.__students = students
        self.__assignments = assignments
        self.__grades = grades

    def populate_students(self):
        """
        populate the "students" repository with 10 hardcoded objects
        :return: None
        """
        self.__students.save(1, Student(1, "Ghita Popescu", 912))
        self.__students.save(2, Student(2, "George Pasca", 914))
        self.__students.save(3, Student(3, "Andreea Ciurdas", 913))
        self.__students.save(4, Student(4, "Silviu Ioan", 911))
        self.__students.save(5, Student(5, "Vlad Georgescu", 912))
        self.__students.save(6, Student(6, "Alin Lupu", 912))
        self.__students.save(7, Student(7, "Paul Mardar", 913))
        self.__students.save(8, Student(8, "Alexandru Campeanu", 914))
        self.__students.save(9, Student(9, "Ioana Avram", 911))
        self.__students.save(10, Student(10, "Cristina Stanciu", 911))

    def populate_assignments(self):
        """
        populathe the "assignments" repository with 10 hardcoded objects
        :return: None
        """
        self.__assignments.save(1, Assignment(1, "Lab1", date(2019, 10, 5)))
        self.__assignments.save(2, Assignment(2, "Lab2", date(2019, 10, 14)))
        self.__assignments.save(3, Assignment(3, "Lab3", date(2019, 11, 2)))
        self.__assignments.save(4, Assignment(4, "Lab4", date(2019, 11, 25)))
        self.__assignments.save(5, Assignment(5, "Lab5", date(2019, 12, 4)))
        self.__assignments.save(6, Assignment(6, "Lab6", date(2019, 12, 10)))
        self.__assignments.save(7, Assignment(7, "Lab7", date(2019, 12, 28)))
        self.__assignments.save(8, Assignment(8, "Lab8", date(2020, 1, 5)))
        self.__assignments.save(9, Assignment(9, "Lab9", date(2020, 1, 23)))
        self.__assignments.save(10, Assignment(10, "Lab10", date(2020, 2, 3)))

    def populate_grades(self):
        self.__grades.save((3, 2), Grade(3, 2, 7))
        self.__grades.save((4, 1), Grade(4, 1, 7))
        self.__grades.save((7, 8), Grade(7, 8, 0))
        self.__grades.save((9, 1), Grade(9, 1, 9))
        self.__grades.save((7, 2), Grade(7, 2, 8))
        self.__grades.save((9, 4), Grade(9, 5, 0))
        self.__grades.save((4, 4), Grade(4, 4, 10))
        self.__grades.save((2, 3), Grade(2, 3, 9))
        self.__grades.save((8, 2), Grade(8, 2, 10))
        self.__grades.save((9, 9), Grade(9, 9, 9))

    def add_student(self, student: Student):
        """
        adds an object of type Student to the "students" repository
        :param student: object of type Student
        :return: None
        """
        self.__students.save(student.id, student)

    def delete_student_by_id(self, student_id: int):
        """
        deletes a Student object from the "students" repository
        :param student_id: unique ID of the object
        :return: None
        """
        self.__students.delete_by_id(student_id)
        for assignment in self.__assignments.find_all():
            if self.__grades.find_by_id((assignment.id, student_id)):
                self.__grades.delete_by_id((assignment.id, student_id))

    def find_assignments_given_to_student(self, student_id: int) -> list:
        grades = list()
        for assignment in self.__assignments.find_all():
            if self.__grades.find_by_id((assignment.id, student_id)):
                grades.append(self.__grades.find_by_id((assignment.id, student_id)))
        return grades

    def update_student(self, student_id, student):
        """
        updates the value of an object from the "students" repository
        :param student_id: unique ID of the object
        :param student: new value of the object
        :return: None
        """
        self.__students.update(student_id, student)

    def list_students(self) -> list:
        """
        :return: returns all the objects from the "students" repository
        """
        return sorted(self.__students.find_all(), key=lambda student: student.id)

    def find_student_by_id(self, student_id: int) -> Student:
        return self.__students.find_by_id(student_id)

    def add_assignment(self, assignment: Assignment):
        """
        adds an object of type Assignment to the "assignments" repository
        :param assignment_id: unique ID of the object
        :param assignment: value of the object
        :return: None
        """
        self.__assignments.save(assignment.id, assignment)

    def find_assignments_given(self, assignment_id: int) -> list:
        grades = list()
        for grade in self.__grades.find_all():
            if grade.assignment == assignment_id:
                grades.append(grade)
        return grades

    def delete_assignment_by_id(self, assignment_id: int) -> list:
        """
        deletes an Assignment object from the "assignments" repository
        :param assignment_id: unique ID of the object
        :return: None
        """
        deleted_entities = list()
        self.__assignments.delete_by_id(assignment_id)
        for student in self.__students.find_all():
            if self.__grades.find_by_id((assignment_id, student.id)):
                deleted_entities.append(self.__grades.find_by_id((assignment_id, student.id)))
                self.__grades.delete_by_id((assignment_id, student.id))
        return deleted_entities

    def update_assignment(self, assignment_id: int, assignment: Assignment):
        """
        updates the value of an object from the "assignments" repository
        :param assignment_id: unique ID of the object
        :param assignment: new value of the object
        :return: None
        """
        self.__assignments.update(assignment_id, assignment)

    def list_assignments(self) -> list:
        """
        :return: returns all the objects from the "assignments" repository
        """
        return sorted(self.__assignments.find_all(), key=lambda assignment: assignment.id)

    def find_assignment_by_id(self, assignment_id: int) -> Assignment:
        return self.__assignments.find_by_id(assignment_id)

    def give_assignment_to_student(self, assignment_id: int, student_id: int):
        """
        creates a new entity in the grades repository with grade 0, meaning a student received an assignment
        :param assignment_id: unique ID of the Assignment object
        :param student_id: unique ID of the Student object
        :return: None
        """
        if self.__assignments.find_by_id(assignment_id) is None:
            raise ServiceException("Assignment does not exist.")
        if self.__students.find_by_id(student_id) is None:
            raise ServiceException("Student does not exist.")
        grade = Grade(assignment_id, student_id, 0)
        self.__grades.save((assignment_id, student_id), grade)

    def give_assignment_to_group(self, assignment_id: int, group: int):
        """
        applies the "give_assignment_to_student" method for all students of a given group
        :param assignment_id: unique ID of the assignment
        :param group: group number
        :return: None
        """
        for student in self.__students.find_all():
            if student.group == group:
                self.give_assignment_to_student(assignment_id, student.id)

    def grade_student(self, assignment_id: int, student_id: int, student_grade: int):
        """
        updates a Grade object into the "grades" repo, meaning an assignment was graded for a student
        if the assignment's deadline has passed, ServiceException is thrown
        :param assignment_id:  unique ID of the Assignment object
        :param student_id: unique ID of the Student object
        :param student_grade: grade received by the student for the assignment
        :return: None
        """
        grade_id = (assignment_id, student_id)
        if self.__grades.find_by_id(grade_id) is None:
            raise ServiceException("Assignment {0} was not given to student {1}".format(assignment_id, student_id))
        if self.__grades.find_by_id(grade_id).grade != 0:
            raise ServiceException("Student {0} was already graded for assignment {1}.".format(assignment_id, student_id))
        if self.__assignments.find_by_id(assignment_id).deadline < date.today():
            raise ServiceException("You cannot grade an assignment whose deadline has passed.")
        self.__grades.update(grade_id, Grade(assignment_id, student_id, student_grade))

    def delete_grade(self, grade_id: int):
        self.__grades.delete_by_id(grade_id)

    def filter_students_by_assignment_given(self, assignment_id: int) -> list:
        """
        filters the students which have received a given assignment
        :param assignment_id: unique ID of the Assignment object
        :return: list of Student objects
        """
        students = [self.__students.find_by_id(grade.student) if grade.assignment == assignment_id else None for grade
                    in self.__grades.find_all()]
        students = list(filter(lambda student: student is not None, students))
        if len(students) == 0:
            raise ServiceException("No student was given assignment {}.".format(assignment_id))
        return students

    def __is_student_late_with_assignment(self, student_id: int, assignment_id: int) -> bool:
        """
        checks whether an assignment given to a student has it's deadline passed
        :param student_id: unique ID of the Student object
        :param assignment_id: unique ID of the Assignment object
        :return: boolean value according to the state of the assignment
        """
        for grade in self.__grades.find_all():
            if grade.grade == 0:
                if grade.assignment == assignment_id and grade.student == student_id:
                    if self.__assignments.find_by_id(assignment_id).deadline < date.today():
                        return True
        return False

    def filter_students_by_assignment_late(self) -> list:
        """
        filters the students which have at least an assignment with it's deadline passed
        :return: list of Student objects
        """
        students = list()
        for student in self.__students.find_all():
            for assignment in self.__assignments.find_all():
                if self.__is_student_late_with_assignment(student.id, assignment.id):
                    students.append(student)
        if len(students) == 0:
            raise ServiceException("No student is late with any assignment.")
        return students

    def __get_grades_of_student(self, student_id: int) -> list:
        """
        gets all the grades a student got for all the assignments
        :param student_id: unique ID of the student
        :return: list of grades
        """
        grades = list()
        for grade in self.__grades.find_all():
            if grade.student == student_id:
                grades.append(int(grade.grade))
        if len(grades) == 0:
            raise ServiceException("The student wasn't graded for any assignment yet.")
        return grades

    def __compute_average_grade_of_student(self, student_id: int) -> int:
        """
        computes the average grade of a student
        :param student_id: unique ID of the Student object
        :return: arithmetic average of the grades
        """
        grades = self.__get_grades_of_student(student_id)
        return reduce(lambda grade, other_grade: grade + other_grade, grades) / len(grades)

    def __get_graded_students(self) -> list:
        """
        gets all the students who received grades for at least an assignment
        :return: list of Student objects
        """
        students = list()
        print(len(self.__grades.find_all()))
        if len(self.__grades.find_all()) == 0:
            raise ServiceException("No student has been given an assignment yet.")
        for grade in self.__grades.find_all():
            print(grade.student, grade.assignment, grade.grade)
            if grade.grade != 0:
                if self.__students.find_by_id(grade.student) not in students:
                    students.append(self.__students.find_by_id(grade.student))
        if len(students) == 0:
            raise ServiceException("No student was graded yet.")
        return students

    def sort_students_by_average_grade(self) -> list:
        """
        descendingly sorts student who received at least a grade
        :return: sorted list of students
        """
        students = self.__get_graded_students()
        students.sort(reverse=True, key=lambda student: self.__compute_average_grade_of_student(student.id))
        return students

