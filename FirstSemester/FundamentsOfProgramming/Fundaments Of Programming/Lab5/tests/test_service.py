from repository.expense import Expense
from service import Service


class TestService():
    @staticmethod
    def add_expense_to_list__correct__works():
        service = Service()
        expense = Expense(3, 200, 'pizza')
        service.add_expense_to_list(3, 200, 'pizza')
        assert(service.list_of_expenses[0] == expense) is True

    @staticmethod
    def add_expense_to_list__day_not_an_int__raises_type_error():
        service = Service()
        try:
            service.add_expense_to_list('fp', 500, 'rent')
            assert False
        except TypeError:
            pass

    @staticmethod
    def add_expense_to_list__day_bigger_than_thirty__raises_value_error():
        service = Service()
        try:
            service.add_expense_to_list(52, 500, 'rent')
        except ValueError:
            assert True

    @staticmethod
    def add_expense_to_list__day_negative__raises_value_error():
        service = Service()
        try:
            service.add_expense_to_list(-2, 500, 'rent')
        except ValueError:
            assert True

    @staticmethod
    def add_expense_to_list__money_not_an_int__raises_type_error():
        service = Service()
        try:
            service.add_expense_to_list(13, 'money', 'rent')
        except TypeError:
            assert True

    @staticmethod
    def add_expense_to_list__money_negative__raises_value_error():
        service = Service()
        try:
            service.add_expense_to_list(52, -500, 'rent')
        except ValueError:
            assert True

    def tests(self):
        self.add_expense_to_list__correct__works()
        self.add_expense_to_list__day_not_an_int__raises_type_error()
        self.add_expense_to_list__day_bigger_than_thirty__raises_value_error()
        self.add_expense_to_list__day_negative__raises_value_error()
        self.add_expense_to_list__money_not_an_int__raises_type_error()
        self.add_expense_to_list__money_negative__raises_value_error()

