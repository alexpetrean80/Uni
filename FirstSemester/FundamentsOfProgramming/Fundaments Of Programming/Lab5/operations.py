from repository.expense import Expense
import copy


class Service:
    def __init__(self):
        self.list_of_expenses = list()
        self.stack_of_expenses = list()

    def populate_list(self):
        """
        populate the list of expenses with some hardcoded values. testing purposes only
        :return: None
        """
        self.list_of_expenses.append(Expense(5, 500, 'bills'))
        self.list_of_expenses.append(Expense(15, 200, 'groceries'))
        self.list_of_expenses.append(Expense(12, 1200, 'rent'))
        self.list_of_expenses.append(Expense(1, 50, 'cigarettes'))
        self.list_of_expenses.append(Expense(7, 100, 'insurance'))
        self.list_of_expenses.append(Expense(8, 1000, 'dentist'))
        self.list_of_expenses.append(Expense(3, 100, 'loan'))
        self.list_of_expenses.append(Expense(15, 400, 'insurance'))
        self.list_of_expenses.append(Expense(23, 2000, 'tuition'))
        self.list_of_expenses.append(Expense(28, 500, 'clothes'))

    def filter_list(self, value):
        """
        filter the list so that only expenses with a value higher than the given one remain in it
        :param value: the lower limit of the expenses which are to remain in the list(integer)
        :return: None
        """
        self.add_list_to_stack()
        if int(value) > 0:
            self.list_of_expenses = list(filter(lambda expense: expense.amount_of_money > value, self.list_of_expenses))
        else:
            raise ValueError("Value needs to be positive!")

    def add_expense_to_list(self, day, amount_of_money, type_of_expense):
        """
        create an expense from the given parameters and append it to the list
        :param day: day of the expense
        :param amount_of_money: cost of the expense
        :param type_of_expense: object of expense
        :return: None
        """
        expense = Expense()
        if isinstance(day, int) is True:
            if 0 < day <= 30:
                expense.day = int(day)
            else:
                raise ValueError('Day needs to be between 1 and 30.')
        else:
            raise TypeError('Day needs to be an integer.')
        if isinstance(amount_of_money, int) is True:
            if amount_of_money >= 0:
                expense.amount_of_money = int(amount_of_money)
            else:
                raise ValueError('Amount of money needs to be positive.')
        else:
            raise TypeError('Amount of money needs to be an integer.')
        self.add_list_to_stack()
        expense.type = type_of_expense
        self.list_of_expenses.append(expense)

    def add_list_to_stack(self):
        self.stack_of_expenses.append(copy.deepcopy(self.list_of_expenses))

    def undo(self):
        if len(self.stack_of_expenses) != 0:
            self.list_of_expenses = self.stack_of_expenses.pop()
        else:
            raise IndexError("There is no operation to undo.")

    def get_list_of_expenses(self):
        return self.list_of_expenses
