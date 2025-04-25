# # Enums for log levels
# from enum import Enum
# import time
# from abc import ABC, abstractmethod

# class LogLevel(Enum):
#     INFO = 8
#     DEBUG = 2
#     ERROR = 3

#     def is_greater_or_equal(self, other):
#         return self.value >= other.value


# # LogMessage to encapsulate log data
# class LogMessage:
#     def __init__(self, level: LogLevel, message: str):
#         self.level = level
#         self.message = message
#         self.timestamp = int(time.time())

#     def __str__(self):
#         return f"[{self.level.name}] {self.timestamp}: {self.message}"


# # Strategy Pattern - LogAppender Interface
# class LogAppender(ABC):
#     @abstractmethod
#     def append(self, log_message: LogMessage):
#         pass


# class ConsoleAppender(LogAppender):
#     def append(self, log_message: LogMessage):
#         print(log_message)


# class FileAppender(LogAppender):
#     def __init__(self, file_path: str):
#         self.file_path = file_path

#     def append(self, log_message: LogMessage):
#         with open(self.file_path, 'a') as f:
#             f.write(str(log_message) + '\n')


# # LoggerConfig
# class LoggerConfig:
#     def __init__(self, log_level: LogLevel, log_appender: LogAppender):
#         self.log_level = log_level
#         self.log_appender = log_appender


# # Chain of Responsibility - LogHandler
# class LogHandler(ABC):
#     def __init__(self, level: LogLevel, appender: LogAppender):
#         self.level = level
#         self.appender = appender
#         self.next_logger = None

#     def set_next(self, next_logger):
#         self.next_logger = next_logger

#     def log_message(self, level: LogLevel, message: str):
#         if level == self.level:
#             self.write(message)
#         elif self.next_logger:
#             self.next_logger.log_message(level, message)

#     @abstractmethod
#     def write(self, message: str):
#         pass


# class InfoLogger(LogHandler):
#     def write(self, message: str):
#         self.appender.append(LogMessage(LogLevel.INFO, message))


# class DebugLogger(LogHandler):
#     def write(self, message: str):
#         self.appender.append(LogMessage(LogLevel.DEBUG, message))


# class ErrorLogger(LogHandler):
#     def write(self, message: str):
#         self.appender.append(LogMessage(LogLevel.ERROR, message))


# # Singleton Logger
# class Logger:
#     __instance = None

#     @staticmethod
#     def get_instance():
#         if Logger.__instance is None:
#             Logger()
#         return Logger.__instance

#     def __init__(self):
#         if Logger.__instance is not None:
#             raise Exception("This is a singleton class.")
#         else:
#             Logger.__instance = self
#             self.config = None

#     def set_config(self, config: LoggerConfig):
#         self.config = config

#     def _log(self, message: str, level: LogLevel):
#         if self.config and level.is_greater_or_equal(self.config.log_level):
#             self.config.log_appender.append(LogMessage(level, message))

#     def info(self, message: str):
#         self._log(message, LogLevel.INFO)

#     def debug(self, message: str):
#         self._log(message, LogLevel.DEBUG)

#     def error(self, message: str):
#         self._log(message, LogLevel.ERROR)


# # Main - Setup
# if __name__ == '__main__':
#     # Strategy appender setup
#     appender = ConsoleAppender()

#     # LoggerConfig
#     config = LoggerConfig(log_level=LogLevel.DEBUG, log_appender=appender)

#     # Logger (Singleton)
#     logger = Logger.get_instance()
#     logger.set_config(config)

#     # Usage
#     logger.info("System boot successful")
#     logger.debug("Debugging application")
#     logger.error("Unhandled exception occurred")

# Enums for log levels
from enum import Enum
import time
from abc import ABC, abstractmethod

class LogLevel(Enum):
    INFO = 1
    DEBUG = 2
    ERROR = 3

    def is_greater_or_equal(self, other):
        return self.value >= other.value


