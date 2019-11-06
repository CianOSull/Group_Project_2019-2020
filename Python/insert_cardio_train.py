from firebase import firebase
import numpy as np
#id;
# age;
# gender;
# height;
# weight;
# ap_hi;
# ap_lo;
# cholesterol;
# gluc;
# smoke;
# alco;
# active;
# cardio
data = np.genfromtxt("cardio_train.csv", delimiter=',', dtype=None, encoding='utf-8-sig')

firebase = firebase.FirebaseApplication('https://medi-screen.firebaseio.com/', None)  # establish connection to database
data_length = len(data)

for i in range(1974, data_length):
    myString = data[i]
    mylist = myString.split(';')
    dict = {'id': mylist[0], 'age': mylist[1], 'gender': mylist[2], 'height': mylist[3], 'weight': mylist[4], 'ap_hi': mylist[5], 'ap_lo': mylist[6], 'cholesterol': mylist[7], 'gluc': mylist[8], 'smoke': mylist[9], 'alco': mylist[10], 'active': mylist[11], 'cardio': mylist[12]}  # create sample data
    res = firebase.post('/medi-screen/Cardio-Train', dict) # use the connection to send data
