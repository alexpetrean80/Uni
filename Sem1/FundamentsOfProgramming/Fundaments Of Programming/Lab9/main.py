from repo.console_repo.repository import Repo
from service.services import Service
from setup import Setup
from ui.console import Console

if __name__ == '__main__':
    repos = Setup.setup()
    service = Service(*repos)
    if isinstance(repos[0], Repo):
        service.populate_students()
        service.populate_assignments()
        service.populate_grades()
    console = Console(service)
    console.menu()


