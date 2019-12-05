#!/usr/bin/env python3

from firebase import firebase

## PUSH
firebase = firebase.FirebaseApplication("https://smartconf-eip-epitech.firebaseio.com/", None)
data =  { 'id': 0,
    'leader_id': 0,
    'reunion_title': 'test_reunion',
    'reunion_date' : '12/12/2019',
    'reunion_status': 'N/A',
    'reunion_subject': 'word1, word2, word3, word4'
    }
result = firebase.post('/reunions/',data)
print(result)

## GET
#def firebaseGet():
#    firebase = firebase.FirebaseApplication("https://fir-python-45d3c.firebaseio.com/", None)
#    result = firebase.get("/python-example-f6d0b/Students/", '')
#    print(result)