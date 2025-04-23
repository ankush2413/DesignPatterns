#------------------------------Builder Design Pattern---------------------------------------------------------------------------

"""
The Builder Pattern lets you construct a complex object in multiple steps and 
allows you to vary the internal representation of that object without changing the construction code.
Eg. I want to build a Pizza, and sometimes it has cheese, sometimes not, sometimes it’s thin crust, etc.
"""
#-------------------------------------------Use Case--------------------------------------------------------------------------
"""
The object you're creating has many optional fields or configurations.
You don’t want to pass 10 arguments to a constructor (ugly and confusing).
You want better readability and maintainability.
eg. Like a meal builder — sometimes you want fries, sometimes a drink, sometimes both. You don’t need a different constructor for every combo 
"""

#-------------------------------------------Code-----------------------------------------------------------------------------

class Computer:
    def __init__(self, cpu=None, ram=None, storage=None, gpu=None):
        self.cpu = cpu
        self.ram = ram
        self.storage = storage
        self.gpu = gpu

    def __str__(self):
        return f"CPU: {self.cpu}, RAM: {self.ram}, Storage: {self.storage}, GPU: {self.gpu}"

#Builder Class
class ComputerBuilder:
    def __init__(self):
        self.computer = Computer()

    def set_cpu(self, cpu):
        self.computer.cpu = cpu
        return self

    def set_ram(self, ram):
        self.computer.ram = ram
        return self

    def set_storage(self, storage):
        self.computer.storage = storage
        return self

    def set_gpu(self, gpu):
        self.computer.gpu = gpu
        return self

    def build(self):
        return self.computer

# Client_code
if __name__ == "__main__":

    builder = ComputerBuilder()

    gaming_pc = builder.set_cpu("Intel i9").set_ram("32GB").set_storage("1TB SSD").set_gpu("RTX 4080").build()
    print(gaming_pc)

    office_pc = builder.set_cpu("Intel i5").set_ram("16GB").set_storage("512GB SSD").set_gpu("None").build()
    print(office_pc)



