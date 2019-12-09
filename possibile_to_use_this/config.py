from flask import Flask
from firebase import firebase


firebase = firebase.FirebaseApplication("https://smartconf-eip-epitech.firebaseio.com/", None)

class Synthesis():
  def __init__(self, token, text):
    self.token = token
    self.text = text

    def add_synthesis():
        self.data.reunion_speech = text
        result = firebase.put('/reunion/' + str(token), 'reunion_speech', data)
        print(result)
