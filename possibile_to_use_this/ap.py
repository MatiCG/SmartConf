#!/usr/bin/env python3

from flask import Flask
from config import begin_synthesis
from firebase import firebase

app = Flask(__name__)

firebase_sql = firebase.FirebaseApplication("https://smartconf-eip-epitech.firebaseio.com/", None)

class Synthesis():
    def __init__(self, token, text):
        result = firebase_sql.get('/reunions/' + token + "/reunion_subject", None)
        text = begin_synthesis(text, result.split(", "))
        self.token_id = token
        self.text_id = text
    def add_synth(self):
        result = firebase_sql.put('/reunions/' + self.token_id, 'reunion_speech', self.text_id)
        #print(result)


@app.route('/<reunion_id>/<filename>', methods=['POST', 'GET'])
def test(reunion_id, filename):
    synth = Synthesis(reunion_id, filename)
    synth.add_synth()
    return (synth.text_id)


if __name__ == "__main__":
    app.run(port=5000, debug=True, threaded=True)
