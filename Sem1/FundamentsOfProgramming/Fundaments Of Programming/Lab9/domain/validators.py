from datetime import date

from domain.exceptions import GradeValidatorException, AssignmentValidatorException, StudentValidatorException


class AssignmentValidator:
    @staticmethod
    def validate(assignment):
        """
        validates properties of a Assignment object, returns AssignmentValidatorException if fails
        :param assignment: object of type Assignment
        :return: None
        """
        if assignment.id < 0:
            raise AssignmentValidatorException("ID is negative.")


class StudentValidator:
    @staticmethod
    def validate(student):
        """
        validates properties of a Student object, returns StudentValidatorException if fails
        :param student: object of type Student
        :return: None
        """
        if not isinstance(student.id, int):
            raise StudentValidatorException("ID needs to be an integer.")
        if student.id < 0:
            raise StudentValidatorException("ID is negative.")
        if not isinstance(student.group, int):
            raise StudentValidatorException("Group needs to be an integer.")
        if student.group < 0:
            raise StudentValidatorException("Group is negative.")


class GradeValidator:
    @staticmethod
    def validate(grade):
        """
         validates properties of a Grade object, returns GradeValidatorException if fails
         :param grade: object of type Grade
         :return: None
        """
        if grade.assignment < 0:
            raise GradeValidatorException("Assignment ID is negative.")
        if grade.student < 0:
            raise GradeValidatorException("Student ID is negative.")
        if not 0 <= grade.grade <= 10:
            raise GradeValidatorException("Grade must be between 1 and 10.")