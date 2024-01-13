# // Use ValueError for general exception (e.g., negative price)
class InvalidProductNameException(ValueError):
    def __init__(self, message):
        super().__init__(message)