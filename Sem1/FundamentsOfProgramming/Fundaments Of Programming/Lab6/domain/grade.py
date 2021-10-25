from dataclasses import dataclass


@dataclass
class Grade:
    __assignment: int
    __student: int
    __grade: int

    @property
    def assignment(self):
        return self.__assignment

    @assignment.setter
    def assignment(self, assignment_id):
        self.__assignment = assignment_id

    @property
    def student(self):
        return self.__student

    @student.setter
    def student(self, student_id):
        self.__student = student_id

    @property
    def grade(self):
        return self.__grade

    @grade.setter
    def grade(self, grade):
        self.__grade = grade
