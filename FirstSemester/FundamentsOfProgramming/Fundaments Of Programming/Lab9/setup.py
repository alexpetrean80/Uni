import re

from domain.assignment import Assignment
from domain.exceptions import SettingsException
from domain.grade import Grade
from domain.student import Student
from domain.validators import StudentValidator, AssignmentValidator, GradeValidator
from repo.console_repo.repository import Repo
from repo.file_repo.file_repo import FileRepo


class RepoType:
    @staticmethod
    def test_type(type: str) -> bool:
        if type == "console" or type == 'file' or type == 'pickle':
            return True
        raise TypeError("Repository needs to be either console, file or pickle")


class Setup:
    @staticmethod
    def trim_setting(setting: str) -> str:
        return  re.split(r"=", setting)[-1].strip() if len(setting[-1]) > 0 else ''

    @staticmethod
    def __read_settings():
        settings_file = open("settings.properties", 'r')
        lines = list()
        for line in settings_file:
            lines.append(Setup.trim_setting(line))
            print(Setup.trim_setting(line))
        repository_type, student_repository, assignment_repository, grade_repository = lines[0], lines[1], lines[2], lines[3]
        if repository_type != 'console':
            return repository_type, student_repository, assignment_repository, grade_repository
        else:
            if not (student_repository == assignment_repository == grade_repository == ""):
                raise SettingsException("If the repository is set to console, then there shouldn't be any other files specified.")
            return [repository_type]

    @staticmethod
    def setup():
        students, assignments, grades = 0, 0, 0
        settings = Setup.__read_settings()
        if settings[0] == "console":
            students = Repo(StudentValidator)
            assignments = Repo(AssignmentValidator)
            grades = Repo(GradeValidator)
        else:
            if settings[0] == "file":
                students = FileRepo(settings[1], Student, StudentValidator)
                assignments = FileRepo(settings[2], Assignment, AssignmentValidator)
                grades = FileRepo(settings[3], Grade, GradeValidator)
            else:
                pass
        return students, assignments, grades
