from flask import Flask
from firebase import firebase

firebase = firebase.FirebaseApplication("https://smartconf-eip-epitech.firebaseio.com/", None)

class Synthesis():
    def __init__(self, token, text):
        self.token_id = token
        self.text_id = text
    def add_synth(self):
        result = firebase.put('/reunions/' + self.token_id, 'reunion_speech', self.text_id)
        print(result)
