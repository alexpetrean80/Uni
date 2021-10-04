from tests.test_service import TestService
from ui.console import UI

if __name__ == '__main__':
    test_service = TestService()
    test_service.tests()
    console = UI()
    console.console_menu()
