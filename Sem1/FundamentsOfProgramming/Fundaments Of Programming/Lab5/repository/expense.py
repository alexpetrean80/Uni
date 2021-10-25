class Expense:
    def __init__(self, day=0, amount_of_money=0, type_of_expense=''):
        self.__day = day
        self.__amount_of_money = amount_of_money
        self.__type_of_expense = type_of_expense

    @property
    def day(self):
        return self.__day

    @day.setter
    def day(self, day):
        self.__day = day

    @property
    def amount_of_money(self):
        return self.__amount_of_money

    @amount_of_money.setter
    def amount_of_money(self, amount):
        self.__amount_of_money = amount

    @property
    def type(self):
        return self.__type_of_expense

    @type.setter
    def type(self, type_of_expense):
        self.__type_of_expense = type_of_expense

    def __eq__(self, other):
        return vars(self) == vars(other)

