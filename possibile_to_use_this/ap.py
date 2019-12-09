#!/usr/bin/env python3

from flask import Flask, render_template, request, make_response, g
#from config import *
import os
import socket
import random
from firebase import firebase
import json

app = Flask(__name__)

firebase = firebase.FirebaseApplication("https://smartconf-eip-epitech.firebaseio.com/", None)

class Synthesis():
    def __init__(self, token, text):
        self.token_id = token
        self.text_id = text
    def add_synth(self):
        result = firebase.put('/reunions/' + self.token_id, 'reunion_speech', self.text_id)
        print(result)


@app.route('/<reunion_id>/<text>', methods=['POST'])
def test(reunion_id, text):
    synth = Synthesis(reunion_id, text)
    synth.add_synth()
    return ("done")


if __name__ == "__main__":
    app.run(port=5000, debug=True, threaded=True)
