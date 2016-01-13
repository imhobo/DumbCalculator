
class Cache:
    def __init__(self, size):
        self.fileVar = open("templates/db.txt", "a+")
        self.SIZE = size


    def findQuery(self, str):
        self.fileVar.seek(0,0)
	self.fileVar.readline()
        while True:
            query = self.fileVar.readline()
            query = query.rstrip('<br>\n')
            result = self.fileVar.readline()
            result = result.rstrip('<br>\n')
            if not result : break
            
            if str == query:
                return result

        return "NO"

    def writeQuery(self, query, result):
        self.fileVar.seek(2,0)
        self.fileVar.write("<br>\n")
        self.fileVar.write(query)
        self.fileVar.write("<br>\n")
        self.fileVar.write(result)

if __name__ == "__main__":
    obj = Cache(20)
    obj.writeQuery("2 + 3","5")
    obj.writeQuery("4 * 3","5")
    #print obj.findQuery("2 + 3")
