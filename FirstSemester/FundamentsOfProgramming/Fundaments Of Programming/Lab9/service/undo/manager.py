from dataclasses import dataclass

from domain.exceptions import UndoException


@dataclass
class UndoOperation:
    source_object: object
    handler: object
    arguments: tuple


class UndoManager:
    __undo_operations = list()
    __redo_operations = list()

    @staticmethod
    def register_operation(source_object, undo_handler, *arguments):
        UndoManager.__undo_operations.append(UndoOperation(source_object, undo_handler, arguments))

    @staticmethod
    def register_operation_to_redo(source_object, redo_handler, *arguments):
        UndoManager.__redo_operations.append(UndoOperation(source_object, redo_handler, arguments))

    @staticmethod
    def undo():
        if len(UndoManager.__undo_operations) == 0:
            raise UndoException("No operation is undoable.")
        undo_operation = UndoManager.__undo_operations.pop()
        undo_operation.handler(undo_operation.source_object, *undo_operation.arguments)

    @staticmethod
    def redo():
        if len(UndoManager.__redo_operations) == 0:
            raise UndoException("No operation is redoable.")
        redo_operation = UndoManager.__redo_operations.pop()
        redo_operation.handler(redo_operation.source_object, *redo_operation.arguments)


