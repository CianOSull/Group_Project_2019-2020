from firebase import firebase
import numpy as np

# Number of times pregnant - int/number
# Plasma glucose concentration a 2 hours in an oral glucose tolerance test - int/number
# Diastolic blood pressure (mm Hg) - int/number
# Triceps skin fold thickness (mm) - int/number
# 2-Hour serum insulin (mu U/ml) - int/number
# Body mass index (weight in kg/(height in m)^2) - float/number
# Diabetes pedigree function - int/number
# Age (years) - int/number
# class varaiable (target, so iether 0 or 1) - int/number
firebase = firebase.FirebaseApplication('https://medi-screen.firebaseio.com/', None)  # establish connection to database

data = np.genfromtxt("pimaIndiansDiabetesDatabase.csv", delimiter=',', dtype=None)

data_length = len(data)
for i in range(1, 51):
    list = data[i]

    dict = {'Number of pregnancies': int(list[0]), 'Plasma glucose concentration': int(list[1]), 'Diastolic blood pressure': int(list[2]), 'Triceps skin fold thickness': int(list[3]), '2-Hour serum insulin': int(list[4]), 'Body mass': float(list[5]), 'Diabetes pedigree function': float(list[6]), 'Age': int(list[7]), 'Class variable': int(list[8])}  # create sample data
    #print(dict.values())
    res = firebase.post('/medi-screen/Diabetes', dict)  # use the connection to send data

    print(res)

