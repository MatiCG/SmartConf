from flask import Flask, render_template, request, make_response, g
from config import app
import os
import socket
import random
import json

ALLOWED_EXTENSIONS = set(['txt', 'pdf'])


def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


@app.route('/', methods=['GET'])
def test():
    return("test")

@app.route('/getfile', methods=['POST'])
def getfile():
    file_name = request.form["file_name"]
    with open(file_name, 'r') as f:
        file_content = f.read()
    return file_content

if __name__ == "__main__":
    app.run(port=5000, debug=True, threaded=True)
