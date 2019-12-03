#!/usr/bin/env python3

from flask import Flask, render_template, request, make_response, g
from config import app
import os
import socket
import random
import json

ALLOWED_EXTENSIONS = set(['txt', 'pdf'])

def allowed_file(filename):
	return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


@app.route('/handle_form', methods=['POST'])
def handle_form():
    print("Posted file: {}".format(request.files['file']))
    file = request.files['file']
    return ""

@app.route("/")
def index():
    return render_template("index.html");

if __name__ == "__main__":
    app.run(host='127.0.0.1', port=80, debug=True, threaded=True)
