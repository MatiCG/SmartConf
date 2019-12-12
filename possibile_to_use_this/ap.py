#!/usr/bin/env python3

from flask import Flask
from config import begin_synthesis
from speech_to_text import script_pourri_de_guillaume
from firebase import firebase

app = Flask(__name__)

firebase_sql = firebase.FirebaseApplication("https://smartconf-eip-epitech.firebaseio.com/", None)

class Synthesis():
    def __init__(self, token, text):
        result = firebase_sql.get('/meetings/' + token + "/subject", None)
        text = begin_synthesis(text, result.split(", "))
        self.token_id = token
        self.text_id = text
    def add_synth(self):
        result = firebase_sql.put('/meetings/' + self.token_id, 'reunion_speech', self.text_id)
        #print(result)


@app.route('/<reunion_id>/<filename>', methods=['POST', 'GET'])
def test(reunion_id, filename):
    text = script_pourri_de_guillaume(filename)
    synth = Synthesis(reunion_id, text)
    synth.add_synth()
    return (synth.text_id)


if __name__ == "__main__":
    app.run(port=6000, debug=True, threaded=True)