# LogMessage to encapsulate log data
class LogMessage:
    def __init__(self, level: LogLevel, message: str):
        self.level = level
        self.message = message
        self.timestamp = int(time.time())

    def __str__(self):
        return f"[{self.level.name}] {self.timestamp}: {self.message}"


# Strategy Pattern - LogAppender Interface
class LogAppender(ABC):
    @abstractmethod
    def append(self, log_message: LogMessage):
        pass


class ConsoleAppender(LogAppender):
    def append(self, log_message: LogMessage):
        print(log_message)


class FileAppender(LogAppender):
    def __init__(self, file_path: str):
        self.file_path = file_path

    def append(self, log_message: LogMessage):
        with open(self.file_path, 'a') as f:
            f.write(str(log_message) + '\n')


# LoggerConfig
class LoggerConfig:
    def __init__(self, log_level: LogLevel, log_appender: LogAppender):
        self.log_level = log_level
        self.log_appender = log_appender

    def get_log_level(self):
        return self.log_level

    def get_log_appender(self):
        return self.log_appender


# Chain of Responsibility - LogHandler
class LogHandler(ABC):
    def __init__(self, level: LogLevel, appender: LogAppender):
        self.level = level
        self.appender = appender
        self.next_logger = None

    def set_next(self, next_logger):
        self.next_logger = next_logger

    def log_message(self, level: LogLevel, message: str):
        if self.level == level:
            self.write(message)
        elif self.next_logger:
            self.next_logger.log_message(level, message)

    @abstractmethod
    def write(self, message: str):
        pass


class InfoLogger(LogHandler):
    def write(self, message: str):
        self.appender.append(LogMessage(LogLevel.INFO, message))


class DebugLogger(LogHandler):
    def write(self, message: str):
        self.appender.append(LogMessage(LogLevel.DEBUG, message))


class ErrorLogger(LogHandler):
    def write(self, message: str):
        self.appender.append(LogMessage(LogLevel.ERROR, message))


# Singleton Logger (Centralized Logging - avoids duplication)
class Logger:
    __instance = None

    @staticmethod
    def get_instance():
        if Logger.__instance is None:
            Logger()
        return Logger.__instance

    def __init__(self):
        if Logger.__instance is not None:
            raise Exception("This is a singleton class.")
        else:
            Logger.__instance = self
            self.config = None

    def set_config(self, config: LoggerConfig):
        self.config = config

    def log(self, level: LogLevel, message: str):
        if self.config and level.is_greater_or_equal(self.config.get_log_level()):
            log_message = LogMessage(level, message)
            self.config.get_log_appender().append(log_message)

    def info(self, message: str):
        self.log(LogLevel.INFO, message)

    def debug(self, message: str):
        self.log(LogLevel.DEBUG, message)

    def error(self, message: str):
        self.log(LogLevel.ERROR, message)


# Main - Setup
if __name__ == '__main__':
    # Using Chain of Responsibility approach (optional, can be skipped in favor of centralized Logger)
    console_appender = ConsoleAppender()

    error_logger = ErrorLogger(LogLevel.ERROR, console_appender)
    debug_logger = DebugLogger(LogLevel.DEBUG, console_appender)
    info_logger = InfoLogger(LogLevel.INFO, console_appender)

    info_logger.set_next(debug_logger)
    debug_logger.set_next(error_logger)

    info_logger.log_message(LogLevel.INFO, "Info using CoR")
    info_logger.log_message(LogLevel.DEBUG, "Debug using CoR")
    info_logger.log_message(LogLevel.ERROR, "Error using CoR")

    print("\n--- Using Singleton Logger (Recommended) ---")

    # Centralized Logger setup
    config = LoggerConfig(LogLevel.INFO, console_appender)
    logger = Logger.get_instance()
    logger.set_config(config)

    logger.info("System boot successful")
    logger.debug("Debugging application")
    logger.error("Unhandled exception occurred")
