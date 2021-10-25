import re
from datetime import date

from domain.assignment import Assignment
from domain.exceptions import UserInputException
from domain.student import Student
from service.undo.handler import UndoHandler
from service.undo.manager import UndoManager


class ConsolePrint:
    @staticmethod
    def print_menu():
        print("1. Add student.\n"
              "2. Remove student.\n"
              "3. Update student.\n"
              "4. List students.\n"
              "5. Add assignment.\n"
              "6. Remove assignment.\n"
              "7. Update assignment.\n"
              "8. List assignments.\n"
              "9. Give assignment to student.\n"
              "10. Give assignment to group.\n"
              "11. Grade student.\n"
              "12. List students who received assignments.\n"
              "13. List students late in handing assignments.\n"
              "14. List students by average grade.\n"
              "15. Undo.\n"
              "16. Redo.\n")

    @staticmethod
    def __print_student(student):
        print("ID: {0} | Name: {1} | Group: {2}".format(student.id, student.name, student.group))

    @staticmethod
    def print_students(students):
        for student in students:
            ConsolePrint.__print_student(student)
        print()

    @staticmethod
    def print_assignment(assignment):
        print("ID: {0} | Description: {1} | Deadline: {2}".format(assignment.id, assignment.description,
                                                                  assignment.deadline))

    @staticmethod
    def print_assignments(assignments):
        for assignment in assignments:
            ConsolePrint.print_assignment(assignment)


class ConsoleRead:
    @staticmethod
    def check_if_input_is_number(input_string: str) -> bool:
        if re.match(r"\d", input_string):
            return True
        return False

    @staticmethod
    def read_menu_entry() -> int:
        user_input = input("\nEnter the operation: ")
        if ConsoleRead.check_if_input_is_number(user_input):
            if 0 < int(user_input) < 17:
                return int(user_input)
            else:
                raise UserInputException("Input needs to be between 1 and 16.")
        else:
            raise UserInputException("Input needs to be an integer.")

    @staticmethod
    def read_date() -> date:
        year = input("Enter the year: ")
        if not ConsoleRead.check_if_input_is_number(year):
            raise UserInputException("Year needs to be an integer.")
        month = input("Enter the month: ")
        if not ConsoleRead.check_if_input_is_number(month):
            if not (0 < month < 13):
                raise UserInputException("Month needs to be between 1 and 12.")
            raise UserInputException("Month needs to be an integer.")
        day = input("Enter the day: ")
        if not ConsoleRead.check_if_input_is_number(day):
            if not (0 < day < 31):
                raise UserInputException("Day needs to be between 1 and 30.")
        return date(int(year), int(month), int(day))

    @staticmethod
    def read_id(entity: str) -> int:
        id = input("Enter the {}'s ID: ".format(entity))
        if ConsoleRead.check_if_input_is_number(id):
            return int(id)
        raise UserInputException("ID needs to be an integer.")

    @staticmethod
    def read_student() -> Student:
        student_id = ConsoleRead.read_id("student")
        student_first_name = input("Enter the student's first name: ").lower()
        student_last_name = input("Enter the student's last name: ").lower()
        student_name = student_first_name.capitalize() + ' ' + student_last_name.capitalize()
        student_group = input("\nEnter the student's group: ")
        if not ConsoleRead.check_if_input_is_number(student_group):
            raise UserInputException("Student group number needs to be an integer.")
        return Student(int(student_id), student_name, int(student_group))

    @staticmethod
    def read_assignment() -> Assignment:
        assignment_id = ConsoleRead.read_id("assignment")
        assignment_description = input("\nEnter the assignment's description: ").lower()
        assignment_deadline = ConsoleRead.read_date()
        return Assignment(int(assignment_id), assignment_description.capitalize(), assignment_deadline)


