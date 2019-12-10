#!/usr/bin/env python3

import sys
from firebase import firebase

## PUSH
#firebase = firebase.FirebaseApplication("https://smartconf-eip-epitech.firebaseio.com", None)
# data =  {
#         'username': 'userr1',
#         'email': 'user1@gmail.com',
#         'password': 'uner1user1'
#     }
# result = firebase.post('/users',data)
# print(result)

#token = sys.argv[1]
#data = sys.argv[2]
#firebase = firebase.FirebaseApplication("https://smartconf-eip-epitech.firebaseio.com", None)
#result = firebase.put('/reunions/' + str(token), 'reunion_speech', data)

## GET
def firebaseGet():
    firebase = firebase.FirebaseApplication("https://fir-python-45d3c.firebaseio.com/", None)
    result = firebase.get("/python-example-f6d0b/Students/", '')
    print(result)
