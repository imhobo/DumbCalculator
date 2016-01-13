
class Parser():
    def __init__(self):
        pass

    def parse(self, str):
        l = str.split()
        op1 = l[0]
        oper = l[1]
        op2 = l[2]

        if oper == "+": result = self.plus(op1,op2)
        elif oper == "-": result = self.minus(op1,op2)
        elif oper == "*": result = self.mul(op1,op2)
        elif oper == "/": result = self.div(op1,op2)
        else: result = "Invalid Operator"

        return self.clean(result)

    def plus(self, op1, op2):
        return str(float(op1) + float(op2))

    def minus(self, op1, op2):
        return str(float(op1) - float(op2))

    def mul(self, op1, op2):
        return str(float(op1) * float(op2))

    def div(self, op1, op2):
        if op2 == "0": return "Div by 0 error"
        return str(float(op1) / float(op2))

    def clean(self, str):
        length = len(str)
        if length<2 : return str
        if str[length-2] == "." and str[length-1] == "0" : return str[:-2]
        return str

if __name__ == '__main__':
    obj = Parser()
    print obj.parse("2 / 4")
