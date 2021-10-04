from domain.validators import StudentValidator, AssignmentValidator, GradeValidator
from repo.repository import Repository
from service.services import Service
from ui.console import Console

if __name__ == '__main__':
    student_repository = Repository(StudentValidator)
    assignment_repository = Repository(AssignmentValidator)
    grade_repository = Repository(GradeValidator)
    service = Service(student_repository, assignment_repository, grade_repository)
    service.populate_students()
    service.populate_assignments()
    service.populate_grades()
    console = Console(service)
    console.menu()


