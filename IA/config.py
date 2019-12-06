from flask import Flask
<<<<<<< HEAD
from firebase import firebase


firebase = firebase.FirebaseApplication("https://smartconf-eip-epitech.firebaseio.com/", None)

class Synthesis:
  def __init__(self, token, text):
    self.token = token
    self.text = text

    def add_synthesis(self):
        self.data.reunion_speech = text
        result = firebase.put('/reunion/' + str(self.token), 'reunion_speech', self.ata)
        print(result)

class user:
    def __init__(self, username, password, email):
        self.username = username
        self.password = password
        self.email = email

    def add_user(self):
        data = {'username': self.username,
                'email': self.email,
                'password': self.password
                }
        result = firebase.post('/user/', data)
=======

UPLOAD_FOLDER = '/home/'

app = Flask(__name__)
app.secret_key = "secret key"
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
app.config['MAX_CONTENT_LENGTH'] = 16 * 1024 * 1024
>>>>>>> 680d1cfc57d8d7d1cac4dfca44993fea8f709bec