class Console:
    def __init__(self, service):
        self.__service = service

    def __add_student(self):
        student = ConsoleRead.read_student()
        grades = self.__service.find_assignments_given_to_student(student.id)
        self.__service.add_student(student)
        UndoManager.register_operation(self.__service, UndoHandler.ADD_STUDENT, student)
        UndoManager.register_operation_to_redo(self.__service, UndoHandler.DELETE_STUDENT, student, grades)

    def __remove_student(self):
        student_id = ConsoleRead.read_id("student")
        student = self.__service.find_student_by_id(student_id)
        grades = self.__service.find_assignments_given_to_student(student_id)
        self.__service.delete_student_by_id(student_id)
        UndoManager.register_operation(self.__service, UndoHandler.DELETE_STUDENT, student, grades)
        UndoManager.register_operation_to_redo(self.__service, UndoHandler.ADD_STUDENT, student)

    def __update_student(self):
        new_student = ConsoleRead.read_student()
        student = self.__service.find_student_by_id(new_student.id)
        self.__service.update_student(new_student.id, new_student)
        UndoManager.register_operation(self.__service, UndoHandler.UPDATE_STUDENT, student)
        UndoManager.register_operation_to_redo(self.__service, UndoHandler.UPDATE_STUDENT, new_student)

    def __list_students(self):
        ConsolePrint.print_students(self.__service.list_students())

    def __add_assignment(self):
        assignment = ConsoleRead.read_assignment()
        grades = self.__service.find_assignments_given(assignment.id)
        self.__service.add_assignment(assignment)
        UndoManager.register_operation(self.__service, UndoHandler.ADD_ASSIGNMENT, assignment)
        UndoManager.register_operation_to_redo(self.__service, UndoHandler.DELETE_ASSIGNMENT, assignment, grades)

    def __remove_assignment(self):
        assignment_id = ConsoleRead.read_id("assignment")
        assignment = self.__service.find_assignment_by_id(assignment_id)
        deleted_entities = self.__service.delete_assignment_by_id(assignment_id)
        UndoManager.register_operation(self.__service, UndoHandler.DELETE_ASSIGNMENT, assignment, deleted_entities)
        UndoManager.register_operation_to_redo(self.__service, UndoHandler.ADD_ASSIGNMENT, assignment)

    def __update_assignment(self):
        assignment_id = ConsoleRead.read_id("assignment")
        assignment_description = input("Enter the assignment's new description: ")
        assignment_deadline = ConsoleRead.read_date()
        new_assignment = Assignment(assignment_id, assignment_description, assignment_deadline)
        assignment = self.__service.find_assignment_by_id(assignment_id)
        self.__service.update_assignment(assignment_id, new_assignment)
        UndoManager.register_operation(self.__service, UndoHandler.UPDATE_STUDENT, assignment)
        UndoManager.register_operation_to_redo(self.__service, UndoHandler.UPDATE_STUDENT, new_assignment)

    def __list_assignments(self):
        ConsolePrint.print_assignments(self.__service.list_assignments())

    def __give_assignment_to_student(self):
        self.__list_assignments()
        assignment_id = ConsoleRead.read_id("assignment")
        student_id = ConsoleRead.read_id("student")
        self.__service.give_assignment_to_student(assignment_id, student_id)
        UndoManager.register_operation(self.__service, UndoHandler.GIVE_ASSIGNMENT_TO_STUDENT, assignment_id, student_id)
        UndoManager.register_operation_to_redo(self.__service, UndoHandler.GIVE_ASSIGNMENT_TO_STUDENT_REDO, assignment_id, student_id)

    def __give_assigment_to_group(self):
        self.__list_assignments()
        assignment_id = ConsoleRead.read_id("assignment")
        group = input("Enter the group: ")
        if not ConsoleRead.check_if_input_is_number(group):
            raise UserInputException("Group number must be an integer")
        self.__service.give_assignment_to_group(assignment_id, int(group))
        UndoManager.register_operation(self.__service, UndoHandler.GIVE_ASSIGNMENT_TO_GROUP, assignment_id, group)
        UndoManager.register_operation_to_redo(self.__service, UndoHandler.GIVE_ASSIGNMENT_TO_GROUP_REDO, assignment_id, group)

    def __grade_student(self):
        student_id = ConsoleRead.read_id("student")
        assignment_id = ConsoleRead.read_id("assignment")
        grade = input("Enter the grade: ")
        if not ConsoleRead.check_if_input_is_number(grade):
            raise UserInputException("Grade needs to be an integer.")
        self.__service.grade_student(assignment_id, student_id, int(grade))
        UndoManager.register_operation(self.__service, UndoHandler.GRADE_STUDENT, assignment_id, student_id)
        UndoManager.register_operation_to_redo(self.__service, UndoHandler.GRADE_STUDENT_REDO, assignment_id, student_id, grade)

    def __list_students_who_received_assignments(self):
        ConsolePrint.print_assignments(self.__service.list_assignments())
        assignment_id = ConsoleRead.read_id("assignment")
        ConsolePrint.print_students(self.__service.filter_students_by_assignment_given(assignment_id))

    def __list_students_with_assignment_late(self):
        ConsolePrint.print_students(self.__service.filter_students_by_assignment_late())

    def __list_students_by_average_grade(self):
        ConsolePrint.print_students(self.__service.sort_students_by_average_grade())

    def __undo(self):
        UndoManager.undo()

    def __redo(self):
        UndoManager.redo()

    def menu(self):
        operations = {
            1: self.__add_student,
            2: self.__remove_student,
            3: self.__update_student,
            4: self.__list_students,
            5: self.__add_assignment,
            6: self.__remove_assignment,
            7: self.__update_assignment,
            8: self.__list_assignments,
            9: self.__give_assignment_to_student,
            10: self.__give_assigment_to_group,
            11: self.__grade_student,
            12: self.__list_students_who_received_assignments,
            13: self.__list_students_with_assignment_late,
            14: self.__list_students_by_average_grade,
            15: self.__undo,
            16: self.__redo
        }
        while True:
            ConsolePrint.print_menu()
            #try:
            operations[ConsoleRead.read_menu_entry()]()
            # except Exception as exception:
            #     print(exception)
