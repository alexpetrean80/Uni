import numpy

class GameTable:
    def __init__(self, length: int):
        self.__player_table = numpy.zeros((length, length))
        self.__ai_table = numpy.zeros((length, length))

    def populate_ai_table(self):
        pass


