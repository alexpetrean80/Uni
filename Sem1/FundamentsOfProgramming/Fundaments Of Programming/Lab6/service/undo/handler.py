from enum import Enum


class ServiceHandler:

    @staticmethod
    def add_student_handler(service, student):
        service.delete_student_by_id(student.id)

    @staticmethod
    def delete_student_handler(service, student, grades):
        service.add_student(student)
        for grade in grades:
            service.give_assignment_to_student(grade.assignment, grade.student)

    @staticmethod
    def update_student_handler(service, student):
        service.update_student(student.id, student)

    @staticmethod
    def add_assignment_handler(service, assignment):
        service.delete_assignment_by_id(assignment.id)

    @staticmethod
    def delete_assignment_handler(service, assignment, grades):
        service.add_assignment(assignment)
        if len(grades) != 0:
            for grade in grades:
                service.give_assignment_to_student(grade.assignment, grade.student)

    @staticmethod
    def update_assignment_handler(service, assignment_id, old_assignment):
        service.update_assignment(assignment_id, old_assignment)

    @staticmethod
    def give_assignment_to_student_handler(service, assignment_id, student_id):
        service.delete_grade((assignment_id, student_id))

    @staticmethod
    def give_assignment_to_student_redo_handler(service, assignment_id, student_id):
        service.give_assignment_to_student(assignment_id, student_id)

    @staticmethod
    def give_assignment_to_group_handler(service, assignment_id, group):
        for student in service.__students.find_all():
            if student.group == group:
                for grade in service.__grades.find_all():
                    if grade.id == (assignment_id, student.id):
                        service.delete_grade(grade.id)

    @staticmethod
    def give_assignment_to_group_redo_handler(service, assignment_id, group):
        service.give_assignment_to_group(assignment_id, group)

    @staticmethod
    def grade_student_handler(service, assignment_id, student_id):
        service.delete_grade((assignment_id, student_id))
        service.give_assignment_to_student(assignment_id, student_id)

    @staticmethod
    def grade_student_redo_handler(service, assignment_id, student_id, grade):
        service.grade_student(assignment_id, student_id, grade)


class UndoHandler(Enum):
    ADD_STUDENT = ServiceHandler.add_student_handler
    DELETE_STUDENT = ServiceHandler.delete_student_handler
    UPDATE_STUDENT = ServiceHandler.update_student_handler
    ADD_ASSIGNMENT = ServiceHandler.add_assignment_handler
    DELETE_ASSIGNMENT = ServiceHandler.delete_assignment_handler
    UPDATE_ASSIGNMENT = ServiceHandler.update_assignment_handler
    GIVE_ASSIGNMENT_TO_STUDENT = ServiceHandler.give_assignment_to_student_handler
    GIVE_ASSIGNMENT_TO_STUDENT_REDO = ServiceHandler.give_assignment_to_student_redo_handler
    GIVE_ASSIGNMENT_TO_GROUP = ServiceHandler.give_assignment_to_group_handler
    GIVE_ASSIGNMENT_TO_GROUP_REDO = ServiceHandler.give_assignment_to_group_redo_handler
    GRADE_STUDENT = ServiceHandler.grade_student_handler
    GRADE_STUDENT_REDO = ServiceHandler.grade_student_redo_handler
