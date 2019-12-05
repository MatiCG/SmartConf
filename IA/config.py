from flask import Flask

class Synthesis:
  def __init__(self, array, text):
    self.array = array
    self.text = text

    def jsonify_this(self):
        return (json.dumps(self.__dict__))