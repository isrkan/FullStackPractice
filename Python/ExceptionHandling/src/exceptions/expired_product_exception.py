# Use RuntimeException for exceptional cases that could not be anticipated abd a reasonable application might want to catch
class ExpiredProductException(RuntimeError):
    def __init__(self, message):
        super().__init__(message)