from Domain.exceptions import ValidatorError


class ShipValidator:
    @staticmethod
    def validate(ship):
        if not isinstance(ship.length, int):
            raise ValidatorError("Ship's length should be an integer.")
        if not 1 < ship.length < 6:
            raise ValidatorError("Ship's length must be between 2 and 5.")
        if not isinstance(ship.position, bool):
            raise ValidatorError("Ship's position should be either \"vertical\" or \"horizontal\".")
        if not isinstance(ship.coordinates, dict):
            raise ValidatorError("Ship's coordinates should be a pair of integers.")

